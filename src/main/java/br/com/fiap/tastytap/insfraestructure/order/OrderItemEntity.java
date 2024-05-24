package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.domain.order.OrderItem;
import br.com.fiap.tastytap.insfraestructure.product.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "orders_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Min(1)
    private int quantity;

    @NotNull
    private BigDecimal total;

    @Deprecated
    public OrderItemEntity() {}

    public OrderItemEntity(OrderItem item) {
        this.id = item.getId();
        this.productEntity = new ProductEntity(item.getProduct());
        this.quantity = item.getQuantity();
        this.total = item.getTotal();
    }

    public Long getId() {
        return id;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderItem toDomain() {
        return new OrderItem(this.id, this.productEntity.toDomain(), this.quantity);
    }
}
