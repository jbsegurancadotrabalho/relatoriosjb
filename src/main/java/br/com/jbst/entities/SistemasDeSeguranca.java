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
@Table(name = "sistemas_de_seguranca")
public class SistemasDeSeguranca {
	
	@Id
	@Column(name = "id_sistemas_de_seguranca")
	private UUID idsistemas_de_seguranca;
	
	@Column(name = "nome_sistema_de_seguranca", length = 100, nullable = true)
	private String nomeSistemaDeSeguranca;//Quimico, Fisico, Biológico
	
	@Column(name = "descricaoSistemaDeSeguranca", length = 1000, nullable = true)
	private String descricaoSistemaDeSeguranca;//Fisico: Ruido / Quimico: Poeiras

   @ManyToOne
   @JoinColumn (name = "id_maquinas", nullable = true)
   private Maquinas maquinas;
}
