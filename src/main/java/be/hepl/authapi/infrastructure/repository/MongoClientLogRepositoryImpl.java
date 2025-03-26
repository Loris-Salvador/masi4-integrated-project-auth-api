package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.model.client.ClientLog;
import be.hepl.authapi.domain.repository.ClientLogRepository;
import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import be.hepl.authapi.infrastructure.mapper.clientlog.ClientLogEntityToClientLogMapper;
import be.hepl.authapi.infrastructure.mapper.clientlog.ClientLogToClientLogEntityMapper;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoClientLogRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/// <comments>
/// Implémentation (manuelle ici pas comme les mongo ports) du repo pour les log clients
/// </comments>
@Repository
public class MongoClientLogRepositoryImpl implements ClientLogRepository {

    private final MongoClientLogRepository mongoClientLogRepository;

    public MongoClientLogRepositoryImpl(@Lazy MongoClientLogRepository mongoClientLogRepository) {
        this.mongoClientLogRepository = mongoClientLogRepository;
    }


    @Override
    public void save(ClientLog clientLog) {
        ClientLogEntity entity = ClientLogToClientLogEntityMapper.INSTANCE.map(clientLog);

        ClientLogEntityToClientLogMapper.INSTANCE.map(mongoClientLogRepository.save(entity));
    }
}
