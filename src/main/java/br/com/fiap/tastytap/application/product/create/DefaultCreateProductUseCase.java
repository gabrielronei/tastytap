package br.com.fiap.tastytap.application.product.create;

import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.application.product.ProductGateway;

import java.util.Optional;

public final class DefaultCreateProductUseCase extends CreateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultCreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Optional<SimpleProductView> execute(NewProductCommand newProductCommand) {
        Product product = this.productGateway.persist(newProductCommand.toProduct());

        //validar o produto
        return product != null ? Optional.of(new SimpleProductView(product)) : Optional.empty();
    }
}
