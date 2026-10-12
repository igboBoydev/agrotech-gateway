package com.agrotech.gateway.api;

import com.agrotech.gateway.dto.request.*;
import com.agrotech.gateway.dto.response.*;
import com.agrotech.gateway.service.OnboardingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/onboarding")
public class OnboardingController {

    private final OnboardingService onboardingService;

    @PostMapping(value = "/create-farmer", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CreateFarmerResponseDto> createFarmer(@Valid @RequestBody CreateFarmerRequestDto request) {
        return onboardingService.createFarmer(request);
    }

    @PostMapping(value = "/add-farmer-kyc", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AddFarmerKycResponseDto> addFarmerKyc(@Valid @RequestBody AddFarmerKycRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.addFarmerKyc(request, headers);
    }

    @PostMapping(value = "/add-farm", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AddFarmResponseDto> addFarm(@Valid @RequestBody AddFarmRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.addFarm(request, headers);
    }

    @GetMapping(value = "/get-farmer-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<FarmerProfileResponseDto> getFarmersProfile(@RequestParam String userId,  @RequestHeader HttpHeaders headers) {
        return onboardingService.getFarmersProfile(userId, headers);
    }

    @GetMapping(value = "/get-farm", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GetFarmsResponseDto> getFarms(@Valid @RequestParam GetFarmsRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.getFarms(request, headers);
    }

    @GetMapping(value = "/check-farmer-kyc-status", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<KYCStatusDto> checkKycStatus(@Valid @RequestParam KYCRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.checkKycStatus(request, headers);
    }

    @PostMapping(value = "/create-business", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CreateBusinessResponseDto> createBusiness(@Valid @RequestBody CreateBusinessRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.createBusiness(request, headers);
    }

    @GetMapping(value = "/get-business-profile", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GetBusinessProfileResponseDto> getBusinessProfile(@Valid @RequestParam GetBusinessProfileRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.getBusinessProfile(request, headers);
    }

    @GetMapping(value = "/get-farm-products", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<GetProductsResponseDto> getFarmProducts(@Valid @RequestParam GetProductsRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.getFarmProducts(request, headers);
    }

    @PostMapping(value = "/add-farm-products", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AddFarmProductResponseDto> addFarmProduct(@Valid @RequestBody AddFarmProductRequestDto request, @RequestHeader HttpHeaders headers) {
        return onboardingService.addFarmProduct(request, headers);
    }
}
