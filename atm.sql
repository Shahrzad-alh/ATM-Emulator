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
--  data for table atm.users

INSERT INTO `users` (`user_id`, `username`, `password`, `full_name`, `failed_attempt`, `account_locked` , `lock_time`) VALUES
    (1, '1111222233334444', '$2a$10$msQstIrW0wwmCXEW5r42YeBaq3FgI0tenq0ow2pC3n/zbtVx0GyKm', 'Shahrzad A', 0, 0, NULL), -- PIN : 123
    (2, '1212343456567878', '$2a$10$50CkBMOuexPeJX70cXcN0O/RpJ6tpr2w9.O273qH.BHdd3L5cE2r.', 'Shabnam B', 0, 0, NULL), -- PIN : 456
    (3, '2121434365658787', '$2a$10$2/1BYZb7FDesgQjT5fYDVOKIiEPwKCv5iml2N6chNHwLmMmLMox0W', 'Shahin C', 0, 0, NULL), -- PIN : 789
    (4, '2222222233333333', '$2a$10$IvE9tf62Iubq/vmjcfrjzOZBYJnmZLV54ugdE30ouPm8MBy37uqUW', 'John J', 0, 0, NULL), -- PIN : 987
    (5, '4444444455555555', '$2a$10$WGc9I8gt0tycf9.d27vd6O/i.MkYadlZxb.lCVQqyUj2gfAQfbmMe', 'Joe K', 0, 0, NULL), -- PIN : 654
    (6, '1111111122222222', '$2a$10$5..6MwhbUBOd4r0xx5vRN.gJAkhYba/uNAjs4pvwOadprFMxHskry', 'Jay L', 0, 0, NULL); -- PIN :321

-- Dumping structure for table atm.accounts
CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `checking` double(8, 2) NOT NULL,
    `saving` double(8, 2) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES users (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
--  data for table atm.accounts
INSERT INTO `accounts` (`account_id`, `checking`, `saving`) VALUES
    (1, 121.45, 8888.90),
    (2, 343.66, 7777.66),
    (3, 768.90, 4444.45),
    (4, 2323.23, 999.99),
    (5, 88.88, 111.88),
    (6, 0.0, 0.0);
