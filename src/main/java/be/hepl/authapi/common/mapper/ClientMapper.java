package be.hepl.authapi.common.mapper;

import be.hepl.authapi.application.dto.request.ClientCreateRequest;
import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientEntity toEntity(Client client);
    Client toClient(ClientEntity entity);
    ClientCreateResponse toResponse(Client client);
}
