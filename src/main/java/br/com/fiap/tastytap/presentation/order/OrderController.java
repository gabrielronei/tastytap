package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.NewOrderView;
import br.com.fiap.tastytap.application.order.retrieve.FindOrdersUseCase;
import br.com.fiap.tastytap.application.order.retrieve.GetOrderStatusByNumberUseCase;
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
    private final FindOrdersUseCase findOrdersUseCase;
    private final GetOrderStatusByNumberUseCase getOrderStatusByNumberUseCase;

    public OrderController(UserGateway userGateway,
                           CreateOrderUseCase createOrderUseCase,
                           FindOrdersUseCase findOrdersUseCase,
                           GetOrderStatusByNumberUseCase getOrderStatusByNumberUseCase) {
        this.userGateway = userGateway;
        this.createOrderUseCase = createOrderUseCase;
        this.findOrdersUseCase = findOrdersUseCase;
        this.getOrderStatusByNumberUseCase = getOrderStatusByNumberUseCase;
    }

    @InitBinder("newOrderForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ValidateCustomerIfPresent(userGateway));
    }

    @GetMapping("/order")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(findOrdersUseCase.execute());
    }

    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@Valid @RequestBody NewOrderForm form) {
        Optional<NewOrderView> possibleOrder = this.createOrderUseCase.execute(form);

        return possibleOrder.map(order -> ResponseEntity.status(HttpStatus.CREATED).body(order))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/order/{number}/status")
    public ResponseEntity<?> checkStatus(@PathVariable("number") Long number) {
        return this.getOrderStatusByNumberUseCase.execute(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
