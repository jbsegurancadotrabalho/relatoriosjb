package br.com.jbst.Controllers;

import br.com.jbst.DTO2.GetAvaliacoesCredenciadosDTO;
import br.com.jbst.DTO2.PostAvaliacoesCredenciadosDTO;
import br.com.jbst.DTO2.PutAvaliacoesCredenciadosDTO;
import br.com.jbst.services.AvaliacoesCredenciadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliacoes-credenciados")
public class AvaliacoesCredenciadosController {

    @Autowired
    AvaliacoesCredenciadosService avaliacoesCredenciadosService;


    @PostMapping
    public ResponseEntity<GetAvaliacoesCredenciadosDTO> criarAvaliacaoCredenciado(@RequestBody PostAvaliacoesCredenciadosDTO dto) {
        GetAvaliacoesCredenciadosDTO result = avaliacoesCredenciadosService.criarAvaliacoesCredenciados(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping
    public ResponseEntity<GetAvaliacoesCredenciadosDTO> atualizarAvaliacaoCredenciado(@RequestBody PutAvaliacoesCredenciadosDTO dto) {
        GetAvaliacoesCredenciadosDTO result = avaliacoesCredenciadosService.atualizarAvaliacoesCredenciados(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<GetAvaliacoesCredenciadosDTO>> buscarTodasAvaliacoesCredenciados() {
        List<GetAvaliacoesCredenciadosDTO> result = avaliacoesCredenciadosService.buscarTodasAvaliacoesCredenciados();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAvaliacoesCredenciadosDTO> buscarAvaliacaoCredenciadoPorId(@PathVariable UUID id) {
        GetAvaliacoesCredenciadosDTO result = avaliacoesCredenciadosService.buscarAvaliacaoCredenciadoPorId(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAvaliacaoCredenciadoPorId(@PathVariable UUID id) {
        avaliacoesCredenciadosService.excluirAvaliacaoCredenciadoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
