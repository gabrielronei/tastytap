package br.com.fiap.tastytap.presentation.product.update;

import br.com.fiap.tastytap.application.product.update.UpdateProductCommand;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public final class UpdateProductForm implements UpdateProductCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    @URL
    @NotBlank
    private String imageURL;

    @NotNull
    @DecimalMin("1")
    private BigDecimal price;

    public UpdateProductForm(Long id, String description, String imageURL, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getImageURL() {
        return this.imageURL;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }
}
