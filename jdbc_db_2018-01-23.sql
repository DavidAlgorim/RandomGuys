# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.5.42)
# Database: jdbc_db
# Generation Time: 2018-01-23 19:10:59 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id_event` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nazev` varchar(255) NOT NULL DEFAULT '',
  `id_misto` int(11) unsigned NOT NULL,
  `cena` int(11) NOT NULL,
  `zvyhodnena_cena` int(11) NOT NULL,
  `kapacita` int(11) NOT NULL,
  `popis` mediumtext,
  `id_organizator` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_event`),
  KEY `id_misto` (`id_misto`),
  KEY `id_organizator` (`id_organizator`),
  CONSTRAINT `event_ibfk_2` FOREIGN KEY (`id_misto`) REFERENCES `misto` (`id_misto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `event_ibfk_3` FOREIGN KEY (`id_organizator`) REFERENCES `organizator` (`id_organizator`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;

INSERT INTO `event` (`id_event`, `nazev`, `id_misto`, `cena`, `zvyhodnena_cena`, `kapacita`, `popis`, `id_organizator`)
VALUES
	(10,'Rock For People 2018',1,1750,1300,5000,'Krátký popis eventu',1);

/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hodnoceni
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hodnoceni`;

CREATE TABLE `hodnoceni` (
  `id_hodnoceni` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hodnoceni` int(1) NOT NULL,
  `text_hodnoceni` tinytext,
  `id_event` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_hodnoceni`),
  KEY `id_event` (`id_event`),
  CONSTRAINT `hodnoceni_ibfk_1` FOREIGN KEY (`id_event`) REFERENCES `event` (`id_event`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table listek
# ------------------------------------------------------------

DROP TABLE IF EXISTS `listek`;

CREATE TABLE `listek` (
  `id_listek` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `zakaznik` varchar(255) DEFAULT '',
  `zvyhodneni` tinyint(1) NOT NULL,
  `id_event` int(11) unsigned NOT NULL,
  `id_osoba` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_listek`),
  KEY `id_osoba` (`id_osoba`),
  KEY `id_event` (`id_event`),
  CONSTRAINT `listek_ibfk_1` FOREIGN KEY (`id_osoba`) REFERENCES `osoba` (`id_osoba`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `listek_ibfk_2` FOREIGN KEY (`id_event`) REFERENCES `event` (`id_event`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table misto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `misto`;

CREATE TABLE `misto` (
  `id_misto` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nazev` varchar(255) NOT NULL DEFAULT '',
  `adresa` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `telefon` int(11) NOT NULL,
  PRIMARY KEY (`id_misto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `misto` WRITE;
/*!40000 ALTER TABLE `misto` DISABLE KEYS */;

INSERT INTO `misto` (`id_misto`, `nazev`, `adresa`, `email`, `telefon`)
VALUES
	(1,'Festivalpark','Letiště Hradec Králové','aaab@ccc.com',444555666);

/*!40000 ALTER TABLE `misto` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table organizator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `organizator`;

CREATE TABLE `organizator` (
  `id_organizator` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `jmeno` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `telefon` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_organizator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `organizator` WRITE;
/*!40000 ALTER TABLE `organizator` DISABLE KEYS */;

INSERT INTO `organizator` (`id_organizator`, `jmeno`, `email`, `telefon`)
VALUES
	(1,'RFP','rfp@rfp.cz',111222333);

/*!40000 ALTER TABLE `organizator` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table osoba
# ------------------------------------------------------------

DROP TABLE IF EXISTS `osoba`;

CREATE TABLE `osoba` (
  `id_osoba` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `jmeno` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(100) NOT NULL UNIQUE DEFAULT '',
  `pass_hash` varchar(100) NOT NULL DEFAULT '',
  `status` varchar(255) NOT NULL DEFAULT '',
  `body` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_osoba`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;

INSERT INTO `osoba` (`id_osoba`, `jmeno`, `email`, `username`, `pass_hash`, `status`, `body`)
VALUES
	(9,'Jmeno Prijmeni','aaaa@bbb.cc','fghjkl','$2a$10$VdhR9h/aZ76R5ZKAKRpcNuldGp7mCa7I.8wNrLYXMHFXoXlygOsZS','uzivatel',0),
	(11,'Hai Hai','hai@hai.cz','haihai','$2a$10$JQo0NjHBDb4XCTsZEY.4YuS3NIy9juLhBG1kLYrCouW48zOAzHLBq','uzivatel',0),
	(12,'Yay Yuy','yyy@ggg.com','yuyyay','$2a$10$0Y1UXSoneKZbYXoV8wLTV.zc/UsUfy6ZAfaPPJYEhG1mOrTgID1g6','uzivatel',0),
	(13,'Canon Pixma','123@gmail.com','QWRTZ','$2a$10$iBCghap9xii4C6a5R9q4H.qkT/N1IWUXBPsQ3z.QBPFeItnrgjb5i','uzivatel',0),
	(15,'Osoba Osoba','osoba@osoba.com','osoba','$2a$10$CO5Ludphh4zD7b5WbXLrn.7XgWm4d/5Z/tIaZi5FthVp5Nmr/ZolG','uzivatel',0);

/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
