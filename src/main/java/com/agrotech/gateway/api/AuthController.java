package com.agrotech.gateway.api;

import com.agrotech.gateway.dto.request.*;
import com.agrotech.gateway.dto.response.*;
import com.agrotech.gateway.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/generate-token", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GenerateTokenResponseDto> generateToken(@Valid @RequestBody GenerateTokenRequestDto request) {
        return authService.generateToken(request);
    }

    @PostMapping(value = "/validate-token", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TokenResponseDto> validateToken(@Valid @RequestBody TokenRequestDto request) {
        return authService.validateToken(request);
    }

    @PostMapping(value = "/validate-email", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ValidateEmailResponseDto> validateEmail(@Valid @RequestBody ValidateEmailRequestDto request) {
        return authService.validateEmail(request);
    }

    @PostMapping(value = "/validate-phone", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ValidatePhoneResponseDto> validatePhone(@Valid @RequestBody ValidatePhoneRequestDto request) {
        return authService.validatePhone(request);
    }

    @PostMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ChangeFarmerPasswordResponseDto> changeFarmerPassword(@Valid @RequestBody ChangeFarmerPasswordRequestDto request) {
        return authService.changeFarmerPassword(request);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<FarmerLoginResponseDto> farmerLogin(@Valid @RequestBody FarmerLoginRequestDto request) {
        return authService.farmerLogin(request);
    }
}
