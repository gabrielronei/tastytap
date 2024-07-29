package br.com.fiap.tastytap.application.order.retrieve;

import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.OrderItem;
import br.com.fiap.tastytap.domain.user.User;

import java.math.BigDecimal;
import java.util.List;

public final class SimpleOrderView {

    private final Long number;
    private final Long transactionId;
    private final String status;
    private final String paymentStatus;
    private final String user;
    private final BigDecimal totalPrice;
    private final List<SimpleOrderItemView> items;

    public SimpleOrderView(Order order) {
        this.number = order.getNumber();
        this.transactionId = order.getTransactionId();
        this.status = order.getStatus().name();
        this.paymentStatus = order.getPaymentStatus().name();
        this.user = order.getPossibleUser().map(User::getCpf).orElse(null);
        this.totalPrice = order.getTotal();
        this.items = order.getOrderItems().stream().map(SimpleOrderItemView::new).toList();
    }

    public Long getNumber() {
        return number;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getUser() {
        return user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<SimpleOrderItemView> getItems() {
        return items;
    }

    class SimpleOrderItemView {
        private final String productName;
        private final int quantity;
        private final BigDecimal price;

        public SimpleOrderItemView(OrderItem orderItem) {
            this.productName = orderItem.getProductName();
            this.quantity = orderItem.getQuantity();
            this.price = orderItem.getTotal();
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
}
