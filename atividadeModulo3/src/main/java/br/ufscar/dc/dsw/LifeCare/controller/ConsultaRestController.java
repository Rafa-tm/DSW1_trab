package br.ufscar.dc.dsw.LifeCare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.ufscar.dc.dsw.LifeCare.domain.Consulta;
import br.ufscar.dc.dsw.LifeCare.domain.Cliente;
import br.ufscar.dc.dsw.LifeCare.domain.Profissional;
import br.ufscar.dc.dsw.LifeCare.service.spec.IClienteService;
import br.ufscar.dc.dsw.LifeCare.service.spec.IConsultaService;
import br.ufscar.dc.dsw.LifeCare.service.spec.IProfissionalService;

@CrossOrigin
@RestController
public class ConsultaRestController {

  @Autowired
  private IConsultaService consultaService;

  @Autowired
  private IProfissionalService profissionalService;

  @Autowired
  private IClienteService clienteService;

  // Retorna a lista de consultas [Read - CRUD]
  @GetMapping(path = "/consultas")
  public ResponseEntity<List<Consulta>> lista() {
    List<Consulta> listaConsultas = consultaService.buscarTodas();
    if (listaConsultas.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(listaConsultas);
  }

  // Retorna a consulta de id = {id} [Read - CRUD]
  @GetMapping(path = "/consultas/{id}")
  public ResponseEntity<Consulta> encontra(@PathVariable("id") long id) {
    Consulta consulta = consultaService.buscarPorId(id);
    if (consulta == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(consulta);
  }

  // Retorna a lista das consultas do cliente de id = {id} [Read - CRUD]
  @GetMapping(path = "/consultas/clientes/{id}")
  public ResponseEntity<List<Consulta>> encontraPorCliente(@PathVariable("id") long id) {
    Cliente cliente = clienteService.buscarPorId(id);
    List<Consulta> consultasCliente = consultaService.buscarPeloCliente(cliente);
    if (consultasCliente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(consultasCliente);
  }

  // Retorna a lista das consultas do profissional de id = {id} [Read - CRUD]
  @GetMapping(path = "/consultas/profissionais/{id}")
  public ResponseEntity<List<Consulta>> encontraPorProfissional(@PathVariable("id") long id) {
    Profissional profissional = profissionalService.buscarPorId(id);
    List<Consulta> consultasProfissional = consultaService.buscarPeloProfissional(profissional);
    if (consultasProfissional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(consultasProfissional);
  }

}
