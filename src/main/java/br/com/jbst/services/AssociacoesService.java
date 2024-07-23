package br.com.jbst.services;



import java.time.Instant;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCnaeDto;
import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenario1DTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenarioDTO;
import br.com.jbst.DTO.Cenarios.Documentos.PostAssociacoesCenarioDTO;
import br.com.jbst.DTO2.GetAssociacoesUnidadeDocDTO;
import br.com.jbst.DTO2.PostAssociacoesFuncionarioDTO;
import br.com.jbst.DTO2.PostAssociacoesUnidadeDocDTO;
import br.com.jbst.DTO2.PutAssociacoesFuncionarioDTO;
import br.com.jbst.DTO2.PutAssociacoesUnidadeDocDTO;
import br.com.jbst.dtos.relatorios.GetAssociacoesRelatoriosFuncionarioDTO;
import br.com.jbst.dtos.relatorios.GetAssociacoesRelatoriosUnidadesDTO;
import br.com.jbst.entities.Associacoes;
import br.com.jbst.entities.Cnae;
import br.com.jbst.repositories.modulo1.IAssociacoesRepository;
import br.com.jbst.repositories.modulo1.ICenarioRepository;
import br.com.jbst.repositories.modulo1.IRelatoriosRepository;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IFuncionarioRepository;

@Service
public class AssociacoesService {

	@Autowired
	IFuncionarioRepository IFuncionarioRepository;
	
	@Autowired
	IAssociacoesRepository iassociacoesRepository;
	
	@Autowired
	IUnidadeDocRepository iunidadeDocRepository;
	
	@Autowired
	IRelatoriosRepository irelatoriosRepository;
	

	
	@Autowired
	ICenarioRepository icenarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public GetAssociacoesUnidadeDocDTO criarAssociacoesUnidadeDoc(PostAssociacoesUnidadeDocDTO dto) {
	    // Gerar automaticamente o número da associação
	    Integer numeroAssociacao = gerarNumeroAssociacoes();

	    // Obter a data e hora atual
	    Instant dataHoraCriacao = Instant.now();

	    // Mapear os dados do DTO para a entidade Associacoes
	    Associacoes associacoes = modelMapper.map(dto, Associacoes.class);

	    // Define o ID da associação
	    associacoes.setIdAssociacoes(UUID.randomUUID());

	    // Define o número da associação
	    associacoes.setNumeroAssociacoes(numeroAssociacao);

	    // Define a data e hora de criação
	    associacoes.setDataHoraCriacao(Instant.now());

	    // Obtém a unidade doc e o relatório correspondentes aos IDs do DTO
	    associacoes.setUnidadedoc(iunidadeDocRepository.findById(dto.getIdunidadeDoc()).orElse(null));
	    associacoes.setRelatorios(irelatoriosRepository.findById(dto.getIdRelatorios()).orElse(null));

	    // Salva a associação no repositório
	    Associacoes associacaoSalva = iassociacoesRepository.save(associacoes);

	    // Mapear a entidade associacaoSalva para o DTO de resposta
	    return modelMapper.map(associacaoSalva, GetAssociacoesUnidadeDocDTO.class);
	}
	
	
	public GetAssociacaoCenarioDTO criarAssociacoesCenario(PostAssociacoesCenarioDTO dto) {
	    // Gerar automaticamente o número da associação
	    Integer numeroAssociacao = gerarNumeroAssociacoes();
	    // Obter a data e hora atual
	    Instant dataHoraCriacao = Instant.now();
	    // Mapear os dados do DTO para a entidade Associacoes
	    Associacoes associacoes = modelMapper.map(dto, Associacoes.class);
	    // Define o ID da associação
	    associacoes.setIdAssociacoes(UUID.randomUUID());
	    // Define o número da associação
	    associacoes.setNumeroAssociacoes(numeroAssociacao);
	    // Define a data e hora de criação
	    associacoes.setDataHoraCriacao(Instant.now());
	    // Obtém a unidade doc e o relatório correspondentes aos IDs do DTO
	    associacoes.setCenario(icenarioRepository.findById(dto.getIdCenario()).orElse(null));
	    associacoes.setRelatorios(irelatoriosRepository.findById(dto.getIdRelatorios()).orElse(null));
	    // Salva a associação no repositório
	    Associacoes associacaoSalva = iassociacoesRepository.save(associacoes);
	    // Mapear a entidade associacaoSalva para o DTO de resposta
	    return modelMapper.map(associacaoSalva, GetAssociacaoCenarioDTO.class);
	}

	
	public GetAssociacoesUnidadeDocDTO editarAssociacoesUnidadeDoc(PutAssociacoesUnidadeDocDTO dto) {
	    try {
	        // Map the DTO to the entity
	        Associacoes associacoes = modelMapper.map(dto, Associacoes.class);

	        // Retrieve the existing association from the repository
	        Associacoes associacaoExistente = iassociacoesRepository.findById(dto.getIdAssociacoes())
	                .orElseThrow(() -> new IllegalArgumentException("Associação não encontrada"));

	        // Update the existing association with new data from the DTO
	        associacaoExistente.setVenda(dto.getVenda());
	        associacaoExistente.setValor(dto.getValor());
	        associacaoExistente.setStatus(dto.getStatus());
	        associacaoExistente.setTipo_de_pagamento(dto.getTipo_de_pagamento());
	        associacaoExistente.setObservacoes(dto.getObservacoes());

	        // Save the updated association
	        Associacoes associacaoAtualizada = iassociacoesRepository.save(associacaoExistente);

	        // Map the updated entity back to the DTO
	        return modelMapper.map(associacaoAtualizada, GetAssociacoesUnidadeDocDTO.class);
	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao editar Associação: " + e.getMessage(), e);
	    }
	}

	
	
	public GetAssociacoesUnidadeDocDTO criarAssociacoesFuncionarios(PostAssociacoesFuncionarioDTO dto) {
	    // Gerar automaticamente o número da associação
	    Integer numeroAssociacao = gerarNumeroAssociacoes();

	    // Obter a data e hora atual
	    Instant dataHoraCriacao = Instant.now();

	    // Mapear os dados do DTO para a entidade Associacoes
	    Associacoes associacoes = modelMapper.map(dto, Associacoes.class);

	    // Define o ID da associação
	    associacoes.setIdAssociacoes(UUID.randomUUID());

	    // Define o número da associação
	    associacoes.setNumeroAssociacoes(numeroAssociacao);

	    // Define a data e hora de criação
	    associacoes.setDataHoraCriacao(dataHoraCriacao);

	    // Obtém a unidade doc e o relatório correspondentes aos IDs do DTO
	    associacoes.setFuncionario(IFuncionarioRepository.findById(dto.getIdFuncionario()).orElse(null));
	    associacoes.setRelatorios(irelatoriosRepository.findById(dto.getIdRelatorios()).orElse(null));

	    // Salva a associação no repositório
	    Associacoes associacaoSalva = iassociacoesRepository.save(associacoes);

	    // Mapear a entidade associacaoSalva para o DTO de resposta
	    return modelMapper.map(associacaoSalva, GetAssociacoesUnidadeDocDTO.class);
	}
	
	public GetAssociacoesUnidadeDocDTO editarAssociacoesFuncionarios(PutAssociacoesFuncionarioDTO dto) {
        // Verificar se o DTO contém o ID da associação
        if (dto.getIdAssociacoes() == null) {
            // Se o ID da associação não estiver presente no DTO, retornar null
            return null;
        }

        // Encontrar a associação a ser editada pelo ID fornecido no DTO
        Associacoes associacoes = iassociacoesRepository.findById(dto.getIdAssociacoes()).orElse(null);
        if (associacoes == null) {
            // Se a associação não for encontrada, retornar null
            return null;
        }

        // Mapear os dados do DTO para a entidade Associacoes
        modelMapper.map(dto, associacoes);

        // Verificar se os IDs de Funcionário e Relatórios no DTO são válidos e atualizá-los
        if (dto.getIdFuncionario() != null) {
            associacoes.setFuncionario(IFuncionarioRepository.findById(dto.getIdFuncionario()).orElse(null));
        }

        if (dto.getIdRelatorios() != null) {
            associacoes.setRelatorios(irelatoriosRepository.findById(dto.getIdRelatorios()).orElse(null));
        }

        // Salvar as alterações no banco de dados
        Associacoes associacaoAtualizada = iassociacoesRepository.save(associacoes);

        // Mapear a entidade associacaoAtualizada para o DTO de resposta
        return modelMapper.map(associacaoAtualizada, GetAssociacoesUnidadeDocDTO.class);
    }
	
	public List<GetAssociacoesRelatoriosUnidadesDTO> buscarTodasAssociacoesUnidade() throws Exception {
        List<Associacoes> associacoes = iassociacoesRepository.findAll();
        return mapToDTOListUnidade(associacoes);
    }

    private List<GetAssociacoesRelatoriosUnidadesDTO> mapToDTOListUnidade(List<Associacoes> matriculas) {
        return modelMapper.map(matriculas, new TypeToken<List<GetAssociacoesUnidadeDocDTO>>() {}.getType());
    }
    
    
	
	public List<GetAssociacoesRelatoriosFuncionarioDTO> buscarTodasAssociacoesFuncionario() throws Exception {
        List<Associacoes> associacoes = iassociacoesRepository.findAll();
        return mapToDTOList(associacoes);
    }

    private List<GetAssociacoesRelatoriosFuncionarioDTO> mapToDTOList(List<Associacoes> associacoes) {
        return modelMapper.map(associacoes, new TypeToken<List<GetAssociacoesRelatoriosFuncionarioDTO>>() {}.getType());
    }
	


	    public GetAssociacoesUnidadeDocDTO buscarAssociacoesPorId(UUID id) {
	        Associacoes associacao = iassociacoesRepository.findById(id).orElse(null);
	        if (associacao != null) {
	            return modelMapper.map(associacao, GetAssociacoesUnidadeDocDTO.class);
	        } else {
	            return null;
	        }
	    }
	    
	    public GetAssociacaoCenarioDTO buscarAssociacaoCenarioPorId(UUID id) {
	        Optional<Associacoes> optionalAssociacao = iassociacoesRepository.findById(id);
	        if (optionalAssociacao.isPresent()) {
	            return modelMapper.map(optionalAssociacao.get(), GetAssociacaoCenarioDTO.class);
	        } else {
	            throw new RuntimeException("Associação não encontrada para o ID: " + id);
	        }
	    }
	    
	    public List< GetAssociacaoCenario1DTO> consultarAssociacoesDosCenarios() {
			List<Associacoes> associacoes = iassociacoesRepository.findAll();
			return associacoes.stream().map(associacao -> modelMapper.map(associacao, GetAssociacaoCenario1DTO.class)).collect(Collectors.toList());
		}
 
	
	    public void excluirAssociacoesPorId(UUID id) {
	        // Verifica se a associação existe antes de excluir
	        if (iassociacoesRepository.existsById(id)) {
	            iassociacoesRepository.deleteById(id);
	        }
	    }
	    
		

private Integer gerarNumeroAssociacoes() {
			Integer ultimoNumero = iassociacoesRepository.findMaxNumeroAssociacao();
			if (ultimoNumero == null) {
				ultimoNumero = 0;
			}
			return ultimoNumero + 1;
		}
}
