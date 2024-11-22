	package br.com.jbst.entities;
	
	import java.util.List;
	import java.util.UUID;
	
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;
	import jakarta.persistence.ManyToMany;
	import jakarta.persistence.OneToMany;
	import jakarta.persistence.Table;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity
	@Table(name = "exames")
	public class Exames {
	
		@Getter
		@Setter
		@Id
		@Column(name = "id_Exames ")
		private UUID idExames;
	
		@Column(name = "nome_exame", length = 100, nullable = false)
		private String nome_exame;
	
		@Column(name = "tipo_exame", length = 100, nullable = false)
		private String tipo_exame; // Imagens, Laboratorio, Avaliação Clinica
	
		@Column(name = "descricao_exame", length = 500, nullable = false)
		private String descricao_exame;
	
	
		@OneToMany(mappedBy = "exames")
		private List<ExamesCredenciados> examescredenciados;
	}
