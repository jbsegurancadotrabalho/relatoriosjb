package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetAgendaDTO;
import br.com.jbst.DTO2.GetGeradorDTO;
import br.com.jbst.DTO2.PostGeradorDTO;
import br.com.jbst.DTO2.PutGeradorDTO;
import br.com.jbst.entities.Agenda;
import br.com.jbst.entities.LaudoEletrico.Gerador;
import br.com.jbst.repositories.modulo1.IGeradorRepository;
import br.com.jbst.repositories.modulo1.IUnidadeDocRepository;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;

@Service
public class GeradorService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IGeradorRepository igeradorRepository;

	@Autowired
	IInspecaoRepository inspecaoRepository;

	public GetGeradorDTO criarGerador(PostGeradorDTO dto) {
		try {
			Gerador gerador = modelMapper.map(dto, Gerador.class);
			gerador.setIdGerador(UUID.randomUUID());
			gerador.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
			Gerador geradores = igeradorRepository.save(gerador);
			return modelMapper.map(geradores, GetGeradorDTO.class);
		} catch (Exception e) {
			// Handle exception here
			// For example, log the error
			// logger.error("Failed to create gerador: " + e.getMessage(), e);
			throw new RuntimeException("Failed to create gerador", e);
		}
	}

	public GetGeradorDTO editarGerador(PutGeradorDTO dto) {
		try {
			Gerador gerador = modelMapper.map(dto, Gerador.class);
			gerador.setInspecao(inspecaoRepository.findById(dto.getIdInspecao()).orElse(null));
			Gerador geradores = igeradorRepository.save(gerador);
			return modelMapper.map(geradores, GetGeradorDTO.class);
		} catch (Exception e) {

			throw new RuntimeException("Falha ao criar Gerador", e);
		}
	}

	public void excluirGeradorPorId(UUID idGerador) throws NotFoundException {
		Optional<Gerador> optionalGerador = igeradorRepository.findById(idGerador);
		if (optionalGerador.isPresent()) {

			Gerador geradores = optionalGerador.get();
			igeradorRepository.delete(geradores);
		}

		else {
			throw new NotFoundException();
		}
	}

	public List<GetGeradorDTO> buscarTodosOsGeradores() {
		List<Gerador> gerador = igeradorRepository.findAll();
		return gerador.stream().map(geradores -> modelMapper.map(geradores, GetGeradorDTO.class))
				.collect(Collectors.toList());

	}

	public GetGeradorDTO consultarGeradorPorId(UUID idGerador) throws Exception {
		Optional<Gerador> optionalGerador = igeradorRepository.findById(idGerador);
		if (optionalGerador.isPresent()) {
			Gerador geradores = optionalGerador.get();
			return modelMapper.map(geradores, GetGeradorDTO.class);
		} else {
			throw new NotFoundException();
		}
	}

}
