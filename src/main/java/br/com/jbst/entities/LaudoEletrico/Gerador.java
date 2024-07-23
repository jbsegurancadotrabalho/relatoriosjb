package br.com.jbst.entities.LaudoEletrico;

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
@Table(name = "gerador")
public class Gerador {
	@Id
	@Column(name = "id_gerador")
	private UUID idGerador;
	
    @Column(name = "nome_gerador", length = 100, nullable = false)
	private String nome_gerador;
	
    @Column(name = "caracteristicas_gerador", length = 500, nullable = false)
	private String caracteristicas_gerador;
    
    @ManyToOne
    @JoinColumn (name = "id_inspecao")
    private Inspecao inspecao;
}
