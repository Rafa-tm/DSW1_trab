package br.ufscar.dc.dsw.domain;

public class Profissional {

	private Long id;
	private String email;
	private String senha;
	private String CPF;
	private String nome;
	private String area;
	private String especialidade;
	private String linkCurriculo;

	public Profissional(Long id, String email, String senha, String CPF, String nome, String area, String especialidade,
			String linkCurriculo) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.CPF = CPF;
		this.nome = nome;
		this.area = area;
		this.especialidade = especialidade;
		this.linkCurriculo = linkCurriculo;
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

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getLinkCurriculo() {
		return this.linkCurriculo;
	}

	public void setLinkCurriculo(String linkCurriculo) {
		this.linkCurriculo = linkCurriculo;
	}

}
