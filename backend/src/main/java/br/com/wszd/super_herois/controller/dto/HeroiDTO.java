package br.com.wszd.super_herois.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record HeroiDTO (
        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 120, message = "campo fora do tamanho padrao")
        String nome,
        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 120, message = "campo fora do tamanho padrao")
        String nomeHeroi,
        @NotNull(message = "campo obrigatório")
        @Past(message = "não pode ser uma data futura")
        LocalDateTime dataNascimento,
        @NotNull(message = "campo obrigatório")
        Double altura,
        @NotNull(message = "campo obrigatório")
        Double peso){
}
