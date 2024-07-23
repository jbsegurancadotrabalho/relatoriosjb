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

import br.com.jbst.DTO.GetEpiDTO;
import br.com.jbst.DTO.PostEpiDTO;
import br.com.jbst.DTO.PostEpiIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutEpiDTO;
import br.com.jbst.services.EpiServices;

@RestController
@RequestMapping("/epi")
public class EPIControllers {

    @Autowired
    private EpiServices epiServices;

    @PostMapping("/criar")
    public ResponseEntity<GetEpiDTO> criarEpi(@RequestBody PostEpiDTO dto) {
        try {
            GetEpiDTO novoEpi = epiServices.criarEpi(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEpi);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/incluir-funcao-especifica")
    public ResponseEntity<GetEpiDTO> criarEpiIncluirFuncaoEspecifica(@RequestBody PostEpiIncluirFuncaoEspecificaDTO dto) {
        try {
            GetEpiDTO epiDTO = epiServices.criarEpiIncluirFuncaoEspecifica(dto);
            return ResponseEntity.ok(epiDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @PutMapping("/epi/editar")
    public ResponseEntity<GetEpiDTO> editarEpi(@RequestBody PutEpiDTO dto) {
        try {
            GetEpiDTO epiAtualizado = epiServices.editarEpi(dto);
            return ResponseEntity.status(HttpStatus.OK).body(epiAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/consultar/{id}")
    public ResponseEntity<GetEpiDTO> consultarEPIPorId(@PathVariable UUID id) {
        try {
            GetEpiDTO epi = epiServices.consultarEPIPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(epi);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<GetEpiDTO>> consultarTodosEPIs() {
        List<GetEpiDTO> epiList = epiServices.consultarTodosEPIs();
        return ResponseEntity.status(HttpStatus.OK).body(epiList);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<GetEpiDTO> excluirEPI(@PathVariable UUID id) {
        try {
            GetEpiDTO epiExcluido = epiServices.excluirEPI(id);
            return ResponseEntity.status(HttpStatus.OK).body(epiExcluido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

