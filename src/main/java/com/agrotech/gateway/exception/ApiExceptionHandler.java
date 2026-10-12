package com.agrotech.gateway.exception;

import io.grpc.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(GrpcGatewayException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleGrpc(GrpcGatewayException ex) {
        Status.Code code = ex.getStatus().getCode();
        HttpStatus httpStatus = switch (code) {
            case INVALID_ARGUMENT -> HttpStatus.BAD_REQUEST;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case UNAUTHENTICATED -> HttpStatus.UNAUTHORIZED;
            case PERMISSION_DENIED -> HttpStatus.FORBIDDEN;
            case DEADLINE_EXCEEDED -> HttpStatus.GATEWAY_TIMEOUT;
            case UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
            case ALREADY_EXISTS, FAILED_PRECONDITION, OUT_OF_RANGE -> HttpStatus.CONFLICT;
            default -> HttpStatus.BAD_GATEWAY;
        };

        String message = ex.getStatus().getDescription();
        if (message == null || message.isBlank()) {
            message = "The downstream gRPC service could not process the request";
        }

        return Mono.just(ResponseEntity.status(httpStatus)
                .body(Map.of("code", code.name(), "message", message)));
    }

    public static class GrpcGatewayException extends RuntimeException {
        private final Status status;

        public GrpcGatewayException(Status status, Throwable cause) {
            super(status.getDescription(), cause);
            this.status = status;
        }

        public Status getStatus() {
            return status;
        }
    }
}
