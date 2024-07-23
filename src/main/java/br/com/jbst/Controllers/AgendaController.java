package br.com.jbst.Controllers;


import java.util.List;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO2.GetAgendaDTO;
import br.com.jbst.DTO2.PostAgendaDTO;
import br.com.jbst.DTO2.PutAgendaDTO;
import br.com.jbst.services.AgendaService;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PutMapping
    public ResponseEntity<GetAgendaDTO> criarAgenda(@RequestBody PostAgendaDTO dto) {
        try {
            GetAgendaDTO novaAgenda = agendaService.criarAgenda(dto);
            return new ResponseEntity<>(novaAgenda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<GetAgendaDTO> editarAgenda(@RequestBody PutAgendaDTO putAgendaDTO) {
        try {
            GetAgendaDTO agendaAtualizada = agendaService.editarAgenda(putAgendaDTO);
            return ResponseEntity.ok(agendaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Bad request if ID is not provided
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal server error for other exceptions
        }
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<GetAgendaDTO> consultarAgendaPorId(@PathVariable UUID id) {
        try {
            GetAgendaDTO agendaDTO = agendaService.consultarAgendaPorId(id);
            return ResponseEntity.ok(agendaDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todas")
	public ResponseEntity<List<GetAgendaDTO>> buscarTodasAsAgendas() {
        List<GetAgendaDTO> agendas = agendaService.buscarTodasAsAgendas();
        return ResponseEntity.ok(agendas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendaPorId(@PathVariable UUID id) {
        try {
            agendaService.excluirAgendaPorId(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
       }
    }