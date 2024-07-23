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

import br.com.jbst.DTO2.GetAterramentoDTO;
import br.com.jbst.DTO2.PostAterramentoDTO;
import br.com.jbst.DTO2.PutAterramentoDTO;
import br.com.jbst.services.AterramentoService;

@RestController
@RequestMapping("/api/aterramento")
public class AterramentoController {

    @Autowired
    private AterramentoService aterramentoService;

    @PostMapping
    public ResponseEntity<GetAterramentoDTO> criarAterramento(@RequestBody PostAterramentoDTO dto) {
        GetAterramentoDTO novoAterramento = aterramentoService.criarAterramento(dto);
        return new ResponseEntity<>(novoAterramento, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GetAterramentoDTO> editarAterramento( @RequestBody PutAterramentoDTO dto) {
        GetAterramentoDTO aterramentoAtualizado = aterramentoService.editarAterramento(dto);
        return ResponseEntity.ok(aterramentoAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAterramentoDTO> consultarAterramentoPorId(@PathVariable("id") UUID id) {
        GetAterramentoDTO aterramento = aterramentoService.consultarAterramentoPorId(id);
        return ResponseEntity.ok(aterramento);
    }

    @GetMapping
    public ResponseEntity<List<GetAterramentoDTO>> consultarTodosOsAterramentos() {
        List<GetAterramentoDTO> aterramentos = aterramentoService.consultarTodosOsAterramentos();
        return ResponseEntity.ok(aterramentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAterramentoPorId(@PathVariable("id") UUID id) {
        aterramentoService.excluirAterramentoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
