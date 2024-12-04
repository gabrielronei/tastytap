package br.com.fiap.tastytap.application.order.create;

import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.OrderItem;
import br.com.fiap.tastytap.domain.user.CPF;

import java.math.BigDecimal;
import java.util.List;

public class NewOrderView {

    private final Long number;
    private final String userDocument;
    private final BigDecimal totalPrice;
    private final List<NewOrderItemView> items;
    private final String qrCodeUrl;

    public NewOrderView(Order order) {
        this.number = order.getNumber();
        this.userDocument = order.getPossibleUserId().map(CPF::getCPF).orElse(null);
        this.totalPrice = order.getTotal();
        this.items = order.getOrderItems().stream().map(NewOrderView.NewOrderItemView::new).toList();
        this.qrCodeUrl = order.getQrCodeUrl();
    }

    public Long getNumber() {
        return number;
    }

    public String getUser() {
        return userDocument;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<NewOrderView.NewOrderItemView> getItems() {
        return items;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    class NewOrderItemView {
        private final String productName;
        private final int quantity;
        private final BigDecimal price;

        public NewOrderItemView(OrderItem orderItem) {
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
