package br.com.wszd.super_herois;

import br.com.wszd.super_herois.controller.dto.HeroiCreateDTO;
import br.com.wszd.super_herois.controller.dto.HeroiResponseDTO;
import br.com.wszd.super_herois.controller.mappers.HeroiMapper;
import br.com.wszd.super_herois.exceptions.OperacaoNaoPermitidaException;
import br.com.wszd.super_herois.exceptions.RegistroDuplicadoException;
import br.com.wszd.super_herois.model.Heroi;
import br.com.wszd.super_herois.repository.HeroiRepository;
import br.com.wszd.super_herois.service.HeroiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para HeroiService")
public class HeroiServiceTest {

    @Mock
    private HeroiRepository heroiRepository;

    @Mock
    private HeroiMapper heroiMapper;

    @InjectMocks
    private HeroiService heroiService;

    private HeroiCreateDTO heroiCreateDTO;
    private Heroi heroi;
    private HeroiResponseDTO heroiResponseDTO;

    @BeforeEach
    void setUp() {
        heroiCreateDTO = new HeroiCreateDTO(
                "Clark Kent", "Superman", LocalDateTime.now(), 1.91, 107.0, null
        );

        heroi = new Heroi();
        heroi.setId(1L);
        heroi.setNome("Clark Kent");
        heroi.setNomeHeroi("Superman");

        heroiResponseDTO = new HeroiResponseDTO(
                "Clark Kent", "Superman", LocalDateTime.now(), 1.91, 107.0, null
        );
    }

    @Test
    @DisplayName("Deve criar um herói com sucesso quando o nome de herói é único")
    void deveCriarHeroiComSucesso() {
        when(heroiRepository.findByNomeHeroi(anyString())).thenReturn(Optional.empty());
        when(heroiMapper.toEntity(any(HeroiCreateDTO.class))).thenReturn(heroi);
        when(heroiRepository.save(any(Heroi.class))).thenReturn(heroi);
        when(heroiMapper.toResponseDTO(any(Heroi.class))).thenReturn(heroiResponseDTO);

        HeroiResponseDTO result = heroiService.criarHeroi(heroiCreateDTO);

        assertNotNull(result);
        assertEquals(heroiResponseDTO.nomeHeroi(), result.nomeHeroi());
        verify(heroiRepository, times(1)).findByNomeHeroi(anyString());
        verify(heroiRepository, times(1)).save(any(Heroi.class));
    }

    @Test
    @DisplayName("Deve lançar RegistroDuplicadoException quando o nome de herói já existe")
    void deveLancarExcecaoAoCriarHeroiComNomeDuplicado() {
        when(heroiRepository.findByNomeHeroi(anyString())).thenReturn(Optional.of(new Heroi()));

        assertThrows(RegistroDuplicadoException.class, () -> heroiService.criarHeroi(heroiCreateDTO));
        verify(heroiRepository, times(1)).findByNomeHeroi(anyString());
        verify(heroiRepository, never()).save(any(Heroi.class));
    }

    @Test
    @DisplayName("Deve atualizar um herói com sucesso quando o nome do herói é alterado")
    void deveAtualizarHeroiComSucessoAlterandoNome() {
        Heroi heroiExistente = new Heroi();
        heroiExistente.setId(1L);
        heroiExistente.setNomeHeroi("NomeAntigo");

        HeroiCreateDTO dtoAtualizacao = new HeroiCreateDTO("Novo Nome", "NomeNovo", null, 0.0, 0.0, null); // Nome novo

        when(heroiRepository.findById(1L)).thenReturn(Optional.of(heroiExistente));
        when(heroiRepository.findByNomeHeroi(anyString())).thenReturn(Optional.empty()); // Necessário agora
        when(heroiRepository.save(any(Heroi.class))).thenReturn(heroiExistente);
        when(heroiMapper.toResponseDTO(any(Heroi.class))).thenReturn(heroiResponseDTO);

        Optional<HeroiResponseDTO> result = heroiService.atualizarHeroi(1L, dtoAtualizacao);

        assertTrue(result.isPresent());
        verify(heroiRepository, times(1)).findById(1L);
        verify(heroiRepository, times(1)).findByNomeHeroi(dtoAtualizacao.nomeHeroi()); // Verifica que o método foi chamado
        verify(heroiRepository, times(1)).save(any(Heroi.class));
    }

    @Test
    @DisplayName("Deve lançar OperacaoNaoPermitidaException ao tentar atualizar herói inexistente")
    void deveLancarExcecaoAoAtualizarHeroiInexistente() {
        when(heroiRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OperacaoNaoPermitidaException.class, () -> heroiService.atualizarHeroi(1L, heroiCreateDTO));
        verify(heroiRepository, times(1)).findById(anyLong());
        verify(heroiRepository, never()).save(any(Heroi.class));
    }

    @Test
    @DisplayName("Deve deletar um herói com sucesso")
    void deveDeletarHeroiComSucesso() {
        when(heroiRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(heroiRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> heroiService.deletarHeroi(1L));

        verify(heroiRepository, times(1)).existsById(anyLong());
        verify(heroiRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve lançar OperacaoNaoPermitidaException ao tentar deletar herói inexistente")
    void deveLancarExcecaoAoDeletarHeroiInexistente() {
        when(heroiRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(OperacaoNaoPermitidaException.class, () -> heroiService.deletarHeroi(1L));
        verify(heroiRepository, times(1)).existsById(anyLong());
        verify(heroiRepository, never()).deleteById(anyLong());
    }
}
