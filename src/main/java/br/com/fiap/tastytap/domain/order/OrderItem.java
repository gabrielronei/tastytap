package br.com.fiap.tastytap.domain.order;

import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.utils.ValidationUtils;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;
    private Product product;
    private Integer quantity;
    private BigDecimal total;

    public OrderItem(Product product, Integer quantity) {
        ValidationUtils.notNull(product, "Product must not be null");
        ValidationUtils.isTrue(quantity > 0, "quantity should be greater than zero");

        this.product = product;
        this.quantity = quantity;
        this.total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public OrderItem(Long id, Product product, Integer quantity) {
        this(product, quantity);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return this.getProduct().getName();
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
