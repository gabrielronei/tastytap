package br.com.fiap.tastytap.application.user.retrieve;

import br.com.fiap.tastytap.application.user.SimpleUserView;
import br.com.fiap.tastytap.application.user.UserGateway;

import java.util.Optional;

public final class DefaultFindUserUseCase extends FindUserUseCase {

    private final UserGateway userGateway;

    public DefaultFindUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Optional<SimpleUserView> execute(String cpf) {
        return userGateway.findByCPF(cpf).map(SimpleUserView::new);
    }
}
