package br.com.fiap.tastytap.application.product.update;

import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.domain.product.Product;

import java.util.Optional;

public final class DefaultUpdateProductUseCase extends UpdateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultUpdateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Optional<SimpleProductView> execute(UpdateProductCommand updateProductCommand) {
        Optional<Product> possibleProduct = productGateway.findById(updateProductCommand.getId());
        if (possibleProduct.isEmpty()) return Optional.empty();

        Product product = possibleProduct.get();
        product.update(updateProductCommand);

        Product save = this.productGateway.persist(product);
        return Optional.of(new SimpleProductView(save));
    }
}
