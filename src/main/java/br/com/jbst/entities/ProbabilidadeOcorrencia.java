
package br.com.jbst.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "probabilidade_ocorrencia")
public class ProbabilidadeOcorrencia {

	@Id
	@Column(name = "id_probabilidade_ocorrencia")
	private UUID idprobabilidadeOcorrencia;
	
	@Column(name = "categoria", length = 50, nullable = true)
	private String categoria;// 1, 2, 3, 4, 5
	
	@Column(name = "denominacao", length = 50, nullable = true)
	private String denominacao;
	//Extremamente Remota, Remota, Improvavel, Provavel, Frequente
	@Column(name = "descricao", length = 1000, nullable = true)
	private String descricao;
    // Descricao
	
	@OneToOne
	@JoinColumn (name = "id_riscos" , nullable = true)
	private Riscos riscos;
	
	@OneToOne
	@JoinColumn (name = "id_perigo" , nullable = true)
	private Perigo perigo;
}
