package br.com.fiap.tastytap.application.order.create;

public interface NewItemOrderCommand {

    Long getProductId();

    int getQuantity();
}
