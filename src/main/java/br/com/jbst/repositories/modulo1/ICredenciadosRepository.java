package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Credenciados;

public interface ICredenciadosRepository extends JpaRepository<Credenciados, UUID>{

	boolean existsByCnpj(String cnpj);

  
}
