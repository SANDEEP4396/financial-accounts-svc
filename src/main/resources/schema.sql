CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(100) NOT NULL,
    `last_name` varchar(100) NOT NULL,
    `email` varchar(255) NOT NULL UNIQUE,
    `phone_number` varchar(20) NOT NULL UNIQUE,
    `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by` varchar(100) NOT NULL,
    `updated_by` varchar(100) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `customer_id` int NOT NULL,
    `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(255) NOT NULL,
    `created_at` timestamp DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created_by` varchar(100) NOT NULL,
    `updated_by` varchar(100) DEFAULT NULL
);