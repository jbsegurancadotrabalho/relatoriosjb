package br.com.jbst.DTO1;

import java.util.UUID;

import br.com.jbst.DTO.GetExamesDTO;
import lombok.Data;

@Data
public class GetExamesCredenciados4DTO {
	private UUID idExameCredenciado;
	private String valorCredenciado;
	private String valorJb;
   // private GetExames2DTO exames;
    private GetCredenciados4DTO credenciados; 
   	public void setLocalidade(String localidade) {
		
    }
}