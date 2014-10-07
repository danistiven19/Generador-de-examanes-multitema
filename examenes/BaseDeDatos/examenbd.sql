-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-09-2014 a las 23:30:40
-- Versión del servidor: 5.6.20
-- Versión de PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `examenbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE IF NOT EXISTS `area` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`codigo`, `nombre`) VALUES
(1, 'Comprension lectora'),
(2, 'Logica Matematica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE IF NOT EXISTS `autor` (
  `idAutor` varchar(15) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`idAutor`, `nombre`, `telefono`) VALUES
('1', 'Hernando Silva', '1233');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combinacionopcion`
--

CREATE TABLE IF NOT EXISTS `combinacionopcion` (
  `idcombinacion` int(11) NOT NULL,
  `A` int(11) NOT NULL,
  `B` int(11) NOT NULL,
  `C` int(11) NOT NULL,
  `D` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `combinacionopcion`
--

INSERT INTO `combinacionopcion` (`idcombinacion`, `A`, `B`, `C`, `D`) VALUES
(1, 1, 2, 3, 4),
(2, 1, 2, 4, 3),
(3, 1, 3, 2, 4),
(4, 1, 3, 4, 2),
(5, 1, 4, 2, 3),
(6, 1, 4, 3, 2),
(7, 2, 1, 3, 4),
(8, 2, 1, 4, 3),
(9, 2, 3, 1, 4),
(10, 2, 3, 4, 1),
(11, 2, 4, 1, 3),
(12, 2, 4, 3, 1),
(13, 3, 1, 2, 4),
(14, 3, 1, 4, 2),
(15, 3, 2, 1, 4),
(16, 3, 2, 4, 1),
(17, 3, 4, 1, 2),
(18, 3, 4, 2, 1),
(19, 4, 1, 2, 3),
(20, 4, 1, 3, 2),
(21, 4, 2, 1, 3),
(22, 4, 2, 3, 1),
(23, 4, 3, 1, 2),
(24, 4, 3, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credencial_tema`
--

CREATE TABLE IF NOT EXISTS `credencial_tema` (
  `tema` int(11) NOT NULL,
  `credencial` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enunciado`
--

CREATE TABLE IF NOT EXISTS `enunciado` (
  `codigo` int(11) NOT NULL,
  `urlEnunciado` varchar(45) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `autor` varchar(15) NOT NULL,
  `area` int(11) NOT NULL,
  `despuesDeEnunciado` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcionE` varchar(15000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion`
--

CREATE TABLE IF NOT EXISTS `opcion` (
  `idOpcion` int(11) NOT NULL,
  `urlOpcion` varchar(45) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `despuesDeOpcion` int(11) DEFAULT NULL,
  `pregunta` int(11) NOT NULL,
  `descripcionO` varchar(15000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

CREATE TABLE IF NOT EXISTS `pregunta` (
  `idPregunta` int(11) NOT NULL,
  `urlPregunta` varchar(45) NOT NULL,
  `enunciado` int(11) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `obligatoria` varchar(45) DEFAULT NULL,
  `despuesDePregunta` int(11) DEFAULT NULL,
  `tipo` varchar(30) DEFAULT NULL,
  `descripcionP` varchar(15000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta_tema`
--

CREATE TABLE IF NOT EXISTS `pregunta_tema` (
  `f_tema` int(11) NOT NULL,
  `f_pregunta` int(11) NOT NULL,
  `respuesta` int(11) DEFAULT NULL,
  `combinacionOpcion_idcombinacion` int(11) NOT NULL,
  `nroPregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta_tema2`
--

CREATE TABLE IF NOT EXISTS `pregunta_tema2` (
  `f_tema` int(11) NOT NULL,
  `f_pregunta` int(11) NOT NULL,
  `respuesta` int(11) DEFAULT NULL,
  `combinacionOpcion_idcombinacion` int(11) NOT NULL,
  `nroPregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema`
--

CREATE TABLE IF NOT EXISTS `tema` (
  `codigo` int(11) NOT NULL,
  `fechaCreacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
 ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
 ADD PRIMARY KEY (`idAutor`);

--
-- Indices de la tabla `combinacionopcion`
--
ALTER TABLE `combinacionopcion`
 ADD PRIMARY KEY (`idcombinacion`);

--
-- Indices de la tabla `credencial_tema`
--
ALTER TABLE `credencial_tema`
 ADD PRIMARY KEY (`tema`,`credencial`), ADD KEY `fk_temas` (`tema`);

--
-- Indices de la tabla `enunciado`
--
ALTER TABLE `enunciado`
 ADD PRIMARY KEY (`codigo`), ADD KEY `despuesDeEn1` (`despuesDeEnunciado`), ADD KEY `fk_area1` (`area`), ADD KEY `fk_autor1` (`autor`);

--
-- Indices de la tabla `opcion`
--
ALTER TABLE `opcion`
 ADD PRIMARY KEY (`idOpcion`,`pregunta`), ADD KEY `despuesDeOpcion` (`despuesDeOpcion`,`pregunta`), ADD KEY `despuesDeOp` (`pregunta`,`despuesDeOpcion`), ADD KEY `fk_preg` (`pregunta`);

--
-- Indices de la tabla `pregunta`
--
ALTER TABLE `pregunta`
 ADD PRIMARY KEY (`idPregunta`,`enunciado`), ADD KEY `despuesDeP` (`despuesDePregunta`), ADD KEY `fk_enunciado` (`enunciado`);

--
-- Indices de la tabla `pregunta_tema`
--
ALTER TABLE `pregunta_tema`
 ADD PRIMARY KEY (`f_tema`,`f_pregunta`), ADD KEY `fk_tema_idx_1` (`f_tema`), ADD KEY `fk_Pregunta_Tema_combinacionOpcion1_idx` (`combinacionOpcion_idcombinacion`), ADD KEY `fk_pregunta_1_idx` (`f_pregunta`);

--
-- Indices de la tabla `pregunta_tema2`
--
ALTER TABLE `pregunta_tema2`
 ADD KEY `fk_tema_2` (`f_tema`), ADD KEY `fk_pregunta_2` (`f_pregunta`), ADD KEY `fk_Pregunta_Tema_combinacionOpcion2` (`combinacionOpcion_idcombinacion`);

--
-- Indices de la tabla `tema`
--
ALTER TABLE `tema`
 ADD PRIMARY KEY (`codigo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credencial_tema`
--
ALTER TABLE `credencial_tema`
ADD CONSTRAINT `fk_temas` FOREIGN KEY (`tema`) REFERENCES `tema` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `enunciado`
--
ALTER TABLE `enunciado`
ADD CONSTRAINT `despuesDeEnunciado` FOREIGN KEY (`despuesDeEnunciado`) REFERENCES `enunciado` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_area` FOREIGN KEY (`area`) REFERENCES `area` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_autor` FOREIGN KEY (`autor`) REFERENCES `autor` (`idAutor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `opcion`
--
ALTER TABLE `opcion`
ADD CONSTRAINT `despuesDeOpcion` FOREIGN KEY (`despuesDeOpcion`, `pregunta`) REFERENCES `opcion` (`idOpcion`, `pregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_pregunta` FOREIGN KEY (`pregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pregunta`
--
ALTER TABLE `pregunta`
ADD CONSTRAINT `despuesDePregunta` FOREIGN KEY (`despuesDePregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_enunciado` FOREIGN KEY (`enunciado`) REFERENCES `enunciado` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pregunta_tema`
--
ALTER TABLE `pregunta_tema`
ADD CONSTRAINT `fk_Pregunta_Tema_combinacionOpcion1` FOREIGN KEY (`combinacionOpcion_idcombinacion`) REFERENCES `combinacionopcion` (`idcombinacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_pregunta_1` FOREIGN KEY (`f_pregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_tema_1` FOREIGN KEY (`f_tema`) REFERENCES `tema` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pregunta_tema2`
--
ALTER TABLE `pregunta_tema2`
ADD CONSTRAINT `fk_Pregunta_Tema_combinacionOpcion2` FOREIGN KEY (`combinacionOpcion_idcombinacion`) REFERENCES `combinacionopcion` (`idcombinacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_pregunta_2` FOREIGN KEY (`f_pregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_tema_2` FOREIGN KEY (`f_tema`) REFERENCES `tema` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
