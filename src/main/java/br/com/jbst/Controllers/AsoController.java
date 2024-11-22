package br.com.jbst.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DtoAtendimento.GetAnamneseDTO1;
import br.com.jbst.DtoAtendimento.GetAsoDTO;
import br.com.jbst.DtoAtendimento.PostAsoDTO;
import br.com.jbst.DtoAtendimento.PutAsoDTO;
import br.com.jbst.services.AsoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aso")
public class AsoController {

	@Autowired
	private AsoService asoService;

	// Endpoint para criar um novo ASO
	@PostMapping
	public ResponseEntity<GetAsoDTO> criarAso(@RequestBody PostAsoDTO dto) {
		GetAsoDTO createdAso = asoService.criarAso(dto);
		return ResponseEntity.ok(createdAso);
	}

	// Endpoint para editar um ASO existente
	@PutMapping("/{id_aso}")
	public ResponseEntity<GetAsoDTO> editarAso(@PathVariable UUID id_aso, @RequestBody PutAsoDTO dto) {
		dto.setId_aso(id_aso);
		GetAsoDTO updatedAso = asoService.editarAso(dto);
		return ResponseEntity.ok(updatedAso);
	}

	// Endpoint para buscar um ASO por ID
	@GetMapping("/{id_aso}")
	public ResponseEntity<GetAsoDTO> buscarAsoPorId(@PathVariable UUID id_aso) {
		GetAsoDTO aso = asoService.buscarAsoPorId(id_aso);
		return ResponseEntity.ok(aso);
	}

	// Endpoint para listar todos os ASOs
	@GetMapping
	public ResponseEntity<List<GetAsoDTO>> buscarTodosAsos() {
		List<GetAsoDTO> asos = asoService.buscarTodosAsos();
		return ResponseEntity.ok(asos);
	}
	
    @GetMapping("/aso-por-mes-ano")
    public ResponseEntity<List<GetAsoDTO>> consultarAsPorMesAno(
            @RequestParam("mes") int mes,
            @RequestParam("ano") int ano) {

        List<GetAsoDTO> aso = asoService.consultarAsoPorMesAno(mes, ano);
        return ResponseEntity.ok(aso);
    }
}
