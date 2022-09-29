<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>
            <% String contextPath=request.getContextPath().replace("/", "" ); %>
                <c:if test="${usuarioLogado.papel == 0}">
                    <!-- Se for um adm-->

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <link href="css/style.css" rel="stylesheet" />
                        <title>Perfil-ADM</title>
                    </head>

                    <body>
                        <header class="cabecalho">
                            <h3 class="cabecalho__titulo">LifeCare</h3>
                            <a class="cabecalho__link" href="/<%=contextPath%>/cliente/listar">Gerenciar Clientes</a>
                            <a class="cabecalho__link" href="/<%=contextPath%>/profissional/listar">Gerenciar
                                Profissionais</a>
                            <a class="cabecalho__link" href="/<%=contextPath%>/logout.jsp">Sair</a>
                        </header>
                        <div class="corpo">
                            <section>
                                <h1>Página do Administrador</h1>
                                <p>Olá ${usuarioLogado.nome}! Aqui você encontra todas as ferramentas para administrar seu sistema!</p>
                            </section>
                        </div>
                    </body>

            </html>
            </c:if>

            <c:if test="${usuarioLogado.papel == 1}">
                <!-- Se for um cliente-->

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <link href="css/style.css" rel="stylesheet" />
                    <title>Perfil-Cliente</title>
                </head>

                <body>
                    <header class="cabecalho">
                        <h3 class="cabecalho__titulo">LifeCare</h3>
                        <a class="cabecalho__link" href="/<%=contextPath%>/profissional/listar">Nossos Profissionais</a>
                        <a class="cabecalho__link" href="/<%=contextPath%>/logout.jsp">Sair</a>
                    </header>
                    <div class="corpo">
                        <section>
                            <c:choose>
                                <c:when test="${listaConsultas == null}">
                                    <h1>Olá ${usuarioLogado.nome},</h1>
                                    <p>Você não possui consultas agendadas.</p><br>
                                </c:when>
                                <c:otherwise>
                                    <h1>Olá ${usuarioLogado.nome},</h1>
                                    <p>Você possui essas consultas agendadas:</p><br>
                                    <section class="conteudo_tabelas">
                                        <table>
                                            <tr>
                                                <th>Profissional</th>
                                                <th>Area</th>
                                                <th>Especialidade</th>
                                                <th>Data</th>
                                                <th>Hora</th>
                                                <th>Estado</th>
                                            </tr>
                                            <c:forEach var="consulta" items="${listaConsultas}">
                                                <tr>
                                                    <td>${consulta.getProfissional().getNome()}</td>
                                                    <td>${consulta.getProfissional().getArea()}</td>
                                                    <td>${consulta.getProfissional().getEspecialidade()}</td>
                                                    <td>${consulta.data}</td>
                                                    <td>${consulta.hora}</td>
                                                    <td>${consulta.estado}</td>
                                                    <td>
                                                        <c:if test="${consulta.estado != \"CANCELADA\"}">
                                                            <a class="botao-primario"
                                                                href="/<%=contextPath%>/consulta/cancelar?id=${consulta.id}">Cancelar</a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </section>
                                </c:otherwise>
                            </c:choose>
                        </section>
                    </div>
                </body>

                </html>
            </c:if>
            <c:if test="${usuarioLogado.papel == 2}">
                <!-- Se for um profissional-->

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <link href="css/style.css" rel="stylesheet" />
                    <title>Perfil-Proffisional</title>
                </head>

                <body>
                    <header class="cabecalho">
                        <h3 class="cabecalho__titulo">LifeCare</h3>
                        <a class="cabecalho__link" href="/<%=contextPath%>/logout.jsp">Sair</a>
                    </header>
                    <div class="corpo">
                        <section>
                            <c:choose>
                                <c:when test="${listaConsultas == null}">
                                    <h1>Olá ${usuarioLogado.nome},</h1>
                                    <p>Ainda não há consultas agendadas.</p><br>
                                </c:when>
                                <c:otherwise>
                                    <h1>Olá ${usuarioLogado.nome},</h1>
                                    <p>Estas são as consultas agendadas com você:</p><br>
                                    <section class="conteudo_tabelas">
                                        <table>
                                            <tr>
                                                <th>Paciente</th>
                                                <th>CPF</th>
                                                <th>Telefone</th>
                                                <th>Data</th>
                                                <th>Hora</th>
                                                <th>Estado</th>
                                            </tr>
                                            <c:forEach var="consulta" items="${listaConsultas}">
                                                <tr>
                                                    <td>${consulta.getCliente().getNome()}</td>
                                                    <td>${consulta.getCliente().getCPF()}</td>
                                                    <td>${consulta.getCliente().getTelefone()}</td>
                                                    <td>${consulta.data}</td>
                                                    <td>${consulta.hora}</td>
                                                    <td>${consulta.estado}</td>

                                                    <td>
                                                        <c:if test="${consulta.estado != \"CANCELADA\"}">
                                                            <a class="botao-primario"
                                                                href="/<%=contextPath%>/consulta/cancelar?id=${consulta.id}">Cancelar</a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </section>
                                </c:otherwise>
                            </c:choose>
                        </section>
                    </div>
                </body>

                </html>
            </c:if>