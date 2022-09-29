<%@ page contentType="text/html" pageEncoding="UTF-8" %>
	<%@ page isELIgnored="false" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<html>
			<% String contextPath=request.getContextPath().replace("/", "" ); %>

				<head>
					<meta charset="UTF-8">
					<meta name="viewport" content="width=device-width, initial-scale=1">
					<link href="../css/style.css" rel="stylesheet" />
					<title>Agendar Consulta</title>
				</head>

				<body>
					<header class="cabecalho">
						<h3 class="cabecalho__titulo">LifeCare</h3>
						<a class="cabecalho__link" href="/<%=contextPath%>/perfil.jsp">Voltar</a>
					</header>
					<div class="corpo">
						<section class="conteudo_tabelas">
							<form class="conteudo__form" action="inserir" method="post">
								<h1>Selecione a data para a consulta com:</h1>
								<p>${profissional.nome} <br> ${profissional.area} especialista em ${profissional.especialidade}
								</p><br>

								<input type="hidden" name="idcliente" value="${idcliente}" />
								<input type="hidden" name="idprofissional" value="${idprofissional}" />

								<label class="conteudo__form-label" for="data">Data da consulta:</label><br>
								<input class="conteudo__form-input" type="date" name="data" required /><br />

								<label class="conteudo__form-label" for="hora">Hora da consulta:</label><br>
								<select class="conteudo__form-input" name="hora" ${cliente.adm} required>
									<c:forTokens items="07 08 09 10 11 14 15 16 17 18" delims=" " var="hora">
										<option value="${hora}">${hora}:00 a ${hora+1}:00</option>
									</c:forTokens>
								</select><br>

								<input type="hidden" name="estado" value="MARCADA" />

								<input class="botao-primario" type="submit" value="Agendar" />
							</form>
						</section>
					</div>
				</body>

			</html>