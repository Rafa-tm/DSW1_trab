package br.ufscar.dc.dsw.LifeCare.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.LifeCare.domain.Profissional;
import br.ufscar.dc.dsw.LifeCare.service.spec.IProfissionalService;

@Controller
@RequestMapping("/")
public class IndexController {

  @Autowired
  IProfissionalService profissionalservice;

  @GetMapping("/")
  public String index(
      @RequestParam(name = "area", required = false) String area,
      @RequestParam(name = "especialidade", required = false) String especialidade,
      Model model) throws ParseException {

    List<Profissional> listaProfissionais = profissionalservice.buscarPorFiltro(area, especialidade);

    model.addAttribute("listaProfissionais", listaProfissionais);

    model.addAttribute("area", area);
    model.addAttribute("especialidade", especialidade);

    return "index";
  }

}
