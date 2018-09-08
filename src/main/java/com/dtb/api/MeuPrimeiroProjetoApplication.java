package com.dtb.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dtb.api.entities.Empresa;
import com.dtb.api.entities.Funcionario;
import com.dtb.api.repositories.EmpresaRepository;
import com.dtb.api.repositories.FuncionarioRepository;
import com.dtb.api.security.entities.Usuario;
import com.dtb.api.security.entities.enums.PerfilEnum;
import com.dtb.api.security.repositores.UsuarioRepository;
import com.dtb.api.utils.SenhaUtils;

@SpringBootApplication
public class MeuPrimeiroProjetoApplication {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeuPrimeiroProjetoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setCnpj("XXXXXXXXYYYYZZ");
			empresa.setRazaoSocial("DTB WEB");
			this.empresaRepository.save(empresa);
			
			Funcionario funcionario = new Funcionario();
			funcionario.setCpf("XXXXXXXXXXX");
			funcionario.setNome("Robson William da Silva Matos");
			funcionario.setEmpresa(empresa);
			this.funcionarioRepository.save(funcionario);
			
			Usuario usuario = new Usuario();
			usuario.setEmail("robson.william@dtb.com");
			usuario.setSenha(SenhaUtils.gerarBCrypt("qwe123"));
			usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
			usuario.setFuncionario(funcionario);
			this.usuarioRepository.save(usuario);
			/*
			Usuario admin = new Usuario();
			admin.setEmail("admin@dtb.com");
			admin.setSenha(SenhaUtils.gerarBCrypt("qwe123"));
			admin.setPerfil(PerfilEnum.ROLE_ADMIN);
			this.usuarioRepository.save(admin);*/
		};
		
	}
}
