package com.dtb.api.security.services;

import java.util.Optional;

import com.dtb.api.security.entities.Usuario;

public interface UsuarioService {
	Optional<Usuario> buscarPorEmail(String email);
}
