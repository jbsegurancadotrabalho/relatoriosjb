
package br.com.jbst.entities.LaudoEletrico;


import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.com.jbst.entities.UnidadeDoc;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "inspecao")
public class Inspecao {

	@Id
	@Column(name = "id_inspecao")
	private UUID idInspecao;
	
	@Column(name = "numeroinspecao", nullable = true)
	private Integer numeroinspecao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dia_horacriacao", nullable = true)
	private Instant dia_horacriacao;
	
    @Column(name = "extensao", length = 1000, nullable = false)
	private String extensao;// o presente laudo foi realizado em 15/06 com validade de 01 ano
	
    @Column(name = "mediaTensao", length = 500, nullable = false)
    private String mediaTensao;
    
    @Column(name = "baixaTensao", length = 500, nullable = false)
	private String baixaTensao;
    
    @Column(name = "fn", length = 500, nullable = false)
	private String fn;
    
    @Column(name = "ffn", length = 500, nullable = false)
	private String ffn;
    
    @Column(name = "fffn", length = 500, nullable = false)
	private String fffn;
    
    @Column(name = "transformadores", length = 500, nullable = false)
	private String transformadores;
    
    @Column(name = "substacoes", length = 500, nullable = false)
	private String substacoes;
    
    @ManyToOne
    @JoinColumn ( name = "id_unidadedoc")
    private UnidadeDoc unidadedoc;
    
    @OneToMany (mappedBy = "inspecao")
    private List <Quadros_Medicoes> quadros_medicoes;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Substacoes> substacao;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Transformadores> transformador;
    
    @OneToMany (mappedBy = "inspecao")
    private List <Gerador> gerador;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Quadros_Paineis> quadrospaineis;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Protecoes> protecoes;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Condutores> condutores;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Tomadas> tomadas;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Aterramento> aterramento;
    
    @OneToMany (mappedBy = "inspecao")
    private List <Maquinas_LaudoEletrico> maquinas_laudoeletrico;
    
    @OneToMany (mappedBy = "inspecao")
    private List<Luminarias> luminarias;
    
    @OneToMany (mappedBy = "inspecao")
    private List<InstalacoesClassificadas> instalacoesclassificadas;
    
    @OneToMany (mappedBy = "inspecao")
    private List <Procedimentos> procedimentos;

	
   
}
