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

import br.com.jbst.DTO2.GetMaquinas_LaudoEletricoDTO;
import br.com.jbst.DTO2.PostMaquinas_LaudoEletricoDTO;
import br.com.jbst.DTO2.PutMaquinas_LaudoEletricoDTO;
import br.com.jbst.services.Maquinas_LaudoEletricoService;

@RestController
@RequestMapping("/api/maquinas-laudo-eletrico")
public class Maquinas_LaudoEletricoController {
    
    @Autowired
    private Maquinas_LaudoEletricoService maquinasService;

    @PostMapping
    public ResponseEntity<GetMaquinas_LaudoEletricoDTO> criarMaquinas_LaudoEletrico(@RequestBody PostMaquinas_LaudoEletricoDTO dto) {
        GetMaquinas_LaudoEletricoDTO maquinaCriada = maquinasService.criarMaquinas_LaudoEletrico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(maquinaCriada);
    }

    @PutMapping
    public ResponseEntity<GetMaquinas_LaudoEletricoDTO> editarMaquinas_LaudoEletrico( @RequestBody PutMaquinas_LaudoEletricoDTO dto) {
        GetMaquinas_LaudoEletricoDTO maquinaEditada = maquinasService.editarMaquinas_LaudoEletrico(dto);
        return ResponseEntity.ok().body(maquinaEditada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMaquinas_LaudoEletricoDTO> consultarMaquinas_LaudoEletricoPorId(@PathVariable UUID id) {
        GetMaquinas_LaudoEletricoDTO maquina = maquinasService.consultarMaquinas_LaudoEletricoPorId(id);
        return ResponseEntity.ok().body(maquina);
    }

    @GetMapping
    public ResponseEntity<List<GetMaquinas_LaudoEletricoDTO>> consultarTodasAsMaquinas_LaudoEletrico() {
        List<GetMaquinas_LaudoEletricoDTO> maquinas = maquinasService.consultarTodasAsMaquinas_LaudoEletrico();
        return ResponseEntity.ok().body(maquinas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMaquinas_LaudoEletricoPorId(@PathVariable UUID id) {
        maquinasService.excluirMaquinas_LaudoEletricoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
