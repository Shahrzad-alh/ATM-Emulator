-- Dumping database structure for atm
CREATE DATABASE IF NOT EXISTS `atm`;
USE `atm`;
DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `users`;

-- Dumping structure for table atm.users
CREATE TABLE IF NOT EXISTS `users` (
    `user_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(100) NOT NULL,
    `full_name` varchar(100) NOT NULL,
    `failed_attempt` tinyint(4) ,
    `account_locked` tinyint(4) ,
    `lock_time` DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- Dumping data for table atm.users

INSERT INTO `users` (`user_id`, `username`, `password`, `full_name`, `failed_attempt`, `account_locked` , `lock_time`) VALUES
    (1, '378282246310005', '$2a$10$msQstIrW0wwmCXEW5r42YeBaq3FgI0tenq0ow2pC3n/zbtVx0GyKm', 'Shahrzad Alhavan', 0, 0, NULL), -- 123
    (2, '371449635398431', '$2a$10$50CkBMOuexPeJX70cXcN0O/RpJ6tpr2w9.O273qH.BHdd3L5cE2r.', 'Shahin Rep', 0, 0, NULL), -- 456
    (3, '378734493671000', '$2a$10$2/1BYZb7FDesgQjT5fYDVOKIiEPwKCv5iml2N6chNHwLmMmLMox0W', 'Jane Doe', 0, 0, NULL), -- 789
    (4, '305693090259041', '$2a$10$IvE9tf62Iubq/vmjcfrjzOZBYJnmZLV54ugdE30ouPm8MBy37uqUW', 'Hasan A', 0, 0, NULL), -- 987
    (5, '385200000232372', '$2a$10$WGc9I8gt0tycf9.d27vd6O/i.MkYadlZxb.lCVQqyUj2gfAQfbmMe', 'Sam A', 0, 0, NULL), -- 654
    (6, '601111111111111', '$2a$10$5..6MwhbUBOd4r0xx5vRN.gJAkhYba/uNAjs4pvwOadprFMxHskry', 'Ali Something', 0, 0, NULL); -- 321

-- Dumping structure for table atm.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `checking` double(8, 2) NOT NULL,
    `saving` double(8, 2) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES users (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
-- Dumping data for table atm.accounts
INSERT INTO `accounts` (`account_id`, `checking`, `saving`) VALUES
    (1, 123.45, 678.90),
    (2, 666.66, 666.66),
    (3, 678.90, 123.45),
    (4, 999.99, 999.99),
    (5, 888.88, 888.88),
    (6, 0.0, 0.0);
