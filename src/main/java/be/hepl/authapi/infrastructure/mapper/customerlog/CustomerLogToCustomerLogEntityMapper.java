package be.hepl.authapi.infrastructure.mapper.customerlog;

import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/// <comments>
/// Mapper qui mappe un Customer log en Customer log entity
/// </comments>
@Mapper
public interface CustomerLogToCustomerLogEntityMapper {
    CustomerLogToCustomerLogEntityMapper INSTANCE = Mappers.getMapper(CustomerLogToCustomerLogEntityMapper.class);

    CustomerLogEntity map(CustomerLog customerLog);
}
