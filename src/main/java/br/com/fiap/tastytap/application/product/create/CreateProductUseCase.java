package br.com.fiap.tastytap.application.product.create;

import br.com.fiap.tastytap.application.UseCase;
import br.com.fiap.tastytap.application.product.SimpleProductView;

import java.util.Optional;

public abstract class CreateProductUseCase extends UseCase<NewProductCommand, Optional<SimpleProductView>> {

}
