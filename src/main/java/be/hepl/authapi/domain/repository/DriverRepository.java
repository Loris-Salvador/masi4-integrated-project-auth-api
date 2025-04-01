package be.hepl.authapi.domain.repository;

import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.model.driver.Driver;

public interface DriverRepository {

    Driver findById(String id);
}
