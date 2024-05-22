package br.com.fiap.tastytap.domain.user;

import br.com.fiap.tastytap.utils.ValidationUtils;

import java.time.Instant;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private Instant created = Instant.now();
    private Role role = Role.REGULAR;

    public User(String name, String email, String cpf) {
        ValidationUtils.notBlank(name, "Name cannot be blank");
        ValidationUtils.notBlank(email, "Email cannot be blank");
        ValidationUtils.notBlank(cpf, "Cpf cannot be blank");

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    enum Role {
        ADMIN,
        REGULAR
    }
}
