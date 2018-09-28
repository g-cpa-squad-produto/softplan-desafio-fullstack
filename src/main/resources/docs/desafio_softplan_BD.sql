-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 28/09/2018 às 17:17
-- Versão do servidor: 5.7.23-0ubuntu0.16.04.1
-- Versão do PHP: 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `desafio`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `parecer`
--

CREATE TABLE `parecer` (
  `id` bigint(20) NOT NULL,
  `indicador_parecer` bit(1) DEFAULT NULL,
  `parecer_descricao` varchar(255) DEFAULT NULL,
  `id_processo` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `processo`
--

CREATE TABLE `processo` (
  `id` bigint(20) NOT NULL,
  `data_processo` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipo_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `nome`, `senha`, `tipo_usuario`) VALUES
(1, 'admin', 'Administrador', '$2a$10$ARppQC0FRWaGP4pnZqYbpuVyYOWIp4q1r2ViT3PGYK6BafD5PXFiS', 1);


--
-- Índices de tabela `parecer`
--
ALTER TABLE `parecer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK711bvdl538jefcbdm8lfr5654` (`id_processo`),
  ADD KEY `FK9y8txe7ttk0d60gb6u3wijb1` (`id_usuario`);

--
-- Índices de tabela `processo`
--
ALTER TABLE `processo`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);



--
-- AUTO_INCREMENT de tabela `parecer`
--
ALTER TABLE `parecer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de tabela `processo`
--
ALTER TABLE `processo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `parecer`
--
ALTER TABLE `parecer`
  ADD CONSTRAINT `FK711bvdl538jefcbdm8lfr5654` FOREIGN KEY (`id_processo`) REFERENCES `processo` (`id`),
  ADD CONSTRAINT `FK9y8txe7ttk0d60gb6u3wijb1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
