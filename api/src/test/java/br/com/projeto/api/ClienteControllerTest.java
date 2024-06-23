package br.com.projeto.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.api.controller.ClienteController;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.service.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ClienteService _clienteService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClienteSuccess()throws Exception{
        Cliente cliente = new Cliente();
        cliente.setName("Luiz Felipe");
        cliente.setEmail("luizfelipe@gmail.com");
        
        when(_clienteService.register(any(Cliente.class))).thenReturn(new ResponseEntity<>(cliente, HttpStatus.CREATED));

        

        mockMvc.perform(post("/api/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());
    }

}
