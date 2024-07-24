package br.com.fiap.tastytap.application.user.create;

import br.com.fiap.tastytap.domain.user.CPF;
import br.com.fiap.tastytap.domain.user.User;

public interface NewUserCommand {

    String getName();
    String getEmail();
    CPF getCPF();
    User toUser();
}
