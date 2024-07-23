package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetEpiDTO;
import br.com.jbst.DTO.PostEpiDTO;
import br.com.jbst.DTO.PostEpiIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutEpiDTO;
import br.com.jbst.entities.EPI;
import br.com.jbst.repositories.modulo1.IEPIRepository;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;

@Service
public class EpiServices {
	
	@Autowired
	IFuncaoEspecificaRepository IFuncaoEspecificaRepository;
	
	
	@Autowired
	IEPIRepository 	iepiRepository;
  
	@Autowired
	IFuncaoDocRepository ifuncaoDocRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public GetEpiDTO criarEpi(PostEpiDTO dto) throws Exception {
	    try {
	        // Buscar todos os EPIs existentes no banco de dados
	        List<EPI> todosEPIs = iepiRepository.findAll();
	        
	        // Verificar se há algum EPI com todos os campos iguais aos do DTO
	        boolean epiExistente = todosEPIs.stream().anyMatch(epi -> 
	                epi.getIdEpi().equals(dto.getNome_epi()) &&
	                epi.getDescricao_epi().equals(dto.getDescricao_epi()) &&
	                epi.getCa().equals(dto.getCa()));
	        
	        if (epiExistente) {
	            throw new Exception("Já existe um EPI com os mesmos dados.");
	        }
	        
	        // Se não houver um EPI com os mesmos dados, criar um novo EPI
	        EPI epi = modelMapper.map(dto, EPI.class);
	        epi.setIdEpi(UUID.randomUUID());
			epi.setFuncaodoc(ifuncaoDocRepository.findById(dto.getIdFuncaoDoc()).orElse(null));
	        EPI novoEpi = iepiRepository.save(epi);
	        
	        return modelMapper.map(novoEpi, GetEpiDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar EPI: " + e.getMessage());
	    }
	}
	
	public GetEpiDTO criarEpiIncluirFuncaoEspecifica(PostEpiIncluirFuncaoEspecificaDTO dto) throws Exception {
	    try {
	        // Buscar todos os EPIs existentes no banco de dados
	        List<EPI> todosEPIs = iepiRepository.findAll();
	        
	        // Verificar se há algum EPI com todos os campos iguais aos do DTO
	        boolean epiExistente = todosEPIs.stream().anyMatch(epi -> 
	                epi.getIdEpi().equals(dto.getNome_epi()) &&
	                epi.getDescricao_epi().equals(dto.getDescricao_epi()) &&
	                epi.getCa().equals(dto.getCa()));
	        
	        if (epiExistente) {
	            throw new Exception("Já existe um EPI com os mesmos dados.");
	        }
	        
	        // Se não houver um EPI com os mesmos dados, criar um novo EPI
	        EPI epi = modelMapper.map(dto, EPI.class);
	        epi.setIdEpi(UUID.randomUUID());
			epi.setFuncaoespecifica(IFuncaoEspecificaRepository.findById(dto.getIdFuncaoEspecifica()).orElse(null));
	        EPI novoEpi = iepiRepository.save(epi);
	        
	        return modelMapper.map(novoEpi, GetEpiDTO.class);
	    } catch (Exception e) {
	        throw new Exception("Erro ao criar EPI: " + e.getMessage());
	    }
	}
	
	public GetEpiDTO editarEpi(PutEpiDTO dto) throws Exception {
        // Verifica se o DTO contém um ID válido
        UUID idEpi = dto.getIdEpi();
        if (idEpi == null) {
            return null; // Retorna null se o ID não for fornecido
        }

        // Busca o perigo no banco de dados com base no ID fornecido no DTO
        EPI epi = iepiRepository.findById(idEpi).orElse(null);

        // Se o perigo não for encontrado, retorna null
        if (epi == null) {
            return null;
        }

        epi.setNome_epi(dto.getNome_epi());
        epi.setDescricao_epi(dto.getDescricao_epi());
        epi.setCa(dto.getCa());
        // Salva o perigo atualizado no banco de dados
         EPI epis  = iepiRepository.save(epi);

        // Retorna o perigo atualizado convertido para DTO
        return modelMapper.map(epis, GetEpiDTO.class);
    }
 
	
	public List<GetEpiDTO> consultarTodosEPIs() {
        List<EPI> epiList = iepiRepository.findAll();
        return epiList.stream()
                .map(epi -> modelMapper.map(epi, GetEpiDTO.class))
                .collect(Collectors.toList());
    }

    public GetEpiDTO consultarEPIPorId(UUID idEpi) throws Exception {
        EPI epi = iepiRepository.findById(idEpi)
                .orElseThrow(() -> new Exception("EPI não encontrado com o ID: " + idEpi));
        return modelMapper.map(epi, GetEpiDTO.class);
    }
    public GetEpiDTO excluirEPI(UUID idEpi) throws Exception {
        try {
            EPI epiExcluido = iepiRepository.findById(idEpi)
                    .orElseThrow(() -> new Exception("EPI não encontrado com o ID: " + idEpi));

            iepiRepository.deleteById(idEpi);

            return modelMapper.map(epiExcluido, GetEpiDTO.class);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("EPI não encontrado com o ID: " + idEpi);
        }
    }
}
