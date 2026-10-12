package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddFarmRequestDto {
    private String userId;
    private String plantType;
    private String plantSeasonStartDate;
    private String plantSeasonEndDate;
    private Integer numOfEmployees;
    private Integer leaseYears;
    private Boolean isLease;
    private String state;
    private String country;
    private String landSize;
    private String address;
    private String lga;
    private Boolean isOwnProperty;
    private String farmType;
    private List<EmployeeDto> employees = new ArrayList<>();
}
