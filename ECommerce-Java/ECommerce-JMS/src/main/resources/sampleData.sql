/*
SQLyog - Free MySQL GUI v5.01
Host - 5.0.16-nt : Database - appdy
*********************************************************************
Server version : 5.0.16-nt
*/


create database if not exists `appdy`;

USE `appdy`;

SET FOREIGN_KEY_CHECKS=0;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL auto_increment,
  `item_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2E7B20886675DF` (`item_id`),
  KEY `FK2E7B201841ADF` (`user_id`),
  CONSTRAINT `FK2E7B201841ADF` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK2E7B20886675DF` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert into `item` values 
(4,'book'),
(10,'cap'),
(7,'chair'),
(3,'eraser'),
(8,'glass'),
(5,'mouse'),
(9,'paper'),
(1,'pen'),
(2,'pencil'),
(6,'table');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(100) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert into `user` values 
(1,'test','appdynamics'),
(2,'appdynamics','appdynamics'),
(3,'vikash','appdynamics'),
(4,'santo','appdynamics'),
(5,'ravi','appdynamics');

SET FOREIGN_KEY_CHECKS=1;
