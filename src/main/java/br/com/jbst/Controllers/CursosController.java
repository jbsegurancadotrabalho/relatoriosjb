package br.com.jbst.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetCursoDTO;
import br.com.jbst.services.CursosService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosService cursosService;

    @GetMapping("/{id}")
    public ResponseEntity<GetCursoDTO> buscarPorId(@PathVariable UUID id) {
        try {
            GetCursoDTO curso = cursosService.buscarPorId(id);
            return ResponseEntity.ok(curso);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetCursoDTO>> buscarTodosCursos() {
        List<GetCursoDTO> cursos = cursosService.buscarTodosCursos();
        return ResponseEntity.ok(cursos);
    }

}
