package br.ufscar.dc.dsw.domain;

public class Usuario {

	private String email;
	private String senha;
	private String CPF;
	private String nome;
	private int papel;

	public Usuario(String email, String senha, String CPF, String nome, int papel) {
		this.email = email;
		this.senha = senha;
		this.CPF = CPF;
		this.nome = nome;
		this.papel = papel;
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

	public int getPapel() {
		return this.papel;
	}

	public void setPapel(int papel) {
		this.papel = papel;
	}

}