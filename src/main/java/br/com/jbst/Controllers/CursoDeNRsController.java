package br.com.jbst.Controllers;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetCursoDeNRsDTO;
import br.com.jbst.DTO.PostCursoDeNRsDTO;
import br.com.jbst.DTO.PostCursosIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutCursoDeNRsDTO;
import br.com.jbst.services.CursoDeNRsServices;

@RestController
@RequestMapping("/curso-de-nrs")
public class CursoDeNRsController {

    @Autowired
    private CursoDeNRsServices cursoDeNRsServices;

    @PostMapping
    public ResponseEntity<GetCursoDeNRsDTO> criarCursoDeNrs(@RequestBody PostCursoDeNRsDTO dto) {
        try {
            GetCursoDeNRsDTO cursoCriado = cursoDeNRsServices.criarCursoDeNrs(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoCriado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/incluir-funcao-especifica")
    public ResponseEntity<GetCursoDeNRsDTO> criarCursoIncluirFuncaoEspecifica(@RequestBody PostCursosIncluirFuncaoEspecificaDTO dto) {
        try {
            GetCursoDeNRsDTO cursoDeNrsDTO = cursoDeNRsServices.criarCursoIncluirFuncaoEspecifica(dto);
            return ResponseEntity.ok(cursoDeNrsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetCursoDeNRsDTO> editarCursoDeNrs(@RequestBody PutCursoDeNRsDTO dto) {
        try {
            GetCursoDeNRsDTO cursoEditado = cursoDeNRsServices.editarCursoDeNrs(dto);
            return ResponseEntity.ok(cursoEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetCursoDeNRsDTO> consultarCursoPorId(@PathVariable("id") UUID id) {
        try {
            GetCursoDeNRsDTO cursoConsultado = cursoDeNRsServices.consultarCursoPorId(id);
            return ResponseEntity.ok(cursoConsultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetCursoDeNRsDTO>> consultarTodosCursos() {
        try {
            List<GetCursoDeNRsDTO> cursos = cursoDeNRsServices.consultarTodosCursos();
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetCursoDeNRsDTO> excluirCursoPorId(@PathVariable("id") UUID id) {
        try {
            GetCursoDeNRsDTO cursoExcluido = cursoDeNRsServices.excluirCursoPorId(id);
            return ResponseEntity.ok(cursoExcluido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
