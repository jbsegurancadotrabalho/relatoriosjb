package br.com.jbst.DTO2;

import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class PostUnidadeIncluirFuncaoDocDTO {
	private UUID idunidadeDoc;
    private List<UUID> idsFuncaodoc;
}
