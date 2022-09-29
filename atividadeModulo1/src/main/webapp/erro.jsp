<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>LifeCare</title>
        <link href="css/erro.css" rel="stylesheet" type="text/css" />
        <link href="../css/erro.css" rel="stylesheet" type="text/css" />
      </head>

      <body>
        <div class="corpo">
          <div class="container">
            <div>
              <h1>Ops! Alguma coisa deu errado.</h1>
              <c:if test="${mensagens != null}">
                <div>
                  <h2>Causa do erro:</h2>
                  <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                      <li class="erro">${erro}</li>
                    </c:forEach>
                  </ul>
                </div>
              </c:if>
            </div>
            <a href="javascript:history.back()"><button class="botao-retorno">Página anterior</button></a>
          </div>
        </div>

      </body>

      </html>