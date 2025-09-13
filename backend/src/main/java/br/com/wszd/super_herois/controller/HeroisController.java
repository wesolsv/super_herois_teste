package br.com.wszd.super_herois.controller;

import br.com.wszd.super_herois.controller.dto.HeroiCreateDTO;
import br.com.wszd.super_herois.controller.dto.HeroiResponseDTO;
import br.com.wszd.super_herois.service.HeroiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/herois")
@RequiredArgsConstructor
@Tag(name = "Heróis", description = "Endpoints para gerenciar super-heróis")
public class HeroisController {

    private final HeroiService heroiService;

    @PostMapping
    @Operation(summary = "Cria um novo super-herói",
            description = "Cadastra um super-herói no banco de dados com nome, nome de herói, etc.")
    @ApiResponse(responseCode = "201", description = "Herói criado com sucesso")
    @ApiResponse(responseCode = "409", description = "Conflito: Nome de herói já em uso")
    @ApiResponse(responseCode = "422", description = "Erro de validação dos campos de entrada")
    public ResponseEntity<HeroiResponseDTO> criarHeroi(@RequestBody @Valid HeroiCreateDTO dto) {
        HeroiResponseDTO response = heroiService.criarHeroi(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um super-herói por ID",
            description = "Retorna um super-herói específico pelo seu ID de identificação.")
    @ApiResponse(responseCode = "200", description = "Herói encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Herói não encontrado")
    public ResponseEntity<HeroiResponseDTO> atualizarHeroi(@PathVariable Long id, @RequestBody @Valid HeroiCreateDTO dto) {
        return heroiService.atualizarHeroi(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Lista todos os super-heróis",
            description = "Retorna uma lista de todos os super-heróis cadastrados. A lista pode ser vazia.")
    @ApiResponse(responseCode = "200", description = "Lista de heróis retornada com sucesso")
    public ResponseEntity<List<HeroiResponseDTO>> listarHerois() {
        List<HeroiResponseDTO> herois = heroiService.listarHerois();
        return ResponseEntity.ok(herois);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um super-herói por ID",
            description = "Retorna um super-herói específico pelo seu ID de identificação.")
    @ApiResponse(responseCode = "200", description = "Herói encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Herói não encontrado")
    public ResponseEntity<HeroiResponseDTO> buscarHeroi(@PathVariable Long id) {
        return heroiService.buscarHeroiPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um super-herói por ID",
            description = "Deleta um super-herói do banco de dados com base no seu ID.")
    @ApiResponse(responseCode = "204", description = "Herói excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Herói não encontrado para exclusão")
    public ResponseEntity<Void> deletarHeroi(@PathVariable Long id) {
        heroiService.deletarHeroi(id);
        return ResponseEntity.noContent().build();
    }
}
