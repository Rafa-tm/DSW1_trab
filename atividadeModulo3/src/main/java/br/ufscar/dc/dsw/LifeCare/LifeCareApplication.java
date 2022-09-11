package br.ufscar.dc.dsw.LifeCare;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.LifeCare.dao.*;
import br.ufscar.dc.dsw.LifeCare.domain.*;

@SpringBootApplication
public class LifeCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeCareApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IClienteDAO clienteDAO, IProfissionalDAO profDAO,
			IConsultaDAO consultaDAO,
			BCryptPasswordEncoder encoder) {
		return (args) -> {

			Usuario admin = usuarioDAO.findByEmail("admin@email.com");

			if (admin == null) {
				admin = new Usuario();
				admin.setNome("admin");
				admin.setSenha(encoder.encode("admin"));
				admin.setEmail("admin@email.com");
				admin.setCpf("473947");
				admin.setTipo("ROLE_admin");
				usuarioDAO.save(admin);
			}

			Cliente cliente = clienteDAO.findByEmail("cliente@email.com");
			Date data = new Date();

			if (cliente == null) {
				cliente = new Cliente();
				cliente.setNome("Rafael");
				cliente.setSenha(encoder.encode("1234"));
				cliente.setEmail("cliente@email.com");
				cliente.setCpf("47394784800");
				cliente.setTipo("ROLE_cliente");
				cliente.setSexo("masculino");
				cliente.setTelefone("1234567891");
				cliente.setDataNascimento(data);
				clienteDAO.save(cliente);
			}

			Profissional profissional = profDAO.findByEmail("profissional@email.com");

			if (profissional == null) {
				profissional = new Profissional();
				profissional.setNome("Pedro");
				profissional.setSenha(encoder.encode("1234"));
				profissional.setEmail("profissional@email.com");
				profissional.setCpf("47694784800");
				profissional.setTipo("ROLE_profissional");
				profissional.setArea("Dentista");
				profissional.setEspecialidade("Cirurgia Bocal");
				profissional.setCurriculo("curriculo");
				profDAO.save(profissional);
			}

		};
	}
}
