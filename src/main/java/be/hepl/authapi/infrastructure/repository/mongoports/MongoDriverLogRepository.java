package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.DriverEntity;
import be.hepl.authapi.infrastructure.entity.DriverLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDriverLogRepository extends MongoRepository<DriverLogEntity, String> {
}
