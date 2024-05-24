package br.com.fiap.tastytap.presentation.product.create;

import br.com.fiap.tastytap.application.product.create.NewProductCommand;
import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public final class NewProductForm implements NewProductCommand {

    @NotBlank(message = "Nome não pode estar em branco ou nulo!")
    private final String name;

    @NotBlank
    private final String description;

    @NotBlank
    @URL
    private final String imageURL;

    @NotNull
    private final Category category;

    @NotNull
    @DecimalMin(value = "1")
    private final BigDecimal price;

    public NewProductForm(String name, String description, String imageURL, Category category, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.category = category;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public String getImageURL() {
        return this.imageURL;
    }

    @Override
    public Product toProduct() {
        return new Product(this.name, this.description, this.imageURL, this.price, this.category);
    }
}
