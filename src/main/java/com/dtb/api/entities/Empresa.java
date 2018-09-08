package com.dtb.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="empresa")

public class Empresa  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -142875434814909875L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="razao_social", nullable=false)
	private String razaoSocial;
	@Column(name="cnpj", nullable = false)
	private String cnpj;
	@Column(name="data_criacao",nullable=false)
	private Date dataCriacao;
	@Column(name="data_atualizacao",nullable=false)
	private Date dataAtualizacao;
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	/**
	 * Mapeamento relacional, um para muitos
	 * mappedBy é necessário ja que é a que possui nenhum ou vários
	 * fetch lazy para não fazer a consulta da lista de funcionários no momento da consulta
	 * Cascade ALL para persistir em todos os funcionários as alterações na empresa
	 * 
	 * */
	private List<Funcionario> funcionarios;
	
	public Empresa() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	@PrePersist 
	/** No momento da criação da entidade,
	 *  a data de criação e atualização serão as datas atuais 
	 *  */
	public void PrePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
		
	@PreUpdate
	/** No momento da atualização da entidade,
	 * a data de atualização será a data atual,
	 *  porem data de criação será mantida
	 * */
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + "]";
	}
	
	

}
