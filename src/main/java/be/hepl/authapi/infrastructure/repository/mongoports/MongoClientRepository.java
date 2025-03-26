package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/// <comments>
/// Interface qui va être automatiquement implémenté par spring et fournir des méthodes
/// pour interagir avec la collection client (utilisation de la classe implémentée dans
/// la classe MongoClientRepositoryImpl)
/// </comments>
@Repository
public interface MongoClientRepository extends MongoRepository<ClientEntity, String> {
    
    Optional<ClientEntity> findByEmail(String email);

    @Query("{ 'email' : ?0 }")
    @Update("{ '$set' : { 'emailVerified' : ?1 } }")
    void updateEmailVerified(String email, boolean newValue);

    @Query("{ 'email' : ?0 }")
    @Update("{ '$set' : { 'phoneVerified' : ?1 } }")
    void updatePhoneVerified(String email, boolean newValue);
}