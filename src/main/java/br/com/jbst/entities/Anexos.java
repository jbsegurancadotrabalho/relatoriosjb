package br.com.jbst.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "anexos")
public class Anexos {
    // Campo 1
    @Id
    @Column(name = "id_anexos")
    private UUID idAnexos;

    // Campo 2
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoracriacao", nullable = false)
    private Instant dataHoraCriacao;

    @Column(name = "nome_anexo", length = 100, nullable = true)
    private String nomeAnexo;

    @Column(name = "descricao_anexo", length = 1000, nullable = true)
    private String descricaoAnexo;

    @ManyToOne
    @JoinColumn(name = "id_associacoes" , nullable = true)
    private Associacoes associacoes;

    @OneToMany(mappedBy = "anexos") 
    private List<Fotos> fotos;

}
