-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 10, 2022 at 01:21 PM
-- Server version: 10.5.12-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19075684_ultramovie`
--

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `kd_genre` int(11) NOT NULL,
  `nama_genre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`kd_genre`, `nama_genre`) VALUES
(1, 'Action'),
(2, 'Romance'),
(3, 'Comedy'),
(4, 'Fantasy'),
(15, 'Horror'),
(16, 'Animation'),
(17, 'Japan'),
(63, 'Indonesia'),
(64, 'Thailand'),
(65, 'Korea'),
(66, 'China'),
(67, 'Indian'),
(68, 'Sad'),
(69, 'Family'),
(70, 'War'),
(71, 'Strategy'),
(72, 'Strategys');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id_movie` int(11) NOT NULL,
  `nm_movie` varchar(100) NOT NULL,
  `director` varchar(100) NOT NULL,
  `stars` varchar(100) NOT NULL,
  `sinopsis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id_movie`, `nm_movie`, `director`, `stars`, `sinopsis`) VALUES
(1, 'Kung Fu Panda', 'Jack Dorsey', 'Mike, Luiz', 'Abcd'),
(2, 'John Wick', 'Alan Sander', 'Keanu Reeves', 'Xyz'),
(8, 'Hero Reborns', 'Sun Quans', 'Qiang Leis', 'Age of Dynastys');

-- --------------------------------------------------------

--
-- Table structure for table `movie_has_genre`
--

CREATE TABLE `movie_has_genre` (
  `id_movie` int(11) NOT NULL,
  `kd_genre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_has_genre`
--

INSERT INTO `movie_has_genre` (`id_movie`, `kd_genre`) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 4),
(8, 70),
(8, 72);

-- --------------------------------------------------------

--
-- Table structure for table `movie_has_writers`
--

CREATE TABLE `movie_has_writers` (
  `id_movie` int(11) NOT NULL,
  `kd_writer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_has_writers`
--

INSERT INTO `movie_has_writers` (`id_movie`, `kd_writer`) VALUES
(1, 1),
(1, 2),
(2, 4),
(8, 14);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(64) NOT NULL,
  `password` varchar(160) NOT NULL,
  `level` enum('member','admin') NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` enum('pria','wanita') NOT NULL,
  `asal_negara` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `level`, `nama`, `jenis_kelamin`, `asal_negara`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Fendyanto', 'pria', 'Indonesia');

-- --------------------------------------------------------

--
-- Table structure for table `writers`
--

CREATE TABLE `writers` (
  `kd_writer` int(11) NOT NULL,
  `nm_writer` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `writers`
--

INSERT INTO `writers` (`kd_writer`, `nm_writer`, `email`, `telepon`) VALUES
(1, 'Fendyanto', 'fendy@gmail.com', '+628738'),
(2, 'Delima Diamante', 'delima@mail.com', '12345'),
(3, 'Antonius Cahyadi', 'antonius@mail.com', '09876'),
(4, 'Shinomiya Kaguya', 'kaguya@mail.com', '56789'),
(7, 'Edogawa Conann', '', ''),
(8, 'SonHeungMin', '', ''),
(9, 'MengAo', '', ''),
(10, 'ZhugeLiang', '', ''),
(11, 'PangTong', '', ''),
(12, 'ZhugeLiangs', '', ''),
(13, 'PangTongs', '', ''),
(14, 'Zhuge Liangt', '', '');

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
  MODIFY `kd_genre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id_movie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `writers`
--
ALTER TABLE `writers`
  MODIFY `kd_writer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie_has_genre`
--
ALTER TABLE `movie_has_genre`
  ADD CONSTRAINT `genre` FOREIGN KEY (`kd_genre`) REFERENCES `genre` (`kd_genre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `movies` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `movie_has_writers`
--
ALTER TABLE `movie_has_writers`
  ADD CONSTRAINT `movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `writer` FOREIGN KEY (`kd_writer`) REFERENCES `writers` (`kd_writer`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
