package com.leware.camadasvshexagonal.hexagonal.adapter.in.messaging;

import com.leware.camadasvshexagonal.hexagonal.application.port.in.CadastrarUsuarioUseCase;

// Adapter de entrada alternativo — mesma porta (CadastrarUsuarioUseCase)
// acionada por HTTP no UserController, agora acionada por mensageria.
// Não implementado nesta demonstração — o objetivo é mostrar que a porta
// de entrada não sabe (nem precisa saber) quem está do outro lado.
public class CadastroUsuarioListener {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public CadastroUsuarioListener(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    // @KafkaListener(topics = "usuarios.cadastro") — ou RabbitMQ, SQS, etc.
    public void aoReceberMensagem(/* payload da mensagem */) {
        // traduz o payload em CadastrarUsuarioCommand e chama:
        // cadastrarUsuarioUseCase.executar(cmd);
    }
}