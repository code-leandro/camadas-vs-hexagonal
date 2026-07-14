package com.leware.camadasvshexagonal.camadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// scanBasePackages restrito a este pacote — evita colisão de bean com o
// pacote hexagonal (que tem classes de mesmo nome, ex: UserController)
@SpringBootApplication(scanBasePackages = "com.leware.camadasvshexagonal.camadas")
public class CamadasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamadasApplication.class, args);
    }
}
