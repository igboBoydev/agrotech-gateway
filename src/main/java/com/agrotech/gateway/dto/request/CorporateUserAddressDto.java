package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CorporateUserAddressDto {
    private String id;
    private String companyAddress;
    private String state;
    private String city;
    private String country;
    private String primaryCompanyAddress;
    private String primaryCompanyState;
    private String primaryCompanyCity;
    private String primaryCompanyCountry;
    private String address;
    private String verificationStatus;
    private String createdAt;
    private String updatedAt;
    private String primaryCompanyLga;
}
