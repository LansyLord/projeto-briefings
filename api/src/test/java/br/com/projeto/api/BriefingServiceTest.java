package br.com.projeto.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.projeto.api.exceptions.BriefingNotFoundException;
import br.com.projeto.api.exceptions.ClienteNotFoundException;
import br.com.projeto.api.model.Briefing;
import br.com.projeto.api.enums.BriefingStatus;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.repository.BriefingRepository;
import br.com.projeto.api.repository.ClienteRepository;
import br.com.projeto.api.service.BriefingService;

public class BriefingServiceTest {

    @InjectMocks
    private BriefingService _briefingService;

    @Mock
    private BriefingRepository _briefingRepository;

    @Mock
    private ClienteRepository _clienteRepository;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterBriefingClienteNotFound(){
        Briefing briefing = new Briefing();
        Cliente cliente = new Cliente();

        cliente.setId(1L);
        briefing.setCliente(cliente);
        briefing.setStatus(BriefingStatus.APPROVED);

        when(_clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClienteNotFoundException.class,() -> _briefingService.register(briefing));
    }


    @Test
    public void testRegisterBriefingSuccess(){
        Briefing briefing = new Briefing();
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        briefing.setCliente(cliente);
        briefing.setStatus(BriefingStatus.APPROVED);

        when(_clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(_briefingRepository.save(any(Briefing.class))).thenReturn(briefing);

        assertDoesNotThrow(() -> _briefingService.register(briefing));
    }

     @Test
    public void testDeleteBriefingSuccess() {
        Briefing briefing = new Briefing();
        briefing.setId(1L);
        when(_briefingRepository.findById(1L)).thenReturn(Optional.of(briefing));
        doNothing().when(_briefingRepository).delete(briefing);

        assertDoesNotThrow(() -> _briefingService.deleteBriefing(1L));
    }

    @Test
    public void testDeleteBriefingNotFound() {
        when(_briefingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BriefingNotFoundException.class, () -> _briefingService.deleteBriefing(1L));
    }
}
