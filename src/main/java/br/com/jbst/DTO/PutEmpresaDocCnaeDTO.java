package br.com.jbst.DTO;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class PutEmpresaDocCnaeDTO {
    private UUID idEmpresaDoc;
    private List<UUID> idsCnae;
}
