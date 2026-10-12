package com.agrotech.gateway.dto.response;

import com.agrotech.gateway.dto.Farm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetFarmsResponseDto {
    private List<Farm> farms;
}
