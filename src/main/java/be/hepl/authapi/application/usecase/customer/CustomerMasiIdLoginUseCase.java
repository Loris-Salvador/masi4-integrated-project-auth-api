package be.hepl.authapi.application.usecase.customer;

import be.hepl.authapi.application.dto.request.MasiIdLoginRequest;
import be.hepl.authapi.application.dto.response.MasiIdLoginResponse;
import be.hepl.authapi.application.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.masiid.MasiIdLoginStatus;
import be.hepl.authapi.domain.model.token.Role;
import be.hepl.authapi.domain.repository.CustomerRepository;
import be.hepl.authapi.application.service.MasiIdService;
import org.springframework.stereotype.Component;

@Component
public class CustomerMasiIdLoginUseCase {

    private final CustomerRepository customerRepository;

    private final MasiIdService masiIdService;

    public CustomerMasiIdLoginUseCase(final CustomerRepository customerRepository, final MasiIdService masiIdService) {
        this.customerRepository = customerRepository;
        this.masiIdService = masiIdService;
    }

    public MasiIdLoginResponse loginUseCase(MasiIdLoginRequest loginRequest) {
        try
        {
            customerRepository.findByPhoneNumber(loginRequest.phoneNumber());

            masiIdService.UserConnection(loginRequest.phoneNumber(), Role.CUSTOMER);

        }
        catch (UserNotFoundException e)
        {
            return new MasiIdLoginResponse(MasiIdLoginStatus.ERROR, e.getMessage());
        }

        return new MasiIdLoginResponse(MasiIdLoginStatus.SEND, "Check your masi id app");
    }
}
