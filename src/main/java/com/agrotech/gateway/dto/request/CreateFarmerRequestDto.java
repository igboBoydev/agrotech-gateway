package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateFarmerRequestDto {
    private String firstName;
    private String lastName;
    private String dob;
    private String homeAddress;
    private String lga;
    private String state;
    private String country;
    private String email;
    private String phone;
    private String password;
}
