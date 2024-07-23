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

import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenario1DTO;
import br.com.jbst.DTO.Cenarios.Documentos.GetAssociacaoCenarioDTO;
import br.com.jbst.DTO.Cenarios.Documentos.PostAssociacoesCenarioDTO;
import br.com.jbst.DTO2.GetAssociacoesCenarioDTO;
import br.com.jbst.DTO2.GetAssociacoesUnidadeDocDTO;
import br.com.jbst.DTO2.PostAssociacoesFuncionarioDTO;
import br.com.jbst.DTO2.PostAssociacoesUnidadeDocDTO;
import br.com.jbst.DTO2.PutAssociacoesFuncionarioDTO;
import br.com.jbst.DTO2.PutAssociacoesUnidadeDocDTO;
import br.com.jbst.dtos.relatorios.GetAssociacoesRelatoriosFuncionarioDTO;
import br.com.jbst.dtos.relatorios.GetAssociacoesRelatoriosUnidadesDTO;
import br.com.jbst.services.AssociacoesService;

@RestController
@RequestMapping("/associacoes") // A raiz para acessar os endpoints relacionados a associações
public class AssociacoesController {

    @Autowired
   AssociacoesService associacoesService;

    @PostMapping("/criar-associacao-unidadedoc") // Endpoint para criar uma nova associação
    public ResponseEntity<GetAssociacoesUnidadeDocDTO> criarAssociacoesUnidadeDoc(@RequestBody PostAssociacoesUnidadeDocDTO dto) {
        try {
            GetAssociacoesUnidadeDocDTO novaAssociacao = associacoesService.criarAssociacoesUnidadeDoc(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAssociacao);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o erro no console para debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/criar-associacao-cenario")
    public ResponseEntity<GetAssociacaoCenarioDTO> criarAssociacao(@RequestBody PostAssociacoesCenarioDTO dto) {
        try {
            GetAssociacaoCenarioDTO responseDto = associacoesService.criarAssociacoesCenario(dto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    
   
    
    @PutMapping("/editar-associacao-unidadedoc") // Endpoint para editar uma associação existente
    public ResponseEntity<GetAssociacoesUnidadeDocDTO> editarAssociacoesUnidadeDoc(@RequestBody PutAssociacoesUnidadeDocDTO dto) {
        try {
            GetAssociacoesUnidadeDocDTO associacaoAtualizada = associacoesService.editarAssociacoesUnidadeDoc(dto);
            if (associacaoAtualizada != null) {
                return ResponseEntity.ok(associacaoAtualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o erro no console para debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/criar-associacao-funcionario")
    public ResponseEntity<GetAssociacoesUnidadeDocDTO> criarAssociacoesFuncionarios(@RequestBody PostAssociacoesFuncionarioDTO dto) {
        try {
            GetAssociacoesUnidadeDocDTO novaAssociacao = associacoesService.criarAssociacoesFuncionarios(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAssociacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/editar-associacao-funcionario")
    public ResponseEntity<GetAssociacoesUnidadeDocDTO> editarAssociacoesFuncionarios(@RequestBody PutAssociacoesFuncionarioDTO dto) {
        try {
            GetAssociacoesUnidadeDocDTO associacaoAtualizada = associacoesService.editarAssociacoesFuncionarios(dto);
            if (associacaoAtualizada != null) {
                return ResponseEntity.ok(associacaoAtualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/buscar-associações-unidades")
    public ResponseEntity<List<GetAssociacoesRelatoriosUnidadesDTO>> buscarTodasAssociacoes() {
        try {
            List<GetAssociacoesRelatoriosUnidadesDTO> associacoes = associacoesService.buscarTodasAssociacoesUnidade();
            return ResponseEntity.ok(associacoes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/buscar-associações-funcionario")
    public List<GetAssociacoesRelatoriosFuncionarioDTO> buscarTodasAssociacoesFuncionario() {
        try {
            return associacoesService.buscarTodasAssociacoesFuncionario();
        } catch (Exception e) {
            // Aqui você pode lidar com exceções, como lançar uma exceção personalizada ou
            // retornar uma resposta de erro apropriada para o cliente.
            e.printStackTrace();
            return null; // Retorno temporário para simplificar o exemplo
        }
    }
    
 
    
    @GetMapping("/associacoess/{id}")
    public ResponseEntity<GetAssociacoesUnidadeDocDTO> buscarAssociacoesPorId(@PathVariable UUID id) {
        GetAssociacoesUnidadeDocDTO associacao = associacoesService.buscarAssociacoesPorId(id);
        if (associacao != null) {
            return ResponseEntity.ok(associacao);
        } else {
            return ResponseEntity.notFound().build();
        }       
    }
    
    @GetMapping("/associacoes-cenario/{id}")
    public ResponseEntity<GetAssociacaoCenarioDTO> buscarPorId(@PathVariable UUID id) {
        try {
        	GetAssociacaoCenarioDTO dto = associacoesService.buscarAssociacaoCenarioPorId(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Not Found
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }
    
    @GetMapping("/associacoes-dos-cenarios")
    public ResponseEntity<List<GetAssociacaoCenario1DTO>> consultarAssociacoesDosCenarios() {
        List<GetAssociacaoCenario1DTO> associacoes = associacoesService.consultarAssociacoesDosCenarios();
        return ResponseEntity.ok(associacoes);
    }
    
	@DeleteMapping("/associacoes/{id}")
    public ResponseEntity<Void> excluirAssociacoesPorId(@PathVariable UUID id) {
        associacoesService.excluirAssociacoesPorId(id);
        return ResponseEntity.noContent().build();
    }
}
