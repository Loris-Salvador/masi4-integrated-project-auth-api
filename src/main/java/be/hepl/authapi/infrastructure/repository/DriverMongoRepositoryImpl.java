package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.application.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.driver.Driver;
import be.hepl.authapi.domain.repository.DriverRepository;
import be.hepl.authapi.infrastructure.entity.DriverEntity;
import be.hepl.authapi.infrastructure.mapper.driver.DriverEntityToDriverMapper;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoDriverRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DriverMongoRepositoryImpl implements DriverRepository {

    private final MongoDriverRepository mongoDriverRepository;

    public DriverMongoRepositoryImpl(MongoDriverRepository mongoDriverRepository) {
        this.mongoDriverRepository = mongoDriverRepository;
    }

    @Override
    public Driver findById(String id) {

        Optional<DriverEntity> result = mongoDriverRepository.findById(id);

        if (result.isEmpty()) {
            throw new UserNotFoundException("Driver not found");
        }

        return DriverEntityToDriverMapper.INSTANCE.map(result.get());
    }
}
