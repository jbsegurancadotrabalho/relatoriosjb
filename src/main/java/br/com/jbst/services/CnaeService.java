package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCenarioDTO;
import br.com.jbst.DTO.GetCnaeDto;
import br.com.jbst.DTO.PostCnaeDTO;
import br.com.jbst.DTO.PutCenarioDTO;
import br.com.jbst.DTO.PutCnaeDto;
import br.com.jbst.entities.Cenario;
import br.com.jbst.entities.Cnae;
import br.com.jbst.repositories.modulo1.ICnaeRepository;
import br.com.jbst.repositories.modulo1.IEmpresaDocRepository;

@Service
public class CnaeService {

	@Autowired
	private ICnaeRepository icnaeRepository;

	@Autowired
	private IEmpresaDocRepository  empresadocRepository;
	
	@Autowired
	private ModelMapper modelMapper;

    public GetCnaeDto criarCnae(PostCnaeDTO dto) throws Exception {
        try {
            
            Cnae cnae = modelMapper.map(dto, Cnae.class);
            cnae.setIdCnae(UUID.randomUUID());
            cnae.setEmpresadoc(empresadocRepository.findById(dto.getIdEmpresa_doc()).orElse(null));
            icnaeRepository.save(cnae);
            return modelMapper.map(cnae, GetCnaeDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

	public List<GetCnaeDto> getAllCnaes() {
		List<Cnae> cnaes = icnaeRepository.findAll();
		return cnaes.stream().map(cnae -> modelMapper.map(cnae, GetCnaeDto.class)).collect(Collectors.toList());
	}

	public GetCnaeDto getCnaeById(UUID id) throws Exception {
		Optional<Cnae> cnaeOptional = icnaeRepository.findById(id);
		if (cnaeOptional.isPresent()) {
			Cnae cnae = cnaeOptional.get();
			return modelMapper.map(cnae, GetCnaeDto.class);
		} else {
			throw new Exception("CNAE não encontrado com o ID: " + id);
		}
	}

	public void excluirCnae(UUID id) throws Exception {
		Optional<Cnae> cnaeOptional = icnaeRepository.findById(id);
		if (cnaeOptional.isPresent()) {
			icnaeRepository.deleteById(id);
		} else {
			throw new Exception("CNAE não encontrado com o ID: " + id);
		}
	}
	
	public GetCnaeDto editarCnae(PutCnaeDto dto) throws Exception {
	        try {
	            UUID idCnae = dto.getIdCnae();
	            // Verificar se o cenário com o ID fornecido existe
	            Optional<Cnae> optionalCnae = icnaeRepository.findById(idCnae);
	            if (!optionalCnae.isPresent()) {
	                throw new IllegalArgumentException("Cenário não encontrado com o ID fornecido: " + idCnae);
	            }

	            // Obter o cenário a ser editado
	            Cnae cnae= optionalCnae.get();

	            // Mapear os campos atualizados do DTO para a entidade Cenario
	            modelMapper.map(dto, cnae);

	            // Definir o GHO Setor na entidade Cenario

	            // Salvar as alterações na entidade Cenario
	            Cnae updatedCnae = icnaeRepository.save(cnae);

	            // Retornar o DTO mapeado da entidade atualizada
	            return modelMapper.map(updatedCnae, GetCnaeDto.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	
}
