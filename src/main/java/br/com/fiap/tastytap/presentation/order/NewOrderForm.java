package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.NewOrderCommand;
import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewOrderForm(List<NewItemOrderForm> items) {
        this.items = items;
    }

    @Nullable
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@Nullable String cpf) {
        this.cpf = cpf;
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
