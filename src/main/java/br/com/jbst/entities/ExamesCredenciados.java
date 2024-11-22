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
@Table(name = "exames_credenciados")
public class ExamesCredenciados {
	
	@Id
	@Column(name = "id_examecredenciados ")
		private UUID idExameCredenciado;
	
	@Column(name = "valorCredenciado", length = 50, nullable = false)
	private String valorCredenciado;
	
	@Column(name = "valorJb", length = 50, nullable = false)
	private String valorJb;
	
	@ManyToOne
	@JoinColumn(name = "id_credenciado")
	private Credenciados credenciados;

	@OneToMany(mappedBy = "examescredenciados")
	private List <Agenda> agenda;
	
	@ManyToOne
	@JoinColumn (name = "id_exames")
	private Exames exames;
	
}
