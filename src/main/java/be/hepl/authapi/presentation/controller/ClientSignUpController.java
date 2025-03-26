package be.hepl.authapi.presentation.controller;

import be.hepl.authapi.application.dto.request.ClientCreateRequest;
import be.hepl.authapi.application.dto.response.ClientCreateResponse;
import be.hepl.authapi.application.usecase.client.CreateClientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/signup")
public class ClientSignUpController {

    private final CreateClientUseCase createClientUseCase;


    public ClientSignUpController(CreateClientUseCase createClientUseCase)
    {
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping()
    public ResponseEntity<ClientCreateResponse> signup(@Valid @RequestBody ClientCreateRequest clientCreateRequest)
    {
        ClientCreateResponse response = createClientUseCase.create(clientCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
