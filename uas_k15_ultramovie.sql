-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2022 at 10:40 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uas_k15_ultramovie`
--
CREATE DATABASE IF NOT EXISTS `uas_k15_ultramovie` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `uas_k15_ultramovie`;

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `kd_genre` int(11) NOT NULL,
  `nama_genre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `genre`
--

TRUNCATE TABLE `genre`;
-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id_movie` int(11) NOT NULL,
  `nm_movie` varchar(100) NOT NULL,
  `director` varchar(100) NOT NULL,
  `stars` varchar(100) NOT NULL,
  `sinopsis` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `movie`
--

TRUNCATE TABLE `movie`;
-- --------------------------------------------------------

--
-- Table structure for table `movie_has_genre`
--

DROP TABLE IF EXISTS `movie_has_genre`;
CREATE TABLE `movie_has_genre` (
  `id_movie` int(11) NOT NULL,
  `kd_genre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `movie_has_genre`
--

TRUNCATE TABLE `movie_has_genre`;
-- --------------------------------------------------------

--
-- Table structure for table `movie_has_writers`
--

DROP TABLE IF EXISTS `movie_has_writers`;
CREATE TABLE `movie_has_writers` (
  `id_movie` int(11) NOT NULL,
  `kd_writer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `movie_has_writers`
--

TRUNCATE TABLE `movie_has_writers`;
-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(64) NOT NULL,
  `password` varchar(160) NOT NULL,
  `level` enum('member','admin') NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` enum('pria','wanita') NOT NULL,
  `asal_negara` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `user`
--

TRUNCATE TABLE `user`;
-- --------------------------------------------------------

--
-- Table structure for table `writers`
--

DROP TABLE IF EXISTS `writers`;
CREATE TABLE `writers` (
  `kd_writer` int(11) NOT NULL,
  `nm_writer` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `writers`
--

TRUNCATE TABLE `writers`;
--
-- Indexes for dumped tables
--

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`kd_genre`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id_movie`);

--
-- Indexes for table `movie_has_genre`
--
ALTER TABLE `movie_has_genre`
  ADD PRIMARY KEY (`id_movie`,`kd_genre`),
  ADD KEY `genre` (`kd_genre`);

--
-- Indexes for table `movie_has_writers`
--
ALTER TABLE `movie_has_writers`
  ADD PRIMARY KEY (`id_movie`,`kd_writer`),
  ADD KEY `writer` (`kd_writer`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `writers`
--
ALTER TABLE `writers`
  ADD PRIMARY KEY (`kd_writer`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `kd_genre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id_movie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `writers`
--
ALTER TABLE `writers`
  MODIFY `kd_writer` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie_has_genre`
--
ALTER TABLE `movie_has_genre`
  ADD CONSTRAINT `genre` FOREIGN KEY (`kd_genre`) REFERENCES `genre` (`kd_genre`) ON UPDATE CASCADE,
  ADD CONSTRAINT `movies` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON UPDATE CASCADE;

--
-- Constraints for table `movie_has_writers`
--
ALTER TABLE `movie_has_writers`
  ADD CONSTRAINT `movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON UPDATE CASCADE,
  ADD CONSTRAINT `writer` FOREIGN KEY (`kd_writer`) REFERENCES `writers` (`kd_writer`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
