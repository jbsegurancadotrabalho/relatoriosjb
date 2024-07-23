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

import br.com.jbst.DTO.GetEmpresaDTO;
import br.com.jbst.DTO.GetEmpresaDocDTO;
import br.com.jbst.DTO.PostEmpresaDocDTO;
import br.com.jbst.DTO.PutEmpresaDocCnaeDTO;
import br.com.jbst.DTO.PutEmpresaDocDTO;
import br.com.jbst.DTO.Empresas.Unidades.GetEmpresaDoc3DTO;
import br.com.jbst.services.EmpresaDocService;

@RestController
@RequestMapping("/empresa-doc")
public class EmpresaDocController {

    @Autowired
    private EmpresaDocService empresaDocService;

    @PostMapping
    public ResponseEntity<GetEmpresaDocDTO> criarEmpresaDoc(@RequestBody PostEmpresaDocDTO dto) {
        try {
            GetEmpresaDocDTO empresaDocDTO = empresaDocService.criarEmpresaDoc(dto);
            return new ResponseEntity<>(empresaDocDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetEmpresaDocDTO>> getAllEmpresaDocs() {
        List<GetEmpresaDocDTO> empresaDocsDTO;
        try {
            empresaDocsDTO = empresaDocService.getAllEmpresaDocs();
            return new ResponseEntity<>(empresaDocsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/consultar-unidades-empresas")
    public ResponseEntity<List<GetEmpresaDoc3DTO>> consultarTodasUnidades() {
        List<GetEmpresaDoc3DTO> empresaDocsDTO;
        try {
            empresaDocsDTO = empresaDocService.consultarTodasUnidades();
            return new ResponseEntity<>(empresaDocsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<GetEmpresaDocDTO> getEmpresaDocById(@PathVariable("id") UUID id) {
        try {                                                 
            GetEmpresaDocDTO empresaDocDTO = empresaDocService.getEmpresaDocById(id);
            return new ResponseEntity<>(empresaDocDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmpresaDoc(@PathVariable("id") UUID id) {
        try {
            empresaDocService.excluirEmpresaDoc(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<GetEmpresaDocDTO> editarEmpresaDoc(@RequestBody PutEmpresaDocDTO dto) {
        try {
            GetEmpresaDocDTO empresaDocDTO = empresaDocService.editarEmpresaDoc(dto);
            return new ResponseEntity<>(empresaDocDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("Consultar-Todas-as-Empresas")
    public ResponseEntity<List<GetEmpresaDTO>> getAllEmpresas() {
        List<GetEmpresaDTO> empresaDocsDTO;
        try {
            empresaDocsDTO = empresaDocService.getAllEmpresa();
            return new ResponseEntity<>(empresaDocsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/cnaes")
    public ResponseEntity<String> atualizarCnaesEmpresaDoc(@RequestBody PutEmpresaDocCnaeDTO dto) {
        try {
            empresaDocService.atualizarCnaesEmpresaDoc(dto);
            return ResponseEntity.ok("CNAEs da EmpresaDoc atualizados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
