package be.hepl.authapi.infrastructure.mapper.client;

import be.hepl.authapi.domain.model.client.Client;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/// <comments>
/// Mapper qui mappe un Client en Client entity
/// </comments>
@Mapper
public interface ClientToClientEntityMapper {

    ClientToClientEntityMapper INSTANCE = Mappers.getMapper(ClientToClientEntityMapper.class);

    ClientEntity map(Client client);
}
