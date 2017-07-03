-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.15 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table no1-map.Login
CREATE TABLE IF NOT EXISTS `Login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `attemptDateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(20) DEFAULT NULL,
  `successful` bit(1) NOT NULL,
  `isMaster` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;


-- Dumping structure for table no1-map.Role
CREATE TABLE IF NOT EXISTS `Role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(512) NOT NULL DEFAULT '',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `id` (`id`),
  KEY `name_idx` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table no1-map.Role: ~2 rows (approximately)
DELETE FROM `Role`;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` (`id`, `name`, `createdAt`, `updatedAt`) VALUES
	(1, 'ADMIN', '2014-11-01 00:44:59', '2016-06-30 14:21:36'),
	(2, 'USER', '2014-11-01 00:45:02', '2015-02-28 14:53:43');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;


-- Dumping structure for table no1-map.User
CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(512) NOT NULL DEFAULT '',
  `username` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(60) NOT NULL DEFAULT '',
  `email` varchar(128) NOT NULL DEFAULT '',
  `FK_createdByUserId` int(11) DEFAULT '0',
  `FK_accountNo` int(11) DEFAULT '0',
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enabled` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `id` (`id`),
  KEY `FK_fpl1ssilrrer2jano8u9cs2p2` (`FK_createdByUserId`),
  CONSTRAINT `FK_fpl1ssilrrer2jano8u9cs2p2` FOREIGN KEY (`FK_createdByUserId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table no1-map.User: ~1 rows (approximately)
DELETE FROM `User`;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`id`, `fullName`, `username`, `password`, `email`, `FK_createdByUserId`, `FK_accountNo`, `createdAt`, `updatedAt`, `enabled`) VALUES
	(1, 'TSI Inc', 'tsi', '$2a$10$x.VPuwMoxdrBjtTTbmbDgedCnE5x2BbCo9jXnswupeokvs.GXWBHO', 'admin@tri-nven.com', NULL, 7, '2014-10-14 01:27:50', '2016-01-27 17:30:20', b'1');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;


-- Dumping structure for table no1-map.UserRole
CREATE TABLE IF NOT EXISTS `UserRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `FK_userId` int(11) NOT NULL DEFAULT '0',
  `FK_roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_FK_username` (`FK_userId`),
  KEY `FK_roleId` (`FK_roleId`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table no1-map.UserRole: ~1 rows (approximately)
DELETE FROM `UserRole`;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
INSERT INTO `UserRole` (`id`, `FK_userId`, `FK_roleId`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `UserRole` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
