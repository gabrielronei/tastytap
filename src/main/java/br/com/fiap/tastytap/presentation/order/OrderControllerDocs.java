package br.com.fiap.tastytap.presentation.order;

import br.com.fiap.tastytap.application.order.create.NewOrderView;
import br.com.fiap.tastytap.application.order.retrieve.OrderStatusView;
import br.com.fiap.tastytap.application.order.retrieve.SimpleOrderView;
import br.com.fiap.tastytap.application.order.update.OrderUpdatedStatusView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos")
interface OrderControllerDocs {

    @Operation(
            summary = "Listagem de pedidos cadastrados",
            description = "Retorna a lista de pedidos cadastrados no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SimpleOrderView.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = {@Content(schema = @Schema())})
    })
    ResponseEntity findAll();

    @Operation(
            summary = "Cria um novo pedido",
            description = "Faz o cadastro de um novo pedido e retorna o pedido em caso de sucesso")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = NewOrderView.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incorretos", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = {@Content(schema = @Schema())})
    })
    ResponseEntity addOrder(@Valid @RequestBody NewOrderForm form);

    @Operation(
            summary = "Atualiza o status do pedido",
            description = "Atualiza na ordem, sempre de RECEBIDO -> PREPARANDO -> PRONTO -> FINALIZADO")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = OrderUpdatedStatusView.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "Você precisa efetuar o pagamento para poder atualizar o status do pedido", content = {@Content(schema = @Schema())})
    })
    ResponseEntity updateStatus(@PathVariable("number") Long number);

    @Operation(
            summary = "Checa o status de um pedido pelo numero do pedido",
            description = "Retorna o status do pedido buscado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = OrderStatusView.class)), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {@Content(schema = @Schema())})
    })
    ResponseEntity checkStatus(@PathVariable("number") Long number);
}
