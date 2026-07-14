package com.leware.camadasvshexagonal.hexagonal.application.service;

import com.leware.camadasvshexagonal.hexagonal.application.port.in.CadastrarUsuarioUseCase;
import com.leware.camadasvshexagonal.hexagonal.application.port.out.UserRepositoryPort;
import com.leware.camadasvshexagonal.hexagonal.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService implements CadastrarUsuarioUseCase {

    private final UserRepositoryPort repository;

    public CadastrarUsuarioService(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public User executar(CadastrarUsuarioCommand cmd) {
        if (repository.existePorEmail(cmd.email())) {
            throw new IllegalStateException("Email já cadastrado");
        }

        // simplificado propositalmente — segurança não é o tema deste projeto
        User user = new User(cmd.nome(), cmd.email(), cmd.senha());
        return repository.salvar(user);
    }
}
