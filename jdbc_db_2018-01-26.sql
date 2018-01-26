# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.5.42)
# Database: jdbc_db
# Generation Time: 2018-01-26 16:12:50 +0000
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;

INSERT INTO `event` (`id_event`, `nazev`, `id_misto`, `cena`, `zvyhodnena_cena`, `kapacita`, `popis`, `id_organizator`)
VALUES
	(10,'Rock For People 2018',1,1750,1300,5000,'Krátký popis eventu',1),
	(12,'jhkj',1,1999,1999,9992,'gsdjhfkjl	',4),
	(13,'Nejaky event',1,1999,1999,9999,'Nejaky popis',5);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

LOCK TABLES `hodnoceni` WRITE;
/*!40000 ALTER TABLE `hodnoceni` DISABLE KEYS */;

INSERT INTO `hodnoceni` (`id_hodnoceni`, `hodnoceni`, `text_hodnoceni`, `id_event`)
VALUES
	(3,3,'Něco něco něco kvalitka',10),
	(4,4,'Kvalita',12),
	(5,5,'Kvalita',13);

/*!40000 ALTER TABLE `hodnoceni` ENABLE KEYS */;
UNLOCK TABLES;


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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

LOCK TABLES `listek` WRITE;
/*!40000 ALTER TABLE `listek` DISABLE KEYS */;

INSERT INTO `listek` (`id_listek`, `zakaznik`, `zvyhodneni`, `id_event`, `id_osoba`)
VALUES
	(1,'N?kdo n?kdo',0,10,NULL),
	(2,'N?kdo n?kdo',0,10,NULL),
	(3,'N?kdo n?kdo',0,10,NULL),
	(4,'N?kdo n?kdo',0,10,NULL),
	(5,'N?kdo n?kdo',0,10,NULL),
	(6,'N?kdo n?kdo',0,10,NULL),
	(7,'N?kdo n?kdo',0,10,NULL),
	(8,'N?kdo n?kdo',0,10,NULL),
	(9,'N?kdo n?kdo',0,10,NULL),
	(10,'N?kdo n?kdo',0,10,NULL),
	(11,'sdgasg',1,10,NULL),
	(12,'sdgasg',1,10,NULL),
	(13,'sdgasg',1,10,NULL),
	(14,'',1,10,16),
	(15,'',1,10,16),
	(16,'',1,10,16),
	(17,'',1,10,16),
	(18,'',0,10,16),
	(19,'',1,10,16),
	(20,'',0,10,16);

/*!40000 ALTER TABLE `listek` ENABLE KEYS */;
UNLOCK TABLES;


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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `misto` WRITE;
/*!40000 ALTER TABLE `misto` DISABLE KEYS */;

INSERT INTO `misto` (`id_misto`, `nazev`, `adresa`, `email`, `telefon`)
VALUES
	(1,'Festivalpark','Letiště Hradec Králové','aaab@ccc.com',444555666),
	(2,'N?jaké místo','Adresa','ffff@fffffffff.cc',181829929);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

LOCK TABLES `organizator` WRITE;
/*!40000 ALTER TABLE `organizator` DISABLE KEYS */;

INSERT INTO `organizator` (`id_organizator`, `jmeno`, `email`, `telefon`)
VALUES
	(1,'RFP','rfp@rfp.cz',111222333),
	(2,'Organizator','fff@fff.cc',333222444),
	(3,'Organizator2','ghjk@hjkl.com',666622233),
	(4,'LiveNation','aaa@byyy.cc',777888999),
	(5,'FFF','ffffff@ffffff.ff',891290019);

/*!40000 ALTER TABLE `organizator` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table osoba
# ------------------------------------------------------------

DROP TABLE IF EXISTS `osoba`;

CREATE TABLE `osoba` (
  `id_osoba` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `jmeno` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(100) NOT NULL DEFAULT '',
  `pass_hash` varchar(100) NOT NULL DEFAULT '',
  `status` varchar(255) NOT NULL DEFAULT '',
  `body` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_osoba`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;

INSERT INTO `osoba` (`id_osoba`, `jmeno`, `email`, `username`, `pass_hash`, `status`, `body`)
VALUES
	(9,'Jmeno Prijmeni','aaaa@bbb.cc','fghjkl','$2a$10$VdhR9h/aZ76R5ZKAKRpcNuldGp7mCa7I.8wNrLYXMHFXoXlygOsZS','uzivatel',0),
	(11,'Hai Hai','hai@hai.cz','haihai','$2a$10$JQo0NjHBDb4XCTsZEY.4YuS3NIy9juLhBG1kLYrCouW48zOAzHLBq','uzivatel',0),
	(12,'Yay Yuy','yyy@ggg.com','yuyyay','$2a$10$0Y1UXSoneKZbYXoV8wLTV.zc/UsUfy6ZAfaPPJYEhG1mOrTgID1g6','uzivatel',0),
	(13,'Canon Pixma','123@gmail.com','QWRTZ','$2a$10$iBCghap9xii4C6a5R9q4H.qkT/N1IWUXBPsQ3z.QBPFeItnrgjb5i','uzivatel',0),
	(15,'Osoba Osoba','osoba@osoba.com','osoba','$2a$10$CO5Ludphh4zD7b5WbXLrn.7XgWm4d/5Z/tIaZi5FthVp5Nmr/ZolG','uzivatel',0),
	(16,'Prvni Druhy','aksfisad@mcm','prvni','$2a$10$fYcuYs8ohfr7./F4ptTEA.5pG/TtCQWlsIFQnhnncvxenYbhROZZu','uzivatel',0),
	(17,'Druhy Treti','kkk@mvoa.com','druhy','$2a$10$ger.zG7J/.HxdZ8lHcZZMeENGg1sjxiXsBUQo83MQPIAJuGB/V/Ja','spravce',0),
	(18,'Treti Ctvrty','ctvrt@cmm.cm','treti','$2a$10$D8vuxEfzb7tRAZvDuBkyjuD5bp2TM15tYJBAWucU4YtlVES4rUnny','admin',0),
	(20,'neco neco','neco@neco.cz','neco','$2a$10$Op2h9lCyujjYQJHzcYrJ0u/MStHnuZX7AUQOkai8A9G7WN4yIhfHy','uzivatel',0);

/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
