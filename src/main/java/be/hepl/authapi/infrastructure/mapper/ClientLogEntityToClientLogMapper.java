package be.hepl.authapi.infrastructure.mapper;

import be.hepl.authapi.domain.model.ClientLog;
import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientLogEntityToClientLogMapper {
    ClientLogEntityToClientLogMapper INSTANCE = Mappers.getMapper(ClientLogEntityToClientLogMapper.class);

    ClientLog map(ClientLogEntity clientLogEntity);
}
