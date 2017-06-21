-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 23 Mai 2017 à 17:33
-- Version du serveur :  5.7.18-0ubuntu0.16.04.1
-- Version de PHP :  7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `fingerprint`
--

-- --------------------------------------------------------

--
-- Structure de la table `extensionData`
--

CREATE TABLE `extensionData` (
  `counter` int(11) NOT NULL,
  `id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `addressHttp` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `creationDate` datetime NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
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
  `sunspiderTime` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pluginsJSHashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `canvasJSHashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `webGLJsHashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `fontsFlashHashed` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `hardwareConcurrency` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `availableScreenResolution` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cpuClass` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modernizr` text COLLATE utf8_unicode_ci,
  `overwrittenObjects` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `osMediaQueries` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appCodeName` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oscpu` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appVersion` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `languages` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mimeTypes` text COLLATE utf8_unicode_ci,
  `pluginsUsingMimeTypes` text COLLATE utf8_unicode_ci,
  `product` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productSub` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vendor` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vendorSub` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `touchSupport` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `buildID` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `navigatorPrototype` text COLLATE utf8_unicode_ci,
  `mathsConstants` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resOverflow` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `errorsGenerated` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unknownImageError` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fontsEnum` text COLLATE utf8_unicode_ci,
  `audio` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `extensionData`
--
ALTER TABLE `extensionData`
  ADD PRIMARY KEY (`counter`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `extensionData`
--
ALTER TABLE `extensionData`
  MODIFY `counter` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
