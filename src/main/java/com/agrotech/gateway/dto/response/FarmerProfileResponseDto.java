package com.agrotech.gateway.dto.response;

import com.agrotech.gateway.dto.request.FarmerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FarmerProfileResponseDto {
    private FarmerDto farmerDto;
}
