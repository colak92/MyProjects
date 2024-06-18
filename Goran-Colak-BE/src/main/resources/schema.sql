CREATE DATABASE  IF NOT EXISTS `gorandb` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `gorandb`;

-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: gorandb
-- ------------------------------------------------------
-- Server version	8.0.20

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities` (
  `id` int NOT NULL,
  `postalcode` int NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `titles`
--

DROP TABLE IF EXISTS `titles`;
CREATE TABLE `titles` (
  `id` int NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `indexnumber` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `indexyear` int NOT NULL,
  `firstname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `lastname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `jmbg` bigint NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `currentyearofstudy` int NOT NULL,
  `city_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indexnumber_UNIQUE` (`indexnumber`),
  UNIQUE KEY `indexyear_UNIQUE` (`indexyear`),
  UNIQUE KEY `jmbg_UNIQUE` (`jmbg`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `FK6f1gnpq4oosj61x2lj3dh7mf8` (`city_id`),
  CONSTRAINT `FK6f1gnpq4oosj61x2lj3dh7mf8` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `noofesp` int NOT NULL,
  `yearofstudy` int NOT NULL,
  `semester` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `professors`
--

DROP TABLE IF EXISTS `professors`;
CREATE TABLE `professors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `lastname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `reelectiondate` datetime NOT NULL,
  `city_id` int DEFAULT NULL,
  `title_id` int NOT NULL,
  `subject_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `FK52kgah9pdcdx5mo1ard78et8e` (`city_id`),
  KEY `FKr6m2veg92hoch6lsy56jri4bk` (`title_id`),
  KEY `FKmn26eutkj2uqis05lim4m8kke` (`subject_id`),
  CONSTRAINT `FK52kgah9pdcdx5mo1ard78et8e` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  CONSTRAINT `FKmn26eutkj2uqis05lim4m8kke` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKr6m2veg92hoch6lsy56jri4bk` FOREIGN KEY (`title_id`) REFERENCES `titles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `examperiods`
--

DROP TABLE IF EXISTS `examperiods`;
CREATE TABLE `examperiods` (
  `id` int NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `examstart` datetime NOT NULL,
  `examend` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
CREATE TABLE `exams` (
  `id` int NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `examdate` datetime NOT NULL,
  `grade` int DEFAULT NULL,
  `passed` tinyint(1) NOT NULL,
  `registered` tinyint(1) NOT NULL,
  `examperiod_id` int NOT NULL,
  `professor_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK55xvdooc3xflh9w5l04lf456r` (`professor_id`),
  KEY `FKopre4n7j7fpxqbtbwpv8ywn1y` (`subject_id`),
  KEY `FKqjkxiyhi22xhkasqbpg32jb0s` (`examperiod_id`),
  KEY `FKg8t292c0nfvmltp1gsudhg78m` (`student_id`),
  CONSTRAINT `FK55xvdooc3xflh9w5l04lf456r` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`id`),
  CONSTRAINT `FKg8t292c0nfvmltp1gsudhg78m` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKopre4n7j7fpxqbtbwpv8ywn1y` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKqjkxiyhi22xhkasqbpg32jb0s` FOREIGN KEY (`examperiod_id`) REFERENCES `examperiods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Table structure for table `examregs`
--

DROP TABLE IF EXISTS `examregs`;
CREATE TABLE `examregs` (
  `id` int NOT NULL,
  `comment` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `exam_id` int DEFAULT NULL,
  `examperiod_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnfluygfhuniipop67994t3yg8` (`exam_id`),
  KEY `FK8judleoc3amy1akon0yvg1iym` (`examperiod_id`),
  KEY `FKeb2ggtx8qrk91webpvky96819` (`student_id`),
  KEY `FK9o84jd47wwcqp4pbpxu76kd2d` (`subject_id`),
  CONSTRAINT `FK8judleoc3amy1akon0yvg1iym` FOREIGN KEY (`examperiod_id`) REFERENCES `examperiods` (`id`),
  CONSTRAINT `FK9o84jd47wwcqp4pbpxu76kd2d` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKeb2ggtx8qrk91webpvky96819` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKnfluygfhuniipop67994t3yg8` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;