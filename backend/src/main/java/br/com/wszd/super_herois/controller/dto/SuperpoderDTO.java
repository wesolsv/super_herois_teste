package br.com.wszd.super_herois.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SuperpoderDTO(
        Long id,

        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 250, message = "campo fora do tamanho padrão")
        String descricao,

        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String superpoder
) {}
