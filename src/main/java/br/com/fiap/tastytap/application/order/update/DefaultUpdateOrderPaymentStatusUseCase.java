package br.com.fiap.tastytap.application.order.update;

import br.com.fiap.tastytap.application.order.OrderGateway;
import br.com.fiap.tastytap.domain.order.Order;

import java.util.Optional;

public class DefaultUpdateOrderPaymentStatusUseCase extends UpdateOrderPaymentStatusUseCase {

    private final OrderGateway orderGateway;

    public DefaultUpdateOrderPaymentStatusUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void execute(UpdateOrderPaymentStatusCommand command) {
        Optional<Order> possibleOrder = orderGateway.findByNumber(command.orderNumber());
        possibleOrder.filter(Order::isValidToPay).ifPresent(order -> {
            order.setPaymentStatus(command.status());
            orderGateway.persist(order);
        });
    }
}
