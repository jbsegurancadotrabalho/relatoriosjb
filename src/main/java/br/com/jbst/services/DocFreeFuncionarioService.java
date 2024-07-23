package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetDocFreeFuncionarioDTO;
import br.com.jbst.DTO.GetDocumentosFree;
import br.com.jbst.DTO.PostDocFreeFuncionarioDTO;
import br.com.jbst.DTO.PutDocFreeFuncionarioDTO;
import br.com.jbst.DTO.Funcionarios.Documentos.GetDocFreeFuncionarioDTO1;
import br.com.jbst.entities.DocFreeFuncionario;
import br.com.jbst.entities.DocumentosFree;
import br.com.jbst.entities.Funcionario;
import br.com.jbst.repositories.modulo1.IDocFreeFuncionarioRepository;
import br.com.jbst.repositories.modulo2.IFuncionarioRepository;

@Service
public class DocFreeFuncionarioService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
    private IDocFreeFuncionarioRepository idocFreeFuncionario;

	@Autowired
	private IFuncionarioRepository IFuncionarioRepository;
	
	 public GetDocFreeFuncionarioDTO criarDocumentosFree(PostDocFreeFuncionarioDTO dto) throws Exception {
	        try {
	            if (dto.getIdFuncionario() == null) {
	                throw new IllegalArgumentException("Empresa ID must not be null");
	            }

	            Funcionario funcionario = IFuncionarioRepository.findById(dto.getIdFuncionario())
	                    .orElseThrow(() -> new IllegalArgumentException("Empresa not found with ID: " + dto.getIdFuncionario()));

	            DocFreeFuncionario documentosFree = modelMapper.map(dto,  DocFreeFuncionario.class);
	            documentosFree.setIdDocFreeFuncionario(UUID.randomUUID());
	            
	            int numeroDocumento = gerarNumeroDocumento();
	            documentosFree.setNumerodocumentofree(numeroDocumento);
	            
	            documentosFree.setDataHoraCriacao(Instant.now());
	            documentosFree.setFuncionario(funcionario);
	            idocFreeFuncionario.save(documentosFree);
	            return modelMapper.map(documentosFree, GetDocFreeFuncionarioDTO.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }

	
	  private Integer gerarNumeroDocumento() {
	        Integer ultimoNumero = idocFreeFuncionario.findMaxDocumentosFree();
	        return (ultimoNumero == null ? 1 : ultimoNumero + 1);
	    }
	  
	  public GetDocFreeFuncionarioDTO editarDocumentosFree(PutDocFreeFuncionarioDTO dto) throws Exception {
	        try {
	            UUID idDocFreeFuncionario = dto.getIdDocFreeFuncionario();
	            // Verificar se o cenário com o ID fornecido existe
	            Optional<DocFreeFuncionario> optionalDocumentosFree = idocFreeFuncionario.findById(idDocFreeFuncionario);
	            if (!optionalDocumentosFree.isPresent()) {
	                throw new IllegalArgumentException("Cenário não encontrado com o ID fornecido: " + idDocFreeFuncionario);
	            }

	            // Obter o cenário a ser editado
	            DocFreeFuncionario documentosFree = optionalDocumentosFree.get();

	            // Mapear os campos atualizados do DTO para a entidade Cenario
	            modelMapper.map(dto, documentosFree);

	            // Definir o GHO Setor na entidade Cenario

	            // Salvar as alterações na entidade Cenario
	            DocFreeFuncionario updatedDocumentosFree = idocFreeFuncionario.save(documentosFree);

	            // Retornar o DTO mapeado da entidade atualizada
	            return modelMapper.map(updatedDocumentosFree, GetDocFreeFuncionarioDTO.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	  
	  public List<GetDocFreeFuncionarioDTO> consultarDocumentosFree() {
			List<DocFreeFuncionario> documentosfree = idocFreeFuncionario.findAll();
			return documentosfree.stream().map(documentofree -> modelMapper.map(documentofree, GetDocFreeFuncionarioDTO.class)).collect(Collectors.toList());
		}

		public GetDocFreeFuncionarioDTO getDocumentoById(UUID id) throws Exception {
			Optional<DocFreeFuncionario> documentoOptional = idocFreeFuncionario.findById(id);
			if (documentoOptional.isPresent()) {
				DocFreeFuncionario documentofree = documentoOptional.get();
				return modelMapper.map(documentofree, GetDocFreeFuncionarioDTO.class);
			} else {
				throw new Exception("Documento não encontrado com o ID: " + id);
			}
		}

		public GetDocFreeFuncionarioDTO1 getDocumentoByIdGerarRelatorio(UUID id) throws Exception {
			Optional<DocFreeFuncionario> documentoOptional = idocFreeFuncionario.findById(id);
			if (documentoOptional.isPresent()) {
				DocFreeFuncionario documentofree = documentoOptional.get();
				return modelMapper.map(documentofree, GetDocFreeFuncionarioDTO1.class);
			} else {
				throw new Exception("Documento não encontrado com o ID: " + id);
			}
		}
		
		
		public void excluirDocumentoFree(UUID id) throws Exception {
			Optional<DocFreeFuncionario> documentoOptional = idocFreeFuncionario.findById(id);
			if (documentoOptional.isPresent()) {
				idocFreeFuncionario.deleteById(id);
			} else {
				throw new Exception("Documento não encontrado com o ID: " + id);
			}
		}

	}
