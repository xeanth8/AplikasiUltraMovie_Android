SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `uas_k15_ultramovie` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `uas_k15_ultramovie`;

CREATE TABLE IF NOT EXISTS `genre` (
  `kd_genre` int(11) NOT NULL AUTO_INCREMENT,
  `nama_genre` varchar(64) NOT NULL,
  PRIMARY KEY (`kd_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `movie` (
  `id_movie` int(11) NOT NULL AUTO_INCREMENT,
  `nm_movie` varchar(100) NOT NULL,
  `kd_writer` int(11) NOT NULL,
  `director` varchar(100) NOT NULL,
  `stars` varchar(100) NOT NULL,
  `sinopsis` varchar(100) NOT NULL,
  PRIMARY KEY (`id_movie`),
  KEY `has_writer` (`kd_writer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(64) NOT NULL,
  `password` varchar(160) NOT NULL,
  `level` enum('member','admin') NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` enum('pria','wanita') NOT NULL,
  `asal_negara` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `writers` (
  `kd_writer` int(11) NOT NULL AUTO_INCREMENT,
  `nm_writer` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  PRIMARY KEY (`kd_writer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `movie`
  ADD CONSTRAINT `has_writer` FOREIGN KEY (`kd_writer`) REFERENCES `writers` (`kd_writer`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
