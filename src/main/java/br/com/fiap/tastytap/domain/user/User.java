package br.com.fiap.tastytap.domain.user;

import br.com.fiap.tastytap.utils.ValidationUtils;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Role role = Role.REGULAR;

    public User(String name, String email, String cpf) {
        ValidationUtils.notBlank(name, "Name cannot be blank");
        ValidationUtils.notBlank(email, "Email cannot be blank");
        ValidationUtils.notBlank(cpf, "Cpf cannot be blank");

        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public User(Long id, String name, String email, String cpf, LocalDateTime createdAt, Role role) {
        this(name, email, cpf);
        this.id = id;
        this.createdAt = createdAt;
        this.role = role;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Role getRole() {
        return role;
    }


}
