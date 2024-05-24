package br.com.fiap.tastytap.application.order.create;

import br.com.fiap.tastytap.application.user.SimpleUserView;
import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public final class SimpleOrderView {

    private final String orderNumber;
    private final SimpleUserView user;
    private final BigDecimal totalPrice;
    private final List<SimpleOrderItemView> items;

    public SimpleOrderView(Order order) {
        this.orderNumber = "#%s".formatted(order.getId());
        this.user = order.getPossibleUser().map(SimpleUserView::new).orElse(null);
        this.totalPrice = order.getTotal();
        this.items = order.getOrderItems().stream().map(SimpleOrderItemView::new).toList();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public SimpleUserView getUser() {
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
