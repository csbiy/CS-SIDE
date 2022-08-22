-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
                                             `user_key` INT NOT NULL,
                                             `name` VARCHAR(45) NULL,
    `email` VARCHAR(45) NOT NULL,
    `id` VARCHAR(45) NOT NULL,
    `created_at` TIMESTAMP NULL,
    `del_flag` VARCHAR(5) NULL,
    `created_by` VARCHAR(45) NULL,
    PRIMARY KEY (`user_key`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER_HIS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER_HIS` (
     `user_key` INT NOT NULL AUTO_INCREMENT,
     `name` VARCHAR(45) NULL,
    `id` VARCHAR(45) NULL,
    `created_at` TIMESTAMP NULL,
    `del_flag` VARCHAR(5) NULL,
    `created_by` VARCHAR(45) NULL,
    `updated_at` TIMESTAMP NOT NULL,
    `updated_by` VARCHAR(45) NULL,
    PRIMARY KEY (`user_key`, `updated_at`),
    INDEX `fk_USER_HIS_USER_idx` (`user_key` ASC) VISIBLE,
    CONSTRAINT `fk_USER_HIS_USER`
    FOREIGN KEY (`user_key`)
    REFERENCES `mydb`.`USER` (`user_key`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
