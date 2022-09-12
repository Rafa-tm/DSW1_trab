package br.ufscar.dc.dsw.LifeCare.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Profissional")
@PrimaryKeyJoinColumn(name = "idUsuario")
public class Profissional extends Usuario {

	@NotBlank(message = "{NotBlank.profissional.area}")
	@Size(max = 60)
	@Column(name = "area", nullable = false, length = 60)
	private String area;

	@NotBlank(message = "{NotBlank.profissional.especialidade}")
	@Size(max = 60)
	@Column(name = "especialidade", nullable = false, length = 60)
	private String especialidade;

	@Lob
	private byte[] curriculo;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public byte[] getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(byte[] curriculo) {
		this.curriculo = curriculo;
	}

}
