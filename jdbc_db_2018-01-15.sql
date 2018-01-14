# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.5.42)
# Database: jdbc_db
# Generation Time: 2018-01-14 23:45:37 +0000
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
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nazev` varchar(255) NOT NULL DEFAULT '',
  `organizator` int(11) NOT NULL,
  `misto_id` int(11) NOT NULL,
  `cena` int(11) NOT NULL,
  `zvyhodnena_cena` int(11) NOT NULL,
  `kapacita` int(11) NOT NULL,
  `popis` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table hodnoceni
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hodnoceni`;

CREATE TABLE `hodnoceni` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hodnoceni` int(1) NOT NULL,
  `text_hodnoceni` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table listek
# ------------------------------------------------------------

DROP TABLE IF EXISTS `listek`;

CREATE TABLE `listek` (
  `osoba_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `zakaznik` varchar(255) DEFAULT NULL,
  `zvyhodneni` tinyint(1) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table misto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `misto`;

CREATE TABLE `misto` (
  `misto_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nazev` varchar(255) NOT NULL DEFAULT '',
  `adresa` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `telefon` int(11) NOT NULL,
  PRIMARY KEY (`misto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table osoba
# ------------------------------------------------------------

DROP TABLE IF EXISTS `osoba`;

CREATE TABLE `osoba` (
  `osoba_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `jmeno` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`osoba_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
