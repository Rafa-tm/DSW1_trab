package br.ufscar.dc.dsw.LifeCare.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.LifeCare.dao.IConsultaDAO;
import br.ufscar.dc.dsw.LifeCare.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.LifeCare.domain.Profissional;
import br.ufscar.dc.dsw.LifeCare.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	IProfissionalDAO dao;

	@Autowired
	IConsultaDAO consultaDAO;

	@Transactional(readOnly = true)
	public Profissional buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}

	public void salvar(Profissional profissional) {
		dao.save(profissional);
	}

	public void excluir(Long id) {
		consultaDAO.deleteAllByProfissional(this.buscarPorId(id));
		dao.deleteById(id);
	}

	@Override
	public List<Profissional> buscarPorFiltro(String area, String especialidade) {

		List<Profissional> listaProfissinalTodos = dao.findAll();

		if ((area == null || area.isEmpty()) && (especialidade == null || especialidade.isEmpty())) {
			return dao.findAll();
		}

		List<Profissional> listaProfissionalPelaArea = null;
		List<Profissional> listaProfissionalPelaEspecialidade = null;

		List<Profissional> listaProfissional = new ArrayList<Profissional>();

		if (area != null && !area.isEmpty()) {
			listaProfissionalPelaArea = dao.findAllByArea(area);
		}

		if (especialidade != null && !especialidade.isEmpty()) {
			listaProfissionalPelaEspecialidade = dao.findAllByEspecialidade(especialidade);
		}

		for (Profissional profissional : listaProfissinalTodos) {
			if ((listaProfissionalPelaArea == null || listaProfissionalPelaArea.contains(profissional)) &&
					(listaProfissionalPelaEspecialidade == null || listaProfissionalPelaEspecialidade.contains(profissional))) {

				listaProfissional.add(profissional);
			}
		}
		return listaProfissional;
	}
}
