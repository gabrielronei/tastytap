package br.com.fiap.tastytap.application.order;

import br.com.fiap.tastytap.domain.order.Order;

public interface OrderGateway {
    Order persist(Order order);
}
