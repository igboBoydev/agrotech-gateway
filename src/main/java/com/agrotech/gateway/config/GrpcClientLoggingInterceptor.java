package com.agrotech.gateway.config;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.TextFormat;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Slf4j
public class GrpcClientLoggingInterceptor implements ClientInterceptor {

    private static final int MAX_PAYLOAD_CHARS = 2000;

    private static final Pattern SENSITIVE = Pattern.compile(
            "(?i)((?:password|token|secret|pin)\\s*:\\s*)\"(?:[^\"\\\\]|\\\\.)*\"");

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {

        final String name = method.getFullMethodName();
        final long startNanos = System.nanoTime();

        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                log.info("gRPC client call started: {} deadline={}", name, callOptions.getDeadline());

                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<>(responseListener) {

                    @Override
                    public void onMessage(RespT message) {
                        log.info("gRPC client response: {} payload={}", name, format(message));
                        super.onMessage(message);
                    }

                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        long ms = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos);
                        if (status.isOk()) {
                            log.info("gRPC client call finished: {} status=OK duration={}ms", name, ms);
                        } else {
                            log.warn("gRPC client call finished: {} status={} description={} duration={}ms",
                                    name, status.getCode(), status.getDescription(), ms);
                        }
                        super.onClose(status, trailers);
                    }
                }, headers);
            }

            @Override
            public void sendMessage(ReqT message) {
                log.info("gRPC client request: {} payload={}", name, format(message));
                super.sendMessage(message);
            }
        };
    }

    private String format(Object message) {
        String text = message instanceof MessageOrBuilder m
                ? TextFormat.printer().emittingSingleLine(true).printToString(m)
                : String.valueOf(message);
        text = SENSITIVE.matcher(text).replaceAll("$1\"***\"");
        return text.length() > MAX_PAYLOAD_CHARS
                ? text.substring(0, MAX_PAYLOAD_CHARS) + "...(truncated)"
                : text;
    }
}
