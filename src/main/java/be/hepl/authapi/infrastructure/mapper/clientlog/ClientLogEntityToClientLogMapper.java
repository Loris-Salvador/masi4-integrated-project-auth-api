package be.hepl.authapi.infrastructure.mapper.clientlog;

import be.hepl.authapi.domain.model.client.ClientLog;
import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/// <comments>
/// Mapper qui mappe un Client log entity en Client log
/// </comments>
@Mapper
public interface ClientLogEntityToClientLogMapper {
    ClientLogEntityToClientLogMapper INSTANCE = Mappers.getMapper(ClientLogEntityToClientLogMapper.class);

    ClientLog map(ClientLogEntity clientLogEntity);
}
