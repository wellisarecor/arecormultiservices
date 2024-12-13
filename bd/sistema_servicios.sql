CREATE DATABASE  IF NOT EXISTS `sistema_servicios` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sistema_servicios`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_servicios
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `dni` char(8) NOT NULL,
  `email` varchar(100) NOT NULL,
  `fecha_nacimiento` varchar(45) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(105) DEFAULT NULL,
  `estado` char(1) DEFAULT 'A',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'WELLIS GILBERTO','AREVALO CORDOVA','73358267','WELLISARECOR@GMAIL.COM','1995-05-01','942836679','JR. ALFONSO UGARTE N° 175','A','2024-10-02 17:25:25'),(2,'KERSSIN ','PANDURO RUIZ','16793672','KERSSINP@GMAIL.COM','1994-06-17','96494395','JR. MANCO CAPAC ULTIMA CUADRA','A','2024-09-24 17:55:39'),(3,'CONSUELO ','CORDOVA','00986751','CCONRDOVA@GMAIL.COM','1963-09-19','914451846','JR. ALFONSO UGARTE 175','A','2024-10-02 17:24:29'),(169,'ALFONSO ','AREVALO DEL AGUILA','00965478','ALFONSOARE@GMAIL.COM','1962-01-26','987456321','JR. ALFONOSO UGARTE N° 175','A','2024-10-02 17:27:42'),(170,'PATRICIA DEL PILAR','ARECALO CORDOVA','45632178','PATTYARECOR@GMAIL.COM','1985-11-05','987456321','JR. ALFONSO UGARTE #175 JUANJUICILLO','A','2024-10-17 21:49:16');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intereses`
--

DROP TABLE IF EXISTS `intereses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intereses` (
  `id_interes` int NOT NULL AUTO_INCREMENT,
  `id_servicio` int DEFAULT NULL,
  `porcentaje` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_interes`),
  KEY `id_servicio` (`id_servicio`),
  CONSTRAINT `intereses_ibfk_1` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intereses`
--

LOCK TABLES `intereses` WRITE;
/*!40000 ALTER TABLE `intereses` DISABLE KEYS */;
INSERT INTO `intereses` VALUES (1,3,5.00);
/*!40000 ALTER TABLE `intereses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `id_servicio` int DEFAULT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_pago` date NOT NULL,
  `tipo_pago` enum('Contado','Credito') NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_servicio` (`id_servicio`),
  CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES (1,1,500.00,'2024-09-05','Contado'),(2,2,300.00,'2024-09-10','Contado'),(3,3,1000.00,'2024-09-12','Credito');
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `pagospendientes`
--

DROP TABLE IF EXISTS `pagospendientes`;
/*!50001 DROP VIEW IF EXISTS `pagospendientes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `pagospendientes` AS SELECT 
 1 AS `id_cliente`,
 1 AS `cliente`,
 1 AS `id_servicio`,
 1 AS `servicio`,
 1 AS `monto_servicio`,
 1 AS `monto_pagado`,
 1 AS `saldo_pendiente`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `id_servicio` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `id_tipo_servicio` int DEFAULT NULL,
  `descripcion` text NOT NULL,
  `fecha_servicio` date NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_servicio`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_tipo_servicio` (`id_tipo_servicio`),
  CONSTRAINT `servicios_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `servicios_ibfk_2` FOREIGN KEY (`id_tipo_servicio`) REFERENCES `tiposservicio` (`id_tipo_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (1,1,1,'Compra de equipo deportivo','2024-09-01',1500.00),(2,2,2,'Reparación de máquina','2024-09-02',300.00),(3,3,3,'Préstamo de dinero para expansión','2024-09-03',5000.00);
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposservicio`
--

DROP TABLE IF EXISTS `tiposservicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiposservicio` (
  `id_tipo_servicio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_tipo_servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposservicio`
--

LOCK TABLES `tiposservicio` WRITE;
/*!40000 ALTER TABLE `tiposservicio` DISABLE KEYS */;
INSERT INTO `tiposservicio` VALUES (1,'Venta'),(2,'Servicio Técnico'),(3,'Préstamo de Dinero');
/*!40000 ALTER TABLE `tiposservicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `pagospendientes`
--

/*!50001 DROP VIEW IF EXISTS `pagospendientes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `pagospendientes` AS select `c`.`id_cliente` AS `id_cliente`,`c`.`nombre` AS `cliente`,`s`.`id_servicio` AS `id_servicio`,`s`.`descripcion` AS `servicio`,`s`.`monto` AS `monto_servicio`,coalesce(sum(`p`.`monto`),0) AS `monto_pagado`,(`s`.`monto` - coalesce(sum(`p`.`monto`),0)) AS `saldo_pendiente` from ((`clientes` `c` join `servicios` `s` on((`c`.`id_cliente` = `s`.`id_cliente`))) left join `pagos` `p` on((`s`.`id_servicio` = `p`.`id_servicio`))) group by `c`.`id_cliente`,`s`.`id_servicio` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 11:13:20
