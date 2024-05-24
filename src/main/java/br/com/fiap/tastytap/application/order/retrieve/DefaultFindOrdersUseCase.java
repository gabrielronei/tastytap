package br.com.fiap.tastytap.application.order.retrieve;

import br.com.fiap.tastytap.application.order.OrderGateway;
import br.com.fiap.tastytap.application.order.create.SimpleOrderView;

import java.util.List;

public class DefaultFindOrdersUseCase extends FindOrdersUseCase {
    private final OrderGateway orderGateway;

    public DefaultFindOrdersUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public List<SimpleOrderView> execute() {
        return orderGateway.findAll().stream().map(SimpleOrderView::new).toList();
    }
}
