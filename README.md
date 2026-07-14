# camadas-vs-hexagonal

Comparação prática entre arquitetura em camadas e arquitetura hexagonal,
usando o mesmo domínio — cadastro e gestão de usuário — implementado das
duas formas, lado a lado, no mesmo projeto Maven.

Este repositório é o material de apoio de um post técnico sobre arquitetura
de software. O objetivo não é ser uma aplicação de produção — é permitir
comparar a estrutura de código real das duas abordagens.

## Estrutura

```
src/main/java/com/leware/camadasvshexagonal/
├── camadas/       — versão em arquitetura em camadas
│   ├── CamadasApplication.java
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
└── hexagonal/      — versão em arquitetura hexagonal (ports & adapters)
    ├── HexagonalApplication.java
    ├── domain/
    ├── application/
    └── adapter/
```

## Como rodar

Os dois pacotes vivem no mesmo projeto, mas cada um tem seu próprio ponto
de entrada (`@SpringBootApplication`), restrito ao próprio pacote via
`scanBasePackages`. Isso evita colisão de bean entre classes de mesmo nome
(ex: `UserController` existe nos dois lados).

Rode uma versão de cada vez, executando o `main` correspondente:

- **Camadas:** `com.leware.camadasvshexagonal.camadas.CamadasApplication`
- **Hexagonal:** `com.leware.camadasvshexagonal.hexagonal.HexagonalApplication`

Ambas usam H2 em memória — sem configuração externa necessária. Console H2
disponível em `/h2-console` enquanto a aplicação estiver rodando.

## O que comparar

- **Estrutura de pastas** — organização por tipo técnico (camadas) vs. por
  caso de uso (hexagonal).
- **`UserService` (camadas)** — 9 métodos numa única classe, acoplada
  diretamente ao repositório JPA.
- **`CadastrarUsuarioService` (hexagonal)** — um caso de uso, uma classe,
  dependendo só de uma porta (`UserRepositoryPort`) — não sabe se existe
  JPA, banco, ou qualquer detalhe de infraestrutura por trás.

## O que este projeto não é

Não trata de segurança — senha é armazenada como texto simples, de
propósito, comentado em cada ponto do código. O tema da comparação é
organização arquitetural, não práticas de autenticação.
