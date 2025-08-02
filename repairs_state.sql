-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 07, 2018 at 05:06 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `repairs_state`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`user_name`, `password`) VALUES
('admin', 'admin'),
('qq', 'qq');

-- --------------------------------------------------------

--
-- Table structure for table `customreg`
--

DROP TABLE IF EXISTS `customreg`;
CREATE TABLE IF NOT EXISTS `customreg` (
  `RMA` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `vardas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pavarde` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `telnr` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `adresas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pastas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `prietaisas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `modelis` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `prietaisoSN` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gedimas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `laikas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  UNIQUE KEY `RMA` (`RMA`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `customreg`
--

INSERT INTO `customreg` (`RMA`, `vardas`, `pavarde`, `telnr`, `adresas`, `pastas`, `prietaisas`, `modelis`, `prietaisoSN`, `gedimas`, `laikas`) VALUES
(18, 'Tomas', 'Sakinas', '865472256', '', '', 'Telefonas', 'Samsung edge 6', 'R52F803084D', 'Iškelti duomenis', '2018/05/27'),
(17, 'Akvilė', 'Paulauskaitė', '861254235', '', '', 'Kompiuteris', 'Lenov', 'CB050627C', 'Nėra garso', '2018/05/27'),
(15, 'Arnas', 'Salonis', '865214752', '', '', 'Kompiuteris', 'Asus x553m', 'E9n0cv601883397', 'Neisijungia', '2018/05/27'),
(16, 'Aurelija', 'Stankutė', '867412545', '', '', 'Telefonas', 'Iphone 6+', '353314070000722', 'Pakeisti ekraną', '2018/05/27'),
(40, 'Julius', 'Lengvinas', '865480482', '', '', 'Kompiuteris', 'Sacionarus', 'nera', 'Neįsijungia', '2018/05/27'),
(13, 'Romas', 'Kataitis', '86745224d', NULL, NULL, 'Teleizorius', 'Philips 42pfl6403', '2345qd5265w', 'Neįsijungia', '2018/05-22'),
(19, 'Taciana', 'Kravčiek', '861416670', 'Laukininkų 45-50', '', 'Televizorius', 'Samsung ue40d5520', '--', 'Persikrauna ant logo', '2018/05/27'),
(20, 'Andrius', 'Lach', '865225553', '', '', 'Plančetinis ', 'TopCon FC-5000', 'nėra', 'Nėra wifi', '2018/05/27'),
(21, 'Daiva Zigmantė', 'Zigmantė', '861578622', '', '', 'Kavos aparatas', 'Bosch Verobar 300', '--', 'Neišbėga kava', '2018/05/27'),
(22, 'Kęstas Jakštas', 'Jakštas', '865412254', '', '', 'Kompiuteris', 'Acer Aspire 5749', '20818252376', 'Pakeisti klaetūrą', '2018/05/27'),
(23, 'Evaldas', 'Bertašius', '867208996', '', 'info@imeistras.lt', 'Kompiuteris', 'Apple a1286', 'C02GJ5BEDV7M', 'Neužsikrauna os', '2018/05/27'),
(24, 'Romualdas', 'Malukas', '866944092', '', '', 'Telefonas', 'Samsung SM-G903F', '355001072797623', 'Ištraukti duomenis', '2018/05/27'),
(25, 'Andrėjus', 'Kvauka', '861276948', '', '', 'Televizorius', 'Tvstar led19rv1', 's1233Ie01FG00942', 'Nerodo kanalų', '2018/05/27'),
(26, 'Danutė', 'Gailiūnienė', '868898236', '', '', 'Fotoaparatas', 'Canon .3ft', '--', 'Nefokusuoja', '2018/05/27'),
(27, 'Mindaugas', 'Arčėnas', '865064582', '', '', 'Kompiuteris', 'HP Pavilion dv9700', 'KL028EA#UUW', 'Nerodo vaizdo', '2018/05/27'),
(28, 'Eimantas', 'Einoris', '867192463', '', '', 'Kompiuteris', 'MSI GE62', '232NLK15070000010', 'Neveikia', '2018/05/27'),
(29, 'UAB', 'Santech ', '868629345', '', '', 'Kompiuteris', 'Dell p26e', '8ZJ8312', 'Nebeįsijungia', '2018/05/27'),
(30, 'Michailas', 'Pozdniakov', '864125836', '', '', 'Telefonas', 'Apple A1688', '353314070000722', 'Atristi', '2018/05/27'),
(31, 'UAB', 'Zundra', '868483865', '', '', 'Kompiuteris', 'Stacionarus', '--', 'Stringa', '2018/05/27'),
(32, 'Evaldas', 'Jurgaitis', '86599824', '', '', 'Kompiuteris', 'Lenovo  X1', '993043081839', 'Perrašyti os', '2018/05/27'),
(33, 'Eimantas', 'Kataris', '869875542', '', '', 'Fotoaparatas', 'Canon 1000D', 'S126191', 'Neskaito kortelių', '2018/05/27'),
(34, 'Evaldas', 'Viskintas', '864577898', '', '', 'Kompiuteris', 'Apple A1297', 'W89340JJ91T', 'Padaryti diagnostiką, kaista', '2018/05/27'),
(35, 'Tadas', 'Kerševičius', '864551235', '', '', 'Planšetė', 'Samsung tab 10.5', 'R52F803084D', 'Neįisijungia, iškelti duomenis', '2018/05/27'),
(36, 'Domantas Klapatauskas', 'Klapatauskas', '862919166', '', '', 'Telefonas', 'huawei p10', '518129', 'Neįsijungia', '2018/05/27'),
(37, 'Julius', 'Lengvinas', '865480482', '', '', 'Kompiuteris', 'Hp 470', '--', 'Pakeisti ekraną', '2018/05/27'),
(38, 'Julius', 'Lengvinas', '865480482', '', '', 'Telefonas', 'Samsung edge 6', '89566632555', 'Nesispaudžia mygtukai', '2018/05/27'),
(39, 'Julius', 'Lengvinas', '865480482', '', '', 'Planšetė', 'Lenova yoga 3', '--', 'Išsijunginėja lauk', '2018/05/27'),
(47, 'Egidijus', 'Valius', '869745487', '', '', 'Kompiuteris', 'Hp probook 470', '555875561', 'Skilęs ekranas', '2018/06/01'),
(46, 'Jonas', 'Brazas', '869745874', '', '', 'Kompiuteris', 'Lenova j36', '36522d5wq66', 'Neįsijungia', '2018/06/01');

-- --------------------------------------------------------

--
-- Table structure for table `remontas`
--

DROP TABLE IF EXISTS `remontas`;
CREATE TABLE IF NOT EXISTS `remontas` (
  `Busena` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Komentaras` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Prietaisas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Modelis` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RMA` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TelNr` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Laikas` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `remontas`
--

INSERT INTO `remontas` (`Busena`, `Komentaras`, `Prietaisas`, `Modelis`, `RMA`, `TelNr`, `Laikas`) VALUES
('Iškoma problemos', 'Įtariamas blogas taitinimo blokas. Tikslinama', 'Teleizorius', 'Philips 42pfl6403', '13', '86745224d', '2018/05/27'),
('Laukiama detalės', 'Užsakytas ekranas', 'Kompiuteris', 'Hp 470', '37', '8654804822', '2018/05/27'),
('Atlikta', 'Pakeistas ekranas', 'Kompiuteris', 'Hp 470', '37', '8654804822', '2018/05/27'),
('Diagnostka', 'Ieškoma problemos', 'Telefonas', 'Samsung edge 6', '38', '865480482', '2018/05/27'),
('Taisoma', 'pagrindinės plokštės gedimas', 'Telefonas', 'Samsung edge 6', '38', '865480482', '2018/05/27'),
('Sutvarkyta', 'Pakeisti keli komponentai', 'Telefonas', 'Samsung edge 6', '38', '865480482', '2018/05/27'),
('Diagnostika', 'Bloga baterija', 'Planšetė', 'Lenova yoga 3', '39', '865480482', '2018/05/27'),
('Atlikta', 'Pakeista baterija', 'Planšetė', 'Lenova yoga 3', '39', '865480482', '2018/05/27'),
('', '', 'Kompiuteris', 'Hp 470', '37', '865480482', '2018/05/27'),
('Diagnostika', 'Blogas maitinmo blokas', 'Kompiuteris', 'Sacionarus', '40', '865480482', '2018/05/27'),
('Atlikta', 'Pakeistas maitinimo blokas', 'Kompiuteris', 'Sacionarus', '40', '865480482', '2018/05/27'),
('', '', 'Telefonas', 'Samsung edge 6', '38', '865480482', '2018/05/27'),
('qw', 'qwd', 'vardenis', 'dqwd', '42', '2782782', '2018/05/28'),
('Taisomas', 'awlkd.', 'Teleizorius', 'Philips 42pfl6403', '13', '86745224d', '2018/05/28'),
('Taisomas', 'Keičiamas ekranas', 'Kompiuteris', 'Hp 470', '37', '865480482', '2018/05/29'),
('taisoams', 'asdwd', 'Telefonas', 'Samsung edge 6', '38', '865480482', '2018/05/29');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_name`, `email`, `password`) VALUES
('865480482', 'lengvinas.julius@gmail.com', '$2a$10$KXF1S3yxID2sswDTy/rRCeItH5ZHY8dPRbGQXQGgGBhQwtJ4SBx1W'),
('86', '86', '86'),
('866', '866', '$2a$10$h5IW.8iilpsepZFvzNh6v.NbE7qY8hXbYUlAJbhQs19wXX7FgdC5i');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
