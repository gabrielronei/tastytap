package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.NewOrderCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.util.StringUtils;

import java.util.*;

public final class NewOrderForm implements NewOrderCommand {

    @Nullable
    private String cpf;

    @Size(min = 1, max = 20)
    private List<@Valid NewItemOrderForm> items = new ArrayList<>();

    public NewOrderForm(@Nullable String cpf, List<NewItemOrderForm> items) {
        this.cpf = cpf;
        this.items = items;
    }

    @Nullable
    public String getCpf() {
        return cpf;
    }

    @Override
    public List<NewItemOrderForm> getItems() {
        return items;
    }

    @JsonIgnore
    @Override
    public Optional<String> getPossibleCpf() {
        return Optional.ofNullable(this.cpf).filter(StringUtils::hasText);
    }
}
