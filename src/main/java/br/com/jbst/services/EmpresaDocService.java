package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetEmpresaDTO;
import br.com.jbst.DTO.GetEmpresaDocDTO;
import br.com.jbst.DTO.PostEmpresaDocDTO;
import br.com.jbst.DTO.PutEmpresaDocCnaeDTO;
import br.com.jbst.DTO.PutEmpresaDocDTO;
import br.com.jbst.DTO.Empresas.Unidades.GetEmpresaDoc3DTO;
import br.com.jbst.entities.Cnae;
import  br.com.jbst.entities.Empresa;
import  br.com.jbst.entities.EmpresaDoc;
import br.com.jbst.repositories.modulo1.ICnaeRepository;
import br.com.jbst.repositories.modulo1.IEmpresaDocRepository;
import br.com.jbst.repositories.modulo1.IEmpresaRepository;

@Service
public class EmpresaDocService {


    @Autowired
    private ICnaeRepository cnaeRepository;
	
	@Autowired
	private IEmpresaDocRepository empresaDocRepository;

	@Autowired
	private IEmpresaRepository empresaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public GetEmpresaDocDTO criarEmpresaDoc(PostEmpresaDocDTO dto) throws Exception {
	    try {
	        // Definir o ID da empresa manualmente
	        UUID idEmpresa = dto.getIdEmpresa();

	        // Verificar se o ID da empresa é nulo
	        if (idEmpresa == null) {
	            throw new IllegalArgumentException("O ID da empresa não pode ser nulo.");
	        }

	        // Mapear o DTO para a entidade EmpresaDoc
	        EmpresaDoc empresaDoc = modelMapper.map(dto, EmpresaDoc.class);

	        // Definir o ID da empresa na entidade
	        empresaDoc.setIdEmpresa_doc(UUID.randomUUID());
	        empresaDoc.setEmpresa(empresaRepository.findById(dto.getIdEmpresa()).orElse(null));

	        // Definir a data e hora de criação
	        empresaDoc.setDataHoraCriacao(Instant.now());

	        // Salvar a entidade EmpresaDoc
	        empresaDocRepository.save(empresaDoc);

	        // Retornar o DTO mapeado
	        return modelMapper.map(empresaDoc, GetEmpresaDocDTO.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}


	public List<GetEmpresaDocDTO> getAllEmpresaDocs() {
		List<EmpresaDoc> empresaDocs = empresaDocRepository.findAll();
		return empresaDocs.stream().map(empresaDoc -> modelMapper.map(empresaDoc, GetEmpresaDocDTO.class))
				.collect(Collectors.toList());
	}
	
	public List<GetEmpresaDoc3DTO> consultarTodasUnidades() {
		List<EmpresaDoc> empresaDocs = empresaDocRepository.findAll();
		return empresaDocs.stream().map(empresaDoc -> modelMapper.map(empresaDoc, GetEmpresaDoc3DTO.class))
				.collect(Collectors.toList());
	}

	public GetEmpresaDocDTO getEmpresaDocById(UUID id) throws Exception {
		Optional<EmpresaDoc> empresaDocOptional = empresaDocRepository.findById(id);
		if (empresaDocOptional.isPresent()) {
			EmpresaDoc empresaDoc = empresaDocOptional.get();
			return modelMapper.map(empresaDoc, GetEmpresaDocDTO.class);
		} else {
			throw new Exception("EmpresaDoc não encontrado com o ID: " + id);
		}
	}



	public void excluirEmpresaDoc(UUID id) throws Exception {
		Optional<EmpresaDoc> empresaDocOptional = empresaDocRepository.findById(id);
		if (empresaDocOptional.isPresent()) {
			empresaDocRepository.deleteById(id);
		} else {
			throw new Exception("EmpresaDoc não encontrado com o ID: " + id);
		}
	}

	public GetEmpresaDocDTO editarEmpresaDoc(PutEmpresaDocDTO dto) throws Exception {
		try {
			EmpresaDoc empresaDoc = modelMapper.map(dto, EmpresaDoc.class);
	        empresaDoc.setDataHoraCriacao(Instant.now());
			empresaDocRepository.save(empresaDoc);
			return modelMapper.map(empresaDoc, GetEmpresaDocDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public List<GetEmpresaDTO> getAllEmpresa() {
		List<Empresa> empresa = empresaRepository.findAll();
		return empresa.stream().map(empresas -> modelMapper.map(empresa, GetEmpresaDTO.class))
				.collect(Collectors.toList());
	}


	public void atualizarCnaesEmpresaDoc(PutEmpresaDocCnaeDTO dto) throws Exception {
        UUID idEmpresaDoc = dto.getIdEmpresaDoc();
        List<UUID> idsCnae = dto.getIdsCnae();

        Optional<EmpresaDoc> empresaDocOptional = empresaDocRepository.findById(idEmpresaDoc);
        if (empresaDocOptional.isPresent()) {
            EmpresaDoc empresaDoc = empresaDocOptional.get();

            // Carregar os CNAEs associados com base nos IDs fornecidos
            List<Cnae> cnaes = idsCnae.stream()
                    .map(cnaeId -> cnaeRepository.findById(cnaeId).orElse(null))
                    .collect(Collectors.toList());

            // Remover todos os CNAEs associados anteriormente
            empresaDoc.getCnaes().clear();

            // Adicionar os novos CNAEs associados
            empresaDoc.getCnaes().addAll(cnaes);

            // Salvar a entidade atualizada
            empresaDocRepository.save(empresaDoc);
        } else {
            throw new Exception("EmpresaDoc não encontrado com o ID: " + idEmpresaDoc);
        }
    }

}
