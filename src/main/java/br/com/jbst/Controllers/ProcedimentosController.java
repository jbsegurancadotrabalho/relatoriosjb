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

import br.com.jbst.DTO2.GetProcedimentosDTO;
import br.com.jbst.DTO2.PostProcedimentosDTO;
import br.com.jbst.DTO2.PutProcedimentosDTO;
import br.com.jbst.services.ProcedimentosService;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentosController {

    @Autowired
    private ProcedimentosService procedimentosService;

    @PostMapping
    public ResponseEntity<GetProcedimentosDTO> criarProcedimentos(@RequestBody PostProcedimentosDTO dto) {
        try {
            GetProcedimentosDTO procedimentosCriado = procedimentosService.criarProcedimentos(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(procedimentosCriado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetProcedimentosDTO> editarProcedimentos(@RequestBody PutProcedimentosDTO dto) {
        try {
            GetProcedimentosDTO procedimentosEditado = procedimentosService.editarProcedimentos(dto);
            return ResponseEntity.ok(procedimentosEditado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProcedimentosDTO> buscarProcedimentosPorId(@PathVariable UUID id) {
        try {
            GetProcedimentosDTO procedimentos = procedimentosService.buscarProcedimentosPorId(id);
            return ResponseEntity.ok(procedimentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetProcedimentosDTO>> buscarTodosProcedimentos() {
        List<GetProcedimentosDTO> todosProcedimentos = procedimentosService.buscarTodosProcedimentos();
        return ResponseEntity.ok(todosProcedimentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProcedimentosPorId(@PathVariable UUID id) {
        try {
            procedimentosService.excluirProcedimentosPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

