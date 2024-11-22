package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetFormacaoSaudeDTO;
import br.com.jbst.DTO2.PostFormacaoSaudeDTO;
import br.com.jbst.DTO2.PutFormacaoSaudeDTO;
import br.com.jbst.entities.FormacaoSaude;
import br.com.jbst.repositories.modulo2.IFormacaoSaudeRepository;
import br.com.jbst.repositories.modulo2.IProfissionalSaudeRepository;

@Service
public class FormacaoSaudeService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IFormacaoSaudeRepository iformacaoRepository;
	
	@Autowired
	IProfissionalSaudeRepository iprofissionalSaudeRepository;
	
	   public  GetFormacaoSaudeDTO criarFormacaoSaude( PostFormacaoSaudeDTO dto) {
		   FormacaoSaude formacaosaude = modelMapper.map(dto, FormacaoSaude.class);
		   formacaosaude.setIdFormacaoSaude(UUID.randomUUID());
		   formacaosaude.setDataHoraCriacao(Instant.now());
		   formacaosaude.setProfissionalsaude(iprofissionalSaudeRepository.findById(dto.getId_profissionalsaude()).orElse(null));
		   formacaosaude = iformacaoRepository.save(formacaosaude);
	        return modelMapper.map(formacaosaude, GetFormacaoSaudeDTO.class);
	    }
	   
		   public GetFormacaoSaudeDTO atualizarFormacaoSaude(PutFormacaoSaudeDTO dto) {
		        // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
		        if (dto.getIdFormacaoSaude() == null) {
		            throw new IllegalArgumentException("ID do ExameCredenciados não fornecido para atualização.");
		        }
	
		        // Recuperar o objeto AvaliacoesCredenciados com base no ID fornecido
		        FormacaoSaude formacaoSaude = iformacaoRepository.findById(dto.getIdFormacaoSaude())
		                .orElseThrow(() -> new RuntimeException("AvaliacoesCredenciados não encontrado com id: " + dto.getIdFormacaoSaude()));
	
		        // Atualizando os campos com os dados do DTO
		        modelMapper.map(dto, formacaoSaude);
		        
		        
		        // Salvando as alterações no banco de dados
				formacaoSaude = iformacaoRepository.save(formacaoSaude);
	
		        // Mapeando a entidade atualizada de volta para DTO
		        return modelMapper.map(formacaoSaude, GetFormacaoSaudeDTO.class);
		    }
	   
	   public List<GetFormacaoSaudeDTO> buscarTodasFormacoesSaude() {
	        List<FormacaoSaude> formacaoSaude = iformacaoRepository.findAll();
	        return formacaoSaude.stream()
	                .map(c -> modelMapper.map(c, GetFormacaoSaudeDTO.class))
	                .collect(Collectors.toList());
	    }

	    public GetFormacaoSaudeDTO buscarFormacaoSaudePorId(UUID id) {
	    	FormacaoSaude formacaoSaude = iformacaoRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Exame Credenciado não encontrado com o ID: " + id));
	        return modelMapper.map(formacaoSaude, GetFormacaoSaudeDTO.class);
	    }
	    

	    public void excluirFormacaoSaudePorId(UUID id) {
	        if (!iformacaoRepository.existsById(id)) {
	            throw new RuntimeException("Exame Credenciado não encontrado com o ID: " + id);
	        }
	        iformacaoRepository.deleteById(id);
	    }
}
