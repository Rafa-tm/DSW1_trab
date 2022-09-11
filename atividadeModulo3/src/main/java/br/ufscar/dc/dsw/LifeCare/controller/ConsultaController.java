package br.ufscar.dc.dsw.LifeCare.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import br.ufscar.dc.dsw.LifeCare.domain.*;
import br.ufscar.dc.dsw.LifeCare.service.spec.IClienteService;
import br.ufscar.dc.dsw.LifeCare.service.spec.IConsultaService;
import br.ufscar.dc.dsw.LifeCare.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.LifeCare.service.spec.IUsuarioService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
  @Autowired
  IConsultaService consultaService;

  @Autowired
  IUsuarioService usuarioService;

  @Autowired
  IClienteService clienteService;

  @Autowired
  IProfissionalService profissionalService;

  @GetMapping("/agendar/{id_profissional}")
  public String agendar(@PathVariable("id_profissional") Long idp, Consulta consulta,
      Authentication auth,
      ModelMap model) {

    Cliente cliente = clienteService.buscarPorEmail(auth.getName());
    Profissional prof = profissionalService.buscarPorId(idp);

    model.addAttribute("cliente", cliente);
    model.addAttribute("profissional", prof);
    return "cadastroConsulta";
  }

  @PostMapping("/salvar")
  public String salvar(@Valid Consulta consulta,
      BindingResult result,
      Long id_profissional,
      Authentication auth) {

    Cliente cliente = clienteService.buscarPorEmail(auth.getName());
    Profissional prof = profissionalService.buscarPorId(id_profissional);

    consulta.setCliente(cliente);
    consulta.setProfissional(prof);
    consulta.setEstado("MARCADA");

    consultaService.salvar(consulta);
    return "redirect:/perfil";
  }

  @GetMapping("/cancelar/{id}")
  public String cancelar(@PathVariable("id") Long id, Model model) {

    Consulta consulta = consultaService.buscarPorId(id);
    String estado = "CANCELADA";
    consulta.setEstado(estado);

    consultaService.salvar(consulta);

    return "redirect:/perfil";
  }

}
