package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import be.hepl.authapi.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<UserEntity, String>, UserRepository {

    @Override
    default User save(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCardNumber(user.getCardNumber());
        userEntity.setEmail(user.getEmail());

        UserEntity savedEntity = save(userEntity);

        User userResponse = new User();
        userResponse.setCardNumber(savedEntity.getCardNumber());
        userResponse.setEmail(savedEntity.getEmail());
        userResponse.setId(savedEntity.getId());

        return userResponse;
    }
}