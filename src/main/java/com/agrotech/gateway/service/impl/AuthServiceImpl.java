package com.agrotech.gateway.service.impl;

import auth.Auth;
import auth.AuthDataServiceGrpc;
import com.agrotech.gateway.dto.request.*;
import com.agrotech.gateway.dto.response.*;
import com.agrotech.gateway.exception.ApiExceptionHandler;
import com.agrotech.gateway.service.AuthService;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthDataServiceGrpc.AuthDataServiceStub asyncStub;

    @Override
    public Mono<GenerateTokenResponseDto> generateToken(GenerateTokenRequestDto request) {

        // map REST DTO -> proto (adjust setters to your .proto fields)
        Auth.GenerateTokenRequest grpcRequest = Auth.GenerateTokenRequest.newBuilder()
                .setEmail(request.getEmail())
                .build();

        return Mono.<GenerateTokenResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .generateToken(grpcRequest, new StreamObserver<Auth.GenerateTokenResponse>() {

                                    @Override
                                    public void onNext(Auth.GenerateTokenResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new GenerateTokenResponseDto(
                                                response.getToken(),
                                                response.getRefreshToken()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<TokenResponseDto> validateToken(TokenRequestDto request) {
        Auth.ValidateType type;
        if(request.getValidateType().equals("PHONE")){
            type = Auth.ValidateType.PHONE;
        }else if (request.getValidateType().equals("EMAIL")){
            type = Auth.ValidateType.EMAIL;
        }else {
            type = Auth.ValidateType.LOGIN;
        }

        Auth.TokenRequest grpcRequest = Auth.TokenRequest.newBuilder()
                .setToken(request.getToken())
                .setValidateType(type)
                .build();

        return Mono.<TokenResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .validateToken(grpcRequest, new StreamObserver<Auth.TokenResponse>() {

                                    @Override
                                    public void onNext(Auth.TokenResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new TokenResponseDto(
                                                response.getCode(),
                                                response.getMessage()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<ValidateEmailResponseDto> validateEmail(ValidateEmailRequestDto request) {
        Auth.ValidateEmailRequest grpcRequest = Auth.ValidateEmailRequest.newBuilder()
                .setEmail(request.getEmail())
                .build();

        return Mono.<ValidateEmailResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .validateEmail(grpcRequest, new StreamObserver<Auth.ValidateEmailResponse>() {

                                    @Override
                                    public void onNext(Auth.ValidateEmailResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new ValidateEmailResponseDto(
                                                response.getMessage(),
                                                response.getCode()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<ValidatePhoneResponseDto> validatePhone(ValidatePhoneRequestDto request) {
        Auth.ValidatePhoneRequest grpcRequest = Auth.ValidatePhoneRequest.newBuilder()
                .setPhone(request.getPhone())
                .build();

        return Mono.<ValidatePhoneResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .validatePhone(grpcRequest, new StreamObserver<Auth.ValidatePhoneResponse>() {

                                    @Override
                                    public void onNext(Auth.ValidatePhoneResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new ValidatePhoneResponseDto(
                                                response.getMessage(),
                                                response.getCode()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<ChangeFarmerPasswordResponseDto> changeFarmerPassword(ChangeFarmerPasswordRequestDto request) {
        Auth.ChangeFarmerPasswordRequest grpcRequest = Auth.ChangeFarmerPasswordRequest.newBuilder()
                .setPassword(request.getPassword())
                .build();

        return Mono.<ChangeFarmerPasswordResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .changeFarmerPassword(grpcRequest, new StreamObserver<Auth.ChangeFarmerPasswordResponse>() {

                                    @Override
                                    public void onNext(Auth.ChangeFarmerPasswordResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new ChangeFarmerPasswordResponseDto(
                                                response.getMessage(),
                                                response.getCode()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<FarmerLoginResponseDto> farmerLogin(FarmerLoginRequestDto request) {
        Auth.FarmerLoginRequest grpcRequest = Auth.FarmerLoginRequest.newBuilder()
                .setPassword(request.getPassword())
                .setEmail(request.getEmail())
                .build();

        return Mono.<FarmerLoginResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .farmerLogin(grpcRequest, new StreamObserver<Auth.FarmerLoginResponse>() {

                                    @Override
                                    public void onNext(Auth.FarmerLoginResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new FarmerLoginResponseDto(
                                                response.getToken(),
                                                response.getMessage(),
                                                response.getCode(),
                                                response.getUuid()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

}
