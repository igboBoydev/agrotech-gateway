package com.agrotech.gateway.service;

import com.agrotech.gateway.dto.request.*;
import com.agrotech.gateway.dto.response.*;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<GenerateTokenResponseDto> generateToken(GenerateTokenRequestDto request);
    Mono<TokenResponseDto> validateToken(TokenRequestDto request);
    Mono<ValidateEmailResponseDto> validateEmail(ValidateEmailRequestDto request);
    Mono<ValidatePhoneResponseDto> validatePhone(ValidatePhoneRequestDto request);
    Mono<ChangeFarmerPasswordResponseDto> changeFarmerPassword(ChangeFarmerPasswordRequestDto request);
    Mono<FarmerLoginResponseDto> farmerLogin(FarmerLoginRequestDto request);
}
