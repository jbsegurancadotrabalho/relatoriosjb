package br.com.jbst.entities.LaudoEletrico;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "perguntas")
public class Perguntas {

	@Id
	@Column(name = "id_perguntas")
	private UUID idPerguntas;
	
    @Column(name = "pergunta", length = 500, nullable = false)
	private String pergunta;
	
    @ManyToOne(fetch = FetchType.LAZY) // Set to lazy loading
    @JoinColumn (name = "id_quadros_medicoes", nullable = true)
    private Quadros_Medicoes quadros_medicoes;
    
    @ManyToOne 
    @JoinColumn (name = "id_quadros_paineis", nullable = true)
    private Quadros_Paineis quadros_paineis;
    
    @ManyToOne
    @JoinColumn (name = " id_protecoes", nullable = true)
    private Protecoes protecoes;
    
    @ManyToOne
    @JoinColumn (name = "id_tomadas", nullable = true)
    private Tomadas tomadas;
    
    @ManyToOne
    @JoinColumn (name = "id_procedimento", nullable = true)
    private Procedimentos procedimentos;
    
    @OneToMany(mappedBy = "perguntas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> resposta;
    
    @ManyToOne
    @JoinColumn(name = "id_aterramento", nullable = true)
    private Aterramento aterramento;
    
    @ManyToOne
    @JoinColumn(name = "id_condutores", nullable = true)
    private Condutores condutores;
    
    @ManyToOne
    @JoinColumn(name = "id_luminarias", nullable = true)
    private Luminarias luminarias;
}

