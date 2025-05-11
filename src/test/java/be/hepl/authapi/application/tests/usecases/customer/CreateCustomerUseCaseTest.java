package be.hepl.authapi.application.tests.usecases.customer;

import be.hepl.authapi.application.dto.request.CustomerCreateRequest;
import be.hepl.authapi.application.dto.response.CustomerCreateResponse;
import be.hepl.authapi.application.service.PasswordHashingService;
import be.hepl.authapi.application.usecase.customer.CreateCustomerUseCase;
import be.hepl.authapi.domain.model.customer.Customer;
import be.hepl.authapi.domain.model.customer.Gender;
import be.hepl.authapi.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordHashingService passwordHashingService;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    @Test
    void create_WhenCreatingAUser_TheEmailShouldNotBeVerified() {
        //Arrange
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(
                "user@example.com",
                "Password1!",
                "1234567890",
                "Doe",
                "John",
                "M",
                Instant.parse("1990-01-01T00:00:00Z")
        );
        Customer customerReturned = new Customer();

        String encodedPassword = "encodedPassword123";
        when(passwordHashingService.hashPassword(anyString())).thenReturn(encodedPassword);

        when(customerRepository.findById(anyString())).thenReturn(null);

        when(customerRepository.save(any(Customer.class))).thenReturn(customerReturned);

        //Act
        CustomerCreateResponse customerCreateResponse = createCustomerUseCase.create(customerCreateRequest);

        //Assert
        assertFalse(customerCreateResponse.emailVerified(), "Email should not be verified");
    }

    @Test
    void create_WhenCreatingAUser_TheUserCreatedShouldMatchTheCustomerSaved() {
        //Arrange
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(
                "user@example.com",
                "Password1!",
                "1234567890",
                "Doe",
                "John",
                "M",
                Instant.parse("1990-01-01T00:00:00Z")
        );
        Customer customerReturned = new Customer();
        customerReturned.setEmail("email@example.com");
        customerReturned.setPassword("Password1!");
        customerReturned.setFirstName("John");
        customerReturned.setName("Doe");
        customerReturned.setGender(Gender.M);
        customerReturned.setPhoneNumber("1234567890");

        String encodedPassword = "encodedPassword123";
        when(passwordHashingService.hashPassword(anyString())).thenReturn(encodedPassword);

        when(customerRepository.findById(anyString())).thenReturn(null);

        when(customerRepository.save(any(Customer.class))).thenReturn(customerReturned);


        //Act
        CustomerCreateResponse customerCreateResponse = createCustomerUseCase.create(customerCreateRequest);

        //Assert
        assertNotNull(customerCreateResponse);
        assertEquals("email@example.com", customerCreateResponse.email());
        assertEquals("John", customerCreateResponse.firstName());
        assertEquals("Doe", customerCreateResponse.name());
        assertEquals("M", customerCreateResponse.gender());
        assertEquals("1234567890", customerCreateResponse.phoneNumber());
    }
}