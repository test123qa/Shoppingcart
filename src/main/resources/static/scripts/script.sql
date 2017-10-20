CREATE TABLE `shoppingCart`.`product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_at03k6o77o1rru4e6jtn4vbx7` (`product_id`),
  CONSTRAINT `FK_at03k6o77o1rru4e6jtn4vbx7` FOREIGN KEY (`product_id`) REFERENCES `purchase_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1



CREATE TABLE `shoppingCart`.`purchase_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `creditCardNumber` varchar(255) DEFAULT NULL,
  `deleteFlag` bit(1) NOT NULL,
  `totalAmount` double DEFAULT NULL,
  `updateFlag` bit(1) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qqbls7cu36ygqhrkasta2q8a4` (`user_id`),
  CONSTRAINT `FK_lxyld0mwdbb7b3wmph6hwpjfh` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qqbls7cu36ygqhrkasta2q8a4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


CREATE TABLE `shoppingCart`.`shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i8xbokgexfbd414phnfpuoc7u` (`product_id`),
  KEY `FK_qx5dh8nhlnh77h8im91vlqwdv` (`user_id`),
  CONSTRAINT `FK_i8xbokgexfbd414phnfpuoc7u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_qx5dh8nhlnh77h8im91vlqwdv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1



CREATE TABLE `shoppingCart`.`user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DOB` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1



ALTER TABLE `shoppingCart`.`product` 
ADD COLUMN `subCategory` VARCHAR(45) NOT NULL AFTER `product_id`