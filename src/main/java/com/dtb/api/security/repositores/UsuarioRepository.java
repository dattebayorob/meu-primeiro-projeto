package com.dtb.api.security.repositores;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.api.security.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByEmail(String email);
}
