package br.com.fiap.tastytap.application.product.create;

import br.com.fiap.tastytap.domain.product.Category;
import br.com.fiap.tastytap.domain.product.Product;

import java.math.BigDecimal;

public interface NewProductCommand {

    String getName();
    String getDescription();
    Category getCategory();
    BigDecimal getPrice();
    String getImageURL();
    Product toProduct();
}
