CREATE DATABASE testhealthcare;
use testhealthcare;

CREATE TABLE `testhealthcare`.`enrollees` (
  `enrollee_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `birth_date` DATE NOT NULL,
  `activation_status` ENUM("True", "False") NOT NULL,
  `phone_number` VARCHAR(22) NULL,
  PRIMARY KEY (`enrollee_id`));


CREATE TABLE `testhealthcare`.`dependents` (
  `dependent_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `birth_date` DATE NOT NULL,
  `enrollee_id` INT NOT NULL,
   PRIMARY KEY (`dependent_id`),
   FOREIGN KEY (enrollee_id) REFERENCES enrollees(enrollee_id)
  );
  
  
INSERT INTO `testhealthcare`.`enrollees` (`enrollee_id`, `name`, `birth_date`, `activation_status`, `phone_number`) 
VALUES 
('1', 'chris jabbour', '1994-02-22', 'True', '7246661122');
INSERT INTO `testhealthcare`.`dependents` (`dependent_id`, `name`, `birth_date`, `enrollee_id`) 
VALUES 
('1', 'stephen jabbour', '1999-10-25', '1');