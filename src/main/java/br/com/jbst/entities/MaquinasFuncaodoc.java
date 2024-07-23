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
@Table(name = "maquinas_funcaodoc")
public class MaquinasFuncaodoc {
	@Id // Campo 1
	@Column(name = "id_maquinas_funcaodoc")
	private UUID id_maquinas_funcaodoc;

	// Campo 2
	@Column(name = "nome_maquinas_funcaodoc", length = 500, nullable = false)
	private String nome_maquinas_funcaodoc;

	// Campo 3
	@Column(name = "descricao_maquinas_funcaodoc", length = 500, nullable = false)
	private String descricao_maquinas_funcaodoc;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcaodoc")
	private FuncaoDoc funcaodoc;
}
