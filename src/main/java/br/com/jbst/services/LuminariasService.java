package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetLuminariasDTO;
import br.com.jbst.DTO2.GetQuadros_MedicoesDTO;
import br.com.jbst.DTO2.PostLuminariasDTO;
import br.com.jbst.DTO2.PutLuminariasDTO;
import br.com.jbst.entities.LaudoEletrico.Luminarias;
import br.com.jbst.entities.LaudoEletrico.Quadros_Medicoes;
import br.com.jbst.repositories.modulo2.IInspecaoRepository;
import br.com.jbst.repositories.modulo2.ILuminariasRepository;

@Service
public class LuminariasService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ILuminariasRepository iluminariasRepository;

	@Autowired
	IInspecaoRepository inspecaoRepository;

	public GetLuminariasDTO criarLuminarias(PostLuminariasDTO dto) {
		try {
			Luminarias luminarias = modelMapper.map(dto, Luminarias.class);
			luminarias.setIdLuminarias(UUID.randomUUID());
			luminarias.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));
			Luminarias luminaria = iluminariasRepository.save(luminarias);
			return modelMapper.map(luminaria, GetLuminariasDTO.class);
		} catch (Exception e) {
			// Tratar a exceção aqui
			e.printStackTrace(); // Isso é apenas para mostrar o erro no console, você pode lidar com ele de
									// outra forma
			throw new RuntimeException("Ocorreu um erro ao criar Luminarias: " + e.getMessage());
		}
	}

	public GetLuminariasDTO editarLuminarias(PutLuminariasDTO dto) {
		try {
			// Verificar se o quadro de medições com o ID fornecido existe
			if (!iluminariasRepository.existsById(dto.getIdLuminarias())) {
				throw new Exception("A Luminarias com o ID fornecido não existe.");
			}

			// Buscar o quadro de medições pelo ID
			Luminarias luminarias = iluminariasRepository.findById(dto.getIdLuminarias())
					.orElseThrow(() -> new Exception("Luminárias não encontrada com o ID fornecido."));

			// Mapear os novos dados do DTO para o quadro de medições existente
			modelMapper.map(dto, luminarias);

			// Associar a inspeção ao quadro de medições
			luminarias.setInspecao(inspecaoRepository.getById(dto.getIdInspecao()));

			// Salvar o quadro de medições atualizado no banco de dados
			Luminarias luminaria = iluminariasRepository.save(luminarias);

			// Mapear o quadro de medições atualizado para o DTO de resposta
			return modelMapper.map(luminaria, GetLuminariasDTO.class);
		} catch (Exception e) {
			// Tratar a exceção aqui
			e.printStackTrace(); // Isso é apenas para mostrar o erro no console, você pode lidar com ele de
									// outra forma
			throw new RuntimeException("Ocorreu um erro ao editar os quadros de medições: " + e.getMessage());
		}
	}

	// Método para buscar quadro de medições por ID
	public GetLuminariasDTO buscarLuminariasPorId(UUID id) throws Exception {
		Optional<Luminarias> luminariasOptional = iluminariasRepository.findById(id);
		if (luminariasOptional.isPresent()) {
			Luminarias luminarias = luminariasOptional.get();
			return modelMapper.map(luminarias, GetLuminariasDTO.class);
		} else {
			throw new Exception("Luminarias não encontrada com o ID fornecido.");
		}
	}

	// Método para buscar todas as medições de quadros
	public List<GetLuminariasDTO> buscarTodasLuminarias() {
		List<Luminarias> todasLuminarias = iluminariasRepository.findAll();
		return todasLuminarias.stream().map(luminarias -> modelMapper.map(luminarias, GetLuminariasDTO.class))
				.collect(Collectors.toList());

	}
	
	 public void excluirLuminariasPorId(UUID id) throws Exception {
         Optional<Luminarias> luminariasOptional = iluminariasRepository.findById(id);
         if (luminariasOptional.isPresent()) {
             iluminariasRepository.delete(luminariasOptional.get());
         } else {
             throw new Exception("Luminarias não encontrada com o ID fornecido.");
         }
     }
}
