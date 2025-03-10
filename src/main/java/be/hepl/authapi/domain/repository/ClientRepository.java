package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.Client;


public interface ClientRepository {
    Client save(Client client);
    Client findByEmail(String email);
    void updateEmailVerification(String email, boolean newValue);
    void updatePhoneVerification(String email, boolean newValue);
}
