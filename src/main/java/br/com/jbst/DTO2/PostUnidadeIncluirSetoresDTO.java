package br.com.jbst.DTO2;

import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class PostUnidadeIncluirSetoresDTO {
	private UUID idunidadeDoc;
    private List<UUID> idsSetores;

}
