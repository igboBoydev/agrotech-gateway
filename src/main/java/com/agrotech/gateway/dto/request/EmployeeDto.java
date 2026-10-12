package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeDto {
    private String occupation;
    private FarmerDto customer;
    private String businessCategory;
    private String employmentCategory;
    private String startDate;
    private String endDate;
    private String durationOfEmployment;
    private String employerPhone;
    private String position;
}
