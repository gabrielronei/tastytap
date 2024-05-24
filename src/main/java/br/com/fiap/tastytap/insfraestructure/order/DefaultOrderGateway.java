package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.application.order.OrderGateway;
import br.com.fiap.tastytap.domain.order.Order;
import org.springframework.stereotype.Service;

@Service
public final class DefaultOrderGateway implements OrderGateway {

    private final OrderRepository orderRepository;

    public DefaultOrderGateway(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order persist(Order order) {
        OrderEntity persisted = this.orderRepository.save(new OrderEntity(order));
        return persisted.toDomain();
    }
}
