package br.com.fiap.tastytap.application.order.retrieve;

import br.com.fiap.tastytap.application.UseCase;

import java.util.Optional;

public abstract class GetOrderStatusByNumber extends UseCase<Long, Optional<OrderStatusView>> {
}
