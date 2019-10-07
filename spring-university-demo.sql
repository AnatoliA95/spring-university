DROP SCHEMA IF EXISTS `spring-university-demo`;

CREATE SCHEMA `spring-university-demo`;

USE `spring-university-demo`;

DROP TABLE IF EXISTS `user`;

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
`id` int(11) NOT NULL auto_increment,
`name` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `authority` (name) 
VALUES 
('ROLE_STUDENT'),('ROLE_TEACHER'),('ROLE_UNIVERSITY');

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `user`
 VALUES 
 (1, "admin", "{bcrypt}$2a$10$/EBCyd28ctzOwApNn40dAO2iTg6i5yDOo3bb19u9Uas./5HgEOiUS", "Anatoli", "Atanasov", "atanasovap34@gmail.com"),
(2, "student", "{bcrypt}$2a$10$W4eiEBatQ9vLktA.J7HZy.ApmaFPGrcdKZBKj8lRSQTZqBY3ec0SS", "Maria", "Stoqnova", "mima@gmail.com"),
(3, "teacher", "{bcrypt}$2a$10$W4EjukiJVRI5z4Q4ajAeD.NpfdSPEMth91982ZpdIaHtiGTg/L5ty", "Qna", "Borisova", "qncheto@abv.bg");


DROP TABLE IF EXISTS `users_authorities`;

CREATE TABLE `users_authorities` (
  `user_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`authority_id`),
  
  KEY `FK_AUTHORITY_idx` (`authority_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_AUTHORITY` FOREIGN KEY (`authority_id`) 
  REFERENCES `authority` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users_authorities` VALUES 
(1,3),
(2,1),
(3,2);

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `university`;

CREATE TABLE `university` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(54) DEFAULT NULL,
`country` VARCHAR(54) DEFAULT NULL,
`user_id` int(11) NOT NULL,
PRIMARY KEY (`id`),
KEY `FK_USER_idx` (`user_id`),
CONSTRAINT `FK_USER_UNIVERSITY` 
FOREIGN KEY (`user_id`) 
REFERENCES `user` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `university` VALUES(
1,"Informatics - New Bulgarian University", "Bulgaria", 1);

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(54) DEFAULT NULL,
`budget` int(11) NOT NULL,
`university_id` int(11) NOT NULL,
PRIMARY KEY (`id`),
KEY `FK_UNIVERSITY_idx` (`university_id`),
CONSTRAINT `FK_UNIVERSITY` 
FOREIGN KEY (`university_id`) 
REFERENCES `university` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `department` VALUES (
1, "Informatics", 100000, 1);

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) DEFAULT NULL,
`last_name` VARCHAR(45) DEFAULT NULL,
`email` VARCHAR(45) DEFAULT NULL,
`department_id` int(11) DEFAULT NULL,
`user_id` int(11) DEFAULT NULL,
PRIMARY KEY(`id`),
KEY `FK_DEPARTMENT_idx` (`department_id`),
CONSTRAINT `FK_DEPARTMENT` 
FOREIGN KEY (`department_id`) 
REFERENCES `department` (`id`),
KEY `FK_USER_idx` (`user_id`),
CONSTRAINT `FK_USER_STUDENT` 
FOREIGN KEY (`user_id`) 
REFERENCES `user` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `student` VALUES(
1, "Maria", "Stoqnova", "mima@gmail.com",1,2);

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) DEFAULT NULL,
`last_name` VARCHAR(45) DEFAULT NULL,
`email` VARCHAR(45) DEFAULT NULL,
`department_id` int(11) DEFAULT NULL,
`user_id` int(11) DEFAULT NULL,
PRIMARY KEY(`id`),
KEY `FK_DEPARTMENT_idx` (`department_id`),
CONSTRAINT `FK_DEPARTMENT_TEACHER` 
FOREIGN KEY (`department_id`) 
REFERENCES `department` (`id`),
KEY `FK_USER_idx` (`user_id`),
CONSTRAINT `FK_USER_TEACHER` 
FOREIGN KEY (`user_id`) 
REFERENCES `user` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `teacher` VALUES(
1, "Qna", "Borisova", "qncheto@abv.bg", 1, 3);



DROP TABLE IF EXISTS `course`;

CREATE TABLE `course`(
`id` int(11) NOT NULL AUTO_INCREMENT,
`topic` VARCHAR(255) DEFAULT NULL,
`teacher_id` int(11) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `FK_TEACHER_idx` (`teacher_id`),
CONSTRAINT `FK_TEACHER` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `course` VALUES(
1, "Borisova",  1);

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course`(
`student_id` int (11) NOT NULL,
`course_id` int (11) NOT NULL,
PRIMARY KEY (`student_id`, `course_id`),
KEY `FK_STUDENT_idx` (`student_id`),
CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION,
KEY `FK_COURSE_id` (`course_id`),
CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
ON UPDATE NO ACTION ON DELETE NO ACTION
)ENGINE = InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

INSERT INTO `student_course` VALUES(
1, 1);