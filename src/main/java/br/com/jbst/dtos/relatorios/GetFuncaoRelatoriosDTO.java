package br.com.jbst.dtos.relatorios;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class GetFuncaoRelatoriosDTO {

    private UUID idFuncao;
    private Instant dataHoraCriacao;
    private String nome_da_funcao;
    private String cbo;
    private String descricao;
    private String gro;
    private String gp;
    private GetFuncaoDocRelatoriosDTO funcaodoc;
}
