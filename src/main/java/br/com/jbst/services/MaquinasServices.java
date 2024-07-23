package br.com.jbst.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetMaquinasDTO;
import br.com.jbst.DTO.PostMaquinasDTO;
import br.com.jbst.DTO.PutMaquinasDTO;
import br.com.jbst.entities.Maquinas;
import br.com.jbst.repositories.modulo1.IMaquinasRepository;

@Service
public class MaquinasServices {

	@Autowired
	private IMaquinasRepository maquinasRepository;

	@Autowired
	private ModelMapper modelMapper;


	public ResponseEntity<GetMaquinasDTO> criarMaquinas(PostMaquinasDTO dto) {
	    try {
	        // Mapear o DTO para a entidade Maquinas
	        Maquinas maquinas = modelMapper.map(dto, Maquinas.class);

	        // Definir o ID da máquina manualmente
	        maquinas.setIdMaquina(UUID.randomUUID());

	        // Definir a categoria na entidade

	        // Definir o horário de criação
	        maquinas.setDataHoraCriacao(Instant.now());

	        // Salvar a entidade Maquinas
	        Maquinas maquinaSalva = maquinasRepository.save(maquinas);

	        // Retornar o DTO mapeado da entidade salva
	        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(maquinaSalva, GetMaquinasDTO.class));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}


	public GetMaquinasDTO editarMaquinas(PutMaquinasDTO dto) throws Exception {
		try {
			// Verificar se a máquina com o ID fornecido existe no banco de dados
			Maquinas maquinaExistente = maquinasRepository.findById(dto.getIdMaquina())
					.orElseThrow(() -> new Exception("Máquina não encontrada com o ID: " + dto.getIdMaquina()));

			// Mapear os dados do DTO para a entidade Maquinas
			Maquinas maquinas = modelMapper.map(dto, Maquinas.class);

			// Copiar o ID da máquina existente para a entidade mapeada
			maquinas.setIdMaquina(maquinaExistente.getIdMaquina());
	        maquinas.setDataHoraCriacao(Instant.now());

			// Salvar a máquina atualizada no banco de dados
			Maquinas maquinaAtualizada = maquinasRepository.save(maquinas);

			// Mapear a máquina atualizada para o DTO de retorno
			return modelMapper.map(maquinaAtualizada, GetMaquinasDTO.class);
		} catch (Exception e) {
			throw new Exception("Erro ao editar máquina: " + e.getMessage());
		}
	}

	public List<GetMaquinasDTO> consultarTodasMaquinas() {
		List<Maquinas> maquinasList = maquinasRepository.findAll();
		return maquinasList.stream().map(maquina -> modelMapper.map(maquina, GetMaquinasDTO.class))
				.collect(Collectors.toList());
	}

	public GetMaquinasDTO consultarMaquinaPorId(UUID idMaquina) throws Exception {
		Maquinas maquina = maquinasRepository.findById(idMaquina)
				.orElseThrow(() -> new Exception("Máquina não encontrada com o ID: " + idMaquina));
		return modelMapper.map(maquina, GetMaquinasDTO.class);
	}
	public GetMaquinasDTO excluirMaquina(UUID idMaquina) throws Exception {
        try {
            // Encontra a máquina pelo ID
            Maquinas maquinaExcluida = maquinasRepository.findById(idMaquina)
                    .orElseThrow(() -> new Exception("Máquina não encontrada com o ID: " + idMaquina));

            // Remove a máquina do banco de dados
            maquinasRepository.deleteById(idMaquina);

            // Retorna o DTO da máquina excluída
            return modelMapper.map(maquinaExcluida, GetMaquinasDTO.class);
        } catch (EmptyResultDataAccessException e) {
            // Se a máquina não for encontrada, lança uma exceção
            throw new Exception("Máquina não encontrada com o ID: " + idMaquina);
        } catch (Exception e) {
            // Qualquer outro erro durante a exclusão
            throw new Exception("Erro ao excluir máquina: " + e.getMessage());
        }
    }
}
