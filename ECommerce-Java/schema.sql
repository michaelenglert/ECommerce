create database if not exists `appdy`;

USE `appdy`;

SET FOREIGN_KEY_CHECKS=0;

/* Table structure for Fault Injection*/
DROP TABLE IF EXISTS `Fault`;
CREATE TABLE `Fault` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bugname` varchar(100) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `timeframe` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

/*Table structure for table `cart` */
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2E7B201841ADF` (`user_id`),
  CONSTRAINT `FK2E7B201841ADF` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart`
--

/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
LOCK TABLES `cart` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

/*Table structure for table `cart` */
DROP TABLE IF EXISTS `cart_item`;
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `items_id` bigint(20) NOT NULL,
  `cart_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK3E7B201841ADF` (`items_id`),
  KEY `FK3E7B20886675DF` (`cart_id`),
  CONSTRAINT `FK3E7B201841ADF` FOREIGN KEY (`items_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FK3E7B20886675DF` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart`
--

/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
LOCK TABLES `cart_item` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL,
  `imagePath` varchar(255) default NULL,
  `price` decimal(19,4) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
LOCK TABLES `item` WRITE;
INSERT INTO `item` (id,title,imagePath,price) VALUES (1,'A Clockwork Orange','images/A_Clockwork_Orange-Anthony_Burgess.jpg', '5.95'),
(2,'The Goldfinch: A Novel','images/goldfinch.jpg', '16.75'),
(3,'Personal','images/personal.jpg', '16.95'),
(4,'Farewell To Arms','images/Farewell_To_Arms-Ernest_Hemingway.jpg', '10.95'),
(5,'Freakonomics','images/Freakonomics-Stephen_Levitt.jpg', '5.95'),
(6,'Driven From Within','images/Jordan-Driven_From_Within.jpg', '10.25'),
(7,'Sacred Hoops','images/Sacred_Hoops-Phil_Jackson.jpg', '14.95'),
(8,'Shantaram','images/Shantaram-Gregory_David_Roberts.jpg' , '12.75'),
(9,'The Fist Of God','images/The_Fist_Of_God-Forsyth.jpg', '10.65'),
(10,'The Godfather','images/The_Godfather-Mario_Puzo.jpg', '5.95'),
(11,'The Lost City Of Z','images/The_Lost_City_Of_Z-David_Grann.jpg' , '5.50'),
(12,'The Tourist','images/The_Tourist-Olen_Steinhauer.jpg', '6.95'),
(13, 'Unbroken','images/unbroken.jpg','26.95');
UNLOCK TABLES;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(100) NOT NULL,
  `password` varchar(32) NOT NULL,
  `customer_name` varchar(32) NOT NULL,
  `customer_type` varchar(32) NOT NULL,
  `city_name`       varchar(32) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES 
(1,'amitabhbachchan@aol.com','appdynamics','Amitabh Bachchan','GOLD','San Francisco'),
(2,'christopher.lee@gmail.com','hollywoodlee','Christopher Lee','PLATINUM','Paris'),
(3,'emilia.clarke@hotmail.com','gameofthrones', 'Emilia Clarke', 'SILVER','San Francisco'),
(4,'tom.hardy@yahoo.com','jokerhardy','Tom Hardy','DIAMOND','Bangalore'),
(5,'kate.upton@msn.com','warofclans','Kate Upton','BRONZE','London'),
(6,'leonardo.dicaprio@actors.com','wolfofwallstreet','Leonardo DiCaprio','BRONZE','Honolulu'),
(7,'brad.pitt@aol.com','jolie','Brad Pitt','BRONZE','San Francisco'),
(8,'jake.gyllenhaal@gmail.com','southpaw','Jake Gyllenhaal','SILVER','San Francisco'),
(9,'steph.curry@hotmail.com','winner2015','Stephen Curry','SILVER','San Francisco'),
(10,'sachin.tendulkar@yahoo.com','godofcricket','Sachin Tendulkar','SILVER','San Francisco'),
(11,'kobe.bryant@msn.com','allstars','Kobe Bryant','SILVER','San Francisco'),
(12,'lebron.james@nba.com', 'gocavs', 'Lebron James', 'PLATINUM', 'Cleveland'),
(13,'ma.lin@gmail.com','pingpong','Ma Lin','SILVER','San Francisco'),
(14,'lin.dan@hotmail.com','badminton','Lin Dan','SILVER','London'),
(15,'tom.brady@deflated.com','patriots','Tom Brady','SILVER','New England'),
(16,'michael.phelps@yahoo.com','olympics2016','Michael Phelps','SILVER','Honolulu'),
(17,'mark.zuckerberg@myspace.com','facebook','Mark Zuckerberg','PLATINUM','Palo Alto'),
(18,'larry.page@hotmail.com','google','Larry Page','PLATINUM','Bangalore'),
(19,'sheryl.sandberg@gmail.com','exgoogle','Sheryl Sandberg','PLATINUM','San Francisco'),
(20,'jeff.bezos@msn.com','amazon','Jeff Bezos','PLATINUM','Seattle'),
(21,'tim.cook@yahoo.com','appleinc','Tim Cook','PLATINUM','Cupertino'),
(22,'marissa.mayer@gmail.com','yahooyodel','Marissa Mayer','PLATINUM','Sunnyvale'),
(23,'steve.jobs@gmail.com','appleforever','Steve Jobs','PLATINUM','Cupertino'),
(24,'bill.gates@yahoo.com','microsoft','Bill Gates','PLATINUM','Seattle'),
(25,'sergey.brin@yahoo.com','googleco','Sergey Brin','PLATINUM','Mountain View'),
(26,'john.legend@msn.com','allofme','Larry Ellison','DIAMOND','Honolulu'),
(27,'bruno.mars@aol.com','uptownfunk','Bruno Mars','DIAMOND','Honolulu'),
(28, 'meg.whitman@aol.com', 'exebay', 'Meg Whitman', 'SILVER', 'Cupertino'),
(29, 'jp.morgan@gmail.com', 'JP Morgan', 'JP Morgan','DIAMOND' ,'New York'),
(30, 'richard.branson@yahoo.com', 'bransonamerica', 'Richard Branson', 'DIAMOND', 'New York'),
(31, 'larry.ellison@msn.com', 'oraclelarry', 'Larry Ellison', 'PLATINUM', 'Redwood City'),
(32, 'jyoti.bansal@hotmail.com', 'AppDynamics', 'Jyoti Bansal', 'PLATINUM', 'San Francisco'),
(33, 'satya.nadella@yahoo.com', 'SatyaMicrosoft', 'Satya Nadella', 'GOLD', 'Seattle'),
(34, 'solomon@aol.com', 'DockerSolomon', 'Solomon', 'SILVER', 'San Francisco'),
(35, 'aleftik', 'aleftik', 'aleftik', 'SILVER', 'San Francisco'),
(36, 'test', 'appdynamics', 'appd', 'SILVER', 'San Francisco');

UNLOCK TABLES;

/*!40101 SET NAMES utf8 */;
/*!40101 SET SQL_MODE=''*/;

create database if not exists `inventory`;

USE `inventory`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/* Table structure for table `item` */
DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` bigint(20) NOT NULL auto_increment,
  `quantity` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Data for the table `item` */
insert  into `item`(`id`,`quantity`) values 
(4,100),
(10,100),
(11,100),
(12,100),
(13,100),
(14,100),
(7,100),
(3,100),
(8,100),
(5,100),
(9,100),
(1,100),
(2,100),
(6,100);

/* Table structure for table `orders` */
DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL auto_increment,
  `quantity` bigint(20) default NULL,
  `price` Decimal (19,4) default NULL,
  `createdOn` datetime default NULL,
  `item_Id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Data for the table `orders` */
insert  into `orders`(`id`,`quantity`,`createdOn`,`item_Id`) values 
(1,1,'2008-12-04 10:14:44',1),
(2,1,'2008-12-04 10:15:47',1),
(3,1,'2008-12-04 10:15:49',1),
(4,1,'2008-12-04 10:15:49',1),
(5,1,'2008-12-04 10:15:50',1),
(6,1,'2008-12-04 10:15:51',1),
(7,1,'2008-12-04 10:15:51',1),
(8,1,'2008-12-04 10:15:52',1),
(9,1,'2008-12-04 10:20:45',1),
(10,1,'2008-12-04 10:21:33',1),
(11,1,'2008-12-04 10:24:08',1),
(12,1,'2008-12-04 10:25:10',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

commit;
