package br.com.wszd.super_herois.controller.mappers;

import br.com.wszd.super_herois.controller.dto.SuperpoderDTO;
import br.com.wszd.super_herois.model.Superpoder;
import org.springframework.stereotype.Component;

@Component
public class SuperpoderMapper {

    public Superpoder toEntity(SuperpoderDTO dto) {
        if (dto == null) {
            return null;
        }

        Superpoder entity = new Superpoder();
        entity.setId(dto.id());
        entity.setSuperpoder(dto.superpoder());
        entity.setDescricao(dto.descricao());

        return entity;
    }

    public SuperpoderDTO toDTO(Superpoder entity) {
        if (entity == null) {
            return null;
        }

        return new SuperpoderDTO(
                entity.getId(),
                entity.getSuperpoder(),
                entity.getDescricao()
        );
    }

    public void atualizarEntity(Superpoder existing, SuperpoderDTO dto) {
        if (dto == null || existing == null) {
            return;
        }

        existing.setSuperpoder(dto.superpoder());
        existing.setDescricao(dto.descricao());
    }
}
