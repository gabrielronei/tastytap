package br.com.fiap.tastytap.application.order.update;

import br.com.fiap.tastytap.application.order.OrderGateway;
import br.com.fiap.tastytap.domain.order.Order;

import java.util.Optional;

public class DefaultUpdateOrderStatusUseCaseImpl extends UpdateOrderStatusUseCase {

    private final OrderGateway orderGateway;

    public DefaultUpdateOrderStatusUseCaseImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public Optional<OrderUpdatedStatusView> execute(Long number) {
        Optional<Order> possibleOrder = orderGateway.findByNumber(number);

        return possibleOrder.map(order -> {
            order.updateStatus();
            orderGateway.persist(order);
            return new OrderUpdatedStatusView(order);
        });
    }
}
