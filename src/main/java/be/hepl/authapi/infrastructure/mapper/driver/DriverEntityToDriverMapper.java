package be.hepl.authapi.infrastructure.mapper.driver;

import be.hepl.authapi.domain.model.driver.Driver;
import be.hepl.authapi.infrastructure.entity.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverEntityToDriverMapper {

    DriverEntityToDriverMapper INSTANCE = Mappers.getMapper(DriverEntityToDriverMapper.class);

    Driver map(DriverEntity driverEntity);
}
