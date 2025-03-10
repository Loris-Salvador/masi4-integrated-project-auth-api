package be.hepl.authapi.presentation.mapper;

import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientEntityMapper {
    ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);

    Client toClient(ClientEntity entity);
}
