package br.ufscar.dc.dsw.LifeCare.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufscar.dc.dsw.LifeCare.domain.Profissional;
import br.ufscar.dc.dsw.LifeCare.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    IProfissionalService service;

    @Autowired
    ServletContext context;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/salvar")
    public String salvar(@Valid Profissional profissional,
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            @RequestParam("curriculoFile") MultipartFile curriculo,
            Model model) throws IOException {

        Boolean error = false;
        Boolean hasOtherErros = false;

        if (curriculo.isEmpty()) {
            model.addAttribute("curriculoFile", "O campo currículo é obrigatorio.");
            hasOtherErros = true;
        } else if (!curriculo.getOriginalFilename().split("\\.")[1].equals("pdf")) {
            model.addAttribute("curriculoFile", "O currículo deve ser uma arquivo PDF");
            hasOtherErros = true;
        }

        if (!profissional.getSenha().equals(confirmarSenha)) {
            model.addAttribute("confirmarSenhaErro", "As senhas estão diferentes");
            error = true;
        }

        if (result.hasErrors() || error) {
            model.addAttribute("result", result);
            return "cadastroProfissional";
        }

        String curriculoDir = addFile(curriculo);
        profissional.setCurriculo(curriculoDir);

        profissional.setSenha(encoder.encode(profissional.getSenha()));
        profissional.setTipo("ROLE_profissional");

        service.salvar(profissional);

        return "redirect:/perfil";
    }

    private String addFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename().split("\\.")[0] + "."
                + file.getOriginalFilename().split("\\.")[1];

        String uploadPath = context.getRealPath("") + File.separator + "upload";
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        file.transferTo(new File(uploadDir, fileName));

        return File.separator + "upload" + File.separator + fileName;
    }

    @GetMapping("/delete/{id}")
    public String excluir(@PathVariable("id") long id) {
        service.excluir(id);

        return "redirect:/perfil";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("profissional", service.buscarPorId(id));

        return "admin/formularioEdicaoProfissional";
    }

    @PostMapping("/editar")
    public String editar(@Valid Profissional profissional, BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model) {
        Boolean error = false;

        if (!profissional.getSenha().equals(confirmarSenha)) {
            model.addAttribute("confiramarSenhaErro", "As senhas estão diferentes");
            error = true;
        }

        if (result.hasErrors() || error) {
            model.addAttribute("result", result);
            return "admin/formularioEdicaoProfissional";
        }

        profissional.setSenha(encoder.encode(profissional.getSenha()));
        service.salvar(profissional);
        return "redirect:/perfil";
    }
}
