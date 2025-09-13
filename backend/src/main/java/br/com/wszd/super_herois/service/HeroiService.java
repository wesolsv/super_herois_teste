package br.com.wszd.super_herois.service;

import br.com.wszd.super_herois.controller.dto.HeroiCreateDTO;
import br.com.wszd.super_herois.controller.dto.HeroiResponseDTO;
import br.com.wszd.super_herois.controller.mappers.HeroiMapper;
import br.com.wszd.super_herois.model.Heroi;
import br.com.wszd.super_herois.repository.HeroiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HeroiService {

    private final HeroiRepository heroiRepository;
    private final HeroiMapper heroiMapper;

    @Transactional
    public HeroiResponseDTO criarHeroi(HeroiCreateDTO dto) {
        Heroi heroi = heroiMapper.toEntity(dto);
        Heroi heroiSalvo = heroiRepository.save(heroi);
        return heroiMapper.toResponseDTO(heroiSalvo);
    }

    @Transactional
    public HeroiResponseDTO atualizarHeroi(Long id, HeroiCreateDTO dto) {
        return heroiRepository.findById(id)
                .map(existing -> {
                    heroiMapper.atualizarEntity(existing, dto);
                    Heroi heroiSalvo = heroiRepository.save(existing);
                    return heroiMapper.toResponseDTO(heroiSalvo);
                })
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<HeroiResponseDTO> listarHerois() {
        List<Heroi> herois = heroiRepository.findAllWithSuperpoderes();

        herois.forEach(heroi -> {
            if (heroi.getSuperpoderes() != null) {
                heroi.getSuperpoderes().size();
            }
        });

        return herois.stream()
                .map(heroiMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public HeroiResponseDTO buscarHeroiPorId(Long id) {
        return heroiRepository.findByIdWithSuperpoderes(id)
                .map(heroi -> {
                    if (heroi.getSuperpoderes() != null) {
                        heroi.getSuperpoderes().size();
                    }
                    return heroiMapper.toResponseDTO(heroi);
                })
                .orElse(null);
    }

    @Transactional
    public void deletarHeroi(Long id) {
        heroiRepository.deleteById(id);
    }
}
