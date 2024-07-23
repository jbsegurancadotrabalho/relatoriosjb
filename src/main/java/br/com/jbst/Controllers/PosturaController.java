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

import br.com.jbst.DTO.GetPosturaDTO;
import br.com.jbst.DTO.PostPosturaDTO;
import br.com.jbst.DTO.PutPosturaDTO;
import br.com.jbst.services.PosturaServices;

@RestController
@RequestMapping("/posturas")
public class PosturaController {

    @Autowired
    private PosturaServices posturaServices;

    @PostMapping
    public ResponseEntity<GetPosturaDTO> criarPostura(@RequestBody PostPosturaDTO dto) {
        try {
            GetPosturaDTO posturaCriada = posturaServices.criarPostura(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(posturaCriada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<GetPosturaDTO> editarPostura(@RequestBody PutPosturaDTO dto) {
        try {
            GetPosturaDTO posturaDTO = posturaServices.editarPostura(dto);
            return ResponseEntity.ok(posturaDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPosturaDTO> buscarPosturaPorId(@PathVariable UUID id) {
        try {
            GetPosturaDTO postura = posturaServices.buscarPosturaPorId(id);
            return ResponseEntity.ok(postura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetPosturaDTO>> buscarTodasPosturas() {
        try {
            List<GetPosturaDTO> posturas = posturaServices.buscarTodasPosturas();
            return ResponseEntity.ok(posturas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetPosturaDTO> excluirPostura(@PathVariable UUID id) {
        try {
            GetPosturaDTO posturaExcluida = posturaServices.excluirPostura(id);
            return ResponseEntity.ok(posturaExcluida);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

