package br.com.fiap.tastytap.domain.product;

import br.com.fiap.tastytap.utils.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {

    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private BigDecimal price;
    private Category category;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Product(String name, String description, String imageURL, BigDecimal price, Category category) {
        ValidationUtils.notBlank(name, "Product name cannot be blank");
        ValidationUtils.notBlank(description, "Description name cannot be blank");
        ValidationUtils.notBlank(imageURL, "Description name cannot be blank");
        ValidationUtils.notNull(price, "Product price cannot be null");
        ValidationUtils.isTrue(price.compareTo(BigDecimal.ONE) > 0, "Product price should be positive");
        ValidationUtils.notNull(category, "Product category cannot be null");

        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageURL = imageURL;
    }

    public Product(Long id, String name, String description, String imageURL, BigDecimal price, Category category) {
        this(name, description, imageURL, price, category);

        ValidationUtils.notNull(id, "id price cannot be null");
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCategoryDescription() {
        return category.getDescription();
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(createdAt);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
