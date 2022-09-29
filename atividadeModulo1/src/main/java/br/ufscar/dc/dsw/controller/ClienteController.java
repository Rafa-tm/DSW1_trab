package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/cliente/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ClienteDAO dao;
    public ConsultaDAO daoconsulta;
    Erro erros;

    public void init() {
        dao = new ClienteDAO();
        daoconsulta = new ConsultaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        erros = new Erro();
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        if (usuarioLogado != null && usuarioLogado.getPapel() == 0) {
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
                        listaClientes(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException | ParseException e) {
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

    private void formEdicaoCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        if (id != 0) {
            Cliente cliente = dao.get(id);
            request.setAttribute("cliente", cliente);
        } else {
            Cliente cliente = null;
            request.setAttribute("cliente", cliente);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/edicao.jsp");
        dispatcher.forward(request, response);
    }

    private void atualiza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String CPF = request.getParameter("CPF");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date data_nascimento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data_nascimento"));
        Boolean adm = Boolean.parseBoolean(request.getParameter("adm"));

        Cliente cliente = new Cliente(id, email, senha, CPF, nome, telefone, sexo, data_nascimento, adm);

        dao.update(cliente);
        response.sendRedirect("listar");
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String CPF = request.getParameter("CPF");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date data_nascimento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data_nascimento"));
        Boolean adm = Boolean.parseBoolean(request.getParameter("adm"));

        if (dao.getByEmail(email) != null) {
            erros.addErro("Email já cadastrado! Tente utilizar outro endereço de email.");
        }

        if (dao.verificaCPFUnico(CPF)) {
            erros.addErro("Este CPF já esta cadastrado em nosso sistema!");
        }

        if (erros.possuiErro()) {
            return;
        }

        Cliente cliente = new Cliente(null, email, senha, CPF, nome, telefone, sexo, data_nascimento, adm);
        dao.insert(cliente);
        response.sendRedirect("listar");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        Cliente cliente = dao.get(id);
        dao.delete(cliente);
        response.sendRedirect("listar");

    }

    private void listaClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cliente> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/lista.jsp");
        dispatcher.forward(request, response);
    }
}