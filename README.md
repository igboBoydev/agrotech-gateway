# WebFlux REST-to-gRPC Gateway

Java 17 / Spring Boot 3.4.1 gateway example. REST requests are handled by Spring WebFlux and forwarded to a downstream gRPC service using the asynchronous gRPC stub; no blocking gRPC stub is used.

## Run

Requirements: JDK 17+ and Maven 3.9+.

```bash
mvn clean package
GRPC_HOST=localhost GRPC_PORT=9090 java -jar target/webflux-grpc-gateway-0.0.1-SNAPSHOT.jar
```

The gRPC server must implement the service in `src/main/proto/credit_bureau.proto` and be reachable at the configured host and port.

## Example REST request

```bash
curl -X POST http://localhost:8080/api/v1/credit-bureau/analyze \
  -H 'Content-Type: application/json' \
  -d '{"customerId":"CUST-1001","bvn":"12345678901","requestedAmount":250000}'
```

Expected response shape:

```json
{
  "requestId": "REQ-123",
  "status": "SUCCESS",
  "message": "Analysis completed",
  "creditScore": 720
}
```

The response above is illustrative; actual values come from the gRPC server.

## Adapt to your existing gRPC services

1. Replace `src/main/proto/credit_bureau.proto` with your actual `.proto` definitions, or add your service `.proto` files under `src/main/proto`.
2. Run `mvn clean compile` to generate Java protobuf messages and gRPC stubs.
3. Update `GrpcClientConfig` to expose the generated async stub for your service.
4. Map REST DTOs to protobuf request builders and map protobuf responses to REST DTOs.
5. Configure TLS for non-local environments. This starter uses plaintext gRPC (`usePlaintext()`), intended only for local development/trusted isolated networks.
6. Add your authentication/authorization filters or Spring Security configuration. No authentication is enabled by this template.
7. Do not blindly retry non-idempotent banking/payment RPCs; use idempotency keys and service-side deduplication where applicable.

## Notes

- gRPC async callbacks are adapted to Reactor `Mono`, so they do not block WebFlux event-loop threads.
- A five-second gRPC deadline is configured in the sample service.
- The included error handler maps common gRPC status codes to HTTP status codes.
- Keep sensitive values such as BVNs out of logs and configure TLS, secrets, rate limits, and request-size limits before production.
