package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.NewOrderView;
import br.com.fiap.tastytap.application.order.retrieve.FindOrdersUseCase;
import br.com.fiap.tastytap.application.order.retrieve.GetOrderStatusByNumberUseCase;
import br.com.fiap.tastytap.application.order.update.UpdateOrderStatusUseCase;
import br.com.fiap.tastytap.application.product.ProductGateway;
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

    public OrderController(ProductGateway productGateway,
                           CreateOrderUseCase createOrderUseCase,
                           FindOrdersUseCase findOrdersUseCase,
                           UpdateOrderStatusUseCase updateOrderStatusUseCase,
                           GetOrderStatusByNumberUseCase getOrderStatusByNumberUseCase) {
        this.productGateway = productGateway;
        this.createOrderUseCase = createOrderUseCase;
        this.findOrdersUseCase = findOrdersUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.getOrderStatusByNumberUseCase = getOrderStatusByNumberUseCase;
    }

    @InitBinder("newOrderForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ValidateCustomerIfPresent(),
                new ProductsValidator(productGateway));
    }

    @GetMapping("/order")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(findOrdersUseCase.execute());
    }

    @Transactional
    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@Valid @RequestBody NewOrderForm form) {
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
