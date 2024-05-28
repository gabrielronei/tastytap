package br.com.fiap.tastytap.domain.product;

import java.math.BigDecimal;

public interface FieldsToUpdateProduct {

    Long getId();
    String getDescription();
    String getImageURL();
    BigDecimal getPrice();
}
