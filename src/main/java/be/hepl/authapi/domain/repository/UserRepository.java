package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.User;


public interface UserRepository {
    User save(User user);
    User getByUsername(String username);
}
