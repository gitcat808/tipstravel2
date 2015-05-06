# Host: localhost  (Version: 5.0.15-nt)
# Date: 2015-05-06 12:27:57
# Generator: MySQL-Front 5.3  (Build 4.205)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "tag"
#

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL auto_increment,
  `tag_name` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "tag"
#

INSERT INTO `tag` VALUES (1,'海洋'),(2,'山脉'),(3,'沙漠'),(4,'雪');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL auto_increment,
  `email` varchar(255) collate utf8_bin default NULL,
  `message` varchar(255) collate utf8_bin default NULL,
  `password` varchar(255) character set utf8 default NULL,
  `username` varchar(255) collate utf8_bin NOT NULL,
  `avatar` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin@admin.com',NULL,'admin','admin','/image/avatar/user1.jpg'),(2,'test1@test.com',NULL,'123','testuser1','/image/avatar/user2.jpg'),(3,'test2@test.com',NULL,'123','testuser2','/image/avatar/user1.jpg'),(4,'test3@test.com',NULL,'123','testuser4','/image/avatar/user2.jpg'),(5,'test4@admin.com',NULL,'admin','gou','/image/avatar/user2.jpg');

#
# Structure for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL auto_increment,
  `context` varchar(255) collate utf8_bin default NULL,
  `image` varchar(255) collate utf8_bin default NULL,
  `message_date` varchar(255) character set utf8 default NULL,
  `user_id` int(11) default NULL,
  `like_count` bigint(20) default NULL,
  PRIMARY KEY  (`message_id`),
  KEY `FK38EB0007A677582F` (`user_id`),
  CONSTRAINT `FK38EB0007A677582F` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "message"
#

INSERT INTO `message` VALUES (1,'test1','/image/test.jpg','2015-05-05 00:00:00',2,1),(2,'test2','/image/test.jpg','2015-05-05 00:00:00',2,1),(3,'test3','/image/test.jpg','2015-05-05 00:00:02',2,1),(4,'test4','/image/test.jpg','2015-05-05 00:00:03',2,1),(5,'test5','/image/test.jpg','2015-05-05 00:00:04',2,1),(6,'test6','/image/test.jpg','2015-05-05 00:00:06',2,1),(7,'test7','/image/test.jpg','2015-05-05 00:00:07',2,1),(8,'test8','/image/test.jpg','2015-05-05 00:00:01',2,1),(9,'test9','/image/test.jpg','2015-05-05 00:00:01',2,1),(10,'test10','/image/test.jpg','2015-05-05 00:00:01',2,1),(11,'test11','/image/test.jpg','2015-05-05 00:00:20',2,1),(12,'test12','/image/test.jpg','2015-05-05 00:00:01',2,1),(13,'test13','/image/test.jpg','2015-05-05 00:00:01',2,1),(14,'test14','/image/test.jpg','2015-05-05 00:00:01',2,1),(15,'test15','/image/test.jpg','2015-05-05 00:00:01',2,1),(16,'test16','/image/test.jpg','2015-05-05 00:00:20',2,1),(17,'test17','/image/test.jpg','2015-05-05 01:00:07',2,1),(18,'test18','/image/test.jpg','2015-05-05 01:00:06',2,1),(19,'test19','/image/test.jpg','2015-05-05 01:20:06',2,1),(20,'test20','/image/test.jpg','2015-05-05 02:20:06',2,1),(21,'test21','/image/test.jpg','2015-05-05 02:20:06',2,1),(22,'test22','/image/test.jpg','2015-05-05 00:00:07',2,1),(23,'test23','/image/test.jpg','2015-05-05 00:00:07',2,1),(24,'test24','/image/test.jpg','2015-05-05 00:00:06',2,1),(25,'test25','/image/test.jpg','2015-05-05 10:00:00',2,1);

#
# Structure for table "tag_message"
#

DROP TABLE IF EXISTS `tag_message`;
CREATE TABLE `tag_message` (
  `id` int(11) NOT NULL auto_increment,
  `message_id` int(11) default NULL,
  `tag_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK74782C629751F685` (`tag_id`),
  KEY `FK74782C62446D05E5` (`message_id`),
  CONSTRAINT `FK74782C62446D05E5` FOREIGN KEY (`message_id`) REFERENCES `message` (`message_id`),
  CONSTRAINT `FK74782C629751F685` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "tag_message"
#

INSERT INTO `tag_message` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,3),(11,11,2),(12,10,2),(13,10,1),(14,1,2),(15,2,3),(16,3,3),(17,6,2),(18,8,3),(19,11,4),(20,1,4);

#
# Structure for table "like"
#

DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
  `like_id` int(11) NOT NULL auto_increment,
  `message_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`like_id`),
  KEY `FK32AF97A677582F` (`user_id`),
  KEY `FK32AF97446D05E5` (`message_id`),
  CONSTRAINT `FK32AF97446D05E5` FOREIGN KEY (`message_id`) REFERENCES `message` (`message_id`),
  CONSTRAINT `FK32AF97A677582F` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "like"
#

INSERT INTO `like` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,11,1),(11,12,1),(12,13,1),(13,14,1),(14,15,1),(15,16,1),(16,17,1),(17,18,1),(18,19,1),(19,20,1),(20,21,1),(21,22,1),(22,23,1),(23,24,1),(24,25,1);

#
# Structure for table "user_following"
#

DROP TABLE IF EXISTS `user_following`;
CREATE TABLE `user_following` (
  `id` int(11) NOT NULL auto_increment,
  `following_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8B0ED71DA677582F` (`user_id`),
  KEY `FK8B0ED71D461B93A9` (`following_id`),
  CONSTRAINT `FK8B0ED71D461B93A9` FOREIGN KEY (`following_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK8B0ED71DA677582F` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "user_following"
#

INSERT INTO `user_following` VALUES (1,1,2),(2,1,3),(3,2,1),(4,1,4),(5,1,5);
