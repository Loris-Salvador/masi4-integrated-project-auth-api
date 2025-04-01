package be.hepl.authapi.infrastructure.repository.mongoports;

import be.hepl.authapi.infrastructure.entity.ClientLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/// <comments>
/// Interface qui va être automatiquement implémenté par spring et fournir des méthodes
/// pour interagir avec la collection client (utilisation de la classe implémentée dans
/// la classe MongoClientLogRepositoryImpl)
/// </comments>
@Repository
public interface MongoClientLogRepository extends MongoRepository<ClientLogEntity, String> {
}
