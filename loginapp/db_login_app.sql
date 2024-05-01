-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_login_app
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
-- Table structure for table `tb_m_user`
--

DROP TABLE IF EXISTS `tb_m_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_m_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `name` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oi49w8hsvcq0i5m93rsppgndk` (`email`),
  UNIQUE KEY `UK_2h453rk9pw2wvj7vjo0i6lvlr` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_m_user`
--

LOCK TABLES `tb_m_user` WRITE;
/*!40000 ALTER TABLE `tb_m_user` DISABLE KEYS */;
INSERT INTO `tb_m_user` VALUES (1,'felixsavero@gmail.com',1,'Felix Savero','$2a$10$s3t9nXdCx9eJE6JUhkm1AekUUF3gUOOgmW1l2jClMLw5ZU2.P8nTG','felixsavero');
/*!40000 ALTER TABLE `tb_m_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tr_audit_trail`
--

DROP TABLE IF EXISTS `tb_tr_audit_trail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tr_audit_trail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` enum('CREATE','UPDATE','DELETE') NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `information` varchar(255) NOT NULL,
  `route` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiojghupsfx3xmgxfwfw06laxo` (`user_id`),
  CONSTRAINT `FKiojghupsfx3xmgxfwfw06laxo` FOREIGN KEY (`user_id`) REFERENCES `tb_m_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tr_audit_trail`
--

LOCK TABLES `tb_tr_audit_trail` WRITE;
/*!40000 ALTER TABLE `tb_tr_audit_trail` DISABLE KEYS */;
INSERT INTO `tb_tr_audit_trail` VALUES (1,'CREATE','2024-05-01 17:31:22.284066','User created account.','/auth/register',1),(2,'CREATE','2024-05-01 17:31:24.689905','User logged in.','/auth/login',1);
/*!40000 ALTER TABLE `tb_tr_audit_trail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-01 17:32:16
