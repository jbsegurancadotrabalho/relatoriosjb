package br.com.jbst.services;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetCursos_CredenciadosDTO;
import br.com.jbst.DTO2.PostCursos_CredenciadosDTO;
import br.com.jbst.DTO2.PutCursos_CredenciadosDTO;
import br.com.jbst.entities.Cursos_Credenciados;
import br.com.jbst.repositories.modulo1.ICredenciadosRepository;
import br.com.jbst.repositories.modulo1.ICursoRepository;
import br.com.jbst.repositories.modulo1.ICursos_CredenciadosRepository;

@Service
public class CursosCredenciadosService {


    @Autowired
    private ICursoRepository icursosRepository;
    
    @Autowired
    private  ICursos_CredenciadosRepository icursosCredenciadosRepository;
    
	@Autowired
	ICredenciadosRepository icredenciadoRepository;
	
    @Autowired
    private ModelMapper modelMapper;

    public  GetCursos_CredenciadosDTO criarCursosCredenciados( PostCursos_CredenciadosDTO dto) {
    	Cursos_Credenciados cursos_credenciados = modelMapper.map(dto, Cursos_Credenciados.class);
        cursos_credenciados.setIdCursosCredenciados(UUID.randomUUID());
        cursos_credenciados.setCurso(icursosRepository.findById(dto.getIdcurso()).orElse(null));
        cursos_credenciados.setCredenciados( icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
        cursos_credenciados = icursosCredenciadosRepository.save(cursos_credenciados);
        return modelMapper.map(cursos_credenciados, GetCursos_CredenciadosDTO.class);
    }
    
    public GetCursos_CredenciadosDTO atualizarCursosCredenciados(PutCursos_CredenciadosDTO dto) {
        // Verifique se o DTO contém um ID para identificar o objeto a ser atualizado
        if (dto.getIdCursosCredenciados() == null) {
            throw new IllegalArgumentException("ID da AvaliacoesCredenciados não fornecido para atualização.");
        }

        Cursos_Credenciados cursoCredenciado = icursosCredenciadosRepository.findById(dto.getIdCursosCredenciados())
                .orElseThrow(() -> new RuntimeException("AvaliacoesCredenciados não encontrado com id: " + dto.getIdCursosCredenciados()));

        // Atualizando os campos com os dados do DTO
        modelMapper.map(dto, cursoCredenciado);
        
        // Atualizando as associações com outras entidades
        cursoCredenciado.setCurso(icursosRepository.findById(dto.getIdcurso()).orElse(null));
        cursoCredenciado.setCredenciados(icredenciadoRepository.findById(dto.getIdCredenciado()).orElse(null));
        
        // Salvando as alterações no banco de dados
        cursoCredenciado = icursosCredenciadosRepository.save(cursoCredenciado);

        // Mapeando a entidade atualizada de volta para DTO
        return modelMapper.map(cursoCredenciado, GetCursos_CredenciadosDTO.class);
    }
    public List<GetCursos_CredenciadosDTO> buscarTodosCursosCredenciados() {
        List<Cursos_Credenciados> cursosCredenciadosList = icursosCredenciadosRepository.findAll();
        return cursosCredenciadosList.stream()
                .map(c -> modelMapper.map(c, GetCursos_CredenciadosDTO.class))
                .collect(Collectors.toList());
    }

    public GetCursos_CredenciadosDTO buscarCursosCredenciadosPorId(UUID id) {
        Cursos_Credenciados cursoCredenciado = icursosCredenciadosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso Credenciado não encontrado com o ID: " + id));
        return modelMapper.map(cursoCredenciado, GetCursos_CredenciadosDTO.class);
    }

    public void excluirCursosCredenciadosPorId(UUID id) {
        // Verificar se o curso credenciado existe antes de excluí-lo
        if (!icursosCredenciadosRepository.existsById(id)) {
            throw new RuntimeException("Curso Credenciado não encontrado com o ID: " + id);
        }
        icursosCredenciadosRepository.deleteById(id);
    }

}