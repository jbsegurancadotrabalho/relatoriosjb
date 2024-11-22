package br.com.jbst.Controllers;




import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.Funcionarios.Documentos.GetFuncionarioDTO1;
import br.com.jbst.DTO.Unidades.Funcionarios.GetFuncionarioRelatorioDTO;
import br.com.jbst.dtos.relatorios.GetFuncionarioRelatoriosDTO;
import br.com.jbst.entities.Funcionario;
import br.com.jbst.services.FuncionarioServices;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioServices funcionarioService;


    @GetMapping
    public List<GetFuncionarioRelatoriosDTO> buscarTodosOsFuncionarios() {
        return funcionarioService.buscarTodosOsFuncionarios();
    }

    @GetMapping("/relatorios/{id}")
    public ResponseEntity<GetFuncionarioRelatorioDTO> buscarFuncionarioPorId(@PathVariable UUID id) {
    	GetFuncionarioRelatorioDTO funcionario = funcionarioService.buscarFuncionarioPorId(id);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }
    
    @GetMapping("/relatorios-gerar-documentos-free")
    public ResponseEntity<List<GetFuncionarioDTO1>> buscarTodosOsFuncionariosGerarRelatorios() {
        List<GetFuncionarioDTO1> funcionarios = funcionarioService.buscarTodosOsFuncionariosGerarRelatorios();
        return ResponseEntity.ok(funcionarios);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<GetFuncionarioDTO1>> buscarFuncionarios(
            @RequestParam UUID idEmpresa,
            @RequestParam String cpf) {
        List<GetFuncionarioDTO1> funcionarios = funcionarioService.buscarFuncionariosPorEmpresaECPF(idEmpresa, cpf);
        return ResponseEntity.ok(funcionarios);
    }
}
