package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.domain.order.*;
import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

class DefaultOrderGatewayTest {

    private OrderRepository orderRepository;
    private DefaultOrderGateway orderGateway;

    @BeforeEach
    void setUp() {
        this.orderRepository = Mockito.mock(OrderRepository.class);

        this.orderGateway = new DefaultOrderGateway(orderRepository);
    }

    @Test
    void findAll() {
        Mockito.when(this.orderRepository.findAllOrdersOrderByStatus()).thenReturn(Collections.emptyList());

        List<Order> all = this.orderGateway.findAll();

        Assertions.assertThat(all).isEmpty();
    }

    @Test
    void findByNumber() {
        Mockito.when(this.orderRepository.findByNumber(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Order> possibleOrder = this.orderGateway.findByNumber(123L);

        Assertions.assertThat(possibleOrder).isEmpty();
    }

    @Test
    void findByTransactionId() {
        Mockito.when(this.orderRepository.findByTransactionId(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Order> possibleOrder = this.orderGateway.findByTransactionId(123L);

        Assertions.assertThat(possibleOrder).isEmpty();
    }

    @Test
    void persist() {
        Order order = new Order(1L, List.of(new OrderItem(1L, new Product(1L , "Lanche maneiro", "Descricao legal", "https://any.image.com", BigDecimal.TEN, Category.SANDWICH, LocalDateTime.now()), 4)), BigDecimal.TEN, LocalDateTime.now(), Status.RECEIVED, 10L, 10L, PaymentStatus.PENDING, "https://any.image.com");
        OrderEntity orderEntity = new OrderEntity(order);

        Mockito.when(this.orderRepository.save(Mockito.any())).thenReturn(orderEntity);

        Order persistedOrder = this.orderGateway.persist(order);

        Assertions.assertThat(persistedOrder.getId()).isEqualTo(orderEntity.getId());
    }
}