package br.com.wszd.super_herois.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "superpoderes", schema = "public")
@Data
//@ToString(exclude = "livros")
@EntityListeners(AuditingEntityListener.class)
public class Superpoder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String descricao;

    @Column(nullable = false, length = 50)
    private String superpoder;

    @ManyToMany(mappedBy = "superpoderes")
    private Set<Heroi> herois = new HashSet<>();
}
