package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoRepositoryImpl implements ClientRepository {

    public final MongoClientRepository mongoRepository;

    public MongoRepositoryImpl(MongoClientRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }


    @Override
    public Client save(Client user) {
        return null;
    }

    @Override
    public Client findByEmail(String email) {
        Optional<ClientEntity> userEntityOpt = mongoRepository.findByEmail(email);

        System.out.println(userEntityOpt);

        if(userEntityOpt.isEmpty()) {
            throw new UserNotFoundException(email);
        }

        ClientEntity userEntity = userEntityOpt.get();

        ModelMapper modelMapper = new ModelMapper();

        Client user = modelMapper.map(userEntity, Client.class);

        return user;

    }
}
