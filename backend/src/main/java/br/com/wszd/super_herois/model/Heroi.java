package br.com.wszd.super_herois.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "herois", schema = "public")
@Data
@ToString(exclude = "superpoderes")
@EntityListeners(AuditingEntityListener.class)
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(name = "nome_heroi", nullable = false, unique = true, length = 120)
    private String nomeHeroi;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDateTime dataNascimento;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private Double peso;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "herois_superpoderes",
            joinColumns = @JoinColumn(name = "heroi_id"),
            inverseJoinColumns = @JoinColumn(name = "superpoder_id")
    )
    private List<Superpoder> superpoderes = new ArrayList<>();
}
