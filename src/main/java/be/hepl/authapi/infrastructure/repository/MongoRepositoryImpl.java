package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.User;
import be.hepl.authapi.domain.repository.UserRepository;
import be.hepl.authapi.infrastructure.entity.UserEntity;
import com.mongodb.client.MongoClient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoRepositoryImpl implements UserRepository {

    public final MongoUserRepository mongoRepository;

    public MongoRepositoryImpl(MongoUserRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }


    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        Optional<UserEntity> userEntityOpt = mongoRepository.findByUsername(username);

        System.out.println(userEntityOpt);

        if(userEntityOpt.isEmpty()) {
            return null;
        }

        UserEntity userEntity = userEntityOpt.get();

        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(userEntity, User.class);

        return user;

    }
}
