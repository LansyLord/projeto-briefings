package br.com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.Briefing;

@Repository
public interface BriefingRepository extends JpaRepository<Briefing, Long>{
    
}
