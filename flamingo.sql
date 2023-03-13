-- MySQL Workbench Synchronization
-- Generated: 2023-03-09 17:56
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Administrator

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `flamingo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

CREATE TABLE IF NOT EXISTS `flamingo`.`addresses` (
  `address_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `building_name` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `pincode` VARCHAR(255) NULL DEFAULT NULL,
  `state` VARCHAR(255) NULL DEFAULT NULL,
  `street` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`cart_items` (
  `cart_item_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `discount` DOUBLE NOT NULL,
  `product_price` DOUBLE NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `cart_id` BIGINT(20) NULL DEFAULT NULL,
  `product_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`),
  INDEX `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id` ASC) VISIBLE,
  INDEX `FK1re40cjegsfvw58xrkdp6bac6` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FK1re40cjegsfvw58xrkdp6bac6`
    FOREIGN KEY (`product_id`)
    REFERENCES `flamingo`.`products` (`product_id`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c`
    FOREIGN KEY (`cart_id`)
    REFERENCES `flamingo`.`carts` (`cart_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`carts` (
  `cart_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `total_price` DOUBLE NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  INDEX `FKb5o626f86h46m4s7ms6ginnop` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop`
    FOREIGN KEY (`user_id`)
    REFERENCES `flamingo`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`categories` (
  `category_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`order_items` (
  `order_item_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `discount` DOUBLE NOT NULL,
  `ordered_product_price` DOUBLE NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `order_id` BIGINT(20) NULL DEFAULT NULL,
  `product_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  INDEX `FKbioxgbv59vetrxe0ejfubep1w` (`order_id` ASC) VISIBLE,
  INDEX `FKocimc7dtr037rh4ls4l95nlfi` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w`
    FOREIGN KEY (`order_id`)
    REFERENCES `flamingo`.`orders` (`order_id`),
  CONSTRAINT `FKocimc7dtr037rh4ls4l95nlfi`
    FOREIGN KEY (`product_id`)
    REFERENCES `flamingo`.`products` (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`orders` (
  `order_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `order_date` DATE NULL DEFAULT NULL,
  `order_status` VARCHAR(255) NULL DEFAULT NULL,
  `total_amount` DOUBLE NULL DEFAULT NULL,
  `payment_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `FK8aol9f99s97mtyhij0tvfj41f` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `FK8aol9f99s97mtyhij0tvfj41f`
    FOREIGN KEY (`payment_id`)
    REFERENCES `flamingo`.`payments` (`payment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`payments` (
  `payment_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `payment_method` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`payment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`products` (
  `product_id` BIGINT(20) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `discount` DOUBLE NOT NULL,
  `image` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `product_name` VARCHAR(255) NULL DEFAULT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `special_price` DOUBLE NOT NULL,
  `category_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id` ASC) VISIBLE,
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9`
    FOREIGN KEY (`category_id`)
    REFERENCES `flamingo`.`categories` (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`roles` (
  `role_id` BIGINT(20) NOT NULL,
  `role_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`user_address` (
  `user_id` BIGINT(20) NOT NULL,
  `address_id` BIGINT(20) NOT NULL,
  INDEX `FKpv7y2l6mvly37lngi3doarqhd` (`address_id` ASC) VISIBLE,
  INDEX `FKrmincuqpi8m660j1c57xj7twr` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKpv7y2l6mvly37lngi3doarqhd`
    FOREIGN KEY (`address_id`)
    REFERENCES `flamingo`.`addresses` (`address_id`),
  CONSTRAINT `FKrmincuqpi8m660j1c57xj7twr`
    FOREIGN KEY (`user_id`)
    REFERENCES `flamingo`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx`
    FOREIGN KEY (`user_id`)
    REFERENCES `flamingo`.`users` (`user_id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q`
    FOREIGN KEY (`role_id`)
    REFERENCES `flamingo`.`roles` (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `flamingo`.`users` (
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(20) NULL DEFAULT NULL,
  `last_name` VARCHAR(20) NULL DEFAULT NULL,
  `mobile_number` VARCHAR(10) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UK_6dotkott2kjsp8vw4d0m25fb7` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
