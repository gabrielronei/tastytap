package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.SimpleOrderView;
import br.com.fiap.tastytap.application.user.UserGateway;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController implements OrderControllerDocs {

    private final UserGateway userGateway;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(UserGateway userGateway,
                           CreateOrderUseCase createOrderUseCase) {
        this.userGateway = userGateway;
        this.createOrderUseCase = createOrderUseCase;
    }

    @InitBinder("newOrderForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ValidateCustomerIfPresent(userGateway));
    }

    @PostMapping("/order")
    public ResponseEntity addOrder(@Valid @RequestBody NewOrderForm form) {
        Optional<SimpleOrderView> possibleOrder = this.createOrderUseCase.execute(form);

        return possibleOrder.map(order -> ResponseEntity.status(HttpStatus.CREATED).body(order))
                .orElse(ResponseEntity.badRequest().build());
    }
}
