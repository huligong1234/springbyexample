DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `re_order` int(11) DEFAULT '0',
  `update_date` varchar(255) DEFAULT NULL,
  `app_code` varchar(32) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_code` (`app_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;