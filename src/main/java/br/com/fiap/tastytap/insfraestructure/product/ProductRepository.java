package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.domain.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory(Category category);
}
