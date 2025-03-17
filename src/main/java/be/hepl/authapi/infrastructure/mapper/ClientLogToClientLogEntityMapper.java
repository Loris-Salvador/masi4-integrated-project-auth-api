package be.hepl.authapi.infrastructure.mapper;

import be.hepl.authapi.domain.model.ClientLog;
import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import org.mapstruct.factory.Mappers;

public interface ClientLogToClientLogEntityMapper {
    ClientLogToClientLogEntityMapper INSTANCE = Mappers.getMapper(ClientLogToClientLogEntityMapper.class);

    ClientLogEntity map(ClientLog clientLog);
}
