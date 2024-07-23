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
@Table(name = "substacoes")
public class Substacoes {
	@Id
	@Column(name = "idSubstacoes")
	private UUID idSubstacoes;
	
    @Column(name = "nome_substacoes", length = 100, nullable = false)
	private String nome_substacoes;
    
    @Column(name = "descricao_substacoes", length = 100, nullable = false)
 	private String descricao_substacoes;
    
    @ManyToOne
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;
}
