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

import br.com.jbst.DTO2.GetTomadasDTO;
import br.com.jbst.DTO2.PostTomadasDTO;
import br.com.jbst.DTO2.PutTomadasDTO;
import br.com.jbst.services.TomadasService;

@RestController
@RequestMapping("/api/tomadas")
public class TomadasController {
    @Autowired
    private TomadasService tomadasService;

    @PostMapping
    public ResponseEntity<GetTomadasDTO> criarTomada(@RequestBody PostTomadasDTO dto) {
        GetTomadasDTO tomadaDTO = tomadasService.criarQuadros_Paineis(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tomadaDTO);
    }

    @PutMapping
    public ResponseEntity<GetTomadasDTO> editarTomada(@RequestBody PutTomadasDTO dto) {
        GetTomadasDTO tomadaDTO = tomadasService.editarQuadros_Paineis(dto);
        return ResponseEntity.ok(tomadaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTomadasDTO> consultarTomadaPorId(@PathVariable UUID id) {
        GetTomadasDTO tomadaDTO = tomadasService.consultarTomadasPorId(id);
        return ResponseEntity.ok(tomadaDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetTomadasDTO>> consultarTodasAsTomadas() {
        List<GetTomadasDTO> tomadasDTO = tomadasService.consultarTodasAsTomadas();
        return ResponseEntity.ok(tomadasDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTomada(@PathVariable UUID id) {
        tomadasService.excluirTomadasPorId(id);
        return ResponseEntity.noContent().build();
    }
}
