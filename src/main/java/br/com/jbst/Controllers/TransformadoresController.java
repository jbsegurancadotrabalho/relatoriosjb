package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO2.GetTransformadoresDTO;
import br.com.jbst.DTO2.PostTransformadoresDTO;
import br.com.jbst.DTO2.PutTransformadoresDTO;
import br.com.jbst.services.TransformadoresService;

@RestController
@RequestMapping("/api/transformadores")
public class TransformadoresController {

    @Autowired
    private TransformadoresService transformadoresService;

    @PostMapping
    public ResponseEntity<GetTransformadoresDTO> CriarTransformador(@RequestBody PostTransformadoresDTO dto) throws Exception {
        GetTransformadoresDTO transformadorDTO = transformadoresService.criarTransformador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transformadorDTO);
    }

    @PutMapping
    public ResponseEntity<GetTransformadoresDTO> editarTransformador(@RequestBody PutTransformadoresDTO dto) throws Exception {
        GetTransformadoresDTO transformadorDTO = transformadoresService.editarTransformador(dto);
        return ResponseEntity.ok(transformadorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTransformadoresDTO> buscarPorId(@PathVariable UUID id) throws Exception {
        GetTransformadoresDTO transformadorDTO = transformadoresService.buscarPorId(id);
        return ResponseEntity.ok(transformadorDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetTransformadoresDTO>> buscarTodos() {
        List<GetTransformadoresDTO> transformadoresDTO = transformadoresService.buscarTodos();
        return ResponseEntity.ok(transformadoresDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTransformador(@PathVariable UUID id) throws Exception {
        transformadoresService.excluirTransformador(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
