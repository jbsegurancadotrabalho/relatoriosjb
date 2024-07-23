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

import br.com.jbst.DTO2.GetAnexoDTO;
import br.com.jbst.DTO2.PostAnexosDTO;
import br.com.jbst.DTO2.PutAnexosDTO;
import br.com.jbst.services.AnexoService;

@RestController
@RequestMapping("/anexos")
public class AnexosController {

    @Autowired
    private AnexoService anexoService;

    @PostMapping
    public ResponseEntity<GetAnexoDTO> criarAnexo(@RequestBody PostAnexosDTO dto) {
        try {
            GetAnexoDTO novoAnexo = anexoService.criarAnexo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAnexo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<GetAnexoDTO> editarAnexo(@RequestBody PutAnexosDTO dto) {
        try {
            GetAnexoDTO anexoEditado = anexoService.editarAnexo(dto);
            return ResponseEntity.ok(anexoEditado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetAnexoDTO>> buscarTodosOsAnexos() {
        List<GetAnexoDTO> anexos = anexoService.buscarTodosOsAnexos();
        return ResponseEntity.ok(anexos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAnexoDTO> consultarAnexoPorId(@PathVariable UUID id) {
        try {
            GetAnexoDTO anexo = anexoService.consultarAnexoPorId(id);
            return ResponseEntity.ok(anexo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAnexoPorId(@PathVariable UUID id) {
        try {
            anexoService.excluirAnexoPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

