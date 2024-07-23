package br.com.jbst.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenarioDTO;
import br.com.jbst.DTO.Empresa.Documentos1.ConsultaUnidadeComAssociaçãoDTO;
import br.com.jbst.DTO.Empresa.Documentos2.ConsultarAssociacoeComUnidadesDTO;
import br.com.jbst.DTO.Empresa.Documentos2.ConsultarUnidadeDeAssociaoDTO;
import br.com.jbst.DTO2.GetAssociacoesCenarioDTO;
import br.com.jbst.services.AssociacoesService;
import br.com.jbst.services.DocumentosUnidadesServices;

@RestController
@RequestMapping("/api/unidades-documentos")
public class DocumentosUnidadesController {

    @Autowired
   AssociacoesService associacoesService;
	
    @Autowired
    private DocumentosUnidadesServices unidadeService;

    @GetMapping("/consultar-todas-unidades-com-associacao")
    public ResponseEntity<List<ConsultaUnidadeComAssociaçãoDTO>> getAllUnidadesComAssociacao() {
        List<ConsultaUnidadeComAssociaçãoDTO> unidades = unidadeService.consultarUnidadesDocumentos();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/consultar-unidade-com-associacao/{id}")
    public ResponseEntity<ConsultaUnidadeComAssociaçãoDTO> getUnidadeComAssociacaoPorId(@PathVariable UUID id) {
        try {
            ConsultaUnidadeComAssociaçãoDTO unidade = unidadeService.consultarUnidadesDocumentosPorId(id);
            return ResponseEntity.ok(unidade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar-todas-unidades-com-empresa")
    public ResponseEntity<List<ConsultarUnidadeDeAssociaoDTO>> getAllUnidadesComEmpresa() {
        List<ConsultarUnidadeDeAssociaoDTO> unidades = unidadeService.consultarUnidadesDocumentosEmpresa();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/consultar-unidade-com-empresa/{id}")
    public ResponseEntity<ConsultarUnidadeDeAssociaoDTO> getUnidadeComEmpresaPorId(@PathVariable UUID id) {
        try {
            ConsultarUnidadeDeAssociaoDTO unidade = unidadeService.consultarUnidadesDocumentosEmpresaPorId(id);
            return ResponseEntity.ok(unidade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultar-todas-associacoes-com-unidades")
    public ResponseEntity<List<ConsultarAssociacoeComUnidadesDTO>> getAllAssociacoeComUnidades() {
        List<ConsultarAssociacoeComUnidadesDTO> associacoes = unidadeService.consultarAssociacoeComUnidades();
        return ResponseEntity.ok(associacoes);
    }

    @GetMapping("/consultar-associacao-com-unidade/{id}")
    public ResponseEntity<ConsultarAssociacoeComUnidadesDTO> getAssociacoeComUnidadesPorId(@PathVariable UUID id) {
        try {
            ConsultarAssociacoeComUnidadesDTO associacao = unidadeService.consultarConsultarAssociacoeComUnidadesPorId(id);
            return ResponseEntity.ok(associacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
 
    
 
}
