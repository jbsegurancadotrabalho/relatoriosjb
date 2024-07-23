package br.com.jbst.Controllers;



import java.util.List;
import java.util.NoSuchElementException;
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

import br.com.jbst.DTO.GetAvaliacoesDTO;
import br.com.jbst.DTO.PostAvaliacoesDTO;
import br.com.jbst.DTO.PutAvaliacoesDTO;
import br.com.jbst.services.AvaliacoesService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacoesController {

    @Autowired
    AvaliacoesService avaliacoesService;

    @PostMapping
    public ResponseEntity<GetAvaliacoesDTO> criarAvaliacoes(@RequestBody PostAvaliacoesDTO dto) {
        try {
        	GetAvaliacoesDTO resultado = avaliacoesService.criarAvaliacoes(dto);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<GetAvaliacoesDTO> editarAvaliacoesSemParametro(@RequestBody PutAvaliacoesDTO dto) {
        try {
            GetAvaliacoesDTO avaliacaoEditada = avaliacoesService.editarAvaliacoes(dto);
            return ResponseEntity.ok(avaliacaoEditada);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
      }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetAvaliacoesDTO> buscarPorId(@PathVariable UUID id) {
        try {
            GetAvaliacoesDTO avaliacao = avaliacoesService.buscarPorId(id);
            return ResponseEntity.ok(avaliacao);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetAvaliacoesDTO>> buscarTodasAvaliacoes() {
        List<GetAvaliacoesDTO> avaliacoes = avaliacoesService.buscarTodasAvaliacoes();
        return ResponseEntity.ok(avaliacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAvaliacoes(@PathVariable UUID id) {
        try {
            avaliacoesService.excluirAvaliacoes(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    }


