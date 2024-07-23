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
@Table(name = "maquinas_laudoeletrico")
public class Maquinas_LaudoEletrico {


	@Id
	@Column(name = "id_Maquinas_LaudoEletrico")
	private UUID idMaquinas_LaudoEletrico;
	
    @Column(name = "tiposMaquinas_LaudoEletrico", length = 1000, nullable = false)
	private String tiposMaquinas_LaudoEletrico;
    
    @Column(name = "condicoesMaquinas_LaudoEletrico", length = 1000, nullable = false)
	private String condicoesMaquinas_LaudoEletrico;
    
    @Column(name = "instalacaoMaquinas_LaudoEletrico", length = 1000, nullable = false)
	private String instalacaoMaquinas_LaudoEletrico;
    
    @Column(name = "acionamentoMaquinas_LaudoEletrico", length = 1000, nullable = false)
  	private String acionamentoMaquinas_LaudoEletrico;
    
    @ManyToOne 
    @JoinColumn (name = "id_inspecao")
    private Inspecao inspecao;
}
