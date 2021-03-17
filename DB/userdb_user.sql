-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: userdb
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `bithdate` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKs2ym81xl98n65ndx09xpwxm66` (`role_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,'$2a$10$u1Fp3t5FKVrXEjK8nERYSuE9WfLNUht.VBjLTGKacSXXmjg87onUu',NULL,'admin@localhost.local',1),(2,'Dhaka','1995-07-18','Md','Islam','$2a$10$u0m/gH8c7HA1752sZu/3LegzuxK3sa.xRQBCgEZqfGIHG.SVtd5oW','01881122334','islam@gmail.com',2),(3,'chittagong','1997-08-12','shadman','shihab','$2a$10$V4A2Px6L9yOnfvW3E6HB/OwwBT4bZYPFG7x.NGB5XlkLwfX1u0rMq','01812345678','shadmanshihab@gmail.com',2),(4,'Chittagong','2001-12-11','shafin','shahriar','$2a$10$yynRt3zeQVxgQkwUDAcqaOlVutEOu449CLvN8Q8jgES4VKuUCElOi','01712345678','shafin@gmail.com',2),(5,'Dhaka','1993-05-06','safkat','alam','$2a$10$8GGVblXMa7EDYgVvlGvHJuuL.We2HoPKw9xeWKYCCHu1OlPRQLTmG','01912345678','safkat@yahoo.com',2),(6,'Chittagong','1990-01-01','shafayat','Jamil','$2a$10$Z2xzOTkP26Wa4rPGoJn5JuzQUerYwZXPfJtsI6AxT1l7OBM1gcXi2','01345167891','shafayat@gmail.com',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-18  1:10:41
