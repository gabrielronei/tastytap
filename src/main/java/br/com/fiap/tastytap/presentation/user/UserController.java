package br.com.fiap.tastytap.presentation.user;

import br.com.fiap.tastytap.application.user.SimpleUserView;
import br.com.fiap.tastytap.application.user.create.CreateUserUseCase;
import br.com.fiap.tastytap.application.user.retrieve.FindUserUseCase;
import br.com.fiap.tastytap.utils.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserController implements UserControllerDocs {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UniqueUserValidator uniqueUserValidator;

    public UserController(CreateUserUseCase createUserUseCase, FindUserUseCase findUserUseCase,
                          UniqueUserValidator uniqueUserValidator) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = findUserUseCase;
        this.uniqueUserValidator = uniqueUserValidator;
    }

    @InitBinder("newUserForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(this.uniqueUserValidator);
    }

    @Transactional
    @PostMapping("/user")
    public ResponseEntity save(@Valid @RequestBody NewUserForm form) {

        Optional<SimpleUserView> possibleView = createUserUseCase.execute(form);

        return possibleView.map(view -> ResponseEntity.status(HttpStatus.CREATED).body(view))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/user/{cpf}")
    public ResponseEntity findBy(@PathVariable String cpf) {
        if (!ValidationUtils.isValidCpf(cpf)) {
            return ResponseEntity.badRequest().body(Map.of("message", "CPF invalido!"));
        }

        return findUserUseCase.execute(cpf).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
