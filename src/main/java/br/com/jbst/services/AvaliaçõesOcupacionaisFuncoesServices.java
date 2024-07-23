package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.DTO.PostAvaliaçõesOcupacionaisFuncoesIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.entities.AvaliaçõesOcupacionaisFuncoes;
import br.com.jbst.entities.PostAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.repositories.modulo1.IAvaliaçõesOcupacionaisFuncoesRepositories;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;

@Service
public class AvaliaçõesOcupacionaisFuncoesServices {

	@Autowired
	IAvaliaçõesOcupacionaisFuncoesRepositories iavaliaçõesOcupacionaisFuncoesRepositories;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IFuncaoEspecificaRepository IFuncaoEspecificaRepository;
	
	@Autowired
	IFuncaoDocRepository funcaoDocRepository;
	

	 
	 public GetAvaliaçõesOcupacionaisFuncoesDTO  criarAvaliaçõesOcupacionaisFuncoes(PostAvaliaçõesOcupacionaisFuncoesDTO  dto) {
	        // Mapear o DTO para a entidade 
		 AvaliaçõesOcupacionaisFuncoes avaliacoes = modelMapper.map(dto,  AvaliaçõesOcupacionaisFuncoes.class);
	        avaliacoes.setIdAvaliacoes_funcao(UUID.randomUUID());
			avaliacoes.setFuncaodoc(funcaoDocRepository.findById(dto.getIdFuncaoDoc()).orElse(null));
	        avaliacoes = iavaliaçõesOcupacionaisFuncoesRepositories.save(avaliacoes);
	        // Retornar o DTO mapeado
	        return modelMapper.map(avaliacoes, GetAvaliaçõesOcupacionaisFuncoesDTO.class);
	    }
	 
	 public GetAvaliaçõesOcupacionaisFuncoesDTO  criarAvaliaçõesOcupacionaisIncluirFuncoesEspecifica(PostAvaliaçõesOcupacionaisFuncoesIncluirFuncaoEspecificaDTO  dto) {
	        // Mapear o DTO para a entidade 
		 AvaliaçõesOcupacionaisFuncoes avaliacoes = modelMapper.map(dto,  AvaliaçõesOcupacionaisFuncoes.class);
	        avaliacoes.setIdAvaliacoes_funcao(UUID.randomUUID());
			avaliacoes.setFuncaoespecifica(IFuncaoEspecificaRepository.findById(dto.getIdFuncaoEspecifica()).orElse(null));
	        avaliacoes = iavaliaçõesOcupacionaisFuncoesRepositories.save(avaliacoes);
	        // Retornar o DTO mapeado
	        return modelMapper.map(avaliacoes, GetAvaliaçõesOcupacionaisFuncoesDTO.class);
	    }
	 public GetAvaliaçõesOcupacionaisFuncoesDTO  editarAvaliaçõesOcupacionaisFuncoes(PutAvaliaçõesOcupacionaisFuncoesDTO  dto) {
	        UUID IdAvaliacoes_funcao = dto.getIdAvaliacoes_funcao();
	        if (IdAvaliacoes_funcao== null) {
	            return null; // Retorna null se o ID não for fornecido
	        }

	        // Busca o perigo no banco de dados com base no ID fornecido no DTO
	        AvaliaçõesOcupacionaisFuncoes avaliacoes = iavaliaçõesOcupacionaisFuncoesRepositories.findById(IdAvaliacoes_funcao).orElse(null);
	        if (avaliacoes == null) {
	            return null;
	        }
	        avaliacoes.setNome_avaliacoes_funcao(dto.getNome_avaliacoes_funcao());
	        avaliacoes.setTipo_avaliacoes_funcao(dto.getTipo_avaliacoes_funcao());
	        avaliacoes.setDescricao_avaliacoes_funcao(dto.getDescricao_avaliacoes_funcao());
	        avaliacoes.setValor_obtido(dto.getValor_obtido());
	        avaliacoes.setLimite_de_tolerancia(dto.getLimite_de_tolerancia());
	        avaliacoes.setTempo_de_exposicao(dto.getTempo_de_exposicao());
	        avaliacoes.setMetodo(dto.getMetodo());
	        avaliacoes.setConclusao_da_exposicao(dto.getConclusao_da_exposicao());
	        avaliacoes.setEquipamento_de_medicao(dto.getEquipamento_de_medicao());
	        avaliacoes.setModelo(dto.getModelo());
	        avaliacoes.setSerie(dto.getSerie());
	        avaliacoes.setCalibracao(dto.getCalibracao());
	        AvaliaçõesOcupacionaisFuncoes avaliacao = iavaliaçõesOcupacionaisFuncoesRepositories.save(avaliacoes);
	        return modelMapper.map(avaliacao, GetAvaliaçõesOcupacionaisFuncoesDTO.class);
	    }
	 public List<GetAvaliaçõesOcupacionaisFuncoesDTO> buscarTodasAvaliaçõesOcupacionaisFuncoes() {
	        List<AvaliaçõesOcupacionaisFuncoes> avaliacoes = iavaliaçõesOcupacionaisFuncoesRepositories.findAll();
	        return avaliacoes.stream()
	                .map(avaliacao -> modelMapper.map(avaliacao, GetAvaliaçõesOcupacionaisFuncoesDTO.class))
	                .collect(Collectors.toList());
	    }

	    // Método para buscar uma avaliação ocupacional função pelo ID
	    public GetAvaliaçõesOcupacionaisFuncoesDTO buscarAvaliaçãoOcupacionalFunçãoPorId(UUID id) {
	        AvaliaçõesOcupacionaisFuncoes avaliacao = iavaliaçõesOcupacionaisFuncoesRepositories.findById(id).orElse(null);
	        if (avaliacao == null) {
	            return null;
	        }
	        return modelMapper.map(avaliacao, GetAvaliaçõesOcupacionaisFuncoesDTO.class);
	    }
	    
	    public GetAvaliaçõesOcupacionaisFuncoesDTO excluirAvaliaçãoOcupacionalFunçãoPorId(UUID id) throws Exception {
			try {
				// Verificar se o curso com o ID fornecido existe
				Optional<AvaliaçõesOcupacionaisFuncoes> optionalAvaliacao = iavaliaçõesOcupacionaisFuncoesRepositories.findById(id);
				if (!optionalAvaliacao.isPresent()) {
					throw new IllegalArgumentException("Avaliação não encontrada com o ID fornecido: " + id);
				}

				// Excluir o curso
				iavaliaçõesOcupacionaisFuncoesRepositories.deleteById(id);

				// Retornar o DTO mapeado da postura excluída
				return modelMapper.map(optionalAvaliacao.get(), GetAvaliaçõesOcupacionaisFuncoesDTO.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
}
