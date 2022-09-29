<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <html>
      <% String contextPath=request.getContextPath().replace("/", "" ); %>

        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
          <title>LifeCare</title>
          <link href="css/index.css" rel="stylesheet" type="text/css" />
        </head>

        <body>
          <header class="cabecalho">
            <h3 class="cabecalho__titulo">LifeCare</h3>
            <a class="cabecalho__link" href="/<%=contextPath%>/profissional/listar">Nossos Profissionais</a>
          </header>
          <div class="corpo">
            <section class="conteudo">
              <form class="conteudo__form" action="login.jsp" method="POST">
                <div>
                  <label class="conteudo__form-label" for="email">E-mail:</label><br />
                  <input class="conteudo__form-input" type="text" name="email" /><br />
                  <label class="conteudo__form-label" for="password">Senha:</label><br />
                  <input class="conteudo__form-input" type="password" name="senha" /><br />
                </div>
                <input class="botao-primario" type="submit" name="botaoLogin" value="Entrar" />
              </form>
            </section>
          </div>
        </body>

      </html>