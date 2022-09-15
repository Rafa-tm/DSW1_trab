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

			Cliente cliente = clienteDAO.findByEmail("rafael@email.com");

			if (cliente == null) {
				cliente = new Cliente();
				cliente.setNome("Rafael");
				cliente.setSenha(encoder.encode("1234"));
				cliente.setEmail("rafael@email.com");
				cliente.setCpf("47394784800");
				cliente.setTipo("ROLE_cliente");
				cliente.setSexo("masculino");
				cliente.setTelefone("1234567891");
				cliente.setDataNascimento("2000-12-12");
				clienteDAO.save(cliente);
			}

			Cliente cliente2 = clienteDAO.findByEmail("paulo@email.com");

			if (cliente2 == null) {
				cliente2 = new Cliente();
				cliente2.setNome("Paulo");
				cliente2.setSenha(encoder.encode("1234"));
				cliente2.setEmail("paulo@email.com");
				cliente2.setCpf("47393484800");
				cliente2.setTipo("ROLE_cliente");
				cliente2.setSexo("masculino");
				cliente2.setTelefone("1234567891");
				cliente2.setDataNascimento("2000-08-31");
				clienteDAO.save(cliente2);
			}

			Profissional profissional = profDAO.findByEmail("pedro@email.com");

			if (profissional == null) {
				profissional = new Profissional();
				profissional.setNome("Pedro");
				profissional.setSenha(encoder.encode("1234"));
				profissional.setEmail("pedro@email.com");
				profissional.setCpf("47694784800");
				profissional.setTipo("ROLE_profissional");
				profissional.setArea("Dentista");
				profissional.setEspecialidade("Cirurgia Bocal");
				profissional.setCurriculo(null);
				profDAO.save(profissional);
			}

			Profissional profissional2 = profDAO.findByEmail("joao@email.com");

			if (profissional2 == null) {
				profissional2 = new Profissional();
				profissional2.setNome("Joao");
				profissional2.setSenha(encoder.encode("1234"));
				profissional2.setEmail("joao@email.com");
				profissional2.setCpf("47694784800");
				profissional2.setTipo("ROLE_profissional");
				profissional2.setArea("Clinico Geral");
				profissional2.setEspecialidade("Cirurgia");
				profissional2.setCurriculo(null);
				profDAO.save(profissional2);
			}

			Profissional profissional3 = profDAO.findByEmail("augusto@email.com");

			if (profissional3 == null) {
				profissional3 = new Profissional();
				profissional3.setNome("Augusto");
				profissional3.setSenha(encoder.encode("1234"));
				profissional3.setEmail("augusto@email.com");
				profissional3.setCpf("47656784800");
				profissional3.setTipo("ROLE_profissional");
				profissional3.setArea("Clinico Geral");
				profissional3.setEspecialidade("Cirurgia");
				profissional3.setCurriculo(null);
				profDAO.save(profissional3);
			}

			Profissional profissional4 = profDAO.findByEmail("ana@email.com");

			if (profissional4 == null) {
				profissional4 = new Profissional();
				profissional4.setNome("Ana");
				profissional4.setSenha(encoder.encode("1234"));
				profissional4.setEmail("ana@email.com");
				profissional4.setCpf("47656784800");
				profissional4.setTipo("ROLE_profissional");
				profissional4.setArea("Psicologia");
				profissional4.setEspecialidade("Infantil");
				profissional4.setCurriculo(null);
				profDAO.save(profissional4);
			}

			Profissional profissional5 = profDAO.findByEmail("marcos@email.com");

			if (profissional5 == null) {
				profissional5 = new Profissional();
				profissional5.setNome("Marcos");
				profissional5.setSenha(encoder.encode("1234"));
				profissional5.setEmail("marcos@email.com");
				profissional5.setCpf("47656994800");
				profissional5.setTipo("ROLE_profissional");
				profissional5.setArea("Psicologia");
				profissional5.setEspecialidade("Casais");
				profissional5.setCurriculo(null);
				profDAO.save(profissional5);
			}

			Consulta consulta1 = new Consulta();
			cliente = clienteDAO.findByEmail("rafael@email.com");
			profissional = profDAO.findByEmail("pedro@email.com");
			consulta1.setCliente(cliente);
			consulta1.setProfissional(profissional);
			consulta1.setEstado("MARCADA");
			consulta1.setDataConsulta("2022-09-30T17:25");
			consultaDAO.save(consulta1);

			Consulta consulta2 = new Consulta();
			cliente = clienteDAO.findByEmail("rafael@email.com");
			profissional = profDAO.findByEmail("joao@email.com");
			consulta2.setCliente(cliente);
			consulta2.setProfissional(profissional);
			consulta2.setEstado("MARCADA");
			consulta2.setDataConsulta("2022-09-30T17:25");
			consultaDAO.save(consulta2);

			Consulta consulta3 = new Consulta();
			cliente = clienteDAO.findByEmail("rafael@email.com");
			profissional = profDAO.findByEmail("ana@email.com");
			consulta3.setCliente(cliente);
			consulta3.setProfissional(profissional);
			consulta3.setEstado("MARCADA");
			consulta3.setDataConsulta("2022-09-30T17:25");
			consultaDAO.save(consulta3);

			Consulta consulta4 = new Consulta();
			cliente = clienteDAO.findByEmail("paulo@email.com");
			profissional = profDAO.findByEmail("pedro@email.com");
			consulta4.setCliente(cliente);
			consulta4.setProfissional(profissional);
			consulta4.setEstado("MARCADA");
			consulta4.setDataConsulta("2022-09-30T17:25");
			consultaDAO.save(consulta4);

		};
	}
}
