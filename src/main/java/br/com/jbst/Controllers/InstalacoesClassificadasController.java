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

import br.com.jbst.DTO2.GetInstalacoesClassificadasDTO;
import br.com.jbst.DTO2.PostInstalacoesClassificadasDTO;
import br.com.jbst.DTO2.PutInstalacoesClassificadasDTO;
import br.com.jbst.services.InstalacoesClassificadasService;

@RestController
@RequestMapping("/instalacoes-classificadas")
public class InstalacoesClassificadasController {

    @Autowired
    private InstalacoesClassificadasService instalacoesClassificadasService;

    @PostMapping
    public ResponseEntity<GetInstalacoesClassificadasDTO> criarInstalacoesClassificadas(@RequestBody PostInstalacoesClassificadasDTO dto) {
        try {
            GetInstalacoesClassificadasDTO instalacoesClassificadasCriada = instalacoesClassificadasService.criarInstalacoesClassificadas(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(instalacoesClassificadasCriada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetInstalacoesClassificadasDTO> EditarInstalacoesClassificadas(@RequestBody PutInstalacoesClassificadasDTO dto) {
        try {
            GetInstalacoesClassificadasDTO instalacoesClassificadasEditada = instalacoesClassificadasService.editarInstalacoesClassificadas(dto);
            return ResponseEntity.ok(instalacoesClassificadasEditada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetInstalacoesClassificadasDTO> buscarInstalacoesClassificadasPorId(@PathVariable UUID id) {
        try {
            GetInstalacoesClassificadasDTO instalacoesClassificadas = instalacoesClassificadasService.buscarInstalacoesClassificadasPorId(id);
            return ResponseEntity.ok(instalacoesClassificadas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetInstalacoesClassificadasDTO>> buscarTodasInstalacoesClassificadas() {
        List<GetInstalacoesClassificadasDTO> todasInstalacoesClassificadas = instalacoesClassificadasService.buscarTodasInstalacoesClassificadas();
        return ResponseEntity.ok(todasInstalacoesClassificadas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirInstalacoesClassificadasPorId(@PathVariable UUID id) {
        try {
            instalacoesClassificadasService.excluirInstalacoesClassificadasPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
