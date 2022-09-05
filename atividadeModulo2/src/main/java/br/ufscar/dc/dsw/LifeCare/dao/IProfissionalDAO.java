package br.ufscar.dc.dsw.LifeCare.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.LifeCare.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long> {

    Profissional findById(long id);

    Profissional findByEmail(String email);

    List<Profissional> findAll();

    List<Profissional> findAllByArea(String area);

    List<Profissional> findAllByEspecialidade(String especialidade);

    Profissional save(Profissional profissional);

    void deleteById(Long id);
}
