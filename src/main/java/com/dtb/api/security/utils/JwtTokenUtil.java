package com.dtb.api.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	
	 
	
	
	/**
	 * Retorna uma String com o email do Token
	 * @param token
	 * @return String
	 * 
	 */
	 
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}catch(Exception e) {
			username = null;
		}
		return username;
	}
	/**
	 * Retorna um Date com o tempo de expiração do Token
	 * @param token
	 * @return Date
	 * 
	 * */
	public Date getExpirationFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		}catch(Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	/**
	 * Cria um token JWT
	 * @param token
	 * @return String
	 * 
	 * */
	
	public String  refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = gerarToken(claims);
		}catch(Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	/**
	 * Valida um token, retornando um boolean
	 * @param token
	 * @return boolean
	 * */
	
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}
	
	/**
	 * Retorna um novo token JWT com base nos dados do usuário
	 * @param userDetails
	 * @return String
	 * 
	 * */
	public String obterToken(UserDetails userdetails) {
		Map<String,Object> claims  = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userdetails.getUsername());
		userdetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		return gerarToken(claims);
	}
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			claims = null;
		}
		return claims;
	}
	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationFromToken(token);
		if(dataExpiracao == null) {
			return false;
		}else {
			return dataExpiracao.before(new Date());
		}
	}
	private String gerarToken(Map<String,Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
