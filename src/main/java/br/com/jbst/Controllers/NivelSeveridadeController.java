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

import br.com.jbst.DTO.GetNivelSeveridadeDTO;
import br.com.jbst.DTO.PostNivelSeveridadeDTO;
import br.com.jbst.DTO.PostNiveldeSeveridadeIncluirRiscosDTO;
import br.com.jbst.DTO.PutNivelSeveridadeDTO;
import br.com.jbst.DTO.PutNiveldeSeveridadeIncluirRiscosDTO;
import br.com.jbst.services.NivelSeveridadeService;

@RestController
@RequestMapping("/nivel-severidade")
public class NivelSeveridadeController {

    @Autowired
    private NivelSeveridadeService nivelSeveridadeService;

    @PostMapping
    public ResponseEntity<GetNivelSeveridadeDTO> criarNivelSeveridade(@RequestBody PostNivelSeveridadeDTO dto) {
        GetNivelSeveridadeDTO novoNivelSeveridade = nivelSeveridadeService.criarNivelSeveridade(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNivelSeveridade);
    }
    
    @PostMapping("/criar-nivel-de-severidade")
    public ResponseEntity<GetNivelSeveridadeDTO> criarNivelSeveridadeRiscos(@RequestBody PostNiveldeSeveridadeIncluirRiscosDTO dto) {
        GetNivelSeveridadeDTO novoNivelSeveridade = nivelSeveridadeService.criarNivelSeveridadeIncluirRiscos(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNivelSeveridade);
    }
    
    @PutMapping
    public ResponseEntity<GetNivelSeveridadeDTO> editarNivelSeveridade(@RequestBody PutNivelSeveridadeDTO dto) {
        GetNivelSeveridadeDTO nivelSeveridadeAtualizado = nivelSeveridadeService.editarNivelSeveridade(dto);
        return ResponseEntity.ok(nivelSeveridadeAtualizado);
    }

    @PutMapping("/editar-nivel-de-severidade")
    public ResponseEntity<GetNivelSeveridadeDTO> editarNivelSeveridadeRiscos(@RequestBody PutNiveldeSeveridadeIncluirRiscosDTO dto) {
        GetNivelSeveridadeDTO nivelSeveridadeAtualizado = nivelSeveridadeService.editarNivelSeveridadeIncluirRiscos(dto);
        return ResponseEntity.ok(nivelSeveridadeAtualizado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetNivelSeveridadeDTO> buscarPorId(@PathVariable UUID id) {
        GetNivelSeveridadeDTO nivelSeveridade = nivelSeveridadeService.buscarPorId(id);
        return ResponseEntity.ok(nivelSeveridade);
    }

    @GetMapping
    public ResponseEntity<List<GetNivelSeveridadeDTO>> buscarTodosNiveisSeveridade() {
        List<GetNivelSeveridadeDTO> niveisSeveridade = nivelSeveridadeService.buscarTodosNiveisSeveridade();
        return ResponseEntity.ok(niveisSeveridade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirNivelSeveridade(@PathVariable UUID id) {
        nivelSeveridadeService.excluirNivelSeveridade(id);
        return ResponseEntity.noContent().build();
    }
}
