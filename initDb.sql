# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: minimetro
# Generation Time: 2019-04-15 09:10:56 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table data
# ------------------------------------------------------------

DROP TABLE IF EXISTS `data`;

CREATE TABLE `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `A101` int(3) DEFAULT NULL,
  `A102` int(20) DEFAULT NULL,
  `A103` int(3) DEFAULT NULL,
  `A201` varchar(30) DEFAULT NULL,
  `A202` int(3) DEFAULT NULL,
  `A203` int(3) DEFAULT NULL,
  `A204` int(3) DEFAULT NULL,
  `A205` int(3) DEFAULT NULL,
  `A206` varchar(30) DEFAULT NULL,
  `A207` varchar(30) DEFAULT NULL,
  `A208` int(3) DEFAULT NULL,
  `A209` int(3) DEFAULT NULL,
  `A210` int(3) DEFAULT NULL,
  `A211` int(3) DEFAULT NULL,
  `A212` varchar(30) DEFAULT NULL,
  `A213` int(3) DEFAULT NULL,
  `A214` int(3) DEFAULT NULL,
  `A215` int(3) DEFAULT NULL,
  `A216` int(3) DEFAULT NULL,
  `P301` int(3) DEFAULT NULL,
  `P302` int(3) DEFAULT NULL,
  `P303` int(3) DEFAULT NULL,
  `P304` int(3) DEFAULT NULL,
  `P305` int(3) DEFAULT NULL,
  `P306` int(3) DEFAULT NULL,
  `P307` int(3) DEFAULT NULL,
  `P308` int(3) DEFAULT NULL,
  `P309` int(3) DEFAULT NULL,
  `A217` varchar(30) DEFAULT NULL,
  `A218` varchar(30) DEFAULT NULL,
  `R401` int(11) DEFAULT NULL,
  `R402` int(11) DEFAULT NULL,
  `R403` int(11) DEFAULT NULL,
  `R404` int(11) DEFAULT NULL,
  `R501` varchar(30) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
