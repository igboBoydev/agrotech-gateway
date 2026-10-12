package com.agrotech.gateway.service.impl;

import com.agrotech.gateway.config.GrpcMetadata;
import com.agrotech.gateway.dto.request.*;
import com.agrotech.gateway.dto.response.*;
import com.agrotech.gateway.exception.ApiExceptionHandler;
import com.agrotech.gateway.service.OnboardingService;
import com.agrotech.gateway.utils.FarmMapper;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import users.*;
import users.Enum;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class OnboardingServiceImpl implements OnboardingService {

    private final UsersServiceGrpc.UsersServiceStub asyncStub;
    private final FarmMapper farmMapper;

    @Override
    public Mono<CreateFarmerResponseDto> createFarmer(CreateFarmerRequestDto request) {
        Farmers.CreateFarmerRequest grpcRequest = Farmers.CreateFarmerRequest.newBuilder()
                .setDob(request.getDob())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setHomeAddress(request.getHomeAddress())
                .setLga(request.getLga())
                .setState(request.getState())
                .setCountry(request.getCountry())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone())
                .setPassword(request.getPassword())
                .build();

        return Mono.<CreateFarmerResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .createFarmer(grpcRequest, new StreamObserver<Farmers.CreateFarmerResponse>() {

                                    @Override
                                    public void onNext(Farmers.CreateFarmerResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new CreateFarmerResponseDto(
                                                response.getMessage(),
                                                response.getCode()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<AddFarmerKycResponseDto> addFarmerKyc(AddFarmerKycRequestDto request, HttpHeaders headers) {

        Metadata metadata = GrpcMetadata.from(headers);

        Farmers.AddFarmerKycRequest.Builder builder = Farmers.AddFarmerKycRequest.newBuilder()
                .setId(request.getId())
                .setSignature(request.getSignature())
                .setBvn(request.getBvn())
                .setTin(request.getTin())
                .setPassportPhotograph(request.getPassportPhotograph())
                .setKycLevel(request.getKycLevel())
                .setTier(request.getTier())
                .setTinVerified(request.isTinVerified())
                .setDrivingLicense(request.getDrivingLicense())
                .setInternationalPassportNumber(request.getInternationalPassportNumber())
                .setVotersCardNumber(request.getVotersCardNumber());

        if (request.getDocumentsAndIdentity() != null) {
            builder.addAllDocumentsAndIdentity(
                    request.getDocumentsAndIdentity().stream().map(this::toProto).toList());
        }
        if (request.getCorporateCustomerAddress() != null) {
            builder.setCorporateCustomerAddress(toProto(request.getCorporateCustomerAddress()));
        }
        Farmers.AddFarmerKycRequest grpcRequest = builder.build();

        return Mono.<AddFarmerKycResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .addFarmerKyc(grpcRequest, new StreamObserver<Farmers.AddFarmerKycResponse>() {

                                    @Override
                                    public void onNext(Farmers.AddFarmerKycResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new AddFarmerKycResponseDto(
                                                response.getMessage(),
                                                response.getCode()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<AddFarmResponseDto> addFarm(AddFarmRequestDto request, HttpHeaders headers) {
        Farmers.AddFarmRequest.Builder builder = Farmers.AddFarmRequest.newBuilder()
                .setUserId(request.getUserId())
                .setPlantType(request.getPlantType())
                .setPlantSeasonStartDate(request.getPlantSeasonStartDate())
                .setPlantSeasonEndDate(request.getPlantSeasonEndDate())
                .setNumOfEmployees(request.getNumOfEmployees())
                .setLeaseYears(request.getLeaseYears())
                .setState(request.getState())
                .setCountry(request.getCountry())
                .setLandSize(request.getLandSize())
                .setAddress(request.getAddress())
                .setLga(request.getLga())
                .setIsOwnProperty(request.getIsOwnProperty())
                .setFarmType(request.getFarmType());

        Metadata metadata = GrpcMetadata.from(headers);

        if(request.getEmployees() != null){
            builder.addAllEmployees(request.getEmployees().stream().map(this::toProto).toList());
        }

        Farmers.AddFarmRequest grpcRequest = builder.build();

        return Mono.<AddFarmResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .addFarm(grpcRequest, new StreamObserver<Farmers.AddFarmResponse>() {

                                    @Override
                                    public void onNext(Farmers.AddFarmResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new AddFarmResponseDto(
                                                response.getMessage(),
                                                response.getCode()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<FarmerProfileResponseDto> getFarmersProfile(String userId, HttpHeaders headers) {
        Metadata metadata = GrpcMetadata.from(headers);

        Farmers.FarmerProfileRequest grpcRequest = Farmers.FarmerProfileRequest.newBuilder()
                .setUserId(userId)
                .build();

        return Mono.<FarmerProfileResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .getFarmerProfile(grpcRequest, new StreamObserver<>() {

                                    @Override
                                    public void onNext(Farmers.FarmerProfileResponse response) {
                                        // proto -> REST DTO
                                        sink.success(
                                                FarmerProfileResponseDto.builder()
                                                        .farmerDto(response.hasFarmerDetails() ? FarmMapper.toDto(response.getFarmerDetails()) : null)
                                                        .build()
                                        );
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<GetFarmsResponseDto> getFarms(GetFarmsRequestDto request, HttpHeaders headers) {
        Farmers.GetFarmsRequest grpcRequest = Farmers.GetFarmsRequest.newBuilder()
                .setUserId(request.getUserId())
                .build();

        Metadata metadata = GrpcMetadata.from(headers);

        return Mono.<GetFarmsResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .getFarms(grpcRequest, new StreamObserver<Farmers.GetFarmsResponse>() {

                                    @Override
                                    public void onNext(Farmers.GetFarmsResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new GetFarmsResponseDto(
                                                response.getFarmsList().stream().map(farmMapper::toDto).toList()));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<KYCStatusDto> checkKycStatus(KYCRequestDto request, HttpHeaders headers) {
        KycOuterClass.KYCRequest grpcRequest = KycOuterClass.KYCRequest.newBuilder()
                .setUserId(request.getUserId())
                .build();

        Metadata metadata = GrpcMetadata.from(headers);

        return Mono.<KYCStatusDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .checkKYCStatus(grpcRequest, new StreamObserver<KycOuterClass.KYCStatus>() {

                                    @Override
                                    public void onNext(KycOuterClass.KYCStatus response) {
                                        // proto -> REST DTO
                                        sink.success(new KYCStatusDto(
                                                response.getMessage(),
                                                response.getVerificationStatus().name()
                                        ));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<CreateBusinessResponseDto> createBusiness(CreateBusinessRequestDto request, HttpHeaders headers) {
        Business.CreateBusinessRequest grpcRequest = Business.CreateBusinessRequest.newBuilder()
                .setCompanyName(request.getCompanyName())
                .setCompanyRegistrationNo(request.getCompanyRegistrationNo())
                .setDateOfRegistration(request.getDateOfRegistration())
                .setCountryOfRegistration(request.getCountryOfRegistration())
                .setCompanyAddress(request.getCompanyAddress())
                .setState(request.getState())
                .setCity(request.getCity())
                .setCountry(request.getCountry())
                .setLastName(request.getLastName())
                .setBvn(request.getBvn())
                .setTin(request.getTin())
                .setAddress(request.getAddress())
                .build();

        Metadata metadata = GrpcMetadata.from(headers);

        return Mono.<CreateBusinessResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .createBusiness(grpcRequest, new StreamObserver<Business.CreateBusinessResponse>() {

                                    @Override
                                    public void onNext(Business.CreateBusinessResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new CreateBusinessResponseDto(
                                                response.getMessage(),
                                                response.getCode()
                                        ));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<GetBusinessProfileResponseDto> getBusinessProfile(GetBusinessProfileRequestDto request, HttpHeaders headers) {
        Business.GetBusinessProfileRequest grpcRequest = Business.GetBusinessProfileRequest.newBuilder()
                .setUserId(request.getUserId())
                .build();

        Metadata metadata = GrpcMetadata.from(headers);

        return Mono.<GetBusinessProfileResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .getBusinessProfile(grpcRequest, new StreamObserver<Business.GetBusinessProfileResponse>() {

                                    @Override
                                    public void onNext(Business.GetBusinessProfileResponse response) {
                                        // proto -> REST DTO
                                        sink.success(farmMapper.toDto(response));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<GetProductsResponseDto> getFarmProducts(GetProductsRequestDto request, HttpHeaders headers) {
        ProductsOuterClass.GetProductsRequest grpcRequest = ProductsOuterClass.GetProductsRequest.newBuilder()
                .setFarmId(request.getFarmId())
                .build();

        Metadata metadata = GrpcMetadata.from(headers);

        return Mono.<GetProductsResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .getFarmProducts(grpcRequest, new StreamObserver<ProductsOuterClass.GetProductsResponse>() {

                                    @Override
                                    public void onNext(ProductsOuterClass.GetProductsResponse response) {
                                        // proto -> REST DTO
                                        sink.success(
                                                farmMapper.toDto(response)
                                        );
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    @Override
    public Mono<AddFarmProductResponseDto> addFarmProduct(AddFarmProductRequestDto request, HttpHeaders headers) {
        ProductsOuterClass.AddFarmProductRequest grpcRequest = ProductsOuterClass.AddFarmProductRequest.newBuilder()
                .setFarmId(request.getFarmId())
                .setProductName(request.getProductName())
                .setCategory(request.getCategory())
                .setProductType(request.getProductType())
                .setDescription(request.getDescription())
                .setQuantity(request.getQuantity())
                .setUnit(request.getUnit())
                .setPricePerUnit(request.getPricePerUnit())
                .setHarvestDate(request.getHarvestDate())
                .setIsAvailable(request.getIsAvailable())
                .build();

        Metadata metadata = GrpcMetadata.from(headers);

        return Mono.<AddFarmProductResponseDto>create(sink ->
                        asyncStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                                .addFarmProduct(grpcRequest, new StreamObserver<ProductsOuterClass.AddFarmProductResponse>() {

                                    @Override
                                    public void onNext(ProductsOuterClass.AddFarmProductResponse response) {
                                        // proto -> REST DTO
                                        sink.success(new AddFarmProductResponseDto(
                                                response.getMessage(),
                                                response.getCode()
                                        ));
                                    }

                                    @Override
                                    public void onError(Throwable error) {
                                        sink.error(error);
                                    }

                                    @Override
                                    public void onCompleted() {
                                        // a unary RPC already called success() in onNext;
                                        // MonoSink ignores a second signal, so this only matters
                                        // if the server completed without sending a message
                                        sink.success();
                                    }
                                }))
                .onErrorMap(StatusRuntimeException.class, error ->
                        new ApiExceptionHandler.GrpcGatewayException(error.getStatus(), error));
    }

    private Farmers.CorporateUserAddress toProto(CorporateUserAddressDto a) {
        return Farmers.CorporateUserAddress.newBuilder()
                .setId(nz(a.getId()))
                .setCompanyAddress(nz(a.getCompanyAddress()))
                .setState(nz(a.getState()))
                .setCity(nz(a.getCity()))
                .setCountry(nz(a.getCountry()))
                .setPrimaryCompanyAddress(nz(a.getPrimaryCompanyAddress()))
                .setPrimaryCompanyState(nz(a.getPrimaryCompanyState()))
                .setPrimaryCompanyCity(nz(a.getPrimaryCompanyCity()))
                .setPrimaryCompanyCountry(nz(a.getPrimaryCompanyCountry()))
                .setAddress(nz(a.getAddress()))
                .setVerificationStatus(nz(a.getVerificationStatus()))
                .setCreatedAt(nz(a.getCreatedAt()))
                .setUpdatedAt(nz(a.getUpdatedAt()))
                .setPrimaryCompanyLga(nz(a.getPrimaryCompanyLga()))
                .build();
    }

    private DocumentOuterClass.Document toProto(DocumentDto d) {
        return DocumentOuterClass.Document.newBuilder()
                .setId(nz(d.getId()))
                .setDocumentName(nz(d.getDocumentName()))
                .setExpirationDate(nz(d.getExpirationDate()))
                .setDocumentUrl(nz(d.getDocumentUrl()))
                .setDocumentNumber(nz(d.getDocumentNumber()))
                .setCountryOfIssuance(nz(d.getCountryOfIssuance()))
                .setDateOfIssuance(nz(d.getDateOfIssuance()))
                .setPlaceOfIssuance(nz(d.getPlaceOfIssuance()))
                .setVerificationStatus(status(d.getVerificationStatus()))
                .setCreatedAt(nz(d.getCreatedAt()))
                .build();
    }

    private Farmers.Employee toProto(EmployeeDto employeeDto){
        return Farmers.Employee.newBuilder()
                .setOccupation(employeeDto.getOccupation())
                .setCustomer(toProto(employeeDto.getCustomer()))
                .setBusinessCategory(employeeDto.getBusinessCategory())
                .setEmploymentCategory(employeeDto.getEmploymentCategory())
                .setStartDate(employeeDto.getStartDate())
                .setEndDate(employeeDto.getEndDate())
                .setDurationOfEmployment(employeeDto.getDurationOfEmployment())
                .setEmployerPhone(employeeDto.getEmployerPhone())
                .setPosition(employeeDto.getPosition())
                .build();
    }

    private Farmers.Farmer toProto(FarmerDto f) {
        return Farmers.Farmer.newBuilder()
                .setId(nz(f.getId()))
                .setFirstName(nz(f.getFirstName()))
                .setLastName(nz(f.getLastName()))
                .setContactPhoneNumber(nz(f.getContactPhoneNumber()))
                .setEmail(nz(f.getEmail()))
                .setMotherMaidenName(nz(f.getMotherMaidenName()))
                .setMiddleName(nz(f.getMiddleName()))
                .setDateOfBirth(nz(f.getDateOfBirth()))
                .setCreatedAt(nz(f.getCreatedAt()))
                .setUpdatedAt(nz(f.getUpdatedAt()))
                .setRimNumber(nz(f.getRimNumber()))
                .setActive(Boolean.TRUE.equals(f.getActive()))
                .setRsm(nz(f.getRsm()))
                .setSectorCode(nz(f.getSectorCode()))
                .setSex(nz(f.getSex()))
                .setTitle(nz(f.getTitle()))
                .setAlternatePhoneNumber(nz(f.getAlternatePhoneNumber()))
                .setNationality(nz(f.getNationality()))
                .setMaritalStatus(nz(f.getMaritalStatus()))
                .setEducation(nz(f.getEducation()))
                .setNameOfSpouse(nz(f.getNameOfSpouse()))
                .setBranch(nz(f.getBranch()))
                .setFatherName(nz(f.getFatherName()))
                .setMotherName(nz(f.getMotherName()))
                .setUseAltAccNum(Boolean.TRUE.equals(f.getUseAltAccNum()))
                .setSmsEnabled(Boolean.TRUE.equals(f.getSmsEnabled()))
                .setEmailEnabled(Boolean.TRUE.equals(f.getEmailEnabled()))
                .build();
    }

    private static String nz(String s) {
        return s == null ? "" : s;
    }

    private static Enum.VerificationStatus status(String s) {
        if (s == null || s.isBlank()) return Enum.VerificationStatus.NULL;
        return Enum.VerificationStatus.valueOf(s.trim().toUpperCase());
    }
}
