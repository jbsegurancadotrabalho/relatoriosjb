package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetDocumentosFree;
import br.com.jbst.DTO.PostDocumentosFree;
import br.com.jbst.DTO.PutDocumentosFree;
import br.com.jbst.DTO.Unidades.Funcionarios.GetFuncionarioRelatorioDTO;
import br.com.jbst.entities.DocumentosFree;
import br.com.jbst.entities.Empresa;
import br.com.jbst.entities.Funcionario;
import br.com.jbst.repositories.modulo1.IDocumentosFreeRepositories;
import br.com.jbst.repositories.modulo1.IEmpresaRepository;

@Service
public class DocumentosFreeService {


    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private IDocumentosFreeRepositories idocumentosFreeRepositories;
    
    @Autowired
    private IEmpresaRepository iempresaRepository;
    
    public GetDocumentosFree criarDocumentosFree(PostDocumentosFree dto) throws Exception {
        try {
            if (dto.getIdEmpresa() == null) {
                throw new IllegalArgumentException("Empresa ID must not be null");
            }

            Empresa empresa = iempresaRepository.findById(dto.getIdEmpresa())
                    .orElseThrow(() -> new IllegalArgumentException("Empresa not found with ID: " + dto.getIdEmpresa()));

            DocumentosFree documentosFree = modelMapper.map(dto, DocumentosFree.class);
            documentosFree.setIdDocumentosFree(UUID.randomUUID());
            
            int numeroDocumento = gerarNumeroDocumento();
            documentosFree.setNumerodocumentofree(numeroDocumento);
            
            documentosFree.setDataHoraCriacao(Instant.now());
            documentosFree.setEmpresa(empresa);
            idocumentosFreeRepositories.save(documentosFree);
            return modelMapper.map(documentosFree, GetDocumentosFree.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Integer gerarNumeroDocumento() {
        Integer ultimoNumero = idocumentosFreeRepositories.findMaxDocumentosFree();
        return (ultimoNumero == null ? 1 : ultimoNumero + 1);
    }


  

	public GetDocumentosFree editarDocumentosFree(PutDocumentosFree dto) throws Exception {
        try {
            UUID idDocumentosFree = dto.getIdDocumentosFree();
            // Verificar se o cenário com o ID fornecido existe
            Optional<DocumentosFree> optionalDocumentosFree = idocumentosFreeRepositories.findById(idDocumentosFree);
            if (!optionalDocumentosFree.isPresent()) {
                throw new IllegalArgumentException("Cenário não encontrado com o ID fornecido: " + idDocumentosFree);
            }

            // Obter o cenário a ser editado
            DocumentosFree documentosFree = optionalDocumentosFree.get();

            // Mapear os campos atualizados do DTO para a entidade Cenario
            modelMapper.map(dto, documentosFree);

            // Definir o GHO Setor na entidade Cenario

            // Salvar as alterações na entidade Cenario
            DocumentosFree updatedDocumentosFree = idocumentosFreeRepositories.save(documentosFree);

            // Retornar o DTO mapeado da entidade atualizada
            return modelMapper.map(updatedDocumentosFree, GetDocumentosFree.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
	

	public List<GetDocumentosFree> consultarDocumentosFree() {
		List<DocumentosFree> documentosfree = idocumentosFreeRepositories.findAll();
		return documentosfree.stream().map(documentofree -> modelMapper.map(documentofree, GetDocumentosFree.class)).collect(Collectors.toList());
	}


	
	public GetDocumentosFree getDocumentoById(UUID id) {
		Optional<DocumentosFree> documentoOptional = idocumentosFreeRepositories.findById(id);
		if (documentoOptional.isPresent()) {
            return modelMapper.map(documentoOptional.get(), GetDocumentosFree.class);
        } else {
            throw new RuntimeException("Funcionário não encontrado para o ID: " + id);
        }
    }


	public void excluirDocumentoFree(UUID id) throws Exception {
		Optional<DocumentosFree> documentoOptional = idocumentosFreeRepositories.findById(id);
		if (documentoOptional.isPresent()) {
			idocumentosFreeRepositories.deleteById(id);
		} else {
			throw new Exception("Documento não encontrado com o ID: " + id);
		}
	}

}
