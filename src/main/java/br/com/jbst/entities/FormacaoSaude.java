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
@Table(name = "formacao_saude")
public class FormacaoSaude {
	@Id
	@Column(name = "id_formacao_saude")
	private UUID idFormacaoSaude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false) 
	private Instant dataHoraCriacao;

	@Column(name = "formacao_saude", length = 100, nullable = false)
	private String formacao_saude;

	@Column(name = "conselho", length = 50, nullable = true)
	private String conselho;

	@Column(name = "registro", length = 100, nullable = true)
	private String registro;

	@Column(name = "estado", length = 100, nullable = true)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_profissional_saude")
	private ProfissionalSaude profissionalsaude;
		
	@OneToMany(mappedBy = "formacaosaude") 
	private List<EspecializacaoSaude> especializacaosaude;
	
	@OneToMany (mappedBy = "formacaosaude")
	private List<Fotos> fotos;
}
