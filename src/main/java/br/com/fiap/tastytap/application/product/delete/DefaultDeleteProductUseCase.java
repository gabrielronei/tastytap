package br.com.fiap.tastytap.application.product.delete;

import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.domain.product.Product;

import java.util.Optional;

public final class DefaultDeleteProductUseCase extends DeleteProductUseCase {

    private final ProductGateway productGateway;

    public DefaultDeleteProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Boolean execute(Long productId) {
        Optional<Product> possibleProduct = productGateway.findById(productId);
        return possibleProduct.filter(productGateway::delete).isPresent();
    }
}
