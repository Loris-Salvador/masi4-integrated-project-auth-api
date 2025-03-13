package be.hepl.authapi.application.mapper;

import be.hepl.authapi.application.dto.request.ClientCreateRequest;
import be.hepl.authapi.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Mapper
public interface ClientCreateRequestToClientMapper {
    ClientCreateRequestToClientMapper INSTANCE = Mappers.getMapper(ClientCreateRequestToClientMapper.class);

    @Mapping(target = "dateOfBirth", expression = "java(convertLocalDateToString(clientCreateRequest.dateOfBirth()))")
    Client toClient(ClientCreateRequest clientCreateRequest);

    default Long convertLocalDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }


        Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);

        return instant.getEpochSecond();
    }
}
