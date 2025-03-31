package be.hepl.authapi.application.mapper;

import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.domain.model.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/// <comments>
/// Mapper qui transforme un client en réponse de création d'un client
/// </comments>
@Mapper
public interface ClientToClientCreateResponseMapper {

    ClientToClientCreateResponseMapper INSTANCE = Mappers.getMapper(ClientToClientCreateResponseMapper.class);

    ClientCreateResponse map(Client client);
}
