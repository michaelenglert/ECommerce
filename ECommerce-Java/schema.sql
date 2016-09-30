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
  `user_id` bigint(20)  NULL,
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
  `description` varchar(4000) NOT NULL,
  `category` varchar(100) NOT NULL,
  `itemType` varchar(25) NOT NULL,
  `imagePath` varchar(255) default NULL,
  `price` decimal(19,4) default NULL,
  `image` LONGBLOB,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
LOCK TABLES `item` WRITE;


INSERT INTO `item` (id,title,description,category,itemType,imagePath,price) VALUES (1,'A Clockwork Orange','A vicious fifteen-year-old droog is the central character of this 1963 classic. In Anthony Burgess\'s nightmare vision of the future, where the criminals take over after dark, the story is told by the central character, Alex, who talks in a brutal invented slang that brilliantly renders his and his friends\' social pathology.','Fiction','BOOK','images/A_Clockwork_Orange-Anthony_Burgess.jpg', '5.95'),
(2,'The Goldfinch: A Novel','Theo Decker, a 13-year-old New Yorker, miraculously survives an accident that kills his mother. Abandoned by his father, Theo is taken in by the family of a wealthy friend. Bewildered by his strange new home on Park Avenue, disturbed by schoolmates who don\'t know how to talk to him, and tormented above all by his longing for his mother, he clings to the one thing that reminds him of her: a small','Fiction','BOOK','images/goldfinch.jpg', '16.75'),
(3,'Personal','You can leave the army, but the army doesn\’t leave you. Not always. Not completely, notes Jack Reacher—and sure enough, the retired military cop is soon pulled back into service. This time, for the State Department and the CIA.','Fiction','BOOK','images/personal.jpg', '16.95'),
(4,'Farewell To Arms','Written when Ernest Hemingway was thirty years old and lauded as the best American novel to emerge from World War I, A Farewell to Arms is the unforgettable story of an American ambulance driver on the Italian front and his passion for a beautiful English nurse. Set against the looming horrors of the battlefield—weary, demoralized men marching in the rain during the German attack on Caporetto; the profound struggle between loyalty and desertion—this gripping, semiautobiographical work captures the harsh realities of war and the pain of lovers caught in its inexorable sweep','Fiction','BOOK','images/Farewell_To_Arms-Ernest_Hemingway.jpg', '10.95'),
(5,'Freakonomics','Which is more dangerous, a gun or a swimming pool? What do schoolteachers and sumo wrestlers have in common? Why do drug dealers still live with their moms? How much do parents really matter? How did the legalization of abortion affect the rate of violent crime? These may not sound like typical questions for an econo-mist to ask. But Steven D. Levitt is not a typical economist.','Non-Fiction','BOOK','images/Freakonomics-Stephen_Levitt.jpg', '5.95'),
(6,'Driven From Within','Michael Jordan is the rare global icon whose celebrity extends beyond his original stage and onto multiple platforms. His relentless determination produced six NBA Championships and some of the most spectacular performances in sports history, while his enduring grace and unique sense of style made him equally famous in the worlds of fashion, business, and marketing.','Fiction','BOOK','images/Jordan-Driven_From_Within.jpg', '10.25'),
(7,'Sacred Hoops','Eleven years ago, when Phil Jackson first wrote these words in Sacred Hoops, he was the triumphant head coach of the Chicago Bulls, known for his Zen approach to the game. He hadnt yet moved to the Los Angeles Lakers, with whom he would bring his total to an astounding nine NBA titles.','Non-Fiction','BOOK','images/Sacred_Hoops-Phil_Jackson.jpg', '14.95'),
(8,'Shantaram','So begins this epic, mesmerizing first novel set in the underworld of contemporary Bombay. Shantaram is narrated by Lin, an escaped convict with a false passport who flees maximum security prison in Australia for the teeming streets of a city where he can disappear.','Fiction','BOOK','images/Shantaram-Gregory_David_Roberts.jpg' , '12.75'),
(9,'The Fist Of God','From the bestselling author of The Day of the Jackal, international  master of intrigue Frederick Forsyth, comes a thriller that brilliantly  blends fact with fiction for one of this summer\'s--or any season\'s--most  explosive reads!','Fiction','BOOK','images/The_Fist_Of_God-Forsyth.jpg', '10.65'),
(10,'The Godfather','The Godfather has gone on to become a part of America\'s national culture, as well as a trilogy of landmark motion pictures. Now, in this newly-repackaged 30th Anniversary Edition, readers old and new can experience this timeless tale of crime for themselves.From the lavish opening scene where Don Corleone entertains guests and conducts business at his daughter\'s wedding...to his son, Michael, who takes his father\'s place to fight for his family...to the bloody climax where all family business is finished,','Fiction','BOOK','images/The_Godfather-Mario_Puzo.jpg', '5.95'),
(11,'The Lost City Of Z','In 1925, the legendary British explorer Percy Fawcett ventured into the Amazon jungle, in search of a fabled civilization. He never returned. ','Fiction','BOOK','images/The_Lost_City_Of_Z-David_Grann.jpg' , '5.50'),
(12,'The Tourist','Milo Weaver has tried to leave his old life of secrets and lies behind by giving up his job as a tourist for the CIA―an undercover agent with no home, no identity. Now he\'s working a desk at the agency\'s New York headquarters. But when the arrest of a long-sought-after assassin sets off an investigation into a colleague, exposing new layers of intrigue in his old cases, he has no choice but to go back undercover and find out who\'s been behind it allfrom the very beginning.','Fiction','BOOK','images/The_Tourist-Olen_Steinhauer.jpg', '6.95'),
(13, 'Unbroken','In this captivating and lavishly illustrated young adult edition of her award-winning #1 New York Times bestseller, Laura Hillenbrand tells the story of a former Olympian\'s courage, cunning, and fortitude following his plane crash in enemy territory. This adaptation of Unbroken introduces a new generation to one of history\'s most thrilling survival epics.','Fiction','BOOK','images/unbroken.jpg','26.95');
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
  `street1` varchar(255)  NULL,
  `street2` varchar(255)  NULL,
  `city_name`       varchar(32)  NULL,
  `state`       varchar(255) NULL,
  `country`       varchar(255)  NULL,
  `zip`       varchar(255)  NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES 
(1,'amitabhbachchan@aol.com','appdynamics','Amitabh Bachchan','GOLD',NULL,NULL,'San Francisco','CA','USA','94507'),
(2,'christopher.lee@gmail.com','hollywoodlee','Christopher Lee','PLATINUM',NULL,NULL,'Paris',NULL,'FRANCE','94507'),
(3,'emilia.clarke@hotmail.com','gameofthrones', 'Emilia Clarke', 'SILVER',NULL,NULL,'San Francisco','CA','USA','94507'),
(4,'tom.hardy@yahoo.com','jokerhardy','Tom Hardy','DIAMOND',NULL,NULL,'Bangalore',NULL,'India','94507'),
(5,'kate.upton@msn.com','warofclans','Kate Upton','BRONZE',NULL,NULL,'London',NULL,'UK','94507'),
(6,'leonardo.dicaprio@actors.com','wolfofwallstreet','Leonardo DiCaprio','BRONZE',NULL,NULL,'Honolulu','HI','USA','94507'),
(7,'brad.pitt@aol.com','jolie','Brad Pitt','BRONZE',NULL,NULL,'San Francisco','CA','USA','94507'),
(8,'jake.gyllenhaal@gmail.com','southpaw','Jake Gyllenhaal','SILVER',NULL,NULL,'San Francisco','CA','USA','94507'),
(9,'steph.curry@hotmail.com','winner2015','Stephen Curry','SILVER',NULL,NULL,'San Francisco','CA','USA','94507'),
(10,'sachin.tendulkar@yahoo.com','godofcricket','Sachin Tendulkar','SILVER',NULL,NULL,'San Francisco','CA','USA','94507'),
(11,'kobe.bryant@msn.com','allstars','Kobe Bryant','SILVER',NULL,NULL,'San Francisco','CA','USA','94507'),
(12,'lebron.james@nba.com', 'gocavs', 'Lebron James', 'PLATINUM',NULL,NULL,'Cleveland','OH','USA','94507'),
(13,'ma.lin@gmail.com','pingpong','Ma Lin','SILVER',NULL,NULL,'San Francisco','CA','USA','94507'),

(14,'lin.dan@hotmail.com','badminton','Lin Dan','SILVER','1 Dan Way',NULL,'London','England','UK', '324234'),
(15,'tom.brady@deflated.com','patriots','Tom Brady','SILVER','10 Deflate Dr.',NULL, 'Boston', 'MA','USA', '432134'),
(16,'michael.phelps@yahoo.com','olympics2016','Michael Phelps','SILVER','269 Ala Puumalu St.',NULL,'Honolulu', 'HI','USA','96818'),
(17,'mark.zuckerberg@myspace.com','facebook','Mark Zuckerberg','PLATINUM','20 Danville Blvd',NULL,'Palo Alto', 'CA', 'USA', '94507'),
(18,'larry.page@hotmail.com','google','Larry Page','PLATINUM','21 Hyderbad way',NULL,'Bangalore', 'ID', 'India','435342'),
(19,'sheryl.sandberg@gmail.com','exgoogle','Sheryl Sandberg','PLATINUM','303 2nd Street',NULL,'San Francisco','CA','USA','94107'),
(20,'jeff.bezos@msn.com','amazon','Jeff Bezos','PLATINUM','1 Microsoft Way',NULL,'Seattle','WA', 'USA','90210'),
(21,'tim.cook@yahoo.com','appleinc','Tim Cook','PLATINUM','2 Apple Loop',NULL,'Cupertino', 'CA','USA', '94566'),
(22,'marissa.mayer@gmail.com','yahooyodel','Marissa Mayer','PLATINUM','1 Cisco Driver', NULL,'Sunnyvale','CA', 'USA','94566'),
(23,'steve.jobs@gmail.com','appleforever','Steve Jobs','PLATINUM','1 Apple Parkway', NULL,'Cupertino', 'CA', 'USA', '945345'),
(24,'bill.gates@yahoo.com','microsoft','Bill Gates','PLATINUM','1 Microsoft Drive', NULL,'Seattle', 'WA', 'USA', '342412'),
(25,'sergey.brin@yahoo.com','googleco','Sergey Brin','PLATINUM','10 Google Drive', NULL,'Mountain View', 'CA', 'USA', '324143'),
(26,'john.legend@msn.com','allofme','Larry Ellison','DIAMOND','20 Lanai Dr.',NULL,'Honolulu', 'HI', 'USA', '95848'),
(27,'bruno.mars@aol.com','uptownfunk','Bruno Mars','DIAMOND','BRUNO WAY',NULL,'Honolulu','HI','USA','32441423'),
(28, 'meg.whitman@aol.com', 'exebay', 'Meg Whitman', 'SILVER', '343 Apple Pkwy', NULL,'Cupertino','CA', 'USA','32412'),
(29, 'jp.morgan@gmail.com', 'JP Morgan', 'JP Morgan','DIAMOND' ,'10 Stanley Drive',NULL,'New York','NY', 'USA','3412343'),

(30, 'richard.branson@yahoo.com', 'bransonamerica', 'Richard Branson', 'DIAMOND','Bransone Way',NULL, 'New York','NY','USA','3241234'),
(31, 'larry.ellison@msn.com', 'oraclelarry', 'Larry Ellison', 'PLATINUM', '10 Ellison St.', NULL,'Redwood City', 'CA', 'USA', '42342'),
(32, 'jyoti.bansal@hotmail.com', 'AppDynamics', 'Jyoti Bansal', 'PLATINUM','10 Bansal Ct.' , NULL,'San Francisco', 'CA', 'USA','94107'),
(33, 'satya.nadella@yahoo.com', 'SatyaMicrosoft', 'Satya Nadella', 'GOLD', '35 Microsoft Way',NULL,'Seattle', 'WA', 'USA','833333'),
(34, 'solomon@aol.com', 'DockerSolomon', 'Solomon', 'SILVER','20 Telegraph Ave', NULL,'San Francisco', 'CA', 'USA','94111'),
(35, 'aleftik@appdynamics.com', 'aleftik', 'aleftik', 'SILVER','260 Stone Valley Way',NULL,'San Francisco','CA','USA','94507'),
(36, 'test@appdynamics.com', 'appdynamics', 'appd', 'SILVER','260 Stone Valley Way',NULL,'San Francisco','CA','USA','94507');



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

DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL auto_increment,
  `store_order_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
    KEY `ORDER_ITEM_ITEM` (`item_id`),
    KEY `ORDER_ID_ORRDER_ITEM_ID` (`store_order_id`),
    CONSTRAINT `ORDER_ITEM_ITEM` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
    CONSTRAINT `ORDER_ID_ORRDER_ITEM_ID` FOREIGN KEY (`store_order_id`) REFERENCES `store_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `store_order`;
CREATE TABLE `store_order` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) NOT NULL,
  `createdOn` datetime default NULL,
  `street1` varchar(255)  NULL,
  `street2` varchar(255)  NULL,
  `city_name`       varchar(32)  NULL,
  `state`       varchar(255) NULL,
  `country`       varchar(255)  NULL,
  `zip`       varchar(255)  NULL,
  `cc_number`       varchar(20)  NOT NULL,
  `cc_type`       bigint(1)  NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `USER_ID_USER` (`user_id`),
    CONSTRAINT `FK3E7B201841ADF` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

commit;
