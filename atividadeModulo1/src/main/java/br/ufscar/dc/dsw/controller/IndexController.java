package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = { "/login.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Erro errosPage = new Erro();
		UsuarioDAO daoUsuario = new UsuarioDAO();
		ConsultaDAO daoConsulta = new ConsultaDAO();
		ClienteDAO daoCliente = new ClienteDAO();
		ProfissionalDAO daoProfissional = new ProfissionalDAO();

		if (request.getParameter("botaoLogin") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			if (email.isEmpty() || email == null) {
				errosPage.addErro("O campo de email não foi preenchido.");
			}
			if (senha.isEmpty() || senha == null) {
				errosPage.addErro("O campo de senha não foi preenchido.");
			}

			if (!errosPage.possuiErro()) {
				Usuario usuarioLogado = null;
				List<Consulta> listaConsultas = new ArrayList<>();
				try {
					usuarioLogado = daoUsuario.buscaUsuarioPorEmail(email);
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
				if (usuarioLogado != null) {
					if (usuarioLogado.getSenha().equals(senha)) {

						// login aprovado
						if (usuarioLogado.getPapel() == 1) {
							listaConsultas = daoConsulta.getAllbyCliente(daoCliente.getByEmail(email).getId());
						} else if (usuarioLogado.getPapel() == 2) {
							listaConsultas = daoConsulta.getAllbyProfissional(daoProfissional.getAllByEmail(email).getId());
						} else {
							listaConsultas = null;
						}

						request.getSession().setAttribute("listaConsultas", listaConsultas);
						request.getSession().setAttribute("usuarioLogado", usuarioLogado);
						response.sendRedirect(request.getContextPath() + "/perfil.jsp");
						return;
					} else {
						errosPage.addErro("Senha incorreta!");
					}
				} else {
					errosPage.addErro("Não existe um usuário cadastrado com essas credenciais!");
				}
			}
		}

		String URL = "/erro.jsp";
		if (request.getServletPath().equals("/logout.jsp")) {
			URL = "/index.jsp";
		} else {
			request.setAttribute("mensagens", errosPage);
		}

		request.getSession().invalidate();
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
}
