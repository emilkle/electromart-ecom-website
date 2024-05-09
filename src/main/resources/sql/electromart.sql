-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 09, 2024 at 11:17 AM
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
-- Database: `electromart`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postal_code` varchar(5) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_ID`, `user_ID`, `address`, `postal_code`, `city`, `country`) VALUES
(1, 0, 'Dagestan mountains 13D', '11456', 'Makhachkala', 'Dagestan'),
(2, 0, 'AKA boulevard', '3045', 'San Jose', 'USA'),
(3, 1, '13 popeye street', '90432', 'Louisiana', 'USA'),
(4, 2, '4th & 2nd karate street', '1231', 'Montreal', 'Canada'),
(5, 3, '13 Sunset boulevard', '84583', 'Miami', 'USA'),
(6, 3, '17 Wittman street', '15202', 'Denver', 'USA'),
(7, 4, '3 Aloha street', '28462', 'Waianae', 'USA'),
(8, 5, '29 Chama street', '6813', 'Danbury', 'USA'),
(9, 6, '3 Jackson Wink street', '4679', 'Albuquerque', 'USA'),
(10, 7, '17 Mamas dinner street', '3897', 'Birmingham', 'England');

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `brand_ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`brand_ID`, `name`, `description`) VALUES
(1, 'Apple', 'Brand that makes smartphones'),
(2, 'Samsung', 'Computers, smartphones and TV'),
(3, 'JBL', 'Speakers'),
(4, 'Sony', 'Playstation, cameras'),
(5, 'Microsoft', 'PC, gaming, software, accessories'),
(6, 'Bosch', 'Appliances'),
(7, 'Miele', 'Appliances'),
(8, 'Lenovo', 'Computers'),
(9, 'Canon', 'Cameras'),
(10, 'Garmin', 'Sports watches'),
(11, 'reMarkable', 'Digital notepads'),
(12, 'LG', 'TVs');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_ID`, `name`, `description`) VALUES
(1, 'Appliances', 'Ovens, refrigerators, dishwasher, washing machine, dryer, etc'),
(2, 'Computers', 'Laptops, stationary computers, gaming computers'),
(3, 'Computer accessories', 'Mouses, keyboards, hardware, monitors'),
(4, 'Cellphone', 'Different brands and types of cell phones'),
(5, 'Tablets', 'Different brands and types of tablets'),
(6, 'Smart watches', 'Different brands and types of smart watches'),
(7, 'TV', 'Different brands and types of TVs'),
(8, 'Sound', 'Speakers, radio, smart speakers, surround systems'),
(9, 'Gaming', 'Gaming consoles, games and accessories'),
(10, 'Kitchen utensils', 'Mix masters, blenders, air fryers, pans, accessories, etc'),
(11, 'Personal care', 'Blow dryers, hair cutters, electrical toothbrushes, etc');


-- --------------------------------------------------------

--
-- Table structure for table `creditcard`
--

CREATE TABLE `creditcard` (
  `credit_card_flag` varchar(255) NOT NULL DEFAULT 'Credit card',
  `third_party_service` varchar(255) NOT NULL,
  `payment_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `creditcard`
--

INSERT INTO `creditcard` (`credit_card_flag`, `third_party_service`, `payment_ID`) VALUES
('Credit card', 'Visa', 1);

-- --------------------------------------------------------

--
-- Table structure for table `cryptocurrency`
--

CREATE TABLE `cryptocurrency` (
  `crypto_flag` varchar(255) NOT NULL DEFAULT 'Cryptocurrency',
  `currency_name` varchar(255) NOT NULL,
  `payment_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `cryptocurrency`
--

INSERT INTO `cryptocurrency` (`crypto_flag`, `currency_name`, `payment_ID`) VALUES
('Cryptocurrency', 'Monero', 1);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `inventory_ID` int(11) NOT NULL,
  `product_ID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `last_restock_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`inventory_ID`, `product_ID`, `quantity`, `last_restock_date`) VALUES
(1, 1, 20, '2024-04-20'),
(2, 10, 7, '2024-02-13'),
(3, 12, 4, '2023-12-05'),
(4, 5, 6, '2024-03-31'),
(5, 3, 3, '2024-01-12'),
(6, 4, 8, '2024-04-07'),
(7, 2, 15, '2024-04-15'),
(8, 6, 10, '2024-05-01'),
(9, 7, 3, '2024-03-17'),
(10, 8, 7, '2024-02-08'),
(11, 9, 11, '2024-04-29'),
(12, 11, 10, '2024-03-20');

-- --------------------------------------------------------

--
-- Table structure for table `klarna`
--

CREATE TABLE `klarna` (
  `klarna_flag` varchar(255) NOT NULL DEFAULT 'Klarna',
  `email_address` varchar(255) NOT NULL,
  `payment_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `klarna`
--

INSERT INTO `klarna` (`klarna_flag`, `email_address`, `payment_ID`) VALUES
('Klarna', '123@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `order_total_amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_ID`, `user_ID`, `order_date`, `order_status`, `order_total_amount`) VALUES
(1, 1, '2024-05-07', 'Shipped', 100),
(2, 2, '2024-05-08', 'Processing', 150),
(3, 3, '2024-05-09', 'Delivered', 200),
(4, 4, '2024-05-10', 'Canceled', 50),
(5, 5, '2024-05-11', 'Pending', 120);

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE `orderitem` (
  `order_ID` int(11) NOT NULL,
  `product_ID` int(11) NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `item_subtotal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_ID` int(11) NOT NULL,
  `order_ID` int(11) NOT NULL,
  `payment_method` varchar(255) NOT NULL,
  `payment_amount` float NOT NULL,
  `payment_date` date NOT NULL,
  `payment_status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_ID`, `order_ID`, `payment_method`, `payment_amount`, `payment_date`, `payment_status`) VALUES
(2, 1, 'Credit card', 50, '2024-05-10', 'Completed'),
(3, 1, 'Payment', 100, '2024-05-12', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_ID` int(11) NOT NULL,
  `brand_ID` int(11) NOT NULL,
  `category_ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_ID`, `brand_ID`, `category_ID`, `name`, `description`, `price`) VALUES
(1, 1, 4, 'iPhone 15 max', 'Cell phone', 15000),
(2, 4, 9, 'Playstation 5', 'Gaming console', 6500),
(3, 6, 1, 'Washing machine', 'Standard washing machine', 16990),
(4, 10, 6, 'Garmin Forerunner 655', 'Sports watch with spotify', 4599),
(5, 12, 7, 'LG 75\" OLED 4K TV', 'Smart TV', 25499),
(6, 8, 2, 'Lenovo Yoga 9i Pro', 'Laptop', 19990),
(7, 7, 1, 'Miele refrigerator', 'Built in', 21990),
(8, 11, 5, 'reMarkable 2', 'Digital notepad', 5000),
(9, 1, 5, 'iPad Pro', 'Apple tablet', 12290),
(10, 3, 8, 'JBL Boombox 3', 'Portable speaker', 6490),
(11, 5, 9, 'XBOX One', 'Gaming console', 6500),
(12, 7, 1, 'Miele D5000 Dishwasher', 'Dishwasher', 11900);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `promotion_ID` int(11) NOT NULL,
  `product_ID` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `discount_type` varchar(255) NOT NULL,
  `discount_value` float NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`promotion_ID`, `product_ID`, `description`, `discount_type`, `discount_value`, `start_date`, `end_date`) VALUES
(1, 1, '10 percent off through may 2024', 'percent', 10, '2024-05-01', '2024-05-31'),
(2, 12, 'Weekend sale: 1500,- off', 'fixed', 1500, '2024-05-03', '2024-05-06');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `review_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `product_ID` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `review_text` varchar(255) NOT NULL,
  `review_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`review_ID`, `user_ID`, `product_ID`, `rating`, `review_text`, `review_date`) VALUES
(1, 0, 2, 0, 'Namber one bullshit brather, I loose against your boy in game', '2024-05-04'),
(2, 3, 2, 5, 'Great product, good quality', '2024-02-12'),
(3, 1, 1, 5, 'Greath phone!', '2024-04-30'),
(4, 5, 5, 5, 'Chama', '2024-05-04'),
(5, 3, 1, 5, '!Muy Bien!', '2024-05-07');

-- --------------------------------------------------------

--
-- Table structure for table `shipping`
--

CREATE TABLE `shipping` (
  `shipping_ID` int(11) NOT NULL,
  `order_ID` int(11) NOT NULL,
  `shipping_method` varchar(255) NOT NULL,
  `shipping_cost` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `shipping`
--

INSERT INTO `shipping` (`shipping_ID`, `order_ID`, `shipping_method`, `shipping_cost`) VALUES
(1, 1, 'Standard Shipping', '10.00'),
(2, 2, 'Express Shipping', '20.00'),
(3, 5, 'Next Day Delivery', '30.00'),
(4, 4, 'Priority Shipping', '15.00'),
(5, 3, 'International Shipping', '50.00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_ID` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_ID`, `email`, `password`, `first_name`, `last_name`) VALUES
(0, 'namber.one.bullshit@smesh.com', '60d78e974dfbaf8e00e85e845c6b4d5dd8bafcf092c81214e856cb44f2111587', 'Khabib', 'Nurmagomedov'),
(1, 'dc@doublechamp.com', 'faee49b8b060d1d8cd263fcf9426fc7c4ad1ad98bfad7f7761c93cca09824043', 'Daniel', 'Cormier'),
(2, 'gsp@ufc.com', '103b72e8fbdbde3794cb78c4c2de379d53331ce5c0fbdd988277196629952208', 'Georges', 'Saint Pierre'),
(3, 'kamaru@usman.com', 'cd259a7acda9d1927508f23c640d14c74d68f39e8cd2e2b5716d1d5fba34aede', 'Kamaru', 'Usman'),
(4, 'bmf@aloha.com', '7b827fa0a009f7580593f251b6334ce1bbf71d372ebada44a237bc2f69956fb6', 'Max', 'Holloway'),
(5, 'poatan@ko.com', 'c290b8cb40788cc1cdf73574b3d9e9b15d20e6a0fed88bef7b9d18bd0c74e951', 'Alex', 'Pereira'),
(6, 'cocaine@cialis.com', '1e4cb95f1e5c6340d4d7dd273707adbee732a565ab23a5d49d451bb8152ca5ea', 'Jon', 'Jones'),
(7, 'leon@headshotdead.com', 'cf65e92019fe84f165ec8ca9830b72bfc7b3214978f0f823df800cfa655139c4', 'Leon', 'Edwards');

-- --------------------------------------------------------

--
-- Table structure for table `vipps`
--

CREATE TABLE `vipps` (
  `vipps_flag` varchar(255) NOT NULL DEFAULT 'Vipps',
  `phone_number` int(8) NOT NULL,
  `payment_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_danish_ci;

--
-- Dumping data for table `vipps`
--

INSERT INTO `vipps` (`vipps_flag`, `phone_number`, `payment_ID`) VALUES
('Vipps', 90887021, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_ID`),
  ADD KEY `user_ID` (`user_ID`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`brand_ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_ID`);

--
-- Indexes for table `creditcard`
--
ALTER TABLE `creditcard`
  ADD PRIMARY KEY (`payment_ID`);

--
-- Indexes for table `cryptocurrency`
--
ALTER TABLE `cryptocurrency`
  ADD PRIMARY KEY (`payment_ID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inventory_ID`),
  ADD KEY `product_ID_fk` (`product_ID`);

--
-- Indexes for table `klarna`
--
ALTER TABLE `klarna`
  ADD PRIMARY KEY (`payment_ID`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_ID`),
  ADD KEY `user_ID` (`user_ID`);

--
-- Indexes for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD PRIMARY KEY (`order_ID`,`product_ID`),
  ADD KEY `orderitem_product_product_ID_fk` (`product_ID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_ID`),
  ADD KEY `order_ID` (`order_ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_ID`),
  ADD KEY `brand_ID` (`brand_ID`),
  ADD KEY `category_ID` (`category_ID`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`promotion_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_ID`),
  ADD KEY `user_ID` (`user_ID`),
  ADD KEY `product_ID` (`product_ID`);

--
-- Indexes for table `shipping`
--
ALTER TABLE `shipping`
  ADD PRIMARY KEY (`shipping_ID`),
  ADD KEY `order_ID` (`order_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_ID`);

--
-- Indexes for table `vipps`
--
ALTER TABLE `vipps`
  ADD PRIMARY KEY (`payment_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `brand_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `creditcard`
--
ALTER TABLE `creditcard`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cryptocurrency`
--
ALTER TABLE `cryptocurrency`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `inventory_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `klarna`
--
ALTER TABLE `klarna`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1006;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `promotion_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `review_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `shipping`
--
ALTER TABLE `shipping`
  MODIFY `shipping_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `vipps`
--
ALTER TABLE `vipps`
  MODIFY `payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`);

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_product_product_ID_fk` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`);

--
-- Constraints for table `orderitem`
--
ALTER TABLE `orderitem`
  ADD CONSTRAINT `orderitem_order_order_ID_fk` FOREIGN KEY (`order_ID`) REFERENCES `order` (`order_ID`),
  ADD CONSTRAINT `orderitem_product_product_ID_fk` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`order_ID`) REFERENCES `order` (`order_ID`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`brand_ID`) REFERENCES `brand` (`brand_ID`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_ID`) REFERENCES `category` (`category_ID`);

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_product_product_ID_fk` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`),
  ADD CONSTRAINT `review_product_product_ID_fk` FOREIGN KEY (`product_ID`) REFERENCES `product` (`product_ID`);

--
-- Constraints for table `shipping`
--
ALTER TABLE `shipping`
  ADD CONSTRAINT `shipping_ibfk_1` FOREIGN KEY (`order_ID`) REFERENCES `order` (`order_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
