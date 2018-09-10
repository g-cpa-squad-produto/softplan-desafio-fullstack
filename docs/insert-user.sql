-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: desafio_softplan
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.31-MariaDB

INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_FINALIZADOR'),(2,'ROLE_TRIADOR');
INSERT INTO `usuario` VALUES (1,'admin','$2a$10$C/Nlewam5GZHRT3LGFH2POb0h1LmcD0pMJKF3IWSx3gvPfDrs9i6i','Administrador','2018-09-08 03:46:00');
INSERT INTO `usuario_role` VALUES (1,1);
