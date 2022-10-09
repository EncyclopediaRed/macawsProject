# ************************************************************
# Sequel Ace SQL dump
# Version 20035
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: localhost (MySQL 8.0.28)
# Database: macaws
# Generation Time: 2022-10-09 21:58:37 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table customers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `customers`;

CREATE TABLE `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb3;



# Dump of table flight
# ------------------------------------------------------------

DROP TABLE IF EXISTS `flight`;

CREATE TABLE `flight` (
  `flight_id` int NOT NULL,
  `depart_date` date NOT NULL,
  `pilot_id` int NOT NULL,
  `route_id` int NOT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `FK_2` (`route_id`),
  KEY `FK_3` (`pilot_id`),
  CONSTRAINT `FK_2` FOREIGN KEY (`route_id`) REFERENCES `routes` (`route_id`),
  CONSTRAINT `FK_3` FOREIGN KEY (`pilot_id`) REFERENCES `pilots` (`pilot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;

INSERT INTO `flight` (`flight_id`, `depart_date`, `pilot_id`, `route_id`)
VALUES
	(202211121,'2022-11-12',1,1),
	(202211122,'2022-11-12',2,2),
	(202211123,'2022-11-12',3,3),
	(202211124,'2022-11-12',1,4),
	(202211131,'2022-11-13',2,1),
	(202211132,'2022-11-13',3,2),
	(202211133,'2022-11-13',1,3),
	(202211134,'2022-11-13',2,4);

/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pilots
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pilots`;

CREATE TABLE `pilots` (
  `pilot_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`pilot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `pilots` WRITE;
/*!40000 ALTER TABLE `pilots` DISABLE KEYS */;

INSERT INTO `pilots` (`pilot_id`, `name`)
VALUES
	(1,'Pilot 1'),
	(2,'Pilot 2'),
	(3,'Pilot 3');

/*!40000 ALTER TABLE `pilots` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table reservations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reservations`;

CREATE TABLE `reservations` (
  `reservation_id` int NOT NULL,
  `flight_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `FK_2` (`flight_id`),
  KEY `FK_3` (`customer_id`),
  KEY `FK_4` (`seat_id`),
  CONSTRAINT `FK_4` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`),
  CONSTRAINT `FK_5` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `FK_6` FOREIGN KEY (`seat_id`) REFERENCES `seats` (`seat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table routes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `routes`;

CREATE TABLE `routes` (
  `route_id` int NOT NULL AUTO_INCREMENT,
  `origin` char(3) NOT NULL,
  `destination` char(3) NOT NULL,
  `time` char(2) NOT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;

INSERT INTO `routes` (`route_id`, `origin`, `destination`, `time`)
VALUES
	(1,'ROA','PHX','AM'),
	(2,'PHX','ROA','AM'),
	(3,'ROA','PHX','PM'),
	(4,'PHX','ROA','PM');

/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table seats
# ------------------------------------------------------------

DROP TABLE IF EXISTS `seats`;

CREATE TABLE `seats` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `row` int NOT NULL,
  `column` char(1) NOT NULL,
  `section_id` int NOT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FK_2` (`section_id`),
  CONSTRAINT `FK_1` FOREIGN KEY (`section_id`) REFERENCES `sections` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;

INSERT INTO `seats` (`seat_id`, `row`, `column`, `section_id`)
VALUES
	(1,1,'A',1),
	(2,1,'B',1),
	(3,2,'A',1),
	(4,2,'B',1),
	(5,3,'A',2),
	(6,3,'B',2),
	(7,3,'C',2),
	(8,3,'D',2),
	(9,4,'A',2),
	(10,4,'B',2),
	(11,4,'C',2),
	(12,4,'D',2);

/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sections
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sections`;

CREATE TABLE `sections` (
  `section_id` int NOT NULL,
  `section` varchar(255) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `sections` WRITE;
/*!40000 ALTER TABLE `sections` DISABLE KEYS */;

INSERT INTO `sections` (`section_id`, `section`, `price`)
VALUES
	(1,'First',850),
	(2,'Economy',450);

/*!40000 ALTER TABLE `sections` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
