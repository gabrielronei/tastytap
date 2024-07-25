package br.com.fiap.tastytap.presentation.user;

import br.com.fiap.tastytap.application.user.create.NewUserCommand;
import br.com.fiap.tastytap.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public class NewUserForm implements NewUserCommand {

    @NotBlank(message = "Nome não pode estar em branco ou nulo!")
    private final String name;

    @Email
    @NotBlank
    private final String email;

    @CPF
    @NotBlank
    private final String cpf;

    public NewUserForm(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @JsonIgnore
    public br.com.fiap.tastytap.domain.user.CPF getDomainCPF() {
        return new br.com.fiap.tastytap.domain.user.CPF(getCPF());
    }

    public String getCPF() {
        return this.cpf;
    }

    @Override
    public User toUser() {
        return new User(this.name, this.email, this.cpf);
    }

}
