package br.com.wszd.super_herois.controller;

import br.com.wszd.super_herois.controller.dto.HeroiCreateDTO;
import br.com.wszd.super_herois.controller.dto.HeroiResponseDTO;
import br.com.wszd.super_herois.service.HeroiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/herois")
@RequiredArgsConstructor
public class HeroisController {

    private final HeroiService heroiService;

    @PostMapping
    public ResponseEntity<HeroiResponseDTO> criarHeroi(@RequestBody @Valid HeroiCreateDTO dto) {
        HeroiResponseDTO response = heroiService.criarHeroi(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> atualizarHeroi(@PathVariable Long id, @RequestBody @Valid HeroiCreateDTO dto) {
        return heroiService.atualizarHeroi(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<HeroiResponseDTO>> listarHerois() {
        List<HeroiResponseDTO> herois = heroiService.listarHerois();
        return ResponseEntity.ok(herois);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> buscarHeroi(@PathVariable Long id) {
        return heroiService.buscarHeroiPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHeroi(@PathVariable Long id) {
        heroiService.deletarHeroi(id);
        return ResponseEntity.noContent().build();
    }
}
