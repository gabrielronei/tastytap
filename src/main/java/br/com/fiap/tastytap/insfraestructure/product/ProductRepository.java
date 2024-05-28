package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.domain.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory(Category category);

    List<ProductEntity> findAllByIdIn(List<Long> ids);

    @Query("SELECT count(id) > 0 FROM OrderItemEntity WHERE productEntity.id = :productId")
    boolean hasItems(Long productId);
}
