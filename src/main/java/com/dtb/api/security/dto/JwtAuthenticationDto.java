package com.dtb.api.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {
	

	@NotEmpty(message = "Email não pode ser vazio")
	@Email(message = "Email invalido")
	private String email;
	
	@NotEmpty(message = "Senha nao pode ser vazia")
	private String senha;
	public JwtAuthenticationDto() {
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
