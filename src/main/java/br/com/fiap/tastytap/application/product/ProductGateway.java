package br.com.fiap.tastytap.application.product;

import br.com.fiap.tastytap.domain.product.Product;

public interface ProductGateway {

    Product create(Product product);
}
