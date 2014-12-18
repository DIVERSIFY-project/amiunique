-- phpMyAdmin SQL Dump
-- version 4.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 18, 2014 at 01:24 PM
-- Server version: 5.5.39-MariaDB
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fingerprint`
--

-- --------------------------------------------------------

--
-- Table structure for table `fpData`
--

CREATE TABLE IF NOT EXISTS `fpData` (
`counter` int(11) NOT NULL,
  `id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `addressHttp` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `userAgentHttp` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `acceptHttp` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hostHttp` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `connectionHttp` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `encodingHttp` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `languageHttp` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `orderHttp` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pluginsJS` text COLLATE utf8_unicode_ci NOT NULL,
  `platformJS` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cookiesJS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `dntJS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `timezoneJS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `resolutionJS` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `localJS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `sessionJS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IEDataJS` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `canvasJS` mediumtext COLLATE utf8_unicode_ci,
  `webGLJs` mediumtext COLLATE utf8_unicode_ci,
  `fontsFlash` mediumtext COLLATE utf8_unicode_ci,
  `resolutionFlash` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `languageFlash` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `platformFlash` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adBlock` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `vendorWebGLJS` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `rendererWebGLJS` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `octaneScore` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sunspiderTime` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=40509 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fpData`
--
ALTER TABLE `fpData`
 ADD PRIMARY KEY (`counter`), ADD KEY `localJS` (`localJS`), ADD KEY `dntJS` (`dntJS`), ADD KEY `acceptHttp` (`acceptHttp`(255)), ADD KEY `hostHttp` (`hostHttp`), ADD KEY `connectionHttp` (`connectionHttp`), ADD KEY `encodingHttp` (`encodingHttp`), ADD KEY `languageHttp` (`languageHttp`), ADD KEY `orderHttp` (`orderHttp`), ADD KEY `platformJS` (`platformJS`), ADD KEY `cookiesJS` (`cookiesJS`), ADD KEY `timezoneJS` (`timezoneJS`), ADD KEY `resolutionJS` (`resolutionJS`), ADD KEY `sessionJS` (`sessionJS`), ADD KEY `IEDataJS` (`IEDataJS`), ADD KEY `resolutionFlash` (`resolutionFlash`), ADD KEY `languageFlash` (`languageFlash`), ADD KEY `platformFlash` (`platformFlash`), ADD KEY `adBlock` (`adBlock`), ADD KEY `vendorWebGLJS` (`vendorWebGLJS`), ADD KEY `rendererWebGLJS` (`rendererWebGLJS`), ADD KEY `userAgentHttp` (`userAgentHttp`(255));

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fpData`
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
