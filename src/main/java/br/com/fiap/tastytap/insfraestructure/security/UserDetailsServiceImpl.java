package br.com.fiap.tastytap.insfraestructure.security;

import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.domain.user.CPF;
import br.com.fiap.tastytap.insfraestructure.user.UserEntity;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserGateway userGateway;

    public UserDetailsServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userGateway.findByCPF(new CPF(username)).map(UserEntity::new)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UserDetailsImpl(user);
    }

}