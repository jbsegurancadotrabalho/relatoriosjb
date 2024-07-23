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

import br.com.jbst.DTO2.GetLuminariasDTO;
import br.com.jbst.DTO2.PostLuminariasDTO;
import br.com.jbst.DTO2.PutLuminariasDTO;
import br.com.jbst.services.LuminariasService;

@RestController
@RequestMapping("/luminarias")
public class LuminariasController {

    @Autowired
    private LuminariasService luminariasService;

    @PostMapping
    public ResponseEntity<GetLuminariasDTO> criarLuminarias(@RequestBody PostLuminariasDTO dto) {
        try {
            GetLuminariasDTO luminariasCriada = luminariasService.criarLuminarias(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(luminariasCriada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetLuminariasDTO> editarLuminarias(@RequestBody PutLuminariasDTO dto) {
        try {
            GetLuminariasDTO luminariasEditada = luminariasService.editarLuminarias(dto);
            return ResponseEntity.ok(luminariasEditada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLuminariasDTO> buscarLuminariasPorId(@PathVariable UUID id) {
        try {
            GetLuminariasDTO luminarias = luminariasService.buscarLuminariasPorId(id);
            return ResponseEntity.ok(luminarias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetLuminariasDTO>> buscarTodasLuminarias() {
        List<GetLuminariasDTO> todasLuminarias = luminariasService.buscarTodasLuminarias();
        return ResponseEntity.ok(todasLuminarias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLuminariasPorId(@PathVariable UUID id) {
        try {
            luminariasService.excluirLuminariasPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
