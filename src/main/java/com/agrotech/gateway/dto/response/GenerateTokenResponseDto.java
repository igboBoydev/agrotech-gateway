package com.agrotech.gateway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenerateTokenResponseDto {
    private String token;
    private String refresh_token;
}
