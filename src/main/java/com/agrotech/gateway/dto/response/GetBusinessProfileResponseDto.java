package com.agrotech.gateway.dto.response;

import com.agrotech.gateway.dto.CustomerOptionsDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetBusinessProfileResponseDto {
    private String companyName;
    private String companyRegistrationNo;
    private String dateOfRegistration;
    private String countryOfRegistration;
    private String companyAddress;
    private String state;
    private String city;
    private String country;
    private String lastName;
    private String bvn;
    private String tin;
    private String address;
    private String principalOfficerMiddleName;
    private String sex;
    private String dateOfBirth;
    private String title;
    private String contactPhoneNumber;
    private String alternatePhone;
    private String officePhoneNumber;
    private String emailAddress;
    private String nin;
    private String idType;
    private String dateOfExpiration;
    private String placeOfIssuance;
    private String idNumber;
    private String countryOfIssuance;
    private String dateOfIssuance;
    private String fathersName;
    private String mothersName;
    private String nationality;
    private String businessCategory;
    private String ownershipStructure;
    private String product;
    private String branchId;
    private String signature;
    private String photoUpload;
    private String introducer;
    private String rsm;
    private String restriction;
    private String principalOfficerLastname;
    private String principalOfficerFirstName;
    private String primaryCompanyAddress;
    private String primaryCompanyState;
    private String primaryCompanyCity;
    private String primaryCompanyCountry;
    private String firstName;
    private String middleName;
    private String productId;
    private Boolean smsNotification;
    private Boolean emailNotification;
    private CustomerOptionsDto customerOptions;
    // Boolean wrapper so JSON stays "is_linked_account" (a primitive would become "linked_account")
    private Boolean isLinkedAccount;
    private String linkedAccountNumber;
    private String creationChannelCode;
}
