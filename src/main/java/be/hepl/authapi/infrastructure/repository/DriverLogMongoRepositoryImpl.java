package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.client.AnonymousClientLog;
import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import be.hepl.authapi.domain.repository.DriverLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class DriverLogMongoRepositoryImpl implements DriverLogRepository {

    private final MongoTemplate mongoTemplate;

    public DriverLogMongoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<AnonymousDriverLog> getAnonymousDriverLogsSince(Instant since) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("timestamp").gte(since)),

                Aggregation.lookup("drivers", "phone", "_id", "driverInfo"),

                Aggregation.unwind("driverInfo", false),

                Aggregation.project("timestamp", "success")
                        .and("driverInfo.gender").as("gender")
                        .and("driverInfo.birthday").as("birthday")
        );

        AggregationResults<AnonymousDriverLog> result = mongoTemplate.aggregate(
                aggregation, "driver_logs", AnonymousDriverLog.class);

        return result.getMappedResults();
    }
}
