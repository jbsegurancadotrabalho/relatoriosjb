package br.com.jbst.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.GetCursoDTO;
import br.com.jbst.entities.Curso;
import br.com.jbst.repositories.modulo1.ICursoRepository;

@Service
public class CursosService {

    @Autowired
    private ICursoRepository cursosRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetCursoDTO buscarPorId(UUID id) {
        Curso curso = cursosRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Curso não encontrado com o ID: " + id));
        return modelMapper.map(curso, GetCursoDTO.class);
    }

    public List<GetCursoDTO> buscarTodosCursos() {
        List<Curso> cursosList = cursosRepository.findAll();
        return cursosList.stream()
                .map(curso -> modelMapper.map(curso, GetCursoDTO.class))
                .collect(Collectors.toList());
    }

    // Outros métodos do serviço, como salvar, editar e excluir cursos, podem ser adicionados aqui
}
