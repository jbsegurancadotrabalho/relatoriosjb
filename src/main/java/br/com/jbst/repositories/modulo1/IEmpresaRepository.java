package br.com.jbst.repositories.modulo1;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.entities.Empresa;

public interface IEmpresaRepository extends JpaRepository<Empresa, UUID> {
	@Query("SELECT e FROM Empresa e LEFT JOIN FETCH e.empresadoc ed LEFT JOIN FETCH e.funcionarios")
    List<Empresa> findAllWithDocumentAndFuncionarios();
	
    Optional<Empresa> findByCnpj(String cnpj);

}
