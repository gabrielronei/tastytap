package br.com.fiap.tastytap.domain.product;

import br.com.fiap.tastytap.utils.ValidationUtils;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Instant createdAt = Instant.now();

    public Product(String name, String description, BigDecimal price, Category category) {
        ValidationUtils.notBlank(name, "Product name cannot be blank");
        ValidationUtils.notBlank(description, "Description name cannot be blank");
        ValidationUtils.notNull(price, "Product price cannot be null");
        ValidationUtils.isTrue(price.compareTo(BigDecimal.ONE) > 0, "Product price should be positive");
        ValidationUtils.notNull(category, "Product category cannot be null");

        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    enum Category {
        SANDWICH,
        SIDE_DISH,
        DRINK,
        DESERT
    }
}
