package br.com.jbst.DTO;

import lombok.Data;


@Data
public class GetEnderecoUfDTO {
    private String uf;
    
    public GetEnderecoUfDTO(String uf) {
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }
}

