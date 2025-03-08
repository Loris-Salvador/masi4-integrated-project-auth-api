package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

}