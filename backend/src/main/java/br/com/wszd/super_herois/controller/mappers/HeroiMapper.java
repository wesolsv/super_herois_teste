package br.com.wszd.super_herois.controller.mappers;

import br.com.wszd.super_herois.controller.dto.HeroiDTO;
import br.com.wszd.super_herois.model.Heroi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeroiMapper {

    Heroi toEntity(HeroiDTO dto);

    HeroiDTO toDTO(Heroi heroi);
}
