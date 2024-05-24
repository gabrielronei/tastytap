package br.com.fiap.tastytap.application.product.update;

import br.com.fiap.tastytap.application.UseCase;
import br.com.fiap.tastytap.application.product.SimpleProductView;

import java.util.Optional;

public abstract class UpdateProductUseCase extends UseCase<UpdateProductCommand, Optional<SimpleProductView>> {
}
