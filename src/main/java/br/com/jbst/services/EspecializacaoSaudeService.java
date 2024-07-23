package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetEspecializacaoSaudeDTO;
import br.com.jbst.DTO2.PostEspecializacaoSaudeDTO;
import br.com.jbst.DTO2.PutEspecializacaoSaudeDTO;
import br.com.jbst.entities.EspecializacaoSaude;
import br.com.jbst.repositories.modulo1.IEspecializacaoSaudeRepository;
import br.com.jbst.repositories.modulo2.IFormacaoSaudeRepository;

@Service
public class EspecializacaoSaudeService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IEspecializacaoSaudeRepository iespecializacaoSaudeRepository;
	
	@Autowired
	IFormacaoSaudeRepository iformacaoSaudeRepository;
	
	public GetEspecializacaoSaudeDTO criarEspecializacaoSaude(PostEspecializacaoSaudeDTO dto) throws Exception{
		EspecializacaoSaude especializacaoSaude = modelMapper.map(dto, EspecializacaoSaude.class);
		especializacaoSaude.setIdEspecializacaoSaude(UUID.randomUUID());
		especializacaoSaude.setFormacaosaude(iformacaoSaudeRepository.findById(dto.getIdFormacaoSaude()).orElse(null));
		especializacaoSaude = iespecializacaoSaudeRepository.save(especializacaoSaude);
		return modelMapper.map(especializacaoSaude, GetEspecializacaoSaudeDTO.class);
	}
	
	 public GetEspecializacaoSaudeDTO atualizarEspecializacaoSaude(PutEspecializacaoSaudeDTO dto) {
	        // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
	        if (dto.getIdEspecializacaoSaude() == null) {
	            throw new IllegalArgumentException("ID do ExameCredenciados não fornecido para atualização.");
	        }

	        // Recuperar o objeto AvaliacoesCredenciados com base no ID fornecido
	        EspecializacaoSaude especializacaoSaude = iespecializacaoSaudeRepository.findById(dto.getIdEspecializacaoSaude())
	                .orElseThrow(() -> new RuntimeException("Especialização não encontrada com id: " + dto.getIdFormacaoSaude()));

	        // Atualizando os campos com os dados do DTO
	        modelMapper.map(dto, especializacaoSaude);
	        
	        // Atualizando as associações com outras entidades
	        especializacaoSaude.setFormacaosaude(iformacaoSaudeRepository.findById(dto.getIdFormacaoSaude()).orElse(null));
	        
	        // Salvando as alterações no banco de dados
	        especializacaoSaude = iespecializacaoSaudeRepository.save(especializacaoSaude);

	        // Mapeando a entidade atualizada de volta para DTO
	        return modelMapper.map(especializacaoSaude, GetEspecializacaoSaudeDTO.class);
	    }
	 
	 
	 public GetEspecializacaoSaudeDTO buscarEspecializacaoPorId(UUID id) {
	        EspecializacaoSaude especializacaoSaude = iespecializacaoSaudeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Especialização não encontrada com id: " + id));

	        return modelMapper.map(especializacaoSaude, GetEspecializacaoSaudeDTO.class);
	    }

	    public List<GetEspecializacaoSaudeDTO> buscarTodasEspecializacoes() {
	        List<EspecializacaoSaude> especializacoes = iespecializacaoSaudeRepository.findAll();
	        return especializacoes.stream()
	                .map(especializacao -> modelMapper.map(especializacao, GetEspecializacaoSaudeDTO.class))
	                .collect(Collectors.toList());
	    }

	    public void excluirEspecializacao(UUID id) {
	        EspecializacaoSaude especializacaoSaude = iespecializacaoSaudeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Especialização não encontrada com id: " + id));

	        iespecializacaoSaudeRepository.delete(especializacaoSaude);
	    }
}
