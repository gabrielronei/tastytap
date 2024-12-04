package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

class DefaultProductGatewayTest {
    private ProductRepository productRepository;
    private DefaultProductGateway defaultProductGateway;

    @BeforeEach
    void setUp() {
        this.productRepository = Mockito.mock(ProductRepository.class);

        this.defaultProductGateway = new DefaultProductGateway(productRepository);
    }

    @Test
    void hasItems() {
        Mockito.when(this.productRepository.hasItems(Mockito.anyLong())).thenReturn(true);

        boolean hasItems = this.defaultProductGateway.hasItems(1L);

        Assertions.assertThat(hasItems).isTrue();
    }

    @Test
    void findByNumber() {
        Mockito.when(this.productRepository.findAllByIdIn(Mockito.any())).thenReturn(Collections.emptyList());

        List<Product> possibleProducts = this.defaultProductGateway.findAllByIdIn(List.of(1L));

        Assertions.assertThat(possibleProducts).isEmpty();
    }

    @Test
    void findAllByCategory() {
        Mockito.when(this.productRepository.findAllByCategory(Mockito.any())).thenReturn(Collections.emptyList());

        List<Product> possibleProducts = this.defaultProductGateway.findAllByCategory(Category.SANDWICH);

        Assertions.assertThat(possibleProducts).isEmpty();
    }

    @Test
    void findById() {
        Mockito.when(this.productRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        Optional<Product> possibleProduct = this.defaultProductGateway.findById(1L);

        Assertions.assertThat(possibleProduct).isEmpty();
    }

    @Test
    void delete() {
        boolean possibleProduct = this.defaultProductGateway.delete(new Product(1L , "Lanche maneiro", "Descricao legal", "https://any.image.com", BigDecimal.TEN, Category.SANDWICH, LocalDateTime.now()));

        Assertions.assertThat(possibleProduct).isTrue();
    }

    @Test
    void persist() {
        ProductEntity productEntity = Mockito.mock(ProductEntity.class);
        Mockito.when(this.productRepository.save(Mockito.any())).thenReturn(productEntity);

        Product product = new Product(1L, "Lanche maneiro", "Descricao legal", "https://any.image.com", BigDecimal.TEN, Category.SANDWICH, LocalDateTime.now());
        Mockito.when(productEntity.toDomain()).thenReturn(product);

        Product persistedProduct = this.defaultProductGateway.persist(product);

        Assertions.assertThat(persistedProduct).isNotNull();
    }
}