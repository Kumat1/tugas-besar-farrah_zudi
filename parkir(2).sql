-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2017 at 05:21 AM
-- Server version: 5.5.34
-- PHP Version: 5.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `parkir`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` int(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 123);

-- --------------------------------------------------------

--
-- Table structure for table `parkirmasuk`
--

CREATE TABLE IF NOT EXISTS `parkirmasuk` (
  `id_tiket` int(10) NOT NULL AUTO_INCREMENT,
  `plat_no` varchar(100) NOT NULL,
  `jenis` enum('Mobil','Motor') NOT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `jam_masuk` time DEFAULT NULL,
  `jam_keluar` time DEFAULT NULL,
  `durasi` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `tarif` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_tiket`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `parkirmasuk`
--

INSERT INTO `parkirmasuk` (`id_tiket`, `plat_no`, `jenis`, `tgl_masuk`, `jam_masuk`, `jam_keluar`, `durasi`, `total`, `tarif`) VALUES
(13, 'BK 123 C', 'Mobil', '2017-06-06', '17:35:55', '19:56:07', 2, 6000, 3000),
(14, 'BK 1707 Z', 'Mobil', '2017-06-06', '16:57:10', '20:02:10', 4, 12000, 3000),
(15, 'BK 44 V', 'Mobil', '2017-06-07', '07:39:16', '10:11:29', 3, 9000, 3000),
(16, 'Bk 111 C', 'Mobil', '2017-06-07', '09:55:49', '10:20:13', 1, 3000, 3000),
(17, 'Bk 356 g', 'Mobil', '2017-06-07', '10:20:17', NULL, NULL, NULL, NULL),
(18, 'BK 4040 S', 'Motor', '2017-06-10', '14:32:22', NULL, NULL, NULL, NULL),
(19, 'BK 1234 AA', 'Mobil', '2017-06-10', '22:26:26', '22:30:48', 0, 0, 3000),
(20, 'BK 111 A', 'Mobil', '2017-06-11', '09:50:40', NULL, NULL, NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
