package br.com.jbst.entities;
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
@Table(name = "postura")
public class Postura {
	
	@Id
	@Column(name = "idPostura")
	private UUID idPostura;
	
	@Column(name = "nomePostura", length = 200, nullable = true)
	private String nomePostura;
	
	@Column(name = "descricaoPostura", length = 1000, nullable = true)
	private String descricaoPostura;
	
	@ManyToOne
	@JoinColumn (name = "id_gho_setor")
	private GHO_SETOR gho_setor;
	
	@OneToMany (mappedBy = "postura")
	private List<AnalisePostura> analisepostura;

}

//POSTURA DAS COSTAS
//X 1 – ERETA
//2 – INCLINADA
//3 – ERETA E TORCIDA
//4 – INCLINADA E TORCIDA
//
//POSTURA DOS BRAÇOS
//X 1– OS DOIS BRAÇOS ABAIXO DOS OMBROS
//2 – UM BRAÇO NO NÍVEL OU ACIMA DOS OMBROS
//3 – AMBOS BRAÇOS NO NÍVEL OU ACIMA DOS OMBROS

//POSTURA DAS PERNAS
//1 – SENTADO
//2 – DE PÉ COM AMBAS AS PERNAS ESTICADAS
//3 – DE PÉ COM O PESO DE UMA DAS PERNAS
//ESTICADA
//4 – DE PÉ OU AGACHADO COM AMBOS OS JOEHOS
//FLEXIONADOS
//5 – DE PÉ OU AGACHADO COM UM DOS JOELHOS
//DOBRADOS
//6 – AJOELHADO EM UM OU AMBOS OS JOELHOS
//X 7 – ANDANDO OU SE MOVENDO

//ESFORÇO
//1 – CARGA MENOR OU IGUA 10 Kg
//X 2 – CARGA MAIOR QUE 10 Kg E MENOR OU IGUAL 20 Kg
//3 – CARGA MAIOR QUE 20 Kg