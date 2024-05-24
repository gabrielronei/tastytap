package br.com.fiap.tastytap.application.product;

import br.com.fiap.tastytap.domain.product.Product;

import java.util.Optional;

public interface ProductGateway {

    Product create(Product product);
    Optional<Product> findById(Long id);
    boolean delete(Product product);
}
