package br.com.fiap.tastytap.insfraestructure.user;

import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.application.user.UserResponse;
import br.com.fiap.tastytap.domain.user.CPF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class DefaultUserClient implements UserGateway {

    @Value("${users.api.url}")
    private String url;

    private final RestTemplate restTemplate;

    public DefaultUserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<UserResponse> findByCPF(final CPF cpf) {
        ResponseEntity<UserResponse> response = this.restTemplate.getForEntity(url + "/user/".concat(cpf.getCPFWithoutPonctuation()), UserResponse.class, cpf);

        return Optional.of(response)
                .filter(resp -> resp.getStatusCode().is2xxSuccessful())
                .map(ResponseEntity::getBody);
    }
}
