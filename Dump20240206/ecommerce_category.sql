-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(20) DEFAULT NULL,
  `departmentid` int DEFAULT NULL,
  `timestamp` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Tomato',1,'09-01-2024 15:26.53'),(2,'Potato',1,'09-01-2024 15:30.24'),(3,'Green Peas',1,'09-01-2024 15:35.26'),(4,'Brinjal',1,'09-01-2024 15:36.23'),(5,'Onion',1,'09-01-2024 15:36.44'),(6,'Banana',2,'09-01-2024 15:38.35'),(7,'Apple',2,'09-01-2024 15:50.44'),(8,'Strawberry',2,'09-01-2024 15:51.26'),(9,'Kaju Katri',3,'09-01-2024 15:54.45'),(10,'Laddu',3,'09-01-2024 15:56.22'),(11,'Soan Papdi',3,'09-01-2024 15:57.06'),(12,'Maaza',4,'09-01-2024 16:08.32'),(13,'Orange Juice',4,'09-01-2024 16:10.02'),(14,'Apple juice',4,'09-01-2024 16:11.15'),(15,'Mixed Fruit',4,'09-01-2024 16:12.36'),(16,'Almond Badam',5,'09-01-2024 16:14.09'),(17,'Cashwe',5,'09-01-2024 16:15.56'),(18,'Kismis',5,'09-01-2024 16:17.41'),(25,'Mawa Peda',3,'23-01-2024 15:28.09');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 16:10:16
