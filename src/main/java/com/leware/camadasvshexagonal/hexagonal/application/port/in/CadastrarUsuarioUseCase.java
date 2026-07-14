package com.leware.camadasvshexagonal.hexagonal.application.port.in;

import com.leware.camadasvshexagonal.hexagonal.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Porta de entrada — quem aciona esse caso de uso (o controller) depende
// só desta interface, nunca da implementação concreta.
public interface CadastrarUsuarioUseCase {

    User executar(CadastrarUsuarioCommand cmd);

    record CadastrarUsuarioCommand(
            @NotBlank(message = "Nome é obrigatório") String nome,
            @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
            String senha
    ) {
    }
}
