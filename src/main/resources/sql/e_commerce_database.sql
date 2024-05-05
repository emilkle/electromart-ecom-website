-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 01, 2024 at 12:14 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_commerce_database_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `address_ID` int(5) NOT NULL,
  `user_ID` int(4) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postal_code` varchar(5) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Brand`
--

CREATE TABLE `Brand` (
  `brand_ID` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `category_ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `CreditCard`
--

CREATE TABLE `CreditCard` (
  `credit_card_flag` enum('Credit card') NOT NULL DEFAULT 'Credit card',
  `third_party_service` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Cryptocurrency`
--

CREATE TABLE `Cryptocurrency` (
  `crypto_flag` enum('Cryptocurrency') NOT NULL DEFAULT 'Cryptocurrency',
  `currency_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Inventory`
--

CREATE TABLE `Inventory` (
  `inventory_ID` int(11) NOT NULL,
  `product_ID` varchar(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `last_restock_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Klarna`
--

CREATE TABLE `Klarna` (
  `klarna_flag` enum('Klarna') NOT NULL DEFAULT 'Klarna',
  `email_address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Order`
--

CREATE TABLE `Order` (
  `order_ID` int(4) NOT NULL,
  `user_ID` int(4) NOT NULL,
  `order_date` date NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `order_total_amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `OrderItem`
--

CREATE TABLE `OrderItem` (
  `order_ID` int(4) NOT NULL,
  `product_ID` varchar(11) NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `item_subtotal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Payment`
--

CREATE TABLE `Payment` (
  `payment_ID` int(4) NOT NULL,
  `order_ID` int(4) NOT NULL,
  `payment_method` enum('Credit card','Cryptocurrency','Vipps','Klarna') NOT NULL DEFAULT 'Credit card',
  `payment_amount` float NOT NULL,
  `payment_date` date NOT NULL,
  `payment_status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Product`
--

CREATE TABLE `Product` (
  `product_ID` varchar(11) NOT NULL,
  `brand_ID` int(5) NOT NULL,
  `category_ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Promotion`
--

CREATE TABLE `Promotion` (
  `promotion_ID` varchar(8) NOT NULL,
  `product_ID` varchar(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `discount_type` varchar(255) NOT NULL,
  `discount_value` float NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Review`
--

CREATE TABLE `Review` (
  `review_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `product_ID` varchar(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `review_text` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Shipping`
--

CREATE TABLE `Shipping` (
  `shipping_ID` int(4) NOT NULL,
  `order_ID` int(4) NOT NULL,
  `shipping_method` varchar(255) NOT NULL,
  `shipping_cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `user_ID` int(4) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Vipps`
--

CREATE TABLE `Vipps` (
  `vipps_flag` enum('Vipps') NOT NULL DEFAULT 'Vipps',
  `phone_number` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`address_ID`),
  ADD KEY `user_ID` (`user_ID`);

--
-- Indexes for table `Brand`
--
ALTER TABLE `Brand`
  ADD PRIMARY KEY (`brand_ID`);

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
    MODIFY `category_ID` INT AUTO_INCREMENT PRIMARY KEY;

--
-- Indexes for table `CreditCard`
--
ALTER TABLE `CreditCard`
  ADD PRIMARY KEY (`credit_card_flag`);

--
-- Indexes for table `Cryptocurrency`
--
ALTER TABLE `Cryptocurrency`
  ADD PRIMARY KEY (`crypto_flag`);

--
-- Indexes for table `Inventory`
--
ALTER TABLE `Inventory`
  ADD PRIMARY KEY (`inventory_ID`),
  ADD KEY `product_ID_fk` (`product_ID`);

--
-- Indexes for table `Klarna`
--
ALTER TABLE `Klarna`
  ADD PRIMARY KEY (`klarna_flag`);

--
-- Indexes for table `Order`
--
ALTER TABLE `Order`
  ADD PRIMARY KEY (`order_ID`),
  ADD KEY `user_ID` (`user_ID`);

--
-- Indexes for table `OrderItem`
--
ALTER TABLE `OrderItem`
  ADD PRIMARY KEY (`order_ID`,`product_ID`);

--
-- Indexes for table `Payment`
--
ALTER TABLE `Payment`
  ADD PRIMARY KEY (`payment_ID`),
  ADD KEY `order_ID` (`order_ID`),
  ADD KEY `payment_method` (`payment_method`);

--
-- Indexes for table `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`product_ID`),
  ADD KEY `brand_ID` (`brand_ID`),
  ADD KEY `category_ID` (`category_ID`);

--
-- Indexes for table `Promotion`
--
ALTER TABLE `Promotion`
  ADD PRIMARY KEY (`promotion_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- Indexes for table `Review`
--
ALTER TABLE `Review`
  ADD PRIMARY KEY (`review_ID`),
  ADD KEY `user_ID` (`user_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- Indexes for table `Shipping`
--
ALTER TABLE `Shipping`
  ADD PRIMARY KEY (`shipping_ID`),
  ADD KEY `order_ID` (`order_ID`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`user_ID`);

--
-- Indexes for table `Vipps`
--
ALTER TABLE `Vipps`
  ADD PRIMARY KEY (`vipps_flag`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Address`
--
ALTER TABLE `Address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `User` (`user_ID`);

--
-- Constraints for table `Inventory`
--
ALTER TABLE `Inventory`
  ADD CONSTRAINT `product_ID_fk` FOREIGN KEY (`product_ID`) REFERENCES `Product` (`product_ID`);

--
-- Constraints for table `Order`
--
ALTER TABLE `Order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `User` (`user_ID`);

--
-- Constraints for table `Payment`
--
ALTER TABLE `Payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`order_ID`) REFERENCES `Order` (`order_ID`),
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`payment_method`) REFERENCES `CreditCard` (`credit_card_flag`),
  ADD CONSTRAINT `payment_ibfk_3` FOREIGN KEY (`payment_method`) REFERENCES `Klarna` (`klarna_flag`),
  ADD CONSTRAINT `payment_ibfk_4` FOREIGN KEY (`payment_method`) REFERENCES `Cryptocurrency` (`crypto_flag`),
  ADD CONSTRAINT `payment_ibfk_5` FOREIGN KEY (`payment_method`) REFERENCES `Vipps` (`vipps_flag`);

--
-- Constraints for table `Product`
--
ALTER TABLE `Product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`brand_ID`) REFERENCES `Brand` (`brand_ID`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_ID`) REFERENCES `Category` (`category_ID`);

--
-- Constraints for table `Promotion`
--
ALTER TABLE `Promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`product_ID`) REFERENCES `Product` (`product_ID`);

--
-- Constraints for table `Review`
--
ALTER TABLE `Review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `User` (`user_ID`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`product_ID`) REFERENCES `Product` (`product_ID`);

--
-- Constraints for table `Shipping`
--
ALTER TABLE `Shipping`
  ADD CONSTRAINT `shipping_ibfk_1` FOREIGN KEY (`order_ID`) REFERENCES `Order` (`order_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
