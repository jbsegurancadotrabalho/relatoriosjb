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

import br.com.jbst.DTO2.GetQuadros_MedicoesDTO;
import br.com.jbst.DTO2.PostQuadros_MedicoesDTO;
import br.com.jbst.DTO2.PutQuadros_MedicoesDTO;
import br.com.jbst.services.Quadros_MedicoesService;

@RestController
@RequestMapping("/quadros-medicoes")
public class Quadros_MedicoesController {

    @Autowired
    private Quadros_MedicoesService quadrosMedicoesService;

    @PostMapping
    public ResponseEntity<GetQuadros_MedicoesDTO> criarQuadrosMedicoes(@RequestBody PostQuadros_MedicoesDTO dto) {
        GetQuadros_MedicoesDTO quadrosMedicoesCriado = quadrosMedicoesService.criarQuadrosMedicoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quadrosMedicoesCriado);
    }

    @PutMapping
    public ResponseEntity<GetQuadros_MedicoesDTO> editarQuadrosMedicoes( @RequestBody PutQuadros_MedicoesDTO dto) {
        GetQuadros_MedicoesDTO quadrosMedicoesAtualizado = quadrosMedicoesService.editarQuadrosMedicoes(dto);
        return ResponseEntity.ok(quadrosMedicoesAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetQuadros_MedicoesDTO> buscarQuadrosMedicoesPorId(@PathVariable UUID id) {
        try {
            GetQuadros_MedicoesDTO quadrosMedicoes = quadrosMedicoesService.buscarQuadrosMedicoesPorId(id);
            return ResponseEntity.ok(quadrosMedicoes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetQuadros_MedicoesDTO>> buscarTodasQuadrosMedicoes() {
        List<GetQuadros_MedicoesDTO> todasQuadrosMedicoes = quadrosMedicoesService.buscarTodasQuadrosMedicoes();
        return ResponseEntity.ok(todasQuadrosMedicoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirQuadrosMedicoes(@PathVariable UUID id) {
        try {
            quadrosMedicoesService.excluirQuadrosMedicoesPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
