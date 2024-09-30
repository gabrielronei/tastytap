package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.NewOrderView;
import br.com.fiap.tastytap.application.order.retrieve.FindOrdersUseCase;
import br.com.fiap.tastytap.application.order.retrieve.GetOrderStatusByNumberUseCase;
import br.com.fiap.tastytap.application.order.update.UpdateOrderStatusUseCase;
import br.com.fiap.tastytap.application.product.ProductGateway;
import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.insfraestructure.security.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController implements OrderControllerDocs {

    private final ProductGateway productGateway;
    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrdersUseCase findOrdersUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final GetOrderStatusByNumberUseCase getOrderStatusByNumberUseCase;
    private final CurrentUser currentUser;

    public OrderController(ProductGateway productGateway,
                           CreateOrderUseCase createOrderUseCase,
                           FindOrdersUseCase findOrdersUseCase,
                           UpdateOrderStatusUseCase updateOrderStatusUseCase,
                           GetOrderStatusByNumberUseCase getOrderStatusByNumberUseCase, CurrentUser currentUser) {
        this.productGateway = productGateway;
        this.createOrderUseCase = createOrderUseCase;
        this.findOrdersUseCase = findOrdersUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.getOrderStatusByNumberUseCase = getOrderStatusByNumberUseCase;
        this.currentUser = currentUser;
    }

    @InitBinder("newOrderForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ValidateCustomerIfPresent(currentUser),
                new ProductsValidator(productGateway));
    }

    @GetMapping("/order")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(findOrdersUseCase.execute());
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@Valid @RequestBody NewOrderForm form) {
        currentUser.getPossibleUser().ifPresent(user -> form.setCpf(user.getCpf()));
        Optional<NewOrderView> possibleOrder = this.createOrderUseCase.execute(form);

        return possibleOrder.map(order -> ResponseEntity.status(HttpStatus.CREATED).body(order))
                .orElse(ResponseEntity.badRequest().build());
    }

    @Transactional
    @PutMapping("/order/{number}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("number") Long number) {
        return this.updateOrderStatusUseCase.execute(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{number}/status")
    public ResponseEntity<?> checkStatus(@PathVariable("number") Long number) {
        return this.getOrderStatusByNumberUseCase.execute(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
