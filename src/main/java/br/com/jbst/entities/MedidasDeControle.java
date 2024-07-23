package br.com.jbst.entities;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "medidas_de_controle")
public class MedidasDeControle {	
	
	
	@Id
	@Column(name = "id_medidasdecontrole ")
	private UUID idMedidasDeControle ;
	
	@Column(name = "valor_MedidasDeControle", length = 100, nullable = true)
	private String valor_MedidasDeControle;	//VALOR - 15

	@Column(name = "tipo_MedidasDeControle", length = 500, nullable = true)
	private String tipo_MedidasDeControle; // Eliminação, Substituição, Controles de Engenharia,
	//Administrativo, EPI´S, Inexistente

	@Column(name = "descricao_MedidasDeControle", length = 1000, nullable = true)
	private String descricao_MedidasDeControle; //Eliminação da Fonte de Risco do Local:

	@OneToOne
	@JoinColumn (name = "id_riscos", nullable = true)
	private Riscos riscos;
	
	@ManyToOne
	@JoinColumn ( name = "id_perigo", nullable = true)
	private Perigo perigo;
}

