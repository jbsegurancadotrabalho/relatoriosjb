package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "docfreefuncionario")
public class DocFreeFuncionario {

	@Id
	@Column(name = "id_docfreefuncionario")
	private UUID idDocFreeFuncionario;
	// Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = true)
	private Instant dataHoraCriacao;

	// Campo 3
	@Column(name = "nome", length = 100, nullable = true)
	private String nome;

	// Campo 5
	@Column(name = "cpf", length = 100, nullable = true)
	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datainicio", nullable = true)
	private Instant datainicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "vigencia", nullable = true)
	private Instant vigencia;
	
	@Column(name = "responsavel", length = 100, nullable = true)
	private String responsavel;
	
	@Column(name = "endereco", length = 500, nullable = true)
	private String endereco;
	
	@Column(name = "numerodocumentofree", nullable = true)
    private Integer numerodocumentofree; // Make sure this attribute exists
	
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

}
