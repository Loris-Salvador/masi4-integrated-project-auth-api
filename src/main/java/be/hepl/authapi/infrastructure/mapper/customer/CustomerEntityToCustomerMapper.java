package be.hepl.authapi.infrastructure.mapper.customer;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/// <comments>
/// Mapper qui mappe un Customer entity en Customer
/// </comments>
@Mapper
public interface CustomerEntityToCustomerMapper {

    CustomerEntityToCustomerMapper INSTANCE = Mappers.getMapper(CustomerEntityToCustomerMapper.class);

    Customer map(CustomerEntity entity);
}
