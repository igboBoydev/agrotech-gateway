package com.agrotech.gateway.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String contactPhoneNumber;
    private String email;
    private String motherMaidenName;
    private String middleName;
    private String dateOfBirth;
    private String createdAt;
    private String updatedAt;
    private String rimNumber;
    private Boolean active;
    private String rsm;
    private String sectorCode;
    private String sex;
    private String title;
    private String alternatePhoneNumber;
    private String nationality;
    private String maritalStatus;
    private String education;
    private String nameOfSpouse;
    private String branch;
    private String fatherName;
    private String motherName;
    private Boolean useAltAccNum;
    private Boolean smsEnabled;
    private Boolean emailEnabled;
}
