package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Cursos_Credenciados;

public interface ICursos_CredenciadosRepository extends JpaRepository<Cursos_Credenciados, UUID> {


}
