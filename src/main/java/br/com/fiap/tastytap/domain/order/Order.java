package br.com.fiap.tastytap.domain.order;

import br.com.fiap.tastytap.domain.user.User;
import br.com.fiap.tastytap.utils.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Order {

    private Long id;
    private User user;
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal total;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Status status = Status.RECEIVED;

    public Order(List<OrderItem> items) {
        ValidationUtils.notNull(items, "items cannot be null");
        ValidationUtils.isTrue(!items.isEmpty(), "items should not be empty");

        this.items = items;
        this.total = items.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order(Long id, List<OrderItem> items, BigDecimal total, LocalDateTime createdAt, Status status) {
        ValidationUtils.notNull(id, "id cannot be null");
        ValidationUtils.notNull(items, "items cannot be null");
        ValidationUtils.notNull(total, "total cannot be null");
        ValidationUtils.notNull(createdAt, "createdAt cannot be null");
        ValidationUtils.notNull(status, "status cannot be null");

        this.id = id;
        this.items = items;
        this.total = total;
        this.createdAt = createdAt;
        this.status = status;
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
        return items;
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
