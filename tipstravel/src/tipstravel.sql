# Host: localhost  (Version: 5.6.17)
# Date: 2015-05-04 23:38:47
# Generator: MySQL-Front 5.3  (Build 4.205)

/*!40101 SET NAMES utf8 */;

#
# Data for table "tag"
#

REPLACE INTO `tag` (`tag_id`,`tag_name`) VALUES (1,'海洋'),(2,'山脉'),(3,'沙漠');

#
# Data for table "user"
#

REPLACE INTO `user` (`user_id`,`email`,`message`,`password`,`username`) VALUES (1,'fangbaby93@gmail.com',NULL,'admin','admin'),(2,NULL,NULL,'123','testuser1'),(3,NULL,NULL,'123','testuser2'),(4,NULL,NULL,'123','testuser4');

#
# Data for table "message"
#

REPLACE INTO `message` (`message_id`,`context`,`image`,`message_date`,`user_id`) VALUES (1,'test1','/image/test.jpg','00:00:01',2),(2,'test2','/image/test.jpg','00:00:00',2),(3,'test3','/image/test.jpg','00:00:02',2),(4,'test4','/image/test.jpg','00:00:03',2),(5,'test5','/image/test.jpg','00:00:04',2),(6,'test6','/image/test.jpg','00:00:06',2),(7,'test7','/image/test.jpg','00:00:07',2),(8,'test8','/image/test.jpg','00:00:01',2),(9,'test9','/image/test.jpg','00:00:01',2),(10,'test10','/image/test.jpg','00:00:01',1),(11,'test11','/image/test.jpg','00:00:20',2);

#
# Data for table "tag_message"
#

REPLACE INTO `tag_message` (`id`,`message_id`,`tag_id`) VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,3),(11,11,2);

#
# Data for table "like"
#


#
# Data for table "user_following"
#

REPLACE INTO `user_following` (`id`,`following_id`,`user_id`) VALUES (1,1,2),(2,1,3),(3,2,1);
