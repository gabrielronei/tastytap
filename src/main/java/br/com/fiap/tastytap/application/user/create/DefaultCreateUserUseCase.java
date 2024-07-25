package br.com.fiap.tastytap.application.user.create;

import br.com.fiap.tastytap.application.user.SimpleUserView;
import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.domain.user.User;

import java.util.Optional;

public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private final UserGateway userGateway;

    public DefaultCreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Optional<SimpleUserView> execute(NewUserCommand newUserCommand) {
        if (userGateway.findByEmail(newUserCommand.getEmail()).isPresent() || userGateway.findByCPF(newUserCommand.getDomainCPF()).isPresent()) {
            throw new RuntimeException("Já existe um usuario com este e-mail ou cpf!");
        }

        User newUser = userGateway.save(newUserCommand.toUser());

        return newUser != null ? Optional.of(new SimpleUserView(newUser)) : Optional.empty();
    }
}
