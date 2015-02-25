-- MySQL dump 10.13  Distrib 5.6.14, for Win64 (x86_64)
--
-- Host: localhost    Database: examenbd
-- ------------------------------------------------------
-- Server version	5.6.14

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `examenBD` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `examenBD` ;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Comprension lectora'),(2,'Logica Matematica');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `idAutor` varchar(15) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAutor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES ('1','Hernando Silva','1233');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combinacionopcion`
--

DROP TABLE IF EXISTS `combinacionopcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combinacionopcion` (
  `idcombinacion` int(11) NOT NULL,
  `A` int(11) NOT NULL,
  `B` int(11) NOT NULL,
  `C` int(11) NOT NULL,
  `D` int(11) NOT NULL,
  PRIMARY KEY (`idcombinacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combinacionopcion`
--

LOCK TABLES `combinacionopcion` WRITE;
/*!40000 ALTER TABLE `combinacionopcion` DISABLE KEYS */;
INSERT INTO `combinacionopcion` VALUES (1,1,2,3,4),(2,1,2,4,3),(3,1,3,2,4),(4,1,3,4,2),(5,1,4,2,3),(6,1,4,3,2),(7,2,1,3,4),(8,2,1,4,3),(9,2,3,1,4),(10,2,3,4,1),(11,2,4,1,3),(12,2,4,3,1),(13,3,1,2,4),(14,3,1,4,2),(15,3,2,1,4),(16,3,2,4,1),(17,3,4,1,2),(18,3,4,2,1),(19,4,1,2,3),(20,4,1,3,2),(21,4,2,1,3),(22,4,2,3,1),(23,4,3,1,2),(24,4,3,2,1);
/*!40000 ALTER TABLE `combinacionopcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credencial_tema`
--

DROP TABLE IF EXISTS `credencial_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credencial_tema` (
  `tema` int(11) NOT NULL,
  `credencial` varchar(45) NOT NULL,
  PRIMARY KEY (`tema`,`credencial`),
  KEY `fk_temas` (`tema`),
  CONSTRAINT `fk_temas` FOREIGN KEY (`tema`) REFERENCES `tema` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credencial_tema`
--

LOCK TABLES `credencial_tema` WRITE;
/*!40000 ALTER TABLE `credencial_tema` DISABLE KEYS */;
/*!40000 ALTER TABLE `credencial_tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enunciado`
--

DROP TABLE IF EXISTS `enunciado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enunciado` (
  `codigo` int(11) NOT NULL,
  `urlEnunciado` varchar(45) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `autor` varchar(15) NOT NULL,
  `area` int(11) NOT NULL,
  `despuesDeEnunciado` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcionE` varchar(15000) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `despuesDeEn1` (`despuesDeEnunciado`),
  KEY `fk_area1` (`area`),
  KEY `fk_autor1` (`autor`),
  CONSTRAINT `despuesDeEnunciado` FOREIGN KEY (`despuesDeEnunciado`) REFERENCES `enunciado` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_area` FOREIGN KEY (`area`) REFERENCES `area` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_autor` FOREIGN KEY (`autor`) REFERENCES `autor` (`idAutor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `opcion`
--

DROP TABLE IF EXISTS `opcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opcion` (
  `idOpcion` int(11) NOT NULL,
  `urlOpcion` varchar(45) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `despuesDeOpcion` int(11) DEFAULT NULL,
  `pregunta` int(11) NOT NULL,
  `descripcionO` varchar(15000) NOT NULL,
  PRIMARY KEY (`idOpcion`,`pregunta`),
  KEY `despuesDeOpcion` (`despuesDeOpcion`,`pregunta`),
  KEY `despuesDeOp` (`pregunta`,`despuesDeOpcion`),
  KEY `fk_preg` (`pregunta`),
  CONSTRAINT `despuesDeOpcion` FOREIGN KEY (`despuesDeOpcion`, `pregunta`) REFERENCES `opcion` (`idOpcion`, `pregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pregunta` FOREIGN KEY (`pregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta` (
  `idPregunta` int(11) NOT NULL,
  `urlPregunta` varchar(45) NOT NULL,
  `enunciado` int(11) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `obligatoria` varchar(45) DEFAULT NULL,
  `despuesDePregunta` int(11) DEFAULT NULL,
  `tipo` varchar(30) DEFAULT NULL,
  `descripcionP` varchar(15000) NOT NULL,
  PRIMARY KEY (`idPregunta`,`enunciado`),
  KEY `despuesDeP` (`despuesDePregunta`),
  KEY `fk_enunciado` (`enunciado`),
  CONSTRAINT `despuesDePregunta` FOREIGN KEY (`despuesDePregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_enunciado` FOREIGN KEY (`enunciado`) REFERENCES `enunciado` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `pregunta_tema`
--

DROP TABLE IF EXISTS `pregunta_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta_tema` (
  `f_tema` int(11) NOT NULL,
  `f_pregunta` int(11) NOT NULL,
  `respuesta` int(11) DEFAULT NULL,
  `combinacionOpcion_idcombinacion` int(11) NOT NULL,
  `nroPregunta` int(11) NOT NULL,
  PRIMARY KEY (`f_tema`,`f_pregunta`),
  KEY `fk_tema_idx_1` (`f_tema`),
  KEY `fk_Pregunta_Tema_combinacionOpcion1_idx` (`combinacionOpcion_idcombinacion`),
  KEY `fk_pregunta_1_idx` (`f_pregunta`),
  CONSTRAINT `fk_pregunta_1` FOREIGN KEY (`f_pregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pregunta_Tema_combinacionOpcion1` FOREIGN KEY (`combinacionOpcion_idcombinacion`) REFERENCES `combinacionopcion` (`idcombinacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tema_1` FOREIGN KEY (`f_tema`) REFERENCES `tema` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `pregunta_tema2`
--

DROP TABLE IF EXISTS `pregunta_tema2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta_tema2` (
  `f_tema` int(11) NOT NULL,
  `f_pregunta` int(11) NOT NULL,
  `respuesta` int(11) DEFAULT NULL,
  `combinacionOpcion_idcombinacion` int(11) NOT NULL,
  `nroPregunta` int(11) NOT NULL,
  KEY `fk_tema_2` (`f_tema`),
  KEY `fk_pregunta_2` (`f_pregunta`),
  KEY `fk_Pregunta_Tema_combinacionOpcion2` (`combinacionOpcion_idcombinacion`),
  CONSTRAINT `fk_pregunta_2` FOREIGN KEY (`f_pregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pregunta_Tema_combinacionOpcion2` FOREIGN KEY (`combinacionOpcion_idcombinacion`) REFERENCES `combinacionopcion` (`idcombinacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tema_2` FOREIGN KEY (`f_tema`) REFERENCES `tema` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tema`
--

DROP TABLE IF EXISTS `tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema` (
  `codigo` int(11) NOT NULL,
  `fechaCreacion` date NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

