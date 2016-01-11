-- phpMyAdmin SQL Dump
-- version 4.5.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 08, 2016 at 04:28 PM
-- Server version: 10.0.21-MariaDB
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fingerprint`
--

-- --------------------------------------------------------

--
-- Table structure for table `canvasTest`
--

CREATE TABLE `canvasTest` (
  `counter` int(11) NOT NULL,
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `canvas1` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas2` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas3` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas4` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas5` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas6` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas7` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas8` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas9` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas10` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas11` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas12` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas13` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas14` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas15` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `canvas1Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas2Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas3Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas4Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas5Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas6Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas7Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas8Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas9Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas10Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas11Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas12Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas13Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas14Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvas15Hashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `canvasTest`
--
ALTER TABLE `canvasTest`
  ADD PRIMARY KEY (`counter`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `canvasTest`
--
ALTER TABLE `canvasTest`
  MODIFY `counter` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
