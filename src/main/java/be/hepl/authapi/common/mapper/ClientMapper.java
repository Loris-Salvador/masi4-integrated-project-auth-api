package be.hepl.authapi.common.mapper;

import be.hepl.authapi.application.command.ClientCreateCommand;
import be.hepl.authapi.application.response.ClientCreateResponse;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import be.hepl.authapi.presentation.request.ClientCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientEntity toEntity(Client client);
    Client toClient(ClientCreateCommand command);
    ClientCreateCommand toCommand(ClientCreateRequest request);
    Client toClient(ClientEntity entity);
    ClientCreateResponse toResponse(Client client);



}
