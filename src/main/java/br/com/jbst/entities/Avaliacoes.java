package br.com.jbst.entities;
import java.util.List;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacoes")
public class Avaliacoes {

	@Id
	@Column(name = "idAvaliacoes")
	private UUID idAvaliacoes;
		
	@Column(name = "nome_avaliacoes", length = 500, nullable = true)
	private String nome_avaliacoes;
	
	@Column(name = "descricao_avaliacoes", length = 500, nullable = true)
	private String descricao_avaliacoes;
	
	@OneToMany (mappedBy = "avaliacoes")
    private List<AvaliacoesCredenciados> avaliacoescredenciados;
}
