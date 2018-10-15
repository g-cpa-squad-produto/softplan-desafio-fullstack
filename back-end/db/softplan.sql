-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.34-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para process
DROP DATABASE IF EXISTS `process`;
CREATE DATABASE IF NOT EXISTS `process` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `process`;

-- Copiando estrutura para tabela process.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.hibernate_sequence: 3 rows
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(45),
	(45),
	(45);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Copiando estrutura para tabela process.process
DROP TABLE IF EXISTS `process`;
CREATE TABLE IF NOT EXISTS `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.process: ~3 rows (aproximadamente)
DELETE FROM `process`;
/*!40000 ALTER TABLE `process` DISABLE KEYS */;
/*!40000 ALTER TABLE `process` ENABLE KEYS */;

-- Copiando estrutura para tabela process.profile
DROP TABLE IF EXISTS `profile`;
CREATE TABLE IF NOT EXISTS `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.profile: ~3 rows (aproximadamente)
DELETE FROM `profile`;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`id`, `name`, `created_at`, `updated_at`) VALUES
	(1, 'ADMIN', NULL, NULL),
	(4, 'TRIADOR', NULL, NULL),
	(5, 'FINALIZADOR', NULL, NULL);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Copiando estrutura para tabela process.profile_role
DROP TABLE IF EXISTS `profile_role`;
CREATE TABLE IF NOT EXISTS `profile_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_profile_role_profile_id` (`profile_id`),
  KEY `fk_profile_role_role_id` (`role_id`),
  CONSTRAINT `fk_profile_role_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `fk_profile_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.profile_role: ~9 rows (aproximadamente)
DELETE FROM `profile_role`;
/*!40000 ALTER TABLE `profile_role` DISABLE KEYS */;
INSERT INTO `profile_role` (`id`, `profile_id`, `role_id`, `createdAt`, `updatedAt`) VALUES
	(1, 1, 3, NULL, NULL),
	(2, 1, 4, NULL, NULL),
	(3, 1, 2, NULL, NULL),
	(4, 1, 1, NULL, NULL),
	(8, 4, 3, NULL, NULL),
	(9, 5, 4, NULL, NULL),
	(10, 5, 2, NULL, NULL),
	(11, 4, 5, NULL, NULL),
	(12, 1, 5, NULL, NULL);
/*!40000 ALTER TABLE `profile_role` ENABLE KEYS */;

-- Copiando estrutura para tabela process.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.role: ~5 rows (aproximadamente)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `created_at`, `updated_at`) VALUES
	(1, 'ROLE_USER_GRANT_ALL', NULL, NULL),
	(2, 'ROLE_SIGHT_GRANT_ALL', NULL, NULL),
	(3, 'ROLE_PROCESS_GRANT_ALL', NULL, NULL),
	(4, 'ROLE_PROCESS_LIST_BY_USER', NULL, NULL),
	(5, 'ROLE_USER_LIST_BY_PROFILE', NULL, NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Copiando estrutura para tabela process.sight
DROP TABLE IF EXISTS `sight`;
CREATE TABLE IF NOT EXISTS `sight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `process_id` int(11) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sight_process_id` (`process_id`),
  CONSTRAINT `fk_sight_process_id` FOREIGN KEY (`process_id`) REFERENCES `process` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.sight: ~3 rows (aproximadamente)
DELETE FROM `sight`;
/*!40000 ALTER TABLE `sight` DISABLE KEYS */;
/*!40000 ALTER TABLE `sight` ENABLE KEYS */;

-- Copiando estrutura para tabela process.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.user: ~5 rows (aproximadamente)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `name`, `password`, `created_at`, `updated_at`) VALUES
	(12, 'admin@mail.com', 'Admin', '$2a$10$VEkuP22rJqKV4yRrzIr2lOsBirHfk2IrKyjFwj97PPyI4Efmgmyce', '2018-10-07', NULL),
	(35, 'triador@mail.com', 'Triador', '$2a$10$ZHitoT339DqC69VEs3864uhrXfP0HjxeHn5gaaKW1m.n/oMhn36EG', '2018-10-11', NULL),
	(36, 'finalizador@mail.com', 'Finalizador', '$2a$10$LhnVY.98tBPh9jq3F3UtdeKHLzqw7pvmE.m3ihkF8DP71DWg87XRq', '2018-10-11', NULL),
	(42, 'triador2@mail.com', 'Triador 2', '$2a$10$8JVKmfShMV5STMhtM59Wdum1V4ZpQnXwwQYygvDtiPo3.CrvOTK6a', '2018-10-11', '2018-10-11'),
	(43, 'triador3@mail.com', 'Triador 3', '$2a$10$Jxa3ImSNRlyCa.53j/HXYux.Mh6RnGpVyp5ftFLgOtkSeviUPVsDu', '2018-10-11', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Copiando estrutura para tabela process.user_process
DROP TABLE IF EXISTS `user_process`;
CREATE TABLE IF NOT EXISTS `user_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `process_id` int(11) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_process_user_id` (`user_id`),
  KEY `fk_user_process_process_id` (`process_id`),
  CONSTRAINT `fk_user_process_process_id` FOREIGN KEY (`process_id`) REFERENCES `process` (`id`),
  CONSTRAINT `fk_user_process_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.user_process: ~3 rows (aproximadamente)
DELETE FROM `user_process`;
/*!40000 ALTER TABLE `user_process` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_process` ENABLE KEYS */;

-- Copiando estrutura para tabela process.user_profile
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_profile_id` (`profile_id`),
  CONSTRAINT `fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela process.user_profile: ~7 rows (aproximadamente)
DELETE FROM `user_profile`;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`id`, `user_id`, `profile_id`, `createdAt`, `updatedAt`) VALUES
	(3, 12, 1, NULL, NULL);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
