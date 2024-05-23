package br.com.fiap.tastytap.presentation.user;

import br.com.fiap.tastytap.application.user.SimpleUserView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "API de gerenciamento de usuarios")
interface UserControllerDocs {

    @Operation(
            summary = "Cadastro de Usuario",
            description = "Realiza o cadastro de um novo usuario com os dados fornecidos e retorna o usuario cadastrado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = SimpleUserView.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incorretos", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do sistema", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity save(@Valid @RequestBody NewUserForm form);

    @Operation(
            summary = "Pesquisa um usuario pelo CPF",
            description = "Retorna um usuario pelo CPF, se for encontrado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = SimpleUserView.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "O numero fornecido não é reconhecido como um cpf", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "O CPF do usuario fornecido não foi encontrado", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity findBy(@PathVariable String cpf);
}
