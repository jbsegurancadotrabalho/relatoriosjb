package br.com.jbst.DTO1;

import java.util.UUID;

import br.com.jbst.DTO.GetExamesDTO;
import lombok.Data;

@Data
public class GetExamesCredenciados2DTO {
	private UUID idExameCredenciado;
	private String valorCredenciado;
	private String valorJb;
    private GetExamesDTO exames;
	
}
