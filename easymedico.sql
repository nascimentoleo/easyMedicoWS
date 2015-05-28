-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Mai 28, 2015 as 06:47 PM
-- Versão do Servidor: 5.5.8
-- Versão do PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `easymedico`
--
CREATE DATABASE `easymedico` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `easymedico`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `agendamentos`
--

CREATE TABLE IF NOT EXISTS `agendamentos` (
  `idagendamento` int(11) NOT NULL AUTO_INCREMENT,
  `nomePaciente` varchar(45) NOT NULL,
  `data` varchar(20) NOT NULL,
  `ordem` int(11) DEFAULT NULL,
  `hora` varchar(20) DEFAULT NULL,
  `medicos_user` varchar(20) NOT NULL,
  PRIMARY KEY (`idagendamento`),
  KEY `fk_agendamentos_medicos_idx` (`medicos_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Extraindo dados da tabela `agendamentos`
--


-- --------------------------------------------------------

--
-- Estrutura da tabela `localizacao_medicos`
--

CREATE TABLE IF NOT EXISTS `localizacao_medicos` (
  `medicos_user` varchar(20) NOT NULL,
  `latitude` varchar(30) DEFAULT NULL,
  `longitude` varchar(30) DEFAULT NULL,
  `ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`medicos_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `localizacao_medicos`
--

INSERT INTO `localizacao_medicos` (`medicos_user`, `latitude`, `longitude`, `ativo`) VALUES
('cubanas', '-123123.12312', '-123123.31231', 'S');

-- --------------------------------------------------------

--
-- Estrutura da tabela `medicos`
--

CREATE TABLE IF NOT EXISTS `medicos` (
  `user` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `especialidade` varchar(45) NOT NULL,
  `tipoAtendimento` varchar(45) DEFAULT NULL,
  `qtdPacientesPorDia` int(11) DEFAULT NULL,
  `qtdPacientesPorHora` int(11) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `medicos`
--

INSERT INTO `medicos` (`user`, `password`, `nome`, `especialidade`, `tipoAtendimento`, `qtdPacientesPorDia`, `qtdPacientesPorHora`) VALUES
('cubanas', 'cubanas', 'Cubanas', 'Casca de Banana', 'ORDEM_CHEGADA', 10, 1);

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD CONSTRAINT `fk_agendamentos_medicos` FOREIGN KEY (`medicos_user`) REFERENCES `medicos` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para a tabela `localizacao_medicos`
--
ALTER TABLE `localizacao_medicos`
  ADD CONSTRAINT `fk_localizacao_medicos_medicos1` FOREIGN KEY (`medicos_user`) REFERENCES `medicos` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION;
