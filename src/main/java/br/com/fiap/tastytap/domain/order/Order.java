package br.com.fiap.tastytap.domain.order;

import br.com.fiap.tastytap.domain.product.Product;
import br.com.fiap.tastytap.domain.user.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;
    private User user;
    private List<Product> products = new ArrayList<>();
    private BigDecimal total;
    private Instant createdAt = Instant.now();
    private Status status = Status.RECEIVED;

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
        this.total = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order(Long id, User user, List<Product> products, BigDecimal total, Instant createdAt, Status status) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.total = total;
        this.createdAt = createdAt;
        this.status = status;
    }

    enum Status {
        RECEIVED,
        PREPARING,
        DONE,
        FINISHED
    }

}
