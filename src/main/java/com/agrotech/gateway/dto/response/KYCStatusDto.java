package com.agrotech.gateway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class KYCStatusDto {
    private String verificationStatus;
    private String message;
}
