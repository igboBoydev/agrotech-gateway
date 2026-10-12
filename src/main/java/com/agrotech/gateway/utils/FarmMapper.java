package com.agrotech.gateway.utils;

import com.agrotech.gateway.dto.CustomerOptionsDto;
import com.agrotech.gateway.dto.Farm;
import com.agrotech.gateway.dto.ProductsDto;
import com.agrotech.gateway.dto.request.DocumentDto;
import com.agrotech.gateway.dto.request.EmployeeDto;
import com.agrotech.gateway.dto.request.FarmerDto;
import com.agrotech.gateway.dto.response.FarmerProfileResponseDto;
import com.agrotech.gateway.dto.response.GetBusinessProfileResponseDto;
import com.agrotech.gateway.dto.response.GetFarmsResponseDto;
import com.agrotech.gateway.dto.response.GetProductsResponseDto;
import org.springframework.stereotype.Component;
import users.Business;
import users.DocumentOuterClass;
import users.Farmers;
import users.ProductsOuterClass;

@Component
public final class FarmMapper {

    private FarmMapper() {}

    public Farm toDto(Farmers.Farm p) {
        return Farm.builder()
                .address(p.getAddress())
                .lga(p.getLga())
                .state(p.getState())
                .country(p.getCountry())
                .landSize(p.getLandSize())
                .isLease(p.getIsLease())
                .isOwnProperty(p.getIsOwnProperty())
                .leaseYears(p.getLeaseYears())
                .numOfEmployees(p.getNumOfEmployees())
                .employees((EmployeeDto) p.getEmployeesList().stream().map(FarmMapper::toDto).toList())
                .farmPictures(p.getFarmPicturesList())
                .farmDocuments((DocumentDto) p.getFarmDocumentsList().stream().map(FarmMapper::toDto).toList())
                .plantType(p.getPlantType())
                .plantSeasonStartDate(p.getPlantSeasonStartDate())
                .plantSeasonEndDate(p.getPlantSeasonEndDate())
                .farmType(p.getFarmType())
                .build();
    }
//
//    public FarmerDto toDto(Farmers.Farmer farmerDetails){
//        return FarmerDto.builder()
//
//                .build();
//    }
//
    public GetProductsResponseDto toDto(ProductsOuterClass.GetProductsResponse response) {
        return GetProductsResponseDto.builder()
                .productsDto(response.getProductList().stream()
                        .map(this::toDto)
                        .toList())
                .build();
    }

    private ProductsDto toDto(ProductsOuterClass.Products p) {
        return ProductsDto.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .type(p.getType())
                .quantity(p.getQuantity())
                .unit(p.getUnit())
                .pricePerUnit(p.getPricePerUnit())
                .isAvailable(p.getIsAvailable())
                .build();
    }

    private static EmployeeDto toDto(Farmers.Employee e) {
        return EmployeeDto.builder()
                .occupation(e.getOccupation())
                .customer(e.hasCustomer() ? toDto(e.getCustomer()) : null)
                .businessCategory(e.getBusinessCategory())
                .employmentCategory(e.getEmploymentCategory())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .durationOfEmployment(e.getDurationOfEmployment())
                .employerPhone(e.getEmployerPhone())
                .position(e.getPosition())
                .build();
    }

    public static FarmerDto toDto(Farmers.Farmer f) {
        return FarmerDto.builder()
                .id(f.getId())
                .firstName(f.getFirstName())
                .lastName(f.getLastName())
                .contactPhoneNumber(f.getContactPhoneNumber())
                .email(f.getEmail())
                .motherMaidenName(f.getMotherMaidenName())
                .middleName(f.getMiddleName())
                .dateOfBirth(f.getDateOfBirth())
                .createdAt(f.getCreatedAt())
                .updatedAt(f.getUpdatedAt())
                .rimNumber(f.getRimNumber())
                .active(f.getActive())
                .rsm(f.getRsm())
                .sectorCode(f.getSectorCode())
                .sex(f.getSex())
                .title(f.getTitle())
                .alternatePhoneNumber(f.getAlternatePhoneNumber())
                .nationality(f.getNationality())
                .maritalStatus(f.getMaritalStatus())
                .education(f.getEducation())
                .nameOfSpouse(f.getNameOfSpouse())
                .branch(f.getBranch())
                .fatherName(f.getFatherName())
                .motherName(f.getMotherName())
                .useAltAccNum(f.getUseAltAccNum())
                .smsEnabled(f.getSmsEnabled())
                .emailEnabled(f.getEmailEnabled())
                .build();
    }

    private static DocumentDto toDto(DocumentOuterClass.Document d) {
        return DocumentDto.builder()
                .id(d.getId())
                .documentName(d.getDocumentName())
                .expirationDate(d.getExpirationDate())
                .documentUrl(d.getDocumentUrl())
                .documentNumber(d.getDocumentNumber())
                .countryOfIssuance(d.getCountryOfIssuance())
                .dateOfIssuance(d.getDateOfIssuance())
                .placeOfIssuance(d.getPlaceOfIssuance())
                .verificationStatus(d.getVerificationStatus().name())
                .createdAt(d.getCreatedAt())
                .build();
    }

    public GetBusinessProfileResponseDto toDto(Business.GetBusinessProfileResponse response){
        return GetBusinessProfileResponseDto.builder()
                .companyName(response.getCompanyName())
                .companyRegistrationNo(response.getCompanyRegistrationNo())
                .dateOfRegistration(response.getDateOfRegistration())
                .countryOfRegistration(response.getCountryOfRegistration())
                .companyAddress(response.getCompanyAddress())
                .state(response.getState())
                .city(response.getCity())
                .country(response.getCountry())
                .lastName(response.getLastName())
                .bvn(response.getBvn())
                .tin(response.getTin())
                .address(response.getAddress())
                .principalOfficerMiddleName(response.getPrincipalOfficerMiddleName())
                .sex(response.getSex())
                .dateOfBirth(response.getDateOfBirth())
                .title(response.getTitle())
                .contactPhoneNumber(response.getContactPhoneNumber())
                .alternatePhone(response.getAlternatePhone())
                .officePhoneNumber(response.getOfficePhoneNumber())
                .emailAddress(response.getEmailAddress())
                .nin(response.getNin())
                .idType(response.getIdType())
                .dateOfExpiration(response.getDateOfExpiration())
                .placeOfIssuance(response.getPlaceOfIssuance())
                .idNumber(response.getIdNumber())
                .countryOfIssuance(response.getCountryOfIssuance())
                .dateOfIssuance(response.getDateOfIssuance())
                .fathersName(response.getFathersName())
                .mothersName(response.getMothersName())
                .nationality(response.getNationality())
                .businessCategory(response.getBusinessCategory())
                .ownershipStructure(response.getOwnershipStructure())
                .product(response.getProduct())
                .branchId(response.getBranchId())
                .signature(response.getSignature())
                .photoUpload(response.getPhotoUpload())
                .introducer(response.getIntroducer())
                .rsm(response.getRsm())
                .restriction(response.getRestriction())
                .principalOfficerLastname(response.getPrincipalOfficerLastname())
                .principalOfficerFirstName(response.getPrincipalOfficerFirstName())
                .primaryCompanyAddress(response.getPrimaryCompanyAddress())
                .primaryCompanyState(response.getPrimaryCompanyState())
                .primaryCompanyCity(response.getPrimaryCompanyCity())
                .primaryCompanyCountry(response.getPrimaryCompanyCountry())
                .firstName(response.getFirstName())
                .middleName(response.getMiddleName())
                .productId(response.getProductId())
                .smsNotification(response.getSmsNotification())
                .emailNotification(response.getEmailNotification())
                .customerOptions(response.hasCustomerOptions()
                        ? CustomerOptionsDto.builder()
                        .customerCreatedAt(response.getCustomerOptions().getCustomerCreatedAt())
                        .isActive(response.getCustomerOptions().getIsActive())
                        .build()
                        : null)
                .isLinkedAccount(response.getIsLinkedAccount())
                .linkedAccountNumber(response.getLinkedAccountNumber())
                .creationChannelCode(response.getCreationChannelCode())
                .build();
    }

    private GetFarmsResponseDto toDto(Farmers.GetFarmsResponse response){
        return GetFarmsResponseDto.builder()
                .farms(response.getFarmsList().stream().map(this::toDtoData).toList())
                .build();
    }

    // takes a proto object, returns your DTO
    private Farm toDtoData(Farmers.Farm p) {
        return Farm.builder()
                .address(p.getAddress())
                .lga(p.getLga())
                .state(p.getState())
                .country(p.getCountry())
                .landSize(p.getLandSize())
                .isLease(p.getIsLease())
                .isOwnProperty(p.getIsOwnProperty())
                .leaseYears(p.getLeaseYears())
                .numOfEmployees(p.getNumOfEmployees())
                .employees((EmployeeDto) p.getEmployeesList().stream().map(FarmMapper::toDto).toList())
                .farmPictures(p.getFarmPicturesList())
                .farmDocuments((DocumentDto) p.getFarmDocumentsList().stream().map(FarmMapper::toDto).toList())
                .plantType(p.getPlantType())
                .plantSeasonStartDate(p.getPlantSeasonStartDate())
                .plantSeasonEndDate(p.getPlantSeasonEndDate())
                .farmType(p.getFarmType())
                .build();
    }
}
