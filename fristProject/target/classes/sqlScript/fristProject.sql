/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.24 : Database - fristproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fristproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fristproject`;

/*Table structure for table `guest_tab` */

DROP TABLE IF EXISTS `guest_tab`;

CREATE TABLE `guest_tab` (
  `guestid` int(8) NOT NULL AUTO_INCREMENT,
  `guestname` varchar(50) DEFAULT NULL,
  `guestidcard` varchar(50) DEFAULT NULL,
  `guestphone` varchar(50) DEFAULT NULL,
  `guestaddress` varchar(50) DEFAULT NULL,
  `guestsex` varchar(50) DEFAULT NULL,
  `guestimg` mediumtext,
  `guestroomid` int(8) DEFAULT NULL,
  `guestroomname` varchar(50) DEFAULT NULL COMMENT '房间号',
  `guestroomprice` double DEFAULT NULL COMMENT '房价',
  `guestroomvip` double DEFAULT NULL COMMENT '折扣',
  `guestroomtotleprice` double DEFAULT NULL COMMENT '折后房价',
  `gueststate` int(8) DEFAULT NULL,
  `guestprice` double DEFAULT NULL,
  `guestremark` varchar(50) DEFAULT NULL,
  `guestbooktime` datetime DEFAULT NULL,
  `guestcometime` datetime DEFAULT NULL,
  `guestleavetime` datetime DEFAULT NULL,
  `guestchickouttime` datetime DEFAULT NULL COMMENT '超时时间',
  `timeoutprice` double DEFAULT NULL COMMENT '超时收费',
  `timeouttime` int(8) DEFAULT NULL,
  `guestcash` double DEFAULT NULL COMMENT '押金',
  `guestcomepayee` varchar(50) DEFAULT NULL COMMENT '入住收款人',
  `guestleavepayee` varchar(50) DEFAULT NULL COMMENT '退房操作员',
  `guestcashreturn` int(8) DEFAULT NULL COMMENT '押金是否退还',
  `guestcashremark` mediumtext COMMENT '押金退还备注',
  `guestcashremarkimg1` mediumtext COMMENT '不予退还原因图片',
  `guestcashremarkimg2` mediumtext,
  `guestcashremarkimg3` mediumtext,
  `guestcashremarkimg4` mediumtext,
  `guestreviewusing` int(8) DEFAULT '0' COMMENT '是否启用评论0不可以评论',
  `guestreview` mediumtext COMMENT '评论',
  `guestreviewimg1` mediumtext COMMENT '评论上传的图片',
  `guestreviewimg2` mediumtext,
  `guestreviewimg3` mediumtext,
  PRIMARY KEY (`guestid`),
  KEY `guestroomid` (`guestroomid`),
  KEY `gueststate` (`gueststate`),
  CONSTRAINT `guest_tab_ibfk_1` FOREIGN KEY (`guestroomid`) REFERENCES `room_tab` (`roomid`),
  CONSTRAINT `guest_tab_ibfk_2` FOREIGN KEY (`gueststate`) REFERENCES `gueststate_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `guest_tab` */

insert  into `guest_tab`(`guestid`,`guestname`,`guestidcard`,`guestphone`,`guestaddress`,`guestsex`,`guestimg`,`guestroomid`,`guestroomname`,`guestroomprice`,`guestroomvip`,`guestroomtotleprice`,`gueststate`,`guestprice`,`guestremark`,`guestbooktime`,`guestcometime`,`guestleavetime`,`guestchickouttime`,`timeoutprice`,`timeouttime`,`guestcash`,`guestcomepayee`,`guestleavepayee`,`guestcashreturn`,`guestcashremark`,`guestcashremarkimg1`,`guestcashremarkimg2`,`guestcashremarkimg3`,`guestcashremarkimg4`,`guestreviewusing`,`guestreview`,`guestreviewimg1`,`guestreviewimg2`,`guestreviewimg3`) values (23,'张三','610628199908294589','15895276854',NULL,'男','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFMAAAA/CAMAAACrWgQTAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAC2VBMVEUAAADwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1nwZ1n///8EX0fsAAAA8XRSTlMAAAYqX5fD3O358t/Jn2cuByxmyuDz/O/EmCkUWanh++OqVQ0MUqji+gtT+LA7Ajms9wEzrvXpfxAP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',1,'101',88,0.8,77,1,77,NULL,NULL,'2017-02-08 12:28:23','2017-02-10 12:00:00',NULL,NULL,NULL,NULL,'177',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `gueststate_tab` */

DROP TABLE IF EXISTS `gueststate_tab`;

CREATE TABLE `gueststate_tab` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `statename` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `gueststate_tab` */

insert  into `gueststate_tab`(`id`,`statename`) values (1,'已预约'),(2,'已入住'),(3,'一离开'),(4,'未入住');

/*Table structure for table `log_tab` */

DROP TABLE IF EXISTS `log_tab`;

CREATE TABLE `log_tab` (
  `logid` int(8) NOT NULL AUTO_INCREMENT,
  `userid` int(8) NOT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `name1` varchar(50) DEFAULT NULL,
  `operation` varchar(100) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `logoutTime` datetime DEFAULT NULL,
  PRIMARY KEY (`logid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=432 DEFAULT CHARSET=utf8;

/*Data for the table `log_tab` */

insert  into `log_tab`(`logid`,`userid`,`ip`,`name`,`name1`,`operation`,`operationTime`,`loginTime`,`logoutTime`) values (151,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 22:25:05',NULL),(152,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 22:31:54',NULL),(153,1,NULL,'admin',NULL,'登录成功',NULL,'2016-11-22 22:33:59',NULL),(154,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 22:38:20',NULL),(155,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 22:40:11',NULL),(156,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 22:52:36',NULL),(157,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 23:00:36',NULL),(158,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-22 23:07:47',NULL),(159,1,'192.168.1.111','admin',NULL,'注销登录',NULL,'2016-11-22 23:07:47','2016-11-22 23:07:57'),(160,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-23 21:54:05',NULL),(161,1,'192.168.1.111','admin',NULL,'注销登录',NULL,'2016-11-23 21:54:05','2016-11-23 22:15:27'),(162,1,'192.168.1.111','admin',NULL,'登录成功',NULL,'2016-11-23 23:35:37',NULL),(163,1,'192.168.1.111','admin',NULL,'注销登录',NULL,'2016-11-23 23:35:37','2016-11-23 23:36:52'),(164,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-28 21:47:14',NULL),(165,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-28 23:08:21',NULL),(166,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-28 23:28:06',NULL),(167,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-28 23:31:10',NULL),(168,1,'192.168.31.139','admin',NULL,'修改密码','2016-11-29 00:08:50','2016-11-28 23:31:10',NULL),(169,1,'192.168.31.139','admin',NULL,'注销登录','2016-11-29 00:08:50','2016-11-28 23:31:10','2016-11-29 00:08:53'),(170,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-29 21:58:58',NULL),(171,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-29 21:58:58','2016-11-29 23:29:05'),(172,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-29 23:29:47',NULL),(173,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-29 23:29:47','2016-11-29 23:31:26'),(174,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-29 23:32:14',NULL),(175,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-29 23:32:14','2016-11-29 23:32:33'),(176,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-29 23:32:56',NULL),(177,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-29 23:32:56','2016-11-29 23:33:03'),(178,2,'192.168.31.139','root',NULL,'登录成功',NULL,'2016-11-29 23:33:33',NULL),(179,2,'192.168.31.139','root',NULL,'注销登录',NULL,'2016-11-29 23:33:33','2016-11-29 23:34:11'),(180,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 21:43:53',NULL),(181,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 21:59:29',NULL),(182,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 21:43:53','2016-11-30 22:00:36'),(183,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 21:59:29','2016-11-30 22:00:52'),(184,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:01:06',NULL),(185,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 22:01:06','2016-11-30 22:02:02'),(186,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:02:15',NULL),(187,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 22:02:15','2016-11-30 22:02:20'),(188,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:02:35',NULL),(189,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:02:50',NULL),(190,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 22:02:50','2016-11-30 22:02:56'),(191,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:03:14',NULL),(192,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 22:02:35','2016-11-30 22:03:23'),(193,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:10:03',NULL),(194,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-11-30 22:11:30',NULL),(195,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-11-30 22:11:30','2016-11-30 22:13:35'),(196,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 21:36:42',NULL),(197,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 21:51:30',NULL),(198,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 22:01:52',NULL),(199,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 22:57:22',NULL),(200,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 23:00:11',NULL),(201,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 23:02:02',NULL),(202,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-13 23:06:26',NULL),(203,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 21:19:51',NULL),(204,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 21:31:28',NULL),(205,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 22:26:52',NULL),(206,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 22:30:52',NULL),(207,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 22:32:37',NULL),(208,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 22:34:45',NULL),(209,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 23:11:00',NULL),(210,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-15 23:18:30',NULL),(211,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 21:38:05',NULL),(212,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 22:39:29',NULL),(213,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 23:13:34',NULL),(214,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 23:15:35',NULL),(215,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 23:19:41',NULL),(216,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 23:22:02',NULL),(217,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-16 23:26:16',NULL),(218,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 00:09:43',NULL),(219,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 00:40:11',NULL),(220,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-17 00:40:11','2016-12-17 00:40:17'),(221,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 00:44:56',NULL),(222,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 01:00:00',NULL),(223,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 01:10:39',NULL),(224,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 13:51:24',NULL),(225,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 15:20:14',NULL),(226,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 15:21:58',NULL),(227,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-17 15:21:58','2016-12-17 15:30:53'),(228,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 15:31:02',NULL),(229,1,'192.168.31.85','admin',NULL,'登录成功',NULL,'2016-12-17 18:03:40',NULL),(230,1,'192.168.31.210','admin',NULL,'登录成功',NULL,'2016-12-17 18:22:10',NULL),(231,1,'192.168.31.210','admin',NULL,'注销登录',NULL,'2016-12-17 18:22:10','2016-12-17 18:23:56'),(232,1,'192.168.31.85','admin',NULL,'登录成功',NULL,'2016-12-17 18:47:48',NULL),(233,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-17 20:49:15',NULL),(234,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-17 20:49:15','2016-12-17 23:54:58'),(235,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:09:04',NULL),(236,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:09:04','2016-12-18 00:09:07'),(237,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:12:14',NULL),(238,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:12:14','2016-12-18 00:12:20'),(239,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:13:53',NULL),(240,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:13:53','2016-12-18 00:14:08'),(241,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:15:31',NULL),(242,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:15:31','2016-12-18 00:15:35'),(243,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:27:00',NULL),(244,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:27:00','2016-12-18 00:27:03'),(245,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:28:06',NULL),(246,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:28:06','2016-12-18 00:28:11'),(247,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:29:38',NULL),(248,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:29:38','2016-12-18 00:29:41'),(249,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:29:59',NULL),(250,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:29:59','2016-12-18 00:34:39'),(251,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:35:29',NULL),(252,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:35:29','2016-12-18 00:38:06'),(253,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:40:06',NULL),(254,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:40:06','2016-12-18 00:40:08'),(255,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:40:33',NULL),(256,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:40:33','2016-12-18 00:41:11'),(257,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:41:48',NULL),(258,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:41:48','2016-12-18 00:41:56'),(259,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:47:48',NULL),(260,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:47:48','2016-12-18 00:47:50'),(261,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:49:16',NULL),(262,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:49:16','2016-12-18 00:49:23'),(263,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 00:58:30',NULL),(264,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 00:58:30','2016-12-18 00:59:38'),(265,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 01:11:46',NULL),(266,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 01:11:46','2016-12-18 01:11:49'),(267,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 01:14:33',NULL),(268,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 01:14:33','2016-12-18 01:20:29'),(269,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 19:20:36',NULL),(270,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 19:20:36','2016-12-18 20:06:53'),(271,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:07:01',NULL),(272,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-18 20:07:01','2016-12-18 20:17:13'),(273,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:17:20',NULL),(274,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:20:29',NULL),(275,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:30:27',NULL),(276,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:41:44',NULL),(277,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:46:03',NULL),(278,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 20:52:47',NULL),(279,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:01:47',NULL),(280,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:06:22',NULL),(281,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:08:27',NULL),(282,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:18:50',NULL),(283,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:48:03',NULL),(284,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:52:00',NULL),(285,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 21:54:42',NULL),(286,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 22:23:46',NULL),(287,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-18 22:45:21',NULL),(288,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-19 23:08:57',NULL),(289,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-19 23:13:07',NULL),(290,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-19 23:14:32',NULL),(291,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-19 23:28:03',NULL),(292,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 20:59:41',NULL),(293,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 21:18:42',NULL),(294,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 21:52:27',NULL),(295,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 21:54:56',NULL),(296,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 21:56:00',NULL),(297,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 21:57:33',NULL),(298,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-20 21:57:33','2016-12-20 22:01:21'),(299,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 22:01:29',NULL),(300,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-20 22:03:00',NULL),(301,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-21 21:43:17',NULL),(302,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 10:28:45',NULL),(303,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-27 10:28:45','2016-12-27 10:48:30'),(304,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 10:48:38',NULL),(305,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-27 10:48:38','2016-12-27 10:49:10'),(306,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 10:49:18',NULL),(307,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 10:55:06',NULL),(308,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 11:01:08',NULL),(309,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 11:49:25',NULL),(310,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 12:34:51',NULL),(311,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 12:41:30',NULL),(312,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2016-12-27 12:41:30','2016-12-27 12:53:28'),(313,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 12:53:36',NULL),(314,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 12:57:05',NULL),(315,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 14:18:47',NULL),(316,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-27 14:37:23',NULL),(317,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2016-12-28 16:32:58',NULL),(318,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 21:43:41',NULL),(319,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-01-04 21:43:41','2017-01-04 22:09:46'),(320,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:09:56',NULL),(321,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:11:23',NULL),(322,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:13:30',NULL),(323,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:16:57',NULL),(324,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:19:47',NULL),(325,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:22:39',NULL),(326,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:36:19',NULL),(327,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:52:03',NULL),(328,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 22:59:57',NULL),(329,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 23:01:15',NULL),(330,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 23:03:55',NULL),(331,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-01-04 23:03:55','2017-01-04 23:21:00'),(332,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-04 23:21:08',NULL),(333,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-17 21:45:42',NULL),(334,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-17 21:55:17',NULL),(335,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-01-17 21:55:17','2017-01-17 22:04:42'),(336,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-17 22:04:56',NULL),(337,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-01-17 22:04:56','2017-01-17 22:12:38'),(338,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-17 22:12:45',NULL),(339,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-18 23:21:14',NULL),(340,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-01-18 23:21:14','2017-01-18 23:59:14'),(341,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-19 23:11:33',NULL),(342,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-19 23:35:16','2017-01-19 23:11:33',NULL),(343,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-20 22:53:04',NULL),(344,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-20 22:54:22','2017-01-20 22:53:04',NULL),(345,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-20 23:19:07','2017-01-20 22:53:04',NULL),(346,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-22 22:41:49',NULL),(347,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-22 22:47:14',NULL),(348,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-22 22:58:44',NULL),(349,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-23 21:39:57',NULL),(350,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-23 21:41:35','2017-01-23 21:39:57',NULL),(351,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-23 21:49:11','2017-01-23 21:39:57',NULL),(352,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-23 22:29:02',NULL),(353,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-23 23:03:36','2017-01-23 22:29:02',NULL),(354,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-24 21:37:25',NULL),(355,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-24 22:02:21','2017-01-24 21:37:25',NULL),(356,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-24 23:21:18',NULL),(357,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-24 23:22:02','2017-01-24 23:21:18',NULL),(358,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-24 23:26:11','2017-01-24 23:21:18',NULL),(359,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-01-24 23:33:36',NULL),(360,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-01-24 23:34:45','2017-01-24 23:33:36',NULL),(361,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-03 21:23:19',NULL),(362,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-02-03 21:27:27','2017-02-03 21:23:19',NULL),(363,1,'192.168.31.139','admin',NULL,'注销登录','2017-02-03 21:27:27','2017-02-03 21:23:19','2017-02-03 21:28:08'),(364,2,'192.168.31.139','root',NULL,'登录成功',NULL,'2017-02-03 21:28:46',NULL),(365,2,'192.168.31.139','root',NULL,'注销登录',NULL,'2017-02-03 21:28:46','2017-02-03 21:29:02'),(366,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-03 21:29:16',NULL),(367,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-07 21:54:47',NULL),(368,1,'192.168.31.139','admin',NULL,'接待  张三 : 610628199908294589 客户入住','2017-02-07 22:11:55','2017-02-07 21:54:47',NULL),(369,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-08 22:11:16',NULL),(370,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-09 21:48:57',NULL),(371,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-21 22:29:43',NULL),(372,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-21 23:11:19',NULL),(373,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 00:05:00',NULL),(374,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 00:30:15',NULL),(375,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 20:15:22',NULL),(376,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 23:03:09',NULL),(377,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 23:05:04',NULL),(378,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 23:07:02',NULL),(379,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 23:13:59',NULL),(380,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-22 23:22:51',NULL),(381,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-23 22:56:54',NULL),(382,2,'192.168.31.139','root',NULL,'登录成功',NULL,'2017-02-24 22:13:54',NULL),(383,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 22:15:07',NULL),(384,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 22:24:34',NULL),(385,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 22:53:15',NULL),(386,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 22:57:52',NULL),(387,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 23:08:17',NULL),(388,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 23:14:24',NULL),(389,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 23:29:38',NULL),(390,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-24 23:43:31',NULL),(391,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-24 23:43:31','2017-02-24 23:43:46'),(392,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-24 22:57:52','2017-02-25 00:03:10'),(393,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 09:31:09',NULL),(394,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 09:34:50',NULL),(395,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 09:34:50','2017-02-25 09:35:06'),(396,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 09:36:44',NULL),(397,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:06:12',NULL),(398,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:10:05',NULL),(399,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:12:36',NULL),(400,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:14:47',NULL),(401,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:23:55',NULL),(402,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:29:52',NULL),(403,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:31:48',NULL),(404,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:47:07',NULL),(405,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:48:00',NULL),(406,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:55:02',NULL),(407,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 11:58:50',NULL),(408,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 11:58:50','2017-02-25 11:59:05'),(409,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 12:11:24',NULL),(410,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 12:11:24','2017-02-25 12:11:39'),(411,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 12:13:46',NULL),(412,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 12:13:46','2017-02-25 12:14:08'),(413,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 12:17:59',NULL),(414,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 12:17:59','2017-02-25 12:18:21'),(415,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 12:35:45',NULL),(416,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 12:35:45','2017-02-25 12:36:17'),(417,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 20:08:49',NULL),(418,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-25 20:08:49','2017-02-25 20:09:22'),(419,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 20:38:14',NULL),(420,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 22:21:38',NULL),(421,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-25 22:27:57',NULL),(422,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-26 23:00:52',NULL),(423,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-26 23:13:16',NULL),(424,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-26 23:17:13',NULL),(425,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-26 23:29:04',NULL),(426,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-28 22:15:11',NULL),(427,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-02-28 22:15:11','2017-02-28 22:16:37'),(428,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-02-28 22:18:55',NULL),(429,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-03-04 23:09:06',NULL),(430,1,'192.168.31.139','admin',NULL,'注销登录',NULL,'2017-03-04 23:09:06','2017-03-04 23:11:04'),(431,1,'192.168.31.139','admin',NULL,'登录成功',NULL,'2017-03-05 16:02:55',NULL);

/*Table structure for table `path_tab` */

DROP TABLE IF EXISTS `path_tab`;

CREATE TABLE `path_tab` (
  `path` varchar(20) NOT NULL,
  `path_name` varchar(20) NOT NULL,
  `url` varchar(20) NOT NULL,
  `state` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `path_tab` */

insert  into `path_tab`(`path`,`path_name`,`url`,`state`) values ('index','hotel','/hotel/index',0),('index','hotel','/hotel/home',0),('index','hote1','/hotel/login',1);

/*Table structure for table `right_tab` */

DROP TABLE IF EXISTS `right_tab`;

CREATE TABLE `right_tab` (
  `rightid` int(11) NOT NULL AUTO_INCREMENT,
  `folderid` int(11) DEFAULT NULL,
  `rightname` varchar(50) DEFAULT NULL,
  `righturl` varchar(50) DEFAULT NULL,
  `rightcode` varchar(50) DEFAULT NULL,
  `rightparam` varchar(50) DEFAULT NULL,
  `rightmemo` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rightid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `right_tab` */

insert  into `right_tab`(`rightid`,`folderid`,`rightname`,`righturl`,`rightcode`,`rightparam`,`rightmemo`,`timestamp`) values (1,1,'系统维护',NULL,'sys:config',NULL,NULL,'2016-11-05 17:13:36'),(2,2,'权限维护',NULL,'sys:role',NULL,NULL,'2016-11-05 17:15:59'),(3,3,'权限分配',NULL,'sys:right',NULL,NULL,'2016-11-05 17:16:38'),(4,4,'财务管理',NULL,'sys:cwgl',NULL,NULL,'2016-11-05 17:19:32');

/*Table structure for table `role_right` */

DROP TABLE IF EXISTS `role_right`;

CREATE TABLE `role_right` (
  `roleid` int(11) DEFAULT NULL,
  `rightid` int(11) DEFAULT NULL,
  KEY `roleid` (`roleid`),
  KEY `rightid` (`rightid`),
  CONSTRAINT `rightid` FOREIGN KEY (`rightid`) REFERENCES `right_tab` (`rightid`),
  CONSTRAINT `roleid` FOREIGN KEY (`roleid`) REFERENCES `role_tab` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_right` */

insert  into `role_right`(`roleid`,`rightid`) values (1,1),(1,2),(1,3),(1,4),(2,4);

/*Table structure for table `role_tab` */

DROP TABLE IF EXISTS `role_tab`;

CREATE TABLE `role_tab` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) DEFAULT NULL,
  `rolecode` varchar(50) DEFAULT NULL,
  `roleparam` varchar(50) DEFAULT NULL,
  `rolememo` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role_tab` */

insert  into `role_tab`(`roleid`,`rolename`,`rolecode`,`roleparam`,`rolememo`,`timestamp`) values (1,'系统管理员','1',NULL,NULL,'2016-11-05 17:08:03'),(2,'财务管理员','2',NULL,NULL,'2016-11-05 17:08:48');

/*Table structure for table `room_tab` */

DROP TABLE IF EXISTS `room_tab`;

CREATE TABLE `room_tab` (
  `roomid` int(8) NOT NULL AUTO_INCREMENT,
  `roomsnum` varchar(50) DEFAULT NULL,
  `roomstate` int(8) DEFAULT NULL,
  `roomprice` double DEFAULT NULL,
  `roomvip` double DEFAULT NULL,
  `roomclass` int(8) NOT NULL,
  `roomname` varchar(50) DEFAULT NULL,
  `roomfirmprice` double NOT NULL,
  `roomCash` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`roomid`),
  KEY `roomstate` (`roomstate`),
  CONSTRAINT `room_tab_ibfk_1` FOREIGN KEY (`roomstate`) REFERENCES `state_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `room_tab` */

insert  into `room_tab`(`roomid`,`roomsnum`,`roomstate`,`roomprice`,`roomvip`,`roomclass`,`roomname`,`roomfirmprice`,`roomCash`) values (1,'101',2,99,0.8,1,'总统客房',87,100),(2,'201',1,88,0.8,2,'豪华客房',77,100),(3,'303',1,77,0.8,3,'商务客房',67,100),(4,'401',1,66,0.8,4,'标准间',57,100),(5,'501',1,55,0.8,5,'普通客房',47,100);

/*Table structure for table `roomtype_tab` */

DROP TABLE IF EXISTS `roomtype_tab`;

CREATE TABLE `roomtype_tab` (
  `roomclass` int(8) NOT NULL,
  `roomname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roomtype_tab` */

insert  into `roomtype_tab`(`roomclass`,`roomname`) values (1,'总统客房'),(2,'豪华客房'),(3,'商务客房'),(4,'标准间'),(5,'普通客房'),(0,'全部客房');

/*Table structure for table `state_tab` */

DROP TABLE IF EXISTS `state_tab`;

CREATE TABLE `state_tab` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `statename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `state_tab` */

insert  into `state_tab`(`id`,`statename`) values (1,'空闲'),(2,'已入住'),(3,'已离开'),(4,'已预订');

/*Table structure for table `step_tab` */

DROP TABLE IF EXISTS `step_tab`;

CREATE TABLE `step_tab` (
  `stepId` int(8) NOT NULL AUTO_INCREMENT,
  `stepparent` int(8) DEFAULT NULL,
  `stepname` varchar(50) DEFAULT NULL,
  `stepurl` varchar(50) DEFAULT NULL,
  `class` int(8) DEFAULT NULL,
  `state` int(8) DEFAULT '0',
  `addition` mediumtext,
  `addition2` mediumtext,
  PRIMARY KEY (`stepId`),
  KEY `stepparent` (`stepparent`),
  CONSTRAINT `step_tab_ibfk_1` FOREIGN KEY (`stepparent`) REFERENCES `step_tab` (`stepId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `step_tab` */

insert  into `step_tab`(`stepId`,`stepparent`,`stepname`,`stepurl`,`class`,`state`,`addition`,`addition2`) values (1,NULL,'宾馆管理系统',NULL,NULL,0,NULL,NULL),(2,1,'网站首页','/font/index.do',NULL,0,NULL,NULL),(3,1,'后台首页','/back/home.do',NULL,0,NULL,NULL),(4,NULL,'客房管理','/back/room.do',NULL,0,NULL,NULL),(5,4,'客房分类管理',NULL,NULL,0,NULL,NULL),(7,4,'客房状态管理',NULL,NULL,0,NULL,NULL),(8,4,'客房价位管理',NULL,NULL,0,NULL,NULL),(9,NULL,'住宿人员信息管理',NULL,NULL,0,NULL,NULL),(10,9,'住宿时间',NULL,NULL,0,NULL,NULL),(11,9,'个人信息登记',NULL,NULL,0,NULL,NULL),(12,9,'消费记录',NULL,NULL,0,NULL,NULL),(13,NULL,'财务管理',NULL,NULL,0,NULL,NULL),(14,13,'收款列表',NULL,NULL,0,NULL,NULL),(16,NULL,'员工管理',NULL,NULL,0,NULL,NULL),(17,16,'添加员工',NULL,NULL,0,NULL,NULL),(18,16,'员工分组',NULL,NULL,0,NULL,NULL),(19,16,'角色管理',NULL,NULL,0,NULL,NULL),(20,16,'权限管理',NULL,NULL,0,NULL,NULL),(22,NULL,'今日房价信息',NULL,NULL,1,NULL,NULL),(23,22,'全部客房','/ajax/selectAllRoom.do',220,1,NULL,NULL),(24,22,'普通客房','/ajax/selectAllRoom.do',225,1,NULL,NULL),(25,22,'标准客房','/ajax/selectAllRoom.do',224,1,NULL,NULL),(26,22,'商务客房','/ajax/selectAllRoom.do',223,1,NULL,NULL),(27,22,'豪华客房','/ajax/selectAllRoom.do',222,1,NULL,NULL),(28,22,'总统客房','/ajax/selectAllRoom.do',221,1,NULL,NULL),(29,NULL,'酒店入住信息',NULL,NULL,1,NULL,NULL),(30,29,'已预订客房','/ajax/checkinRoominfo.do',294,1,NULL,NULL),(31,29,'已入住客房','/ajax/checkinRoominfo.do',292,1,NULL,NULL),(32,29,'空闲客房','/ajax/checkinRoominfo.do',291,1,NULL,NULL),(33,NULL,'酒店销售报表',NULL,NULL,1,NULL,NULL),(34,33,'最近一个月','/ajax/ReportRoominfo.do',3330,1,NULL,NULL),(35,NULL,'宾馆管理系统','宾馆管理系统(version=1.0.0)',NULL,10,'宾馆欢迎您登录','系统将在2016年11月18日晚上五点到2016年11月19日凌晨五点进行维护，再次期间任何人将不能登录该系统请提前完成所有工作！'),(36,33,'最近三个月','/ajax/ReportRoominfo.do',3390,1,NULL,NULL),(37,33,'最近一年','/ajax/ReportRoominfo.do',33365,1,NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  KEY `userid` (`userid`),
  KEY `role` (`roleid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user_tab` (`userid`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role_tab` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`userid`,`roleid`) values (1,1),(2,2);

/*Table structure for table `user_tab` */

DROP TABLE IF EXISTS `user_tab`;

CREATE TABLE `user_tab` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `creatortime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` varchar(50) DEFAULT NULL,
  `idCard` varchar(50) DEFAULT NULL,
  `phoneNum` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user_tab` */

insert  into `user_tab`(`userid`,`username`,`password`,`name`,`creator`,`creatortime`,`comment`,`idCard`,`phoneNum`,`address`,`state`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','系统','system','2016-11-29 00:08:50','超级管理员',NULL,NULL,NULL,1),(2,'root','e10adc3949ba59abbe56e057f20f883e','张三','admin','2016-11-17 23:38:26','财务管理员',NULL,NULL,NULL,1);

/*Table structure for table `weather_tab` */

DROP TABLE IF EXISTS `weather_tab`;

CREATE TABLE `weather_tab` (
  `Data_id` date NOT NULL,
  `city` varchar(100) NOT NULL,
  `temperature` varchar(50) NOT NULL,
  `picture1` varchar(50) DEFAULT NULL,
  `picture2` varchar(50) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `weather_tab` */

/*Table structure for table `room_state_view` */

DROP TABLE IF EXISTS `room_state_view`;

/*!50001 DROP VIEW IF EXISTS `room_state_view` */;
/*!50001 DROP TABLE IF EXISTS `room_state_view` */;

/*!50001 CREATE TABLE  `room_state_view`(
 `roomid` int(8) ,
 `roomsnum` varchar(50) ,
 `roomstate` int(8) ,
 `roomprice` double ,
 `roomvip` double ,
 `roomclass` int(8) ,
 `roomname` varchar(50) ,
 `roomfirmprice` double ,
 `roomCash` double ,
 `statename` varchar(50) 
)*/;

/*Table structure for table `user_right_view` */

DROP TABLE IF EXISTS `user_right_view`;

/*!50001 DROP VIEW IF EXISTS `user_right_view` */;
/*!50001 DROP TABLE IF EXISTS `user_right_view` */;

/*!50001 CREATE TABLE  `user_right_view`(
 `username` varchar(50) ,
 `name` varchar(50) ,
 `rolename` varchar(50) ,
 `rightname` varchar(50) ,
 `rightcode` varchar(50) 
)*/;

/*Table structure for table `user_role_view` */

DROP TABLE IF EXISTS `user_role_view`;

/*!50001 DROP VIEW IF EXISTS `user_role_view` */;
/*!50001 DROP TABLE IF EXISTS `user_role_view` */;

/*!50001 CREATE TABLE  `user_role_view`(
 `username` varchar(50) ,
 `name` varchar(50) ,
 `rolename` varchar(50) ,
 `roleid` int(11) 
)*/;

/*View structure for view room_state_view */

/*!50001 DROP TABLE IF EXISTS `room_state_view` */;
/*!50001 DROP VIEW IF EXISTS `room_state_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `room_state_view` AS select `room_tab`.`roomid` AS `roomid`,`room_tab`.`roomsnum` AS `roomsnum`,`room_tab`.`roomstate` AS `roomstate`,`room_tab`.`roomprice` AS `roomprice`,`room_tab`.`roomvip` AS `roomvip`,`room_tab`.`roomclass` AS `roomclass`,`room_tab`.`roomname` AS `roomname`,`room_tab`.`roomfirmprice` AS `roomfirmprice`,`room_tab`.`roomCash` AS `roomCash`,`state_tab`.`statename` AS `statename` from (`room_tab` join `state_tab` on((`room_tab`.`roomstate` = `state_tab`.`id`))) */;

/*View structure for view user_right_view */

/*!50001 DROP TABLE IF EXISTS `user_right_view` */;
/*!50001 DROP VIEW IF EXISTS `user_right_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `user_right_view` AS (select `user_role_view`.`username` AS `username`,`user_role_view`.`name` AS `name`,`user_role_view`.`rolename` AS `rolename`,`right_tab`.`rightname` AS `rightname`,`right_tab`.`rightcode` AS `rightcode` from ((`user_role_view` join `right_tab`) join `role_right`) where ((`user_role_view`.`roleid` = `role_right`.`roleid`) and (`role_right`.`rightid` = `right_tab`.`rightid`))) */;

/*View structure for view user_role_view */

/*!50001 DROP TABLE IF EXISTS `user_role_view` */;
/*!50001 DROP VIEW IF EXISTS `user_role_view` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=``@`` SQL SECURITY DEFINER VIEW `user_role_view` AS (select `user_tab`.`username` AS `username`,`user_tab`.`name` AS `name`,`role_tab`.`rolename` AS `rolename`,`role_tab`.`roleid` AS `roleid` from ((`user_tab` join `role_tab`) join `user_role`) where ((`user_tab`.`userid` = `user_role`.`userid`) and (`user_role`.`roleid` = `role_tab`.`roleid`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
