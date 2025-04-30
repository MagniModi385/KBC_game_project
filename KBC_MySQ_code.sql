-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2025 at 05:12 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `game`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DELETION` (IN `P_tag` VARCHAR(50))   Begin
DELETE from Player where P_tag=PlayerTag;
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DISPLAY` ()   BEGIN
Select * from Player;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Update_Escore` (IN `Score` INT, IN `P_tag` VARCHAR(50))   BEGIN
Update Player set Easy_score=score where PlayerTag=P_tag;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Update_Hscore` (IN `Score` INT, IN `P_tag` VARCHAR(50))   BEGIN
Update Player set Hard_score=score where PlayerTag=P_tag;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `easy_questions`
--

CREATE TABLE `easy_questions` (
  `Q_no` int(11) NOT NULL,
  `Question` varchar(200) NOT NULL,
  `Option_A` varchar(50) NOT NULL,
  `Option_B` varchar(50) NOT NULL,
  `Option_C` varchar(50) NOT NULL,
  `Option_D` varchar(50) NOT NULL,
  `Correct_Option` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `easy_questions`
--

INSERT INTO `easy_questions` (`Q_no`, `Question`, `Option_A`, `Option_B`, `Option_C`, `Option_D`, `Correct_Option`) VALUES
(1, 'Which city is decided to be common time zone of India?', 'Bihar', 'Mirzapur', 'Gorakhpur', 'Allahbad', 'B'),
(2, 'Who is the current prime minister of Italy?', 'Georgia Meloni', 'Saddam Hussein', 'Ivanka Trump', 'Rishi Sunak', 'A'),
(3, 'How many bones does a human have?', '208', '207', '206', '300', 'C'),
(4, 'What does not grow on tree in regard to the HINDI saying?', 'Money', 'Paise', 'Leaves', 'Roots', 'B'),
(5, 'Who wrote India\'s National anthem?', 'Chetan Bhagat', 'Udit Narayan', 'Lal Bahadur Shastri', 'Rabindranath Tagore', 'D'),
(6, 'What is the longest bone on the human body?', 'Clavicle', 'Patella', 'Femur', 'Cranium', 'C'),
(7, 'Who was the first woman to win a nobel prize?', 'Shakira', 'Marie Curie', 'Dr Annie Fitzgerald', 'Estora Grubusky', 'B'),
(8, 'Which country had Chris Columbus searching for?', 'America', 'Brazil', 'India', 'Myanmar', 'C'),
(9, 'What is the national bird of the USA?', 'Sparrow', 'Falcon', 'Eagle', 'The bald eagle', 'D'),
(10, 'What is the capital of the country where Eiffel Tower is located?', 'Paris', 'Hungary', 'Budapest', 'Beijing', 'A'),
(11, 'What is the largest ocean on Earth?', 'Atlantic Ocean', 'Indian Ocean', 'Pacific Ocean', 'Arctic Ocean', 'C'),
(12, 'What is the hardest natural substance on Earth?', 'Sillicon Carbide', 'Tungsten', 'Graphene', 'Diamond', 'D'),
(13, 'What is the speed of light?', '30,000 km/s', '30,00,000 m/s', '300,000 km/s', '300,000 m/s', 'C'),
(14, 'Which city is know as Pink City of India?', 'Jaipur', 'Kochi', 'Udaipur', 'Kolkata', 'A'),
(15, 'Who is the actor in the picture who played this Legendary role in The Dark Knight?', 'Joaquin Phoenix', 'Dhanush', 'Heath Ledger', 'Christian Bale', 'C'),
(16, 'Which country gave USA the Statue of Liberty as a gift?', 'France', 'Germany', 'Italy', 'Spain', 'A'),
(17, 'Which wall is visible from space?', 'Great Wall of Mongol', 'Great Wall of China', 'Mexico-US Border Wall', 'Trojan Wall', 'B'),
(18, 'Which country won the First Cricket World Cup in 1975?', 'West Indies', 'Australia', 'India', 'South Africa', 'A'),
(19, 'Who is known as the King of Pop?', 'Elvis Presley', 'Queen', 'Michael Jackson', 'Freddie Mercury', 'C'),
(20, 'In which sport would you perform a 3-pointer?', 'Basketball', 'Rugby', 'Football', 'Ice Hockey', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `getans`
--

CREATE TABLE `getans` (
  `Q_no` int(11) NOT NULL,
  `Ans_S1` varchar(50) NOT NULL,
  `Ans_S2` varchar(50) NOT NULL,
  `Ans_S3` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `getans`
--

INSERT INTO `getans` (`Q_no`, `Ans_S1`, `Ans_S2`, `Ans_S3`) VALUES
(1, 'B', 'B', 'C'),
(2, 'A', 'A', 'C'),
(3, 'C', 'A', 'B'),
(4, 'D', 'A', 'C'),
(5, 'B', 'B', 'B'),
(6, 'B', 'A', 'A'),
(7, 'A', 'A', 'A'),
(8, 'C', 'C', 'A'),
(9, 'B', 'C', 'C'),
(10, 'A', 'A', 'C');

-- --------------------------------------------------------

--
-- Table structure for table `hard_set1`
--

CREATE TABLE `hard_set1` (
  `Q_no` int(11) NOT NULL,
  `Question_S1` varchar(200) NOT NULL,
  `Option_A` varchar(50) NOT NULL,
  `Option_B` varchar(50) NOT NULL,
  `Option_C` varchar(50) NOT NULL,
  `Option_D` varchar(50) NOT NULL,
  `Correct_Option` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hard_set1`
--

INSERT INTO `hard_set1` (`Q_no`, `Question_S1`, `Option_A`, `Option_B`, `Option_C`, `Option_D`, `Correct_Option`) VALUES
(1, 'Announced in Sept 2021, the AUKUS is a trilateral Security pact among which countries?', 'Austria, Uganda, Kenya', 'UK, USA, Australia', 'Australia, UK, Japan', 'Austria, Ukraine, USA', 'B'),
(2, 'In 2021, Anita Anand took charge as the Minister of national defence of which country?', 'Canada', 'Norway', 'UK', 'USA', 'A'),
(3, 'Where are the WHO (World Health Organization) headquarters located?', 'Paris, France', 'Brussels, Belgium', 'Geneva, Switzerland', 'Austin, Texas', 'C'),
(4, 'Who set the fastest lap record in the Monaco Grand Prix in 2024?', 'Max Verstappen', 'Fernando Alonso', 'George Russell', 'Lewis Hamilton', 'D'),
(5, 'What is unique to the naming of two elements in the periodic table with atomic numbers 96 and 109?', 'Named after Nobel Laureates', 'Named after women Scientists', 'Named after Indian Scientists', 'They are unnamed', 'B'),
(6, 'From which country that has a well-known football team did Brazil gain its Independence?', 'Spain', 'Portugal', 'Argentina', 'Italy', 'B'),
(7, 'Which country received a Nobel Prize in Peace category in 2022?', 'Ukraine', 'Iceland', 'USA', 'Switzerland', 'A'),
(8, 'Who patented RDX in 1898 and was first used in World War II?', 'Steve Wozniak', 'Arthur J Morgan', 'George Friedrich Henning', 'Albrecht Schneider', 'C'),
(9, 'Who found the planet Uranus?', 'Galileo Galilei', 'William Herschel', 'Augean Heracle', 'Kramer Kravitz', 'B'),
(10, 'The news of which of these was announced in papers in London the day of coronation of Queen Elizabeth II?', 'Summiting of Mount Everest', 'Moon Landing', 'Avalanche in Alps killing People', 'Launch of Sputnik', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `hard_set2`
--

CREATE TABLE `hard_set2` (
  `Q_no` int(11) NOT NULL,
  `Question_S2` varchar(200) NOT NULL,
  `Option_A` varchar(50) NOT NULL,
  `Option_B` varchar(50) NOT NULL,
  `Option_C` varchar(50) NOT NULL,
  `Option_D` varchar(50) NOT NULL,
  `Correct_Option` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hard_set2`
--

INSERT INTO `hard_set2` (`Q_no`, `Question_S2`, `Option_A`, `Option_B`, `Option_C`, `Option_D`, `Correct_Option`) VALUES
(1, 'Which mathematician is known for his work on the theory of numbers and the introduction of the concept of mathematical rigor?', 'Carl Friedrich Gauss', 'Georg Cantor', 'Euclid', 'David Hilbert', 'B'),
(2, 'What is the name of the principle stating that you cannot simultaneously know the exact position and momentum of a particle?', 'Heisenberg Uncertainty Principle', 'Pauli Exclusion Principle', 'Hawking Radiation', 'Planck’s Constant', 'A'),
(3, 'Which organ in the human body can regenerate itself completely every few months?', 'Liver', 'Heart', 'Kidney', 'Lung', 'A'),
(4, 'Which element has the chemical symbol \"W\"?', 'Tungsten', 'Tantalum', 'Thallium', 'Wolfram', 'A'),
(5, 'Which famous physicist developed the theory of general relativity?', 'Niels Bohr', 'Albert Einstein', 'Max Planck', 'Erwin Schrödinger', 'B'),
(6, 'What is the capital city of Mongolia?', 'Ulaanbaatar', 'Astana', 'Baku', 'Tbilisi', 'A'),
(7, 'In which year did the Titanic sink?', '1912', '1914', '1916', '1918', 'A'),
(8, 'What is the main ingredient in traditional Japanese miso soup?', 'Tofu', 'Seaweed', 'Miso paste', 'Soy sauce', 'C'),
(9, 'Which historical figure is known as the \"Father of Modern Physics\"?', 'Isaac Newton', 'Galileo Galilei', 'Albert Einstein', 'Richard Feynman', 'C'),
(10, 'Who discovered penicillin?', 'Alexander Fleming', 'Louis Pasteur', 'Joseph Lister', 'Edward Jenner', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `hard_set3`
--

CREATE TABLE `hard_set3` (
  `Q_no` int(11) NOT NULL,
  `Question_S3` varchar(200) NOT NULL,
  `Option_A` varchar(50) NOT NULL,
  `Option_B` varchar(50) NOT NULL,
  `Option_C` varchar(50) NOT NULL,
  `Option_D` varchar(50) NOT NULL,
  `Correct_Option` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hard_set3`
--

INSERT INTO `hard_set3` (`Q_no`, `Question_S3`, `Option_A`, `Option_B`, `Option_C`, `Option_D`, `Correct_Option`) VALUES
(1, 'Which planet has the most moons?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'C'),
(2, 'Who painted the \"Starry Night\"?', 'Leonardo da Vinci', 'Pablo Picasso', 'Vincent van Gogh', 'Claude Monet', 'C'),
(3, 'Which organelle is known as the powerhouse of the cell?', 'Nucleus', 'Mitochondria', 'Ribosome', 'Chloroplast', 'B'),
(4, 'In which ocean is the Mariana Trench, the deepest point on Earth, located?', 'Atlantic Ocean', 'Indian Ocean', 'Pacific Ocean', 'Arctic Ocean', 'C'),
(5, 'What is the capital city of Finland?', 'Oslo', 'Helsinki', 'Stockholm', 'Copenhagen', 'B'),
(6, 'What is the term used for a word that is spelled the same forwards and backwards?', 'Palindrome', 'Anagram', 'Oxymoron', 'Homonym', 'A'),
(7, 'Which chemical element has the highest atomic number that is found naturally on Earth?', 'Uranium', 'Plutonium', 'Neptunium', 'Thorium', 'A'),
(8, 'Which planet in the solar system is closest in size to Earth?', 'Venus', 'Mars', 'Mercury', 'Neptune', 'A'),
(9, 'Which country hosted the first modern Olympic Games?', 'USA', 'France', 'Greece', 'UK', 'C'),
(10, 'Which planet is known as the Red Planet?', 'Venus', 'Saturn', 'Mars', 'Jupiter', 'C');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `PlayerTag` varchar(50) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Easy_score` int(11) DEFAULT NULL,
  `Hard_score` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `player`
--
DELIMITER $$
CREATE TRIGGER `Update_HardScore` BEFORE UPDATE ON `player` FOR EACH ROW BEGIN
    IF NEW.Hard_score < OLD.Hard_score THEN
        -- Raise an error if the new score is not higher
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'New score is less than the current high score';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_Easyscore` BEFORE UPDATE ON `player` FOR EACH ROW BEGIN
    IF NEW.Easy_score < OLD.Easy_score THEN
        -- Raise an error if the new score is not higher
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'New score is less than the current high score';
    END IF;
END
$$
DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
