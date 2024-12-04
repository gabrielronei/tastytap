package br.com.fiap.tastytap.application.user;

import br.com.fiap.tastytap.domain.user.CPF;

import java.util.Optional;

public interface UserGateway {
    Optional<UserResponse> findByCPF(CPF cpf);
}
