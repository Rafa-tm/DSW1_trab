package br.ufscar.dc.dsw.LifeCare.controller;

import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.LifeCare.domain.Profissional;
import br.ufscar.dc.dsw.LifeCare.service.spec.IProfissionalService;

@CrossOrigin
@RestController
public class ProfissionalRestController {

  @Autowired
  private IProfissionalService profissionalService;

  private boolean isJSONValid(String jsonInString) {
    try {
      return new ObjectMapper().readTree(jsonInString) != null;
    } catch (IOException e) {
      return false;
    }
  }

  private void parse(Profissional profissional, JSONObject json) {

    Object id = json.get("id");
    if (id != null) {
      if (id instanceof Integer) {
        profissional.setId(((Integer) id).longValue());
      } else {
        profissional.setId((Long) id);
      }
    }

    profissional.setNome((String) json.get("nome"));
    profissional.setEmail((String) json.get("email"));
    profissional.setSenha((String) json.get("senha"));
    profissional.setCpf((String) json.get("cpf"));
    profissional.setTipo((String) json.get("tipo"));
    profissional.setArea((String) json.get("area"));
    profissional.setEspecialidade((String) json.get("especialidade"));
    profissional.setCurriculo((byte[]) json.get("curriculo"));
  }

  // Cria um profissional [Create - CRUD]
  @PostMapping(path = "/profissionais")
  @ResponseBody
  public ResponseEntity<Profissional> cria(@RequestBody JSONObject json) {
    try {
      if (isJSONValid(json.toString())) {
        Profissional profissional = new Profissional();
        parse(profissional, json);
        profissionalService.salvar(profissional);
        return ResponseEntity.ok(profissional);
      } else {
        return ResponseEntity.badRequest().body(null);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
  }

  // Retorna a lista de profissionais [Read - CRUD]
  @GetMapping(path = "/profissionais")
  public ResponseEntity<List<Profissional>> lista() {
    List<Profissional> listaprofissionais = profissionalService.buscarTodos();
    if (listaprofissionais.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(listaprofissionais);
  }

  // Retorna o profissional de id = {id} [Read - CRUD]
  @GetMapping(path = "/profissionais/{id}")
  public ResponseEntity<Profissional> encontra(@PathVariable("id") long id) {
    Profissional profissional = profissionalService.buscarPorId(id);
    if (profissional == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(profissional);
  }

  // Retorna a lista de todos os profissionais de especialidade cujo nome = {nome}
  @GetMapping(path = "/profissionais/especialidade/{nome}")
  public ResponseEntity<List<Profissional>> encontraPorEspecialidade(@PathVariable("nome") String nome) {

    List<Profissional> listaEspecialidade = profissionalService.buscarPorEspecialidade(nome);

    if (listaEspecialidade.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(listaEspecialidade);
  }

  // Atualiza o profissional de id = {id} [Update - CRUD]
  @PutMapping(path = "/profissionais/{id}")
  public ResponseEntity<Profissional> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
    try {
      if (isJSONValid(json.toString())) {
        Profissional profissional = profissionalService.buscarPorId(id);

        if (profissional == null) {
          return ResponseEntity.notFound().build();
        } else {
          parse(profissional, json);
          profissionalService.salvar(profissional);
          return ResponseEntity.ok(profissional);
        }
      } else {
        return ResponseEntity.badRequest().body(null);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
  }

  // Remove o profissional de id = {id} [Delete - CRUD]
  @DeleteMapping(path = "/profissionais/{id}")
  public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
    Profissional profissional = profissionalService.buscarPorId(id);
    if (profissional == null) {
      return ResponseEntity.notFound().build();
    } else {
      profissionalService.excluir(id);
      return ResponseEntity.noContent().build();
    }
  }

}
