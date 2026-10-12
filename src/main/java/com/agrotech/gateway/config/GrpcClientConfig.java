package com.agrotech.gateway.config;

import auth.AuthDataServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import users.UsersServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Bean(destroyMethod = "shutdown")
    ManagedChannel grpcChannel(
            @Value("${grpc.client.host:localhost}") String host,
            @Value("${grpc.client.port:8080}") int port) {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .intercept(new GrpcClientLoggingInterceptor())
                .build();
    }

    @Bean
    public AuthDataServiceGrpc.AuthDataServiceStub authStub(ManagedChannel authChannel) {
        return AuthDataServiceGrpc.newStub(authChannel);
    }

    @Bean
    public UsersServiceGrpc.UsersServiceStub onboardingStub(ManagedChannel onboardingChannel){
        return UsersServiceGrpc.newStub(onboardingChannel);
    }
}
