
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
@Table(name = "nivel_severidade")
public class Nivel_Severidade {
	
	@Id
	@Column(name = "id_nivel_severidade")
	private UUID idNivelSeveridade;
	
	@Column(name = "nivel_risco_MedidasDeControle", length = 100, nullable = true)
	private String nivel_risco_MedidasDeControle; //BAIXO - MODERADO - ALTO

	@Column(name = "numero_risco_MedidasDeControle", length = 100, nullable = true)
	private String numero_risco_MedidasDeControle; //1 A 10 11 A 15 16 A 25

	@Column(name = "descricao_MedidasDeControle", length = 100, nullable = true)
	private String descricao_MedidasDeControle; // TROCAR MATERIAL POR OUTRO QUE SEJA SEGURO

	@Column(name = "nivel_permissao_MedidasDeControle", length = 100, nullable = true)
	private String nivel_permissao_MedidasDeControle; // ACEITAVEL - TOLERAVEL - INTOLERAVEL

	@OneToOne
	@JoinColumn ( name = "id_riscos" , nullable = true)
    private Riscos riscos;
	
	@OneToOne
	@JoinColumn (name = "id_perigo" , nullable = true)
	private Perigo perigo;

}


//Nivel de 1 ate 10 - Baixo - Aceitavel
//Nivel de 11 até 15 - Moderado - Toleravel
//Nivel de 16 até 25 - Moderado - Intoleravel