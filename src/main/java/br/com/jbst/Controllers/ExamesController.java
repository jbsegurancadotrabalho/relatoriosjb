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

import br.com.jbst.DTO.GetExamesDTO;
import br.com.jbst.DTO.PostExamesDTO;
import br.com.jbst.DTO.PutExamesDTO;
import br.com.jbst.services.ExamesServices;

@RestController
@RequestMapping("/exames")
public class ExamesController {

    @Autowired
    private ExamesServices examesServices;

    @PostMapping
    public ResponseEntity<GetExamesDTO> criarExames(@RequestBody PostExamesDTO dto) {
        try {
            GetExamesDTO novoExame = examesServices.criarExames(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoExame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetExamesDTO> editarExames(@RequestBody PutExamesDTO dto) {
        try {
            GetExamesDTO exameAtualizado = examesServices.editarExames(dto);
            return ResponseEntity.status(HttpStatus.OK).body(exameAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<GetExamesDTO>> consultarExames() {
        List<GetExamesDTO> exames = examesServices.consultarExames();
        return ResponseEntity.ok(exames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetExamesDTO> consultarExamePorId(@PathVariable("id") UUID id) {
        try {
            GetExamesDTO exame = examesServices.consultarExamePorId(id);
            return ResponseEntity.ok(exame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetExamesDTO> excluirExame(@PathVariable("id") UUID id) {
        try {
            GetExamesDTO exameExcluido = examesServices.excluirExame(id);
            return ResponseEntity.ok(exameExcluido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
