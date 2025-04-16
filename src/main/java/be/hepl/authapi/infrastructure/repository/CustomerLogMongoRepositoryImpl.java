package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import be.hepl.authapi.infrastructure.entity.AnonymousCustomerLogEntity;
import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import be.hepl.authapi.infrastructure.mapper.customerlog.CustomerLogToCustomerLogEntityMapper;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoCustomerLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Repository
public class CustomerLogMongoRepositoryImpl implements CustomerLogRepository {

    private final MongoCustomerLogRepository mongoCustomerLogRepository;

    private final MongoTemplate mongoTemplate;


    public CustomerLogMongoRepositoryImpl(MongoCustomerLogRepository mongoCustomerLogRepository,
                                          MongoTemplate mongoTemplate)
    {
        this.mongoCustomerLogRepository = mongoCustomerLogRepository;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void save(CustomerLog customerLog) {
        CustomerLogEntity entity = CustomerLogToCustomerLogEntityMapper.INSTANCE.map(customerLog);

        mongoCustomerLogRepository.save(entity);
    }

    @Override
    public List<AnonymousCustomerLog> getAnonymousCustomerLogsSince(Instant since) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("timestamp").gte(since)),

                Aggregation.lookup("customers", "customer_id", "_id", "customerInfo"),

                Aggregation.unwind("customerInfo", false),

                Aggregation.project("timestamp", "method", "success")
                        .and("customerInfo.gender").as("gender")
                        .and("customerInfo.birthday").as("birthday")
        );

        AggregationResults<AnonymousCustomerLogEntity> result = mongoTemplate.aggregate(
                aggregation, "customer_logs", AnonymousCustomerLogEntity.class);


        ZoneId zone = ZoneId.systemDefault();
        return result.getMappedResults().stream()
                .map(e -> new AnonymousCustomerLog(
                        e.timestamp(),
                        e.method(),
                        e.success(),
                        e.gender(),
                        e.birthday() != null
                                ? Period.between(LocalDate.ofInstant(e.birthday(), zone), LocalDate.now(zone)).getYears()
                                : -1
                ))
                .toList();
    }
}
