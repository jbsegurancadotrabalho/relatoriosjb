package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO.GetExamesFuncaoDocDTO;
import br.com.jbst.DTO.PostExamesFuncaoDocDTO;
import br.com.jbst.DTO.PostExamesFuncaodocIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutExamesFuncaoDocDTO;
import br.com.jbst.entities.Exames;
import br.com.jbst.entities.ExamesFuncaodoc;
import br.com.jbst.repositories.modulo1.IExamesFuncaodocRepository;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;

@Service
public class ExamesFuncaodocServices {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IExamesFuncaodocRepository iexamesFuncaodocRepository;
	
	@Autowired
	IFuncaoDocRepository funcaoDocRepository;
	
	@Autowired
	IFuncaoEspecificaRepository IFuncaoEspecificaRepository;
	
	 public GetExamesFuncaoDocDTO  criarExamesFuncaodoc(PostExamesFuncaoDocDTO  dto) {
	        // Mapear o DTO para a entidade Perigo
		 ExamesFuncaodoc exame = modelMapper.map(dto, ExamesFuncaodoc.class);
	        
	        // Definir o ID do perigo manualmente (ou de outra forma, como preferir)
	        exame.setId_exames_funcaodoc(UUID.randomUUID());
			exame.setFuncaodoc(funcaoDocRepository.findById(dto.getIdFuncaoDoc()).orElse(null));

	        // Salvar o perigo no banco de dados
	        exame = iexamesFuncaodocRepository.save(exame);
	        
	        // Retornar o DTO mapeado
	        return modelMapper.map(exame, GetExamesFuncaoDocDTO.class);
	    }
	 
	 public GetExamesFuncaoDocDTO  criarExamesFuncaoEspecifica(PostExamesFuncaodocIncluirFuncaoEspecificaDTO  dto) {
	        // Mapear o DTO para a entidade Perigo
		 ExamesFuncaodoc exame = modelMapper.map(dto, ExamesFuncaodoc.class);
	        
	        // Definir o ID do perigo manualmente (ou de outra forma, como preferir)
	        exame.setId_exames_funcaodoc(UUID.randomUUID());
			exame.setFuncaoespecifica(IFuncaoEspecificaRepository.findById(dto.getIdFuncaoEspecifica()).orElse(null));
	        // Salvar o perigo no banco de dados
	        exame = iexamesFuncaodocRepository.save(exame);   
	        // Retornar o DTO mapeado
	        return modelMapper.map(exame, GetExamesFuncaoDocDTO.class);
	    }
	 
	 public GetExamesFuncaoDocDTO  editarExamesFuncaodoc(PutExamesFuncaoDocDTO  dto) {
	        UUID Id_exames_funcaodoc = dto.getId_exames_funcaodoc();
	        if (Id_exames_funcaodoc == null) {
	            return null; // Retorna null se o ID não for fornecido
	        }

	        // Busca o perigo no banco de dados com base no ID fornecido no DTO
	        ExamesFuncaodoc exames = iexamesFuncaodocRepository.findById(Id_exames_funcaodoc).orElse(null);

	        // Se o perigo não for encontrado, retorna null
	        if (exames == null) {
	            return null;
	        }

	        // Atualiza os dados do perigo existente com base nas informações fornecidas no DTO
	        exames.setNome_exames_funcaodoc(dto.getNome_exames_funcaodoc());
	        exames.setDescricao_exames_funcaodoc(dto.getDescricao_exames_funcaodoc());
	        ExamesFuncaodoc exame = iexamesFuncaodocRepository.save(exames);
	        return modelMapper.map(exame, GetExamesFuncaoDocDTO.class);
	    }
	 public GetExamesFuncaoDocDTO consultarExamesFuncaodocPorId(UUID id) {
	        ExamesFuncaodoc exame = iexamesFuncaodocRepository.findById(id).orElse(null);
	        if (exame == null) {
	            return null;
	        }
	        return modelMapper.map(exame, GetExamesFuncaoDocDTO.class);
	    }

	    // Método para listar todos os ExamesFuncaodoc
	    public List<GetExamesFuncaoDocDTO> consultarTodosOsExamesFuncaodoc() {
	        List<ExamesFuncaodoc> examesList = iexamesFuncaodocRepository.findAll();
	        return examesList.stream()
	                         .map(exame -> modelMapper.map(exame, GetExamesFuncaoDocDTO.class))
	                         .collect(Collectors.toList());
	    }
	    
	    public GetExamesFuncaoDocDTO excluirExamesFuncaodoc(UUID Id_exames_funcaodoc) throws Exception {
	        try {
	        	ExamesFuncaodoc exameExcluido = iexamesFuncaodocRepository.findById(Id_exames_funcaodoc)
	                    .orElseThrow(() -> new Exception("Exame não encontrado com o ID: " + Id_exames_funcaodoc));

	            iexamesFuncaodocRepository.deleteById(Id_exames_funcaodoc);

	            return modelMapper.map(exameExcluido, GetExamesFuncaoDocDTO.class);
	        } catch (EmptyResultDataAccessException e) {
	            throw new Exception("Exame não encontrado com o ID: " + Id_exames_funcaodoc);
	        }
	    }
	 
}
