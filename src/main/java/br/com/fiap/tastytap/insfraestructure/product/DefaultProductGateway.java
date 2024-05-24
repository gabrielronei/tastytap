package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.domain.product.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class DefaultProductGateway implements ProductGateway {

    private final ProductRepository productRepository;

    public DefaultProductGateway(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        ProductEntity entity = productRepository.save(new ProductEntity(product));
        return entity.toDomain();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(ProductEntity::toDomain);
    }

    @Override
    public boolean delete(Product product) {
        productRepository.deleteById(product.getId());
        return true;
    }
}
