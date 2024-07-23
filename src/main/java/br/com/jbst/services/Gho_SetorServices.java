package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetGho_SetorDTO;
import br.com.jbst.DTO.PostGho_SetorDTO;
import br.com.jbst.DTO.PutGho_SetorDTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetSetor2DTO;
import br.com.jbst.entities.GHO_SETOR;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IGho_SetorRepository;

@Service
public class Gho_SetorServices {

	@Autowired
	IGho_SetorRepository igho_SetorRepository;

	@Autowired
	private IUnidadeDocRepository iunidadeDocRepository;

	@Autowired
	private ModelMapper modelMapper;

	public GetGho_SetorDTO criarGhoSetor(PostGho_SetorDTO dto) throws Exception {
	    try {
	        // Mapear o DTO para a entidade GHO_SETOR
	        GHO_SETOR ghoSetor = modelMapper.map(dto, GHO_SETOR.class);
	        ghoSetor.setId_gho_setor(UUID.randomUUID());
	        ghoSetor.setDataHoraCriacao(Instant.now());
	        ghoSetor.setUnidadedoc(iunidadeDocRepository.findById(dto.getIdunidadeDoc()).orElse(null));
	        igho_SetorRepository.save(ghoSetor);

	        // Retornar o DTO mapeado
	        return modelMapper.map(ghoSetor, GetGho_SetorDTO.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	public GetGho_SetorDTO editarGhoSetor(PutGho_SetorDTO dto) throws Exception {
        try {
            UUID idGhoSetor = dto.getId_gho_setor();
            // Verificar se o GHO_SETOR com o ID fornecido existe
            Optional<GHO_SETOR> optionalGhoSetor = igho_SetorRepository.findById(idGhoSetor);
            if (!optionalGhoSetor.isPresent()) {
                throw new IllegalArgumentException("Setor não encontrado com o ID fornecido: " + idGhoSetor);
            }

            // Obter o GHO_SETOR a ser editado
            GHO_SETOR ghoSetor = optionalGhoSetor.get();

            // Mapear os campos atualizados do DTO para a entidade GHO_SETOR
            modelMapper.map(dto, ghoSetor);

            // Definir a unidade doc na entidade GHO_SETOR

            // Definir a data e hora de criação
            ghoSetor.setDataHoraCriacao(Instant.now());

            // Salvar as alterações na entidade GHO_SETOR
            GHO_SETOR updatedGhoSetor = igho_SetorRepository.save(ghoSetor);

            // Retornar o DTO mapeado da entidade atualizada
            return modelMapper.map(updatedGhoSetor, GetGho_SetorDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

	public GetGho_SetorDTO buscarGhoSetorPorId(UUID id) throws Exception {
        try {
            Optional<GHO_SETOR> optionalGhoSetor = igho_SetorRepository.findById(id);
            if (!optionalGhoSetor.isPresent()) {
                throw new IllegalArgumentException("Setor não encontrado com o ID fornecido: " + id);
            }
            GHO_SETOR ghoSetor = optionalGhoSetor.get();
            return modelMapper.map(ghoSetor, GetGho_SetorDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<GetGho_SetorDTO> buscarTodosGhoSetores() {
        List<GHO_SETOR> ghoSetores = igho_SetorRepository.findAll();
        return ghoSetores.stream()
                         .map(ghoSetor -> modelMapper.map(ghoSetor, GetGho_SetorDTO.class))
                         .collect(Collectors.toList());
    }
    
    public List<GetSetor2DTO> buscarSetoresCenarios() {
        List<GHO_SETOR> ghoSetores = igho_SetorRepository.findAll();
        return ghoSetores.stream()
                         .map(ghoSetor -> modelMapper.map(ghoSetor, GetSetor2DTO.class))
                         .collect(Collectors.toList());
    }
    
    public GetGho_SetorDTO excluirGhoSetor(UUID id) throws Exception {
        try {
            // Verificar se o setor existe antes de excluí-lo
            Optional<GHO_SETOR> optionalGhoSetor = igho_SetorRepository.findById(id);
            if (!optionalGhoSetor.isPresent()) {
                throw new IllegalArgumentException("Setor não encontrado com o ID fornecido: " + id);
            }

            // Excluir o setor
            GHO_SETOR ghoSetor = optionalGhoSetor.get();
            igho_SetorRepository.deleteById(id);

            // Mapear e retornar o DTO correspondente ao setor excluído
            return modelMapper.map(ghoSetor, GetGho_SetorDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}