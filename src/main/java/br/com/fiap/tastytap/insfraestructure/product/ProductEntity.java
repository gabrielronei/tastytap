package br.com.fiap.tastytap.insfraestructure.product;

import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @URL
    @NotBlank
    private String imageURL;

    @Min(value = 1)
    private BigDecimal price;

    @NotNull
    private Category category;

    @PastOrPresent
    private LocalDateTime createdAt;

    @Nullable
    private LocalDateTime updatedAt;

    @Deprecated
    public ProductEntity() {
    }

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageURL = product.getImageURL();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt().orElse(null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable(updatedAt);
    }

    public Product toDomain() {
        Product product = new Product(this.id, this.name, this.description, this.imageURL, this.price, this.category, this.createdAt);
        product.setUpdatedAt(this.updatedAt);
        return product;
    }
}

