package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.domain.product.ProductGateway;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductGateway implements ProductGateway {

    private final ProductRepository productRepository;

    public DefaultProductGateway(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        ProductEntity entity = productRepository.save(new ProductEntity(product));
        return entity.toDomain();
    }
}
