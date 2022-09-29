<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@page isELIgnored="false" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<html>
			<% String contextPath=request.getContextPath().replace("/", "" ); %>

				<head>
					<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
					<title>Editar Profissional</title>
					<link href="../css/style.css" rel="stylesheet" type="text/css" />
				</head>

				<body>
					<header class="cabecalho">
						<h3 class="cabecalho__titulo">LifeCare</h3>
						<a class="cabecalho__link" href="/<%=contextPath%>/profissional/listar">Voltar</a>
					</header>
					<div class="corpo">
						<section class="conteudo_tabelas">
							<c:choose>
								<c:when test="${profissional != null}">
									<form class="conteudo__form" action="atualizar" method="post">

										<input type="hidden" name="id" value="${profissional.id}" />

										<label class="conteudo__form-label" for="email">E-mail:</label><br>
										<input class="conteudo__form-input" type="email" name="email" value="${profissional.email}"
											required /><br />

										<label class="conteudo__form-label" for="password">Senha:</label><br>
										<input class="conteudo__form-input" type="password" name="senha" value="${profissional.senha}"
											required /><br />

										<label class="conteudo__form-label" for="CPF">CPF:</label><br>
										<input class="conteudo__form-input" type="text" name="CPF" value="${profissional.CPF}"
											required /><br />

										<label class="conteudo__form-label" for="nome">Nome:</label><br>
										<input class="conteudo__form-input" type="text" name="nome" value="${profissional.nome}"
											required /><br />

										<label class="conteudo__form-label" for="area">Area:</label><br>
										<input class="conteudo__form-input" type="text" name="area" value="${profissional.area}"
											required /><br />

										<label class="conteudo__form-label" for="especialidade">Especialidade:</label><br>
										<input class="conteudo__form-input" type="text" name="especialidade"
											value="${profissional.especialidade}" required /><br />

										<input type="hidden" id="linkCurriculo" name="linkCurriculo" value="${profissional.linkCurriculo}">

										<input class="botao-primario" type="submit" value="Atualizar" />
									</form>
								</c:when>
								<c:otherwise>
									<form class="conteudo__form" action="inserir" method="post" enctype="multipart/form-data">
										<label class="conteudo__form-label" for="email">E-mail:</label><br>
										<input class="conteudo__form-input" type="email" name="email" required /><br />

										<label class="conteudo__form-label" for="password">Senha:</label><br>
										<input class="conteudo__form-input" type="password" name="senha" required /><br />

										<label class="conteudo__form-label" for="CPF">CPF:</label><br>
										<input class="conteudo__form-input" type="text" name="CPF" required /><br />

										<label class="conteudo__form-label" for="nome">Nome:</label><br>
										<input class="conteudo__form-input" type="text" name="nome" required /><br />

										<label class="conteudo__form-label" for="area">Area:</label><br>
										<input class="conteudo__form-input" type="text" name="area" required /><br />

										<label class="conteudo__form-label" for="especialidade">Especialidade:</label><br>
										<input class="conteudo__form-input" type="text" name="especialidade" required /><br />

										<label for="linkCurriculo" class="form-label">Curriculo:</label>
										<input type="file" id="linkCurriculo" name="linkCurriculo"><br /><br />

										<input class="botao-primario" type="submit" value="Cadastrar" />
									</form>
								</c:otherwise>
							</c:choose>
						</section>
					</div>
				</body>

			</html>