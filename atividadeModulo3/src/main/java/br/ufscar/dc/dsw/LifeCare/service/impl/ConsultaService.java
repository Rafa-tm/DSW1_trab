package br.ufscar.dc.dsw.LifeCare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.LifeCare.dao.IConsultaDAO;
import br.ufscar.dc.dsw.LifeCare.domain.Cliente;
import br.ufscar.dc.dsw.LifeCare.domain.Consulta;
import br.ufscar.dc.dsw.LifeCare.domain.Profissional;
import br.ufscar.dc.dsw.LifeCare.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

	@Autowired
	IConsultaDAO dao;

	public void salvar(Consulta consulta) {
		dao.save(consulta);
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPeloCliente(Cliente cliente) {
		return dao.findByCliente(cliente);
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPeloProfissional(Profissional profissional) {
		return dao.findByProfissional(profissional);
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarTodas() {
		return dao.findAll();
	}

	@Override
	public Consulta buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

}
