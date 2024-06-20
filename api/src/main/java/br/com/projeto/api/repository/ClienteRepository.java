package br.com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    int countById(Long id);

}
