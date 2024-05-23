package br.com.fiap.tastytap.application.user;

import br.com.fiap.tastytap.domain.user.User;

public final class SimpleUserView {

    private final Long id;
    private final String name;
    private final String email;
    private final String cpf;

    public SimpleUserView(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
