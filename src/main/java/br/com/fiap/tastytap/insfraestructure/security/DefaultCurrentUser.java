package br.com.fiap.tastytap.insfraestructure.security;

import br.com.fiap.tastytap.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class DefaultCurrentUser implements CurrentUser {


    @Override
    public Optional<User> getPossibleUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return Optional.empty();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return Optional.ofNullable(userDetails.unwrap());
        }
        return Optional.empty();
    }
}
