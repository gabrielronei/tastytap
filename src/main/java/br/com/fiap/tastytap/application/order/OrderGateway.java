package br.com.fiap.tastytap.application.order;

import br.com.fiap.tastytap.domain.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {
    Order persist(Order order);
    List<Order> findAll();
    Optional<Order> findByNumber(Long number);
    Optional<Order> findByTransactionId(Long transactionId);
}
