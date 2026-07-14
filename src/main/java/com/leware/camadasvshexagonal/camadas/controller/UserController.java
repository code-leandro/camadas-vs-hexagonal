package com.leware.camadasvshexagonal.camadas.controller;

import com.leware.camadasvshexagonal.camadas.dto.UserDTO;
import com.leware.camadasvshexagonal.camadas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserDTO cadastrar(@RequestBody UserDTO dto) {
        return service.cadastrar(dto);
    }

    @PostMapping("/autenticar")
    public UserDTO autenticar(@RequestBody Map<String, String> credenciais) {
        return service.autenticar(credenciais.get("email"), credenciais.get("senha"));
    }

    @PutMapping("/{id}/senha")
    public UserDTO redefinirSenha(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.redefinirSenha(id, body.get("novaSenha"));
    }

    @PutMapping("/{id}")
    public UserDTO atualizarPerfil(@PathVariable Long id, @RequestBody UserDTO dto) {
        return service.atualizarPerfil(id, dto);
    }

    @PostMapping("/{id}/boas-vindas")
    public void enviarEmailBoasVindas(@PathVariable Long id) {
        service.enviarEmailBoasVindas(id);
    }

    @DeleteMapping("/{id}")
    public UserDTO desativarConta(@PathVariable Long id) {
        return service.desativarConta(id);
    }

    @GetMapping("/{id}")
    public UserDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<UserDTO> listarTodos() {
        return service.listarTodos();
    }

    @PutMapping("/{id}/email")
    public UserDTO alterarEmail(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.alterarEmail(id, body.get("novoEmail"));
    }
}
