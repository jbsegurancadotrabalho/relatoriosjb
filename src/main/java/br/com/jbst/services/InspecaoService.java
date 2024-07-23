package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetInspecaoDTO;
import br.com.jbst.DTO2.PostInspecaoDTO;
import br.com.jbst.DTO2.PutInspecaoDTO;
import br.com.jbst.entities.LaudoEletrico.Inspecao;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;

@Service
public class InspecaoService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IInspecaoRepository inspecaoRepository;

	@Autowired
	IUnidadeDocRepository iunidadedocrepository;

	public GetInspecaoDTO criarInspecao(PostInspecaoDTO dto) {
		try {

			// Gerar automaticamente o número da agenda
			Integer numeroInspecao = gerarNumeroInspecao();

			Inspecao inspecao = modelMapper.map(dto, Inspecao.class);
			inspecao.setIdInspecao(UUID.randomUUID());
			inspecao.setNumeroinspecao(numeroInspecao);
			inspecao.setDia_horacriacao(Instant.now());
			inspecao.setUnidadedoc(iunidadedocrepository.findById(dto.getIdunidadeDoc()).orElse(null));
			Inspecao inspecoes = inspecaoRepository.save(inspecao);
			return modelMapper.map(inspecoes, GetInspecaoDTO.class);
		} catch (Exception e) {
			// Lidar com a exceção aqui
			e.printStackTrace(); // Apenas para mostrar o erro no console
			throw new RuntimeException("Ocorreu um erro ao criar a inspeção: " + e.getMessage());
		}
	}

	private Integer gerarNumeroInspecao() {
		Integer ultimoNumero = inspecaoRepository.findMaxNumeroInspecao();
		if (ultimoNumero == null) {
			ultimoNumero = 0;
		}
		return ultimoNumero + 1;
	}

	public GetInspecaoDTO editarInspecao(PutInspecaoDTO dto) {
		try {
			// Verificar se o ID da inspecao está presente no DTO
			if (dto.getIdInspecao() == null) {
				throw new IllegalArgumentException("O ID da inspecao deve ser fornecido para editar a inspecao.");
			}

			// Buscar a inspecao no banco de dados pelo ID
			Optional<Inspecao> inspecaoOptional = inspecaoRepository.findById(dto.getIdInspecao());
			if (inspecaoOptional.isEmpty()) {
				throw new Exception("Inspecao não encontrada com o ID: " + dto.getIdInspecao());
			}

			// Obter a inspecao existente do banco de dados
			Inspecao inspecao = inspecaoOptional.get();

			// Mapear os dados do DTO para a entidade Inspecao
			modelMapper.map(dto, inspecao);

			// Atualizar a data e hora da criação
			inspecao.setDia_horacriacao(Instant.now());

			// Salvar a inspecao atualizada no banco de dados
			Inspecao inspecoes = inspecaoRepository.save(inspecao);

			// Mapear a entidade inspecao para o DTO de resposta
			return modelMapper.map(inspecoes, GetInspecaoDTO.class);
		} catch (Exception e) {
			// Lidar com a exceção aqui
			e.printStackTrace(); // Apenas para mostrar o erro no console
			throw new RuntimeException("Ocorreu um erro ao editar a inspeção: " + e.getMessage());
		}
	}

	public GetInspecaoDTO consultarInspecaoPorId(UUID idInspecao) throws Exception {
		Optional<Inspecao> inspecao = inspecaoRepository.findById(idInspecao);
		if (inspecao.isPresent()) {
			Inspecao inspecoes = inspecao.get();
			return modelMapper.map(inspecoes, GetInspecaoDTO.class);
		} else {
			throw new NotFoundException();
		}
	}

	public List<GetInspecaoDTO> consultarTodasAsInspecoes() {
		List<Inspecao> inspecoes = inspecaoRepository.findAll();
		return inspecoes.stream().map(inspecao -> modelMapper.map(inspecao, GetInspecaoDTO.class))
				.collect(Collectors.toList());
	}

	public void ExcluirInspecaoPorId(UUID idInspecao) throws NotFoundException {
	
		Optional<Inspecao> inspecao = inspecaoRepository.findById(idInspecao);
	    if (inspecao.isPresent()) {
	    	
	    	Inspecao inspecoes = inspecao.get();
	    	inspecaoRepository.delete(inspecoes);	 
	    	
	    } else {
			throw new NotFoundException();
	    }
	}
}