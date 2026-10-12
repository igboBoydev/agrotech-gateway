package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddFarmProductRequestDto {
    private String farmId;
    private String productName;
    private String category;
    private String productType;
    private String description;
    private Double quantity;
    private String unit;
    private Double pricePerUnit;
    private String harvestDate;
    private String expiryDate;
    private Boolean isAvailable;
}
