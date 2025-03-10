package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.exception.ClientNotFoundException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import be.hepl.authapi.common.mapper.ClientMapper;
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
        ClientEntity clientEntity = ClientMapper.INSTANCE.toEntity(client);

        ClientEntity response = mongoRepository.save(clientEntity);

        return ClientMapper.INSTANCE.toClient(response);
    }

    @Override
    public Client findByEmail(String email) {
        Optional<ClientEntity> clientEntityOpt = mongoRepository.findByEmail(email);


        if(clientEntityOpt.isEmpty()) {
            throw new ClientNotFoundException(email);
        }

        ClientEntity clientEntity = clientEntityOpt.get();

        return ClientMapper.INSTANCE.toClient(clientEntity);
    }

    @Override
    public void updateEmailVerification(String email, boolean newValue) {
        mongoRepository.updateEmailVerified(email, newValue);
    }

    @Override
    public void updatePhoneVerification(String email, boolean newValue) {
        mongoRepository.updatePhoneVerified(email, newValue);
    }


}
