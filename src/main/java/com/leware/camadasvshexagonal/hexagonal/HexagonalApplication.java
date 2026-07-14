package com.leware.camadasvshexagonal.hexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// scanBasePackages restrito a este pacote — evita colisão de bean com o
// pacote camadas (que tem classes de mesmo nome, ex: UserController)
@SpringBootApplication(scanBasePackages = "com.leware.camadasvshexagonal.hexagonal")
public class HexagonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalApplication.class, args);
    }
}
