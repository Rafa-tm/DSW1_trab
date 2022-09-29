package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/consulta/*")
public class ConsultaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ConsultaDAO dao;
    private ClienteDAO daoCliente;
    private ProfissionalDAO daoProfissional;
    Erro erros;

    public void init() {
        dao = new ConsultaDAO();
        daoCliente = new ClienteDAO();
        daoProfissional = new ProfissionalDAO();
        erros = new Erro();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/agendar":
                    formAgendar(request, response);
                    break;
                case "/inserir":
                    insere(request, response);
                    break;
                case "/cancelar":
                    cancela(request, response);
                    break;
                default:
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (erros.possuiErro()) {
            request.setAttribute("mensagens", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
            erros.limpaListaErros();
        }

    }

    private void formAgendar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Long idprofissional = Long.parseLong(request.getParameter("id"));

        if (usuarioLogado.getPapel() == 1 && usuarioLogado != null) {

            Long idcliente = daoCliente.getByEmail(usuarioLogado.getEmail()).getId();
            Profissional profissional = daoProfissional.get(idprofissional);

            request.setAttribute("profissional", profissional);
            request.setAttribute("idprofissional", idprofissional);
            request.setAttribute("idcliente", idcliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/agendarConsulta.jsp");
            dispatcher.forward(request, response);
        } else {
            erros.addErro("Apenas clientes podem agendar consultas. Acesso negado!");
        }

    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("UTF-8");

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        if (usuarioLogado.getPapel() == 1 && usuarioLogado != null) {

            Long idcliente = Long.parseLong(request.getParameter("idcliente"));
            Long idprofissional = Long.parseLong(request.getParameter("idprofissional"));
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data"));
            int hora = Integer.parseInt(request.getParameter("hora"));
            String estado = request.getParameter("estado");

            Cliente clienteConsulta = daoCliente.get(idcliente);
            Profissional profissionalConsulta = daoProfissional.get(idprofissional);

            if (dao.clienteDisponivel(data, hora, idcliente) == false) {
                erros.addErro("Você já possui uma consulta nessa data e horario!");
            }
            if (dao.profissionalDisponivel(data, hora, idprofissional) == false) {
                erros.addErro("O profissional não está disponivel na data e horario!");
            }
            if (erros.possuiErro()) {
                return;
            }

            Consulta consulta = new Consulta(null, clienteConsulta, profissionalConsulta, data, hora, estado);
            dao.insert(consulta);

            List<Consulta> listaConsultas = new ArrayList<>();
            listaConsultas = dao.getAllbyCliente(clienteConsulta.getId());
            request.getSession().setAttribute("listaConsultas", listaConsultas);
            response.sendRedirect(request.getContextPath() + "/perfil.jsp");
        } else {
            erros.addErro("Apenas clientes podem agendar consultas. Acesso negado!");
        }
    }

    private void cancela(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if ((usuarioLogado.getPapel() == 1 || usuarioLogado.getPapel() == 2) && usuarioLogado != null) {
            Consulta consulta = dao.get(id);

            LocalDate dataAtual = LocalDate.now();
            DateTimeFormatter moldeDias = DateTimeFormatter.ofPattern("dd");

            int diaConsulta = Integer.parseInt(new SimpleDateFormat("dd").format(consulta.getData()));
            int diaAtual = Integer.parseInt(dataAtual.format(moldeDias));

            int diferenca = diaConsulta - diaAtual;

            if(diferenca <0 ){
                diferenca = diferenca*(-1);
                diferenca = diaAtual - diferenca;
            }
            
            if(diferenca > 3){
                consulta.setEstado("CANCELADA");
                dao.update(consulta);
            }else{
                erros.addErro("Uma consulta só pode ser cancelada com 3 dias de antecedencia.");
                erros.addErro("Sua consulta é a " + diferenca + " dia(s)!");
                return;
            }

            if (usuarioLogado.getPapel() == 1) {
                List<Consulta> listaConsultas = new ArrayList<>();
                listaConsultas = dao.getAllbyCliente(consulta.getCliente().getId());
                request.getSession().setAttribute("listaConsultas", listaConsultas);
                request.getSession().setAttribute("teste", diferenca);
                response.sendRedirect(request.getContextPath() + "/perfil.jsp");
            }
            if (usuarioLogado.getPapel() == 2) {
                List<Consulta> listaConsultas = new ArrayList<>();
                listaConsultas = dao.getAllbyProfissional(consulta.getProfissional().getId());
                request.getSession().setAttribute("listaConsultas", listaConsultas);
                response.sendRedirect(request.getContextPath() + "/perfil.jsp");
            }

        } else {
            erros.addErro("Acesso negado! Apenas clientes e profissionais podem cancelar consultas");
        }
    }
}
