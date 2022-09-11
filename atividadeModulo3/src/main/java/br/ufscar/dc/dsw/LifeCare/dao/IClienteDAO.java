package br.ufscar.dc.dsw.LifeCare.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.LifeCare.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long> {

    Cliente findById(long id);

    Cliente findByEmail(String email);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    void deleteById(Long id);

}
