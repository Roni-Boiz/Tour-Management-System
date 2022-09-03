-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projecttour
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `airport` (
  `aptID` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `guest` (
  `gstID` varchar(10) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `fName` varchar(25) DEFAULT NULL,
  `lName` varchar(25) DEFAULT NULL,
  `mobileNo` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `flightNo` varchar(100) DEFAULT NULL,
  `airport` varchar(50) DEFAULT NULL,
  `arrivedDate` date DEFAULT NULL,
  `arrivedTime` time DEFAULT NULL,
  `departureDate` date DEFAULT NULL,
  `departureTime` time DEFAULT NULL,
  PRIMARY KEY (`gstID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `guide`
--

DROP TABLE IF EXISTS `guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `guide` (
  `gidID` varchar(10) NOT NULL,
  `fName` varchar(20) DEFAULT NULL,
  `lName` varchar(20) DEFAULT NULL,
  `NIC` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `YOE` int(3) DEFAULT NULL,
  `mobileNo` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `vehicle` varchar(50) DEFAULT NULL,
  `guideLicence` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`gidID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel` (
  `htlID` varchar(10) NOT NULL,
  `hName` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `telNo` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `accommodation` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`htlID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `place` (
  `plcID` varchar(10) NOT NULL,
  `plcName` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `distanceFromColombo` int(5) DEFAULT NULL,
  PRIMARY KEY (`plcID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tour` (
  `tourID` varchar(10) NOT NULL,
  `gstID` varchar(10) DEFAULT NULL,
  `gidID` varchar(10) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `pickupOn` varchar(50) DEFAULT NULL,
  `dropOn` varchar(50) DEFAULT NULL,
  `adults` int(2) DEFAULT NULL,
  `children` int(2) DEFAULT NULL,
  `rooms` int(2) DEFAULT NULL,
  `roomType` varchar(50) DEFAULT NULL,
  `mealType` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tourID`),
  KEY `gstID` (`gstID`),
  KEY `gidID` (`gidID`),
  CONSTRAINT `tour_ibfk_1` FOREIGN KEY (`gstID`) REFERENCES `guest` (`gstID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_ibfk_2` FOREIGN KEY (`gidID`) REFERENCES `guide` (`gidID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tourdetail`
--

DROP TABLE IF EXISTS `tourdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tourdetail` (
  `tourID` varchar(10) NOT NULL,
  `visitDate` date NOT NULL,
  `visitPlace` varchar(50) NOT NULL,
  `nights` int(2) DEFAULT NULL,
  `stayHotel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tourID`,`visitDate`,`visitPlace`),
  CONSTRAINT `tourdetail_ibfk_1` FOREIGN KEY (`tourID`) REFERENCES `tour` (`tourID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `userID` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vehicle` (
  `vclID` varchar(10) NOT NULL,
  `vclNum` varchar(10) DEFAULT NULL,
  `category` varchar(10) DEFAULT NULL,
  `YOM` int(4) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pricePer1Km` decimal(5,2) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `ownerName` varchar(50) DEFAULT NULL,
  `ownerMobNum` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`vclID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-03 23:45:52
