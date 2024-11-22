package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DtoAtendimento.GetAtendimentoDTO;
import br.com.jbst.DtoAtendimento.PostAtendimentoDTO;
import br.com.jbst.DtoAtendimento.PutAtendimentoDTO;
import br.com.jbst.services.AtendimentoService;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<GetAtendimentoDTO> criarAtendimento(@RequestBody PostAtendimentoDTO dto) {
        GetAtendimentoDTO atendimento = atendimentoService.criarAtendimento(dto);
        return ResponseEntity.status(201).body(atendimento); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetAtendimentoDTO> editarAtendimento(@PathVariable UUID id, @RequestBody PutAtendimentoDTO dto) {
        dto.setIdAtendimento(id); // Set the ID in the DTO
        GetAtendimentoDTO updatedAtendimento = atendimentoService.editarAtendimento(dto);
        return ResponseEntity.ok(updatedAtendimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAtendimentoDTO> buscarAtendimento(@PathVariable UUID id) {
        GetAtendimentoDTO atendimento = atendimentoService.buscarAtendimento(id);
        return ResponseEntity.ok(atendimento);
    }

    @GetMapping
    public ResponseEntity<List<GetAtendimentoDTO>> buscarAtendimentos() {
        List<GetAtendimentoDTO> atendimentos = atendimentoService.buscarAtendimentos();
        return ResponseEntity.ok(atendimentos);
    }
}
