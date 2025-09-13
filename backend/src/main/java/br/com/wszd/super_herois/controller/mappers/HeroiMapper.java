package br.com.wszd.super_herois.controller.mappers;

import br.com.wszd.super_herois.controller.dto.HeroiCreateDTO;
import br.com.wszd.super_herois.controller.dto.HeroiResponseDTO;
import br.com.wszd.super_herois.model.Heroi;
import br.com.wszd.super_herois.model.Superpoder;
import br.com.wszd.super_herois.repository.SuperpoderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HeroiMapper {

    private final SuperpoderRepository superPoderRepository;

    public Heroi toEntity(HeroiCreateDTO dto) {
        Heroi heroi = new Heroi();
        heroi.setNome(dto.nome());
        heroi.setNomeHeroi(dto.nomeHeroi());
        heroi.setDataNascimento(dto.dataNascimento());
        heroi.setAltura(dto.altura());
        heroi.setPeso(dto.peso());

        if (dto.superpoderIds() != null && !dto.superpoderIds().isEmpty()) {
            List<Superpoder> superPoderes = superPoderRepository.findAllById(dto.superpoderIds());
            heroi.setSuperpoderes(superPoderes);
        }

        return heroi;
    }

    public HeroiResponseDTO toResponseDTO(Heroi heroi) {
        List<String> nomesSuperpoderes = heroi.getSuperpoderes() != null ?
                heroi.getSuperpoderes().stream()
                        .map(Superpoder::getSuperpoder)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        return new HeroiResponseDTO(
                heroi.getNome(),
                heroi.getNomeHeroi(),
                heroi.getDataNascimento(),
                heroi.getAltura(),
                heroi.getPeso(),
                nomesSuperpoderes
        );
    }

    public void atualizarEntity(Heroi existing, HeroiCreateDTO dto) {
        existing.setNome(dto.nome());
        existing.setNomeHeroi(dto.nomeHeroi());
        existing.setDataNascimento(dto.dataNascimento());
        existing.setAltura(dto.altura());
        existing.setPeso(dto.peso());

        if (dto.superpoderIds() != null) {
            List<Superpoder> poderes = new ArrayList<>(superPoderRepository.findAllById(dto.superpoderIds()));
            existing.setSuperpoderes(poderes);
        }
    }
}
