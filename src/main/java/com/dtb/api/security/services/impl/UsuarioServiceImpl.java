package com.dtb.api.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtb.api.security.entities.Usuario;
import com.dtb.api.security.repositores.UsuarioRepository;
import com.dtb.api.security.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscarPorEmail(String email){
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}
}
