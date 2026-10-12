package com.agrotech.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductsDto {
    private String id;
    private String name;
    private String category;
    private String type;
    private Double quantity;
    private String unit;
    private Double pricePerUnit;
    private Boolean isAvailable;
}
