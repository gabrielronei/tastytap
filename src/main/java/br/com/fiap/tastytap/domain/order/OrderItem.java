package br.com.fiap.tastytap.domain.order;

import br.com.fiap.tastytap.domain.product.Product;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;
    private Product product;
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public OrderItem(Long id, Product product, Integer quantity) {
        this(product, quantity);
        this.id = id;
    }
}
