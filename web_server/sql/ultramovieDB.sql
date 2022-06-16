CREATE DATABASE IF NOT EXISTS `ultramoviekelompok15`;

USE `ultramoviekelompok15`;

CREATE TABLE IF NOT EXISTS `genre` (
  `kd_genre` int(11) NOT NULL AUTO_INCREMENT,
  `nama_genre` varchar(64) NOT NULL,
  PRIMARY KEY (`kd_genre`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`kd_genre`, `nama_genre`) VALUES
(2, 'Romance'),
(3, 'Comedy'),
(4, 'Fantasy'),
(15, 'Horror'),
(16, 'Animation'),
(17, 'Japan'),
(63, 'Indonesia'),
(65, 'Korea'),
(66, 'China'),
(69, 'Family'),
(70, 'War'),
(71, 'Strategy'),
(73, 'Drama'),
(74, 'Adventure'),
(76, 'Action'),
(82, 'Thriller');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE IF NOT EXISTS `movie` (
  `id_movie` int(11) NOT NULL AUTO_INCREMENT,
  `nm_movie` varchar(500) NOT NULL,
  `director` varchar(500) NOT NULL,
  `stars` varchar(500) NOT NULL,
  `sinopsis` text NOT NULL,
  PRIMARY KEY (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id_movie`, `nm_movie`, `director`, `stars`, `sinopsis`) VALUES
(22, 'I Want to Eat Your Pancreas', 'Shinichiro Ushijima', 'Mahiro Takasugi, Lynn, Yukiyo Fuiji', 'One day, \"Me\" - a highschooler - found a paperback in the hospital. The \"Disease Coexistence Journal\" was its title. It was a diary that \"Me\"\'s classmate, Sakura Yamauchi, had written in secret. Inside, it was written that due to her pancreatic disease, her days were numbered. And thus, \"Me\" coincidentally went from Just-a-Classmate to a Secret-Knowing-Classmate. It was as if he were being drawn to her, who was his polar opposite. However, the world presented the girl that was already suffering from an illness with an equally cruel reality.'),
(24, 'A Silent Voice: The Movie', 'Naoko Yamada', 'Miyu Irino, Saori Hayami, Aoi Yuki', 'The story revolves around Shôko Nishimiya, a grade school student who has impaired hearing. She transfers into a new school, where she is bullied by her classmates, especially Shôya Ishida. It gets to the point where she transfers to another school and as a result, Shôya is ostracized and bullied himself, with no friends to speak to and no plans for the future. Years later, he epicly sets himself on a path to redemption.'),
(26, 'Dilan 1990', 'Pidi Baiq, Fajar Bustomi', 'Iqbaal Ramadhan, Vanesha Prescillia, Sissy Priscillia', 'Milea (Vanesha Prescilla) met with Dilan (Iqbaal Ramadhan) at a high school in Bandung in 1990, when Milea moved from Jakarta to Bandung. An unusual introduction brings Milea to know the uniqueness of Dilan, smart, kind, romantic. The way Dilan approaches Milea is not the same as other male friends, even Beni (Brandon Salim), Milea\'s boyfriend in Jakarta. The journey of their relationship is not always smooth. Beni, Anhar (Giulio Perangkan), Kang Adi (Refal Hadi) color the journey. Dilan makes Milea believe that she can arrive at the destination safely.'),
(27, 'Spider-Man: No Way Home', 'Jon Watts', 'Tom Holland, Zendaya, Bendict Cumberbatch', 'Peter Parker\'s secret identity is revealed to the entire world. Desperate for help, Peter turns to Doctor Strange to make the world forget that he is Spider-Man. The spell goes horribly wrong and shatters the multiverse, bringing in monstrous villains that could destroy the world.'),
(28, 'Avengers: Endgame', 'Anthony Russo, Joe Russo', 'Robert Downey Jr.,Chris Evans,Mark Ruffalo,Chris Hemsworth,Scarlett Johansson,Jeremy Renner,Don Cheadle,Paul Rudd,Brie Larson,Karen Gillan,Danai Gurira,Benedict Wong,Jon Favreau,Bradley Cooper,Gwyneth Paltrow,Josh Brolin', 'After the devastating events of Avengers: Infinity War (2018), the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos\'s actions and undo the chaos to the universe, no matter what consequences may be in store, and no matter who they face...'),
(29, 'Hotel Transylvania: Transformania', 'Derek Drymon, Jennifer Kluska', 'Andy Samberg,Selena Gomez,Kathryn Hahn,Jim Gaffigan', 'When Van Helsing\'s mysterious invention, the \"Monsterfication Ray\", goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac, stripped of his powers, and an exuberant Johnny, loving life as a monster, must team up and race across the globe to find a cure before it\'s too late, and before they drive each other crazy. With help from Mavis and the hilariously human Drac Pack, the heat is on to find a way to switch themselves back before their transformations become permanent.'),
(31, 'Weathering With You', 'Makoto Shinkai', 'Korato Daigo, Nana Mori', 'Set during a period of exceptionally rainy weather, high school boy Hodako Morishima runs away from his troubled rural home to Tokyo and befriends an orphan girl who can manipulate the weather.');

-- --------------------------------------------------------

--
-- Table structure for table `movie_has_genre`
--

CREATE TABLE IF NOT EXISTS `movie_has_genre` (
  `id_movie` int(11) NOT NULL,
  `kd_genre` int(11) NOT NULL,
  PRIMARY KEY (`id_movie`,`kd_genre`),
  KEY `genre` (`kd_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_has_genre`
--

INSERT INTO `movie_has_genre` (`id_movie`, `kd_genre`) VALUES
(22, 16),
(22, 69),
(22, 73),
(24, 2),
(24, 16),
(24, 73),
(26, 2),
(26, 73),
(27, 4),
(27, 74),
(28, 73),
(28, 74),
(29, 3),
(29, 16),
(29, 74),
(31, 2),
(31, 4);

-- --------------------------------------------------------

--
-- Table structure for table `movie_has_writers`
--

CREATE TABLE IF NOT EXISTS `movie_has_writers` (
  `id_movie` int(11) NOT NULL,
  `kd_writer` int(11) NOT NULL,
  PRIMARY KEY (`id_movie`,`kd_writer`),
  KEY `writer` (`kd_writer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_has_writers`
--

INSERT INTO `movie_has_writers` (`id_movie`, `kd_writer`) VALUES
(22, 16),
(22, 17),
(22, 18),
(24, 20),
(24, 21),
(24, 22),
(26, 23),
(26, 24),
(27, 25),
(27, 26),
(27, 27),
(28, 27),
(28, 28),
(28, 29),
(29, 30),
(29, 31),
(29, 32),
(31, 20);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(64) NOT NULL,
  `password` varchar(160) NOT NULL,
  `level` enum('member','admin') NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` enum('pria','wanita') NOT NULL,
  `asal_negara` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `level`, `nama`, `jenis_kelamin`, `asal_negara`) VALUES
('31190038', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Fendyanto', 'pria', 'Indonesia'),
('31190042', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Johanes Shane', 'pria', 'Indonesia'),
('31190050', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Kosasi', 'pria', 'Germany'),
('31190052', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Michelle', 'wanita', 'Indonesia'),
('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'Fendyanto', 'pria', 'Indonesia'),
('antoniofendy', 'daa5107ee657c8161d198f818f1a50ee', 'member', 'Antonio Fendy', 'pria', 'Indonesia'),
('asdfasd', '63d0cea9d550e495fde1b81310951bd7', 'member', 'asdfasdfas', 'pria', 'Andorra'),
('fsdfsdfdf', '33e78d60bc1f9dcc7291c891e6f069e4', 'member', 'dfasdfsdf', 'wanita', 'Andorra'),
('pria', '07734712c926ea53d1d434d2c355ea48', 'member', 'pria', 'pria', 'Andorra'),
('user', 'ee11cbb19052e40b07aac0ca060c23ee', 'member', 'User', 'pria', 'Indonesia');

-- --------------------------------------------------------

--
-- Table structure for table `writers`
--

CREATE TABLE IF NOT EXISTS `writers` (
  `kd_writer` int(11) NOT NULL AUTO_INCREMENT,
  `nm_writer` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  PRIMARY KEY (`kd_writer`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `writers`
--

INSERT INTO `writers` (`kd_writer`, `nm_writer`, `email`, `telepon`) VALUES
(16, 'Yoru Sumino', 'yorusumino@gmail.com', '123456789'),
(17, 'Shinichiro Ushijima', 'shinichiroushi@gmail.com', '987654321'),
(18, 'Erica Mendez', 'ericamendez@gmail.com', '564231897'),
(20, 'Yoshitoki Oima', 'yoshitokioima@gmail.com', '645132798'),
(21, 'Reiko Yoshida', 'reikoyosh@gmail.com', '7978161652'),
(22, 'Kiyoshi Shigematshu', 'kiyoshishige@gmail.com', '976764813'),
(23, 'Pidi Baiq', 'pidibaiq@gmail.com', '6182846678'),
(24, 'Titien Wattimena', 'titienwattimena@gmail.com', '97876134678'),
(25, 'Chris Mckenna', 'chrismckenna@gmail.com', '7676184056'),
(26, 'Erik Sommers', 'eriksommers@gmail.com', '76673481'),
(27, 'Stan Lee', 'stanlee@gmail.com', '43794994'),
(28, 'Christopher Markus', 'chrismark@gmail.com', '6764848464'),
(29, 'Stephen Mcfeely', 'stepmcfeely@gmail.com', '43780446'),
(30, 'Amos Vernon', 'amosvernon@gmail.com', '467384943'),
(31, 'Nunzio Randazzo', 'nunzioran@gmail.com', '46764894'),
(32, 'Genndy Tartakovsky', 'genndytarta@gmail.com', '764943767');

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
