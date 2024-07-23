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
@Table(name = "potencial_severidade_danos")
public class Potencial_Severidade_Danos {

	@Id
	@Column(name = "id_potencial_severidade")
	private UUID idPotencialSeveridade;
	
	@Column(name = "categoria_potencial_severidade", length = 200, nullable = true)
	private String categoria_potencial_severidade; // Categoria: 1
	
	@Column(name = "tipo_potencial_severidade", length = 200, nullable = true)
	private String tipo;//Tipo: Desprezivel
	
	@Column(name = "caracteristicas_potencial_severidade", length = 500, nullable = true)
	private String caracteristicas_potencial_severidade;//Caracteristicas: Não degrada o sistema nem seu funcionamento

	@OneToOne
	@JoinColumn (name = "id_riscos" , nullable = true )
	private Riscos riscos;
	
	@OneToOne
	@JoinColumn(name = "id_perigo" , nullable = true)
	private Perigo perigo;
}


