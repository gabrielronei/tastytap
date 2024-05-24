package br.com.fiap.tastytap.application.product;

import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product persist(Product product);

    Optional<Product> findById(Long id);

    boolean delete(Product product);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByIdIn(List<Long> ids);
}
