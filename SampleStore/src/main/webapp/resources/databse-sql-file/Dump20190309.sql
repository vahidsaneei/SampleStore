-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: softupdbstore
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `com_id` bigint(20) NOT NULL,
  `comment` varchar(255) CHARACTER SET latin1 NOT NULL,
  `prd_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`com_id`),
  KEY `FKfro76752aptbhmbmqya70hrga` (`id`),
  KEY `FKaqcabu4lb8xgiiyvlttwoio9q` (`prd_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (10);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `total_fee` double NOT NULL,
  `ord_id` bigint(20) NOT NULL,
  `prd_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtp3g6lk96mpl58dvldcc5fgin` (`prd_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_products`
--

DROP TABLE IF EXISTS `orders_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders_products` (
  `Orders_id` bigint(20) NOT NULL,
  `products_prd_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_k849nfuybq1oicb52gxd88brr` (`products_prd_id`),
  KEY `FK53e73qtabsk4donm5rui2ia7` (`Orders_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_products`
--

LOCK TABLES `orders_products` WRITE;
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productfiles`
--

DROP TABLE IF EXISTS `productfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productfiles` (
  `id` bigint(20) NOT NULL,
  `content` longblob NOT NULL,
  `name` varchar(128) NOT NULL,
  `type` varchar(64) NOT NULL,
  `prd_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlof8dsl3fwvpu0ov54pha9ujf` (`prd_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productfiles`
--

LOCK TABLES `productfiles` WRITE;
/*!40000 ALTER TABLE `productfiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `productfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `prd_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET latin1 NOT NULL,
  `company_name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `likes` int(11) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `seller_name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `inser_date` datetime DEFAULT NULL,
  `insert_date` datetime DEFAULT NULL,
  PRIMARY KEY (`prd_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Shoe and Sneakers','NIKE','2019-01-01 00:01:00','casual-running-comfort',NULL,'kafshe piadeh ravi',2,340,100,'mobin store',NULL,NULL),(4,'Accessories','NIKE','2019-01-01 00:01:00','so good bag foe women',NULL,'nicer bag',NULL,120,100,'matin store',NULL,NULL),(5,'Electronics','A4Tech','2019-01-01 00:01:00','laser mouse',NULL,'mouse real nice',NULL,38,100,'AI fun',NULL,NULL),(6,'Electronics','Hoawei','2019-01-01 00:01:00','two camera in behind',NULL,'mobile honor 7X',NULL,800,50,'paradise',NULL,NULL),(35,'Electronics','Hoawei','2018-01-01 00:11:00','dual sim',NULL,'hoawei p samrt',NULL,190,100,'AI fun',NULL,NULL),(36,'Electronics','ASUS','2016-01-09 00:11:00','',NULL,'laptap 15\"',NULL,1400,55,'parsian',NULL,NULL),(37,'Shoe and Sneakers','addidas','2019-01-04 00:04:00','new face and black colored\r\n',NULL,'mountain snicker',NULL,230,100,'jivoory',NULL,NULL),(38,'Home Appliances','midas','2018-01-01 00:10:00','',NULL,'square pan',NULL,300,100,'banoo',NULL,NULL),(39,'Home Appliances','midas','2018-01-01 00:10:00','',NULL,'square pan',NULL,300,100,'banoo',NULL,NULL),(40,'Food','fariman','2019-01-12 00:02:00','',NULL,'suger .5 kilo',NULL,20,200,'ghaem',NULL,NULL),(41,'Food','golestan','2018-01-01 00:10:00','',NULL,'tea .6 kilo',NULL,40,309,'ghaem',NULL,NULL),(42,'Electronics','tsco','2019-01-12 00:02:00','',NULL,'power cable',NULL,20,200,'tsco',NULL,NULL),(43,'Electronics','A4Tech','2019-01-04 00:04:00','',NULL,'laser mouse',NULL,40,100,'tsco',NULL,NULL),(44,'Electronics','A4Tech','2019-01-04 00:04:00','',NULL,'laser mouse',NULL,40,100,'tsco',NULL,NULL),(45,'Electronics','Samsung','2019-01-01 00:01:00','',NULL,'mobile samsung A8+',NULL,600,100,'DG mobi',NULL,NULL),(46,'Home Appliances','negin kashan','2016-01-09 00:11:00','',NULL,'carpet 12meters',NULL,700,30,'azim carpet',NULL,NULL),(47,'Clothes','test company','2019-01-12 00:02:00','',NULL,'test kala',NULL,200,200,'test store',NULL,'2019-03-05 01:51:04'),(48,'Shoe and Sneakers','test company','2018-01-01 00:10:00','',NULL,'test kala',NULL,2300,100,'test store',NULL,'2019-03-05 02:16:21'),(49,'Shoe and Sneakers','test company1','2018-01-01 00:11:00','',NULL,'test kala1',NULL,230,100,'test store1',NULL,'2019-03-05 02:19:07');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `id` bigint(20) NOT NULL,
  `addres` varchar(255) DEFAULT NULL,
  `business_type` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `store_name` varchar(255) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `image` longblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4u7lfj44aivecut94bysvki1k` (`store_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `username` bigint(20) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `UKstlxfukw77ov5w1wo1tm3omca` (`role`,`username`),
  KEY `FKcdp2dxqcsdh6rnh6o64rgtcir` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (3,'ROLE_ADMIN',1),(4,'ROLE_USER',2),(5,'ROLE_USER',3),(6,'ROLE_USER',4),(7,'ROLE_USER',5),(8,'ROLE_USER',6),(9,'ROLE_USER',7),(10,'ROLE_USER',8),(11,'ROLE_USER',9);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `gender` char(1) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `expired` tinyint(1) DEFAULT '1',
  `locked` tinyint(1) DEFAULT '1',
  `credential` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tehran-tehran-17 shahrivar St-sajadieh Av. block 53. no 6',1,'M','$2a$10$mrsjmbA3zlJ5gbHaKBCeuez9yxDnF2qJsBS49FmQluaLeJYIYlR5a','09195587135','vahidsaneei',1,1,1),(2,'tehran',1,'M','$2a$10$mrsjmbA3zlJ5gbHaKBCeuez9yxDnF2qJsBS49FmQluaLeJYIYlR5a','09123434345','hamidlotfi',1,1,1),(3,'kashan',1,'M','$2a$10$2.xmYNejlAl3CcOTBPFAz.ArkbquW.gRcUTgTymL0fLUjZWcADhgy','09134354657','nader2020',1,1,1),(4,'tehran',1,'M','$2a$10$oy.3o/iNg2v85VXFC7LY7ecRC1mmV/s7XFQAfbVoV8f4l.vWF7JTC','09196565654','hadi2019',1,1,1),(5,'shiraz',1,'M','$2a$10$pcOYyPJ5CWkMmMbYfbOWheb5lRuEfb2eLCDdfnkP1mXTviY5E9keK','09199199193','vahid5060',1,1,1),(6,'shiraz',1,'M','$2a$10$mrsjmbA3zlJ5gbHaKBCeuez9yxDnF2qJsBS49FmQluaLeJYIYlR5a','','shamsazizi',1,1,1),(8,'ahvaz',1,'M','$2a$10$Eyf6qulGrgbiPTZTFRZTD.vj8fyWvyPtOWaZfm7LliZbTBre.4YwW','09198787654','hamidtala',1,1,1),(9,'tehran',1,'M','$2a$10$Z6OX8Yzn1ZaJtJjTffhSQeuYDlED9KwOfHP4a99MQgxm5G5cEEByS','09194545456','hashemshargi',1,1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'softupdbstore'
--

--
-- Dumping routines for database 'softupdbstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-09  1:29:24
