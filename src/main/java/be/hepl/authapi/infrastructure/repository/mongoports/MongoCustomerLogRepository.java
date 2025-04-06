package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MongoCustomerLogRepository extends MongoRepository<CustomerLogEntity, String> {
}
