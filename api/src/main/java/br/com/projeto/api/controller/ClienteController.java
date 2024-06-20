package br.com.projeto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.exceptions.ClienteNotFoundException;
import br.com.projeto.api.model.Cliente;

import br.com.projeto.api.service.ClienteService;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping("/api/cliente")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente){
        return service.register(cliente);
    }

    //Modelo de método com id sem validação por dependência
    @PutMapping("/api/cliente")
    public ResponseEntity<?> editCliente(@Valid @RequestBody Cliente cliente)throws ClienteNotFoundException{
        return service.editCliente(cliente);
    }

    @GetMapping("/api/cliente")
    public List<Cliente> getAllClientes(){
        return service.getAllClientes();
    }

    @DeleteMapping("/api/cliente/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id)throws ClienteNotFoundException{
        service.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
