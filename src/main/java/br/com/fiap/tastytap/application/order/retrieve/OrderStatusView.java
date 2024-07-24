package br.com.fiap.tastytap.application.order.retrieve;

import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.Status;

public record OrderStatusView(Status status) {

    public OrderStatusView(Order order) {
        this(order.getStatus());
    }
}
