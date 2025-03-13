package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.exception.UserAlreadyExistException;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.Client;
import be.hepl.authapi.domain.repository.ClientRepository;
import be.hepl.authapi.infrastructure.entity.ClientEntity;
import be.hepl.authapi.infrastructure.mapper.ClientEntityToClientMapper;
import be.hepl.authapi.infrastructure.mapper.ClientToClientEntityMapper;
import com.mongodb.MongoWriteException;
import org.springframework.dao.DuplicateKeyException;
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
        ClientEntity clientEntity = ClientToClientEntityMapper.INSTANCE.map(client);

        try
        {
            ClientEntity response = mongoRepository.save(clientEntity);

            return ClientEntityToClientMapper.INSTANCE.map(response);
        }
        catch (DuplicateKeyException e) {

            if (e.getMessage().contains("email")) {
                throw new UserAlreadyExistException("The email address is already in use.");
            }
            else if (e.getMessage().contains("phone_number")) {
                throw new UserAlreadyExistException("The phone number is already in use.");
            }
            else
                throw e;
        }
    }

    @Override
    public Client findByEmail(String email) {
        Optional<ClientEntity> clientEntityOpt = mongoRepository.findByEmail(email);


        if(clientEntityOpt.isEmpty()) {
            throw new UserNotFoundException("The client with email " + email + " does not exist");
        }

        ClientEntity clientEntity = clientEntityOpt.get();

        return ClientEntityToClientMapper.INSTANCE.map(clientEntity);
    }

    @Override
    public Client findById(String id) {
        Optional<ClientEntity> clientEntityOpt = mongoRepository.findById(id);

        if(clientEntityOpt.isEmpty()) {
            return null;
        }

        return ClientEntityToClientMapper.INSTANCE.map(clientEntityOpt.get());
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
