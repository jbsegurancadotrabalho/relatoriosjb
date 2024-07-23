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

import br.com.jbst.DTO2.GetProfissionalSaudeDTO;
import br.com.jbst.DTO2.PostProfissionalSaudeDTO;
import br.com.jbst.DTO2.PutProfissionalSaudeDTO;
import br.com.jbst.services.ProfissionalSaudeService;

@RestController
@RequestMapping("/profissionais-saude")
public class ProfissionalSaudeController {

    @Autowired
    private ProfissionalSaudeService profissionalSaudeService;

    @PostMapping
    public ResponseEntity<GetProfissionalSaudeDTO> criarProfissionalSaude(@RequestBody PostProfissionalSaudeDTO dto) {
        GetProfissionalSaudeDTO profissionalSaudeDTO = profissionalSaudeService.criarProfissionalSaude(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSaudeDTO);
    }

    @PutMapping
    public ResponseEntity<GetProfissionalSaudeDTO> atualizarProfissionalSaude(@RequestBody PutProfissionalSaudeDTO dto) {
        GetProfissionalSaudeDTO profissionalSaudeDTO = profissionalSaudeService.atualizarProfissionalSaude(dto);
        return ResponseEntity.ok(profissionalSaudeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProfissionalSaudeDTO> buscarProfissionalSaudePorId(@PathVariable UUID id) {
        GetProfissionalSaudeDTO profissionalSaudeDTO = profissionalSaudeService.buscarProfissionalSaudePorId(id);
        return ResponseEntity.ok(profissionalSaudeDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetProfissionalSaudeDTO>> buscarTodosProfissionaisSaude() {
        List<GetProfissionalSaudeDTO> profissionaisSaudeDTO = profissionalSaudeService.buscarTodosProfissionaisSaude();
        return ResponseEntity.ok(profissionaisSaudeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfissionalSaudePorId(@PathVariable UUID id) {
        profissionalSaudeService.excluirProfissionalSaudePorId(id);
        return ResponseEntity.noContent().build();
    }
}
