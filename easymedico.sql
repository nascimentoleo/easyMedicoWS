-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 18, 2016 at 04:02 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `easymedico`
--
CREATE DATABASE IF NOT EXISTS `easymedico` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `easymedico`;

-- --------------------------------------------------------

--
-- Table structure for table `agendamentos`
--

DROP TABLE IF EXISTS `agendamentos`;
CREATE TABLE IF NOT EXISTS `agendamentos` (
  `idagendamento` int(11) NOT NULL AUTO_INCREMENT,
  `nomePaciente` varchar(45) NOT NULL,
  `data` varchar(20) NOT NULL,
  `hora` varchar(20) DEFAULT NULL,
  `medicos_user` varchar(20) NOT NULL,
  `IMEI` varchar(200) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idagendamento`),
  KEY `fk_agendamentos_medicos_idx` (`medicos_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `agendamentos`
--

INSERT INTO `agendamentos` (`idagendamento`, `nomePaciente`, `data`, `hora`, `medicos_user`, `IMEI`, `telefone`) VALUES
(1, 'Édipo', '18/03/2016', '07:00', 'freud', '355480065226206', '32164235'),
(15, 'Seu Madruga', '05/06/2015', '15:00', 'leo', '355480065226206', 'SEM TELEFONE'),
(16, 'Seu Zé', '01/07/2015', '10:00', 'freud', '355480065226206', '998653215');

-- --------------------------------------------------------

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
CREATE TABLE IF NOT EXISTS `especialidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=59 ;

--
-- Dumping data for table `especialidades`
--

INSERT INTO `especialidades` (`id`, `nome`) VALUES
(2, 'Acupuntura'),
(3, 'Alergia e Imunologia'),
(4, 'Anestesiologia'),
(5, 'Angiologia'),
(6, 'Cancerologia'),
(7, 'Cardiologia'),
(8, 'Cirurgia Cardiovascular'),
(9, 'Cirurgia da Mão'),
(10, 'Cirurgia de Cabeça e Pescoço'),
(11, 'Cirurgia do Aparelho Digestório'),
(12, 'Cirurgia Geral'),
(13, 'Cirurgia Pediátrica'),
(14, 'Cirurgia Plástica'),
(15, 'Cirurgia Torácica'),
(16, 'Cirurgia Vascular'),
(17, 'Clínica Médica'),
(18, 'Coloproctologia'),
(19, 'Dermatologia'),
(20, 'Endocrinologia e Metabologia'),
(21, 'Endoscopia'),
(22, 'Fonoaudiologia'),
(23, 'Fisioterapia'),
(24, 'Gastroenterologia'),
(25, 'Genética médica'),
(26, 'Geriatria'),
(27, 'Ginecologia e Obstetrícia'),
(28, 'Hematologia e Hemoterapia'),
(29, 'Homeopatia'),
(30, 'Infectologia'),
(31, 'Mastologia'),
(32, 'Medicina de Família e Comunidade'),
(33, 'Medicina do Trabalho'),
(34, 'Medicina do Tráfego'),
(35, 'Medicina Esportiva'),
(36, 'Medicina Física e Reabilitação'),
(37, 'Medicina Intensiva'),
(38, 'Medicina Legal'),
(39, 'Medicina Nuclear'),
(40, 'Medicina Preventiva e Social'),
(41, 'Nefrologia'),
(42, 'Neurocirurgia'),
(43, 'Neurologia'),
(44, 'Nutrologia'),
(45, 'Oftalmologia'),
(46, 'Ortopedia e Traumatologia'),
(47, 'Otorrinolaringologia'),
(48, 'Patologia'),
(49, 'Pediatria'),
(50, 'Neonatologia'),
(51, 'Pneumologia'),
(52, 'Psiquiatria'),
(53, 'Psicologia'),
(54, 'Radiologia e Diagnóstico por Imagem'),
(55, 'Radioterapia'),
(56, 'Reumatologia'),
(57, 'Terapia Ocupacional'),
(58, 'Urologia');

-- --------------------------------------------------------

--
-- Table structure for table `localizacao_medicos`
--

DROP TABLE IF EXISTS `localizacao_medicos`;
CREATE TABLE IF NOT EXISTS `localizacao_medicos` (
  `medicos_user` varchar(20) NOT NULL,
  `latitude` varchar(30) DEFAULT NULL,
  `longitude` varchar(30) DEFAULT NULL,
  `ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`medicos_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `localizacao_medicos`
--

INSERT INTO `localizacao_medicos` (`medicos_user`, `latitude`, `longitude`, `ativo`) VALUES
('freud', '-2.533495', '-44.225381', 'S'),
('hans', '-2.521442', '-44.255676', 'S'),
('leo', '-2.543200', '-44.204553', 'S'),
('Teste', '-2.5352681', '-44.2266906', 'S');

-- --------------------------------------------------------

--
-- Table structure for table `medicos`
--

DROP TABLE IF EXISTS `medicos`;
CREATE TABLE IF NOT EXISTS `medicos` (
  `user` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `especialidade` varchar(45) NOT NULL,
  `qtdPacientesPorHora` int(11) DEFAULT NULL,
  `agendaManha` varchar(1) NOT NULL,
  `agendaTarde` varchar(1) NOT NULL,
  `crm` varchar(10) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `medicos`
--

INSERT INTO `medicos` (`user`, `password`, `nome`, `especialidade`, `qtdPacientesPorHora`, `agendaManha`, `agendaTarde`, `crm`) VALUES
('freud', 'freud', 'Freu', 'Psicologo', 2, 'S', 'N', '7654'),
('hans', 'hans', 'Hans Chucrute', 'Dermatologista', 3, 'S', 'S', '9864'),
('leo', 'leo', 'Leonardo', 'Clínico Geral', 2, 'S', 'S', '1234'),
('Teste', 'teste', 'Teste', 'teste', 1, 'S', 'S', '123345');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD CONSTRAINT `fk_agendamentos_medicos` FOREIGN KEY (`medicos_user`) REFERENCES `medicos` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `localizacao_medicos`
--
ALTER TABLE `localizacao_medicos`
  ADD CONSTRAINT `fk_localizacao_medicos_medicos1` FOREIGN KEY (`medicos_user`) REFERENCES `medicos` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
