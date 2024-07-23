package br.com.jbst.entities;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "associacoes")
public class Associacoes {
	// Campo 1	
    @Id
    @Column(name = "id_associacoes")
    private UUID idAssociacoes;

	// Campo 2
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", nullable = false)
    private Instant dataHoraCriacao;

	// Campo 3
    @Column(name = "venda", length = 100, nullable = true)
    private String venda;

    
	// Campo 4
    @Column(name = "valor", length = 100, nullable = true)
    private BigDecimal valor;

	// Campo 5
    @Column(name = "numero_associacoes", nullable = true)
    private Integer numeroAssociacoes;

	// Campo 6
    @Column(name = "status", length = 100, nullable = false)
    private String status;

	// Campo 7
    @Column(name = "tipo_de_pagamento", length = 100, nullable = false)
    private String tipo_de_pagamento;
    
	// Campo 8
    @Column(name = "observacoes", length = 1000, nullable = true)
    private String observacoes;
    
    
    @ManyToOne
    @JoinColumn(name = "id_relatorios" , nullable = false)
    private Relatorios relatorios;
    
    @ManyToOne
    @JoinColumn(name = "unidade_doc" , nullable = true)
    private UnidadeDoc unidadedoc;
    
    @ManyToOne
    @JoinColumn(name = "id_funcionario" , nullable = true)
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "id_cenario" , nullable = true)
    private Cenario cenario;
    
    @OneToMany(mappedBy = "associacoes")
    private List <Anexos> anexos;
}
