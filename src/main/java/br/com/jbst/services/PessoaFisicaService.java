package br.com.jbst.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO2.GetPessoaFisicaDTO;
import br.com.jbst.entities.PessoaFisica;
import br.com.jbst.repositories.modulo2.IPessoaFisicaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class PessoaFisicaService {

    @Autowired
	IPessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetPessoaFisicaDTO buscarPessoaFisicaPorId(UUID idPessoaFisica) throws AccountNotFoundException {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(idPessoaFisica)
                .orElseThrow(() -> new AccountNotFoundException("Pessoa física não encontrada com o ID: " + idPessoaFisica));
        return modelMapper.map(pessoaFisica, GetPessoaFisicaDTO.class);
    }

    public List<GetPessoaFisicaDTO> buscarTodasAsPessoasFisicas() {
        List<PessoaFisica> pessoasFisicas = pessoaFisicaRepository.findAll();
        return pessoasFisicas.stream()
                .map(pessoaFisica -> modelMapper.map(pessoaFisica, GetPessoaFisicaDTO.class))
                .collect(Collectors.toList());
    }
}
