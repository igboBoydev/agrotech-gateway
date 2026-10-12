package com.agrotech.gateway.dto.response;

import com.agrotech.gateway.dto.ProductsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetProductsResponseDto {
    private List<ProductsDto> productsDto;
}
