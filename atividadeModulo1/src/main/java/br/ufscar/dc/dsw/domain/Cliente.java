package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Cliente {

	private Long id;
	private String email;
	private String senha;
	private String CPF;
	private String nome;
	private String telefone;
	private String sexo;
	private Date data_nascimento;
	private boolean adm;

	public Cliente(Long id, String email, String senha, String CPF, String nome, String telefone, String sexo,
			Date data_nascimento, boolean adm) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.CPF = CPF;
		this.nome = nome;
		this.telefone = telefone;
		this.sexo = sexo;
		this.data_nascimento = data_nascimento;
		this.adm = adm;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCPF() {
		return this.CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getData_nascimento() {
		return this.data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public boolean isAdm() {
		return this.adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

}
