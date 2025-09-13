package br.com.wszd.super_herois.repository;

import br.com.wszd.super_herois.model.Heroi;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    @Query("SELECT DISTINCT h FROM Heroi h LEFT JOIN FETCH h.superpoderes")
    List<Heroi> findAllWithSuperpoderes();

    @Query("SELECT h FROM Heroi h LEFT JOIN FETCH h.superpoderes WHERE h.id = :id")
    Optional<Heroi> findByIdWithSuperpoderes(Long id);

    Optional<Heroi> findByNomeHeroi(String nomeHeroi);
}
