package br.com.jbst.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCursoDeNRsDTO;
import br.com.jbst.DTO.PostCursoDeNRsDTO;
import br.com.jbst.DTO.PostCursosIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutCursoDeNRsDTO;
import br.com.jbst.entities.CursoDeNrs;
import br.com.jbst.repositories.modulo1.ICursoDeNrsRepository;
import br.com.jbst.repositories.modulo1.IFuncaoEspecificaRepository;
import br.com.jbst.repositories.modulo2.IFuncaoDocRepository;


@Service
public class CursoDeNRsServices {

	@Autowired
	ICursoDeNrsRepository icursoDeNrsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IFuncaoDocRepository funcaoDocRepository;
	
	@Autowired
	IFuncaoEspecificaRepository IFuncaoEspecificaRepository;

	public GetCursoDeNRsDTO criarCursoDeNrs(PostCursoDeNRsDTO dto) throws Exception {
		try {
			// Mapear o DTO para a entidade CursoDeNrs
			CursoDeNrs cursoDeNrs = modelMapper.map(dto, CursoDeNrs.class);

			// Definir o ID do curso manualmente
			cursoDeNrs.setIdCursoNrs(UUID.randomUUID());

			cursoDeNrs.setFuncaodoc(funcaoDocRepository.findById(dto.getIdFuncaoDoc()).orElse(null));

			// Salvar a entidade CursoDeNrs
			CursoDeNrs savedCursoDeNrs = icursoDeNrsRepository.save(cursoDeNrs);

			// Retornar o DTO mapeado da entidade salva
			return modelMapper.map(savedCursoDeNrs, GetCursoDeNRsDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public GetCursoDeNRsDTO criarCursoIncluirFuncaoEspecifica(PostCursosIncluirFuncaoEspecificaDTO dto) throws Exception {
		try {
			// Mapear o DTO para a entidade CursoDeNrs
			CursoDeNrs cursoDeNrs = modelMapper.map(dto, CursoDeNrs.class);

			// Definir o ID do curso manualmente
			cursoDeNrs.setIdCursoNrs(UUID.randomUUID());

			cursoDeNrs.setFuncaoespecifica(IFuncaoEspecificaRepository.findById(dto.getIdFuncaoEspecifica()).orElse(null));

			// Salvar a entidade CursoDeNrs
			CursoDeNrs savedCursoDeNrs = icursoDeNrsRepository.save(cursoDeNrs);

			// Retornar o DTO mapeado da entidade salva
			return modelMapper.map(savedCursoDeNrs, GetCursoDeNRsDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public GetCursoDeNRsDTO editarCursoDeNrs(PutCursoDeNRsDTO dto) throws Exception {
        // Verifica se o DTO contém um ID válido
        UUID idCursoNrs = dto.getIdCursoNrs();
        if (idCursoNrs == null) {
            return null; // Retorna null se o ID não for fornecido
        }

        // Busca o perigo no banco de dados com base no ID fornecido no DTO
        CursoDeNrs curso = icursoDeNrsRepository.findById(idCursoNrs).orElse(null);

        // Se o perigo não for encontrado, retorna null
        if (curso == null) {
            return null;
        }

        curso.setNomeCurso(dto.getNomeCurso());

        curso.setDescricaoCurso(dto.getDescricaoCurso());

        // Salva o perigo atualizado no banco de dados
        CursoDeNrs cursos  = icursoDeNrsRepository.save(curso);

        // Retorna o perigo atualizado convertido para DTO
        return modelMapper.map(cursos, GetCursoDeNRsDTO.class);
    }
 
	

    
	public GetCursoDeNRsDTO consultarCursoPorId(UUID idCursoNrs) throws Exception {
		try {
			// Buscar o curso pelo ID
			Optional<CursoDeNrs> optionalCursoDeNrs = icursoDeNrsRepository.findById(idCursoNrs);
			if (!optionalCursoDeNrs.isPresent()) {
				throw new IllegalArgumentException("Curso não encontrado com o ID fornecido: " + idCursoNrs);
			}

			// Retornar o DTO mapeado da entidade encontrada
			return modelMapper.map(optionalCursoDeNrs.get(), GetCursoDeNRsDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<GetCursoDeNRsDTO> consultarTodosCursos() throws Exception {
		try {
			// Buscar todos os cursos
			List<CursoDeNrs> cursos = icursoDeNrsRepository.findAll();

			// Mapear os cursos para DTOs
			List<GetCursoDeNRsDTO> dtos = cursos.stream().map(curso -> modelMapper.map(curso, GetCursoDeNRsDTO.class))
					.collect(Collectors.toList());

			return dtos;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public GetCursoDeNRsDTO excluirCursoPorId(UUID idCursoNrs) throws Exception {
		try {
			// Verificar se o curso com o ID fornecido existe
			Optional<CursoDeNrs> optionalCursoDeNrs = icursoDeNrsRepository.findById(idCursoNrs);
			if (!optionalCursoDeNrs.isPresent()) {
				throw new IllegalArgumentException("Curso não encontrado com o ID fornecido: " + idCursoNrs);
			}

			// Excluir o curso
			icursoDeNrsRepository.deleteById(idCursoNrs);

			// Retornar o DTO mapeado da postura excluída
			return modelMapper.map(optionalCursoDeNrs.get(), GetCursoDeNRsDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
