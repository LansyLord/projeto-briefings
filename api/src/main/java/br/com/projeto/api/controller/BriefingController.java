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

import br.com.projeto.api.dto.BriefingDTO;
import br.com.projeto.api.exceptions.BriefingNotFoundException;
import br.com.projeto.api.exceptions.InvalidBriefingStatusException;
import br.com.projeto.api.exceptions.ClienteNotFoundException;
import br.com.projeto.api.model.Briefing;
import br.com.projeto.api.service.BriefingService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class BriefingController {
    
    @Autowired
    private BriefingService _briefingService;

    @PostMapping("/api/briefing")
    public ResponseEntity<BriefingDTO> registerBriefing(@Valid @RequestBody Briefing briefing) throws ClienteNotFoundException, InvalidBriefingStatusException{
        BriefingDTO createdBriefing = BriefingDTO.from(_briefingService.register(briefing));
        return new ResponseEntity<>(createdBriefing, HttpStatus.CREATED);
    }

    @PutMapping("/api/briefing")
    public ResponseEntity<BriefingDTO> updateBriefing(@Valid @RequestBody Briefing briefing) throws ClienteNotFoundException, InvalidBriefingStatusException{
        BriefingDTO updatedBriefing = BriefingDTO.from(_briefingService.register(briefing));
        return ResponseEntity.ok(updatedBriefing);
    }

    @DeleteMapping("/api/briefing/{id}")
    public ResponseEntity<Void> deleteBriefing(@Valid @PathVariable Long id)throws BriefingNotFoundException{
        return _briefingService.deleteBriefing(id);
    }
    
    @GetMapping("/api/briefing")
    public ResponseEntity<List<BriefingDTO>> getAllBriefings(){
        List<Briefing> briefings = _briefingService.findAll();
        List<BriefingDTO> BriefingDTOList = BriefingDTO.fromAll(briefings);
        return ResponseEntity.ok(BriefingDTOList);
    }


}
