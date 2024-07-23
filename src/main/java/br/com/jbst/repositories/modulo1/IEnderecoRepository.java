package br.com.jbst.repositories.modulo1;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Endereco;

public interface IEnderecoRepository extends JpaRepository <Endereco, UUID> {
    

 
}
