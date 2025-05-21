-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: student_management
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curs`
--

DROP TABLE IF EXISTS `curs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descriere` varchar(255) DEFAULT NULL,
  `titlu` varchar(255) DEFAULT NULL,
  `profesor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78ihah7ncca5ou9hcf6vxl929` (`profesor_id`),
  CONSTRAINT `FK78ihah7ncca5ou9hcf6vxl929` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curs`
--

LOCK TABLES `curs` WRITE;
/*!40000 ALTER TABLE `curs` DISABLE KEYS */;
INSERT INTO `curs` VALUES (1,'Matematica','Matematica',2);
/*!40000 ALTER TABLE `curs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_notare` date DEFAULT NULL,
  `valoare` double DEFAULT NULL,
  `curs_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb49dfwkytcjrcue59vlxsfglk` (`curs_id`),
  KEY `FKhfuxqhkjq0mw5iygqre4hry2n` (`student_id`),
  CONSTRAINT `FKb49dfwkytcjrcue59vlxsfglk` FOREIGN KEY (`curs_id`) REFERENCES `curs` (`id`),
  CONSTRAINT `FKhfuxqhkjq0mw5iygqre4hry2n` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (1,'2025-05-15',3.11,1,1),(2,'2025-05-15',5,1,3),(3,'2025-05-15',1,1,5),(4,'2025-05-16',5,1,4);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nume` varchar(255) DEFAULT NULL,
  `parola` varchar(255) DEFAULT NULL,
  `rol` varchar(50) DEFAULT 'ROLE_PROFESOR',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (2,'maria.ionescu@example.com','Maria Ionescu','$2b$12$UBv2eEiIpqD4NTioIN7E0OG04NjlB99hf/xbCzS4jWPevGB6SmwCm','ROLE_PROFESOR'),(3,'admin','Administrator','$2a$10$JGK0edt2CZ86Sqy0JPYeieWU6gaxShpLZtUXlCCOq2ncTz8zDv1JS','ROLE_PROFESOR'),(4,'danielporojan280@gmail.com','Porojan Daniel','$2a$10$S7iKZPnthM1bXFrpqE21tuFChMTP4kUqTc76ZGuaMmqI0kSKZ.2tC','ROLE_PROFESOR'),(5,'tanase@ceva.com','Tanase Andrei','$2a$10$mU8hE.IHcC9OdDCOjkt6T.rcFOHRDRu9o0SL9CNEO2tEMOV94uD96','ROLE_PROFESOR');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor_student`
--

DROP TABLE IF EXISTS `profesor_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor_student` (
  `profesor_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`profesor_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `profesor_student_ibfk_1` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`),
  CONSTRAINT `profesor_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor_student`
--

LOCK TABLES `profesor_student` WRITE;
/*!40000 ALTER TABLE `profesor_student` DISABLE KEYS */;
INSERT INTO `profesor_student` VALUES (2,1),(4,1),(2,3),(2,4),(2,5);
/*!40000 ALTER TABLE `profesor_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `grupa` varchar(255) DEFAULT NULL,
  `nume` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'marius-daniel.porojan@s.unibuc.ro','123','Porojan Daniel'),(3,'maria.ionescu@example.com','123','Alina Eremia'),(4,'maria.ionescu@example.com','123','Alina Eremia'),(5,'gigi@email.com','123','gigi');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_curs`
--

DROP TABLE IF EXISTS `student_curs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_curs` (
  `student_id` bigint NOT NULL,
  `curs_id` bigint NOT NULL,
  KEY `FKmev3lhr84ypn0nm6c2j32vpn6` (`curs_id`),
  KEY `FKq8h40d3n7pt7nnrxbc3gi6rmw` (`student_id`),
  CONSTRAINT `FKmev3lhr84ypn0nm6c2j32vpn6` FOREIGN KEY (`curs_id`) REFERENCES `curs` (`id`),
  CONSTRAINT `FKq8h40d3n7pt7nnrxbc3gi6rmw` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_curs`
--

LOCK TABLES `student_curs` WRITE;
/*!40000 ALTER TABLE `student_curs` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_curs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-16  9:07:54
