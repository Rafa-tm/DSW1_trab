package br.ufscar.dc.dsw.LifeCare.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.ufscar.dc.dsw.LifeCare.domain.Cliente;
import br.ufscar.dc.dsw.LifeCare.service.spec.IClienteService;

@CrossOrigin
@RestController
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Cliente cliente, JSONObject json) throws ParseException {
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
		}
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		cliente.setNome((String) json.get("nome"));
		cliente.setEmail((String) json.get("email"));
		cliente.setSenha((String) json.get("senha"));
		cliente.setCpf((String) json.get("cpf"));
		cliente.setTipo((String) json.get("tipo"));
		cliente.setTelefone((String) json.get("telefone"));
		cliente.setSexo((String) json.get("sexo"));
		cliente.setDataNascimento((String) json.get("dataNascimento"));

	}

	// Cria um cliente [Create - CRUD]
	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				clienteService.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	// Retorna a lista de clientes [Read - CRUD]
	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> listaclientes = clienteService.buscarTodos();
		if (listaclientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaclientes);
	}

	// Retorna o cliente de id = {id} [Read - CRUD]
	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> encontra(@PathVariable("id") long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	// Atualiza o cliente de id = {id} [Update - CRUD]
	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = clienteService.buscarPorId(id);

				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					clienteService.salvar(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	// Remove o cliente de id = {id} [Delete - CRUD]
	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			clienteService.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}

}