package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import br.com.jbst.DTO2.GetAgendamentoDTO;
import br.com.jbst.DTO2.PostAgendamentoDTO;
import br.com.jbst.DTO2.PutAgendamentoDTO;
import br.com.jbst.services.AgendamentoServices;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
  AgendamentoServices agendamentoServices;

    @PostMapping
    public ResponseEntity<GetAgendamentoDTO> criarAgendamento(@RequestBody PostAgendamentoDTO dto) {
        GetAgendamentoDTO agendamentoDTO = agendamentoServices.criarAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoDTO);
    }

    @PutMapping
    public ResponseEntity<GetAgendamentoDTO> editarAgendamento(@RequestBody PutAgendamentoDTO dto) {
        GetAgendamentoDTO agendamentoDTO = agendamentoServices.editarAgendamento(dto);
        if (agendamentoDTO != null) {
            return ResponseEntity.ok(agendamentoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAgendamentoDTO> buscarPorId(@PathVariable UUID id) {
        GetAgendamentoDTO agendamentoDTO = agendamentoServices.buscarPorId(id);
        if (agendamentoDTO != null) {
            return ResponseEntity.ok(agendamentoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetAgendamentoDTO>> buscarTodos() {
        List<GetAgendamentoDTO> agendamentosDTO = agendamentoServices.buscarTodos();
        return ResponseEntity.ok(agendamentosDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable UUID id) {
        try {
            agendamentoServices.excluirAgendaPorId(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



