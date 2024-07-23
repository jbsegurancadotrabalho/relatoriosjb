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
@Table(name = "cursos_credenciados")
public class Cursos_Credenciados {

	
	@Id
	@Column(name = "idcursos_credenciados")
	private UUID idCursosCredenciados;
	
	@Column(name = "nomeCursosCredenciados", length = 100, nullable = false)
	private String nomeCursosCredenciados;
	
	@Column(name = "statusCursosCredenciados", length = 100, nullable = false)
	private String statusCursosCredenciados;
	
	@Column(name = "valorCredenciado", length = 100, nullable = false)
	private String valorCredenciado;
	
	@Column(name = "valorJB", length = 100, nullable = false)
	private String valorJB;
	
	@ManyToOne
	@JoinColumn(name = "id_credenciados")
	private Credenciados credenciados;
	
	@ManyToOne
	@JoinColumn( name = "id_curso")
	private Curso curso;
}
