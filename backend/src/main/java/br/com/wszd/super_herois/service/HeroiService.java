package br.com.wszd.super_herois.service;

import br.com.wszd.super_herois.controller.dto.HeroiCreateDTO;
import br.com.wszd.super_herois.controller.dto.HeroiResponseDTO;
import br.com.wszd.super_herois.controller.mappers.HeroiMapper;
import br.com.wszd.super_herois.exceptions.OperacaoNaoPermitidaException;
import br.com.wszd.super_herois.exceptions.RegistroDuplicadoException;
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
        if (heroiRepository.findByNomeHeroi(dto.nomeHeroi()).isPresent()) {
            throw new RegistroDuplicadoException("O nome de herói '" + dto.nomeHeroi() + "' já está em uso.");
        }

        Heroi heroi = heroiMapper.toEntity(dto);

        Heroi heroiSalvo = heroiRepository.save(heroi);

        return heroiMapper.toResponseDTO(heroiSalvo);
    }

    @Transactional
    public Optional<HeroiResponseDTO> atualizarHeroi(Long id, HeroiCreateDTO dto) {
        Heroi heroiExistente = heroiRepository.findById(id)
                .orElseThrow(() -> new OperacaoNaoPermitidaException("Herói com ID " + id + " não encontrado."));

        if (!heroiExistente.getNomeHeroi().equals(dto.nomeHeroi())) {
            boolean nomeHeroiJaExiste = heroiRepository.findByNomeHeroi(dto.nomeHeroi()).isPresent();
            if (nomeHeroiJaExiste) {
                throw new RegistroDuplicadoException("O nome de herói '" + dto.nomeHeroi() + "' já está em uso.");
            }
        }

        heroiMapper.atualizarEntity(heroiExistente, dto);
        Heroi heroiSalvo = heroiRepository.save(heroiExistente);

        return Optional.ofNullable(heroiMapper.toResponseDTO(heroiSalvo));
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
    public Optional<HeroiResponseDTO> buscarHeroiPorId(Long id) {
        Heroi heroi = heroiRepository.findByIdWithSuperpoderes(id)
                .orElseThrow(() -> new OperacaoNaoPermitidaException("Herói com ID " + id + " não encontrado."));

        if (heroi.getSuperpoderes() != null) {
            heroi.getSuperpoderes().size();
        }
        return Optional.ofNullable(heroiMapper.toResponseDTO(heroi));
    }

    @Transactional
    public void deletarHeroi(Long id) {
        if (!heroiRepository.existsById(id)) {
            throw new OperacaoNaoPermitidaException("Herói com ID " + id + " não encontrado.");
        }
        heroiRepository.deleteById(id);
    }

    public Optional<Heroi> obterPorId(Long id){
        return heroiRepository.findById(id);
    }
}
