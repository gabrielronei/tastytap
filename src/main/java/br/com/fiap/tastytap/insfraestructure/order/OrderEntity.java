package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.domain.order.Order;
import br.com.fiap.tastytap.domain.order.PaymentStatus;
import br.com.fiap.tastytap.domain.order.Status;
import br.com.fiap.tastytap.domain.user.CPF;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static br.com.fiap.tastytap.domain.order.PaymentStatus.PENDING;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime createdAt;

    @Nullable
    @Column(name = "user_document")
    private String userDocument;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> items = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @DecimalMin("1")
    private BigDecimal total;

    @NotNull
    private Long number;

    @Nullable
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PENDING;

    private String qrCodeUrl;

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.createdAt = order.getCreatedAt();
        this.userDocument = order.getPossibleUserId().map(CPF::getCPFWithoutPonctuation).orElse(null);
        this.items = order.getOrderItems().stream().map(OrderItemEntity::new).toList();
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.number = order.getNumber();
        this.transactionId = order.getTransactionId();
        this.paymentStatus = order.getPaymentStatus();
        this.qrCodeUrl = order.getQrCodeUrl();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Optional<CPF> getPossibleUser() {
        return Optional.ofNullable(userDocument).map(CPF::new);
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public Order toDomain() {
        Order order = new Order(this.id, this.items.stream().map(OrderItemEntity::toDomain).toList(), this.total,
                this.createdAt, this.status, this.number, this.transactionId, this.paymentStatus, this.qrCodeUrl);

        this.getPossibleUser().ifPresent(order::setUser);

        return order;
    }
}
