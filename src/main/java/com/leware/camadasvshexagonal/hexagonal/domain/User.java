package com.leware.camadasvshexagonal.hexagonal.domain;

import java.time.LocalDateTime;

// Objeto de domínio puro — não conhece JPA, Spring, nem nenhum detalhe
// de infraestrutura. É esse desconhecimento que a arquitetura hexagonal
// garante estruturalmente (via portas), não por convenção do time.
public class User {

    private final Long id;
    private final String nome;
    private final String email;
    // simplificado propositalmente — segurança não é o tema deste projeto
    private final String senha;
    private final boolean ativo;
    private final LocalDateTime dataCriacao;

    public User(String nome, String email, String senha) {
        this(null, nome, email, senha, true, LocalDateTime.now());
    }

    public User(Long id, String nome, String email, String senha, boolean ativo, LocalDateTime dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
