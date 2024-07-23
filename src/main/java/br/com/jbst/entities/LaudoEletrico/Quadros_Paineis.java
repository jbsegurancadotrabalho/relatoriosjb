package br.com.jbst.entities.LaudoEletrico;

import java.util.List;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "quadros_paineis")
public class Quadros_Paineis {

	@Id
	@Column(name = "idquadros_paineis")
	private UUID idquadros_paineis;
	
    @Column(name = "nome_quadros_paineis", length = 500, nullable = false)
	private String nome_quadros_paineis;
	
    @Column(name = "descricao_quadros_paineis", length = 500, nullable = false)
	private String descricao_quadros_paineis;
	
	@ManyToOne
	@JoinColumn(name = "id_inspecao")
	private Inspecao inspecao;
	
	@OneToMany(mappedBy = "quadros_paineis")
	private List<Perguntas> perguntas;	
	
}
