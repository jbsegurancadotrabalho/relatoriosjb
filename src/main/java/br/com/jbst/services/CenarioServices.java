package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCenarioDTO;
import br.com.jbst.DTO.GetCenarioUnidadeDTO;
import br.com.jbst.DTO.PostCenarioDTO;
import br.com.jbst.DTO.PutCenarioDTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetCenario3DTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetCenario4DTO;
import br.com.jbst.entities.Cenario;
import br.com.jbst.repositories.modulo1.ICenarioRepository;
import br.com.jbst.repositories.modulo2.IGho_SetorRepository;

@Service
public class CenarioServices {
	
	@Autowired
	ICenarioRepository icenarioRepository;
	

	
	@Autowired
	IGho_SetorRepository igho_SetorRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public GetCenarioDTO criarCenario(PostCenarioDTO dto) throws Exception {
        try {
            // Definir o ID do cenário manualmente
            UUID idGhoSetor = dto.getId_gho_setor();
            if (idGhoSetor == null) {
                throw new IllegalArgumentException("O ID do GHO Setor não pode ser nulo.");
            }
            // Mapear o DTO para a entidade Cenario
            Cenario cenario = modelMapper.map(dto, Cenario.class);
            // Definir o ID do cenário na entidade
            cenario.setIdCenario(UUID.randomUUID());
            cenario.setGho_setor(igho_SetorRepository.findById(dto.getId_gho_setor()).orElse(null));
            // Salvar a entidade Cenario
            icenarioRepository.save(cenario);

            // Retornar o DTO mapeado
            return modelMapper.map(cenario, GetCenarioDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
	
	 public GetCenarioDTO editarCenario(PutCenarioDTO dto) throws Exception {
	        try {
	            UUID idCenario = dto.getIdCenario();
	            // Verificar se o cenário com o ID fornecido existe
	            Optional<Cenario> optionalCenario = icenarioRepository.findById(idCenario);
	            if (!optionalCenario.isPresent()) {
	                throw new IllegalArgumentException("Cenário não encontrado com o ID fornecido: " + idCenario);
	            }

	            // Obter o cenário a ser editado
	            Cenario cenario = optionalCenario.get();

	            // Mapear os campos atualizados do DTO para a entidade Cenario
	            modelMapper.map(dto, cenario);

	            // Definir o GHO Setor na entidade Cenario

	            // Salvar as alterações na entidade Cenario
	            Cenario updatedCenario = icenarioRepository.save(cenario);

	            // Retornar o DTO mapeado da entidade atualizada
	            return modelMapper.map(updatedCenario, GetCenarioDTO.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	 public GetCenario4DTO buscarCenarioPorId(UUID id) throws Exception {
	        try {
	            // Encontrar o cenário pelo ID
	            Optional<Cenario> optionalCenario = icenarioRepository.findById(id);

	            // Verificar se o cenário existe
	            if (!optionalCenario.isPresent()) {
	                throw new IllegalArgumentException("Cenário não encontrado com o ID fornecido: " + id);
	            }

	            // Mapear o cenário para o DTO GetCenarioDTO
	            Cenario cenario = optionalCenario.get();
	            GetCenario4DTO cenarioDTO = modelMapper.map(cenario, GetCenario4DTO.class);

	            return cenarioDTO;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	 public List<GetCenarioDTO> buscarTodosCenarios() {
	        List<Cenario> todosCenarios = icenarioRepository.findAll();

	        // Mapear a lista de entidades Cenario para uma lista de DTOs GetCenarioDTO
	        List<GetCenarioDTO> dtos = todosCenarios.stream()
	            .map(cenario -> modelMapper.map(cenario, GetCenarioDTO.class))
	            .collect(Collectors.toList());

	        return dtos;
	    }
	 
	 public List<GetCenario3DTO> buscarTodosCenariosAssociacoes() {
	        List<Cenario> todosCenarios = icenarioRepository.findAll();

	        // Mapear a lista de entidades Cenario para uma lista de DTOs GetCenarioDTO
	        List<GetCenario3DTO> dtos = todosCenarios.stream()
	            .map(cenario -> modelMapper.map(cenario, GetCenario3DTO.class))
	            .collect(Collectors.toList());

	        return dtos;
	    }
	 
	 public List<GetCenarioUnidadeDTO> buscarCenariosUnidades() {
	        List<Cenario> todosCenarios = icenarioRepository.findAll();

	        // Mapear a lista de entidades Cenario para uma lista de DTOs GetCenarioDTO
	        List<GetCenarioUnidadeDTO> dtos = todosCenarios.stream()
	            .map(cenario -> modelMapper.map(cenario, GetCenarioUnidadeDTO.class))
	            .collect(Collectors.toList());

	        return dtos;
	    }
	 
	 public GetCenarioDTO excluirCenario(UUID id) throws Exception {
	        try {
	            // Verificar se o cenário existe antes de excluí-lo
	            Optional<Cenario> optionalCenario = icenarioRepository.findById(id);
	            if (!optionalCenario.isPresent()) {
	                throw new IllegalArgumentException("Cenário não encontrado com o ID fornecido: " + id);
	            }

	            // Excluir o cenário
	            Cenario cenarioExcluido = optionalCenario.get();
	            icenarioRepository.deleteById(id);

	            // Retornar o DTO mapeado do cenário excluído
	            return modelMapper.map(cenarioExcluido, GetCenarioDTO.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
}
