package br.com.fiap.tastytap.application.product.retrieve;

import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.domain.product.Category;

import java.util.List;

public final class DefaultFindProductsByCategoryUseCase extends FindProductsByCategoryUseCase {

    private final ProductGateway productGateway;

    public DefaultFindProductsByCategoryUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public List<SimpleProductView> execute(Category category) {
        return productGateway.findAllByCategory(category).stream().map(SimpleProductView::new).toList();
    }
}
