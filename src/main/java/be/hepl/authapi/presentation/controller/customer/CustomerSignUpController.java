package be.hepl.authapi.presentation.controller.customer;

import be.hepl.authapi.application.dto.request.CustomerCreateRequest;
import be.hepl.authapi.application.dto.response.CustomerCreateResponse;
import be.hepl.authapi.application.usecase.customer.CreateCustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/// <comments>
/// Controller appelé lors de la création de compte d'un client
/// </comments>d
@RestController
@RequestMapping("/api/customer/signup")
public class CustomerSignUpController {

    private final CreateCustomerUseCase createCustomerUseCase;


    public CustomerSignUpController(CreateCustomerUseCase createCustomerUseCase)
    {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @PostMapping()
    public ResponseEntity<CustomerCreateResponse> signup(@Valid @RequestBody CustomerCreateRequest customerCreateRequest)
    {
        CustomerCreateResponse response = createCustomerUseCase.create(customerCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
