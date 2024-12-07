CREATE DATABASE  IF NOT EXISTS `bibliotecaadb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bibliotecaadb`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotecaadb
-- ------------------------------------------------------
-- Server version	8.4.2

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
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `autor_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  PRIMARY KEY (`autor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'Gabriel','García Márquez'),(2,'William','Shakespeare'),(3,'Michael','Jackson'),(4,'María','Rodríguez'),(5,'Miguel','de Cervantes'),(6,'John Ronald','Reuel Tolkien'),(7,'Patrick','Rothfuss'),(8,'C.S.','Lewis'),(9,'Stephen','King');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cd`
--

DROP TABLE IF EXISTS `cd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cd` (
  `material_id` int NOT NULL,
  `formato` varchar(20) DEFAULT NULL,
  `duracion` varchar(20) DEFAULT NULL,
  `contenido` varchar(50) DEFAULT NULL,
  `numero_pistas` int DEFAULT NULL,
  `requisitos_minimos` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  CONSTRAINT `fk_cd_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd`
--

LOCK TABLES `cd` WRITE;
/*!40000 ALTER TABLE `cd` DISABLE KEYS */;
INSERT INTO `cd` VALUES (32,'CD de audio','42:20','Música Pop',9,'Reproductor de CD estándar'),(34,'CD de audio','58:40','Música',17,'Reproductor de CD estándar');
/*!40000 ALTER TABLE `cd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuracion` (
  `configuracion_id` int NOT NULL AUTO_INCREMENT,
  `limite_prestamos` int NOT NULL,
  `mora_diaria` decimal(10,2) NOT NULL,
  `rol_id` int NOT NULL,
  PRIMARY KEY (`configuracion_id`),
  UNIQUE KEY `rol_id` (`rol_id`),
  CONSTRAINT `fk_configuracion_rol` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,3,1.00,3),(2,6,0.50,2);
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `material_id` int NOT NULL,
  `ISBN` varchar(20) DEFAULT NULL,
  `numero_edicion` varchar(20) DEFAULT NULL,
  `numero_paginas` int DEFAULT NULL,
  `formato` varchar(20) DEFAULT NULL,
  `dimensiones` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  CONSTRAINT `fk_libro_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (27,'978-3-16-148410-1','Primera',96,'Tapa blanda','14 x 21 cm'),(35,'414356666141541','Primera',154,'Digital','PDF, 10 MB'),(41,'41435666614154144','Primera',154,'Digital','PDF, 10 MB');
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `fecha_hora` datetime NOT NULL,
  `nivel` varchar(20) NOT NULL,
  `usuario_id` int DEFAULT NULL,
  `accion` varchar(50) NOT NULL,
  `descripcion` text,
  `ip_direccion` varchar(45) DEFAULT NULL,
  `detalles` text,
  PRIMARY KEY (`log_id`),
  KEY `fk_log_usuario` (`usuario_id`),
  CONSTRAINT `fk_log_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'2024-11-01 08:00:00','INFO',3,'Inicio de Sesión','El usuario carlosl ha iniciado sesión.','192.168.1.10',NULL),(2,'2024-11-01 08:05:00','INFO',3,'Realizar Préstamo','El usuario carlosl realizó un préstamo del material ID 1.','192.168.1.10',NULL),(3,'2024-10-20 09:00:00','INFO',4,'Inicio de Sesión','El usuario anam ha iniciado sesión.','192.168.1.11',NULL),(4,'2024-10-20 09:10:00','INFO',4,'Realizar Préstamo','El usuario anam realizó un préstamo del material ID 2.','192.168.1.11',NULL),(5,'2024-11-06 10:00:00','WARN',4,'Mora Generada','Se generó una mora de 2.00 para el usuario anam por el préstamo ID 2.','192.168.1.11',NULL);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `tipo_material_id` int NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `año_publicacion` int DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `ubicacion_fisica` varchar(50) DEFAULT NULL,
  `estado` varchar(15) DEFAULT 'Disponible',
  `cantidad_total` int DEFAULT '1',
  `cantidad_disponible` int DEFAULT '1',
  `descripcion` text,
  `fecha_adquisicion` date DEFAULT NULL,
  `codigo_barras` varchar(50) DEFAULT NULL,
  `idioma` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  UNIQUE KEY `codigo_barras` (`codigo_barras`),
  KEY `fk_material_tipo` (`tipo_material_id`),
  CONSTRAINT `fk_material_tipo` FOREIGN KEY (`tipo_material_id`) REFERENCES `tipomaterial` (`tipo_material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,1,'El Quijote (Edición Revisada)',1605,'Francisco de Robles','Miguel de Cervantes','Estante A2','Disponible',10,8,'Edición revisada con comentarios.','2024-11-13','CB011','Español'),(2,2,'Revista National Geographic - Edición Septiembre 2021',2020,'National Geographic Society',NULL,'Estante B2','Disponible',5,5,'Revista de ciencia y naturaleza','2024-11-27','CB002','Español'),(4,4,'Tesis sobre Inteligencia Artificial',2020,'Universidad XYZ',NULL,'Estante D4','Disponible',1,1,'Tesis doctoral sobre IA','2020-12-01','CB004','Español'),(5,5,'Obras Completas de Shakespeare',1997,'Penguin Classics',NULL,'Estante E5','Disponible',4,3,'Colección de obras de William Shakespeare','2010-03-22','CB005','Inglés'),(23,2,'Science Advances',2024,'American Association for the Advancement of Science',NULL,'Estante C4','Disponible',15,13,'Revista científica enfocada en los avances tecnológicos y descubrimientos recientes.','2024-01-10','9876543210987','Inglés'),(24,2,'Time - Edición Internacional',2023,'Time USA, LLC',NULL,'Estante D2','Disponible',22,22,'Revista internacional con enfoque en noticias globales y análisis político.','2024-10-01','9789876543210','Inglés'),(25,2,'National Geographic - Edición Especial',2023,'National Geographic Society',NULL,'Estante B3','Disponible',10,9,'Edición especial de la revista con temas científicos.','2023-05-01','1234567890123','Español'),(26,2,'Forbes - Ranking de Millonarios',2024,'Forbes Media LLC',NULL,'Estante E5','Disponible',30,30,'Edición especial con el ranking de las personas más ricas del mundo y análisis financiero.','2024-10-02','1234987654321','Inglés'),(27,1,'El Principito',1943,'Reynal & Hitchcock','Antoine de Saint-Exupéry','Estante A3','Disponible',5,5,'Libro clásico de literatura infantil y filosófica.','2024-01-01','CB009','Español'),(28,6,'Mapa Topográfico de la Región Actualizado',2023,'Instituto Geográfico Nacional',NULL,'Estante F6','Disponible',1,1,'Mapa detallado de la región con actualizaciones de carreteras y caminos.','2024-12-01','CB106','Español'),(29,6,'Constitución Política',2024,'Editorial Jurídica',NULL,'Estante G1','Disponible',3,3,'Documento legal que establece las normas fundamentales del Estado.','2024-11-20','CB110','Español'),(31,6,'Informe Técnico de Impacto Ambiental',2023,'EcoConsultores S.A.',NULL,'Estante H3','Disponible',2,2,'Informe detallado sobre el impacto ambiental de la nueva planta industrial.','2024-09-15','CB111','Español'),(32,3,'Album Thriller (Remasterizado)',1982,'Epic Records','Michael Jackson','Estante C3','Disponible',2,2,'Álbum clásico remasterizado de Michael Jackson.','2025-01-01','CB333','Inglés'),(34,3,'Greatest Hits',1981,'EMI','Queen','Estante D1','Disponible',5,5,'Colección de las mejores canciones de Queen.','2024-05-10','CB212','Inglés'),(35,1,'ABC Edicion Especial',2023,'ABC','ABC Autor','Estante B2','Disponible',2,2,'ABC','2024-12-07','4654168486','Espa?ol'),(37,4,'Test1',2024,'Autor tesis','Auto 2','Test1','Disponible',2,2,'Test1','2024-12-07','46541684862','Ingl?s'),(38,4,'Test198',2024,'Autor tesis',NULL,'Test1','Disponible',2,2,'Test1','2024-12-07','465416848624156','Espa�ol'),(39,4,'Test1e3232',2024,'Autor tesis',NULL,'Test1','Disponible',2,2,'Test1','2024-12-07','4654168486354165','Ingl�s'),(40,4,'Test11546182',2024,'Test1','Autor tesis98127224','Test1','Disponible',2,2,'Test1','2024-12-07','4654168486268618145','Espa?ol'),(41,1,'Informe T�cnico de Impacto Ambiental4545',2023,'Test1','Autor tesis','Test1','Disponible',2,2,'Test1','2024-12-07','4654168486544544','Ingl�s'),(42,5,'Test1e2q313',2024,'Test1','Autor obras','Test1','Disponible',2,2,'Test1','2024-12-07','465416848621487677','Ingl�s'),(43,5,'Test157',2024,'Test1','Autor obras856123','Test1','Disponible',2,2,'Test1','2024-12-07','465416848621828564','Ingl?s');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mora`
--

DROP TABLE IF EXISTS `mora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mora` (
  `mora_id` int NOT NULL AUTO_INCREMENT,
  `prestamo_id` int NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_generacion` date NOT NULL,
  `estado` varchar(10) DEFAULT 'Pendiente',
  PRIMARY KEY (`mora_id`),
  UNIQUE KEY `prestamo_id` (`prestamo_id`),
  CONSTRAINT `fk_mora_prestamo` FOREIGN KEY (`prestamo_id`) REFERENCES `prestamo` (`prestamo_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mora`
--

LOCK TABLES `mora` WRITE;
/*!40000 ALTER TABLE `mora` DISABLE KEYS */;
INSERT INTO `mora` VALUES (1,2,2.00,'2023-10-06','Pendiente'),(2,1,418.00,'2024-12-06','Pendiente');
/*!40000 ALTER TABLE `mora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obra`
--

DROP TABLE IF EXISTS `obra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obra` (
  `material_id` int NOT NULL,
  `tipo_obra` varchar(50) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `numero_volumenes` int DEFAULT NULL,
  `artista_compositor` varchar(100) DEFAULT NULL,
  `formato` varchar(50) DEFAULT NULL,
  `dimensiones` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  CONSTRAINT `fk_obra_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra`
--

LOCK TABLES `obra` WRITE;
/*!40000 ALTER TABLE `obra` DISABLE KEYS */;
INSERT INTO `obra` VALUES (5,'Teatro','Clásico',1,'William Shakespeare','Libro','20 x 25 cm'),(42,'test1','test1',NULL,NULL,'Digital','12 x 21 cm');
/*!40000 ALTER TABLE `obra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otrosdocumentos`
--

DROP TABLE IF EXISTS `otrosdocumentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otrosdocumentos` (
  `material_id` int NOT NULL,
  `tipo_documento` varchar(50) DEFAULT NULL,
  `organizacion_entidad` varchar(100) DEFAULT NULL,
  `numero_serie` varchar(50) DEFAULT NULL,
  `formato` varchar(50) DEFAULT NULL,
  `dimensiones` varchar(50) DEFAULT NULL,
  `requisitos_especiales` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  CONSTRAINT `fk_otrosdocumentos_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otrosdocumentos`
--

LOCK TABLES `otrosdocumentos` WRITE;
/*!40000 ALTER TABLE `otrosdocumentos` DISABLE KEYS */;
INSERT INTO `otrosdocumentos` VALUES (28,'Mapa','Instituto Geográfico Nacional','MGN-REG-2025','Impreso','100 x 70 cm','Manejo cuidadoso; evitar dobleces y exposición al agua.'),(29,'Documento Legal','Gobierno Nacional','DL-2024-CP','Impreso','21 x 29.7 cm','Almacenamiento en lugar seguro, evitar humedad.'),(31,'Informe Técnico','EcoConsultores S.A.','IT-2023-ECO','Digital','PDF, 10 MB','Requiere lector de PDF, acceso restringido.');
/*!40000 ALTER TABLE `otrosdocumentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `prestamo_id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `material_id` int NOT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion_prevista` date NOT NULL,
  `fecha_devolucion_real` date DEFAULT NULL,
  `estado` varchar(10) DEFAULT 'Activo',
  PRIMARY KEY (`prestamo_id`),
  KEY `fk_prestamo_usuario` (`usuario_id`),
  KEY `fk_prestamo_material` (`material_id`),
  CONSTRAINT `fk_prestamo_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_prestamo_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,3,1,'2023-10-01','2023-10-15',NULL,'Activo'),(2,4,2,'2023-09-20','2023-10-05',NULL,'Finalizado'),(3,4,5,'2023-10-10','2023-10-25',NULL,'Activo'),(4,5,32,'2024-12-06','2024-12-21','2024-12-06','Finalizado'),(5,5,29,'2024-12-06','2024-12-21','2024-12-06','Finalizado'),(6,5,1,'2024-12-06','2024-12-21','2024-12-06','Finalizado');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revista`
--

DROP TABLE IF EXISTS `revista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revista` (
  `material_id` int NOT NULL,
  `ISSN` varchar(20) DEFAULT NULL,
  `volumen` varchar(20) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `periodicidad` varchar(20) DEFAULT NULL,
  `tema_principal` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  CONSTRAINT `fk_revista_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revista`
--

LOCK TABLES `revista` WRITE;
/*!40000 ALTER TABLE `revista` DISABLE KEYS */;
INSERT INTO `revista` VALUES (2,'0027-9358','Vol. 240','No. 3','2021-09-01','Mensual','Ciencia y Naturaleza'),(23,'1234-5678','Vol. 30','No. 1','2024-01-01','Mensual','Ciencia y Tecnología'),(24,'0024-3019','Vol. 200','No. 48','2024-09-20','Semanal','Actualidad y Política'),(25,'9876-5432','Vol. 10','No. 2','2023-05-15','Mensual','Ciencia y Naturaleza'),(26,'0015-6914','Vol. 35','No. 7','2024-01-02','Mensual','Finanzas y Negocios');
/*!40000 ALTER TABLE `revista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`rol_id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(3,'Alumno'),(2,'Profesor');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tesis`
--

DROP TABLE IF EXISTS `tesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tesis` (
  `material_id` int NOT NULL,
  `grado_academico` varchar(50) DEFAULT NULL,
  `institucion` varchar(100) DEFAULT NULL,
  `director` varchar(100) DEFAULT NULL,
  `area_investigacion` varchar(100) DEFAULT NULL,
  `fecha_defensa` date DEFAULT NULL,
  `numero_paginas` int DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  CONSTRAINT `fk_tesis_material` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tesis`
--

LOCK TABLES `tesis` WRITE;
/*!40000 ALTER TABLE `tesis` DISABLE KEYS */;
INSERT INTO `tesis` VALUES (4,'Doctorado','Universidad XYZ','Dr. Alberto Fernández','Inteligencia Artificial','2020-11-20',250),(40,'Test1','Test1','Test1','Test1','2024-11-01',154);
/*!40000 ALTER TABLE `tesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipomaterial`
--

DROP TABLE IF EXISTS `tipomaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipomaterial` (
  `tipo_material_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`tipo_material_id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipomaterial`
--

LOCK TABLES `tipomaterial` WRITE;
/*!40000 ALTER TABLE `tipomaterial` DISABLE KEYS */;
INSERT INTO `tipomaterial` VALUES (3,'CD'),(1,'Libro'),(5,'Obra'),(6,'Otros Documentos'),(2,'Revista'),(4,'Tesis');
/*!40000 ALTER TABLE `tipomaterial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuario_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `correo_electronico` varchar(100) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `contraseña` varchar(256) NOT NULL,
  `estado` varchar(10) DEFAULT 'Activo',
  `rol_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`),
  KEY `fk_usuario_rol` (`rol_id`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Juan','Pérez','juan.perez@ejemplo.com','juanp','0192023a7bbd73250516f069df18b500','Activo',1),(2,'María','Gómez','maria.gomez@ejemplo.com','mariag','70cf5c0095d91b8f2b9798700651df25','Activo',2),(3,'Carlos','López','carlos.lopez@ejemplo.com','carlosl','0c82ca5b1092a0c21dcfe3200688046e','Activo',3),(4,'Ana','Martínez','ana.martinez@ejemplo.com','anam','0c82ca5b1092a0c21dcfe3200688046e','Activo',3),(5,'Camila','Ramos','Cramos@axample.com','cramos','123456789','Activo',3),(6,'Hilda','Ortiz','Hortiz@example.com','hortiz','123456789','Activo',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bibliotecaadb'
--

--
-- Dumping routines for database 'bibliotecaadb'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizar_cd` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_cd`(
    IN p_material_id INT,
    IN p_tipo_material_id INT,
    IN p_titulo VARCHAR(200),
    IN p_año_publicacion INT,
    IN p_editorial VARCHAR(100),
    IN p_autor VARCHAR(100),
    IN p_ubicacion_fisica VARCHAR(50),
    IN p_estado VARCHAR(15),
    IN p_cantidad_total INT,
    IN p_cantidad_disponible INT,
    IN p_descripcion TEXT,
    IN p_fecha_adquisicion DATE,
    IN p_codigo_barras VARCHAR(50),
    IN p_idioma VARCHAR(30),
    IN p_formato VARCHAR(20),
    IN p_duracion VARCHAR(20),
    IN p_contenido VARCHAR(50),
    IN p_numero_pistas INT,
    IN p_requisitos_minimos VARCHAR(100),
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejadores de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;
    
    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    -- Validaciones de entrada: todos los campos deben ser obligatorios
    IF p_material_id IS NULL OR p_tipo_material_id IS NULL OR p_titulo IS NULL OR
       p_año_publicacion IS NULL OR p_editorial IS NULL OR p_autor IS NULL OR 
       p_ubicacion_fisica IS NULL OR p_estado IS NULL OR p_cantidad_total IS NULL OR
       p_cantidad_disponible IS NULL OR p_descripcion IS NULL OR p_fecha_adquisicion IS NULL OR
       p_codigo_barras IS NULL OR p_idioma IS NULL OR p_formato IS NULL OR
       p_duracion IS NULL OR p_contenido IS NULL OR p_numero_pistas IS NULL OR
       p_requisitos_minimos IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'Todos los campos son obligatorios.';
        LEAVE sp;
    END IF;

    -- Validación lógica: cantidad total >= cantidad disponible
    IF p_cantidad_total < p_cantidad_disponible THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La cantidad total no puede ser menor que la cantidad disponible.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en la tabla material
    IF NOT EXISTS (SELECT 1 FROM material WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no existe en la tabla material.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en la tabla cd
    IF NOT EXISTS (SELECT 1 FROM cd WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no existe en la tabla cd.';
        LEAVE sp;
    END IF;

    -- Iniciar transacción
    START TRANSACTION;

    -- Actualizar en la tabla material
    UPDATE material
    SET tipo_material_id = p_tipo_material_id,
        titulo = p_titulo,
        año_publicacion = p_año_publicacion,
        editorial = p_editorial,
        autor = p_autor,
        ubicacion_fisica = p_ubicacion_fisica,
        estado = p_estado,
        cantidad_total = p_cantidad_total,
        cantidad_disponible = p_cantidad_disponible,
        descripcion = p_descripcion,
        fecha_adquisicion = p_fecha_adquisicion,
        codigo_barras = p_codigo_barras,
        idioma = p_idioma
    WHERE material_id = p_material_id;

    -- Actualizar en la tabla cd
    UPDATE cd
    SET formato = p_formato,
        duracion = p_duracion,
        contenido = p_contenido,
        numero_pistas = p_numero_pistas,
        requisitos_minimos = p_requisitos_minimos
    WHERE material_id = p_material_id;

    -- Confirmar transacción
    COMMIT;

    -- Finalización exitosa
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizar_otros_documentos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_otros_documentos`(
    IN p_material_id INT,
    IN p_tipo_material_id INT,
    IN p_titulo VARCHAR(200),
    IN p_año_publicacion INT,
    IN p_editorial VARCHAR(100),
    IN p_ubicacion_fisica VARCHAR(50),
    IN p_estado VARCHAR(15),
    IN p_cantidad_total INT,
    IN p_cantidad_disponible INT,
    IN p_descripcion TEXT,
    IN p_fecha_adquisicion DATE,
    IN p_codigo_barras VARCHAR(50),
    IN p_idioma VARCHAR(30),
    IN p_tipo_documento VARCHAR(50),
    IN p_organizacion_entidad VARCHAR(100),
    IN p_numero_serie VARCHAR(50),
    IN p_formato VARCHAR(50),
    IN p_dimensiones VARCHAR(50),
    IN p_requisitos_especiales VARCHAR(100),
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejadores de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    -- Validaciones de entrada: campos no nulos
    IF p_material_id IS NULL OR p_tipo_material_id IS NULL OR p_titulo IS NULL OR
       p_año_publicacion IS NULL OR p_editorial IS NULL OR p_ubicacion_fisica IS NULL OR
       p_estado IS NULL OR p_cantidad_total IS NULL OR p_cantidad_disponible IS NULL OR
       p_descripcion IS NULL OR p_fecha_adquisicion IS NULL OR p_codigo_barras IS NULL OR
       p_idioma IS NULL OR p_tipo_documento IS NULL OR p_organizacion_entidad IS NULL OR
       p_numero_serie IS NULL OR p_formato IS NULL OR p_dimensiones IS NULL OR
       p_requisitos_especiales IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'Todos los campos son obligatorios.';
        LEAVE sp; -- Detener la ejecución
    END IF;

    -- Validación lógica: cantidad total >= cantidad disponible
    IF p_cantidad_total < p_cantidad_disponible THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La cantidad total no puede ser menor que la cantidad disponible.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en la tabla material
    IF NOT EXISTS (SELECT 1 FROM material WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no existe en la tabla material.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en la tabla otrosdocumentos
    IF NOT EXISTS (SELECT 1 FROM otrosdocumentos WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no existe en la tabla otrosdocumentos.';
        LEAVE sp;
    END IF;

    -- Iniciar transacción
    START TRANSACTION;

    -- Actualizar en la tabla material
    UPDATE material
    SET tipo_material_id = p_tipo_material_id, 
        titulo = p_titulo, 
        año_publicacion = p_año_publicacion, 
        editorial = p_editorial,
        ubicacion_fisica = p_ubicacion_fisica, 
        estado = p_estado, 
        cantidad_total = p_cantidad_total, 
        cantidad_disponible = p_cantidad_disponible,
        descripcion = p_descripcion, 
        fecha_adquisicion = p_fecha_adquisicion, 
        codigo_barras = p_codigo_barras, 
        idioma = p_idioma
    WHERE material_id = p_material_id;

    -- Actualizar en la tabla otrosdocumentos
    UPDATE otrosdocumentos
    SET tipo_documento = p_tipo_documento, 
        organizacion_entidad = p_organizacion_entidad, 
        numero_serie = p_numero_serie, 
        formato = p_formato, 
        dimensiones = p_dimensiones, 
        requisitos_especiales = p_requisitos_especiales
    WHERE material_id = p_material_id;

    -- Confirmar transacción
    COMMIT;

    -- Configurar valores de éxito
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizar_revista` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_revista`(
    IN p_material_id INT,
    IN p_tipo_material_id INT,
    IN p_titulo VARCHAR(200),
    IN p_año_publicacion INT,
    IN p_editorial VARCHAR(100),
    IN p_ubicacion_fisica VARCHAR(50),
    IN p_estado VARCHAR(15),
    IN p_cantidad_total INT,
    IN p_cantidad_disponible INT,
    IN p_descripcion TEXT,
    IN p_fecha_adquisicion DATE,
    IN p_codigo_barras VARCHAR(50),
    IN p_idioma VARCHAR(30),
    IN p_ISSN VARCHAR(20),
    IN p_volumen VARCHAR(20),
    IN p_numero VARCHAR(20),
    IN p_fecha_publicacion DATE,
    IN p_periodicidad VARCHAR(20),
    IN p_tema_principal VARCHAR(100),
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    -- Validaciones de entrada: campos no nulos
    IF p_material_id IS NULL OR p_tipo_material_id IS NULL OR p_titulo IS NULL OR
       p_año_publicacion IS NULL OR p_editorial IS NULL OR p_ubicacion_fisica IS NULL OR
       p_estado IS NULL OR p_cantidad_total IS NULL OR p_cantidad_disponible IS NULL OR
       p_descripcion IS NULL OR p_fecha_adquisicion IS NULL OR p_codigo_barras IS NULL OR
       p_idioma IS NULL OR p_ISSN IS NULL OR p_volumen IS NULL OR
       p_numero IS NULL OR p_fecha_publicacion IS NULL OR p_periodicidad IS NULL OR
       p_tema_principal IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'Todos los campos son obligatorios.';
        LEAVE sp; -- Detener la ejecución
    END IF;

    -- Validación lógica: cantidad total >= cantidad disponible
    IF p_cantidad_total < p_cantidad_disponible THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La cantidad total no puede ser menor que la cantidad disponible.';
        LEAVE sp;
    END IF;

    -- Validación de existencia de material en la tabla material
    IF NOT EXISTS (SELECT 1 FROM material WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no existe en la tabla material.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en la tabla revista
    IF NOT EXISTS (SELECT 1 FROM revista WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no existe en la tabla revista.';
        LEAVE sp;
    END IF;

    -- Iniciar transacción
    START TRANSACTION;

    -- Actualizar en la tabla material
    UPDATE material
    SET tipo_material_id = p_tipo_material_id, 
        titulo = p_titulo, 
        año_publicacion = p_año_publicacion, 
        editorial = p_editorial,
        ubicacion_fisica = p_ubicacion_fisica, 
        estado = p_estado, 
        cantidad_total = p_cantidad_total, 
        cantidad_disponible = p_cantidad_disponible,
        descripcion = p_descripcion, 
        fecha_adquisicion = p_fecha_adquisicion, 
        codigo_barras = p_codigo_barras, 
        idioma = p_idioma
    WHERE material_id = p_material_id;

    -- Actualizar en la tabla revista
    UPDATE revista
    SET ISSN = p_ISSN, 
        volumen = p_volumen, 
        numero = p_numero, 
        fecha_publicacion = p_fecha_publicacion, 
        periodicidad = p_periodicidad, 
        tema_principal = p_tema_principal
    WHERE material_id = p_material_id;

    -- Confirmar transacción
    COMMIT;

    -- Configurar valores de éxito
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminar_cd` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_cd`(
    IN p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 
            @p_sqlstate = RETURNED_SQLSTATE, 
            @p_errno = MYSQL_ERRNO, 
            @p_text = MESSAGE_TEXT;
        
        SET p_error = 1;
        
        IF @p_text IS NULL THEN
            SET p_mensaje_error = CONCAT('Ocurrió un error inesperado. Código de error: ', @p_errno);
        ELSE
            SET p_mensaje_error = @p_text;
        END IF;
        
        ROLLBACK;
    END;

    -- Iniciar transacción
    START TRANSACTION;

    -- Eliminar de la tabla cd
    DELETE FROM cd WHERE material_id = p_material_id;

    -- Eliminar de la tabla material
    DELETE FROM material WHERE material_id = p_material_id;

    -- Confirmar transacción
    COMMIT;

    -- Finalización exitosa
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminar_otros_documentos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_otros_documentos`(
    IN p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            @p_sqlstate = RETURNED_SQLSTATE,
            @p_errno = MYSQL_ERRNO,
            @p_text = MESSAGE_TEXT;
        SET p_error = 1;
        IF @p_text IS NULL THEN
            SET p_mensaje_error = CONCAT('Ocurrió un error inesperado. Código de error: ', @p_errno);
        ELSE
            SET p_mensaje_error = @p_text;
        END IF;
        ROLLBACK;
    END;

    -- Iniciar transacción
    START TRANSACTION;

    -- Eliminar de la tabla otrosdocumentos
    DELETE FROM otrosdocumentos WHERE material_id = p_material_id;

    -- Eliminar de la tabla material
    DELETE FROM material WHERE material_id = p_material_id;

    -- Confirmar transacción
    COMMIT;

    -- Finalización exitosa
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminar_revista` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_revista`(
    IN p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    START TRANSACTION;

    -- Eliminar de la tabla revista
    DELETE FROM revista WHERE material_id = p_material_id;

    -- Eliminar de la tabla material
    DELETE FROM material WHERE material_id = p_material_id;

    COMMIT;

    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_IngresarLibro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_IngresarLibro`(
	isbn_IN varchar(20),
    edicion_IN varchar(20),
    titulo_IN varchar(200),
    descripcion_IN text,
    ano_IN int, 
    paginas_IN int, 
    editorial_IN varchar(100),
    autor_IN varchar(100),
    formato_IN varchar(20),
    dimensiones_IN varchar(50),
    ubicacion_IN varchar(50),
    codigo_IN varchar(50),
    idioma_IN varchar(30),
    cantidad_IN int
)
BEGIN

	INSERT INTO material
    SET 
		tipo_material_id = 1,
        titulo = titulo_IN, 
        año_publicacion = ano_IN, 
        editorial = editorial_IN,
         autor = autor_IN,
        ubicacion_fisica = ubicacion_IN,
        estado = 'Disponible',
        cantidad_total = cantidad_IN,
        cantidad_disponible = cantidad_IN,
        descripcion = descripcion_IN,
        fecha_adquisicion = CURDATE(),
        codigo_barras = codigo_IN,
        idioma = idioma_IN;
	
    INSERT INTO libro
    SET
		material_id = LAST_INSERT_ID(),
        ISBN = isbn_IN,
        numero_edicion = edicion_IN,
        numero_paginas = paginas_IN,
        formato = formato_IN, 
        dimensiones = dimensiones_IN;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_IngresarObra` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_IngresarObra`(
	tipo_obra_IN VARCHAR(50),
    genero_IN VARCHAR(50),
    formato_IN VARCHAR(50),
    dimensiones_IN VARCHAR(50),
    titulo_IN varchar(200),
    descripcion_IN text,
    ano_IN int, 
    editorial_IN varchar(100),
    ubicacion_IN varchar(50),
    codigo_IN varchar(50),
    idioma_IN varchar(30),
    cantidad_IN int,
    autor_IN varchar(100)

)
BEGIN

	INSERT INTO material
    SET 
		tipo_material_id = 5,
        titulo = titulo_IN, 
        año_publicacion = ano_IN, 
        editorial = editorial_IN,
        ubicacion_fisica = ubicacion_IN,
        estado = 'Disponible',
        cantidad_total = cantidad_IN,
        cantidad_disponible = cantidad_IN,
        descripcion = descripcion_IN,
        fecha_adquisicion = CURDATE(),
        codigo_barras = codigo_IN,
        idioma = idioma_IN,
        autor = autor_IN;
    INSERT INTO obra
    SET
		material_id = LAST_INSERT_ID(),
        tipo_obra = tipo_obra_IN,
        genero = genero_IN,
        formato = formato_IN, 
        dimensiones = dimensiones_IN;
	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_IngresarTesis` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_IngresarTesis`(
	grado_academico_IN VARCHAR(50),
    institucion_IN VARCHAR(100),
	director_IN VARCHAR(100),
    area_investigacion_IN VARCHAR(100),
    fecha_defensa_IN DATE,
    numero_paginas_IN INT,
    titulo_IN varchar(200),
    descripcion_IN text,
    ano_IN int, 
    editorial_IN varchar(100),
    autor_IN varchar(100),
    ubicacion_IN varchar(50),
    codigo_IN varchar(50),
    idioma_IN varchar(30),
    cantidad_IN int
)
BEGIN

	INSERT INTO material
    SET 
		tipo_material_id = 4,
        titulo = titulo_IN, 
        año_publicacion = ano_IN, 
        editorial = editorial_IN,
        autor = autor_IN,
        ubicacion_fisica = ubicacion_IN,
        estado = 'Disponible',
        cantidad_total = cantidad_IN,
        cantidad_disponible = cantidad_IN,
        descripcion = descripcion_IN,
        fecha_adquisicion = CURDATE(),
        codigo_barras = codigo_IN,
        idioma = idioma_IN;
	
    INSERT INTO tesis
    SET
		material_id = LAST_INSERT_ID(),
        grado_academico = grado_academico_IN,
        institucion = institucion_IN,
        director = director_IN,
        area_investigacion = area_investigacion_IN,
        fecha_defensa = fecha_defensa_IN,
        numero_paginas = numero_paginas_IN;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertar_cd` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_cd`(
    IN p_tipo_material_id INT,
    IN p_titulo VARCHAR(200),
    IN p_año_publicacion INT,
    IN p_editorial VARCHAR(100),
    IN p_autor VARCHAR(100),
    IN p_ubicacion_fisica VARCHAR(50),
    IN p_estado VARCHAR(15),
    IN p_cantidad_total INT,
    IN p_cantidad_disponible INT,
    IN p_descripcion TEXT,
    IN p_fecha_adquisicion DATE,
    IN p_codigo_barras VARCHAR(50),
    IN p_idioma VARCHAR(30),
    IN p_formato VARCHAR(20),
    IN p_duracion VARCHAR(20),
    IN p_contenido VARCHAR(50),
    IN p_numero_pistas INT,
    IN p_requisitos_minimos VARCHAR(100),
    OUT p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejadores de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    -- Validaciones de entrada
    IF p_tipo_material_id IS NULL OR p_titulo IS NULL OR p_año_publicacion IS NULL OR 
       p_editorial IS NULL OR p_autor IS NULL OR p_ubicacion_fisica IS NULL OR p_estado IS NULL OR
       p_cantidad_total IS NULL OR p_cantidad_disponible IS NULL OR 
       p_descripcion IS NULL OR p_fecha_adquisicion IS NULL OR p_codigo_barras IS NULL OR 
       p_idioma IS NULL OR p_formato IS NULL OR p_duracion IS NULL OR
       p_contenido IS NULL OR p_numero_pistas IS NULL OR p_requisitos_minimos IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'Todos los campos son obligatorios.';
        LEAVE sp;
    END IF;

    -- Validación lógica: cantidad total >= cantidad disponible
    IF p_cantidad_total < p_cantidad_disponible THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La cantidad total no puede ser menor que la cantidad disponible.';
        LEAVE sp;
    END IF;

    -- Validar duplicados: código de barras único
    IF EXISTS (SELECT 1 FROM material WHERE codigo_barras = p_codigo_barras) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El código de barras ya existe.';
        LEAVE sp;
    END IF;

    -- Iniciar transacción
    START TRANSACTION;

    -- Insertar en la tabla material
    INSERT INTO material (
        tipo_material_id,
        titulo,
        año_publicacion,
        editorial,
        autor,
        ubicacion_fisica,
        estado,
        cantidad_total,
        cantidad_disponible,
        descripcion,
        fecha_adquisicion,
        codigo_barras,
        idioma
    ) VALUES (
        p_tipo_material_id,
        p_titulo,
        p_año_publicacion,
        p_editorial,
        p_autor,
        p_ubicacion_fisica,
        p_estado,
        p_cantidad_total,
        p_cantidad_disponible,
        p_descripcion,
        p_fecha_adquisicion,
        p_codigo_barras,
        p_idioma
    );

    SET p_material_id = LAST_INSERT_ID();

    -- Insertar en la tabla cd
    INSERT INTO cd (
        material_id,
        formato,
        duracion,
        contenido,
        numero_pistas,
        requisitos_minimos
    ) VALUES (
        p_material_id,
        p_formato,
        p_duracion,
        p_contenido,
        p_numero_pistas,
        p_requisitos_minimos
    );

    -- Confirmar transacción
    COMMIT;

    -- Configurar valores de éxito
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertar_otros_documentos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_otros_documentos`(
    IN p_tipo_material_id INT,
    IN p_titulo VARCHAR(200),
    IN p_año_publicacion INT,
    IN p_editorial VARCHAR(100),
    IN p_ubicacion_fisica VARCHAR(50),
    IN p_estado VARCHAR(15),
    IN p_cantidad_total INT,
    IN p_cantidad_disponible INT,
    IN p_descripcion TEXT,
    IN p_fecha_adquisicion DATE,
    IN p_codigo_barras VARCHAR(50),
    IN p_idioma VARCHAR(30),
    IN p_tipo_documento VARCHAR(50),
    IN p_organizacion_entidad VARCHAR(100),
    IN p_numero_serie VARCHAR(50),
    IN p_formato VARCHAR(50),
    IN p_dimensiones VARCHAR(50),
    IN p_requisitos_especiales VARCHAR(100),
    OUT p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejadores de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    -- Validaciones de entrada
    IF p_tipo_material_id IS NULL OR p_titulo IS NULL OR p_año_publicacion IS NULL OR 
       p_editorial IS NULL OR p_ubicacion_fisica IS NULL OR p_estado IS NULL OR
       p_cantidad_total IS NULL OR p_cantidad_disponible IS NULL OR 
       p_descripcion IS NULL OR p_fecha_adquisicion IS NULL OR p_codigo_barras IS NULL OR 
       p_idioma IS NULL OR p_tipo_documento IS NULL OR p_organizacion_entidad IS NULL OR
       p_numero_serie IS NULL OR p_formato IS NULL OR p_dimensiones IS NULL OR
       p_requisitos_especiales IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'Todos los campos son obligatorios.';
        LEAVE sp;
    END IF;

    -- Validación lógica: cantidad total >= cantidad disponible
    IF p_cantidad_total < p_cantidad_disponible THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La cantidad total no puede ser menor que la cantidad disponible.';
        LEAVE sp;
    END IF;

    -- Validar duplicados: código de barras único
    IF EXISTS (SELECT 1 FROM material WHERE codigo_barras = p_codigo_barras) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El código de barras ya existe.';
        LEAVE sp;
    END IF;

    -- Validar duplicados: número de serie único (si aplica)
    IF EXISTS (SELECT 1 FROM otrosdocumentos WHERE numero_serie = p_numero_serie) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El número de serie ya existe.';
        LEAVE sp;
    END IF;

    -- Iniciar transacción
    START TRANSACTION;

    -- Insertar en la tabla material
    INSERT INTO material (
        tipo_material_id,
        titulo,
        año_publicacion,
        editorial,
        ubicacion_fisica,
        estado,
        cantidad_total,
        cantidad_disponible,
        descripcion,
        fecha_adquisicion,
        codigo_barras,
        idioma
    ) VALUES (
        p_tipo_material_id,
        p_titulo,
        p_año_publicacion,
        p_editorial,
        p_ubicacion_fisica,
        p_estado,
        p_cantidad_total,
        p_cantidad_disponible,
        p_descripcion,
        p_fecha_adquisicion,
        p_codigo_barras,
        p_idioma
    );

    SET p_material_id = LAST_INSERT_ID();

    -- Insertar en la tabla otrosdocumentos
    INSERT INTO otrosdocumentos (
        material_id,
        tipo_documento,
        organizacion_entidad,
        numero_serie,
        formato,
        dimensiones,
        requisitos_especiales
    ) VALUES (
        p_material_id,
        p_tipo_documento,
        p_organizacion_entidad,
        p_numero_serie,
        p_formato,
        p_dimensiones,
        p_requisitos_especiales
    );

    -- Confirmar transacción
    COMMIT;

    -- Configurar valores de éxito
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertar_revista` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_revista`(
    IN p_tipo_material_id INT,
    IN p_titulo VARCHAR(200),
    IN p_año_publicacion INT,
    IN p_editorial VARCHAR(100),
    IN p_ubicacion_fisica VARCHAR(50),
    IN p_estado VARCHAR(15),
    IN p_cantidad_total INT,
    IN p_cantidad_disponible INT,
    IN p_descripcion TEXT,
    IN p_fecha_adquisicion DATE,
    IN p_codigo_barras VARCHAR(50),
    IN p_idioma VARCHAR(30),
    IN p_ISSN VARCHAR(20),
    IN p_volumen VARCHAR(20),
    IN p_numero VARCHAR(20),
    IN p_fecha_publicacion DATE,
    IN p_periodicidad VARCHAR(20),
    IN p_tema_principal VARCHAR(100),
    OUT p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
        ROLLBACK;
    END;

    -- Validaciones de entrada
    IF p_tipo_material_id IS NULL OR p_titulo IS NULL OR p_año_publicacion IS NULL OR 
       p_editorial IS NULL OR p_ubicacion_fisica IS NULL OR p_estado IS NULL OR
       p_cantidad_total IS NULL OR p_cantidad_disponible IS NULL OR 
       p_descripcion IS NULL OR p_fecha_adquisicion IS NULL OR p_codigo_barras IS NULL OR 
       p_idioma IS NULL OR p_ISSN IS NULL OR p_volumen IS NULL OR 
       p_numero IS NULL OR p_fecha_publicacion IS NULL OR p_periodicidad IS NULL OR 
       p_tema_principal IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'Todos los campos son obligatorios.';
        LEAVE sp;
    END IF;

    -- Validación lógica: cantidad total >= cantidad disponible
    IF p_cantidad_total < p_cantidad_disponible THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La cantidad total no puede ser menor que la cantidad disponible.';
        LEAVE sp;
    END IF;

    -- Validar duplicados: código de barras único
    IF EXISTS (SELECT 1 FROM material WHERE codigo_barras = p_codigo_barras) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El código de barras ya existe.';
        LEAVE sp;
    END IF;

    -- Validar duplicados: ISSN único
    IF EXISTS (SELECT 1 FROM revista WHERE ISSN = p_ISSN) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ISSN ya existe.';
        LEAVE sp;
    END IF;

    -- Iniciar transacción
    START TRANSACTION;

    -- Insertar en la tabla material
    INSERT INTO material (tipo_material_id, titulo, año_publicacion, editorial, ubicacion_fisica, estado, cantidad_total, cantidad_disponible, descripcion, fecha_adquisicion, codigo_barras, idioma)
    VALUES (p_tipo_material_id, p_titulo, p_año_publicacion, p_editorial, p_ubicacion_fisica, p_estado, p_cantidad_total, p_cantidad_disponible, p_descripcion, p_fecha_adquisicion, p_codigo_barras, p_idioma);

    SET p_material_id = LAST_INSERT_ID();

    -- Insertar en la tabla revista
    INSERT INTO revista (material_id, ISSN, volumen, numero, fecha_publicacion, periodicidad, tema_principal)
    VALUES (p_material_id, p_ISSN, p_volumen, p_numero, p_fecha_publicacion, p_periodicidad, p_tema_principal);

    -- Confirmar transacción
    COMMIT;

    -- Configurar valores de éxito
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listar_cds` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_cds`(
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        SET p_mensaje_error = 'Ocurrió un error al ejecutar el procedimiento.';
    END;

    -- Verificar si hay datos en las tablas
    IF NOT EXISTS (SELECT 1 FROM material m JOIN cd c ON m.material_id = c.material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'No se encontraron registros de CDs.';
        LEAVE sp;
    END IF;

    -- Listar todos los CDs
    SELECT m.*, c.*
    FROM material m
    JOIN cd c ON m.material_id = c.material_id;

    -- Si todo es exitoso
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listar_otros_documentos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_otros_documentos`(
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        SET p_mensaje_error = 'Ocurrió un error al ejecutar el procedimiento.';
    END;

    -- Verificar si hay datos en las tablas
    IF NOT EXISTS (SELECT 1 FROM material m JOIN otrosdocumentos od ON m.material_id = od.material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'No se encontraron registros de otros documentos.';
        LEAVE sp;
    END IF;

    -- Listar todos los otros documentos
    SELECT 
        m.material_id,
        m.tipo_material_id,
        m.titulo,
        m.año_publicacion,
        m.editorial,
        m.ubicacion_fisica,
        m.estado,
        m.cantidad_total,
        m.cantidad_disponible,
        m.descripcion,
        m.fecha_adquisicion,
        m.codigo_barras,
        m.idioma,
        od.tipo_documento,
        od.organizacion_entidad,
        od.numero_serie,
        od.formato,
        od.dimensiones,
        od.requisitos_especiales
    FROM material m
    JOIN otrosdocumentos od ON m.material_id = od.material_id;

    -- Si todo es exitoso
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listar_revistas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_revistas`(
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        SET p_mensaje_error = 'Ocurrió un error al ejecutar el procedimiento.';
    END;

    -- Verificar si hay datos en las tablas
    IF NOT EXISTS (SELECT 1 FROM material m JOIN revista r ON m.material_id = r.material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'No se encontraron registros de revistas.';
        LEAVE sp;
    END IF;

    -- Listar todas las revistas
    SELECT m.*, r.*
    FROM material m
    JOIN revista r ON m.material_id = r.material_id;

    -- Si todo es exitoso
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_obtener_cd_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtener_cd_por_id`(
    IN p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
    END;

    -- Validación de entrada
    IF p_material_id IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no puede ser NULL.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en material
    IF NOT EXISTS (SELECT 1 FROM material WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El material con el ID proporcionado no existe.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en cd
    IF NOT EXISTS (SELECT 1 FROM cd WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El CD con el ID proporcionado no existe.';
        LEAVE sp;
    END IF;

    -- Recuperar información de material y cd
    SELECT 
        m.material_id,
        m.tipo_material_id,
        m.titulo,
        m.año_publicacion,
        m.editorial,
        m.autor,
        m.ubicacion_fisica,
        m.estado,
        m.cantidad_total,
        m.cantidad_disponible,
        m.descripcion,
        m.fecha_adquisicion,
        m.codigo_barras,
        m.idioma,
        c.formato,
        c.duracion,
        c.contenido,
        c.numero_pistas,
        c.requisitos_minimos
    FROM material m
    JOIN cd c ON m.material_id = c.material_id
    WHERE m.material_id = p_material_id;

    -- Si todo es exitoso
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_obtener_otros_documentos_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtener_otros_documentos_por_id`(
    IN p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
    END;

    -- Validación de entrada
    IF p_material_id IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no puede ser NULL.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en material
    IF NOT EXISTS (SELECT 1 FROM material WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El material con el ID proporcionado no existe.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en otrosdocumentos
    IF NOT EXISTS (SELECT 1 FROM otrosdocumentos WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El documento con el ID proporcionado no existe en la tabla otrosdocumentos.';
        LEAVE sp;
    END IF;

    -- Recuperar información de material y otrosdocumentos
    SELECT 
        m.material_id,
        m.tipo_material_id,
        m.titulo,
        m.año_publicacion,
        m.editorial,
        m.ubicacion_fisica,
        m.estado,
        m.cantidad_total,
        m.cantidad_disponible,
        m.descripcion,
        m.fecha_adquisicion,
        m.codigo_barras,
        m.idioma,
        od.tipo_documento,
        od.organizacion_entidad,
        od.numero_serie,
        od.formato,
        od.dimensiones,
        od.requisitos_especiales
    FROM material m
    JOIN otrosdocumentos od ON m.material_id = od.material_id
    WHERE m.material_id = p_material_id;

    -- Si todo es exitoso
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_obtener_revista_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtener_revista_por_id`(
    IN p_material_id INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
sp: BEGIN
    -- Manejador de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SET p_error = 1;
        GET DIAGNOSTICS CONDITION 1 p_mensaje_error = MESSAGE_TEXT;
    END;

    -- Validación de entrada
    IF p_material_id IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El ID del material no puede ser NULL.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en material
    IF NOT EXISTS (SELECT 1 FROM material WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'El material con el ID proporcionado no existe.';
        LEAVE sp;
    END IF;

    -- Validación de existencia en revista
    IF NOT EXISTS (SELECT 1 FROM revista WHERE material_id = p_material_id) THEN
        SET p_error = 1;
        SET p_mensaje_error = 'La revista con el ID proporcionado no existe.';
        LEAVE sp;
    END IF;

    -- Recuperar información de material y revista
    SELECT 
        m.material_id,
        m.tipo_material_id,
        m.titulo,
        m.año_publicacion,
        m.editorial,
        m.ubicacion_fisica,
        m.estado,
        m.cantidad_total,
        m.cantidad_disponible,
        m.descripcion,
        m.fecha_adquisicion,
        m.codigo_barras,
        m.idioma,
        r.ISSN,
        r.volumen,
        r.numero,
        r.fecha_publicacion,
        r.periodicidad,
        r.tema_principal
    FROM material m
    JOIN revista r ON m.material_id = r.material_id
    WHERE m.material_id = p_material_id;

    -- Si todo es exitoso
    SET p_error = 0;
    SET p_mensaje_error = NULL;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_obtener_tipo_material_id_por_material_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtener_tipo_material_id_por_material_id`(
    IN p_materialId INT,
    OUT p_tipoMaterialId INT,
    OUT p_error INT,
    OUT p_mensaje_error VARCHAR(255)
)
BEGIN
    -- Declarar una variable local para almacenar el tipoMaterialId
    DECLARE v_tipoMaterialId INT;

    -- Manejador de excepciones para capturar errores SQL
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de errores
        SET p_error = 1;
        SET p_mensaje_error = 'Error al ejecutar el procedimiento.';
        -- Opcionalmente, puedes obtener más detalles del error si lo deseas
    END;

    -- Inicializar variables de salida
    SET p_error = 0;
    SET p_mensaje_error = '';
    SET p_tipoMaterialId = NULL; -- Inicializar el parámetro de salida

    -- Intentar obtener el tipoMaterialId
    SELECT tipo_material_id INTO v_tipoMaterialId
    FROM material
    WHERE material_id = p_materialId
    LIMIT 1;

    -- Verificar si se obtuvo un resultado
    IF v_tipoMaterialId IS NULL THEN
        SET p_error = 1;
        SET p_mensaje_error = 'No se encontró el tipoMaterialId para el materialId proporcionado.';
    ELSE
        -- Asignar el valor obtenido al parámetro de salida
        SET p_tipoMaterialId = v_tipoMaterialId;
        SET p_error = 0;
        SET p_mensaje_error = 'Operación exitosa.';
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_VerLibros` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_VerLibros`(
)
BEGIN
	SELECT DISTINCT libro.ISBN, libro.numero_edicion, material.titulo, material.descripcion, material.año_publicacion, libro.numero_paginas, material.editorial, material.autor, libro.formato, libro.dimensiones, material.ubicacion_fisica, material.codigo_barras, material.idioma, material.cantidad_disponible 
	FROM libro, material 
	WHERE (libro.material_id = material.material_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_VerObras` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_VerObras`()
BEGIN
	SELECT DISTINCT obra.tipo_obra, obra.genero, obra.formato, obra.dimensiones, material.titulo, material.año_publicacion, material.editorial, material.autor, material.ubicacion_fisica, material.estado, material.cantidad_total, material.cantidad_disponible, material.descripcion, material.fecha_adquisicion, material.codigo_barras, material.idioma
	FROM obra, material
	WHERE (obra.material_id = material.material_id);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_VerTesis` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_VerTesis`()
BEGIN
	SELECT DISTINCT tesis.grado_academico, tesis.institucion, tesis.director, tesis.area_investigacion, tesis.fecha_defensa, tesis.numero_paginas, material.titulo, material.año_publicacion, material.editorial, material.autor, material.ubicacion_fisica, material.estado, material.cantidad_total, material.cantidad_disponible, material.descripcion, material.fecha_adquisicion, material.codigo_barras, material.idioma
	FROM tesis, material
	WHERE (tesis.material_id = material.material_id) ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-07  1:23:30
