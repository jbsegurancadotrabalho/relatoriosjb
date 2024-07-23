package br.com.jbst.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@Entity
@Table(name = "etapas")
@AllArgsConstructor
public class Etapas {

	
	@Id
	@Column(name = "id_Etapas")
	private UUID idEtapas;
	
	@Column(name = "numero_etapa", length = 20, nullable = false)
	private String numero_etapa; //Exemplo etapa 1
	
	@Column(name = "nome_etapa", length = 100, nullable = false)
	private String nome_etapa;
	
	@Column(name = "descricao_etapa", length = 1000, nullable = false)
	private String descricao_etapa;
	
	@Column(name = "acoes", length = 1000, nullable = false)
	private String acoes;
	
	@ManyToOne
	@JoinColumn (name = "id_cenario")
    private Cenario cenario;
	
	@ManyToOne
	@JoinColumn (name = "id_funcaodoc")
    private FuncaoDoc funcaodoc;
	
	@ManyToOne
	@JoinColumn(name = "id_documentos_free")
	private DocumentosFree documentosfree;
	

	public void setFuncaodoccenarios(FuncaoDocCenarios funcaoDoc2) {
		
	}

	 public Etapas() {}

	
	
}
