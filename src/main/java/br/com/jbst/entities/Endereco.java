package br.com.jbst.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "endereco")
public class Endereco {

    // Campo 1
	@Id
	@Column(name = "idendereco")
	private UUID idEndereco;

    // Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
    // Campo 3
	@Column(name = "cep", length = 15, nullable = false)
	private String cep;
	
    // Campo 4
	@Column(name = "logradouro", length = 100, nullable = false)
	private String logradouro;
	
    // Campo 5
	@Column(name = "complemento", length = 100, nullable = false)
	private String complemento;
	
    // Campo 6
	@Column(name = "numero", length = 100, nullable = false)
	private String numero;
	
    // Campo 7
	@Column(name = "bairro", length = 100, nullable = false)
	private String bairro;
	
    // Campo 8
	@Column(name = "localidade", length = 100, nullable = false)
	private String localidade;
	
    // Campo 9
	@Column(name = "uf", length = 100, nullable = false)
	private String uf;
	
    // Campo 10
	@Column(name = "ibge", length = 100, nullable = true)
	private String ibge;
	
    // Campo 11
	@Column(name = "gia", length = 100, nullable = true)
	private String gia;
	
    // Campo 12
	@Column(name = "ddd", length = 5, nullable = true)
	private String ddd;
	
    // Campo 13
	@Column(name = "siafi", length = 100, nullable = true)
	private String siafi;
	
	
    @OneToOne (mappedBy = "endereco")
    private Credenciados credenciados;
    
    @OneToOne (mappedBy = "endereco")
    private ProfissionalSaude profissionalsaude;
    
    @OneToOne (mappedBy = "endereco")
    private UnidadeDoc unidadedoc;
    
}

