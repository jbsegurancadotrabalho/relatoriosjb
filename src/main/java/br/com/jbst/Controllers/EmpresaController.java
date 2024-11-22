package br.com.jbst.Controllers;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.GetAllEmpresaDTO;
import br.com.jbst.DTO.GetEmpresaDTO;
import br.com.jbst.DTO.Empresas.Unidades.GetEmpresa3DTO;
import br.com.jbst.DTO.Funcionarios.Documentos.GetEmpresa1DTO;
import br.com.jbst.services.EmpresaServices;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaServices empresaServices;

    @GetMapping("/{id}")
    public ResponseEntity<GetEmpresaDTO> buscarEmpresaPorId(@PathVariable("id") UUID id) {
        GetEmpresaDTO empresaDTO = empresaServices.buscarEmpresaPorId(id);
        if (empresaDTO != null) {
            return ResponseEntity.ok(empresaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 

    @GetMapping
    public ResponseEntity<List<GetAllEmpresaDTO>> buscarTodasEmpresasComDocumentosEFuncionarios() {
        List<GetAllEmpresaDTO> empresas = empresaServices.buscarTodasEmpresasComDocumentosEFuncionarios();
        if (empresas != null && !empresas.isEmpty()) {
            return ResponseEntity.ok(empresas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @GetMapping("/consultar-empresa-funcionarios")
    public ResponseEntity<List<GetEmpresa1DTO>> buscarTodasEmpresasFuncionarios() {
        List<GetEmpresa1DTO> empresas = empresaServices.buscarTodasEmpresasDocumentosFreeFuncionarios();
        if (empresas != null && !empresas.isEmpty()) {
            return ResponseEntity.ok(empresas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @GetMapping("/consultar-todas-unidades/{id}")
    public ResponseEntity<GetEmpresa3DTO> getUnidadesEmpresa(@PathVariable UUID id) {
        GetEmpresa3DTO empresaDTO = empresaServices.buscarUnidadesEmpresaPorId(id);
        if (empresaDTO != null) {
            return ResponseEntity.ok(empresaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/busca-por-cnpj/{cnpj}")
    public ResponseEntity<GetAllEmpresaDTO> buscarEmpresaPorCnpj(@PathVariable String cnpj) {
        Optional<GetAllEmpresaDTO> empresaDTO = empresaServices.buscarEmpresaPorCnpj(cnpj);
        return empresaDTO.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
