package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddFarmerKycRequestDto {
    private String id;
    private String signature;
    private String bvn;
    private String tin;
    private String passportPhotograph;
    private String kycLevel;
    private String tier;
    private boolean tinVerified;
    private String drivingLicense;
    private String internationalPassportNumber;
    private String votersCardNumber;
    private List<DocumentDto> documentsAndIdentity = new ArrayList<>();
    private CorporateUserAddressDto corporateCustomerAddress;
}
