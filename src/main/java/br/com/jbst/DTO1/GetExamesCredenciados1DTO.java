package br.com.jbst.DTO1;

import java.util.UUID;

import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO2.GetCredenciados1DTO;
import br.com.jbst.DTO2.GetCredenciados2DTO;
import lombok.Data;

@Data
public class GetExamesCredenciados1DTO {

	private UUID idExameCredenciado;
	private String valorCredenciado;
	private String valorJb;
    private GetExamesDTO exames;
    //   private GetCredenciados2DTO credenciados; // Alterado para GetCredenciados2DTO
    //	public void setLocalidade(String localidade) {
		// TODO Auto-generated method stub
		
    //	}
}
