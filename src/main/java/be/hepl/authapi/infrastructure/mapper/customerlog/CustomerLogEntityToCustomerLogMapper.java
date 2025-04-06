package be.hepl.authapi.infrastructure.mapper.customerlog;

import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerLogEntityToCustomerLogMapper {
    CustomerLogEntityToCustomerLogMapper INSTANCE = Mappers.getMapper(CustomerLogEntityToCustomerLogMapper.class);

    CustomerLog map(CustomerLogEntity customerLogEntity);
}
