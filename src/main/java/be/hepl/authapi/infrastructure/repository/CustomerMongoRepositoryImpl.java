package be.hepl.authapi.infrastructure.repository;

import be.hepl.authapi.domain.exception.UserAlreadyExistException;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.infrastructure.entity.CustomerEntity;
import be.hepl.authapi.infrastructure.mapper.customer.CustomerEntityToCustomerMapper;
import be.hepl.authapi.infrastructure.mapper.customer.CustomerToCustomerEntityMapper;
import be.hepl.authapi.infrastructure.repository.mongoports.MongoCustomerRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/// <comments>
/// Implémentation (manuelle ici pas comme les mongo ports) du repo pour les clients
/// </comments>
@Repository
public class CustomerMongoRepositoryImpl implements CustomerRepository {

    public final MongoCustomerRepository mongoRepository;

    public CustomerMongoRepositoryImpl(MongoCustomerRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }


    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerToCustomerEntityMapper.INSTANCE.map(customer);

        try
        {
            CustomerEntity response = mongoRepository.save(customerEntity);

            return CustomerEntityToCustomerMapper.INSTANCE.map(response);
        }
        catch (DuplicateKeyException e) {

            if (e.getMessage().contains("email")) {
                throw new UserAlreadyExistException("The email address is already in use.");
            }
            else if (e.getMessage().contains("phone_number")) {
                throw new UserAlreadyExistException("The phone number is already in use.");
            }
            else
                throw e;
        }
    }

    @Override
    public Customer findByEmail(String email) {
        Optional<CustomerEntity> customerEntityOptional = mongoRepository.findByEmail(email);


        if(customerEntityOptional.isEmpty()) {
            throw new UserNotFoundException("The customer with email " + email + " does not exist");
        }

        CustomerEntity customerEntity = customerEntityOptional.get();

        return CustomerEntityToCustomerMapper.INSTANCE.map(customerEntity);
    }

    @Override
    public Customer findById(String id) {
        Optional<CustomerEntity> customerEntityOptional = mongoRepository.findById(id);

        if(customerEntityOptional.isEmpty()) {
            return null;
        }

        return CustomerEntityToCustomerMapper.INSTANCE.map(customerEntityOptional.get());
    }

    @Override
    public void updateEmailVerification(String email, boolean newValue) {
        mongoRepository.updateEmailVerified(email, newValue);
    }

    @Override
    public void updatePhoneVerification(String email, boolean newValue) {
        mongoRepository.updatePhoneVerified(email, newValue);
    }


}
