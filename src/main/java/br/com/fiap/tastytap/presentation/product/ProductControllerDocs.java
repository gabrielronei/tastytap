package br.com.fiap.tastytap.presentation.product;

import br.com.fiap.tastytap.application.product.SimpleProductView;
import br.com.fiap.tastytap.presentation.product.create.NewProductForm;
import br.com.fiap.tastytap.presentation.product.update.UpdateProductForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Product", description = "API de gerenciamento de produtos")
interface ProductControllerDocs {

    @Operation(
            summary = "Cadastra um novo produto",
            description = "Faz o cadastro de uma novo produto e retorna o produto em caso de sucesso")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = SimpleProductView.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incorretos", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = {@Content(schema = @Schema())})
    })
    ResponseEntity save(@Valid @RequestBody NewProductForm form);

    @Operation(
            summary = "Altera um produto existente",
            description = "Altera um produto já cadastrado no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = SimpleProductView.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incorretos", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = {@Content(schema = @Schema())})
    })
    ResponseEntity update(@Valid @RequestBody UpdateProductForm form);

    @Operation(
            summary = "Exclui um produto existente",
            description = "Exclui um produto cadastrado no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto excluído com sucesso", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", description = "O Id do produto fornecido não foi encontrado", content = {@Content(schema = @Schema())}),
    })
    ResponseEntity deleteById(@RequestParam Long id);
}
