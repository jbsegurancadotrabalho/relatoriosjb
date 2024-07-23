package br.com.jbst.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import br.com.jbst.DTO2.GetAgendaDTO;
import br.com.jbst.DTO2.GetRelatoriosDTO;
import br.com.jbst.DTO2.PostRelatoriosDTO;
import br.com.jbst.DTO2.PutAgendaDTO;
import br.com.jbst.DTO2.PutRelatoriosDTO;
import br.com.jbst.entities.Agenda;
import br.com.jbst.entities.Relatorios;
import br.com.jbst.repositories.modulo1.IRelatoriosRepository;

@Service
public class RelatoriosService {

	@Autowired
	IRelatoriosRepository irelatorioRepository;

	@Autowired
	private ModelMapper modelMapper;

	public GetRelatoriosDTO criarRelatorio(PostRelatoriosDTO dto) {
		Relatorios relatorio = modelMapper.map(dto, Relatorios.class);

		// Gerar UUID para o relatório
		UUID idRelatorio = UUID.randomUUID();
		relatorio.setIdRelatorios(idRelatorio);

		// Gerar número do relatório (podendo ser um valor aleatório)
		int numeroRelatorio = gerarNumeroRelatorio();
		relatorio.setNumeroRelatorio(numeroRelatorio);

		// Salvar no banco de dados
		relatorio = irelatorioRepository.save(relatorio);

		// Mapear a entidade para DTO
		GetRelatoriosDTO relatorioDTO = modelMapper.map(relatorio, GetRelatoriosDTO.class);

		return relatorioDTO;
	}

	private Integer gerarNumeroRelatorio() {
		Integer ultimoNumero = irelatorioRepository.findMaxNumeroRelatorio();
		if (ultimoNumero == null) {
			ultimoNumero = 0;
		}
		return ultimoNumero + 1;
	}


	public GetRelatoriosDTO  editarRelatorios(PutRelatoriosDTO dto) throws Exception {
		// Verificar se o ID da agenda está presente no DTO
		if (dto.getIdRelatorios() == null) {
			throw new IllegalArgumentException("O ID do relatórios deve ser fornecido para editar a agenda.");
		}

		// Buscar a agenda no banco de dados pelo ID
		Optional<Relatorios> relatoriosOptional = irelatorioRepository.findById(dto.getIdRelatorios());
		if (relatoriosOptional.isEmpty()) {
			throw new Exception("Relatorio não encontrada com o ID: " + dto.getIdRelatorios());
		}

		// Obter a agenda existente do banco de dados
		Relatorios relatoriosExistente = relatoriosOptional.get();

		// Mapear os dados do DTO para a agenda existente
		modelMapper.map(dto,  relatoriosExistente);
		
		// Salvar as alterações no banco de dados
		Relatorios relatoriosAtualizado = irelatorioRepository.save(relatoriosExistente);

		// Mapear a agenda atualizada para o DTO de resposta
		return modelMapper.map(relatoriosAtualizado, GetRelatoriosDTO.class);
	}


	public GetRelatoriosDTO buscarRelatorioPorId(UUID id) {
		Relatorios relatorio = irelatorioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
		return modelMapper.map(relatorio, GetRelatoriosDTO.class);
	}

	// Buscar todos os relatórios
	public List<GetRelatoriosDTO> buscarTodosRelatorios() {
		List<Relatorios> relatorios = irelatorioRepository.findAll();
		return relatorios.stream().map(relatorio -> modelMapper.map(relatorio, GetRelatoriosDTO.class))
				.collect(Collectors.toList());
	}

	public void excluirRelatorioPorId(UUID id) {
		// Verifica se o relatório com o ID fornecido existe no banco de dados
		Relatorios relatorio = irelatorioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Relatório não encontrado"));

		// Exclui o relatório
		irelatorioRepository.delete(relatorio);
	}

}
