DROP DATABASE IF EXISTS LifeCare1;
CREATE DATABASE LifeCare1;
USE LifeCare1;
CREATE TABLE Cliente (
	id SERIAL PRIMARY KEY,
	email varchar(256) not null unique,
	senha varchar(64) not null,
	CPF varchar(20) not null unique,
	nome varchar(256) not null,
	telefone varchar(20) not null,
	sexo varchar(20) not null,
	data_nascimento varchar(20) not null,
	adm boolean not null
);
CREATE TABLE Profissional(
	id SERIAL PRIMARY KEY,
	email varchar(256) not null unique,
	senha varchar(64) not null,
	CPF varchar(20) not null unique,
	nome varchar(256) not null,
	area varchar(20) not null,
	especialidade varchar(40) not null,
	link_curriculo varchar(256) not null
);
CREATE TABLE Consulta(
	id SERIAL PRIMARY KEY,
	idcliente bigint not null,
	idprofissional bigint not null,
	data varchar(20) not null,
	hora int not null,
	estado varchar(40) not null
);