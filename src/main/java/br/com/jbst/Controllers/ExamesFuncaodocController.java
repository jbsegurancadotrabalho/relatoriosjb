package br.com.jbst.Controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO.GetExamesFuncaoDocDTO;
import br.com.jbst.DTO.PostExamesFuncaoDocDTO;
import br.com.jbst.DTO.PostExamesFuncaodocIncluirFuncaoEspecificaDTO;
import br.com.jbst.DTO.PutExamesFuncaoDocDTO;
import br.com.jbst.services.ExamesFuncaodocServices;

@RestController
@RequestMapping("/exames-funcaodoc")
public class ExamesFuncaodocController {
    
    @Autowired
    private ExamesFuncaodocServices examesFuncaodocServices;

    @PostMapping
    public ResponseEntity<GetExamesFuncaoDocDTO> criarExamesFuncaodoc(@RequestBody PostExamesFuncaoDocDTO dto) {
        GetExamesFuncaoDocDTO createdExame = examesFuncaodocServices.criarExamesFuncaodoc(dto);
        return new ResponseEntity<>(createdExame, HttpStatus.CREATED);
    }
    
    @PostMapping("/incluir-funcao-especifica")
    public ResponseEntity<GetExamesFuncaoDocDTO> criarExamesFuncaoEspecifica(@RequestBody PostExamesFuncaodocIncluirFuncaoEspecificaDTO dto) {
        try {
            GetExamesFuncaoDocDTO exameDTO = examesFuncaodocServices.criarExamesFuncaoEspecifica(dto);
            return ResponseEntity.ok(exameDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<GetExamesFuncaoDocDTO> editarExamesFuncaodoc(@RequestBody PutExamesFuncaoDocDTO dto) {
        GetExamesFuncaoDocDTO updatedExame = examesFuncaodocServices.editarExamesFuncaodoc(dto);
        if (updatedExame == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedExame, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetExamesFuncaoDocDTO> consultarExamesFuncaodocPorId(@PathVariable UUID id) {
        GetExamesFuncaoDocDTO exame = examesFuncaodocServices.consultarExamesFuncaodocPorId(id);
        if (exame == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exame, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetExamesFuncaoDocDTO>> consultarTodosOsExamesFuncaodoc() {
        List<GetExamesFuncaoDocDTO> examesList = examesFuncaodocServices.consultarTodosOsExamesFuncaodoc();
        return new ResponseEntity<>(examesList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetExamesFuncaoDocDTO> excluirExamesFuncaodoc(@PathVariable UUID id) {
        try {
            GetExamesFuncaoDocDTO exame = examesFuncaodocServices.excluirExamesFuncaodoc(id);
            return new ResponseEntity<>(exame, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
