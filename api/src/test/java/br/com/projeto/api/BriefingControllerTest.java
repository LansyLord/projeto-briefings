package br.com.projeto.api;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

import br.com.projeto.api.controller.BriefingController;
import br.com.projeto.api.exceptions.BriefingNotFoundException;
import br.com.projeto.api.model.Briefing;
import br.com.projeto.api.enums.BriefingStatus;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.service.BriefingService;

@WebMvcTest(BriefingController.class)
public class BriefingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BriefingService _briefingService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteBriefingSuccess() throws Exception {
        when(_briefingService.deleteBriefing(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        mockMvc.perform(delete("/api/briefing/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteBriefingNotFound() throws Exception {
        doThrow(new BriefingNotFoundException("Briefing not found")).when(_briefingService).deleteBriefing(anyLong());

        mockMvc.perform(delete("/api/briefing/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRegisterBriefing() throws Exception {
        Briefing briefing = new Briefing();
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        briefing.setId(1L);
        briefing.setCliente(cliente);
        briefing.setStatus(BriefingStatus.APPROVED);
        briefing.setTask("Sample task");
        briefing.setDescription("Sample description");

        when(_briefingService.register(any(Briefing.class))).thenReturn(briefing);

        mockMvc.perform(post("/api/briefing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(briefing)))
                .andExpect(status().isCreated());
    }

}
