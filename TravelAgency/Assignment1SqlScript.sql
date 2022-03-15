-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema travel_agency
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel_agency
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel_agency` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `travel_agency` ;

-- -----------------------------------------------------
-- Table `travel_agency`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(255) NULL DEFAULT NULL,
  `lastName` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travel_agency`.`vacation_destination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`vacation_destination` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `destinationName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travel_agency`.`vacation_package`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`vacation_package` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `availablePlaces` INT NULL DEFAULT NULL,
  `extraDetails` VARCHAR(255) NULL DEFAULT NULL,
  `packageName` VARCHAR(255) NULL DEFAULT NULL,
  `period` INT NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  `status` INT NULL DEFAULT NULL,
  `dest_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKs1j4firf3vrwr9va38r6vpm84` (`dest_id` ASC) VISIBLE,
  CONSTRAINT `FKs1j4firf3vrwr9va38r6vpm84`
    FOREIGN KEY (`dest_id`)
    REFERENCES `travel_agency`.`vacation_destination` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travel_agency`.`bookings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`bookings` (
  `user_id` INT NOT NULL,
  `package_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `package_id`),
  INDEX `FKt8v86hs3lphom7wajpbkhvqvg` (`package_id` ASC) VISIBLE,
  CONSTRAINT `FK65bh1tn1y443fxcah5u36e8fy`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_agency`.`user` (`id`),
  CONSTRAINT `FKt8v86hs3lphom7wajpbkhvqvg`
    FOREIGN KEY (`package_id`)
    REFERENCES `travel_agency`.`vacation_package` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
