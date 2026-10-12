package com.agrotech.gateway.service;

import com.agrotech.gateway.dto.request.*;
import com.agrotech.gateway.dto.response.*;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public interface OnboardingService {
    Mono<CreateFarmerResponseDto> createFarmer(CreateFarmerRequestDto request);

    Mono<AddFarmerKycResponseDto> addFarmerKyc(AddFarmerKycRequestDto request, HttpHeaders headers);

    Mono<AddFarmResponseDto> addFarm(AddFarmRequestDto request, HttpHeaders headers);

    Mono<FarmerProfileResponseDto> getFarmersProfile(String userId, HttpHeaders headers);

    Mono<GetFarmsResponseDto> getFarms(GetFarmsRequestDto request, HttpHeaders headers);

    Mono<KYCStatusDto> checkKycStatus(KYCRequestDto request, HttpHeaders headers);

    Mono<CreateBusinessResponseDto> createBusiness(CreateBusinessRequestDto request, HttpHeaders headers);

    Mono<GetBusinessProfileResponseDto> getBusinessProfile(GetBusinessProfileRequestDto request, HttpHeaders headers);

    Mono<GetProductsResponseDto> getFarmProducts(GetProductsRequestDto request, HttpHeaders headers);

    Mono<AddFarmProductResponseDto> addFarmProduct(AddFarmProductRequestDto request, HttpHeaders headers);
}
