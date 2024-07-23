package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetAnaliseDePosturaDTO;
import br.com.jbst.DTO.PostAnaliseDePosturaDTO;
import br.com.jbst.DTO.PutAnaliseDePosturaDTO;
import br.com.jbst.entities.AnalisePostura;
import br.com.jbst.entities.Postura;
import br.com.jbst.repositories.modulo1.IAnalisePosturaRepository;
import br.com.jbst.repositories.modulo2.IPosturaRepository;

@Service
public class AnalisePosturaServices {
	
	@Autowired
    private IPosturaRepository posturaRepository;
	
	@Autowired
	IAnalisePosturaRepository 	ianalisePosturaRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public GetAnaliseDePosturaDTO criarAnaliseDePostura(PostAnaliseDePosturaDTO dto) throws Exception {
        try {
            // Verificar se o ID da postura é nulo
            UUID idPostura = dto.getIdPostura();
            if (idPostura == null) {
                throw new IllegalArgumentException("O ID da postura não pode ser nulo.");
            }

            // Verificar se a postura existe
            Optional<Postura> optionalPostura = posturaRepository.findById(idPostura);
            if (!optionalPostura.isPresent()) {
                throw new IllegalArgumentException("Postura não encontrada com o ID fornecido: " + idPostura);
            }

            // Mapear o DTO para a entidade AnalisePostura
            AnalisePostura analisePostura = modelMapper.map(dto, AnalisePostura.class);

            // Associar a postura à análise de postura
            analisePostura.setPostura(optionalPostura.get());

            // Definir o ID da análise de postura manualmente
            analisePostura.setIdAnalisePostura(UUID.randomUUID());

            // Salvar a entidade AnalisePostura
            AnalisePostura savedAnalisePostura = ianalisePosturaRepository.save(analisePostura);

            // Retornar o DTO mapeado da entidade salva
            return modelMapper.map(savedAnalisePostura, GetAnaliseDePosturaDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
	
	 public GetAnaliseDePosturaDTO editarAnaliseDePostura(PutAnaliseDePosturaDTO dto) throws Exception {
	        try {
	            // Verificar se o ID da análise de postura é nulo
	            UUID idAnalisePostura = dto.getIdAnalisePostura();
	            if (idAnalisePostura == null) {
	                throw new IllegalArgumentException("O ID da análise de postura não pode ser nulo.");
	            }

	            // Verificar se a análise de postura existe
	            Optional<AnalisePostura> optionalAnalisePostura = ianalisePosturaRepository.findById(idAnalisePostura);
	            if (!optionalAnalisePostura.isPresent()) {
	                throw new IllegalArgumentException("Análise de postura não encontrada com o ID fornecido: " + idAnalisePostura);
	            }

	            // Obter a análise de postura a ser editada
	            AnalisePostura analisePostura = optionalAnalisePostura.get();

	           
	            // Mapear os campos atualizados do DTO para a entidade AnalisePostura
	            modelMapper.map(dto, analisePostura);

	            // Associar a postura à análise de postura

	            // Salvar as alterações na entidade AnalisePostura
	            AnalisePostura updatedAnalisePostura = ianalisePosturaRepository.save(analisePostura);

	            // Retornar o DTO mapeado da entidade atualizada
	            return modelMapper.map(updatedAnalisePostura, GetAnaliseDePosturaDTO.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	        
	        
	    }
	  public GetAnaliseDePosturaDTO buscarAnaliseDePosturaPorId(UUID id) throws Exception {
	        try {
	            // Encontrar a análise de postura pelo ID
	            Optional<AnalisePostura> optionalAnalisePostura = ianalisePosturaRepository.findById(id);

	            // Verificar se a análise de postura existe
	            if (!optionalAnalisePostura.isPresent()) {
	                throw new IllegalArgumentException("Análise de postura não encontrada com o ID fornecido: " + id);
	            }

	            // Mapear a análise de postura para o DTO GetAnaliseDePosturaDTO
	            AnalisePostura analisePostura = optionalAnalisePostura.get();
	            GetAnaliseDePosturaDTO analisePosturaDTO = modelMapper.map(analisePostura, GetAnaliseDePosturaDTO.class);

	            return analisePosturaDTO;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }

	    // Consulta todas as análises de postura
	    public List<GetAnaliseDePosturaDTO> buscarTodasAnalisesDePostura() {
	        List<AnalisePostura> todasAnalisesDePostura = ianalisePosturaRepository.findAll();

	        // Mapear a lista de entidades AnalisePostura para uma lista de DTOs GetAnaliseDePosturaDTO
	        List<GetAnaliseDePosturaDTO> dtos = todasAnalisesDePostura.stream()
	            .map(analisePostura -> modelMapper.map(analisePostura, GetAnaliseDePosturaDTO.class))
	            .collect(Collectors.toList());

	        return dtos;
	    }
	    public GetAnaliseDePosturaDTO excluirAnaliseDePosturaPorId(UUID id) throws Exception {
	        try {
	            // Encontrar a análise de postura pelo ID
	            Optional<AnalisePostura> optionalAnalisePostura = ianalisePosturaRepository.findById(id);

	            // Verificar se a análise de postura existe
	            if (!optionalAnalisePostura.isPresent()) {
	                throw new IllegalArgumentException("Análise de postura não encontrada com o ID fornecido: " + id);
	            }

	            // Mapear a análise de postura para o DTO GetAnaliseDePosturaDTO
	            AnalisePostura analisePostura = optionalAnalisePostura.get();
	            GetAnaliseDePosturaDTO analisePosturaDTO = modelMapper.map(analisePostura, GetAnaliseDePosturaDTO.class);

	            // Excluir a análise de postura
	            ianalisePosturaRepository.deleteById(id);

	            // Retornar o DTO mapeado da análise de postura excluída
	            return analisePosturaDTO;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }

}
