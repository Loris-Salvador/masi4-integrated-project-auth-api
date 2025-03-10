package be.hepl.authapi.common.mapper;

import be.hepl.authapi.application.command.AuthCommand;
import be.hepl.authapi.presentation.request.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRequestMapper {
    AuthRequestMapper INSTANCE = Mappers.getMapper(AuthRequestMapper.class);


    AuthCommand toCommand(AuthRequest authRequest);
}
