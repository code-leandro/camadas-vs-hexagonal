package com.leware.camadasvshexagonal.hexagonal.adapter.out.persistence;

import com.leware.camadasvshexagonal.hexagonal.application.port.out.UserRepositoryPort;
import com.leware.camadasvshexagonal.hexagonal.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Implementa a porta de saída. Só esta classe sabe que a persistência
// é feita com JPA — o resto da aplicação não sabe nem precisa saber.
@Component
    public class UserJpaAdapter implements UserRepositoryPort {

    private final UserSpringDataRepository jpaRepository;

    public UserJpaAdapter(UserSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User salvar(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setNome(user.getNome());
        entity.setEmail(user.getEmail());
        entity.setSenha(user.getSenha());
        entity.setAtivo(user.isAtivo());
        entity.setDataCriacao(user.getDataCriacao());

        entity = jpaRepository.save(entity);

        return toDomain(entity);
    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getNome(), entity.getEmail(),
                entity.getSenha(), entity.isAtivo(), entity.getDataCriacao());
    }
}
