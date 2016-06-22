-- phpMyAdmin SQL Dump
-- version 4.6.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 22, 2016 at 11:37 AM
-- Server version: 10.0.23-MariaDB
-- PHP Version: 5.6.22

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
-- Table structure for table `audio`
--

CREATE TABLE `audio` (
  `counter` int(11) NOT NULL,
  `id` varchar(50) NOT NULL,
  `acSampleRate` varchar(11) NOT NULL,
  `acState` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `acMaxChannelCount` varchar(6) NOT NULL,
  `acNumberOfInputs` varchar(6) NOT NULL,
  `acNumberOfOutputs` varchar(6) NOT NULL,
  `acChannelCount` varchar(6) NOT NULL,
  `acChannelCountMode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `acChannelInterpretation` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `anFftSize` varchar(6) NOT NULL,
  `anFrequencyBinCount` varchar(6) NOT NULL,
  `anMinDecibels` varchar(6) NOT NULL,
  `anMaxDecibels` varchar(6) NOT NULL,
  `anSmoothingTimeConstant` varchar(60) NOT NULL,
  `anNumberOfInputs` varchar(6) NOT NULL,
  `anNumberOfOutputs` varchar(6) NOT NULL,
  `anChannelCount` varchar(6) NOT NULL,
  `anChannelCountMode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `anChannelInterpretation` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `audioDynSum` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `audioDynHash` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `audioPoints` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `audioDynPoints` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `audio`
--
ALTER TABLE `audio`
  ADD PRIMARY KEY (`counter`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `audio`
--
ALTER TABLE `audio`
  MODIFY `counter` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
