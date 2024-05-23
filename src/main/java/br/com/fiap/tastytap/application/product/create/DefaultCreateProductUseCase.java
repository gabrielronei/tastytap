package br.com.fiap.tastytap.application.product.create;

import br.com.fiap.tastytap.application.product.ProductView;
import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.domain.product.ProductGateway;

import java.util.Optional;

public class DefaultCreateProductUseCase extends CreateProductUseCase {

    private final ProductGateway productGateway;

    public DefaultCreateProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Optional<ProductView> execute(NewProductCommand newProductCommand) {
        Product product = this.productGateway.create(newProductCommand.toProduct());

        //validar o produto
        return product != null ? Optional.of(new ProductView(product)) : Optional.empty();
    }
}
