package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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