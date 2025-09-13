package br.com.wszd.super_herois.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "API de Super-Heróis",
        version = "1.0",
        description = "API para gerenciar super-heróis e seus superpoderes. " +
                "Criar, ler, atualizar e deletar heróis e poderes."
))
public class OpenApiConfig {
}
