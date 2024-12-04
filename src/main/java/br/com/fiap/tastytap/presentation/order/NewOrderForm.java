package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.NewOrderCommand;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.*;

public final class NewOrderForm implements NewOrderCommand {

    @Nullable
    private final String cpf;

    @Size(min = 1, max = 20)
    private List<@Valid NewItemOrderForm> items = new ArrayList<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewOrderForm(String cpf, List<NewItemOrderForm> items) {
        this.cpf = cpf;
        this.items = items;
    }

    public Optional<String> getPossibleCpf() {
        return Optional.ofNullable(cpf);
    }

    @Override
    public List<NewItemOrderForm> getItems() {
        return items;
    }
}
