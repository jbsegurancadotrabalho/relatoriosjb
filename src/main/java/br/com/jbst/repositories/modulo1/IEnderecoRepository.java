package br.com.jbst.repositories.modulo1;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jbst.DTO.GetBairroDTO;
import br.com.jbst.DTO.GetEnderecoLocalidadesDTO;
import br.com.jbst.DTO.GetEnderecoUfDTO;
import br.com.jbst.entities.Endereco;

public interface IEnderecoRepository extends JpaRepository <Endereco, UUID> {
    

	@Query("SELECT DISTINCT new br.com.jbst.DTO.GetEnderecoUfDTO(e.uf) FROM Endereco e")
	List<GetEnderecoUfDTO> findDistinctUf();
	   
	@Query("SELECT DISTINCT new br.com.jbst.DTO.GetEnderecoLocalidadesDTO(e.localidade) FROM Endereco e")
	List<GetEnderecoLocalidadesDTO> findDistinctLocalidades();

	   
	@Query("SELECT DISTINCT new br.com.jbst.DTO.GetBairroDTO(e.bairro) FROM Endereco e")
	List<GetBairroDTO> findDistinctBairros();

}
