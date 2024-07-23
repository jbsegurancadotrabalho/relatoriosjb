package br.com.jbst.enuns;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credenciados {
	
    public enum Status {
        CLINICA("Clínica"),
        CENTRO_DE_TREINAMENTO("Centro de Treinamento"),
        HIGIENE_OCUPACIONAL("Higiene Ocupacional"), ATIVO;
        Status(String descricao) {
        }

		Status() {
			// TODO Auto-generated constructor stub
		}

        
    }
}
