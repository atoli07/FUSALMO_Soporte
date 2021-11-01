-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-10-2021 a las 02:07:42
-- Versión del servidor: 8.0.21
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `soportefusalmo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

DROP TABLE IF EXISTS `area`;
CREATE TABLE IF NOT EXISTS `area` (
  `Id` char(6) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `IdJefeAsignado` char(6) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `fk_area` (`IdJefeAsignado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`Id`, `Nombre`, `IdJefeAsignado`, `isDeleted`) VALUES
('AR0001', 'Contabilidad', 'JF0001', 0),
('AR0002', 'Socio Laboral', 'JF0001', 0),
('AR0003', 'Innovación', 'JF0002', 0),
('AR0004', 'CAIF', 'JF0002', 0),
('AR0005', 'Deportes', 'JF0003', 0),
('AR0006', 'MINED 1', 'JF0003', 0),
('AR0007', 'MINED 2: Escuela Abierta para la Convivencia', 'JF0003', 0),
('AR0008', 'Administración', 'JF0004', 0),
('AR0009', 'RRHH', 'JF0001', 0),
('AR0010', 'Gestión y Recaudación', 'JF0001', 0),
('AR0011', 'Servicios Generales', 'JF0001', 0),
('AR0012', 'Proyecto GIZ', 'JF0002', 0),
('AR0013', 'Comunicaciones', 'JF0002', 0),
('AR0014', 'Gerencia Educativa', 'JF0003', 0),
('AR0015', 'Red de Juventudes', 'JF0003', 0),
('AR0016', 'FUSALMO Santa Ana', 'JF0004', 0),
('AR0017', 'FUSALMO San Miguel', 'JF0001', 0),
('AR0018', 'FUSALMO Centro Cultural', 'JF0002', 0),
('AR0019', 'CAIF Santa Ana', 'JF0003', 0),
('AR0020', 'Innovación San Miguel', 'JF0004', 0),
('AR0021', 'Innovación Santa Ana', 'JF0004', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `Id` char(6) NOT NULL,
  `Nombres` varchar(100) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `TelefonoFijo` varchar(9) DEFAULT NULL,
  `Genero` char(1) NOT NULL,
  `DUI` varchar(10) NOT NULL,
  `Cargo` varchar(100) NOT NULL,
  `IdAreaAsignada` char(6) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contra` varchar(20) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `ck_area_asignada_empleado` (`IdAreaAsignada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`Id`, `Nombres`, `Apellidos`, `FechaNacimiento`, `TelefonoFijo`, `Genero`, `DUI`, `Cargo`, `IdAreaAsignada`, `Correo`, `Contra`, `isDeleted`) VALUES
('EM0001', 'Ricardo Alberto', 'Morales Rosas', '2021-06-11', '72189654', 'M', '8596584184', 'Asistente técnico', 'AR0002', 'ricardo.morales@subdominio.dominio', '456', 0),
('EM1234', 'Juan', 'Frank', '2021-09-16', '49823788', 'M', '0194839902', 'Educador de tecnología', 'AR0001', 'juan.frank@subdominio.dominio', '123456', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadotoken`
--

DROP TABLE IF EXISTS `estadotoken`;
CREATE TABLE IF NOT EXISTS `estadotoken` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(20) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estadotoken`
--

INSERT INTO `estadotoken` (`Id`, `Nombre`, `Descripcion`) VALUES
(1, 'Enviado', 'Espere al personal que llegará a su estación de trabajo'),
(2, 'En Atencion', 'Su token está siendo procesado actualmente'),
(3, 'No Procede', 'Su token no ha sido aceptado, comuníquese con soporte para obtener más detalles'),
(4, 'Exitoso/Finalizado', 'Se ha finalizado con éxito su petición');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jefeasignado`
--

DROP TABLE IF EXISTS `jefeasignado`;
CREATE TABLE IF NOT EXISTS `jefeasignado` (
  `Id` char(6) NOT NULL,
  `Nombres` varchar(100) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `jefeasignado`
--

INSERT INTO `jefeasignado` (`Id`, `Nombres`, `Apellidos`, `isDeleted`) VALUES
('JF0001', 'Juan Antonio', 'Perez Gomez', 0),
('JF0002', 'Alberto Ramos', 'Pérez Rosas', 0),
('JF0003', 'Marta Flores', 'Castro Villareal', 0),
('JF0004', 'Marco Antonio', 'Gómez Peraza', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimientos`
--

DROP TABLE IF EXISTS `mantenimientos`;
CREATE TABLE IF NOT EXISTS `mantenimientos` (
  `Id` char(6) NOT NULL,
  `IdRecurso` char(6) NOT NULL,
  `Descripcion` text,
  `FechaRealizacion` date NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `ck_mantenimientos_recursos` (`IdRecurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `memos`
--

DROP TABLE IF EXISTS `memos`;
CREATE TABLE IF NOT EXISTS `memos` (
  `Id` char(6) NOT NULL,
  `IdAgenteIT` char(6) NOT NULL,
  `IdEmpleado` char(6) NOT NULL,
  `Asunto` text NOT NULL,
  `IdTipo` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PDF` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `PDFFirmado` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `FechaDevolucion` date DEFAULT NULL,
  `FechaEntrega` date DEFAULT NULL,
  `CantidadRecursos` int DEFAULT '0',
  `Para` varchar(10000) NOT NULL,
  `De` varchar(10000) NOT NULL,
  `Descripcion` text NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `ck_agente_IT` (`IdAgenteIT`),
  KEY `ck_empleados` (`IdEmpleado`),
  KEY `fk_IdTipo` (`IdTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `memos`
--

INSERT INTO `memos` (`Id`, `IdAgenteIT`, `IdEmpleado`, `Asunto`, `IdTipo`, `PDF`, `PDFFirmado`, `FechaDevolucion`, `FechaEntrega`, `CantidadRecursos`, `Para`, `De`, `Descripcion`, `isDeleted`) VALUES
('ME0001', 'IT0001', 'EM1234', 'cualquiera', 'ASI', NULL, NULL, NULL, NULL, 0, '', '', '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamorecursos`
--

DROP TABLE IF EXISTS `prestamorecursos`;
CREATE TABLE IF NOT EXISTS `prestamorecursos` (
  `IdPrestamo` int NOT NULL AUTO_INCREMENT,
  `IdMemo` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IdRecurso` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IdEmpleado` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`IdPrestamo`),
  KEY `fk_IdMemo` (`IdMemo`),
  KEY `fk_IdRecurso` (`IdRecurso`),
  KEY `fk_IdEmpleado` (`IdEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `prestamorecursos`
--

INSERT INTO `prestamorecursos` (`IdPrestamo`, `IdMemo`, `IdRecurso`, `IdEmpleado`) VALUES
(1, 'ME0001', 'R00001', 'EM1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recursos`
--

DROP TABLE IF EXISTS `recursos`;
CREATE TABLE IF NOT EXISTS `recursos` (
  `Id` char(6) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Marca` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Modelo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumSerie` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `IdTipoRecurso` int NOT NULL,
  `DireccionIP` varchar(15) DEFAULT NULL,
  `DireccionMAC` varchar(17) DEFAULT NULL,
  `Cargador` tinyint(1) DEFAULT NULL,
  `CodActivo` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IdAreaAsignada` char(6) NOT NULL,
  `Imagen` mediumtext NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `ck_tipos` (`IdTipoRecurso`),
  KEY `ck_area_asignada` (`IdAreaAsignada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `recursos`
--

INSERT INTO `recursos` (`Id`, `Nombre`, `Marca`, `Modelo`, `NumSerie`, `IdTipoRecurso`, `DireccionIP`, `DireccionMAC`, `Cargador`, `CodActivo`, `IdAreaAsignada`, `Imagen`, `isDeleted`) VALUES
('R00001', 'DESKTOP-SD6RO5M', 'HP', '14-ck0013la', '5CG8182FC6', 1, '172.19.8.151', '80:C5:F2:A2:57:C3', 1, 'FS0-ST-EI-0049', 'AR0001', 'https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c06596763.png', 0),
('R00002', 'OFFICE 365', NULL, NULL, NULL, 2, NULL, NULL, NULL, 'FSO-51-MO-003', 'AR0001', 'https://www.r2tecnio.com/wp-content/uploads/2021/01/365MS.jpg', 0),
('R00003', 'Laptop 15', 'HP', 'HP Heavy 2021', 'LFOJI935235', 1, '192.168.1.1', '12:54:66:99:12:33', 0, 'FSO-ST-EI-0018', 'AR0019', 'https://siman.vtexassets.com/arquivos/ids/1012612/102961247_2.jpg?v=637431413600630000', 0),
('R00004', 'DELL', 'HP', 'HP Heavy 2021', 'LFOJI935235', 4, '192.168.1.1', '12:54:66:99:12:33', NULL, 'FSO-ST-EI-0018', 'AR0004', 'https://http2.mlstatic.com/D_NQ_NP_731895-MPE45788894850_052021-O.jpg', 0),
('R00005', 'Teclado USB', 'Enhance', 'GTX-865', 'BFUASB8214', 3, NULL, NULL, NULL, 'FSO-ST-EI-0001', 'AR0003', 'https://poindoin.com/wp-content/uploads/2021/03/22630_pictures_product_pictorial_1.png', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recursosdeempleados`
--

DROP TABLE IF EXISTS `recursosdeempleados`;
CREATE TABLE IF NOT EXISTS `recursosdeempleados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idRecurso` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idEmpleado` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ck_recursos_empleados1` (`idRecurso`),
  KEY `ck_recursos_empleados2` (`idEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `recursosdeempleados`
--

INSERT INTO `recursosdeempleados` (`id`, `idRecurso`, `idEmpleado`) VALUES
(1, 'R00001', 'EM1234'),
(2, 'R00002', 'EM1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomemo`
--

DROP TABLE IF EXISTS `tipomemo`;
CREATE TABLE IF NOT EXISTS `tipomemo` (
  `IdTipo` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`IdTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipomemo`
--

INSERT INTO `tipomemo` (`IdTipo`, `Nombre`) VALUES
('ASI', 'Asignación'),
('EXT', 'Prestamo externo'),
('INT', 'Prestamo interno'),
('REM', 'Remoción');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiporecurso`
--

DROP TABLE IF EXISTS `tiporecurso`;
CREATE TABLE IF NOT EXISTS `tiporecurso` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` text NOT NULL,
  `img` varchar(10000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tiporecurso`
--

INSERT INTO `tiporecurso` (`Id`, `Nombre`, `Descripcion`, `img`) VALUES
(1, 'Laptop ', 'Computadora portátil de peso y tamaño ligero.', 'https://www.abasteo.mx/out/pictures/master/category/icon/laptops-home2.png'),
(2, 'Producto Microsoft Enterprise ', 'Producto oficial de Microsoft 365.', 'https://cdn.codecoda.com/img/ms-enterprise.png'),
(3, 'Accesorios', 'Accesorios electrónicos con el cual pueden ser acompañados otros tipos de recursos electrónicos en la fundación.', 'https://static.kemikcdn.com/2019/10/Personalcomputers-L-ARCOBALENO-Palermo-006-300x300.jpg'),
(4, 'PC Desktop', 'Computadora de escritorio de peso liviano, mediano y pesado.', 'https://s3.us-east-2.amazonaws.com/ccp-prd-s3-uploads/2021/4/28/f5febb708cb1ee1f5dca4f194184a463ded32af2.jpeg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tokens`
--

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE IF NOT EXISTS `tokens` (
  `Id` char(6) NOT NULL,
  `IdEmpleado` char(6) NOT NULL,
  `SeleccionRecurso` char(6) NOT NULL,
  `Descripcion` text NOT NULL,
  `IdEstado` int NOT NULL,
  `Fecha` date NOT NULL,
  `Prioridad` varchar(100) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `ck_estados` (`IdEstado`),
  KEY `ck_empleado_token` (`IdEmpleado`),
  KEY `ck_recurso_token` (`SeleccionRecurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tokens`
--

INSERT INTO `tokens` (`Id`, `IdEmpleado`, `SeleccionRecurso`, `Descripcion`, `IdEstado`, `Fecha`, `Prioridad`, `isDeleted`) VALUES
('TK0001', 'EM1234', 'R00001', 'Mi laptop agarró fuego', 1, '2021-10-05', 'Sumamente importante así el edificio no agarra fuego también', 0),
('TK0003', 'EM1234', 'R00001', 'No quiere encender', 1, '2021-10-11', 'Media', 0),
('TK0004', 'EM1234', 'R00002', 'No puedo iniciar sesión', 3, '2021-10-11', 'Baja', 0),
('TK0005', 'EM1234', 'R00001', 'No quiere encender', 4, '2021-10-19', 'Alta', 0),
('TK0006', 'EM1234', 'R00002', 'Inicia sesión pero no puede guardar ningún archivo', 1, '2021-10-25', 'Alta', 0),
('TK0007', 'EM1234', 'R00002', 'Inicia sesión pero no puede guardar ningún archivo', 1, '2021-10-25', 'Alta', 0),
('TK0008', 'EM1234', 'R00001', 'No quiere funcionar el cargador', 2, '2021-10-25', 'Alta', 0),
('TK0009', 'EM1234', 'R00001', 'Parpadea la pantalla y se apaga sola', 1, '2021-10-25', 'Alta', 0),
('TK0010', 'EM1234', 'R00002', 'Se sale de la cuenta por sí solo', 1, '2021-10-25', 'Alta', 0),
('TK0011', 'EM1234', 'R00001', 'Se apaga después de 10 min', 1, '2021-10-26', 'Alta', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariosit`
--

DROP TABLE IF EXISTS `usuariosit`;
CREATE TABLE IF NOT EXISTS `usuariosit` (
  `Id` char(6) NOT NULL,
  `Nombres` varchar(100) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Genero` char(1) NOT NULL,
  `DUI` varchar(10) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contra` varchar(20) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuariosit`
--

INSERT INTO `usuariosit` (`Id`, `Nombres`, `Apellidos`, `FechaNacimiento`, `Genero`, `DUI`, `Correo`, `Contra`, `isDeleted`) VALUES
('IT0001', 'Luis', 'Erazo', '2021-09-28', 'M', '9210494022', 'saijfij21@gmail.com', '123', 0),
('IT0002', 'Pedro', 'Elmono', '2021-10-03', 'M', '8596584184', 'pedro.elm@subdominio.dominio', '995511', 0);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `area`
--
ALTER TABLE `area`
  ADD CONSTRAINT `fk_area` FOREIGN KEY (`IdJefeAsignado`) REFERENCES `jefeasignado` (`Id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `ck_area_asignada_empleado` FOREIGN KEY (`IdAreaAsignada`) REFERENCES `area` (`Id`);

--
-- Filtros para la tabla `mantenimientos`
--
ALTER TABLE `mantenimientos`
  ADD CONSTRAINT `ck_mantenimientos_recursos` FOREIGN KEY (`IdRecurso`) REFERENCES `recursos` (`Id`);

--
-- Filtros para la tabla `memos`
--
ALTER TABLE `memos`
  ADD CONSTRAINT `ck_agente_IT` FOREIGN KEY (`IdAgenteIT`) REFERENCES `usuariosit` (`Id`),
  ADD CONSTRAINT `ck_empleados` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`Id`),
  ADD CONSTRAINT `fk_IdTipo` FOREIGN KEY (`IdTipo`) REFERENCES `tipomemo` (`IdTipo`);

--
-- Filtros para la tabla `prestamorecursos`
--
ALTER TABLE `prestamorecursos`
  ADD CONSTRAINT `fk_IdEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`Id`),
  ADD CONSTRAINT `fk_IdMemo` FOREIGN KEY (`IdMemo`) REFERENCES `memos` (`Id`),
  ADD CONSTRAINT `fk_IdRecurso` FOREIGN KEY (`IdRecurso`) REFERENCES `recursos` (`Id`);

--
-- Filtros para la tabla `recursos`
--
ALTER TABLE `recursos`
  ADD CONSTRAINT `ck_area_asignada` FOREIGN KEY (`IdAreaAsignada`) REFERENCES `area` (`Id`),
  ADD CONSTRAINT `ck_tipos` FOREIGN KEY (`IdTipoRecurso`) REFERENCES `tiporecurso` (`Id`);

--
-- Filtros para la tabla `recursosdeempleados`
--
ALTER TABLE `recursosdeempleados`
  ADD CONSTRAINT `ck_recursos_empleados1` FOREIGN KEY (`idRecurso`) REFERENCES `recursos` (`Id`),
  ADD CONSTRAINT `ck_recursos_empleados2` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`Id`);

--
-- Filtros para la tabla `tokens`
--
ALTER TABLE `tokens`
  ADD CONSTRAINT `ck_empleado_token` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`Id`),
  ADD CONSTRAINT `ck_estados` FOREIGN KEY (`IdEstado`) REFERENCES `estadotoken` (`Id`),
  ADD CONSTRAINT `ck_recurso_token` FOREIGN KEY (`SeleccionRecurso`) REFERENCES `recursos` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
