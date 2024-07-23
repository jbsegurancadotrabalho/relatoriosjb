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

import br.com.jbst.DTO.GetEtapasDTO;
import br.com.jbst.DTO.PostEtapasIncluirCenarioDTO;
import br.com.jbst.DTO.PostEtapasIncluirDocumentosFreeDTO;
import br.com.jbst.DTO.PostEtapasIncluirFuncaoDocDTO;
import br.com.jbst.DTO.PutEtapasCenarioDTO;
import br.com.jbst.DTO.PutEtapasFuncaoDocDTO;
import br.com.jbst.services.EtapasServices;

@RestController
@RequestMapping("/etapas")
public class EtapasController {

    @Autowired
    private EtapasServices etapasServices;

    @PostMapping("/criar-etapas-cenario")
    public ResponseEntity<GetEtapasDTO> criarEtapaCenario(@RequestBody PostEtapasIncluirCenarioDTO dto) {
        try {
            GetEtapasDTO etapaCriada = etapasServices.criarEtapaCenario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(etapaCriada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @PostMapping("/criar-etapas-documentos-free")
    public ResponseEntity<GetEtapasDTO> criarEtapaDocumentosFree( @RequestBody PostEtapasIncluirDocumentosFreeDTO dto) {
        try {
            GetEtapasDTO etapaDTO = etapasServices.criarEtapaDocumentosFree(dto);
            return new ResponseEntity<>(etapaDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/criar-etapas-funcaodoc")
    public ResponseEntity<GetEtapasDTO> criarEtapaFuncaodoc(@RequestBody PostEtapasIncluirFuncaoDocDTO dto) {
        try {
            GetEtapasDTO etapaDTO = etapasServices.criarEtapaFuncaodoc(dto);
            return new ResponseEntity<>(etapaDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar-etapas-cenario")
    public ResponseEntity<GetEtapasDTO> EditarEtapasCenario(@RequestBody PutEtapasCenarioDTO dto) {
        try {
            GetEtapasDTO etapaAtualizada = etapasServices.editarEtapaCenario(dto);
            return ResponseEntity.ok().body(etapaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    @PutMapping("/editar-etapas-funcao-doc")
    public ResponseEntity<GetEtapasDTO> editarEtapaFuncaoDoc(@RequestBody PutEtapasFuncaoDocDTO dto) {
        try {
            GetEtapasDTO etapaAtualizadaDTO = etapasServices.editarEtapaFuncaoDoc(dto);
            return ResponseEntity.ok(etapaAtualizadaDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetEtapasDTO> buscarEtapaPorId(@PathVariable UUID id) {
        try {
            GetEtapasDTO etapaEncontrada = etapasServices.buscarEtapaPorId(id);
            return ResponseEntity.ok(etapaEncontrada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetEtapasDTO>> buscarTodasEtapas() {
        try {
            List<GetEtapasDTO> todasEtapas = etapasServices.buscarTodasEtapas();
            return ResponseEntity.ok(todasEtapas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetEtapasDTO> excluirEtapa(@PathVariable UUID id) {
        try {
            GetEtapasDTO etapaExcluida = etapasServices.excluirEtapa(id);
            return ResponseEntity.ok(etapaExcluida);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

