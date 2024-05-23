package br.com.fiap.tastytap.application.product;

import br.com.fiap.tastytap.domain.product.Product;

import java.math.BigDecimal;

public final class SimpleProductView {

    private final Long id;
    private final String name;
    private final String description;
    private final String imageURL;
    private final String createdAt;
    private final String category;
    private final BigDecimal price;

    public SimpleProductView(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageURL = product.getImageURL();
        this.createdAt = product.getFormattedCreatedAt();
        this.category = product.getCategoryDescription();
        this.price = product.getPrice();
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
