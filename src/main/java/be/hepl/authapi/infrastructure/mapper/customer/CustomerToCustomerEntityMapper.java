package be.hepl.authapi.infrastructure.mapper.customer;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/// <comments>
/// Mapper qui mappe un Customer en Customer entity
/// </comments>
@Mapper
public interface CustomerToCustomerEntityMapper {

    CustomerToCustomerEntityMapper INSTANCE = Mappers.getMapper(CustomerToCustomerEntityMapper.class);

    CustomerEntity map(Customer customer);
}
