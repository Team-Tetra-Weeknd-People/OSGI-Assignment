-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 19, 2023 at 08:04 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `malldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `name`, `type`, `price`) VALUES
(1, 'Rice', 'Chicken', 800),
(2, 'Koththu', 'Egg', 500),
(3, 'Noodles', 'Fish', 1000),
(4, 'Pizza', 'Cheese', 1500),
(5, 'Soup', 'Vegetable', 650);

-- --------------------------------------------------------

--
-- Table structure for table `part`
--

CREATE TABLE `part` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `part`
--

INSERT INTO `part` (`id`, `name`, `brand`, `model`, `price`) VALUES
(1, 'Front-Bumper', 'Toyota', 'Premio', 50000),
(2, 'Rear-Bumper', 'Toyota', 'Aqua', 42500),
(3, 'Headlights', 'BMW', '318i', 19200),
(4, 'Brakelights', 'BMW', '530e', 26800),
(5, 'Side-Mirrors', 'Nissan', 'Leaf', 32000),
(8, 'ICS-Sensor', 'Toyota', 'Premio', 180000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `part`
--
ALTER TABLE `part`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `part`
--
ALTER TABLE `part`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Table structure for table `clothingitem`
--

CREATE TABLE `clothingitem` (
  `id` int(11) NOT NULL,
  `brand` varchar(15) NOT NULL,
  `type` varchar(15) NOT NULL,
  `size` varchar(5) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothingitem`
--

INSERT INTO `clothingitem` (`id`, `brand`, `type`, `size`, `price`) VALUES
(1, 'Nike', 'T-Shirt', 'S', 5550),
(2, 'Nike', 'T-Shirt', 'L', 6250),
(3, 'Moose', 'Trouser', '32', 4250),
(4, 'Polo', 'Shirt', 'L', 9820),
(5, 'Adidas', 'T-Shirt', 'XL', 17580);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clothingitem`
--
ALTER TABLE `clothingitem`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clothingitem`
--
ALTER TABLE `clothingitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Table structure for table `toy`
--

CREATE TABLE `toy` (
  `id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `ageGroup` varchar(50) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `toy`
--

INSERT INTO `toy` (`id`, `type`, `ageGroup`, `price`) VALUES
(1, 'Teddy Bear', 'Primary', 500),
(3, 'GamePad', 'Secondary', 5000),
(5, 'Remote car', 'Primary', 10000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `toy`
--
ALTER TABLE `toy`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `toy`
--
ALTER TABLE `toy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
