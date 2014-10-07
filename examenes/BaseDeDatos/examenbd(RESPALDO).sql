SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `examenBD` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `examenBD` ;

-- -----------------------------------------------------
-- Table `examenBD`.`Area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Area` (
  `codigo` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `examenBD`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Autor` (
  `idAutor` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`idAutor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `examenBD`.`Enunciado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Enunciado` (
  `codigo` INT NOT NULL,
  `urlEnunciado` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  `orden` INT NULL,
  `autor` VARCHAR(15) NOT NULL,
  `area` INT NOT NULL,
  `despuesDeEnunciado` INT NULL,
  `nombre` VARCHAR(45) NULL,
  `descripcionE` VARCHAR(15000) NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `despuesDeEnunciado`
    FOREIGN KEY (`despuesDeEnunciado`)
    REFERENCES `examenBD`.`Enunciado` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_area`
    FOREIGN KEY (`area`)
    REFERENCES `examenBD`.`Area` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autor`
    FOREIGN KEY (`autor`)
    REFERENCES `examenBD`.`Autor` (`idAutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `despuesDeEn1` ON `examenBD`.`Enunciado` (`despuesDeEnunciado` ASC);

CREATE INDEX `fk_area1` ON `examenBD`.`Enunciado` (`area` ASC);

CREATE INDEX `fk_autor1` ON `examenBD`.`Enunciado` (`autor` ASC);


-- -----------------------------------------------------
-- Table `examenBD`.`Pregunta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Pregunta` (
  `idPregunta` INT NOT NULL,
  `urlPregunta` VARCHAR(45) NOT NULL,
  `enunciado` INT NOT NULL,
  `orden` INT NULL,
  `obligatoria` VARCHAR(45) NULL,
  `despuesDePregunta` INT NULL,
  `tipo` VARCHAR(30) NULL,
  `descripcionP` VARCHAR(15000) NOT NULL,
  PRIMARY KEY (`idPregunta`, `enunciado`),
  CONSTRAINT `despuesDePregunta`
    FOREIGN KEY (`despuesDePregunta`)
    REFERENCES `examenBD`.`Pregunta` (`idPregunta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_enunciado`
    FOREIGN KEY (`enunciado`)
    REFERENCES `examenBD`.`Enunciado` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `despuesDeP` ON `examenBD`.`Pregunta` (`despuesDePregunta` ASC);

CREATE INDEX `fk_enunciado` ON `examenBD`.`Pregunta` (`enunciado` ASC);


-- -----------------------------------------------------
-- Table `examenBD`.`Opcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Opcion` (
  `idOpcion` INT NOT NULL,
  `urlOpcion` VARCHAR(45) NOT NULL,
  `orden` INT NULL,
  `despuesDeOpcion` INT NULL,
  `pregunta` INT NOT NULL,
  `descripcionO` VARCHAR(15000) NOT NULL,
  PRIMARY KEY (`idOpcion`, `pregunta`),
  CONSTRAINT `despuesDeOpcion`
    FOREIGN KEY (`despuesDeOpcion` , `pregunta`)
    REFERENCES `examenBD`.`Opcion` (`idOpcion` , `pregunta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pregunta`
    FOREIGN KEY (`pregunta`)
    REFERENCES `examenBD`.`Pregunta` (`idPregunta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `despuesDeOp` ON `examenBD`.`Opcion` (`pregunta` ASC, `despuesDeOpcion` ASC);

CREATE INDEX `fk_preg` ON `examenBD`.`Opcion` (`pregunta` ASC);


-- -----------------------------------------------------
-- Table `examenBD`.`Tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Tema` (
  `codigo` INT NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `examenBD`.`Pregunta_Tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Pregunta_Tema` (
  `f_tema` INT NOT NULL,
  `f_opcion` INT NOT NULL,
  `f_pregunta` INT NOT NULL,
  `respuesta` CHAR NULL,
  `nroPregunta` INT NOT NULL,
  `letra` CHAR NOT NULL,
  PRIMARY KEY (`f_tema`, `f_opcion`, `f_pregunta`),
  CONSTRAINT `fk_tema_1`
    FOREIGN KEY (`f_tema`)
    REFERENCES `examenBD`.`Tema` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pregunta_1`
    FOREIGN KEY (`f_opcion` , `f_pregunta`)
    REFERENCES `examenBD`.`Opcion` (`idOpcion` , `pregunta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tema_idx_1` ON `examenBD`.`Pregunta_Tema` (`f_tema` ASC);

CREATE INDEX `fk_pregunta_idx_1` ON `examenBD`.`Pregunta_Tema` (`f_pregunta` ASC, `f_opcion` ASC);


-- -----------------------------------------------------
-- Table `examenBD`.`Credencial_Tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `examenBD`.`Credencial_Tema` (
  `tema` INT NOT NULL,
  `credencial` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tema`, `credencial`),
  CONSTRAINT `fk_temas`
    FOREIGN KEY (`tema`)
    REFERENCES `examenBD`.`Tema` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_temas` ON `examenBD`.`Credencial_Tema` (`tema` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
