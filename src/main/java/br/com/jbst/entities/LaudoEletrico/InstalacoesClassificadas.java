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
@Table(name = "instalacoesclassificadas")
public class InstalacoesClassificadas {
	
	

	@Id
	@Column(name = "id_instalacoesclassificadas")
	private UUID idInstalacoesClassificadas;
	
    @Column(name = "existeInstalacoesClassificadas", length = 1000, nullable = false)
	private String existeInstalacoesClassificadas;
    
    @Column(name = "necessidadeInstalacoesClassificadas", length = 1000, nullable = false)
	private String necessidadeInstalacoesClassificadas;
    
    @Column(name = "conservacaoInstalacoesClassificadas", length = 1000, nullable = false)
   	private String conservacaoInstalacoesClassificadas;
    
    @ManyToOne
    @JoinColumn (name  = "id_inspecao")
    private Inspecao inspecao;
}
