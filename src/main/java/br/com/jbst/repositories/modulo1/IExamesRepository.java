package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Exames;

public interface IExamesRepository extends JpaRepository<Exames, UUID> {

}
