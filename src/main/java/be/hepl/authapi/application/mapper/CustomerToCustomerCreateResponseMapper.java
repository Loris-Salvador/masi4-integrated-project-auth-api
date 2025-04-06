package be.hepl.authapi.application.mapper;

import be.hepl.authapi.application.dto.response.CustomerCreateResponse;
import be.hepl.authapi.domain.model.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerToCustomerCreateResponseMapper {

    CustomerToCustomerCreateResponseMapper INSTANCE = Mappers.getMapper(CustomerToCustomerCreateResponseMapper.class);

    CustomerCreateResponse map(Customer customer);
}
