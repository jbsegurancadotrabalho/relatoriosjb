package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DtoEvento.GetParticipanteDTO;
import br.com.jbst.DtoEvento.PostParticipanteDTO;
import br.com.jbst.DtoEvento.PutParticipanteDTO;
import br.com.jbst.services.ParticipanteService;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    // Endpoint para criar um participante
    @PostMapping
    public ResponseEntity<GetParticipanteDTO> criarParticipante(@RequestBody PostParticipanteDTO dto) {
        try {
            GetParticipanteDTO novoParticipante = participanteService.criarParticipante(dto);
            return new ResponseEntity<>(novoParticipante, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para editar um participante
    @PutMapping
    public ResponseEntity<GetParticipanteDTO> editarParticipante(@RequestBody PutParticipanteDTO dto) {
        try {
            GetParticipanteDTO participanteAtualizado = participanteService.editarParticipante(dto);
            return new ResponseEntity<>(participanteAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obter um participante por ID
    @GetMapping("/{idParticipante}")
    public ResponseEntity<GetParticipanteDTO> getParticipanteById(@PathVariable UUID idParticipante) {
        try {
            GetParticipanteDTO participante = participanteService.getParticipanteById(idParticipante);
            return new ResponseEntity<>(participante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para listar todos os participantes
    @GetMapping
    public ResponseEntity<List<GetParticipanteDTO>> getAllParticipantes() {
        List<GetParticipanteDTO> participantes = participanteService.getAllParticipantes();
        return new ResponseEntity<>(participantes, HttpStatus.OK);
    }

    // Endpoint para excluir um participante
    @DeleteMapping("/{idParticipante}")
    public ResponseEntity<GetParticipanteDTO> excluirParticipante(@PathVariable UUID idParticipante) {
        try {
            GetParticipanteDTO participanteExcluido = participanteService.excluirParticipante(idParticipante);
            return new ResponseEntity<>(participanteExcluido, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
