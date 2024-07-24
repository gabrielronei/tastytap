package br.com.fiap.tastytap.application.order.retrieve;

import br.com.fiap.tastytap.application.order.OrderGateway;

import java.util.Optional;

public class DefaultGetOrderStatusByNumberUseCase extends GetOrderStatusByNumberUseCase {

    private final OrderGateway orderGateway;

    public DefaultGetOrderStatusByNumberUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public Optional<OrderStatusView> execute(Long number) {
        return orderGateway.findByNumber(number).map(OrderStatusView::new);
    }
}
