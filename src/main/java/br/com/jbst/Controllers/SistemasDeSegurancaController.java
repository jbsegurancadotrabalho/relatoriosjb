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

import br.com.jbst.DTO.GetSistemasDeSegurancaDTO;
import br.com.jbst.DTO.PostSistemasDeSegurancaDTO;
import br.com.jbst.DTO.PutSistemasDeSegurancaDTO;
import br.com.jbst.services.SistemasDeSegurancaService;

@RestController
@RequestMapping("/sistemas-de-seguranca")
public class SistemasDeSegurancaController {

    @Autowired
    SistemasDeSegurancaService sistemasDeSegurancaService;

    @PostMapping
    public ResponseEntity<GetSistemasDeSegurancaDTO> criarSistemasDeSeguranca(@RequestBody PostSistemasDeSegurancaDTO dto) {
        GetSistemasDeSegurancaDTO sistemasDeSegurancaDTO = sistemasDeSegurancaService.criarSistemasDeSeguranca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sistemasDeSegurancaDTO);
    }

    @PutMapping
    public ResponseEntity<GetSistemasDeSegurancaDTO> editarSistemasDeSeguranca(@RequestBody PutSistemasDeSegurancaDTO dto) {
        GetSistemasDeSegurancaDTO sistemasDeSegurancaDTO = sistemasDeSegurancaService.editarSistemasDeSeguranca(dto);
        return ResponseEntity.ok(sistemasDeSegurancaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSistemasDeSegurancaDTO> buscarSistemasDeSegurancaPorId(@PathVariable UUID id) {
        GetSistemasDeSegurancaDTO sistemasDeSegurancaDTO = sistemasDeSegurancaService.buscarPorId(id);
        return ResponseEntity.ok(sistemasDeSegurancaDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetSistemasDeSegurancaDTO>> buscarTodosSistemasDeSeguranca() {
        List<GetSistemasDeSegurancaDTO> sistemasDeSegurancaDTOList = sistemasDeSegurancaService.buscarTodosSistemasDeSeguranca();
        return ResponseEntity.ok(sistemasDeSegurancaDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirSistemasDeSeguranca(@PathVariable UUID id) {
        sistemasDeSegurancaService.excluirSistemasDeSeguranca(id);
        return ResponseEntity.noContent().build();
    }
}
