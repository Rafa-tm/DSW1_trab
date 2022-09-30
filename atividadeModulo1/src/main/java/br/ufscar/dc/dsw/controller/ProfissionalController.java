package br.ufscar.dc.dsw.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissional/*")
@MultipartConfig
public class ProfissionalController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProfissionalDAO dao;
	Erro erros;

	public void init() {
		dao = new ProfissionalDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		erros = new Erro();
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		if ((usuarioLogado != null && usuarioLogado.getPapel() == 0) || action.equals("/listar")) {
			try {
				switch (action) {
					case "/inserir":
						insere(request, response);
						break;
					case "/remover":
						remove(request, response);
						break;
					case "/editar":
						formEdicaoCadastro(request, response);
						break;
					case "/atualizar":
						atualiza(request, response);
						break;
					default:
						listaProfissionais(request, response);
						break;
				}
			} catch (RuntimeException | IOException | ServletException e) {
				throw new ServletException(e);
			}
		} else {
			erros.addErro("Acesso negado! Faça login como administrador.");
		}

		if (erros.possuiErro()) {
			request.setAttribute("mensagens", erros);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
			dispatcher.forward(request, response);
			erros.limpaListaErros();
		}

	}

	private String gravaCurriculo(Part curriculo) {
		String link_curriculo = null;
		String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			link_curriculo = uploadPath + File.separator + curriculo.getSubmittedFileName();
			curriculo.write(link_curriculo);
			link_curriculo = "upload/" + curriculo.getSubmittedFileName();
			return link_curriculo;
		} catch (Exception ex) {
			erros.addErro("Erro no upload: " + ex.getMessage());
		}
		return link_curriculo;
	}

	private void formEdicaoCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("id"));
		if (id != 0) {
			Profissional profissional = dao.get(id);
			request.setAttribute("profissional", profissional);
		} else {
			Profissional profissional = null;
			request.setAttribute("profissional", profissional);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/edicao.jsp");
		dispatcher.forward(request, response);
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String CPF = request.getParameter("CPF");
		String nome = request.getParameter("nome");
		String area = request.getParameter("area");
		String especialidade = request.getParameter("especialidade");
		Part Curriculo = request.getPart("linkCurriculo");

		String linkCurriculo = gravaCurriculo(Curriculo);

		Profissional prestador = new Profissional(null, email, senha, CPF, nome, area, especialidade, linkCurriculo);
		dao.insert(prestador);
		response.sendRedirect("lista");
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Long id = Long.parseLong(request.getParameter("id"));

		Profissional profissional = dao.get(id);
		dao.delete(profissional);
		response.sendRedirect("lista");

	}

	private void atualiza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String CPF = request.getParameter("CPF");
		String nome = request.getParameter("nome");
		String area = request.getParameter("area");
		String especialidade = request.getParameter("especialidade");
		String linkCurriculo = request.getParameter("linkCurriculo");

		Profissional profissional = new Profissional(id, email, senha, CPF, nome, area, especialidade, linkCurriculo);
		dao.update(profissional);
		response.sendRedirect("lista");
	}

	private void listaProfissionais(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				List<Profissional> listaProfissionais = dao.getAll();

				String filtroArea = request.getParameter("filtroArea");
				String filtroEspec = request.getParameter("filtroEspec");

				if(filtroArea != null || filtroEspec != null){
					if( filtroEspec == null || filtroEspec.isEmpty()){
						listaProfissionais = dao.getAllbyFiltro(filtroArea, null);
					}else{
						listaProfissionais = dao.getAllbyFiltro(filtroArea, filtroEspec);
					}
				}

		request.setAttribute("listaProfissionais", listaProfissionais);
		request.setAttribute("filtroArea", filtroArea);
		request.setAttribute("filtroEspec", filtroEspec);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/lista.jsp");
		dispatcher.forward(request, response);
	}

}