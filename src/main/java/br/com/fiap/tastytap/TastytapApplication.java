package br.com.fiap.tastytap;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class TastytapApplication {

    public static void main(String[] args) {
        SpringApplication.run(TastytapApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
