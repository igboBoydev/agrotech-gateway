package com.agrotech.gateway.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DocumentDto {
    private String id;
    private String documentName;
    private String expirationDate;
    private String documentUrl;
    private String documentNumber;
    private String countryOfIssuance;
    private String dateOfIssuance;
    private String placeOfIssuance;
    private String verificationStatus; // enum name, e.g. "PENDING"
    private String createdAt;
}
