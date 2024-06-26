package br.com.projeto.api.service;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.exceptions.BriefingNotFoundException;
import br.com.projeto.api.exceptions.InvalidBriefingStatusException;
import br.com.projeto.api.exceptions.ClienteNotFoundException;
import br.com.projeto.api.model.Briefing;
import br.com.projeto.api.model.BriefingStatus;
import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.repository.BriefingRepository;
import br.com.projeto.api.repository.ClienteRepository;

@Service
public class BriefingService {

    @Autowired
    private BriefingRepository _briefingRepository;

    @Autowired
    private ClienteRepository _clienteRepository;

    public Briefing register(Briefing briefing)throws ClienteNotFoundException, InvalidBriefingStatusException{

        Long id = briefing.getCliente().getId();

        
        Cliente clienteFound = _clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente not found"));

        BriefingStatus status = briefing.getStatus();

        if(status != BriefingStatus.APPROVED && status != BriefingStatus.NEGOTIATION && status != BriefingStatus.FINISHED){
            throw new InvalidBriefingStatusException("Invalid Briefing status");
        } 

        briefing.setCliente(clienteFound);
        briefing.setDate(LocalDate.now());
        briefing.setTime(LocalTime.now());

        Briefing savedBriefing = _briefingRepository.save(briefing);
    
        return savedBriefing;
    }

    public Briefing update(Briefing briefing)throws ClienteNotFoundException, InvalidBriefingStatusException{

        Long id = briefing.getCliente().getId();

        Cliente clienteFound = _clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente not found"));

        BriefingStatus status = briefing.getStatus();

        if(status != BriefingStatus.APPROVED && status != BriefingStatus.NEGOTIATION && status != BriefingStatus.FINISHED){
            throw new InvalidBriefingStatusException("Invalid Briefing status");
        } 

        briefing.setCliente(clienteFound);

        Briefing savedBriefing = _briefingRepository.save(briefing);
    
        return savedBriefing;
    }

    public ResponseEntity<Void> deleteBriefing(Long id)throws BriefingNotFoundException{
        Briefing b = _briefingRepository.findById(id).orElseThrow(() -> new BriefingNotFoundException("Briefing not found"));
        _briefingRepository.delete(b);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Briefing> findAll(){
         return _briefingRepository.findAll();
    }
}
