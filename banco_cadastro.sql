
DROP DATABASE IF EXISTS tcc2;
CREATE DATABASE tcc2;
USE tcc2;

-- Criando a tabela Usuario
CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    contra_senha VARCHAR(255) NOT NULL
);

-- Criando a tabela QRCode com o campo status e ID como VARCHAR
CREATE TABLE QRCode (
    id VARCHAR(255) PRIMARY KEY,  -- ID como VARCHAR
    apelido VARCHAR(255),
    status INT NOT NULL,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

-- Criando a tabela Animal
CREATE TABLE Animal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_responsavel VARCHAR(255),
    endereco_responsavel VARCHAR(255),
    telefone_responsavel VARCHAR(255),
    email_responsavel VARCHAR(255),
    nome_animal VARCHAR(255),
    tipo_animal VARCHAR(255),
    raca_animal VARCHAR(255),
    descricao_animal VARCHAR(255),
    foto_animal VARCHAR(255),  -- Campo de imagem do tipo VARCHAR para link
    observacao_animal VARCHAR(255),
    qrcode_id VARCHAR(255),
    FOREIGN KEY (qrcode_id) REFERENCES QRCode(id)
);

-- Criando a tabela Objeto
CREATE TABLE Objeto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_responsavel VARCHAR(255),
    endereco_responsavel VARCHAR(255),
    telefone_responsavel VARCHAR(255),
    email_responsavel VARCHAR(255),
    nome_objeto VARCHAR(255),
    descricao_objeto VARCHAR(255),
	foto_animal VARCHAR(255),  -- Campo de imagem do tipo VARCHAR para link
    observacao_objeto VARCHAR(255),
    qrcode_id VARCHAR(255),
    FOREIGN KEY (qrcode_id) REFERENCES QRCode(id)
);

-- Criando a tabela Pessoa
CREATE TABLE Pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_responsavel VARCHAR(255),
    endereco_responsavel VARCHAR(255),
    telefone_responsavel VARCHAR(255),
    email_responsavel VARCHAR(255),
    nome_pessoa VARCHAR(255),
    patologia_pessoa VARCHAR(255),
    medicacao_pessoa VARCHAR(255),
    tipo_sanguineo_pessoa VARCHAR(255),
     foto_animal VARCHAR(255),  -- Campo de imagem do tipo VARCHAR para link
    observacao_pessoa VARCHAR(255),
    qrcode_id VARCHAR(255),
    FOREIGN KEY (qrcode_id) REFERENCES QRCode(id)
);

-- Inserindo alguns dados de exemplo
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('Xy9GhT2R1B', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('Ae7LuP8Q3N', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR1A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR2A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR3A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR4A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR5A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR6A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR7A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR8A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR9A2B3C4D', NULL, 0, NULL);
INSERT INTO QRCode (id, apelido, status, usuario_id) VALUES ('QR10A2B3C4D', NULL, 0, NULL);


-- Consultas de exemplo
SELECT * FROM QRCode; 
SELECT * FROM Usuario;
SELECT * FROM Animal;
SELECT * FROM Pessoa;
SELECT * FROM Objeto;
