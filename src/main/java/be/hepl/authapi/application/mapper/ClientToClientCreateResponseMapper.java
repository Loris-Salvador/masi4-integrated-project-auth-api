package be.hepl.authapi.application.mapper;

import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.domain.model.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

/// <comments>
/// Mapper qui transforme un client en réponse de création d'un client
/// </comments>
@Mapper
public interface ClientToClientCreateResponseMapper {

    ClientToClientCreateResponseMapper INSTANCE = Mappers.getMapper(ClientToClientCreateResponseMapper.class);

    @Mapping(target = "birthDate", expression = "java(convertLongToLocalDate(client.getBirthDate()))")
    @Mapping(target = "createAccount", expression = "java(convertLongToLocalDate(client.getCreateAccount()))")
    ClientCreateResponse map(Client client);


    default LocalDate convertLongToLocalDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        }

        Instant instant = Instant.ofEpochSecond(timestamp);


        return instant.atZone(ZoneOffset.UTC).toLocalDate();
    }
}
