package br.com.jbst.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetUnidade3DTO;
import br.com.jbst.DTO.GetUnidadeDTO;
import br.com.jbst.DTO.PostUnidadeDTO;
import br.com.jbst.DTO.PutUnidadeDTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetUnidade2DTO;
import br.com.jbst.DTO2.PostUnidadeIncluirFuncaoDocDTO;
import br.com.jbst.DTO2.PostUnidadeIncluirSetoresDTO;
import br.com.jbst.entities.FuncaoDoc;
import br.com.jbst.entities.GHO_SETOR;
import br.com.jbst.entities.UnidadeDoc;
import br.com.jbst.entities.UnidadeDocEntities;
import br.com.jbst.repositories.modulo1.IEmpresaDocRepository;
import br.com.jbst.repositories.modulo1.IEnderecoRepository;
import br.com.jbst.repositories.modulo1.ISetor_GHORepository;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;
import br.com.jbst.repositories.modulo2.IUnidadeDocRepositoryEmpresaDoc;
import jakarta.transaction.Transactional;

@Service
public class UnidadeService {
	
    @Autowired
	IUnidadeDocRepositoryEmpresaDoc	IUnidadeDocRepositoryEmpresaDoc;
    
    @Autowired
    private IUnidadeDocRepository unidadeDocRepository;
    
    @Autowired
    private IEmpresaDocRepository iempresaDocRepository;

    @Autowired
    private IEnderecoRepository enderecoRepository;
    
    @Autowired
    ISetor_GHORepository isetor_GHORepository;
    
    @Autowired
    IFuncaoDocRepository IFuncaoDocRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public GetUnidadeDTO criarUnidade(PostUnidadeDTO dto) {
        UUID idEmpresaDoc = dto.getIdEmpresa_doc();
        if (idEmpresaDoc == null) {
            throw new IllegalArgumentException("O ID da empresa não pode ser nulo.");
        }

        UnidadeDocEntities unidadeDoc = modelMapper.map(dto, UnidadeDocEntities.class);
        unidadeDoc.setDataHoraCriacao(Instant.now());
        unidadeDoc.setIdunidadeDoc(UUID.randomUUID());
        unidadeDoc.setEmpresadoc(iempresaDocRepository.findById(idEmpresaDoc).orElse(null));
        unidadeDoc.setEndereco(enderecoRepository.findById(dto.getIdEndereco()).orElse(null));
        UnidadeDocEntities savedUnidade = IUnidadeDocRepositoryEmpresaDoc.save(unidadeDoc);
        return modelMapper.map(savedUnidade, GetUnidadeDTO.class);
    }

    public GetUnidadeDTO editarUnidade(PutUnidadeDTO dto) {
        UnidadeDoc existingUnidadeDoc = unidadeDocRepository.findById(dto.getIdunidadeDoc())
                .orElseThrow(() -> new IllegalArgumentException("Unidade não encontrada"));

        // Mapeando apenas os campos atualizados do DTO para a entidade existente
        modelMapper.map(dto, existingUnidadeDoc);

        // Atualizando a data de criação (ou mantenha a original se não quiser atualizá-la)
        existingUnidadeDoc.setDataHoraCriacao(Instant.now());

        // Salvando a entidade atualizada
        UnidadeDoc updatedUnidade = unidadeDocRepository.save(existingUnidadeDoc);

        // Retornando o DTO atualizado
        return modelMapper.map(updatedUnidade, GetUnidadeDTO.class);
    }

    
    public List<GetUnidade3DTO> consultarTodasAsUnidasEmpresas() {
        List<UnidadeDoc> unidades = unidadeDocRepository.findAll();
        return unidades.stream()
                .map(unidade -> modelMapper.map(unidade, GetUnidade3DTO.class))
                .collect(Collectors.toList());
    }

    public List<GetUnidadeDTO> getAllUnidades() {
        List<UnidadeDoc> unidades = unidadeDocRepository.findAll();
        return unidades.stream()
                .map(unidade -> modelMapper.map(unidade, GetUnidadeDTO.class))
                .collect(Collectors.toList());
    }
    
    public List<GetUnidade2DTO> UnidadesSetores() {
        List<UnidadeDoc> unidades = unidadeDocRepository.findAll();
        return unidades.stream()
                .map(unidade -> modelMapper.map(unidade, GetUnidade2DTO.class))
                .collect(Collectors.toList());
    }
    
    public List<GetUnidade3DTO> UnidadesRelatorios() {
        List<UnidadeDoc> unidades = unidadeDocRepository.findAll();
        return unidades.stream()
                .map(unidade -> modelMapper.map(unidade, GetUnidade3DTO.class))
                .collect(Collectors.toList());
    }

    public GetUnidadeDTO getUnidadeById(UUID id) {
        Optional<UnidadeDoc> optionalUnidade = unidadeDocRepository.findById(id);
        if (optionalUnidade.isPresent()) {
            UnidadeDoc unidade = optionalUnidade.get();
            return modelMapper.map(unidade, GetUnidadeDTO.class);
        } else {
            throw new IllegalArgumentException("Unidade com o ID " + id + " não encontrada.");
        }
    }

    public void excluirUnidade(UUID id) {
        unidadeDocRepository.deleteById(id);
    }
    
    public GetUnidadeDTO adicionarSetorEmUnidade(PostUnidadeIncluirSetoresDTO dto) throws Exception {
	    try {
	        UUID unidadeID = dto.getIdunidadeDoc();
	       UnidadeDoc unidadedoc = unidadeDocRepository.findById(unidadeID)
	                .orElseThrow(() -> new NoSuchElementException("Matricula não encontrada com o ID: " + unidadeID));
	        modelMapper.map(dto, unidadedoc);
	        List<GHO_SETOR> setor = obterSetoresPorIds(dto.getIdsSetores());

	        // Salva a matrícula separadamente
	        unidadedoc = unidadeDocRepository.save(unidadedoc);

	        // Adiciona os usuários à matrícula
	        unidadedoc.getGho_setor().addAll(setor);
	        unidadeDocRepository.save(unidadedoc);

	        return modelMapper.map(unidadedoc, GetUnidadeDTO.class);
	    } catch (Exception ex) {
	        // Trate a exceção de forma apropriada, registre logs, etc.
	        throw new Exception("Erro ao incluir Matriculas.", ex);
	    }
	}
    
    
    public GetUnidadeDTO adicionarSetorEmFuncaoDoc(PostUnidadeIncluirFuncaoDocDTO dto) throws Exception {
	    try {
	        UUID unidadeID = dto.getIdunidadeDoc();
	       UnidadeDoc unidadedoc = unidadeDocRepository.findById(unidadeID)
	                .orElseThrow(() -> new NoSuchElementException("Matricula não encontrada com o ID: " + unidadeID));
	        modelMapper.map(dto, unidadedoc);
	        List<FuncaoDoc> funcaodoc = obterFuncaodocPorIds(dto.getIdsFuncaodoc());

	        // Salva a matrícula separadamente
	        unidadedoc = unidadeDocRepository.save(unidadedoc);

	        // Adiciona os usuários à matrícula
	        unidadedoc.getFuncaodoc().addAll(funcaodoc);
	        unidadeDocRepository.save(unidadedoc);

	        return modelMapper.map(unidadedoc, GetUnidadeDTO.class);
	    } catch (Exception ex) {
	        // Trate a exceção de forma apropriada, registre logs, etc.
	        throw new Exception("Erro ao incluir Matriculas.", ex);
	    }
	}
    
    
    private List<GHO_SETOR> obterSetoresPorIds(List<UUID> setoresIds) {
	    List<GHO_SETOR> setores = new ArrayList<>();

	    if (setoresIds != null && !setoresIds.isEmpty()) {
	        for (UUID id : setoresIds) {
	        	GHO_SETOR gho_SETOR = isetor_GHORepository.findById(id)
	                    .orElseThrow(() -> new NoSuchElementException("Setor não encontrado com o ID: " + id));

	        	setores.add(gho_SETOR);
	        }
	    }

	    return setores;
	}
    
    private List<FuncaoDoc> obterFuncaodocPorIds(List<UUID> funcaodocIds) {
	    List<FuncaoDoc> funcoes = new ArrayList<>();

	    if (funcaodocIds != null && !funcaodocIds.isEmpty()) {
	        for (UUID id : funcaodocIds) {
	        	FuncaoDoc funcao = IFuncaoDocRepository.findById(id)
	                    .orElseThrow(() -> new NoSuchElementException("Setor não encontrado com o ID: " + id));

	        	funcoes.add(funcao);
	        }
	    }

	    return funcoes;
	}
    
    @Transactional
    public GetUnidadeDTO excluirSetor(UUID idUnidade, UUID idSetor) throws AccountNotFoundException {
    	UnidadeDoc unidadedoc =  unidadeDocRepository.findById(idUnidade)
                .orElseThrow();

        GHO_SETOR setor = isetor_GHORepository.findById(idSetor)
                .orElseThrow(() -> new AccountNotFoundException("Instrutor não encontrado"));

        unidadedoc.getGho_setor().remove(setor);
        unidadeDocRepository.save(unidadedoc);

        return modelMapper.map(unidadedoc, GetUnidadeDTO .class);
    }
    
    @Transactional
    public GetUnidadeDTO excluirFuncao(UUID idUnidade, UUID idFuncaodoc) throws AccountNotFoundException {
    	UnidadeDoc unidadedoc =  unidadeDocRepository.findById(idUnidade)
                .orElseThrow();

        FuncaoDoc funcaodoc = IFuncaoDocRepository.findById(idFuncaodoc)
                .orElseThrow(() -> new AccountNotFoundException("Instrutor não encontrado"));

        unidadedoc.getFuncaodoc().remove(funcaodoc);
        unidadeDocRepository.save(unidadedoc);

        return modelMapper.map(unidadedoc, GetUnidadeDTO .class);
    }

    
 

}

