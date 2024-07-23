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

import br.com.jbst.DTO.GetMedidasDeControleDTO;
import br.com.jbst.DTO.PostMedidasDeControleDTO;
import br.com.jbst.DTO.PostMedidasDeControleIncluirRiscosDTO;
import br.com.jbst.DTO.PutMedidasDeControleDTO;
import br.com.jbst.DTO.PutMedidasDeControleIncluirRiscosDTO;
import br.com.jbst.services.MedidasDeControleServices;

@RestController
@RequestMapping("/medidasdecontrole")
public class MedidasDeControleControllers {

    @Autowired
    private MedidasDeControleServices medidasDeControleServices;

    @PostMapping("/criar-medidas-de-controlle")
    public ResponseEntity<GetMedidasDeControleDTO> criarMedidasDeControle(@RequestBody PostMedidasDeControleDTO dto) {
        GetMedidasDeControleDTO medidasDeControleCriadas = medidasDeControleServices.criarMedidasDeControle(dto);
        return new ResponseEntity<>(medidasDeControleCriadas, HttpStatus.CREATED);
    }
    
    @PostMapping("/criar-medidas-de-controlle-riscos")
    public ResponseEntity<GetMedidasDeControleDTO> criarMedidasDeControleRiscos(@RequestBody PostMedidasDeControleIncluirRiscosDTO dto) {
        GetMedidasDeControleDTO medidasDeControleCriadas = medidasDeControleServices.criarMedidasDeControleIncluirRiscos(dto);
        return new ResponseEntity<>(medidasDeControleCriadas, HttpStatus.CREATED);
    }
    
    @PutMapping("/editar-medidas-de-controlle")
    public ResponseEntity<GetMedidasDeControleDTO> editarMedidasDeControle(@RequestBody PutMedidasDeControleDTO dto) {
        GetMedidasDeControleDTO medidasDeControleAtualizadas = medidasDeControleServices.editarMedidasDeControle(dto);
        if (medidasDeControleAtualizadas != null) {
            return ResponseEntity.ok(medidasDeControleAtualizadas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<GetMedidasDeControleDTO> buscarMedidasDeControlePorId(@PathVariable UUID id) {
        GetMedidasDeControleDTO medidasEncontradas = medidasDeControleServices.buscarPorId(id);
        return ResponseEntity.ok(medidasEncontradas);
    }

    @GetMapping
    public ResponseEntity<List<GetMedidasDeControleDTO>> buscarTodasMedidasDeControle() {
        List<GetMedidasDeControleDTO> todasMedidas = medidasDeControleServices.buscarTodasMedidas();
        return ResponseEntity.ok(todasMedidas);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMedidasDeControle(@PathVariable UUID id) {
        medidasDeControleServices.excluirMedidasDeControle(id);
        return ResponseEntity.noContent().build();
    }
}
