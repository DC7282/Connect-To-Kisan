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
-- Table structure for table `billingdetails`
--

DROP TABLE IF EXISTS `billingdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billingdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `deliverystatus` int DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `pastcode` int DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `timestamp` varchar(30) DEFAULT NULL,
  `totalbillingamount` float DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `landmark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgmp8c0maimosrg5m6j4bsg3e3` (`userid`),
  KEY `FKh3mdisq9bkyjj4yten6ce3imb` (`deliverystatus`),
  CONSTRAINT `FKgmp8c0maimosrg5m6j4bsg3e3` FOREIGN KEY (`userid`) REFERENCES `userregisteration` (`uid`),
  CONSTRAINT `FKh3mdisq9bkyjj4yten6ce3imb` FOREIGN KEY (`deliverystatus`) REFERENCES `orderstatus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billingdetails`
--

LOCK TABLES `billingdetails` WRITE;
/*!40000 ALTER TABLE `billingdetails` DISABLE KEYS */;
INSERT INTO `billingdetails` VALUES (1,'HOME NO. 4-34-367 MATOSHRI NAGAR','AURANGABAD',4,'dhirajchaudhary989@gmail.com','Dhiraj','Chaudhary',431005,'9762537999','Maharashtra','26-01-2024 19:24.08',169,1,'Hanuman nagar'),(2,'Matoshree Nagar','Aurangabad',2,'somnarhmalode96@gmail.com','Somnath','Malode',431005,'9637669555','Maharashtra','22-01-2024 15:51.29',785,2,'Hanuman nagar'),(3,'Hinjewadi','Pune',1,'amolsathe9560@gmailcom','Amol','Sathe',411057,'8412861835','Maharashtra','22-01-2024 15:21.01',612,3,'Hanuman nagar'),(5,'HOME NO. 4-34-367 MATOSHRI NAGAR','AURANGABAD',5,'dhirajchaudhary989@gmail.com','Dhiraj','Chaudhary',431005,'+919762537999','Maharashtra','26-01-2024 19:23.36',0,1,'water tank'),(6,'HOME NO. 4-34-367 MATOSHRI NAGAR','AURANGABAD',5,'dhirajchaudhary989@gmail.com','niraj','Chaudhary',431005,'9762537999','Maharashtra','29-01-2024 20:50.12',0,1,'water tank'),(7,'HOME NO. 4-34-367 MATOSHRI NAGAR','AURANGABAD',5,'nirajchaudhary989@gmail.com','Niraj','Chaudhary',431005,'9762537999','Maharashtra','29-01-2024 20:55.58',0,1,'water tank'),(8,'HOME NO. 4-34-367 MATOSHRI NAGAR','AURANGABAD',1,'santoshchaudhary989@gmail.com','Santosh','Chaudhary',431005,'9762537912','Maharashtra','29-01-2024 20:53.34',785,1,'water tank'),(9,'ZXcZXcZXcZXc','SDs',3,'adf@gan.com','ZxcZ','ZxcZXc',13413,'12341245345','asdfsdf','04-02-2024 01:36.24',77,4,'ZXcZxcZXc'),(10,'HOME NO. 4-34-367 MATOSHRI NAGAR','AURANGABAD',1,'dhirajchaudhary7282@gmail.com','Dhiraj','Chaudhary',431005,'9762537999','Maharashtra','04-02-2024 19:29.19',490,1,'water tank');
/*!40000 ALTER TABLE `billingdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 16:10:13
