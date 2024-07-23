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

import br.com.jbst.DTO.GetAnaliseDePosturaDTO;
import br.com.jbst.DTO.PostAnaliseDePosturaDTO;
import br.com.jbst.DTO.PutAnaliseDePosturaDTO;
import br.com.jbst.services.AnalisePosturaServices;

@RestController
@RequestMapping("/analise-postura")
public class AnalisePosturaController {

    @Autowired
    AnalisePosturaServices analisePosturaServices;

    @PostMapping("/criar")
    public ResponseEntity<GetAnaliseDePosturaDTO> criarAnaliseDePostura(@RequestBody PostAnaliseDePosturaDTO dto) {
        try {
            GetAnaliseDePosturaDTO resultado = analisePosturaServices.criarAnaliseDePostura(dto);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<GetAnaliseDePosturaDTO> editarAnaliseDePostura(@RequestBody PutAnaliseDePosturaDTO dto) {
        try {
            GetAnaliseDePosturaDTO resultado = analisePosturaServices.editarAnaliseDePostura(dto);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            // Se o ID da análise de postura ou o ID da postura for nulo
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetAnaliseDePosturaDTO> buscarAnaliseDePosturaPorId(@PathVariable UUID id) {
        try {
            GetAnaliseDePosturaDTO resultado = analisePosturaServices.buscarAnaliseDePosturaPorId(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<List<GetAnaliseDePosturaDTO>> buscarTodasAnalisesDePostura() {
        try {
            List<GetAnaliseDePosturaDTO> resultado = analisePosturaServices.buscarTodasAnalisesDePostura();
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<GetAnaliseDePosturaDTO> excluirAnaliseDePosturaPorId(@PathVariable UUID id) {
        try {
            GetAnaliseDePosturaDTO resultado = analisePosturaServices.excluirAnaliseDePosturaPorId(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
