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

import br.com.jbst.DTO2.GetSubstacoesDTO;
import br.com.jbst.DTO2.PostSubstacoesDTO;
import br.com.jbst.DTO2.PutSubstacoesDTO;
import br.com.jbst.services.SubstacoesServices;

@RestController
@RequestMapping("/substacoes")
public class SubstacoesController {

	@Autowired
	private SubstacoesServices substacoesServices;

	@PostMapping
	public ResponseEntity<GetSubstacoesDTO> criarSubstacao(@RequestBody PostSubstacoesDTO dto) {
		try {
			GetSubstacoesDTO novaSubstacao = substacoesServices.criarSubstacoes(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(novaSubstacao);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<GetSubstacoesDTO> editarSubstacao(@RequestBody PutSubstacoesDTO dto) {
		try {
			GetSubstacoesDTO substacaoAtualizada = substacoesServices.editarSubstacoes(dto);
			return ResponseEntity.ok().body(substacaoAtualizada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetSubstacoesDTO> buscarSubstacaoPorId(@PathVariable UUID id) {
		try {
			GetSubstacoesDTO substacao = substacoesServices.buscarSubstacaoPorId(id);
			return ResponseEntity.ok().body(substacao);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<List<GetSubstacoesDTO>> buscarTodasSubstacoes() {
		List<GetSubstacoesDTO> todasSubstacoes = substacoesServices.buscarTodasSubstacoes();
		return ResponseEntity.ok().body(todasSubstacoes);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirSubstacao(@PathVariable UUID id) {
		try {
			substacoesServices.excluirSubstacaoPorId(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
