package com.dtb.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtb.api.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	Funcionario findByCpf(String cpf);
}
