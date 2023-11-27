-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Nov 27, 2023 at 07:33 AM
-- Server version: 10.4.28-MariaDB-1:10.4.28+maria~ubu2004-log
-- PHP Version: 8.1.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Table structure for table `mana-accounts`
--

CREATE TABLE `mana-accounts` (
  `id` int(11) NOT NULL,
  `username` varchar(16) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mana-accounts`
--

INSERT INTO `mana-accounts` (`id`, `username`, `first_name`, `last_name`, `dob`, `password`) VALUES
(24, 'legoray2203', 'Ray', 'Mendez', '2003-05-23', 'HelloWorld23!'),
(25, 'john2543', 'John', 'Doe', '1999-05-03', 'ILoveJaneDoe2564');

-- --------------------------------------------------------

--
-- Table structure for table `mana-accounts_seq`
--

CREATE TABLE `mana-accounts_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mana-accounts_seq`
--

INSERT INTO `mana-accounts_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `mana-categories`
--

CREATE TABLE `mana-categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mana-categories`
--

INSERT INTO `mana-categories` (`id`, `category_name`) VALUES
(1, 'Action'),
(2, 'Suspense'),
(3, 'Horror'),
(4, 'Drama'),
(5, 'Comedy'),
(6, 'Documentary'),
(7, 'Fantasy'),
(8, 'Musical'),
(9, 'Mystery'),
(10, 'Romance'),
(11, 'Science Fiction'),
(12, 'Thriller'),
(13, 'Western');

-- --------------------------------------------------------

--
-- Table structure for table `mana-movies`
--

CREATE TABLE `mana-movies` (
  `id` int(11) NOT NULL,
  `movie_name` varchar(255) NOT NULL,
  `movie_plot` varchar(255) DEFAULT NULL,
  `movie_director` varchar(255) NOT NULL,
  `movie_actors` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mana-movies`
--

INSERT INTO `mana-movies` (`id`, `movie_name`, `movie_plot`, `movie_director`, `movie_actors`, `category_id`, `user_id`) VALUES
(4, 'Jaws', 'When a killer shark unleashes chaos on a beach community off Cape Cod, it\'s up to a local sheriff, a marine biologist, and an old seafarer to hunt the beast down.', 'Steven Spielberg', 'Roy Scheider, Robert Shaw, Richard Dreyfuss, Lorraine Gary, Murray Hamilton, Carl Gottlieb, Jeffrey Kramer, Susan Backlinie', 2, 24),
(5, 'Titanic', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 'James Cameron', 'Leonardo DiCaprio, Kate Winslet, Billy Zane, Kathy Bates, Frances Fisher, Gloria Stuart, Bill Paxton, Bernard Hill', 4, 25),
(6, 'Home Alone', 'An eight-year-old troublemaker, mistakenly left home alone, must defend his home against a pair of burglars on Christmas eve.', 'Chris Columbus', 'Macaulay Culkin, Joe Pesci, Daniel Stern, John Heard, Roberts Blossom, Catherine O\'Hara, Angela Goethals, Devin Ratray', 5, 24),
(9, 'Elemental', 'Follows Ember and Wade, in a city where fire-, water-, earth- and air-residents live together.', 'Peter Sohn', 'Leah Lewis, Mamoudou Athie, Ronnie Del Carme, Shila Ommi, Wendi McLendon-Covey, Catherine O\'Hara, Mason Wertheimer, Ronobir Lahiri', 7, 24);

-- --------------------------------------------------------

--
-- Table structure for table `mana-reviews`
--

CREATE TABLE `mana-reviews` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `review_title` varchar(255) NOT NULL,
  `review_text` varchar(255) NOT NULL,
  `review_rating` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mana-reviews`
--

INSERT INTO `mana-reviews` (`id`, `user_id`, `review_title`, `review_text`, `review_rating`, `movie_id`) VALUES
(8, 24, 'Great', 'Very beautiful and cinematic movie with lots of classic scenes. Also extremely sad at times. Absolute 90\'s classic.', 5, 5),
(11, 24, 'Am I the only person who sees this as OVERRATED?', 'As a gory film, this works, but as a GOOD film? Doesn\'t break \'okay.\' And yet, everyone thinks it is a great shark film. (Bruce oughta eat those people.)', 3, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mana-accounts`
--
ALTER TABLE `mana-accounts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mana-categories`
--
ALTER TABLE `mana-categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mana-movies`
--
ALTER TABLE `mana-movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mana-movies_mana-categories_id_fk` (`category_id`),
  ADD KEY `mana-movies_mana-accounts_id_fk` (`user_id`);

--
-- Indexes for table `mana-reviews`
--
ALTER TABLE `mana-reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mana-reviews_mana-accounts_id_fk` (`user_id`),
  ADD KEY `mana-reviews_mana-movies_id_fk` (`movie_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mana-accounts`
--
ALTER TABLE `mana-accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `mana-categories`
--
ALTER TABLE `mana-categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `mana-movies`
--
ALTER TABLE `mana-movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `mana-reviews`
--
ALTER TABLE `mana-reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mana-movies`
--
ALTER TABLE `mana-movies`
  ADD CONSTRAINT `mana-movies_mana-accounts_id_fk` FOREIGN KEY (`user_id`) REFERENCES `mana-accounts` (`id`),
  ADD CONSTRAINT `mana-movies_mana-categories_id_fk` FOREIGN KEY (`category_id`) REFERENCES `mana-categories` (`id`);

--
-- Constraints for table `mana-reviews`
--
ALTER TABLE `mana-reviews`
  ADD CONSTRAINT `mana-reviews_mana-accounts_id_fk` FOREIGN KEY (`user_id`) REFERENCES `mana-accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mana-reviews_mana-movies_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `mana-movies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
