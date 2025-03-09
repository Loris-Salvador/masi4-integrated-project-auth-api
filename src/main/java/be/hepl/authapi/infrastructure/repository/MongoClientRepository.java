package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<ClientEntity, String> {
    Optional<ClientEntity> findByEmail(String email);
}