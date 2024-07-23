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

import br.com.jbst.DTO.GetMaquinasDTO;
import br.com.jbst.DTO.PostMaquinasDTO;
import br.com.jbst.DTO.PutMaquinasDTO;
import br.com.jbst.services.MaquinasServices;

@RestController
@RequestMapping("/maquinas")
public class MaquinasControllers {

    @Autowired
    MaquinasServices maquinasService;

    @PostMapping
    public ResponseEntity<GetMaquinasDTO> criarMaquinas(@RequestBody PostMaquinasDTO dto) {
        try {
            return maquinasService.criarMaquinas(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PutMapping
    public ResponseEntity<?> editarMaquinas(@RequestBody PutMaquinasDTO dto) {
        try {
            GetMaquinasDTO maquinaAtualizada = maquinasService.editarMaquinas(dto);
            return ResponseEntity.ok(maquinaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<GetMaquinasDTO>> consultarTodasMaquinas() {
        List<GetMaquinasDTO> maquinas = maquinasService.consultarTodasMaquinas();
        return ResponseEntity.ok(maquinas);
    }

    @GetMapping("/{idMaquina}")
    public ResponseEntity<?> consultarMaquinaPorId(@PathVariable UUID idMaquina) {
        try {
            GetMaquinasDTO maquina = maquinasService.consultarMaquinaPorId(idMaquina);
            return ResponseEntity.ok(maquina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idMaquina}")
    public ResponseEntity<?> excluirMaquina(@PathVariable UUID idMaquina) {
        try {
            GetMaquinasDTO maquinaExcluida = maquinasService.excluirMaquina(idMaquina);
            return ResponseEntity.ok(maquinaExcluida);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

