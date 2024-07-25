package br.com.fiap.tastytap.application.order.update;

import br.com.fiap.tastytap.domain.order.Order;

public final class OrderUpdatedStatusView {

    public final Long id;
    public final String status;

    public OrderUpdatedStatusView(Order order) {
        this.id = order.getId();
        this.status = order.getStatus().name();
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
