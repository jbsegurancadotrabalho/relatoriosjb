package br.com.jbst.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetGho_SetorDTO;
import br.com.jbst.DTO.PostGho_SetorDTO;
import br.com.jbst.DTO.PutGho_SetorDTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetSetor2DTO;
import br.com.jbst.services.Gho_SetorServices;

@RestController
@RequestMapping("/api/gho-setor")
public class Gho_SetorController {


    @Autowired
    Gho_SetorServices ghoSetorServices;


    @PostMapping
    public ResponseEntity<GetGho_SetorDTO> criarGhoSetor(@RequestBody PostGho_SetorDTO dto) {
        try {
            GetGho_SetorDTO novoGhoSetor = ghoSetorServices.criarGhoSetor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoGhoSetor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<GetGho_SetorDTO> editarGhoSetor(@RequestBody PutGho_SetorDTO dto) {
        try {
            GetGho_SetorDTO ghoSetorEditado = ghoSetorServices.editarGhoSetor(dto);
            return ResponseEntity.ok(ghoSetorEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetGho_SetorDTO> buscarGhoSetorPorId(@PathVariable UUID id) {
        try {
            GetGho_SetorDTO ghoSetor = ghoSetorServices.buscarGhoSetorPorId(id);
            return ResponseEntity.ok(ghoSetor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetGho_SetorDTO>> buscarTodosGhoSetores() {
        List<GetGho_SetorDTO> ghoSetores = ghoSetorServices.buscarTodosGhoSetores();
        return ResponseEntity.ok(ghoSetores);
    }
    
    @GetMapping("/setor-cenário")
    public ResponseEntity<List<GetSetor2DTO>> buscarSetoresCenarios() {
        List<GetSetor2DTO> ghoSetores = ghoSetorServices.buscarSetoresCenarios();
        return ResponseEntity.ok(ghoSetores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetGho_SetorDTO> excluirGhoSetor(@PathVariable UUID id) {
        try {
            GetGho_SetorDTO ghoSetorExcluido = ghoSetorServices.excluirGhoSetor(id);
            return ResponseEntity.ok(ghoSetorExcluido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

