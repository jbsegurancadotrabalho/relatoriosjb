package br.com.jbst.entities;

import java.util.List;

import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "especializacao_saude")
public class EspecializacaoSaude {

	@Id
	@Column(name = "id_especializacao_saude")
	private UUID idEspecializacaoSaude;
	
	@Column(name = "nome_especializacao_saude", length = 100, nullable = false)
	private String nomeEspecializacaoSaude;
	
	@Column(name = "descricao_especializacao", length = 1000, nullable = false)
	private String descricaoEspecializacao;

	@ManyToOne
	@JoinColumn(name = "id_formacao_saude")
	private FormacaoSaude formacaosaude;
	
	@OneToMany (mappedBy = "especializacaosaude")
	private List<Fotos> fotos;
}
