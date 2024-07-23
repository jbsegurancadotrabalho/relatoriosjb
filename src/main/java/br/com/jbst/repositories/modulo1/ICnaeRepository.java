package br.com.jbst.repositories.modulo1;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Cnae;

public interface ICnaeRepository extends JpaRepository<Cnae, UUID> {
    boolean existsByCodigo(String codigo);
    boolean existsByDenominacao(String denominacao);

}
