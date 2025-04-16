package be.hepl.authapi.infrastructure.mapper.driver;

import be.hepl.authapi.domain.model.driver.DriverLog;
import be.hepl.authapi.infrastructure.entity.DriverLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverLogToDriverLogEntityMapper {
    DriverLogToDriverLogEntityMapper INSTANCE = Mappers.getMapper(DriverLogToDriverLogEntityMapper.class);

    DriverLogEntity map(DriverLog driverLog);
}
