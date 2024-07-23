package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.DTO.PostAvaliaçõesOcupacionaisFuncoesIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.entities.PostAvaliaçõesOcupacionaisFuncoesDTO;
import br.com.jbst.services.AvaliaçõesOcupacionaisFuncoesServices;

@RestController
@RequestMapping("/avaliacoes-ocupacionais-funcoes")
public class AvaliaçõesOcupacionaisFuncoesController {

    @Autowired
    private AvaliaçõesOcupacionaisFuncoesServices avaliacoesOcupacionaisFuncoesServices;

    @PostMapping
    public ResponseEntity<GetAvaliaçõesOcupacionaisFuncoesDTO> criarAvaliação(@RequestBody PostAvaliaçõesOcupacionaisFuncoesDTO dto) {
        GetAvaliaçõesOcupacionaisFuncoesDTO created = avaliacoesOcupacionaisFuncoesServices.criarAvaliaçõesOcupacionaisFuncoes(dto);
        return ResponseEntity.ok(created);
    }
    
    @PostMapping("/incluir-funcao-especifica")
    public ResponseEntity<GetAvaliaçõesOcupacionaisFuncoesDTO> criarAvaliacoesOcupacionaisIncluirFuncoesEspecifica(
            @RequestBody PostAvaliaçõesOcupacionaisFuncoesIncluirFuncaoEspecificaDTO dto) {
        GetAvaliaçõesOcupacionaisFuncoesDTO response = avaliacoesOcupacionaisFuncoesServices.criarAvaliaçõesOcupacionaisIncluirFuncoesEspecifica(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GetAvaliaçõesOcupacionaisFuncoesDTO> editarAvaliação( @RequestBody PutAvaliaçõesOcupacionaisFuncoesDTO dto) {
        GetAvaliaçõesOcupacionaisFuncoesDTO updated = avaliacoesOcupacionaisFuncoesServices.editarAvaliaçõesOcupacionaisFuncoes(dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<GetAvaliaçõesOcupacionaisFuncoesDTO>> buscarTodasAvaliações() {
        List<GetAvaliaçõesOcupacionaisFuncoesDTO> avaliacoes = avaliacoesOcupacionaisFuncoesServices.buscarTodasAvaliaçõesOcupacionaisFuncoes();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAvaliaçõesOcupacionaisFuncoesDTO> buscarAvaliaçãoPorId(@PathVariable UUID id) {
        GetAvaliaçõesOcupacionaisFuncoesDTO avaliacao = avaliacoesOcupacionaisFuncoesServices.buscarAvaliaçãoOcupacionalFunçãoPorId(id);
        if (avaliacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetAvaliaçõesOcupacionaisFuncoesDTO> excluirAvaliação(@PathVariable UUID id) {
        try {
            GetAvaliaçõesOcupacionaisFuncoesDTO deleted = avaliacoesOcupacionaisFuncoesServices.excluirAvaliaçãoOcupacionalFunçãoPorId(id);
            return ResponseEntity.ok(deleted);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
