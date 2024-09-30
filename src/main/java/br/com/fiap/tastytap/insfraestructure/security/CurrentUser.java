package br.com.fiap.tastytap.insfraestructure.security;

import br.com.fiap.tastytap.domain.user.User;

import java.util.Optional;


public interface CurrentUser {

    Optional<User> getPossibleUser();
}
