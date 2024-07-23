package br.com.jbst.repositories.modulo2;

import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Funcionario;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, UUID> {
	
}
