-- ERP Array Enterprises - Criação das relações do banco de dados "ERPArrayEnterprises"

-- Arquivo: criacaoTabelas.sql

--DROP TABLE Produtos_Venda;
--DROP TABLE Lote;
--DROP TABLE Venda;
--DROP TABLE Cliente;
--DROP TABLE Produto;

CREATE TABLE Produto(
	codigo		INT PRIMARY KEY,
	nome		VARCHAR(50)
);

CREATE TABLE Cliente(
	codigo		INT PRIMARY KEY,
	nome		VARCHAR(50),
	ramo		VARCHAR(20),
	esp_ramo	VARCHAR(20)
);

CREATE TABLE Venda(
	codigo		INT PRIMARY KEY,
	codigo_cliente	INT,
	FOREIGN KEY 	(codigo_cliente) REFERENCES Cliente(codigo) ON DELETE SET NULL ON UPDATE SET NULL
);

CREATE TABLE Lote(
	codigo		INT PRIMARY KEY,
	codigo_produto	INT,
	dt_fabricacao	DATE,
	dt_validade	DATE,
	qtde_inicial	INT,
	qtde_atual	INT,
	FOREIGN KEY	(codigo_produto) REFERENCES Produto(codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Produtos_Venda(
	codigo_venda	INT,
	codigo_lote	INT,
	quantidade	INT,
	PRIMARY KEY	(codigo_venda, codigo_lote),
	FOREIGN KEY 	(codigo_venda) REFERENCES Venda(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY 	(codigo_lote) REFERENCES Lote(codigo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Usuario(
	nome_usuario	varchar(50),
	senha		varchar(6),
	PRIMARY KEY	(nome_usuario)
);
