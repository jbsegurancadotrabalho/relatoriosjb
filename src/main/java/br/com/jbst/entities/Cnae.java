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
@Table(name = "cnae")
public class Cnae {	
	
	@Id
	@Column(name = "idCnae")
	private UUID idCnae;
	
	@Column(name = "codigo", length = 20, nullable = false)
	private String codigo;
	
	@Column(name = "denominacao", length = 500, nullable = false)
	private String denominacao;
	
	@Column(name = "grau_de_risco", length = 20, nullable = false)
	private String grau_de_risco;
	
	@ManyToOne
	@JoinColumn(name = "idempresa_doc")
	private EmpresaDoc empresadoc;

}
