package be.hepl.authapi.infrastructure.service;

import be.hepl.authapi.domain.service.MasiIdService;
import be.hepl.authapi.domain.exception.UserNotFoundException;
import be.hepl.authapi.domain.model.jwt.Role;
import be.hepl.authapi.infrastructure.service.model.MasiIdServiceLoginRequest;
import be.hepl.authapi.infrastructure.service.model.MasiIdServiceLoginResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MasiIdServiceImpl implements MasiIdService {

    private final WebClient webClient;

    public MasiIdServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .build();
    }

    @Override
    public String UserConnection(String phone, Role role) {
/*        MasiIdServiceLoginResponse masiIdServiceLoginResponse =
                webClient.post()
                .uri("")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode().value() == 404) {
                        throw new UserNotFoundException("Driver not found in MASI ID server");
                    }
                    return Mono.error(new RuntimeException("Erreur 4XX détectée !"));
                })
                .bodyToMono(MasiIdServiceLoginResponse.class)
                .block();

        return masiIdServiceLoginResponse.phoneNumber();*/

        return "+32484185894";
    }
}
