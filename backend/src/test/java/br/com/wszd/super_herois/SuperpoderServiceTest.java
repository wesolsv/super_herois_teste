package br.com.wszd.super_herois;

import br.com.wszd.super_herois.controller.dto.SuperpoderDTO;
import br.com.wszd.super_herois.controller.mappers.SuperpoderMapper;
import br.com.wszd.super_herois.model.Superpoder;
import br.com.wszd.super_herois.repository.SuperpoderRepository;
import br.com.wszd.super_herois.service.SuperpoderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para SuperpoderService")
public class SuperpoderServiceTest {

    @Mock
    private SuperpoderRepository superpoderRepository;

    @Mock
    private SuperpoderMapper superpoderMapper;

    @InjectMocks
    private SuperpoderService superpoderService;

    private SuperpoderDTO superpoderDTO;
    private Superpoder superpoder;

    @BeforeEach
    void setUp() {
        superpoderDTO = new SuperpoderDTO(1L, "Força sobre-humana", "Super Força");
        superpoder = new Superpoder();
        superpoder.setId(1L);
        superpoder.setDescricao("Força sobre-humana");
        superpoder.setSuperpoder("Super Força");
    }

    @Test
    @DisplayName("Deve criar um superpoder com sucesso")
    void deveCriarSuperpoderComSucesso() {
        // Arrange
        when(superpoderMapper.toEntity(any(SuperpoderDTO.class))).thenReturn(superpoder);
        when(superpoderRepository.save(any(Superpoder.class))).thenReturn(superpoder);
        when(superpoderMapper.toDTO(any(Superpoder.class))).thenReturn(superpoderDTO);

        SuperpoderDTO result = superpoderService.criarSuperpoder(superpoderDTO);

        assertNotNull(result);
        assertEquals(superpoderDTO.superpoder(), result.superpoder());
        verify(superpoderRepository, times(1)).save(any(Superpoder.class));
    }

    @Test
    @DisplayName("Deve deletar um superpoder com sucesso")
    void deveDeletarSuperpoderComSucesso() {
        doNothing().when(superpoderRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> superpoderService.deletarSuperpoder(1L));
        verify(superpoderRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve listar todos os superpoderes com sucesso")
    void deveListarSuperpoderesComSucesso() {
        // Arrange
        List<Superpoder> listaEntidades = List.of(superpoder);
        when(superpoderRepository.findAll()).thenReturn(listaEntidades);
        when(superpoderMapper.toDTO(any(Superpoder.class))).thenReturn(superpoderDTO);

        List<SuperpoderDTO> resultado = superpoderService.listarSuperpoderes();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(superpoderRepository, times(1)).findAll();
    }
}
