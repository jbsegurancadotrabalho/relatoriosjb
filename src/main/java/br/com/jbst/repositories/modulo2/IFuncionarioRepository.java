package br.com.jbst.repositories.modulo2;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Funcionario;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    List<Funcionario> findByEmpresa_idEmpresaAndCpf(UUID empresaId, String cpf);

}
