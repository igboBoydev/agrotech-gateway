package com.agrotech.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerOptionsDto {
    private String customerCreatedAt;
    private String isActive; // a string in the proto, not a bool
}
