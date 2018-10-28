-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: agfpb_autenticacao
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agf_aviso`
--

DROP TABLE IF EXISTS `agf_aviso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_aviso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `descricao` longtext NOT NULL,
  `nomedoc` varchar(100) DEFAULT NULL,
  `sistema` varchar(255) NOT NULL,
  `tipoUsuario` varchar(255) DEFAULT NULL,
  `titulo` varchar(100) NOT NULL,
  `urldoc` longtext,
  `perfil_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g8wpdt467lwhaqk9e54c3ct18` (`perfil_id`),
  CONSTRAINT `FK_g8wpdt467lwhaqk9e54c3ct18` FOREIGN KEY (`perfil_id`) REFERENCES `agf_perfil` (`id`),
  CONSTRAINT `FK_k3fxhww0n7s1o1nsp93tkyhk8` FOREIGN KEY (`perfil_id`) REFERENCES `agf_perfil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_aviso`
--

LOCK TABLES `agf_aviso` WRITE;
/*!40000 ALTER TABLE `agf_aviso` DISABLE KEYS */;
/*!40000 ALTER TABLE `agf_aviso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_compativeis`
--

DROP TABLE IF EXISTS `agf_compativeis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_compativeis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `codsistema` int(11) DEFAULT NULL,
  `senhadeliberacao` varchar(30) DEFAULT NULL,
  `versaoliberada` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9um7p2gjyq2awidxkh19bjfxv` (`codsistema`),
  UNIQUE KEY `UK_dyggeu28enuftaad8taa1rkvt` (`codsistema`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_compativeis`
--

LOCK TABLES `agf_compativeis` WRITE;
/*!40000 ALTER TABLE `agf_compativeis` DISABLE KEYS */;
INSERT INTO `agf_compativeis` VALUES (4,0,7,'------------','3.0.00.00');
/*!40000 ALTER TABLE `agf_compativeis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_componente`
--

DROP TABLE IF EXISTS `agf_componente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_componente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `menu` varchar(60) DEFAULT NULL,
  `nome` varchar(60) DEFAULT NULL,
  `sistema` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `informacao` varchar(555) DEFAULT NULL,
  `icone_id` bigint(20) DEFAULT NULL,
  `tipomenu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rninqjgvehpfvxfw14hkgmh3n` (`nome`,`sistema`),
  UNIQUE KEY `UK_428xbaxwofj46o5eb9m57q7td` (`nome`,`sistema`),
  KEY `FK_ah58gknyqce716bppb1o3deha` (`icone_id`),
  KEY `FK_4212bbk1ker4mdjcole61y1np` (`tipomenu_id`),
  CONSTRAINT `FK_4212bbk1ker4mdjcole61y1np` FOREIGN KEY (`tipomenu_id`) REFERENCES `agf_tipomenu` (`id`),
  CONSTRAINT `FK_ah58gknyqce716bppb1o3deha` FOREIGN KEY (`icone_id`) REFERENCES `agf_icones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_componente`
--

LOCK TABLES `agf_componente` WRITE;
/*!40000 ALTER TABLE `agf_componente` DISABLE KEYS */;
INSERT INTO `agf_componente` VALUES (1,0,'Autorização',NULL,'AGF_AUTENTICACAO','ProjetoBase',NULL,NULL,NULL,1),(2,1,'Usuário','AGF_PROJETO','AGF_USUARIO','ProjetoBase','/view/autenticacao/view/usuariomanage.zul','',NULL,2),(3,0,'Perfil','AGF_AUTENTICACAO','AGF_PERFIL','ProjetoBase','/view/autenticacao/view/perfilmanage.zul','',NULL,2),(4,0,'Usuário Perfil','AGF_AUTENTICACAO','AGF_USUARIOPERFIL','ProjetoBase','/view/autenticacao/view/usuarioperfilmanage.zul','Informar o Perfil do Usuário',NULL,2),(5,0,'Ícones','AGF_AUTENTICACAO','AGF_ICONES','ProjetoBase','/view/autenticacao/view/iconesmanage.zul','',NULL,2),(6,0,'Tipo Menu','AGF_AUTENTICACAO','AGF_TIPOMENU','ProjetoBase','/view/autenticacao/view/tipomenumanage.zul','',NULL,2),(7,0,'Componente do Menu','AGF_AUTENTICACAO','AGF_COMPONENTE','ProjetoBase','/view/autenticacao/view/componentemanage.zul','O componente pode ser tanto um Menu como um Componente',NULL,2),(8,0,'Permissão','AGF_AUTENTICACAO','AGF_PERMISSAO','ProjetoBase','/view/autenticacao/view/permissaomanage.zul','',NULL,2),(9,0,'Dados da entidade','AGF_AUTENTICACAO','AGF_PARAMETROS','ProjetoBase','/view/autenticacao/view/parametrosmanage.zul','',NULL,2),(31,1,'Cadastro',NULL,'AGF_PROJETO','ProjetoBase',NULL,NULL,NULL,1),(227,0,'Tipo Pessoa','AGF_PROJETO','tipopessoa','ProjetoBase','/view/gerenciadorprocessos/view/tipopessoamanage.zul','',NULL,2),(228,0,'Pessoa','AGF_PROJETO','pessoa','ProjetoBase','/view/gerenciadorprocessos/view/pessoamanage.zul','',NULL,2),(229,0,'Processo','AGF_PROJETO','processo','ProjetoBase','/view/gerenciadorprocessos/view/processomanage.zul','',NULL,2),(231,1,'Usuário','AGF_PROJETO','AGF_USUARIOATALHO','ProjetoBase','/view/autenticacao/view/usuariomanage.zul','',NULL,2);
/*!40000 ALTER TABLE `agf_componente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_icones`
--

DROP TABLE IF EXISTS `agf_icones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_icones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `url` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_icones`
--

LOCK TABLES `agf_icones` WRITE;
/*!40000 ALTER TABLE `agf_icones` DISABLE KEYS */;
/*!40000 ALTER TABLE `agf_icones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_parametros`
--

DROP TABLE IF EXISTS `agf_parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_parametros` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `Logradouro` varchar(100) DEFAULT NULL,
  `TipoLogradouro` varchar(30) DEFAULT NULL,
  `acod` varchar(5) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `brasao` longblob,
  `cep` varchar(10) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `emailcontato` varchar(255) DEFAULT NULL,
  `nomereduzido` varchar(33) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_35f6bm31db3ou5mqp8jo51ft1` (`cnpj`),
  UNIQUE KEY `UK_f4t2jwhvrt9xej86f0rbuq463` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_parametros`
--

LOCK TABLES `agf_parametros` WRITE;
/*!40000 ALTER TABLE `agf_parametros` DISABLE KEYS */;
INSERT INTO `agf_parametros` VALUES (1,4,'LUIZ BOITEUX PIAZZA','AV','000','CACHOEIRA DO BOM JESUS',NULL,'88056-000','FLORIANÓPOLIS','89.735.211/0001-86','SOFTPLAN','1302','','SC','ARTHURGFREIRE@GMAIL.COM','SOFTPLAN');
/*!40000 ALTER TABLE `agf_parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_parametrosemail`
--

DROP TABLE IF EXISTS `agf_parametrosemail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_parametrosemail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `finalidade` varchar(255) DEFAULT NULL,
  `login` varchar(71) DEFAULT NULL,
  `porta` int(11) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `servidor` varchar(100) DEFAULT NULL,
  `parametro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7e3yolq5dx7qkc812tjwhpsw0` (`parametro_id`),
  CONSTRAINT `FK_66f0t0k0orv1ap0rn2023vdgh` FOREIGN KEY (`parametro_id`) REFERENCES `agf_parametros` (`id`),
  CONSTRAINT `FK_7e3yolq5dx7qkc812tjwhpsw0` FOREIGN KEY (`parametro_id`) REFERENCES `agf_parametros` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_parametrosemail`
--

LOCK TABLES `agf_parametrosemail` WRITE;
/*!40000 ALTER TABLE `agf_parametrosemail` DISABLE KEYS */;
/*!40000 ALTER TABLE `agf_parametrosemail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_perfil`
--

DROP TABLE IF EXISTS `agf_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_perfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `descricao` longtext,
  `nome` varchar(100) DEFAULT NULL,
  `sistema` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_perfil`
--

LOCK TABLES `agf_perfil` WRITE;
/*!40000 ALTER TABLE `agf_perfil` DISABLE KEYS */;
INSERT INTO `agf_perfil` VALUES (4,1,'PARTE DO DESENVOLVEDOR','DESENVOLVEDOR','ProjetoBase','Comum'),(6,1,'ADMINISTRADOR DO SISTEMA','ADMINISTRADOR','ProjetoBase','Comum'),(7,1,'USUÁRIO TRIADOR','TRIADOR','ProjetoBase','Comum'),(8,0,'USUÁRIO FINALIZADOR','FINALIZADOR','ProjetoBase','Comum');
/*!40000 ALTER TABLE `agf_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_permissao`
--

DROP TABLE IF EXISTS `agf_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_permissao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `atalho` bit(1) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `controle1` bit(1) NOT NULL,
  `controle2` bit(1) NOT NULL,
  `controle3` bit(1) NOT NULL,
  `controle4` bit(1) NOT NULL,
  `controle5` bit(1) NOT NULL,
  `excluir` bit(1) NOT NULL,
  `somenteLeitura` bit(1) NOT NULL,
  `componente_id` bigint(20) DEFAULT NULL,
  `perfil_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7eog878qdke6pnc45a9hnev08` (`componente_id`,`perfil_id`),
  UNIQUE KEY `UK_gk2euynjn637iprw6l9h15q6q` (`componente_id`,`perfil_id`),
  KEY `FK_pse1txyt3gvvwbee81rfsdap3` (`perfil_id`),
  CONSTRAINT `FK_1pw3bwibludekf0dtcm1qju5t` FOREIGN KEY (`componente_id`) REFERENCES `agf_componente` (`id`),
  CONSTRAINT `FK_9y4vvtv9t49xa9nnwmvj69pi7` FOREIGN KEY (`perfil_id`) REFERENCES `agf_perfil` (`id`),
  CONSTRAINT `FK_jr90fnoc2n87mi7smi934m7xt` FOREIGN KEY (`componente_id`) REFERENCES `agf_componente` (`id`),
  CONSTRAINT `FK_pse1txyt3gvvwbee81rfsdap3` FOREIGN KEY (`perfil_id`) REFERENCES `agf_perfil` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1120 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_permissao`
--

LOCK TABLES `agf_permissao` WRITE;
/*!40000 ALTER TABLE `agf_permissao` DISABLE KEYS */;
INSERT INTO `agf_permissao` VALUES (1,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',1,4),(2,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',2,4),(3,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',3,4),(4,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',4,4),(5,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',7,4),(6,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',8,4),(9,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',9,4),(31,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',31,4),(228,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',228,4),(229,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',229,4),(1113,2,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',31,6),(1114,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',2,6),(1115,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',31,7),(1116,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',228,7),(1117,1,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',229,7),(1118,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',31,8),(1119,0,_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',229,8);
/*!40000 ALTER TABLE `agf_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_tipomenu`
--

DROP TABLE IF EXISTS `agf_tipomenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_tipomenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `nome` varchar(133) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_tipomenu`
--

LOCK TABLES `agf_tipomenu` WRITE;
/*!40000 ALTER TABLE `agf_tipomenu` DISABLE KEYS */;
INSERT INTO `agf_tipomenu` VALUES (1,0,'MENU'),(2,0,'COMPONENTE');
/*!40000 ALTER TABLE `agf_tipomenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_usuario`
--

DROP TABLE IF EXISTS `agf_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL,
  `login` varchar(60) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `telefoneAlternativo` varchar(15) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `aviso_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_deiy6wiw0bajco4khj3eo756v` (`login`),
  UNIQUE KEY `UK_2pfvmr7xaoprq38mc24eql46h` (`login`),
  KEY `FK_ixlh0y0s082u5sdoul314wry0` (`aviso_id`),
  CONSTRAINT `FK_4csvru7fu2vw0wv0b2xrc525j` FOREIGN KEY (`aviso_id`) REFERENCES `agf_aviso` (`id`),
  CONSTRAINT `FK_ixlh0y0s082u5sdoul314wry0` FOREIGN KEY (`aviso_id`) REFERENCES `agf_aviso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_usuario`
--

LOCK TABLES `agf_usuario` WRITE;
/*!40000 ALTER TABLE `agf_usuario` DISABLE KEYS */;
INSERT INTO `agf_usuario` VALUES (1,1,'admin@AGFgerador.com',_binary '','a','DESENVOLVEDOR','c4ca4238a0b923820dcc509a6f75849b','(71) 9999-9999','(71) 9999-9999','Administrador',NULL),(2,1,'arthurgfreire@gmail.com',_binary '','agfadm','ARTHUR ADMINISTRADOR','e10adc3949ba59abbe56e057f20f883e','(81) 3316-0000','(75) 99947-0585','Comum',NULL),(3,0,'agftriador@gmail.com',_binary '','agftriador','ARTHUR TRIADOR','e10adc3949ba59abbe56e057f20f883e','(81) 9584-5221','(75) 99947-0585','Comum',NULL),(4,1,'argfinalizador1@gmail.com',_binary '','agffinalizador1','ARTHUR FINALIZADOR 1','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(6,1,'argfinalizador2@gmail.com',_binary '','agffinalizador2','ARTHUR FINALIZADOR 2','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(7,1,'argfinalizador3@gmail.com',_binary '','agffinalizador3','ARTHUR FINALIZADOR 3','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(8,1,'argfinalizador4@gmail.com',_binary '','agffinalizador4','ARTHUR FINALIZADOR 4','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(9,1,'argfinalizador5@gmail.com',_binary '','agffinalizador5','ARTHUR FINALIZADOR 5','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(10,1,'argfinalizador6@gmail.com',_binary '','agffinalizador6','ARTHUR FINALIZADOR 6','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(11,1,'argfinalizador7@gmail.com',_binary '','agffinalizador7','ARTHUR FINALIZADOR 7','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(12,1,'argfinalizador8@gmail.com',_binary '','agffinalizador8','ARTHUR FINALIZADOR 8','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(13,1,'argfinalizador9@gmail.com',_binary '','agffinalizador9','ARTHUR FINALIZADOR 9','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL),(14,1,'argfinalizador10@gmail.com',_binary '','agffinalizador10','ARTHUR FINALIZADOR 10','e10adc3949ba59abbe56e057f20f883e','(81) 5478-5245','(75) 99947-0585','Comum',NULL);
/*!40000 ALTER TABLE `agf_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agf_usuarioperfil`
--

DROP TABLE IF EXISTS `agf_usuarioperfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agf_usuarioperfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `administrador` bit(1) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `perfil_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i12r085c0u9u6r5gxpkfqg2vp` (`perfil_id`,`usuario_id`),
  UNIQUE KEY `UK_rhpbdqylvhfqu7o2lj13vf3fp` (`perfil_id`,`usuario_id`),
  KEY `FK_q30ff6is9g3rv5pg4nf97u4on` (`usuario_id`),
  CONSTRAINT `FK_41jose5ey2lvmxe8bt9m226yj` FOREIGN KEY (`usuario_id`) REFERENCES `agf_usuario` (`id`),
  CONSTRAINT `FK_hcogcoofiegmslv9hc6u5vh4t` FOREIGN KEY (`perfil_id`) REFERENCES `agf_perfil` (`id`),
  CONSTRAINT `FK_q30ff6is9g3rv5pg4nf97u4on` FOREIGN KEY (`usuario_id`) REFERENCES `agf_usuario` (`id`),
  CONSTRAINT `FK_r2pewg6iakxuglsqoanyltrg9` FOREIGN KEY (`perfil_id`) REFERENCES `agf_perfil` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agf_usuarioperfil`
--

LOCK TABLES `agf_usuarioperfil` WRITE;
/*!40000 ALTER TABLE `agf_usuarioperfil` DISABLE KEYS */;
INSERT INTO `agf_usuarioperfil` VALUES (33,0,_binary '',_binary '',4,1),(41,0,_binary '',_binary '',6,2),(42,0,_binary '\0',_binary '',7,3),(43,0,_binary '\0',_binary '',8,4),(44,0,_binary '\0',_binary '',8,6),(45,0,_binary '\0',_binary '',8,7),(46,0,_binary '\0',_binary '',8,8),(47,0,_binary '\0',_binary '',8,9),(48,0,_binary '\0',_binary '',8,10),(49,0,_binary '\0',_binary '',8,11),(50,0,_binary '\0',_binary '',8,12),(51,0,_binary '\0',_binary '',8,13),(52,0,_binary '\0',_binary '',8,14);
/*!40000 ALTER TABLE `agf_usuarioperfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_agf`
--

DROP TABLE IF EXISTS `log_agf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `log_agf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `domainName` varchar(255) DEFAULT NULL,
  `idObject` bigint(20) DEFAULT NULL,
  `metodo` varchar(255) DEFAULT NULL,
  `modulo` varchar(255) DEFAULT NULL,
  `usuarioLogin` varchar(255) DEFAULT NULL,
  `valorAtributos` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_agf`
--

LOCK TABLES `log_agf` WRITE;
/*!40000 ALTER TABLE `log_agf` DISABLE KEYS */;
INSERT INTO `log_agf` VALUES (1,0,'2018-08-25','Empresa',1,'ADICIONAR','PROJETOBASE','a','brasao : [B@44fc15b6;\nnomefantadia : solon;\ncnpj : 9999999999999999999;\nrazaosocial : solom;\n'),(2,0,'2018-08-25','Empresa',2,'ADICIONAR','PROJETOBASE','a','brasao : [B@53939b7e;\nnomefantadia : felipe;\ncnpj : 242424242424224;\nrazaosocial : filipe;\n'),(3,0,'2018-08-26','Disciplina',1,'ADICIONAR','PROJETOBASE','a','nome : matemática;\n'),(4,0,'2018-08-26','Assunto',1,'ADICIONAR','PROJETOBASE','a','nome : equação do 1º;\ndisciplina : 1;\n'),(5,0,'2018-08-26','Matdidatico',1,'ADICIONAR','PROJETOBASE','a','descricao : teste;\nmaterial : [B@566be786;\nassunto : 1;\n'),(6,0,'2018-08-26','Empresa',2,'ATUALIZAR','PROJETOBASE','a','brasao : [B@56ec2ef6;\nnomefantadia : FELIPE;\ncnpj : 23+1;\nrazaosocial : FILIPE;\n'),(7,0,'2018-08-30','Servidor',1,'ADICIONAR','PROJETOBASE','a','login : teste;\nsenha : teste;\nip : teste;\nipreal : teste;\nsistemaoperacional : testes;\n'),(8,0,'2018-08-30','Servidor',2,'ADICIONAR','PROJETOBASE','a','login : oioi;\nsenha : oioio;\nip : ioioio;\nipreal : oioioi;\nsistemaoperacional : oioioi;\ndataserv : Wed Aug 01 00:00:00 BRT 2018;\nboollleaasds : true;\n'),(9,0,'2018-09-03','Usuarioo',1,'ADICIONAR','PROJETOBASE','a','nome : arthur;\nresponsavel : false;\nsetor : admin;\n'),(10,0,'2018-09-04','Sistema',1,'ADICIONAR','PROJETOBASE','a','nome : teste;\ndesenvolvedor : teste;\n'),(11,0,'2018-09-06','Usuario',2,'ADICIONAR','AUTENTICAÇÃO','a','nome : Arthur Freire;\nlogin : arthur;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : false;\nemail : arthur@gmail.com;\ntipo : Comum;\n'),(12,0,'2018-09-06','Usuario',2,'ATUALIZAR','AUTENTICAÇÃO','a','nome : ARTHUR FREIRE;\nlogin : arthur;\nsenha : 14e1b600b1fd579f47433b88e8d85291;\nhabilitado : true;\nemail : arthur@gmail.com;\ntipo : Comum;\n'),(13,0,'2018-09-06','Usuario',2,'ATUALIZAR','AUTENTICAÇÃO','a','nome : ARTHUR FREIRE;\nlogin : arthur;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : arthur@gmail.com;\ntipo : Comum;\n'),(14,0,'2018-09-06','UsuarioPerfil',34,'ADICIONAR','AUTENTICAÇÃO','a','ativo : false;\nadministrador : false;\nperfil : 4;\nusuario : 2;\n'),(15,0,'2018-09-06','UsuarioPerfil',34,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 4;\nusuario : 2;\n'),(16,0,'2018-09-07','Permissao',173,'REMOVER','AUTENTICAÇÃO','a','ativo : false;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 173;\n'),(17,0,'2018-09-07','Perfil',5,'ADICIONAR','PROJETOBASE','a','nome : Comun;\ndescricao : Perfil Comun;\n'),(18,0,'2018-09-07','Usuario',3,'ADICIONAR','AUTENTICAÇÃO','a','nome : comun comun;\nlogin : comun1;\nsenha : c56d0e9a7ccec67b4ea131655038d604;\nhabilitado : true;\nemail : comun@gmail.com;\ntelefone : (__) ____-____;\ntipo : Comum;\n'),(19,0,'2018-09-07','UsuarioPerfil',35,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 3;\n'),(20,0,'2018-09-07','UsuarioPerfil',35,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 3;\n'),(21,0,'2018-09-07','Permissao',181,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 5;\ncomponente : 1;\n'),(22,0,'2018-09-07','Permissao',182,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 5;\ncomponente : 164;\n'),(23,0,'2018-09-07','Permissao',183,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 5;\ncomponente : 165;\n'),(24,0,'2018-09-07','Usuario',3,'ATUALIZAR','AUTENTICAÇÃO','a','nome : COMUN COMUN;\nlogin : comun1;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : comun@gmail.com;\ntelefone : (__) ____-____;\ntipo : Comum;\n'),(25,0,'2018-09-07','Perfil',5,'ATUALIZAR','PROJETOBASE','arthur','nome : COMUN;\ndescricao : PERFIL COMUN;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(26,0,'2018-09-07','Usuario',3,'ATUALIZAR','AUTENTICAÇÃO','a','nome : COMUN COMUN;\nlogin : comun1;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : comun@gmail.com;\ntelefone : (__) ____-____;\ntipo : Comum;\n'),(27,0,'2018-09-07','UsuarioPerfil',35,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 3;\n'),(28,0,'2018-09-07','Usuario',3,'ATUALIZAR','AUTENTICAÇÃO','a','nome : COMUN COMUN;\nlogin : comun1;\nsenha : 14e1b600b1fd579f47433b88e8d85291;\nhabilitado : true;\nemail : comun@gmail.com;\ntelefone : ;\ntelefoneAlternativo : (__) _____-____;\ntipo : Comum;\n'),(29,0,'2018-09-07','Usuario',3,'ATUALIZAR','AUTENTICAÇÃO','a','nome : COMUN COMUN;\nlogin : comun1;\nsenha : c56d0e9a7ccec67b4ea131655038d604;\nhabilitado : true;\nemail : comun@gmail.com;\ntelefone : (75) 3281-1948;\ntelefoneAlternativo : ;\ntipo : Comum;\n'),(30,0,'2018-09-07','Componente',181,'ADICIONAR','AUTENTICAÇÃO','a','nome : testemenu;\ndescricao : teste menu;\nsistema : ProjetoBase;\ntipomenu : 1;\n'),(31,0,'2018-09-07','Permissao',184,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 181;\n'),(32,0,'2018-09-07','Componente',182,'ADICIONAR','AUTENTICAÇÃO','a','nome : testecomponente;\ndescricao : teste componente;\nurl : /view/autenticacao/view/usuariomanage.zul;\nsistema : ProjetoBase;\nmenu : PERMISSAO;\ninformacao : teste test;\ntipomenu : 2;\n'),(33,0,'2018-09-07','Permissao',185,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 182;\n'),(34,0,'2018-09-07','Componente',182,'ATUALIZAR','AUTENTICAÇÃO','a','nome : TESTECOMPONENTE;\ndescricao : teste componente;\nurl : /view/autenticacao/view/usuariomanage.zul;\nsistema : ProjetoBase;\nmenu : TESTEMENU;\ninformacao : teste test;\ntipomenu : 2;\n'),(35,0,'2018-09-07','Componente',164,'ATUALIZAR','AUTENTICAÇÃO','a','nome : ORGAO;\ndescricao : Órgãos;\nurl : /view/controlecliente/view/orgaomanage.zul;\nsistema : ProjetoBase;\nmenu : AGF_PROJETO;\ntipomenu : 2;\n'),(36,0,'2018-09-07','Permissao',185,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 182;\n'),(37,0,'2018-09-07','Componente',182,'REMOVER','AUTENTICAÇÃO','a','nome : TESTECOMPONENTE;\ndescricao : teste componente;\nurl : /view/autenticacao/view/usuariomanage.zul;\nsistema : ProjetoBase;\nmenu : TESTEMENU;\ninformacao : teste test;\ntipomenu : 2;\n'),(38,0,'2018-09-07','Permissao',184,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 181;\n'),(39,0,'2018-09-07','Componente',181,'REMOVER','AUTENTICAÇÃO','a','nome : TESTEMENU;\ndescricao : teste menu;\nsistema : ProjetoBase;\ntipomenu : 1;\n'),(40,0,'2018-09-07','Componente',183,'ADICIONAR','AUTENTICAÇÃO','a','nome : agorateste;\ndescricao : teste 2;\nsistema : ProjetoBase;\nmenu : ;\ntipomenu : 1;\n'),(41,0,'2018-09-07','Componente',183,'REMOVER','AUTENTICAÇÃO','a','nome : AGORATESTE;\ndescricao : teste 2;\nsistema : ProjetoBase;\nmenu : ;\ntipomenu : 1;\n'),(42,0,'2018-09-07','Componente',184,'ADICIONAR','AUTENTICAÇÃO','a','nome : testeagora;\ndescricao : teste 2;\nsistema : ProjetoBase;\nmenu : ;\ntipomenu : 1;\n'),(43,0,'2018-09-07','Permissao',186,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 184;\n'),(44,0,'2018-09-07','Componente',185,'ADICIONAR','AUTENTICAÇÃO','a','nome : compoteste;\ndescricao : compo teste;\nurl : /view/autenticacao/view/perfilmanage.zul;\nsistema : ProjetoBase;\nmenu : AGF_MENU1;\ntipomenu : 2;\n'),(45,0,'2018-09-07','Permissao',187,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 185;\n'),(46,0,'2018-09-07','Componente',184,'ATUALIZAR','AUTENTICAÇÃO','a','nome : TESTEAGORA;\ndescricao : teste 2;\nsistema : ProjetoBase;\nmenu : AGF_MENU1;\ntipomenu : 1;\n'),(47,0,'2018-09-07','Componente',184,'ATUALIZAR','AUTENTICAÇÃO','a','nome : TESTECOMPO;\ndescricao : teste 2;\nsistema : ProjetoBase;\ntipomenu : 1;\n'),(48,0,'2018-09-07','Componente',185,'ATUALIZAR','AUTENTICAÇÃO','a','nome : COMPOTESTE;\ndescricao : compo teste;\nurl : /view/autenticacao/view/perfilmanage.zul;\nsistema : ProjetoBase;\nmenu : TESTECOMPO;\ntipomenu : 2;\n'),(49,0,'2018-09-07','Permissao',187,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 185;\n'),(50,0,'2018-09-07','Permissao',186,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 184;\n'),(51,0,'2018-09-07','Componente',184,'REMOVER','AUTENTICAÇÃO','a','nome : TESTECOMPO;\ndescricao : teste 2;\nsistema : ProjetoBase;\ntipomenu : 1;\n'),(52,0,'2018-09-09','Usuario',4,'ADICIONAR','AUTENTICAÇÃO','a','nome : teste comun;\nlogin : testet;\nsenha : c56d0e9a7ccec67b4ea131655038d604;\nhabilitado : true;\nemail : a@gmail.com;\ntelefone : ;\ntipo : Comum;\n'),(53,0,'2018-09-09','UsuarioPerfil',36,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 4;\n'),(54,0,'2018-09-09','UsuarioPerfil',36,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 4;\n'),(55,0,'2018-09-09','Usuario',4,'REMOVER','AUTENTICAÇÃO','a','nome : TESTE COMUN;\nlogin : testet;\nsenha : c56d0e9a7ccec67b4ea131655038d604;\nhabilitado : true;\nemail : a@gmail.com;\ntelefone : ;\ntipo : Comum;\n'),(56,0,'2018-09-09','Usuario',5,'ADICIONAR','AUTENTICAÇÃO','a','nome : teste comun;\nlogin : testet;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : a@gmail.com;\ntipo : Comum;\n'),(57,0,'2018-09-09','UsuarioPerfil',37,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 5;\n'),(58,0,'2018-09-09','UsuarioPerfil',37,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 5;\n'),(59,0,'2018-09-09','Usuario',5,'REMOVER','AUTENTICAÇÃO','a','nome : TESTE COMUN;\nlogin : testet;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : a@gmail.com;\ntipo : Comum;\n'),(60,0,'2018-09-09','Usuario',6,'ADICIONAR','AUTENTICAÇÃO','a','nome : teste comun;\nlogin : testet;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : false;\nemail : art@gmail.com;\ntipo : Comum;\n'),(61,0,'2018-09-09','UsuarioPerfil',38,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 6;\n'),(62,0,'2018-09-09','Usuario',6,'ATUALIZAR','AUTENTICAÇÃO','a','nome : teste comun;\nlogin : testet;\nsenha : 14e1b600b1fd579f47433b88e8d85291;\nhabilitado : true;\nemail : art@gmail.com;\ntipo : Comum;\n'),(63,0,'2018-09-09','Usuario',6,'ATUALIZAR','AUTENTICAÇÃO','a','nome : TESTE COMUN;\nlogin : testet;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : art@gmail.com;\ntipo : Comum;\n'),(64,0,'2018-09-09','Usuario',6,'ATUALIZAR','AUTENTICAÇÃO','a','nome : TESTE COMUN;\nlogin : testet;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : art@gmail.com;\ntipo : Comum;\n'),(65,0,'2018-09-09','Usuario',7,'ADICIONAR','AUTENTICAÇÃO','a','nome : teste comun2;\nlogin : testett;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : false;\nemail : a@gmail.com;\ntipo : Comum;\n'),(66,0,'2018-09-09','UsuarioPerfil',39,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 7;\n'),(67,0,'2018-09-09','Usuario',7,'ATUALIZAR','AUTENTICAÇÃO','a','nome : teste comun2;\nlogin : testett;\nsenha : 14e1b600b1fd579f47433b88e8d85291;\nhabilitado : true;\nemail : a@gmail.com;\ntipo : Comum;\n'),(68,0,'2018-09-09','UsuarioPerfil',39,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 7;\n'),(69,0,'2018-09-09','Usuario',7,'REMOVER','AUTENTICAÇÃO','a','nome : TESTE COMUN2;\nlogin : testett;\nsenha : 14e1b600b1fd579f47433b88e8d85291;\nhabilitado : true;\nemail : a@gmail.com;\ntipo : Comum;\n'),(70,0,'2018-09-09','Usuario',8,'ADICIONAR','AUTENTICAÇÃO','a','nome : teste comun2;\nlogin : testett;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : false;\nemail : as@gmail.com;\ntipo : Comum;\n'),(71,0,'2018-09-09','UsuarioPerfil',40,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 8;\n'),(72,0,'2018-09-09','Usuario',8,'ATUALIZAR','AUTENTICAÇÃO','a','nome : teste comun2;\nlogin : testett;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : as@gmail.com;\ntipo : Comum;\n'),(73,0,'2018-09-09','Usuario',8,'ATUALIZAR','AUTENTICAÇÃO','a','nome : TESTE COMUN2;\nlogin : testett;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : as@gmail.com;\ntipo : Comum;\n'),(74,0,'2018-09-09','Perfil',4,'ATUALIZAR','PROJETOBASE','a','nome : desenvolvedor;\ndescricao : Parte do desenvolvedor;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(75,0,'2018-09-09','UsuarioPerfil',40,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 8;\n'),(76,0,'2018-09-09','UsuarioPerfil',34,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 4;\nusuario : 2;\n'),(77,0,'2018-09-09','UsuarioPerfil',35,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 3;\n'),(78,0,'2018-09-09','UsuarioPerfil',38,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 5;\nusuario : 6;\n'),(79,0,'2018-09-09','Usuario',2,'REMOVER','AUTENTICAÇÃO','a','nome : ARTHUR FREIRE;\nlogin : arthur;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : arthur@gmail.com;\ntipo : Comum;\n'),(80,0,'2018-09-09','Usuario',3,'REMOVER','AUTENTICAÇÃO','a','nome : COMUN COMUN;\nlogin : comun1;\nsenha : c56d0e9a7ccec67b4ea131655038d604;\nhabilitado : true;\nemail : comun@gmail.com;\ntelefone : (75) 3281-1948;\ntelefoneAlternativo : ;\ntipo : Comum;\n'),(81,0,'2018-09-09','Usuario',6,'REMOVER','AUTENTICAÇÃO','a','nome : TESTE COMUN;\nlogin : testet;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : art@gmail.com;\ntipo : Comum;\n'),(82,0,'2018-09-09','Usuario',8,'REMOVER','AUTENTICAÇÃO','a','nome : TESTE COMUN2;\nlogin : testett;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : as@gmail.com;\ntipo : Comum;\n'),(83,0,'2018-09-09','Perfil',5,'REMOVER','PROJETOBASE','a','nome : COMUN;\ndescricao : PERFIL COMUN;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(84,0,'2018-09-10','Usuarioo',1,'ADICIONAR','PROJETOBASE','a','nome : teste;\nresponsavel : true;\nsetor : teste;\n'),(85,0,'2018-09-10','Usuarioo',2,'ADICIONAR','PROJETOBASE','a','nome : oi;\nresponsavel : true;\nsetor : oi;\n'),(86,0,'2018-09-10','Usuarioo',2,'ATUALIZAR','PROJETOBASE','a','nome : oi;\nresponsavel : true;\nsetor : oi;\n'),(87,0,'2018-09-10','Usuarioo',3,'ADICIONAR','PROJETOBASE','a','nome : o;\nresponsavel : true;\nsetor : o;\n'),(88,0,'2018-09-10','Usuarioo',4,'ADICIONAR','PROJETOBASE','a','nome : y;\nresponsavel : true;\nsetor : y;\n'),(89,0,'2018-09-10','Usuarioo',5,'ADICIONAR','PROJETOBASE','a','nome : e;\nresponsavel : true;\nsetor : e;\n'),(90,0,'2018-09-10','Usuarioo',5,'ATUALIZAR','PROJETOBASE','a','nome : E;\nresponsavel : true;\nsetor : E;\n'),(91,0,'2018-09-10','Usuarioo',6,'ADICIONAR','PROJETOBASE','a','nome : r;\nresponsavel : true;\nsetor : r;\n'),(92,0,'2018-09-10','Componente',204,'ADICIONAR','AUTENTICAÇÃO','a','nome : menuteste;\ndescricao : menu teste;\nsistema : ProjetoBase;\ntipomenu : 1;\n'),(93,0,'2018-09-10','Permissao',1112,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 204;\n'),(94,0,'2018-09-10','Permissao',1112,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : false;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 204;\n'),(95,0,'2018-09-10','Permissao',1112,'REMOVER','AUTENTICAÇÃO','a','ativo : false;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 204;\n'),(96,0,'2018-09-11','Email',1,'ADICIONAR','AUTENTICAÇÃO','a','servidor : teste;\nporta : 9999;\nlogin : teste;\nsenha : teste;\nemail : a@gmail.com;\nfinalidade : teste;\nparametro : 1;\n'),(97,0,'2018-09-11','Email',1,'ATUALIZAR','AUTENTICAÇÃO','a','servidor : teste;\nporta : 9999;\nlogin : teste;\nsenha : teste;\nemail : a@gmail.com;\nfinalidade : teste;\nparametro : 1;\n'),(98,0,'2018-09-11','Email',1,'REMOVER','AUTENTICAÇÃO','a','servidor : teste;\nporta : 9999;\nlogin : teste;\nsenha : teste;\nemail : a@gmail.com;\nfinalidade : teste;\nparametro : 1;\n'),(99,0,'2018-09-11','Email',2,'ADICIONAR','AUTENTICAÇÃO','a','servidor : teste;\nporta : 123456;\nlogin : teste;\nsenha : teste;\nemail : arthur@gmail.com;\nfinalidade : ;\nparametro : 2;\n'),(100,0,'2018-09-11','Email',2,'ATUALIZAR','AUTENTICAÇÃO','a','servidor : teste;\nporta : 123456;\nlogin : teste;\nsenha : teste;\nemail : arthur@gmail.com;\nfinalidade : ;\nparametro : 2;\n'),(101,0,'2018-09-13','Email',2,'REMOVER','AUTENTICAÇÃO','a','servidor : teste;\nporta : 123456;\nlogin : teste;\nsenha : teste;\nemail : arthur@gmail.com;\nfinalidade : ;\nparametro : 2;\n'),(102,0,'2018-10-24','Tipopessoa',1,'ADICIONAR','PROJETOBASE','a','descricao : Física;\n'),(103,0,'2018-10-24','Tipopessoa',2,'ADICIONAR','PROJETOBASE','a','descricao : jurídica;\n'),(104,0,'2018-10-24','Pessoa',1,'ADICIONAR','PROJETOBASE','a','tipopessoa : 1;\nnome : Arthur Gomes Freire de Souza;\ndata : Mon Jul 13 00:00:00 BRT 2009;\ncpf : 056.524.245.83;\ncnpj : ;\ncep : 48605-195;\nuf : ba;\ncidade : paulo Afonso;\ntipologradouro : Rua;\nlogradouro : Rua dos estudantes número;\n'),(105,0,'2018-10-24','Pessoa',2,'ADICIONAR','PROJETOBASE','a','tipopessoa : 2;\nnome : teste empresa;\ndata : Fri Aug 24 00:00:00 BRT 2018;\ncpf : ;\ncnpj : 87.544.455/1225-54;\ncep : 99999-999;\nuf : PE;\ncidade : Recife;\ntipologradouro : rua;\nlogradouro : oliveira lima;\n'),(106,0,'2018-10-24','Pessoa',1,'ATUALIZAR','PROJETOBASE','a','tipopessoa : 1;\nnome : ARTHUR GOMES FREIRE DE SOUZA;\ndata : 2009-07-13;\ncpf : 056.524.245.83;\ncnpj : ;\ncep : 48605-195;\nuf : BA;\ncidade : PAULO AFONSO;\ntipologradouro : RUA;\nlogradouro : RUA DOS ESTUDANTES;\n'),(107,0,'2018-10-24','Pessoa',1,'ATUALIZAR','PROJETOBASE','a','tipopessoa : 1;\nnome : ARTHUR GOMES FREIRE DE SOUZA;\ndata : 2009-07-13;\ncpf : 056.524.245.83;\ncnpj : ;\ncep : 48605-195;\nuf : BA;\ncidade : PAULO AFONSO;\ntipologradouro : RUA;\nlogradouro : RUA DOS ESTUDANTES;\nbairro : Amauri Alves de Menezes;\nncasa : 38;\n'),(108,0,'2018-10-24','Pessoa',2,'ATUALIZAR','PROJETOBASE','a','tipopessoa : 2;\nnome : TESTE EMPRESA;\ndata : 2018-08-24;\ncpf : ;\ncnpj : 87.544.455/1225-54;\ncep : 99999-999;\nuf : PE;\ncidade : RECIFE;\ntipologradouro : RUA;\nlogradouro : OLIVEIRA LIMA;\nbairro : Boa Vista;\nncasa : 999;\n'),(109,0,'2018-10-24','Pessoa',1,'ATUALIZAR','PROJETOBASE','a','tipopessoa : 1;\nimagem : [B@69c9646a;\nnome : ARTHUR GOMES FREIRE DE SOUZA;\ndata : 2009-07-13;\ncpf : 056.524.245.83;\ncnpj : ;\ncep : 48605-195;\nuf : BA;\ncidade : PAULO AFONSO;\ntipologradouro : RUA;\nlogradouro : RUA DOS ESTUDANTES;\nbairro : AMAURI ALVES DE MENEZES;\nncasa : 38;\n'),(110,0,'2018-10-24','Processo',1,'ADICIONAR','PROJETOBASE','a','pessoa : 1;\ndtabertura : Wed Oct 17 00:00:00 BRT 2018;\nobs : teste ;\n'),(111,0,'2018-10-24','Processo',23,'ATUALIZAR','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(112,0,'2018-10-24','Processo',23,'ATUALIZAR','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(113,0,'2018-10-24','Processo',23,'ATUALIZAR','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(114,0,'2018-10-24','Processo',2,'REMOVER','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(115,0,'2018-10-24','Processo',3,'REMOVER','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(116,0,'2018-10-24','Processo',4,'REMOVER','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(117,0,'2018-10-24','Processo',23,'ATUALIZAR','PROJETOBASE','a','pessoa : 2;\ndtabertura : Thu Oct 25 00:00:00 BRST 2018;\n'),(118,0,'2018-10-24','Processo',1,'ATUALIZAR','PROJETOBASE','a','numProcesso : 50;\npessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\n'),(119,0,'2018-10-24','Processo',5,'ATUALIZAR','PROJETOBASE','a','numProcesso : 50;\npessoa : 2;\ndtabertura : 2018-10-25;\n'),(120,0,'2018-10-24','Processo',5,'ATUALIZAR','PROJETOBASE','a','numProcesso : 51;\npessoa : 2;\ndtabertura : 2018-10-25;\n'),(121,0,'2018-10-24','Processo',1,'ATUALIZAR','PROJETOBASE','a','pessoa : 2;\ndtabertura : 2018-10-17;\nobs : TESTE ;\nnumprocesso : 50;\n'),(122,0,'2018-10-24','Processo',1,'ATUALIZAR','PROJETOBASE','a','pessoa : 1;\ndtabertura : 2018-10-17;\nobs : TESTE ;\nnumprocesso : 50;\n'),(123,0,'2018-10-24','Perfil',6,'ADICIONAR','PROJETOBASE','a','nome : administrador;\ndescricao : ;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(124,0,'2018-10-24','Perfil',6,'ATUALIZAR','PROJETOBASE','a','nome : ADMINISTRADOR;\ndescricao : Administrador do Sistema;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(125,0,'2018-10-24','Permissao',1113,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 6;\ncomponente : 2;\n'),(126,0,'2018-10-24','Permissao',1113,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 6;\ncomponente : 1;\n'),(127,0,'2018-10-24','Permissao',1114,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 6;\ncomponente : 2;\n'),(128,0,'2018-10-24','Usuario',2,'ADICIONAR','AUTENTICAÇÃO','a','nome : Arthur Administrador;\nlogin : agfadministrador;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : arthurgfreire@gmail.com;\ntelefone : (81) 3316-0000;\ntelefoneAlternativo : (75) 99947-0585;\ntipo : Comum;\n'),(129,0,'2018-10-24','UsuarioPerfil',41,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : true;\nperfil : 6;\nusuario : 2;\n'),(130,0,'2018-10-24','Usuario',2,'ATUALIZAR','AUTENTICAÇÃO','a','nome : Arthur Administrador;\nlogin : agfadm;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : arthurgfreire@gmail.com;\ntelefone : (81) 3316-0000;\ntelefoneAlternativo : (75) 99947-0585;\ntipo : Comum;\n'),(131,0,'2018-10-24','Perfil',7,'ADICIONAR','PROJETOBASE','a','nome : Triador;\ndescricao : usuário triador;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(132,0,'2018-10-24','Perfil',7,'ATUALIZAR','PROJETOBASE','a','nome : Triador;\ndescricao : usuário triador;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(133,0,'2018-10-24','Permissao',1115,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 7;\ncomponente : 31;\n'),(134,0,'2018-10-24','Permissao',1116,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 7;\ncomponente : 228;\n'),(135,0,'2018-10-24','Permissao',1117,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 7;\ncomponente : 229;\n'),(136,0,'2018-10-24','Permissao',1117,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : true;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 7;\ncomponente : 229;\n'),(137,0,'2018-10-24','Usuario',3,'ADICIONAR','AUTENTICAÇÃO','a','nome : Arthur Triador;\nlogin : agftriador;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : agftriador@gmail.com;\ntelefone : (81) 9584-5221;\ntelefoneAlternativo : (75) 99947-0585;\ntipo : Comum;\n'),(138,0,'2018-10-24','UsuarioPerfil',42,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 7;\nusuario : 3;\n'),(139,0,'2018-10-24','Perfil',8,'ADICIONAR','PROJETOBASE','a','nome : finalizador;\ndescricao : usuário finalizador;\ntipo : Comum;\nsistema : ProjetoBase;\n'),(140,0,'2018-10-24','Permissao',1118,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 8;\ncomponente : 31;\n'),(141,0,'2018-10-24','Permissao',1119,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : false;\nsomenteLeitura : true;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 8;\ncomponente : 229;\n'),(142,0,'2018-10-24','Usuario',4,'ADICIONAR','AUTENTICAÇÃO','a','nome : Arthur Finalizador;\nlogin : agffinalizador;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : argfinalizador@gmail.com;\ntelefone : (81) 5478-5245;\ntelefoneAlternativo : (75) 99947-0585;\ntipo : Comum;\n'),(143,0,'2018-10-24','Usuario',4,'ATUALIZAR','AUTENTICAÇÃO','a','nome : ARTHUR FINALIZADOR;\nlogin : agffinalizador1;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : argfinalizador@gmail.com;\ntelefone : (81) 5478-5245;\ntelefoneAlternativo : (75) 99947-0585;\ntipo : Comum;\n'),(144,0,'2018-10-24','UsuarioPerfil',43,'ADICIONAR','AUTENTICAÇÃO','a','ativo : true;\nadministrador : false;\nperfil : 8;\nusuario : 4;\n'),(145,0,'2018-10-24','Usuario',6,'ATUALIZAR','AUTENTICAÇÃO','a','nome : ARTHUR FINALIZADOR;\nlogin : agffinalizador2;\nsenha : e10adc3949ba59abbe56e057f20f883e;\nhabilitado : true;\nemail : argfinalizador2@gmail.com;\ntelefone : (81) 5478-5245;\ntelefoneAlternativo : (75) 99947-0585;\ntipo : Comum;\n'),(146,0,'2018-10-25','Processo',6,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 2;\ndtabertura : Wed Oct 31 00:00:00 BRST 2018;\nnumprocesso : 121212;\n'),(147,0,'2018-10-25','Processo',7,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 1;\ndtabertura : Tue Oct 23 00:00:00 BRST 2018;\nnumprocesso : 78945652;\n'),(148,0,'2018-10-25','Parecer',1,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 1;\n'),(149,0,'2018-10-25','Parecer',4,'ADICIONAR','PROJETOBASE','agftriador','usuario : 6;\nprocesso : 1;\n'),(150,0,'2018-10-25','Parecer',5,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 7;\n'),(151,0,'2018-10-25','Parecer',6,'ADICIONAR','PROJETOBASE','agftriador','usuario : 9;\nprocesso : 7;\n'),(152,0,'2018-10-25','Parecer',7,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 5;\n'),(153,0,'2018-10-25','Parecer',8,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 6;\n'),(154,0,'2018-10-25','Processo',8,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 2;\ndtabertura : Wed Oct 03 00:00:00 BRT 2018;\nnumprocesso : 12333;\n'),(155,0,'2018-10-25','Parecer',9,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 8;\n'),(156,0,'2018-10-25','Processo',9,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 1;\ndtabertura : Wed Oct 03 00:00:00 BRT 2018;\nobs : 123123;\nnumprocesso : 123123;\n'),(157,0,'2018-10-25','Parecer',10,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 9;\n'),(158,0,'2018-10-25','Processo',10,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 1;\ndtabertura : Wed Oct 03 00:00:00 BRT 2018;\nnumprocesso : 5454;\n'),(159,0,'2018-10-25','Parecer',11,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 10;\n'),(160,0,'2018-10-25','Processo',11,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 2;\ndtabertura : Tue Oct 30 00:00:00 BRST 2018;\nnumprocesso : 64545;\n'),(161,0,'2018-10-25','Parecer',12,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 11;\n'),(162,0,'2018-10-25','Processo',12,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 2;\ndtabertura : Wed Oct 17 00:00:00 BRT 2018;\nobs : 34534;\nnumprocesso : 4545;\n'),(163,0,'2018-10-25','Parecer',13,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 12;\n'),(164,0,'2018-10-25','Processo',14,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 1;\ndtabertura : Wed Oct 03 00:00:00 BRT 2018;\nnumprocesso : 445766;\n'),(165,0,'2018-10-25','Parecer',14,'ADICIONAR','PROJETOBASE','agftriador','usuario : 6;\nprocesso : 14;\n'),(166,0,'2018-10-25','Processo',15,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 2;\ndtabertura : Tue Oct 16 00:00:00 BRT 2018;\nobs : rtyry5467456;\nnumprocesso : 89989;\n'),(167,0,'2018-10-25','Parecer',15,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 15;\n'),(168,0,'2018-10-25','Parecer',16,'ADICIONAR','PROJETOBASE','agftriador','usuario : 6;\nprocesso : 15;\n'),(169,0,'2018-10-25','Processo',16,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 1;\ndtabertura : Tue Oct 09 00:00:00 BRT 2018;\nobs : 456456456;\nnumprocesso : 456456;\n'),(170,0,'2018-10-25','Parecer',17,'ADICIONAR','PROJETOBASE','agftriador','usuario : 8;\nprocesso : 16;\n'),(171,0,'2018-10-25','Parecer',1,'ATUALIZAR','PROJETOBASE','agffinalizador1','usuario : 4;\nprocesso : 1;\ndescricao : teste;\n'),(172,0,'2018-10-25','Parecer',1,'ATUALIZAR','PROJETOBASE','agffinalizador1','usuario : 4;\nprocesso : 1;\ndescricao : teste;\n'),(173,0,'2018-10-25','Parecer',18,'ADICIONAR','PROJETOBASE','a','usuario : 7;\nprocesso : 1;\n'),(174,0,'2018-10-25','Parecer',19,'ADICIONAR','PROJETOBASE','agftriador','usuario : 8;\nprocesso : 1;\n'),(175,0,'2018-10-25','Parecer',20,'ADICIONAR','PROJETOBASE','agftriador','usuario : 9;\nprocesso : 1;\n'),(176,0,'2018-10-25','Parecer',21,'ADICIONAR','PROJETOBASE','agftriador','usuario : 10;\nprocesso : 1;\n'),(177,0,'2018-10-25','Parecer',23,'ADICIONAR','PROJETOBASE','agftriador','usuario : 11;\nprocesso : 1;\n'),(178,0,'2018-10-25','Parecer',24,'ADICIONAR','PROJETOBASE','agftriador','usuario : 12;\nprocesso : 1;\n'),(179,0,'2018-10-25','Parecer',25,'ADICIONAR','PROJETOBASE','agftriador','usuario : 13;\nprocesso : 1;\n'),(180,0,'2018-10-26','Parecer',7,'ATUALIZAR','PROJETOBASE','agffinalizador1','usuario : 4;\nprocesso : 5;\ndescricao : Eu estou terminando a visão do finalizador;\n'),(181,0,'2018-10-26','Parecer',7,'ATUALIZAR','PROJETOBASE','agffinalizador1','usuario : 4;\nprocesso : 5;\ndescricao : estou terminando a visão finalizador;\n'),(182,0,'2018-10-26','Parecer',7,'ATUALIZAR','PROJETOBASE','agffinalizador1','usuario : 4;\nprocesso : 5;\ndescricao : estou terminando a visão finalizador;\n'),(183,0,'2018-10-26','Componente',2,'ATUALIZAR','AUTENTICAÇÃO','a','nome : AGF_USUARIO;\ndescricao : Usuário;\nurl : /view/autenticacao/view/usuariomanage.zul;\nsistema : ProjetoBase;\nmenu : AGF_PROJETO;\ninformacao : ;\ntipomenu : 2;\n'),(184,0,'2018-10-26','Permissao',227,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 4;\ncomponente : 227;\n'),(185,0,'2018-10-26','Pessoa',1,'ATUALIZAR','PROJETOBASE','a','imagem : [B@12e185b3;\nnome : ARTHUR GOMES FREIRE DE SOUZA;\ndata : 2009-07-13;\ncpf : 056.524.245.83;\n'),(186,0,'2018-10-26','Componente',31,'ATUALIZAR','AUTENTICAÇÃO','a','nome : AGF_PROJETO;\ndescricao : Cadastro;\nsistema : ProjetoBase;\ntipomenu : 1;\n'),(187,0,'2018-10-26','Permissao',1113,'ATUALIZAR','AUTENTICAÇÃO','a','ativo : true;\natalho : false;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 6;\ncomponente : 31;\n'),(188,0,'2018-10-26','Permissao',1121,'REMOVER','AUTENTICAÇÃO','a','ativo : true;\natalho : true;\nexcluir : true;\nsomenteLeitura : false;\ncontrole1 : false;\ncontrole2 : false;\ncontrole3 : false;\ncontrole4 : false;\ncontrole5 : false;\nperfil : 6;\ncomponente : 231;\n'),(189,0,'2018-10-26','Pessoa',3,'ADICIONAR','PROJETOBASE','agftriador','imagem : [B@2d38b463;\nnome : joão;\ncpf : 671.605.660.25;\n'),(190,0,'2018-10-26','Processo',18,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 3;\ndtabertura : Wed Oct 10 00:00:00 BRT 2018;\nnumprocesso : 1231233;\n'),(191,0,'2018-10-26','Parecer',26,'ADICIONAR','PROJETOBASE','agftriador','usuario : 13;\nprocesso : 18;\n'),(192,0,'2018-10-26','Parecer',27,'ADICIONAR','PROJETOBASE','agftriador','usuario : 14;\nprocesso : 18;\n'),(193,0,'2018-10-26','Parecer',29,'ADICIONAR','PROJETOBASE','agftriador','usuario : 4;\nprocesso : 18;\n'),(194,0,'2018-10-26','Parecer',25,'ATUALIZAR','PROJETOBASE','agffinalizador9','usuario : 13;\nprocesso : 1;\ndescricao : teste parecer teste teste.;\n'),(195,0,'2018-10-26','Pessoa',4,'ADICIONAR','PROJETOBASE','agftriador','imagem : [B@11fb2424;\nnome : Jose;\ncpf : 503.665.540.00;\n'),(196,0,'2018-10-26','Pessoa',4,'REMOVER','PROJETOBASE','agftriador','imagem : [B@b8da21b;\nnome : JOSE;\ncpf : 503.665.540.00;\n'),(197,0,'2018-10-26','Pessoa',5,'ADICIONAR','PROJETOBASE','agftriador','imagem : [B@4aa18c8b;\nnome : jose;\ncpf : 503.665.540.00;\n'),(198,0,'2018-10-26','Parecer',30,'ADICIONAR','PROJETOBASE','agftriador','usuario : 7;\nprocesso : 5;\n'),(199,0,'2018-10-26','Pessoa',5,'ATUALIZAR','PROJETOBASE','agftriador','imagem : [B@32a625aa;\nnome : JOSÉ;\ncpf : 503.665.540.00;\n'),(200,0,'2018-10-26','Pessoa',6,'ADICIONAR','PROJETOBASE','agftriador','nome : Matheus;\ncpf : 57861946045;\n'),(201,0,'2018-10-26','Pessoa',6,'ATUALIZAR','PROJETOBASE','agftriador','nome : MATHEUS;\ndata : Wed Oct 03 00:00:00 BRT 2018;\ncpf : 57861946045;\n'),(202,0,'2018-10-26','Pessoa',2,'ATUALIZAR','PROJETOBASE','a','imagem : [B@6ee8b9cd;\nnome : TESTE EMPRESA;\ndata : 2018-08-24;\ncpf : 776.730.300.47;\n'),(203,0,'2018-10-26','Pessoa',7,'ADICIONAR','PROJETOBASE','a','nome : Ruth;\ndata : Wed Oct 10 00:00:00 BRT 2018;\ncpf : 050.039.555.10;\n'),(204,0,'2018-10-27','Pessoa',8,'ADICIONAR','PROJETOBASE','agftriador','imagem : [B@24282201;\nnome : youre;\ncpf : 610.966.020.59;\n'),(205,0,'2018-10-27','Processo',19,'ADICIONAR','PROJETOBASE','agftriador','pessoa : 8;\ndtabertura : Wed Oct 10 00:00:00 BRT 2018;\nnumprocesso : 5432555;\n'),(206,0,'2018-10-27','Parecer',5,'ATUALIZAR','PROJETOBASE','agffinalizador1','usuario : 4;\nprocesso : 7;\ndescricao : carambolas;\n');
/*!40000 ALTER TABLE `log_agf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-28  0:54:25
