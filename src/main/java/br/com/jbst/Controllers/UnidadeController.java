package br.com.jbst.Controllers;

import java.util.List;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

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

import br.com.jbst.DTO.GetUnidade3DTO;
import br.com.jbst.DTO.GetUnidadeDTO;
import br.com.jbst.DTO.PostUnidadeDTO;
import br.com.jbst.DTO.PutUnidadeDTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetUnidade2DTO;
import br.com.jbst.DTO2.PostUnidadeIncluirFuncaoDocDTO;
import br.com.jbst.DTO2.PostUnidadeIncluirSetoresDTO;
import br.com.jbst.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<GetUnidadeDTO>  CriarUnidades(@RequestBody PostUnidadeDTO dto) {
        GetUnidadeDTO createdUnidade = unidadeService.criarUnidade(dto);
        return new ResponseEntity<>(createdUnidade, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<GetUnidadeDTO> editarUnidade( @RequestBody PutUnidadeDTO dto) {
        GetUnidadeDTO updatedUnidade = unidadeService.editarUnidade(dto);
        return ResponseEntity.ok(updatedUnidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUnidadeDTO> getUnidadeById(@PathVariable UUID id) {
        GetUnidadeDTO unidade = unidadeService.getUnidadeById(id);
        return ResponseEntity.ok(unidade);
    }

    @GetMapping("/unidades-setores")
    public ResponseEntity<List<GetUnidadeDTO>> getAllUnidades() {
        List<GetUnidadeDTO> unidades = unidadeService.getAllUnidades();
        return ResponseEntity.ok(unidades);
    }
    
    @GetMapping("/unidades-para-empresas")
    public ResponseEntity<List<GetUnidade3DTO>> consultarTodasAsUnidasEmpresas() {
        List<GetUnidade3DTO> unidades = unidadeService.consultarTodasAsUnidasEmpresas();
        return ResponseEntity.ok(unidades);
    }
    
    @GetMapping
    public ResponseEntity<List<GetUnidade2DTO>> UnidadesSetores() {
        List<GetUnidade2DTO> unidades = unidadeService.UnidadesSetores();
        return ResponseEntity.ok(unidades);
    }
    
    @GetMapping("/unidades-para-relatorios")
    public ResponseEntity<List<GetUnidade3DTO>> UnidadesRelatorios() {
        List<GetUnidade3DTO> unidades = unidadeService.UnidadesRelatorios();
        return ResponseEntity.ok(unidades);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUnidade(@PathVariable UUID id) {
        unidadeService.excluirUnidade(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/incluir-setores")
    public ResponseEntity<GetUnidadeDTO> adicionarSetorEmUnidade(@RequestBody PostUnidadeIncluirSetoresDTO dto) {
        try {
            GetUnidadeDTO responseDTO = unidadeService.adicionarSetorEmUnidade(dto);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/incluir-funcaodoc")
    public ResponseEntity<GetUnidadeDTO> adicionarFuncaoDocEmUnidade(@RequestBody PostUnidadeIncluirFuncaoDocDTO dto) {
        try {
            GetUnidadeDTO responseDTO = unidadeService.adicionarSetorEmFuncaoDoc(dto);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/unidades/{idUnidade}/setores/{idSetor}")
    public ResponseEntity<GetUnidadeDTO> excluirSetor(
            @PathVariable UUID idUnidade,
            @PathVariable UUID idSetor
    ) {
        try {
            GetUnidadeDTO unidadeDTO = unidadeService.excluirSetor(idUnidade, idSetor);
            return ResponseEntity.ok(unidadeDTO);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    
    

    
    @DeleteMapping("/unidades/{idUnidade}/funcoes/{idFuncao}")
    public ResponseEntity<GetUnidadeDTO> excluirFuncao(@PathVariable UUID idUnidade, @PathVariable UUID idFuncao) {
        try {
            GetUnidadeDTO unidadeDTO = unidadeService.excluirFuncao(idUnidade, idFuncao);
            return ResponseEntity.ok(unidadeDTO);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
