package br.com.fiap.tastytap.application.order;

import br.com.fiap.tastytap.domain.order.Order;

import java.util.List;

public interface OrderGateway {
    Order persist(Order order);
    List<Order> findAll();
}
