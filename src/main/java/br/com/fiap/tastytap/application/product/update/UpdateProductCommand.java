package br.com.fiap.tastytap.application.product.update;

import java.math.BigDecimal;

public interface UpdateProductCommand {

    Long getId();
    String getDescription();
    String getImageURL();
    BigDecimal getPrice();
}
