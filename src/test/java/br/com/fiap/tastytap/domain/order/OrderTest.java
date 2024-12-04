package br.com.fiap.tastytap.domain.order;

import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class OrderTest {

    @Test
    void shouldCheckIfEverthingIsAlright() {
        Order order = new Order(List.of(new OrderItem(1L, new Product(1L , "Lanche maneiro", "Descricao legal", "https://any.image.com", BigDecimal.TEN, Category.SANDWICH, LocalDateTime.now()), 4)));

        Assertions.assertThat(order.getTotal()).isEqualTo(BigDecimal.valueOf(40));
        Assertions.assertThat(order.isValidToPay()).isTrue();
    }

    @Test
    void shouldUpdateCorrectly() {
        Order order = new Order(List.of(new OrderItem(1L, new Product(1L , "Lanche maneiro", "Descricao legal", "https://any.image.com", BigDecimal.TEN, Category.SANDWICH, LocalDateTime.now()), 4)));

        long updatedTransactionId = 10L;
        String url = "https://any.another.com";

        order.update(updatedTransactionId, url);

        Assertions.assertThat(order.getTransactionId()).isEqualTo(updatedTransactionId);
        Assertions.assertThat(order.getQrCodeUrl()).isEqualTo(url);
    }
}