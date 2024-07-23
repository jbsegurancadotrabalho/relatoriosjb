	package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.entities.LaudoEletrico.Tomadas;
import br.com.jbst.repositories.modulo1.IAterramentoRepository;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.DTO2.GetAterramentoDTO;
import br.com.jbst.DTO2.GetTomadasDTO;
import br.com.jbst.DTO2.PostAterramentoDTO;
import br.com.jbst.DTO2.PostTomadasDTO;
import br.com.jbst.DTO2.PutAterramentoDTO;
import br.com.jbst.DTO2.PutTomadasDTO;
import br.com.jbst.entities.LaudoEletrico.Aterramento;

@Service
public class AterramentoService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IAterramentoRepository iaterramentoRepository;

	@Autowired
	IInspecaoRepository inspecaoRepository;

	public GetAterramentoDTO criarAterramento(PostAterramentoDTO dto) {
		try {
			Aterramento aterramento = modelMapper.map(dto, Aterramento.class);
			aterramento.setIdAterramento(UUID.randomUUID());
			aterramento.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
			Aterramento aterramentos = iaterramentoRepository.save(aterramento);
			return modelMapper.map(aterramentos, GetAterramentoDTO.class);
		} catch (Exception e) {
			// Handle exception here
			throw new RuntimeException("Falha ao criar tomadas", e);
		}
	}

	public GetAterramentoDTO editarAterramento(PutAterramentoDTO dto) {
		try {
			Aterramento aterramento = modelMapper.map(dto, Aterramento.class);
			aterramento.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
			Aterramento aterramentos = iaterramentoRepository.save(aterramento);
			return modelMapper.map(aterramentos, GetAterramentoDTO.class);
		} catch (Exception e) {
			// Handle exception here
			throw new RuntimeException("Falha ao criar tomadas", e);
		}
	}

	public GetAterramentoDTO consultarAterramentoPorId(UUID id) {
		Aterramento aterramento = iaterramentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aterramento não encontrado com o ID: " + id));
		return modelMapper.map(aterramento, GetAterramentoDTO.class);
	}

	public List<GetAterramentoDTO> consultarTodosOsAterramentos() {
		List<Aterramento> aterramentosList = iaterramentoRepository.findAll();
		return aterramentosList.stream().map(aterramento -> modelMapper.map(aterramento, GetAterramentoDTO.class))
				.collect(Collectors.toList());
	}

	public void excluirAterramentoPorId(UUID id) {
		iaterramentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Aterramento não encontrado com o ID: " + id));
		iaterramentoRepository.deleteById(id);
	}
}
