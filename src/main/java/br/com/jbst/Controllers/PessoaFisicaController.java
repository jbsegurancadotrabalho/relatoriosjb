package br.com.jbst.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO2.GetPessoaFisicaDTO;
import br.com.jbst.services.PessoaFisicaService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoafisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @GetMapping("/{id}")
    public ResponseEntity<GetPessoaFisicaDTO> buscarPessoaFisicaPorId(@PathVariable UUID id) {
        try {
            GetPessoaFisicaDTO pessoaFisicaDTO = pessoaFisicaService.buscarPessoaFisicaPorId(id);
            return ResponseEntity.ok(pessoaFisicaDTO);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<List<GetPessoaFisicaDTO>> buscarTodasAsPessoasFisicas() {
        List<GetPessoaFisicaDTO> pessoasFisicasDTO = pessoaFisicaService.buscarTodasAsPessoasFisicas();
        return ResponseEntity.ok(pessoasFisicasDTO);
    }
}
