package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.domain.order.*;
import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class OrderEntityTest {

    @Test
    void name() {
        Order order = new Order(1L, List.of(new OrderItem(1L, new Product(1L , "Lanche maneiro", "Descricao legal", "https://any.image.com", BigDecimal.TEN, Category.SANDWICH, LocalDateTime.now()), 4)), BigDecimal.TEN, LocalDateTime.now(), Status.RECEIVED, 10L, 10L, PaymentStatus.PENDING, "https://any.image.com");
        OrderEntity orderEntity = new OrderEntity(order);

        Assertions.assertNotNull(orderEntity);
        Assertions.assertNotNull(orderEntity.getTotal());
        Assertions.assertNotNull(orderEntity.getStatus());
        Assertions.assertNotNull(orderEntity.getPossibleUser());
        Assertions.assertNotNull(orderEntity.toDomain());
        Assertions.assertNotNull(orderEntity.getCreatedAt());
        Assertions.assertNotNull(orderEntity.getId());
        Assertions.assertNotNull(orderEntity.getItems());
        Assertions.assertNotNull(orderEntity.getQrCodeUrl());
        Assertions.assertNotNull(orderEntity.toDomain());
    }
}