package br.com.wszd.super_herois.service;

import br.com.wszd.super_herois.controller.dto.SuperpoderDTO;
import br.com.wszd.super_herois.controller.mappers.SuperpoderMapper;
import br.com.wszd.super_herois.model.Superpoder;
import br.com.wszd.super_herois.repository.SuperpoderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperpoderService {

    private final SuperpoderRepository superpoderRepository;
    private final SuperpoderMapper superpoderMapper;

    public SuperpoderDTO criarSuperpoder(SuperpoderDTO dto) {
        Superpoder entity = superpoderMapper.toEntity(dto);
        entity = superpoderRepository.save(entity);
        return superpoderMapper.toDTO(entity);
    }

    public SuperpoderDTO atualizarSuperpoder(Long id, SuperpoderDTO dto) {
        return superpoderRepository.findById(id)
                .map(existing -> {
                    Superpoder atualizado = superpoderMapper.toEntity(dto);
                    atualizado.setId(id);
                    atualizado = superpoderRepository.save(atualizado);
                    return superpoderMapper.toDTO(atualizado);
                })
                .orElse(null); // ou lançar exceção personalizada
    }

    public void deletarSuperpoder(Long id) {
        superpoderRepository.deleteById(id);
    }

    public List<SuperpoderDTO> listarSuperpoderes() {
        return superpoderRepository.findAll()
                .stream()
                .map(superpoderMapper::toDTO)
                .toList();
    }

    public SuperpoderDTO buscarSuperpoderPorId(Long id) {
        return superpoderRepository.findById(id)
                .map(superpoderMapper::toDTO)
                .orElse(null);
    }
}
