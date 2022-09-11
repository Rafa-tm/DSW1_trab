package br.ufscar.dc.dsw.LifeCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.LifeCare.domain.*;

import br.ufscar.dc.dsw.LifeCare.service.spec.*;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
  @Autowired
  IUsuarioService usuarioService;

  @Autowired
  IClienteService clienteService;

  @Autowired
  IConsultaService consultaService;

  @Autowired
  IProfissionalService profissionalService;

  @GetMapping("")
  public String onLoad(Authentication auth, Model model) {
    String role = auth.getAuthorities().toArray()[0].toString();

    switch (role) {
      case "ROLE_profissional": {

        Profissional profissional = (Profissional) usuarioService.buscarPorEmail(auth.getName());
        List<Consulta> listaConsultasProfissional = consultaService.buscarPeloProfissional(profissional);

        model.addAttribute("listaConsultas", listaConsultasProfissional);
      }
        break;

      case "ROLE_cliente": {
        Cliente cliente = (Cliente) usuarioService.buscarPorEmail(auth.getName());

        List<Consulta> listaConsultasCliente = consultaService.buscarPeloCliente(cliente);
        model.addAttribute("listaConsultas", listaConsultasCliente);
      }
        break;

      case "ROLE_admin":
        List<Profissional> listaProfissional = profissionalService.buscarTodos();
        List<Cliente> listaCliente = clienteService.buscarTodos();
        model.addAttribute("listaProfissional", listaProfissional);
        model.addAttribute("listaCliente", listaCliente);
        break;

      default:
        break;
    }

    return "perfil";
  }
}
