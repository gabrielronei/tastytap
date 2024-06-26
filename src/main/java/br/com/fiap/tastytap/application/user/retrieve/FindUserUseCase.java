package br.com.fiap.tastytap.application.user.retrieve;

import br.com.fiap.tastytap.application.UseCase;
import br.com.fiap.tastytap.application.user.SimpleUserView;

import java.util.Optional;

public abstract class FindUserUseCase extends UseCase<String, Optional<SimpleUserView>> {
}
