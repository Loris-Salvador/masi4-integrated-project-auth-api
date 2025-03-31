package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.client.ClientLog;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import be.hepl.authapi.infrastructure.mapper.clientlog.ClientLogEntityToClientLogMapper;
import be.hepl.authapi.infrastructure.mapper.clientlog.ClientLogToClientLogEntityMapper;
import be.hepl.authapi.domain.model.client.ClientLogAnonymous;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoClientLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/// <comments>
/// Implémentation (manuelle ici pas comme les mongo ports) du repo pour les log clients
/// </comments>
@Repository
public class ClientLogMongoRepositoryImpl implements ClientLogRepository {

    private final MongoClientLogRepository mongoClientLogRepository;

    private final MongoTemplate mongoTemplate;


    public ClientLogMongoRepositoryImpl(MongoClientLogRepository mongoClientLogRepository,
                                        MongoTemplate mongoTemplate)
    {
        this.mongoClientLogRepository = mongoClientLogRepository;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void save(ClientLog clientLog) {
        ClientLogEntity entity = ClientLogToClientLogEntityMapper.INSTANCE.map(clientLog);

        ClientLogEntityToClientLogMapper.INSTANCE.map(mongoClientLogRepository.save(entity));
    }

    @Override
    public List<ClientLogAnonymous> getClientLogsSince(Instant since) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("timestamp").gte(since)),

                Aggregation.lookup("clients", "client_id", "_id", "clientInfo"),

                Aggregation.unwind("clientInfo", false),

                Aggregation.project("timestamp", "method", "success")
                        .and("clientInfo.gender").as("gender")
                        .and("clientInfo.birthday").as("birthday")
        );

        AggregationResults<ClientLogAnonymous> result = mongoTemplate.aggregate(
                aggregation, "client_log", ClientLogAnonymous.class
        );

        return result.getMappedResults();
    }


}
