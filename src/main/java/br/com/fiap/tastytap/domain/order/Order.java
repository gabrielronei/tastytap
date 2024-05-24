package br.com.fiap.tastytap.domain.order;

import br.com.fiap.tastytap.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Order {

    private Long id;
    private User user;
    private List<OrderItem> products = new ArrayList<>();
    private BigDecimal total;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Status status = Status.RECEIVED;

    public Order(Long id, List<OrderItem> items, BigDecimal total, LocalDateTime createdAt, Status status) {
        this.id = id;
        this.products = items;
        this.total = total;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Order(List<OrderItem> items) {
        this.products = items;
        this.total = items.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        ;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Optional<User> getPossibleUser() {
        return Optional.ofNullable(user);
    }

    public List<OrderItem> getOrderItems() {
        return products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }
}
