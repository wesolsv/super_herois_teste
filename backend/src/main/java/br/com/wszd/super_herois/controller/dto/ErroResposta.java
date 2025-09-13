package br.com.wszd.super_herois.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int Status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
