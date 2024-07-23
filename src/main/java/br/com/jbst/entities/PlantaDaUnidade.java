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
@Table(name = "planta_da_unidade")
public class PlantaDaUnidade {
	
	@Id
	@Column(name = "id_planta_unidade")
	private UUID idPlanta;	
	
	@Column(name = "planta", length = 1000, nullable = true)
	private String Planta_unidade;
	
	@Column(name = "localização_planta_unidade", length = 500, nullable = true)
	private String Localização_planta_unidade;
	
	@Column(name = "construção", length = 500, nullable = true)
	private String Construção;
	
	@Column(name = "dimensões", length = 500, nullable = true)
	private String Dimensões;
	
	@Column(name = "ocupação", length = 500, nullable = true)
	private String Ocupação;
	
	@Column(name = "população", length = 100, nullable = true)
	private String População; 
	
	@Column(name = "HorárioFuncionamento", length = 100, nullable = true)
	private String HorárioFuncionamento;
	
	@Column(name = "deficientes", length = 500, nullable = true)
	private String deficientes;
	
	@Column(name = "recursos_humanos", length = 500, nullable = true)
	private String RecursosHumanos;
	
	private String RecursosMateriais;
	
	
	@OneToOne
	@JoinColumn (name = "id_unidadedoc")
	private UnidadeDoc unidadedoc;

}
