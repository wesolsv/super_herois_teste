package br.com.wszd.super_herois.controller;

import br.com.wszd.super_herois.controller.dto.SuperpoderDTO;
import br.com.wszd.super_herois.service.SuperpoderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superpoderes")
@RequiredArgsConstructor
@Tag(name = "Superpoderes", description = "Endpoints para gerenciar superpoderes")
public class SuperpoderController {

    private final SuperpoderService superpoderService;

    @PostMapping
    @Operation(summary = "Cria um novo superpoder",
            description = "Adiciona um novo superpoder ao banco de dados.")
    @ApiResponse(responseCode = "200", description = "Superpoder criado com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação dos campos de entrada")
    public ResponseEntity<SuperpoderDTO> criar(@RequestBody SuperpoderDTO dto) {
        SuperpoderDTO response = superpoderService.criarSuperpoder(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um superpoder por ID",
            description = "Atualiza a descrição de um superpoder existente com base no ID.")
    @ApiResponse(responseCode = "200", description = "Superpoder atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Superpoder não encontrado para atualização")
    public ResponseEntity<SuperpoderDTO> atualizar(@PathVariable Long id, @RequestBody SuperpoderDTO dto) {
        SuperpoderDTO atualizado = superpoderService.atualizarSuperpoder(id, dto);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um superpoder por ID",
            description = "Deleta um superpoder do banco de dados com base no seu ID.")
    @ApiResponse(responseCode = "204", description = "Superpoder excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Superpoder não encontrado para exclusão")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        superpoderService.deletarSuperpoder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lista todos os superpoderes",
            description = "Retorna uma lista de todos os superpoderes cadastrados. A lista pode ser vazia.")
    @ApiResponse(responseCode = "200", description = "Lista de superpoderes retornada com sucesso")
    public ResponseEntity<List<SuperpoderDTO>> listar() {
        List<SuperpoderDTO> poderes = superpoderService.listarSuperpoderes();
        return ResponseEntity.ok(poderes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um superpoder por ID",
            description = "Retorna um superpoder específico pelo seu ID de identificação.")
    @ApiResponse(responseCode = "200", description = "Superpoder encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Superpoder não encontrado")
    public ResponseEntity<SuperpoderDTO> buscar(@PathVariable Long id) {
        SuperpoderDTO dto = superpoderService.buscarSuperpoderPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
