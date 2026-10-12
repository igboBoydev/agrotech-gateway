package com.agrotech.gateway.config;

import io.grpc.Metadata;
import org.springframework.http.HttpHeaders;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public final class GrpcMetadata {

    private static final Set<String> SKIP = Set.of(
            "host", "connection", "content-length", "content-type", "transfer-encoding",
            "te", "keep-alive", "upgrade", "expect", "user-agent", "accept-encoding",
            "proxy-authorization", "trailer");

    private static final Pattern PRINTABLE_ASCII = Pattern.compile("[\\x20-\\x7E]*");

    private GrpcMetadata() {}

    public static Metadata from(HttpHeaders headers) {
        Metadata metadata = new Metadata();
        headers.forEach((name, values) -> {
            String key = name.toLowerCase(Locale.ROOT);
            if (SKIP.contains(key) || key.startsWith("grpc-") || key.endsWith("-bin")) {
                return;
            }
            Metadata.Key<String> metaKey;
            try {
                metaKey = Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER);
            } catch (IllegalArgumentException e) {
                return; // illegal characters in the header name
            }
            for (String value : values) {
                if (PRINTABLE_ASCII.matcher(value).matches()) {
                    metadata.put(metaKey, value);
                }
            }
        });
        return metadata;
    }
}
