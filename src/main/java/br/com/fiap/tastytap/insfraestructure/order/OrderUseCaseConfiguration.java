package br.com.fiap.tastytap.insfraestructure.order;

import br.com.fiap.tastytap.application.order.create.CreateOrderUseCase;
import br.com.fiap.tastytap.application.order.create.DefaultCreateOrderUseCase;
import br.com.fiap.tastytap.application.order.retrieve.*;
import br.com.fiap.tastytap.application.order.update.DefaultUpdateOrderStatusUseCaseImpl;
import br.com.fiap.tastytap.application.order.update.UpdateOrderStatusUseCase;
import br.com.fiap.tastytap.application.order.update.payment.DefaultUpdateOrderPaymentStatusUseCase;
import br.com.fiap.tastytap.insfraestructure.payment.DefaultPaymentGateway;
import br.com.fiap.tastytap.insfraestructure.product.DefaultProductGateway;
import br.com.fiap.tastytap.insfraestructure.user.DefaultUserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCaseConfiguration {

    private final DefaultOrderGateway defaultOrderGateway;
    private final DefaultProductGateway defaultProductGateway;
    private final DefaultUserClient defaultUserClient;
    private final DefaultPaymentGateway defaultPaymentGateway;

    public OrderUseCaseConfiguration(DefaultOrderGateway defaultOrderGateway,
                                     DefaultProductGateway defaultProductGateway,
                                     DefaultUserClient defaultUserClient,
                                     DefaultPaymentGateway defaultPaymentGateway) {
        this.defaultOrderGateway = defaultOrderGateway;
        this.defaultProductGateway = defaultProductGateway;
        this.defaultUserClient = defaultUserClient;
        this.defaultPaymentGateway = defaultPaymentGateway;
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new DefaultCreateOrderUseCase(defaultOrderGateway, defaultProductGateway, defaultUserClient, defaultPaymentGateway);
    }

    @Bean
    public FindOrdersUseCase findOrdersUseCase() {
        return new DefaultFindOrdersUseCase(defaultOrderGateway);
    }

    @Bean
    public DefaultUpdateOrderPaymentStatusUseCase updateOrderPaymentStatusUseCase() {
        return new DefaultUpdateOrderPaymentStatusUseCase(defaultOrderGateway);
    }

    @Bean
    public GetOrderStatusByNumberUseCase getOrderStatusByNumberUseCase() {
        return new DefaultGetOrderStatusByNumberUseCase(defaultOrderGateway);
    }

    @Bean
    public UpdateOrderStatusUseCase updateOrderStatusUseCase() {
        return new DefaultUpdateOrderStatusUseCaseImpl(defaultOrderGateway);
    }
}
