package com.dtb.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dtb.api.security.entities.enums.PerfilEnum;
import com.dtb.api.security.entities.Usuario;

public class JwtUserFactory {
	private JwtUserFactory() {
		
	}
	
	/**
	 * Converte um funcionário em um JwtUser
	 * @param funcionario
	 * @return JwtUser
	 * */
	
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(),usuario.getEmail(),usuario.getSenha()
				,mapTograntedAuthorities(usuario.getPerfil()));
	}
	
	/**
	 * Converte o Enum Perfil em um formato utilizado pelo Spring Security
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 * */
	private static List<GrantedAuthority> mapTograntedAuthorities(PerfilEnum perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
	
}
