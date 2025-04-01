package be.hepl.authapi.application.mapper;

import be.hepl.authapi.application.dto.request.CustomerCreateRequest;
import be.hepl.authapi.domain.model.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/// <comments>
/// Mapper pour transformer un model de creation de client en client
/// </comments>
@Mapper
public interface CustomerCreateRequestToCustomerMapper {
    CustomerCreateRequestToCustomerMapper INSTANCE = Mappers.getMapper(CustomerCreateRequestToCustomerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "phoneVerified", ignore = true)
    @Mapping(target = "createAccount", ignore = true)
    @Mapping(target = "password", ignore = true)
    Customer map(CustomerCreateRequest customerCreateRequest);
}
