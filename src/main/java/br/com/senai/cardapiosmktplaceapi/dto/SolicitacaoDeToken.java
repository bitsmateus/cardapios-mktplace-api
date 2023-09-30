package br.com.senai.cardapiosmktplaceapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SolicitacaoDeToken {
	
	@NotBlank(message = "O login é obrigatorio")
	private String login;
	
	@NotBlank(message = "A senha é obrigatora")
	private String senha;

}
