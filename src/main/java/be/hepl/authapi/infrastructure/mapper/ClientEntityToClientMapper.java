package be.hepl.authapi.infrastructure.mapper;

import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientEntityToClientMapper {

    ClientEntityToClientMapper INSTANCE = Mappers.getMapper(ClientEntityToClientMapper.class);

    Client map(ClientEntity entity);
}
