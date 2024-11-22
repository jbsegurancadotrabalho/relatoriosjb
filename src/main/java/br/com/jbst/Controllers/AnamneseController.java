package br.com.jbst.Controllers;




import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.jbst.DTO2.GetAgendamentoDTO;
import br.com.jbst.DtoAtendimento.GetAnamneseDTO;
import br.com.jbst.DtoAtendimento.GetAnamneseDTO1;
import br.com.jbst.DtoAtendimento.PostAnamneseDTO;
import br.com.jbst.DtoAtendimento.PutAnamneseDTO;
import br.com.jbst.services.AnamneseService;

@RestController
@RequestMapping("/anamneses")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

    @PostMapping
    public ResponseEntity<GetAnamneseDTO> criarAnamnese(@RequestBody PostAnamneseDTO dto) {
        GetAnamneseDTO anamneseDTO = anamneseService.criarAnamnese(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(anamneseDTO);
    }

    @PutMapping("/{idAnamnese}")
    public ResponseEntity<GetAnamneseDTO> editarAnamnese(
            @PathVariable UUID idAnamnese,
            @RequestBody PutAnamneseDTO dto) {
        dto.setIdAnamnese(idAnamnese);
        GetAnamneseDTO anamneseDTO = anamneseService.editarAnamnese(dto);
        return ResponseEntity.ok(anamneseDTO);
    }

    @GetMapping("/{idAnamnese}")
    public ResponseEntity<GetAnamneseDTO> buscarAnamnesePorId(@PathVariable UUID idAnamnese) {
        GetAnamneseDTO anamneseDTO = anamneseService.buscarAnamnesePorId(idAnamnese);
        return ResponseEntity.ok(anamneseDTO);
    }

    @GetMapping
    public ResponseEntity<List<GetAnamneseDTO1>> buscarTodasAnamneses() {
        List<GetAnamneseDTO1> anamneses = anamneseService.buscarTodasAnamneses();
        return ResponseEntity.ok(anamneses);
    }
    
    
    @GetMapping("/anamnese-por-mes-ano")
    public ResponseEntity<List<GetAnamneseDTO1>> consultarAgendamentoFuncionariosPorMesAno(
            @RequestParam("mes") int mes,
            @RequestParam("ano") int ano) {

        List<GetAnamneseDTO1> anamneses = anamneseService.consultarAnamnesePorMesAno(mes, ano);
        return ResponseEntity.ok(anamneses);
    }
 	 
}
