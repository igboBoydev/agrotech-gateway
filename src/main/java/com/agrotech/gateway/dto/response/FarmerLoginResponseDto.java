package com.agrotech.gateway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FarmerLoginResponseDto {
    private String token;
    private String message;
    private String code;
    private String uuid;
}
