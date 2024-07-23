package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class PutFuncaoDocIncluirExames {
	private UUID idFuncaoDoc;
	private List<UUID> idExames;
}
