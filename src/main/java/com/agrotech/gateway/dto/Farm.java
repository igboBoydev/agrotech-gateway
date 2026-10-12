package com.agrotech.gateway.dto;

import com.agrotech.gateway.dto.request.DocumentDto;
import com.agrotech.gateway.dto.request.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Farm {
    private String address;
    private String lga;
    private String state;
    private String country;
    private String landSize;
    private Boolean isLease;
    private Boolean isOwnProperty;
    private Integer leaseYears;
    private Integer numOfEmployees;
    private EmployeeDto employees;
    private List<String> farmPictures;
    private DocumentDto farmDocuments;
    private String plantType;
    private String plantSeasonStartDate;
    private String plantSeasonEndDate;
    private String farmType;
}
