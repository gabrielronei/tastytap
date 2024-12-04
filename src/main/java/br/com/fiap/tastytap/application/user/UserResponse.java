package br.com.fiap.tastytap.application.user;

import br.com.fiap.tastytap.domain.user.CPF;

public record UserResponse(Long id, String cpf) {

    public CPF getCpf() {
        return new CPF(cpf);
    }
}