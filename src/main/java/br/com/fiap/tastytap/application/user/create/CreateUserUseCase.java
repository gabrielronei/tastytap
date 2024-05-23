package br.com.fiap.tastytap.application.user.create;

import br.com.fiap.tastytap.application.UseCase;
import br.com.fiap.tastytap.application.user.SimpleUserView;

import java.util.Optional;

public abstract class CreateUserUseCase extends UseCase<NewUserCommand, Optional<SimpleUserView>> {
}
