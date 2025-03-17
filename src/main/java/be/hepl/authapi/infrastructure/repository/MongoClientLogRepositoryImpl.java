package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.ClientLog;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import be.hepl.authapi.infrastructure.mapper.ClientLogEntityToClientLogMapper;
import be.hepl.authapi.infrastructure.mapper.ClientLogToClientLogEntityMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class MongoClientLogRepositoryImpl implements ClientLogRepository {

    private final MongoClientLogRepository mongoClientLogRepository;

    public MongoClientLogRepositoryImpl(@Lazy MongoClientLogRepository mongoClientLogRepository) {
        this.mongoClientLogRepository = mongoClientLogRepository;
    }


    @Override
    public ClientLog save(ClientLog clientLog) {
        ClientLogEntity entity = ClientLogToClientLogEntityMapper.INSTANCE.map(clientLog);

        return ClientLogEntityToClientLogMapper.INSTANCE.map(mongoClientLogRepository.save(entity));
    }
}
