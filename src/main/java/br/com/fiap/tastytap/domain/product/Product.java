package br.com.fiap.tastytap.domain.product;

import br.com.fiap.tastytap.utils.DateFormatterUtils;
import br.com.fiap.tastytap.utils.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Product {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Category category;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Product(String name, String description, String imageUrl, BigDecimal price, Category category) {
        ValidationUtils.notBlank(name, "Product name cannot be blank");
        ValidationUtils.notBlank(description, "Product description name cannot be blank");
        ValidationUtils.notBlank(imageUrl, "Product imageUrl name cannot be blank");
        ValidationUtils.notNull(price, "Product price cannot be null");
        ValidationUtils.isTrue(price.compareTo(BigDecimal.ONE) >= 0, "Product price should be positive");
        ValidationUtils.notNull(category, "Product category cannot be null");

        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, String name, String description, String imageUrl, BigDecimal price, Category category, LocalDateTime createdAt) {
        this(name, description, imageUrl, price, category);

        ValidationUtils.notNull(id, "id price cannot be null");
        ValidationUtils.notNull(createdAt, "createdAt cannot be null");
        this.id = id;
        this.createdAt = createdAt;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable(updatedAt);
    }

    public String getCategoryDescription() {
        return category.getDescription();
    }

    public String getFormattedCreatedAt() {
        return DateFormatterUtils.format(this.createdAt);
    }

    public String getFormattedUpdatedAt() {
        return DateFormatterUtils.format(this.updatedAt);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void update(FieldsToUpdateProduct updateProduct) {
        this.id = updateProduct.getId();
        this.description = updateProduct.getDescription();
        this.price = updateProduct.getPrice();
        this.imageUrl = updateProduct.getImageURL();
        this.updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
