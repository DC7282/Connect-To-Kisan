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
-- Table structure for table `productandmainpagesectionjoin`
--

DROP TABLE IF EXISTS `productandmainpagesectionjoin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productandmainpagesectionjoin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mainpagesections_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `timestamp` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm5yxop53c9k23n5cd5dnk0yfw` (`product_id`),
  CONSTRAINT `FKm5yxop53c9k23n5cd5dnk0yfw` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productandmainpagesectionjoin`
--

LOCK TABLES `productandmainpagesectionjoin` WRITE;
/*!40000 ALTER TABLE `productandmainpagesectionjoin` DISABLE KEYS */;
INSERT INTO `productandmainpagesectionjoin` VALUES (1,2,1,NULL),(2,2,7,NULL),(3,2,8,NULL),(4,2,13,NULL),(5,2,2,NULL),(6,2,18,NULL),(7,2,9,NULL),(8,2,6,NULL),(9,1,15,'17-01-2024 11:21.16'),(10,1,10,'17-01-2024 11:22.13'),(11,1,14,'17-01-2024 11:22.34'),(12,1,16,'17-01-2024 11:22.45'),(13,1,17,'17-01-2024 11:23.14'),(14,4,11,'29-01-2024 15:05.43'),(15,4,12,'29-01-2024 15:05.58'),(16,4,19,'29-01-2024 15:06.47'),(17,5,3,'29-01-2024 15:08.07'),(18,5,21,'29-01-2024 15:08.50'),(19,5,20,'29-01-2024 15:10.25');
/*!40000 ALTER TABLE `productandmainpagesectionjoin` ENABLE KEYS */;
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
