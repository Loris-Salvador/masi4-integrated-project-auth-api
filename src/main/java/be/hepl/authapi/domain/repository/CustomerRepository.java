package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.customer.Customer;

/// <comments>
/// Interface utilisée par les implémentations qui permettent de stocker les clients
/// </comments>
public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findByEmail(String email);
    Customer findById(String id);
    void updateEmailVerification(String email, boolean newValue);
    void updatePhoneVerification(String email, boolean newValue);
}
