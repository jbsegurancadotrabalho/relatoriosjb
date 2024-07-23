package br.com.jbst.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "exames_funcaodoc")
public class ExamesFuncaodoc {
	@Id // Campo 1
	@Column(name = "id_exames_funcaodoc")
	private UUID id_exames_funcaodoc;

	// Campo 2
	@Column(name = "nome_exames_funcaodoc", length = 500, nullable = false)
	private String nome_exames_funcaodoc;

	// Campo 3
	@Column(name = "descricao_exames_funcaodoc", length = 500, nullable = false)
	private String descricao_exames_funcaodoc;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcaodoc", nullable = true)
	private FuncaoDoc funcaodoc;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcao_especifica", nullable = true)
	private FuncaoEspecifica funcaoespecifica;
}
