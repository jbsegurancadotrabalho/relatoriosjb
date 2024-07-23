package br.com.jbst.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curso_de_nrs")
public class CursoDeNrs {

	@Getter
	@Setter
	@Id
	@Column(name = "id_curso_nrs")
	private UUID idCursoNrs;

	@Column(name = "nomeCurso", length = 200, nullable = false)
	private String nomeCurso;

	@Column(name = "descricaoCurso", length = 200, nullable = false)
	private String descricaoCurso;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcaodoc")
	private FuncaoDoc funcaodoc;
	
	@ManyToOne 
	@JoinColumn(name = "id_funcao_especifica")
	private FuncaoEspecifica funcaoespecifica;

}
