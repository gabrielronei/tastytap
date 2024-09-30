package br.com.fiap.tastytap.insfraestructure.user;

import br.com.fiap.tastytap.domain.user.Role;
import br.com.fiap.tastytap.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @CPF
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @PastOrPresent
    private LocalDateTime createdAt;

    @Deprecated
    public UserEntity() {
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

    public String getCpf() {
        return cpf;
    }

    public Role getRole() {
        return role;
    }

    public User toDomain() {
        return new User(this.id, this.name, this.email, this.cpf, this.createdAt, this.role);
    }
}
