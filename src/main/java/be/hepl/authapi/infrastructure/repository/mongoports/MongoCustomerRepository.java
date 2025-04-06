package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoCustomerRepository extends MongoRepository<CustomerEntity, String> {
    
    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByPhoneNumber(String phone);

    @Query("{ 'email' : ?0 }")
    @Update("{ '$set' : { 'emailVerified' : ?1 } }")
    void updateEmailVerified(String email, boolean newValue);

    @Query("{ 'email' : ?0 }")
    @Update("{ '$set' : { 'phoneVerified' : ?1 } }")
    void updatePhoneVerified(String email, boolean newValue);
}