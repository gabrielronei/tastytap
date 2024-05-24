package br.com.fiap.tastytap.application.order.create;

import br.com.fiap.tastytap.application.order.OrderGateway;
import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.OrderItem;
import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.domain.user.User;

import java.util.*;
import java.util.stream.Collectors;

public final class DefaultCreateOrderUseCase extends CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final UserGateway userGateway;

    public DefaultCreateOrderUseCase(OrderGateway orderGateway,
                                     ProductGateway productGateway,
                                     UserGateway userGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
        this.userGateway = userGateway;
    }

    @Override
    public Optional<SimpleOrderView> execute(NewOrderCommand newOrderCommand) {
        List<Long> itemsIds = newOrderCommand.getItems().stream().map(NewItemOrderCommand::getProductId).toList();
        List<Product> products = productGateway.findAllByIdIn(itemsIds);
        Map<Long, Integer> productsAndQuantity = newOrderCommand.getItems().stream()
                .collect(Collectors.toMap(NewItemOrderCommand::getProductId, NewItemOrderCommand::getQuantity));

        List<OrderItem> orderItems = products.stream()
                .map(product -> new OrderItem(product, productsAndQuantity.getOrDefault(product.getId(), 1)))
                .toList();

        Order order = new Order(orderItems);
        Optional<User> possibleUser = newOrderCommand.getPossibleCpf().flatMap(userGateway::findByCPF);
        possibleUser.ifPresent(order::setUser);

        Order persistedOrder = this.orderGateway.persist(order);

        return persistedOrder != null ? Optional.of(new SimpleOrderView(persistedOrder)) : Optional.empty();
    }
}
