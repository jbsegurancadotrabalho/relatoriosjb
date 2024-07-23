package br.com.jbst.repositories.modulo2;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Potencial_Severidade_Danos;

public interface IPotencial_SeveridadeRepository extends JpaRepository<Potencial_Severidade_Danos, UUID>{

}
