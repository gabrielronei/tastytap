package br.com.fiap.tastytap.application.user;

import br.com.fiap.tastytap.domain.user.CPF;
import br.com.fiap.tastytap.domain.user.User;

import java.util.Optional;

public interface UserGateway {

    Optional<User> findByEmail(String email);
    Optional<User> findByCPF(CPF cpf);

    User save(User user);
}
