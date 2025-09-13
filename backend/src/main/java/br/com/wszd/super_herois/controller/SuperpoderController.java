package br.com.wszd.super_herois.controller;

import br.com.wszd.super_herois.controller.dto.SuperpoderDTO;
import br.com.wszd.super_herois.service.SuperpoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superpoderes")
@RequiredArgsConstructor
public class SuperpoderController {

    private final SuperpoderService superpoderService;

    @PostMapping
    public ResponseEntity<SuperpoderDTO> criar(@RequestBody SuperpoderDTO dto) {
        SuperpoderDTO response = superpoderService.criarSuperpoder(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperpoderDTO> atualizar(@PathVariable Long id, @RequestBody SuperpoderDTO dto) {
        SuperpoderDTO atualizado = superpoderService.atualizarSuperpoder(id, dto);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        superpoderService.deletarSuperpoder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SuperpoderDTO>> listar() {
        List<SuperpoderDTO> poderes = superpoderService.listarSuperpoderes();
        return ResponseEntity.ok(poderes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperpoderDTO> buscar(@PathVariable Long id) {
        SuperpoderDTO dto = superpoderService.buscarSuperpoderPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
