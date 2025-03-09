package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.Client;


public interface ClientRepository {
    Client save(Client user);
    Client findByEmail(String email);
}
