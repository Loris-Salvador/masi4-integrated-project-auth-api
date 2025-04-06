package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.customer.AnonymousCustomerLog;
import be.hepl.authapi.domain.model.customer.CustomerLog;
import be.hepl.authapi.domain.repository.CustomerLogRepository;
import be.hepl.authapi.infrastructure.entity.CustomerLogEntity;
import be.hepl.authapi.infrastructure.mapper.customerlog.CustomerLogToCustomerLogEntityMapper;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoCustomerLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.Instant;
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

        AggregationResults<AnonymousCustomerLog> result = mongoTemplate.aggregate(
                aggregation, "customer_logs", AnonymousCustomerLog.class);

        return result.getMappedResults();
    }
}
