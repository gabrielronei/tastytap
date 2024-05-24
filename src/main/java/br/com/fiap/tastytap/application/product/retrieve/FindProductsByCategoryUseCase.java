package br.com.fiap.tastytap.application.product.retrieve;

import br.com.fiap.tastytap.application.UseCase;
import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.domain.product.Category;

import java.util.List;

public abstract class FindProductsByCategoryUseCase extends UseCase<Category, List<SimpleProductView>> {
}
