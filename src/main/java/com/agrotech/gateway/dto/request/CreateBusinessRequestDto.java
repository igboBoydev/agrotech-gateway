package com.agrotech.gateway.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateBusinessRequestDto {
    @NotBlank
    private String companyName;

    @NotBlank
    private String companyRegistrationNo;

    private String dateOfRegistration;

    @NotBlank
    private String countryOfRegistration;

    @NotBlank
    private String companyAddress;

    private String state;
    private String city;
    private String country;

    private String lastName;

    @Pattern(regexp = "^$|\\d{11}", message = "BVN must be 11 digits")
    private String bvn;

    private String tin;
    private String address;
}
