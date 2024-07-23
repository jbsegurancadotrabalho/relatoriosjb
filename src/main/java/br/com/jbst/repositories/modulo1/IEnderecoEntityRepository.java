package br.com.jbst.repositories.modulo1;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.EnderecoEntity;

public interface IEnderecoEntityRepository extends JpaRepository<EnderecoEntity, UUID> {

	Optional<EnderecoEntity> findByCepAndLogradouroAndComplementoAndNumeroAndBairroAndLocalidadeAndUf(
	        String cep,
	        String logradouro,
	        String complemento,
	        String numero,
	        String bairro,
	        String localidade,
	        String uf
	    );
}
