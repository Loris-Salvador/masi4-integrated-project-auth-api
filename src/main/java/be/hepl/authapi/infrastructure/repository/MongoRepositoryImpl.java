package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.exception.ClientNotFoundException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import be.hepl.authapi.presentation.mapper.ClientEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoRepositoryImpl implements ClientRepository {

    public final MongoClientRepository mongoRepository;

    public MongoRepositoryImpl(MongoClientRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }


    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Client findByEmail(String email) {
        Optional<ClientEntity> clientEntityOpt = mongoRepository.findByEmail(email);


        if(clientEntityOpt.isEmpty()) {
            throw new ClientNotFoundException(email);
        }

        ClientEntity clientEntity = clientEntityOpt.get();

        return ClientEntityMapper.INSTANCE.toClient(clientEntity);

    }
}
