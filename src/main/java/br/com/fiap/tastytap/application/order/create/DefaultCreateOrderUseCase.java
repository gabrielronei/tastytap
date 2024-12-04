package br.com.fiap.tastytap.application.order.create;

import br.com.fiap.tastytap.application.order.OrderGateway;
import br.com.fiap.tastytap.application.payment.PaymentGateway;
import br.com.fiap.tastytap.application.payment.QRCodeView;
import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.application.user.UserResponse;
import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.OrderItem;
import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.domain.user.CPF;

import java.util.*;
import java.util.stream.Collectors;

public final class DefaultCreateOrderUseCase extends CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final UserGateway userGateway;
    private final PaymentGateway paymentGateway;

    public DefaultCreateOrderUseCase(OrderGateway orderGateway,
                                     ProductGateway productGateway,
                                     UserGateway userGateway,
                                     PaymentGateway paymentGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
        this.userGateway = userGateway;
        this.paymentGateway = paymentGateway;
    }

    @Override
    public Optional<NewOrderView> execute(NewOrderCommand newOrderCommand) {
        List<Long> itemsIds = newOrderCommand.getItems().stream().map(NewItemOrderCommand::getProductId).toList();
        List<Product> products = productGateway.findAllByIdIn(itemsIds);

        List<Long> missingIds = findMissingIds(products, itemsIds);
        if (!missingIds.isEmpty()) {
            throw new RuntimeException("IDs não encontrados no banco: " + missingIds);
        }

        Map<Long, Integer> productsAndQuantity = newOrderCommand.getItems().stream()
                .collect(Collectors.toMap(NewItemOrderCommand::getProductId, NewItemOrderCommand::getQuantity));

        List<OrderItem> orderItems = products.stream()
                .map(product -> new OrderItem(product, productsAndQuantity.getOrDefault(product.getId(), 1)))
                .toList();

        Order order = new Order(orderItems);

        newOrderCommand.getPossibleCpf().map(CPF::new)
                .flatMap(userGateway::findByCPF)
                .map(UserResponse::getCpf)
                .ifPresent(order::setUser);

        QRCodeView qrcode = this.paymentGateway.generateQRCode(order.getNumber(), order.getTotal());
        order.update(qrcode.transactionId(), qrcode.qrCodeUrl());

        Order persistedOrder = this.orderGateway.persist(order);

        return persistedOrder != null ? Optional.of(new NewOrderView(persistedOrder)) : Optional.empty();
    }

    private List<Long> findMissingIds(List<Product> products, List<Long> itemsIds) {
        if (products.size() != itemsIds.size()) {
            Set<Long> idsFromFoundedProducts = products.stream().map(Product::getId).collect(Collectors.toSet());

            return itemsIds.stream()
                    .filter(id -> !idsFromFoundedProducts.contains(id))
                    .toList();
        }

        return Collections.emptyList();
    }
}
