package com.agrotech.gateway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddFarmerKycResponseDto {
    private String message;
    private String code;
}
