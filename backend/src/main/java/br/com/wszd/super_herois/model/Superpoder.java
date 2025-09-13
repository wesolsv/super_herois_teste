package br.com.wszd.super_herois.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "superpoderes", schema = "public")
@Data
@ToString(exclude = "herois")
@EntityListeners(AuditingEntityListener.class)
public class Superpoder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String descricao;

    @Column(nullable = false, length = 50)
    private String superpoder;

    @ManyToMany(mappedBy = "superpoderes", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Schema(hidden = true)
    private List<Heroi> herois = new ArrayList<>();
}
