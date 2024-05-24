package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.NewItemOrderCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class NewItemOrderForm implements NewItemOrderCommand {

    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;

    public NewItemOrderForm(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
