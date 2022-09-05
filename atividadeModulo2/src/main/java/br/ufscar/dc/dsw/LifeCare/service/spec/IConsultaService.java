package br.ufscar.dc.dsw.LifeCare.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.LifeCare.domain.Cliente;
import br.ufscar.dc.dsw.LifeCare.domain.Consulta;
import br.ufscar.dc.dsw.LifeCare.domain.Profissional;

public interface IConsultaService {

	void salvar(Consulta consulta);

	Consulta buscarPorId(Long id);

	List<Consulta> buscarPeloCliente(Cliente cliente);

	List<Consulta> buscarPeloProfissional(Profissional profissional);

	List<Consulta> buscarTodas();

}
