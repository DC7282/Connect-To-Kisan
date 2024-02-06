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
-- Table structure for table `orderplaced`
--

DROP TABLE IF EXISTS `orderplaced`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderplaced` (
  `id` int NOT NULL AUTO_INCREMENT,
  `availability` int NOT NULL,
  `billingid` int DEFAULT NULL,
  `category` int NOT NULL,
  `department` int NOT NULL,
  `mrp` float NOT NULL,
  `productid` int DEFAULT NULL,
  `productimage` varchar(300) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sellingprice` float NOT NULL,
  `timestamp` varchar(30) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `userid` int DEFAULT NULL,
  `deliverystatus` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK58brrlqdxaddq24p70f6qgrem` (`userid`),
  KEY `FK7rr518wicilsonoj7sw1p5gdw` (`productid`),
  KEY `FK9t5dgtm8hb2dohbyhia6ttnch` (`billingid`),
  CONSTRAINT `FK58brrlqdxaddq24p70f6qgrem` FOREIGN KEY (`userid`) REFERENCES `userregisteration` (`uid`),
  CONSTRAINT `FK7rr518wicilsonoj7sw1p5gdw` FOREIGN KEY (`productid`) REFERENCES `product` (`id`),
  CONSTRAINT `FK9t5dgtm8hb2dohbyhia6ttnch` FOREIGN KEY (`billingid`) REFERENCES `billingdetails` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderplaced`
--

LOCK TABLES `orderplaced` WRITE;
/*!40000 ALTER TABLE `orderplaced` DISABLE KEYS */;
INSERT INTO `orderplaced` VALUES (1,1,1,12,4,75,13,'2024-01-09T165859.760743400_Maaza.jpg',1,65,'20-01-2024 17:10.05','Maaza Mango Drink - Original Flavour, Refreshing, 1.2 l Pet Bottle',1,4),(2,1,1,17,5,750,18,'2024-01-09T170953.203977900_cashewkaju.webp',1,365,'26-01-2024 19:24.07','Cashew/Kaju - Whole, 500 g',1,5),(3,1,1,1,1,70,1,'2024-01-09T163214.070007800_tomato.webp',1,55,'22-01-2024 15:52.50','Fresho Tomato - Local 1 kg',1,4),(4,1,2,7,2,250,7,'2024-01-09T164744.845998100_Apple.webp',4,180,'22-01-2024 15:51.29','Fresho Apple - Shimla, Premium, 4 pcs (Approx.720 - 800 g)',2,2),(5,1,2,8,2,110,8,'2024-01-09T164941.355545_strawnerry.webp',1,65,'22-01-2024 15:45.38','Fresho Strawberry, 200 g',2,4),(6,1,3,11,3,170,12,'2024-01-09T165556.944834900_soan_papdi.webp',1,120,'20-01-2024 17:22.05','Haldiram\'s Soan Papdi - Authentic Taste, Crispy & Flaky Texture, Perfect For Gifting, 500 g Pouch',3,1),(7,1,3,10,3,520,10,'2024-01-09T165401.412778800_haldiram-besan-ladoo.webp',1,350,'20-01-2024 17:22.05','Besan Laddoo 1 kg',3,1),(8,1,3,14,4,165,15,'2024-01-09T170240.566901900_apple-juice.webp',1,142,'20-01-2024 17:22.06','Real 100 % Apple Juice 1L',3,1),(12,1,5,2,1,45,2,'2024-01-09T163452.436491_Potato.jpeg',7,28,'26-01-2024 19:23.36','Fresho Potato - Fresh Crop',1,5),(13,1,6,9,3,999,22,'2024-01-23T190636.864790_chitale-kaju-katli.jpg',1,720,'29-01-2024 20:49.48','Kaju Katli (No Added Sugar) from Chitale Bandhu – Pune',1,5),(14,1,6,8,2,110,8,'2024-01-09T164941.355545_strawnerry.webp',1,65,'29-01-2024 20:50.00','Fresho Strawberry, 200 g',1,5),(15,2,6,6,2,70,6,'2024-01-09T164616.537013400_Bannana.jpg',1,50,'29-01-2024 20:50.11','Fresho Premium Banana - Robusta, 1 kg',1,5),(16,2,7,6,2,70,6,'2024-01-09T164616.537013400_Bannana.jpg',1,50,'29-01-2024 20:55.58','Fresho Premium Banana - Robusta, 1 kg',1,5),(17,1,7,8,2,110,8,'2024-01-09T164941.355545_strawnerry.webp',1,65,'29-01-2024 20:55.42','Fresho Strawberry, 200 g',1,5),(18,1,7,9,3,999,22,'2024-01-23T190636.864790_chitale-kaju-katli.jpg',1,720,'29-01-2024 20:55.30','Kaju Katli (No Added Sugar) from Chitale Bandhu – Pune',1,5),(19,1,8,8,2,110,8,'2024-01-09T164941.355545_strawnerry.webp',1,65,'29-01-2024 20:53.36','Fresho Strawberry, 200 g',1,1),(20,1,8,9,3,999,22,'2024-01-23T190636.864790_chitale-kaju-katli.jpg',1,720,'29-01-2024 20:53.36','Kaju Katli (No Added Sugar) from Chitale Bandhu – Pune',1,1),(21,1,9,2,1,45,2,'2024-01-09T163452.436491_Potato.jpeg',1,28,'04-02-2024 01:36.24','Fresho Potato - Fresh Crop',4,3),(22,1,10,13,4,160,14,'2024-01-09T170037.946088900_orange-juice.webp',1,140,'04-02-2024 19:29.20','Tropicana 100% Orange Juice, 1 L',1,1),(23,1,10,10,3,520,10,'2024-01-09T165401.412778800_haldiram-besan-ladoo.webp',1,350,'04-02-2024 19:29.20','Besan Laddoo 1 kg',1,1);
/*!40000 ALTER TABLE `orderplaced` ENABLE KEYS */;
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
