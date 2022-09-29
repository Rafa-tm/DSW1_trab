package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Consulta {

	private Long id;
	private Cliente cliente;
	private Profissional profissional;
	private Date data;
	private int hora;
	private String estado;

	public Consulta(Long id, Cliente cliente, Profissional profissional, Date data, int hora, String estado) {
		this.id = id;
		this.cliente = cliente;
		this.profissional = profissional;
		this.data = data;
		this.hora = hora;
		this.estado = estado;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Profissional getProfissional() {
		return this.profissional;
	}

	public void setPrestador(Profissional profissional) {
		this.profissional = profissional;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getHora() {
		return this.hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
