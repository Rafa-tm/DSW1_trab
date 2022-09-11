package br.ufscar.dc.dsw.LifeCare.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@SuppressWarnings("serial")
@Entity
@Table(name = "Consulta")
@Inheritance(strategy = InheritanceType.JOINED)
public class Consulta extends AbstractEntity<Long> {

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idProfissional")
	private Profissional profissional;

	@Column(name = "dataConsulta", nullable = false, length = 40)
	private String dataConsulta;

	@Column(name = "estado", nullable = false, length = 15)
	private String estado;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
