package com.leware.camadasvshexagonal.hexagonal.application.port.out;

import com.leware.camadasvshexagonal.hexagonal.domain.User;

import java.util.Optional;

// Porta de saída — o caso de uso depende só desta interface, nunca de
// uma implementação concreta de persistência.
public interface UserRepositoryPort {

    User salvar(User user);

    Optional<User> buscarPorEmail(String email);

    boolean existePorEmail(String email);
}
