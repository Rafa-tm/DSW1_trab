<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ page isELIgnored="false" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <html>
      <% String contextPath=request.getContextPath().replace("/", "" ); %>

        <head>
          <title>Lista Profissionais</title>
          <meta charset="UTF-8" />
          <meta name="viewport" content="width=device-width, initial-scale=1" />
          <link href="../css/style.css" rel="stylesheet" />
        </head>

        <body>
          <header class="cabecalho">
            <h3 class="cabecalho__titulo">LifeCare</h3>
            <c:if test="${usuarioLogado.papel == null}">
              <h4>Gerenciar Profissionais</h4>
              <a class="cabecalho__link" href="/<%=contextPath%>">Voltar</a>
            </c:if>
            <c:if test="${usuarioLogado.papel == 0}">
              <h4>Gerenciar Profissionais</h4>
              <a class="cabecalho__link" href="/<%=contextPath%>/profissional/editar?id=0">Adicionar novo</a>
              <a class="cabecalho__link" href="/<%=contextPath%>/perfil.jsp">Voltar</a>
            </c:if>
            <c:if test="${usuarioLogado.papel == 1}">
              <a class="cabecalho__link" href="/<%=contextPath%>/perfil.jsp">Perfil</a>
            </c:if>
          </header>
          <div class="corpo-prof">
            <form id="form" class="filtro" th:action="listar" method="post">
              <label for="filtroArea">Filtrar:</label>
              <input
                type="text"
                class="campo"
                name="filtroArea"
              />
              <input
                type="text"
                class="campo"
                name="filtroEspec"
              />
              <input
                type="submit"
                id="submit"
                class="botao-primario"
                name="botaoFiltro"
              />
            </form>
            <section class="conteudo_tabelas">
              <table>
                <tr>
                  <th>ID</th>
                  <th>Email</th>
                  <th>CPF</th>
                  <th>Nome</th>
                  <th>Area</th>
                  <th>Especialidade</th>
                  <th>Curriculo</th>
                </tr>
                <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                  <tr>
                    <td>${profissional.id}</td>
                    <td>${profissional.email}</td>
                    <td>${profissional.CPF}</td>
                    <td>${profissional.nome}</td>
                    <td>${profissional.area}</td>
                    <td>${profissional.especialidade}</td>
                    <td><a class="botao-primario" href="/<%= contextPath%>/${profissional.linkCurriculo}"
                        target="_blank">Curriculo</a>
                    </td>
                    <c:if test="${usuarioLogado.papel == 0}">
                      <td>
                        <a class="botao-primario"
                          href="/<%= contextPath%>/profissional/editar?id=${profissional.id}">Editar</a>
                      </td>
                      <td>
                        <a class="botao-primario"
                          href="/<%= contextPath%>/profissional/remover?id=${profissional.id}">Remover</a>
                      </td>
                    </c:if>
                    <c:if test="${usuarioLogado.papel == 1}">
                      <td>
                        <a class="botao-primario"
                          href="/<%= contextPath%>/consulta/agendar?id=${profissional.id}">Agendar Consulta</a>
                      </td>
                    </c:if>
                  </tr>
                </c:forEach>
              </table>
            </section>
          </div>
        </body>

      </html>