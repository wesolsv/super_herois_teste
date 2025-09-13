package br.com.wszd.super_herois.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record HeroiResponseDTO(
        String nome,
        String nomeHeroi,
        LocalDateTime dataNascimento,
        Double altura,
        Double peso,
        List<String> superpoderes
){}
