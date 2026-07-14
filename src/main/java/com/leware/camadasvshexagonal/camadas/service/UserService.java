package com.leware.camadasvshexagonal.camadas.service;

import com.leware.camadasvshexagonal.camadas.dto.UserDTO;
import com.leware.camadasvshexagonal.camadas.entity.UserEntity;
import com.leware.camadasvshexagonal.camadas.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Acoplamento direto ao repositório é intencional — é o ponto que este
// projeto demonstra (arquitetura em camadas não cria mecanismo que
// desestimule isso).
@Service
public class UserService {

    @Autowired
    private UserJpaRepository repository;

    public UserDTO cadastrar(UserDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalStateException("Email já cadastrado");
        }

        UserEntity entity = new UserEntity();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        // simplificado propositalmente — segurança não é o tema deste projeto
        entity.setSenha(dto.getSenha());
        entity.setAtivo(true);

        entity = repository.save(entity);
        enviarEmailBoasVindas(entity.getId());

        return toDto(entity);
    }

    public UserDTO autenticar(String email, String senha) {
        UserEntity entity = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!entity.isAtivo()) {
            throw new IllegalStateException("Conta inativa");
        }
        // simplificado propositalmente — comparação direta de String,
        // sem hash — segurança não é o tema deste projeto
        if (!entity.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha inválida");
        }

        return toDto(entity);
    }

    public UserDTO redefinirSenha(Long id, String novaSenha) {
        UserEntity entity = buscarEntidade(id);
        entity.setSenha(novaSenha);
        entity = repository.save(entity);
        return toDto(entity);
    }

    public UserDTO atualizarPerfil(Long id, UserDTO dto) {
        UserEntity entity = buscarEntidade(id);
        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            entity.setNome(dto.getNome());
        }
        entity = repository.save(entity);
        return toDto(entity);
    }

    public void enviarEmailBoasVindas(Long id) {
        UserEntity entity = buscarEntidade(id);
        // simula envio — sem integração real de e-mail
        System.out.println("[EMAIL] Enviando boas-vindas para " + entity.getEmail());
    }

    public UserDTO desativarConta(Long id) {
        UserEntity entity = buscarEntidade(id);
        entity.setAtivo(false);
        entity = repository.save(entity);
        return toDto(entity);
    }

    public UserDTO buscarPorId(Long id) {
        return toDto(buscarEntidade(id));
    }

    public List<UserDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO alterarEmail(Long id, String novoEmail) {
        if (novoEmail == null || !novoEmail.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (repository.existsByEmail(novoEmail)) {
            throw new IllegalStateException("Email já cadastrado");
        }
        UserEntity entity = buscarEntidade(id);
        entity.setEmail(novoEmail);
        entity = repository.save(entity);
        return toDto(entity);
    }

    private UserEntity buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    private UserDTO toDto(UserEntity entity) {
        return new UserDTO(entity.getId(), entity.getNome(), entity.getEmail(),
                entity.isAtivo(), entity.getDataCriacao());
    }
}
