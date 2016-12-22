-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema student_tool
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema student_tool
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `student_tool` ;
USE `student_tool` ;

-- -----------------------------------------------------
-- Table `student_tool`.`st_department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `department_name` VARCHAR(20) NULL,
  `department_building` VARCHAR(15) NULL,
  `department_description` VARCHAR(100) NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE INDEX `department_id_UNIQUE` (`department_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_field_of_study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_field_of_study` (
  `field_of_study_id` INT NOT NULL AUTO_INCREMENT,
  `field_of_study_name` VARCHAR(100) NULL,
  `department_id` INT NULL,
  PRIMARY KEY (`field_of_study_id`),
  INDEX `idx_field_of_study_department` (`department_id` ASC),
  UNIQUE INDEX `field_of_study_id_UNIQUE` (`field_of_study_id` ASC),
  CONSTRAINT `fk_field_of_study_department`
    FOREIGN KEY (`department_id`)
    REFERENCES `student_tool`.`st_department` (`department_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(15) NULL,
  `group_description` VARCHAR(100) NULL,
  `field_of_study_id` INT NULL,
  PRIMARY KEY (`group_id`),
  INDEX `idx_group_field_of_study` (`field_of_study_id` ASC),
  UNIQUE INDEX `group_id_UNIQUE` (`group_id` ASC),
  CONSTRAINT `fk_group_field_of_study`
    FOREIGN KEY (`field_of_study_id`)
    REFERENCES `student_tool`.`st_field_of_study` (`field_of_study_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `address_city` VARCHAR(100) NULL,
  `address_street` VARCHAR(150) NULL,
  `address_code` VARCHAR(8) NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_specialization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_specialization` (
  `specialization_id` INT NOT NULL AUTO_INCREMENT,
  `specialization_name` VARCHAR(75) NULL,
  `field_of_study_id` INT NOT NULL,
  PRIMARY KEY (`specialization_id`, `field_of_study_id`),
  INDEX `idx_specialization_field_of_study` (`field_of_study_id` ASC),
  UNIQUE INDEX `specialization_id_UNIQUE` (`specialization_id` ASC),
  CONSTRAINT `fk_specialization_field_of_study`
    FOREIGN KEY (`field_of_study_id`)
    REFERENCES `student_tool`.`st_field_of_study` (`field_of_study_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_login` VARCHAR(5) NULL,
  `user_password` VARCHAR(45) NULL,
  `user_email` VARCHAR(45) NULL,
  `user_type` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_student` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `group_id` INT NULL,
  `address_id` INT NULL,
  `field_of_study_id` INT NULL,
  `specialization_id` INT NULL,
  `student_photo` VARCHAR(200) NULL,
  `student_title` VARCHAR(75) NULL,
  `student_firstname` VARCHAR(100) NULL,
  `student_secondname` VARCHAR(100) NULL,
  `student_lastname` VARCHAR(100) NULL,
  `student_pesel` VARCHAR(45) NULL,
  `student_birthdate` VARCHAR(10) NULL,
  `student_phone` VARCHAR(20) NULL,
  `student_album` VARCHAR(5) NULL,
  `student_study_system` VARCHAR(45) NULL,
  `student_current_term` INT NULL,
  `student_form_of_study` VARCHAR(45) NULL,
  `student_term_title` VARCHAR(55) NULL,
  `student_diploma_title` VARCHAR(250) NULL,
  PRIMARY KEY (`student_id`, `user_id`),
  INDEX `idx_student_group` (`group_id` ASC),
  INDEX `idx_student_address` (`address_id` ASC),
  INDEX `idx_student_field_of_study` (`field_of_study_id` ASC),
  INDEX `idx_student_specialization` (`specialization_id` ASC),
  UNIQUE INDEX `student_id_UNIQUE` (`student_id` ASC),
  INDEX `idx_student_user` (`user_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_student_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `student_tool`.`st_group` (`group_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `student_tool`.`st_address` (`address_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_field_of_study`
    FOREIGN KEY (`field_of_study_id`)
    REFERENCES `student_tool`.`st_field_of_study` (`field_of_study_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_specialization`
    FOREIGN KEY (`specialization_id`)
    REFERENCES `student_tool`.`st_specialization` (`specialization_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `student_tool`.`st_user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_institute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_institute` (
  `institute_id` INT NOT NULL AUTO_INCREMENT,
  `institute_name` VARCHAR(20) NULL,
  `institute_description` VARCHAR(100) NULL,
  `department_id` INT NULL,
  PRIMARY KEY (`institute_id`),
  INDEX `idx_institute_department` (`department_id` ASC),
  UNIQUE INDEX `institute_id_UNIQUE` (`institute_id` ASC),
  CONSTRAINT `fk_institute_department`
    FOREIGN KEY (`department_id`)
    REFERENCES `student_tool`.`st_department` (`department_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_teacher` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `address_id` INT NULL,
  `department_id` INT NULL,
  `institute_id` INT NULL,
  `teacher_photo` VARCHAR(200) NULL,
  `teacher_title` VARCHAR(75) NULL,
  `teacher_firstname` VARCHAR(100) NULL,
  `teacher_secondname` VARCHAR(100) NULL,
  `teacher_lastname` VARCHAR(100) NULL,
  `teacher_birthdate` VARCHAR(10) NULL,
  `teacher_phone` VARCHAR(20) NULL,
  `teacher_room` VARCHAR(10) NULL,
  `teacher_website` VARCHAR(100) NULL,
  PRIMARY KEY (`teacher_id`, `user_id`),
  INDEX `idx_teacher_address` (`address_id` ASC),
  INDEX `idx_teacher_institute` (`institute_id` ASC),
  INDEX `idx_teacher_department` (`department_id` ASC),
  UNIQUE INDEX `teacher_id_UNIQUE` (`teacher_id` ASC),
  INDEX `idx_teacher_user` (`user_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_teacher_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `student_tool`.`st_address` (`address_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher_institute`
    FOREIGN KEY (`institute_id`)
    REFERENCES `student_tool`.`st_institute` (`institute_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher_department`
    FOREIGN KEY (`department_id`)
    REFERENCES `student_tool`.`st_department` (`department_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `student_tool`.`st_user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `course_name` VARCHAR(150) NULL,
  `course_ects` INT NULL,
  `course_term` INT NULL,
  `field_of_study_id` INT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE INDEX `course_id_UNIQUE` (`course_id` ASC),
  INDEX `idx_course_field_of_study` (`field_of_study_id` ASC),
  CONSTRAINT `fk_course_field_of_study`
    FOREIGN KEY (`field_of_study_id`)
    REFERENCES `student_tool`.`st_field_of_study` (`field_of_study_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_course_form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_course_form` (
  `course_form_id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `course_form` VARCHAR(15) NULL,
  PRIMARY KEY (`course_form_id`, `course_id`),
  INDEX `idx_course_form_course` (`course_id` ASC),
  UNIQUE INDEX `course_form_id_UNIQUE` (`course_form_id` ASC),
  CONSTRAINT `fk_course_form_course`
    FOREIGN KEY (`course_id`)
    REFERENCES `student_tool`.`st_course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_grade` (
  `grade_id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `teacher_id` INT NULL,
  `course_id` INT NOT NULL,
  `course_form_id` INT NOT NULL,
  `grade_value` FLOAT NULL,
  `grade_type` VARCHAR(30) NULL,
  `grade_description` VARCHAR(150) NULL,
  PRIMARY KEY (`grade_id`, `student_id`, `course_id`, `course_form_id`),
  INDEX `idx_grade_student` (`student_id` ASC),
  INDEX `idx_grade_teacher` (`teacher_id` ASC),
  INDEX `idx_grade_course_form` (`course_form_id` ASC),
  INDEX `idx_grade_course` (`course_id` ASC),
  UNIQUE INDEX `grade_id_UNIQUE` (`grade_id` ASC),
  CONSTRAINT `fk_student_grade`
    FOREIGN KEY (`student_id`)
    REFERENCES `student_tool`.`st_student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher_grade`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `student_tool`.`st_teacher` (`teacher_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_form_grade`
    FOREIGN KEY (`course_form_id`)
    REFERENCES `student_tool`.`st_course_form` (`course_form_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_grade`
    FOREIGN KEY (`course_id`)
    REFERENCES `student_tool`.`st_course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_bank` (
  `bank_id` INT NOT NULL AUTO_INCREMENT,
  `bank_number` VARCHAR(26) NULL,
  `student_id` INT NOT NULL,
  PRIMARY KEY (`bank_id`, `student_id`),
  INDEX `idx_bank_student` (`student_id` ASC),
  UNIQUE INDEX `bank_id_UNIQUE` (`bank_id` ASC),
  CONSTRAINT `fk_bank_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `student_tool`.`st_student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_teachers_courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_teachers_courses` (
  `teacher_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `course_form_id` INT NOT NULL,
  PRIMARY KEY (`teacher_id`, `course_id`, `course_form_id`),
  INDEX `idx_courses_teacher` (`teacher_id` ASC),
  INDEX `idx_courses_course` (`course_id` ASC),
  CONSTRAINT `fk_courses_teacher`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `student_tool`.`st_teacher` (`teacher_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_courses_form`
    FOREIGN KEY (`course_form_id`)
    REFERENCES `student_tool`.`st_course_form` (`course_form_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_courses_course`
    FOREIGN KEY (`course_id`)
    REFERENCES `student_tool`.`st_course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `student_tool`.`st_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_tool`.`st_certificate` (
  `certificate_id` INT NOT NULL AUTO_INCREMENT,
  `certificate_name` VARCHAR(200) NULL,
  `teacher_id` INT NOT NULL,
  PRIMARY KEY (`certificate_id`, `teacher_id`),
  INDEX `idx_certificate_teacher` (`teacher_id` ASC),
  UNIQUE INDEX `certificate_id_UNIQUE` (`certificate_id` ASC),
  CONSTRAINT `fk_certificate_teacher`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `student_tool`.`st_teacher` (`teacher_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
