package br.com.fiap.tastytap.application.order.create;

import java.util.List;
import java.util.Optional;

public interface NewOrderCommand {

    Optional<String> getPossibleCpf();

    List<? extends NewItemOrderCommand> getItems();
}
