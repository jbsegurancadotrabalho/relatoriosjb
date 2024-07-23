package br.com.jbst.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().components(new Components()).info(new Info().title("API dos Relatórios de Segurança do Trabalho - JBST Sistemas")
				.description("Nesta API temos os Documentos por Unidade\n"
						+ "PGR\n"
						+ "PCMSO\n"
						+ "LTCAT\n"
						+ "Plano de Emergencia\n"
						+ "Laudo Elétrico \n"
						+ "Laudo de Máquinas\n"
						+ "Laudo Ergonômico\n"
						+ "PCR - Programa de Controle Respiratorio\n"
						+ "PCA - Programa de Controle Auditivo\n"
						+ "Documentos por Cenários: APR, APP e PT\n"
						+ "Documentos por Funcionário\n"
						+ "Ficha de EPI\n"
						+ "Ordem de Serviço\n"
						+ "CAT\n"
						+ "PPP\n"
						+ "ASO.").version("v1"));
	}
}