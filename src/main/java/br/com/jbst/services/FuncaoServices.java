package br.com.jbst.services;

import java.util.List;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetFuncaoDTO;
import br.com.jbst.DTO.Unidades.Funcionarios.GetFuncaoRelatoriosDTO;
import br.com.jbst.entities.Funcao;
import br.com.jbst.repositories.modulo2.IFuncaoRepository;

@Service
public class FuncaoServices {

    @Autowired
    IFuncaoRepository funcaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<GetFuncaoDTO> getAll() throws Exception {

		List<Funcao> funcao = funcaoRepository.findAll();
		List<GetFuncaoDTO> lista = modelMapper.map(funcao, new TypeToken<List<GetFuncaoDTO>>() {
		}.getType());
		return lista;
	}
    
    public List<br.com.jbst.dtos.relatorios.GetFuncaoRelatoriosDTO> funcaoRelatorios() throws Exception {
        List<Funcao> funcao = funcaoRepository.findAll();
        List<br.com.jbst.dtos.relatorios.GetFuncaoRelatoriosDTO> lista = modelMapper.map(funcao, new TypeToken<List<GetFuncaoRelatoriosDTO>>() {}.getType());
        return lista;
    }
    
    
    
    
    
    
    public GetFuncaoDTO getById(UUID id) throws Exception {
        Funcao funcao = funcaoRepository.findById(id)
                .orElseThrow(() -> new Exception("Função não encontrada com o ID: " + id));
        GetFuncaoDTO dto = modelMapper.map(funcao, GetFuncaoDTO.class);
        return dto;
    }

}
 


  

