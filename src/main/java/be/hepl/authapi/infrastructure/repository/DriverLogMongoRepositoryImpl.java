package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.driver.AnonymousDriverLog;
import be.hepl.authapi.domain.model.driver.DriverLog;
import be.hepl.authapi.domain.repository.DriverLogRepository;
import be.hepl.authapi.infrastructure.entity.AnonymousCustomerLogEntity;
import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import be.hepl.authapi.infrastructure.entity.DriverLogEntity;
import be.hepl.authapi.infrastructure.mapper.customerlog.CustomerLogToCustomerLogEntityMapper;
import be.hepl.authapi.infrastructure.mapper.driver.DriverLogToDriverLogEntityMapper;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoDriverLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Repository
public class DriverLogMongoRepositoryImpl implements DriverLogRepository {

    private final MongoTemplate mongoTemplate;

    private final MongoDriverLogRepository driverLogRepository;

    public DriverLogMongoRepositoryImpl(MongoTemplate mongoTemplate, MongoDriverLogRepository driverLogRepository) {
        this.mongoTemplate = mongoTemplate;
        this.driverLogRepository = driverLogRepository;
    }


    @Override
    public void save(DriverLog driverLog) {
        DriverLogEntity entity = DriverLogToDriverLogEntityMapper.INSTANCE.map(driverLog);

        driverLogRepository.save(entity);
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

        AggregationResults<AnonymousCustomerLogEntity> result = mongoTemplate.aggregate(
                aggregation, "driver_logs", AnonymousCustomerLogEntity.class);

        ZoneId zone = ZoneId.systemDefault();
        return result.getMappedResults().stream()
                .map(e -> new AnonymousDriverLog(
                        e.timestamp(),
                        e.success(),
                        e.gender(),
                        e.birthday() != null
                                ? Period.between(LocalDate.ofInstant(e.birthday(), zone), LocalDate.now(zone)).getYears()
                                : -1
                ))
                .toList();
    }
}
