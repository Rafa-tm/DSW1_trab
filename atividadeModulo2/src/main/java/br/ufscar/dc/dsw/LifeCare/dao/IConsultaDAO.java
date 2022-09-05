package br.ufscar.dc.dsw.LifeCare.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.LifeCare.domain.Cliente;
import br.ufscar.dc.dsw.LifeCare.domain.Consulta;
import br.ufscar.dc.dsw.LifeCare.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {

    Consulta findById(long id);

    List<Consulta> findByCliente(Cliente cliente);

    List<Consulta> findByProfissional(Profissional profissional);

    List<Consulta> findAll();

    Consulta save(Consulta consulta);

    void deleteAllByCliente(Cliente cliente);

    void deleteAllByProfissional(Profissional profissional);
}
