package com.leware.camadasvshexagonal.hexagonal.adapter.in.web;

import com.leware.camadasvshexagonal.hexagonal.application.port.in.CadastrarUsuarioUseCase;
import com.leware.camadasvshexagonal.hexagonal.application.port.in.CadastrarUsuarioUseCase.CadastrarUsuarioCommand;
import com.leware.camadasvshexagonal.hexagonal.domain.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Aciona a porta de entrada — não implementa nada, só traduz HTTP em
// uma chamada ao caso de uso.
@RestController
@RequestMapping("/api/hexagonal/usuarios")
public class UserController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public UserController(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    @PostMapping
    public User cadastrar(@Valid @RequestBody CadastrarUsuarioCommand cmd) {
        return cadastrarUsuarioUseCase.executar(cmd);
    }
}
