package br.com.wszd.super_herois.repository;

import br.com.wszd.super_herois.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {
}
