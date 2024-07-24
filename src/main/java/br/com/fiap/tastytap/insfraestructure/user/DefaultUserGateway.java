package br.com.fiap.tastytap.insfraestructure.user;

import br.com.fiap.tastytap.application.user.UserGateway;
import br.com.fiap.tastytap.domain.user.CPF;
import br.com.fiap.tastytap.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class DefaultUserGateway implements UserGateway {
    private final UserRepository userRepository;

    public DefaultUserGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByCPF(CPF cpf) {
        return this.userRepository.findByCpf(cpf.getCPFWithoutPonctuation()).map(UserEntity::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = userRepository.save(new UserEntity(user));
        return entity.toDomain();
    }
}
