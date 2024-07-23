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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jbst.DTO2.GetFotosDTO;
import br.com.jbst.DTO2.PostFotosAnexosDTO;
import br.com.jbst.DTO2.PostFotosEspecializacaoSaudeEntityDTO;
import br.com.jbst.DTO2.PostFotosFormacaoSaudeDTO;
import br.com.jbst.DTO2.PostFotosMaquinasDTO;
import br.com.jbst.DTO2.PostFotosUnidadeDocDTO;
import br.com.jbst.entities.FotosAnexosEntity;
import br.com.jbst.services.FotosService;

@RestController
@RequestMapping("/api/fotos")
public class FotosController {
    
    @Autowired
	FotosService fotosService;

    @PostMapping("/inserir-imagens-formacao")
    public ResponseEntity<String> CriarFotosFormacao(@RequestBody PostFotosFormacaoSaudeDTO  dto) {
        try {
        	fotosService.criarFotosFormacao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Foto criada com Sucesso !");
        } catch (Exception e) {
            // Handle exception here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar fotos: " + e.getMessage());
        }
    }
    
    @PostMapping("/inserir-imagens-especializacao")
    public ResponseEntity<String> criarFotosEspecializacaoSaude(@RequestBody PostFotosEspecializacaoSaudeEntityDTO dto) {
        try {
            fotosService.criarFotosEspecializaçãoSaude(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fotos de especialização em saúde criadas com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar fotos de especialização em saúde: " + e.getMessage());
        }
    }
    
    @PostMapping("/inserir-imagens-maquinas")
    public ResponseEntity<String> CriarFotosMaquinass(@RequestBody PostFotosMaquinasDTO dto) {
        try {
            fotosService.criarFotosMaquinas(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fotos de máquinas criadas com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar fotos de máquinas: " + e.getMessage());
        }
    }
    
    @PostMapping("/inserir-imagens-unidadedoc")
    public ResponseEntity<String> criarFotosUnidadeDoc(@RequestBody PostFotosUnidadeDocDTO dto) {
        try {
            fotosService.UnidadeDoc(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fotos da unidade documental criadas com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar fotos da unidade documental: " + e.getMessage());
        }
    }
    
    @PostMapping("/inserir-imagens-anexos")
    public ResponseEntity<String> criarFotosAnexos(@RequestBody PostFotosAnexosDTO dto) {
        try {
            fotosService.FotosAnexos (dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fotos do anexo incluida com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao criar fotos da unidade documental: " + e.getMessage());
        }
    }
    
    @PutMapping(value = "incluir-fotos", consumes = { "multipart/form-data" })
	public ResponseEntity<FotosAnexosEntity> IncluirFotos(@RequestParam("id") UUID id,
			@RequestParam("fotos") MultipartFile fotos) throws Exception {

		// capturando o tipo do arquivo
		String tipo = fotos.getContentType();

		// verificando se o formato é válido (JPG, JPEG, PNG)
		if (tipo.equals("image/jpg") || tipo.equals("image/jpeg") || tipo.equals("image/png")) {
			// atualizando a foto do produto e retornando a resposta
			FotosAnexosEntity result =   fotosService.incluirFotos(id, fotos.getBytes());
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
    
    @GetMapping("/buscar-todas-fotos")
    public ResponseEntity<List<GetFotosDTO>> BuscarTodasAsFotos() {
        List<GetFotosDTO> fotosDTOList = fotosService.buscarTodasAsFotos();
        return new ResponseEntity<>(fotosDTOList, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetFotosDTO> buscarFotosPorId(@PathVariable UUID id) {
        GetFotosDTO fotosDTO = fotosService.buscarFotosPorId(id);
        return new ResponseEntity<>(fotosDTO, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirFotoPorId(@PathVariable UUID id) {
        fotosService.excluirFotoPorId(id);
        return new ResponseEntity<>("Foto excluída com sucesso", HttpStatus.OK);
    }
}
