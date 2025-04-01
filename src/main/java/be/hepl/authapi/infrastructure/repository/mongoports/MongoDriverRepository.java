package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import be.hepl.authapi.infrastructure.entity.DriverEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDriverRepository extends MongoRepository<DriverEntity, String> {
}
