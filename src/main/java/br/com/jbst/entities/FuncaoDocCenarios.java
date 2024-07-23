package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "funcao_doc")
public class FuncaoDocCenarios {
	@Id
	@Column(name = "id_funcao_doc")
	private UUID idFuncaoDoc;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;

	@Column(name = "nome_da_funcao", length = 100, nullable = false)
	private String nome_da_funcao;
	
	
	@Column(name = "setor_gho", length = 100, nullable = false)
	private String setor_gho;
	
	@ManyToOne
	@JoinColumn(name = "id_funcao" , nullable = false)
	private Funcao funcao;

	
}

