package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "gho_setor")
public class GHO_SETOR {
	
	@Id
	@Column(name = "id_gho_setor")
	private UUID id_gho_setor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	@Column(name = "nome_gho_setor", length = 100, nullable = true)
	private String nome_gho_setor;
	
	@Column(name = "descricao_gho_setor", length = 500, nullable = true)
	private String descricao_gho_setor;

	@OneToMany (mappedBy = "gho_setor")
	private List<Cenario> cenario;	
	
	@OneToMany (mappedBy = "gho_setor")
	private List<Postura> postura;
	
	
	@ManyToOne
	@JoinColumn(name = "idunidadeDoc")
	private UnidadeDoc unidadedoc;
	
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
