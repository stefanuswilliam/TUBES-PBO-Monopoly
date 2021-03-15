-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 23, 2019 at 08:18 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `monopoly`
--

-- --------------------------------------------------------

--
-- Table structure for table `chance`
--

CREATE TABLE `chance` (
  `no` int(2) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `is_get` varchar(6) NOT NULL,
  `jumlah` int(5) NOT NULL,
  `pihak_2` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chance`
--

INSERT INTO `chance` (`no`, `nama`, `is_get`, `jumlah`, `pihak_2`) VALUES
(1, 'Selamat Ulang Tahun', 'true', 100, 'bank'),
(2, 'Pesta Bareng', 'false', 100, 'player'),
(3, 'Pajak', 'false', 150, 'bank'),
(4, 'Dapat Bonus', 'true', 150, 'player'),
(5, 'Nothing Happened', 'true', 0, 'bank'),
(6, 'Win Lottery', 'true', 200, 'bank');

-- --------------------------------------------------------

--
-- Table structure for table `high score`
--

CREATE TABLE `high score` (
  `nama` varchar(30) NOT NULL DEFAULT 'John Doe',
  `money` int(6) NOT NULL,
  `date` varchar(15) NOT NULL,
  `id` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `high score`
--

INSERT INTO `high score` (`nama`, `money`, `date`, `id`) VALUES
('John Doe', 100, '01-01-2000', '0000000'),
('John Doe', 200, '01-01-2000', '0000000'),
('John Doe', 200, '01-01-2000', '0000000'),
('John Doe', 300, '01-01-2000', '0000000'),
('John Doe', 400, '01-01-2000', '0000000'),
('John Doe', 500, '01-01-2000', '0000000');

-- --------------------------------------------------------

--
-- Table structure for table `petaks`
--

CREATE TABLE `petaks` (
  `nama_lokasi` varchar(30) NOT NULL,
  `id_kelompok` int(2) NOT NULL,
  `tipe` varchar(10) NOT NULL,
  `harga_beli` int(4) NOT NULL,
  `biaya0` int(4) NOT NULL,
  `biaya1` int(4) NOT NULL,
  `biaya2` int(4) NOT NULL,
  `biaya3` int(4) NOT NULL,
  `biaya4` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petaks`
--

INSERT INTO `petaks` (`nama_lokasi`, `id_kelompok`, `tipe`, `harga_beli`, `biaya0`, `biaya1`, `biaya2`, `biaya3`, `biaya4`) VALUES
('Start', 1, 'start', 0, 0, 0, 0, 0, 0),
('Mus. Nasional', 2, 'biru', 90, 10, 30, 50, 80, 100),
('Gojek', 3, 'service', 200, 50, 100, 0, 0, 0),
('Mus. Geologi', 4, 'biru', 100, 20, 40, 60, 80, 110),
('Mus. Angkut', 5, 'biru', 120, 25, 50, 75, 100, 125),
('Jail', 6, 'jail', 0, 0, 0, 0, 0, 0),
('Jatim Park', 7, 'pink', 130, 30, 60, 90, 120, 150),
('Chance Card', 8, 'chance', 0, 0, 0, 0, 0, 0),
('Trans Studio', 9, 'pink', 140, 35, 70, 100, 135, 170),
('Ancol', 10, 'pink', 150, 40, 80, 120, 140, 160),
('Free Parking', 11, 'free', 0, 0, 0, 0, 0, 0),
('C. Prambanan', 12, 'orange', 160, 40, 80, 120, 150, 190),
('Grab', 13, 'service', 200, 50, 100, 0, 0, 0),
('C. Borobudur', 14, 'orange', 170, 45, 90, 130, 175, 220),
('PLN', 15, 'plumber', 150, 15, 0, 0, 0, 0),
('Go to Jail', 16, 'go_jail', 0, 0, 0, 0, 0, 0),
('Pulau Komodo', 17, 'green', 180, 45, 90, 135, 180, 225),
('Pulau Lombok', 18, 'green', 190, 50, 100, 150, 200, 250),
('Chance Card', 19, 'chance', 0, 0, 0, 0, 0, 0),
('Pulau Bali', 20, 'green', 200, 55, 110, 165, 220, 275);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chance`
--
ALTER TABLE `chance`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `petaks`
--
ALTER TABLE `petaks`
  ADD PRIMARY KEY (`id_kelompok`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
