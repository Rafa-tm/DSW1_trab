# Sobre este repositório

Este repositório contém os 3 trabalhos desenvolvidos na disciplina de Desenvolvimento de Software Web 1 da UFSCar. A disciplina é focada no desenvolvimento back-end utilizando da linguagem Java.

# Tecnologias utilizadas

Para o primeiro módulo utilizou-se de Servlet, JSP, JSTL e JDBC para o server-side.

Nos segundo módulo foi utilizado SpringMVC, Thymeleaf e Spring Data JPA para o server-side.

E para o terceiro e ultimo módulo foram utilizados as mesmas tecnologias do segundo, porém com todas as funcionalidades sendo providas por REST API. 

Em todos os módulos, foram utilizadas HTML, Javascript e CSS para o client-side.

# Requisitos dos módulos no projeto

1. CRUD (Create, Read, Update e Delete) de clientes (requer login de
administrador). O sistema deve possuir um cadastro de clientes, com os seguintes dados: e-mail, senha, CPF, nome, telefone, sexo e data de nascimento.

2. CRUD de profissionais (requer login de administrador). O sistema deve possuir um cadastro de profissionais com os seguintes dados: e-mail, senha, CPF, nome, área de conhecimento (medicina, advocacia, psicologia) e especialidade (por exemplo, médico cardiologista, advogado criminal e psicólogo infantil) e suas qualificações/histórico profissional (upload do currículo em formato PDF).
3. Listagem de todos os profissionais liberais em uma única página (não requer login). O sistema deve prover a funcionalidade de filtrar os profissionais por área de conhecimento (por exemplo, medicina e psicologia) e posteriormente filtrar pela especialidade (médico cardiologista).
4. Agendamento de horário de consulta com um profissional (requer login do cliente via email e senha). Ao clicar em um profissional (requisito R3), o cliente pode cadastrar uma consulta. Para isso, deve escolher uma data/horário, e deve ser gravado o horário da consulta na base de dados.
5. O sistema não deve permitir o cadastro de consulta de um mesmo profissional ou de um mesmo cliente em uma mesma data/horário.
6. Listagem de todas os horários de consultas de um cliente (requer login do cliente via e-mail e senha). Depois de fazer login, o cliente pode visualizar todos os seus horários de consultas cadastrados.
7. Listagem de todas as consultas de um profissional (requer login do profissional
via e-mail e senha). Depois de fazer login, o profissional poderá visualizar todos os horários de atendimento cadastrados, bem como os horários bloqueados.
8. Clientes ou profissionais poderão cancelar consultas com no mínimo 3 dias de antecedência. A lista de consultas dos clientes deverá ser atualizada e as consultas canceladas deverão aparecer nessa lista. Para fazer os testes, o grupo poderá rodarscripts para alterar o banco de dados na hora da apresentação ou deixar consultas previamente cadastradas.
9. O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas técnicos, acesso ilegal etc.) mostrando uma página de erros amigável ao usuário e registrando o erro no console.

## Para o terceiro módulo houve a incrementação dos requisitos:

- REST API – CRUD de clientes
  - Cria um cliente [Create - CRUD]
    - POST http://localhost:8080/clientes
    - Body: raw/JSON (application/json)
  - Retorna a lista de clientes [Read - CRUD]
    - GET http://localhost:8080/clientes
  - Retorna o cliente de id = {id} [Read - CRUD]
    - GET http://localhost:8080/clientes/{id}
  - Atualiza o cliente de id = {id} [Update - CRUD]
    - PUT http://localhost:8080/clientes/{id}
    - Body: raw/JSON (application/json)
  - Remove o cliente de id = {id} [Delete - CRUD]
    - DELETE http://localhost:8080/clientes/{id}

- REST API -- CRUD de profissionais
  - Cria um profissional [Create - CRUD]
    - POST http://localhost:8080/profissionais
    - Body: raw/JSON (application/json)
  - Retorna a lista de profissionais [Read - CRUD]
    - GET http://localhost:8080/profissionais
  - Retorna o profissional de id = {id} [Read - CRUD]
    - GET http://localhost:8080/profissionais/{id}
  - Retorna a lista de todos os profissionais de especialidade cujo nome = {nome}
    - GET http://localhost:8080/profissionais/especialidades/{nome}
  - Atualiza o profissional de id = {id} [Update - CRUD]
    - PUT http://localhost:8080/profissionais/{id}
    - Body: raw/JSON (application/json)
  - Remove o profissional de id = {id} [Delete - CRUD]
    - DELETE http://localhost:8080/profissionais/{id}

- REST API
  - Retorna a lista de consultas [Read - CRUD]
    - GET http://localhost:8080/consultas
  - Retorna a consulta de id = {id} [Read - CRUD]
    - GET http://localhost:8080/consultas/{id}
  - Retorna a lista das consultas do cliente de id = {id} - [Read - CRUD]
    - GET http://localhost:8080/consultas/clientes/{id}
  - Retorna a lista das consultas do profissional de id = {id} [Read - CRUD]
    - GET http://localhost:8080/consultas/profissionais/{id}
