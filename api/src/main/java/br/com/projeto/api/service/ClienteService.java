package br.com.projeto.api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.exceptions.ClienteNotFoundException;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.repository.ClienteRepository;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository acao;

    public Cliente register(Cliente c){
        return acao.save(c);
    }

    public ResponseEntity<?> editCliente(Cliente c)throws ClienteNotFoundException{
        if(acao.countById(c.getId()) == 0){
            throw new ClienteNotFoundException("Cliente not found");
        }
        return new ResponseEntity<>(acao.save(c), HttpStatus.OK);
    }

    public List<Cliente> getAllClientes(){
        return acao.findAll();
    }

    public void deleteCliente(Long id)throws ClienteNotFoundException{
        if(acao.countById(id) == 0){
            throw new ClienteNotFoundException("Cliente not found");
        }
        acao.deleteById(id);
    }
}
