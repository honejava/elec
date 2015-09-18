/*
SQLyog Enterprise - MySQL GUI v6.14
MySQL - 5.0.22-community-nt : Database - elec
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `elec`;

USE `elec`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `elec_adjust` */

DROP TABLE IF EXISTS `elec_adjust`;

CREATE TABLE `elec_adjust` (
  `adjustID` int(11) NOT NULL,
  `equapmentID` int(11) default NULL,
  `jctID` varchar(255) default NULL,
  `devName` varchar(255) default NULL,
  `adjustPeriod` varchar(255) default NULL,
  `apunit` varchar(255) default NULL,
  `useDate` date default NULL,
  `devType` varchar(255) default NULL,
  `startDate` date default NULL,
  `isHaving` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  `record` varchar(255) default NULL,
  PRIMARY KEY  (`adjustID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_adjust` */

insert  into `elec_adjust`(`adjustID`,`equapmentID`,`jctID`,`devName`,`adjustPeriod`,`apunit`,`useDate`,`devType`,`startDate`,`isHaving`,`comment`,`record`) values (6,2,'1','ç¬”è®°æœ¬ç”µè„‘','1','æœˆ','2014-05-04','2','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(7,3,'4','Iphone4','1','æœˆ','2014-05-04','3','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(8,4,'1','word2007','1','æœˆ','2014-05-04','5','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(9,5,'1','ç”µè§†æœº','1','æœˆ','2014-05-04','6','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(10,6,'1','office','1','æœˆ','2014-05-04','5','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(11,7,'3','è”æƒ³','1','æœˆ','2014-05-04','3','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(13,9,'4','ä¸œèŠ','1','æœˆ','2014-05-04','3','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(15,11,'4','é¿é›·é’ˆ','2','æœˆ','2014-05-04','4','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(16,12,'1','ç¬”è®°æœ¬ç”µè„‘','1','æœˆ','2014-05-04','2','2014-05-05','2','hhhhh','å‘µå‘µå‘µ'),(17,1,'4','usbç”µè„‘è¿æ¥æ¥å£','1','æœˆ','2014-05-04','2','2014-05-05','2','æœ‰æ„ä¹‰','åŒ»é™¢'),(18,1,'4','usbç”µè„‘è¿æ¥æ¥å£','1','æœˆ','2014-05-04','2','2014-05-05','2','adjustID','adjustID'),(19,1,'4','usbç”µè„‘è¿æ¥æ¥å£','1','æœˆ','2014-05-04','2','2014-05-05','2','','');

/*Table structure for table `elec_application` */

DROP TABLE IF EXISTS `elec_application`;

CREATE TABLE `elec_application` (
  `applicationID` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  `path` varchar(255) default NULL,
  `applyTime` datetime default NULL,
  `status` varchar(255) default NULL,
  `processInstanceID` varchar(255) default NULL,
  `applicationTemplateID` int(11) default NULL,
  `applicationUserID` varchar(255) default NULL,
  PRIMARY KEY  (`applicationID`),
  KEY `FKA907BB962E934728` (`applicationTemplateID`),
  KEY `FKA907BB968F86D388` (`applicationUserID`),
  CONSTRAINT `FKA907BB962E934728` FOREIGN KEY (`applicationTemplateID`) REFERENCES `elec_application_template` (`id`),
  CONSTRAINT `FKA907BB968F86D388` FOREIGN KEY (`applicationUserID`) REFERENCES `elec_user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_application` */

/*Table structure for table `elec_application_template` */

DROP TABLE IF EXISTS `elec_application_template`;

CREATE TABLE `elec_application_template` (
  `id` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `processDefinitionKey` varchar(255) default NULL,
  `path` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_application_template` */

/*Table structure for table `elec_approveinfo` */

DROP TABLE IF EXISTS `elec_approveinfo`;

CREATE TABLE `elec_approveinfo` (
  `approveID` int(11) NOT NULL,
  `comment` varchar(255) default NULL,
  `approval` bit(1) default NULL,
  `approveTime` datetime default NULL,
  `applicationID` int(11) default NULL,
  `approveUserID` varchar(255) default NULL,
  PRIMARY KEY  (`approveID`),
  KEY `FK764523E1D1280994` (`applicationID`),
  KEY `FK764523E1EB349FC5` (`approveUserID`),
  CONSTRAINT `FK764523E1D1280994` FOREIGN KEY (`applicationID`) REFERENCES `elec_application` (`applicationID`),
  CONSTRAINT `FK764523E1EB349FC5` FOREIGN KEY (`approveUserID`) REFERENCES `elec_user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_approveinfo` */

/*Table structure for table `elec_bug` */

DROP TABLE IF EXISTS `elec_bug`;

CREATE TABLE `elec_bug` (
  `bugID` int(11) NOT NULL,
  `stationID` int(11) default NULL,
  `bugType` varchar(255) default NULL,
  `occurDate` date default NULL,
  `produceHome` varchar(255) default NULL,
  `bugDescribe` varchar(255) default NULL,
  `resolveDate` date default NULL,
  `resolveMethod` varchar(255) default NULL,
  `btnResolve` varchar(255) default NULL,
  `bugReason` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  `sbYear` varchar(255) default NULL,
  `sbMonth` varchar(255) default NULL,
  PRIMARY KEY  (`bugID`),
  KEY `FK2CADABA9416660A` (`stationID`),
  CONSTRAINT `FK2CADABA9416660A` FOREIGN KEY (`stationID`) REFERENCES `elec_station` (`stationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_bug` */

insert  into `elec_bug`(`bugID`,`stationID`,`bugType`,`occurDate`,`produceHome`,`bugDescribe`,`resolveDate`,`resolveMethod`,`btnResolve`,`bugReason`,`comment`,`sbYear`,`sbMonth`) values (1,1,'1','2014-05-30','åŒ—äº¬å¸‚','åŒ—äº¬åœç”µ','2014-05-30','å‘ç”µæœºå‘ç”µ',NULL,'ä¸¾å›½å“€æ‚¼','èƒ¡æ‰¯','2014','1'),(2,2,'3','2014-05-30','åŒ—äº¬å¸‚ä¼ æ™ºæ’­å®¢','é€šè®¯æ•…éšœ','2014-05-30','å›å¤é€šè®¯',NULL,'é€šè®¯è†¨èƒ€','å¢åŠ é€šè®¯å¸¦å®½','2014','2'),(3,3,'1','2014-05-15','ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’','ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’','2014-05-22','ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’',NULL,'ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’','ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’','2014','2'),(4,4,'1','2014-05-30','å£°éœ‡å¸‚äº”æœˆè®¡åˆ’','å£°éœ‡å¸‚äº”æœˆè®¡åˆ’','2014-06-26','å£°éœ‡å¸‚äº”æœˆè®¡åˆ’',NULL,'å£°éœ‡å¸‚äº”æœˆè®¡åˆ’','å£°éœ‡å¸‚äº”æœˆè®¡åˆ’','2014','3'),(5,1,'1','2014-05-29','hhhhhhhhhh','hhhhhhhhhh','2014-05-30','hhhhhhhhhh',NULL,'hhhhhhhhhh','hhhhhhhhhh','2014','5'),(6,2,'2','2014-05-30','ä¿¡é˜³å¸‚ä¼Šå°”å’Œ','ä¿¡é˜³å¸‚ä¼Šå°”å’Œ','2014-05-30','ä¿¡é˜³å¸‚ä¼Šå°”å’Œ',NULL,'ä¿¡é˜³å¸‚ä¼Šå°”å’Œ','ä¿¡é˜³å¸‚ä¼Šå°”å’Œ','2014','5'),(7,1,'1','2014-05-30','æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2014-05-30','æ²³å—å·¥ä¸šå¤§å­¦',NULL,'æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2014','6'),(8,1,'1','2014-05-30','æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2014-05-30','æ²³å—å·¥ä¸šå¤§å­¦',NULL,'æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2014','5'),(9,8,'2','2014-05-21','','æ²³å—å·¥ä¸šå¤§å­¦','2014-05-30','æ²³å—å·¥ä¸šå¤§å­¦',NULL,'æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2014','3'),(10,8,'1','2014-05-08','æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2014-05-31','æ²³å—å·¥ä¸šå¤§å­¦',NULL,'æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—å·¥ä¸šå¤§å­¦','2012','5'),(11,1,'3','2014-05-05','ç”˜äº®','bugID','2014-05-30','bugID',NULL,'bugID','bugID','2014','6');

/*Table structure for table `elec_building` */

DROP TABLE IF EXISTS `elec_building`;

CREATE TABLE `elec_building` (
  `buildingID` int(11) NOT NULL,
  `jctID` varchar(255) default NULL,
  `buildName` varchar(255) default NULL,
  `buildType` varchar(255) default NULL,
  `buildLayer` int(11) default NULL,
  `buildArea` double default NULL,
  `buildStartDate` date default NULL,
  `extendBuildDate` date default NULL,
  `extendBuildArea` double default NULL,
  `dxDate` date default NULL,
  `buildExpense` double default NULL,
  `useDate` date default NULL,
  `comment` varchar(255) default NULL,
  PRIMARY KEY  (`buildingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_building` */

insert  into `elec_building`(`buildingID`,`jctID`,`buildName`,`buildType`,`buildLayer`,`buildArea`,`buildStartDate`,`extendBuildDate`,`extendBuildArea`,`dxDate`,`buildExpense`,`useDate`,`comment`) values (7,'3','007','3',NULL,1234,NULL,NULL,0,NULL,0,NULL,''),(9,'4','009','3',NULL,1234,NULL,NULL,0,NULL,0,NULL,''),(10,'1','010','3',NULL,11234,NULL,NULL,0,NULL,0,NULL,''),(11,'2','011','3',NULL,12345,NULL,NULL,0,NULL,0,NULL,''),(12,'1','012','1',NULL,1123,NULL,NULL,0,NULL,0,NULL,''),(15,'2','015','1',NULL,123,NULL,NULL,0,NULL,0,NULL,''),(16,'3','016','1',NULL,123,NULL,NULL,0,NULL,0,NULL,''),(19,'4','019','1',NULL,123,NULL,NULL,0,NULL,0,NULL,''),(20,'3','020','2',NULL,123,NULL,NULL,0,NULL,0,NULL,''),(21,'1','001','1',NULL,123,NULL,NULL,0,NULL,0,NULL,''),(22,'1','001','1',NULL,123,NULL,NULL,0,NULL,0,NULL,''),(23,'1','001','1',NULL,1,NULL,NULL,0,NULL,0,NULL,''),(24,'3','007','3',12,1234,'1990-05-15','1990-05-15',0,'1990-05-15',0,'1990-05-15','geggege'),(25,'3','3',NULL,12,1234,'1990-05-15','1990-05-15',0,'1990-05-15',0,'1990-05-15','geggege'),(26,'3','007','3',12,1234,'1990-05-15','1990-05-15',0,'1990-05-15',0,'1990-05-15','geggege'),(27,'3','007','3',12,1234,'1990-05-15','1990-05-15',0,'1990-05-15',0,'1990-05-15','geggege');

/*Table structure for table `elec_commonmsg` */

DROP TABLE IF EXISTS `elec_commonmsg`;

CREATE TABLE `elec_commonmsg` (
  `comID` varchar(255) NOT NULL,
  `stationRun` varchar(255) default NULL,
  `devRun` varchar(255) default NULL,
  `createDate` datetime default NULL,
  PRIMARY KEY  (`comID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_commonmsg` */

insert  into `elec_commonmsg`(`comID`,`stationRun`,`devRun`,`createDate`) values ('8aa50bc745828d0d0145829039360001','<p>\r\n	&nbsp;hello &nbsp; &nbsp;&nbsp;</p>\r\n','<p>\r\n	æˆ‘å» &nbsp;è¿™æ˜¯åœ¨é‚£é‡Œå•Š &nbsp;å°å­</p>\r\n','2014-04-22 11:48:42');

/*Table structure for table `elec_datachart` */

DROP TABLE IF EXISTS `elec_datachart`;

CREATE TABLE `elec_datachart` (
  `dataChartID` int(11) NOT NULL,
  `jctID` varchar(255) default NULL,
  `belongTo` varchar(255) default NULL,
  `dataChartName` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  PRIMARY KEY  (`dataChartID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_datachart` */

/*Table structure for table `elec_devplan` */

DROP TABLE IF EXISTS `elec_devplan`;

CREATE TABLE `elec_devplan` (
  `devPlanID` int(11) NOT NULL,
  `devType` varchar(255) default NULL,
  `devName` varchar(255) default NULL,
  `trademark` varchar(255) default NULL,
  `specType` varchar(255) default NULL,
  `produceHome` varchar(255) default NULL,
  `quality` int(11) default NULL,
  `qunit` varchar(255) default NULL,
  `useness` varchar(255) default NULL,
  `produceArea` varchar(255) default NULL,
  `devExpense` double default NULL,
  `useUnit` varchar(255) default NULL,
  `jctID` varchar(255) default NULL,
  `planDate` date default NULL,
  `adjustPeriod` varchar(255) default NULL,
  `apunit` varchar(255) default NULL,
  `overhaulPeriod` varchar(255) default NULL,
  `opunit` varchar(255) default NULL,
  `configure` varchar(255) default NULL,
  PRIMARY KEY  (`devPlanID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_devplan` */

insert  into `elec_devplan`(`devPlanID`,`devType`,`devName`,`trademark`,`specType`,`produceHome`,`quality`,`qunit`,`useness`,`produceArea`,`devExpense`,`useUnit`,`jctID`,`planDate`,`adjustPeriod`,`apunit`,`overhaulPeriod`,`opunit`,`configure`) values (22,'4','é¿é›·é’ˆ','é¿é›·é’ˆ','é¿é›·é’ˆ5555','é¿é›·é’ˆ',1,'ä¸ª','é¿é›·é’ˆ','é¿é›·é’ˆ',234,'äººæ°‘å¸','4','2014-05-03','2','æœˆ','5','æœˆ','é¿é›·é’ˆé¿é›·é’ˆé¿é›·é’ˆé¿é›·é’ˆé¿é›·é’ˆ'),(23,'1','usbç”µè„‘è¿æ¥æ¥å£','USB','USB-2259','usbç”µè„‘è¿æ¥æ¥å£',1000,'ä¸ª','usbç”µè„‘è¿æ¥æ¥å£','usbç”µè„‘è¿æ¥æ¥å£',122,'äººæ°‘å¸','1','2014-05-04','1','æœˆ','5','æ—¥','usbç”µè„‘è¿æ¥æ¥å£'),(25,'3','Iphone4','Apple','æ‹¼è¿‡å››ä»£','éƒ‘å·å¯Œå£«åº·',1,'ä¸ª','è€é…·','éƒ‘å·å¸‚',3600,'äººæ°‘å¸','4','2014-05-03','1','æœˆ','5','æ—¥','è‹¹æœç”µè„‘'),(26,'5','word2007','office','word2007','å¾®è½¯',1,'ä»½','ç¼–å†™wordæ–‡æ¡£','ä¸­å›½',200,'äººæ°‘å¸','1','2014-05-03','1','æœˆ','5','æ—¥','office'),(27,'6','ç”µè§†æœº','IT','IT-5525','ä¸­å…³æ‘',100,'å°','é”€å”®','åŒ—äº¬å¸‚',8000,'äººæ°‘å¸','1','2014-05-03','1','æœˆ','5','æ—¥','ä¸­å…³æ‘ç”µè§†æœº'),(28,'5','office','office','office','office',1,'å°','office','office',234,'äººæ°‘å¸','1','2014-05-03','1','æœˆ','5','æ—¥','officeofficeofficeoffice'),(29,'3','è”æƒ³','è”æƒ³','lenovon-i7','è”æƒ³',1,'å°','è”æƒ³','è”æƒ³',345,'äººæ°‘å¸','3','2014-05-03','1','æœˆ','5','å‘¨','è”æƒ³è”æƒ³è”æƒ³è”æƒ³è”æƒ³'),(30,'3','æˆ´å°”','æˆ´å°”','æˆ´å°”678','æˆ´å°”',1,'å°','æˆ´å°”','æˆ´å°”',4567,'äººæ°‘å¸','3','2014-05-03','2','æœˆ','6','æœˆ',NULL),(31,'3','ä¸œèŠ','ä¸œèŠ','ä¸œèŠ4567','ä¸œèŠ',1,'å°','ä¸œèŠ','ä¸œèŠ',5678,'äººæ°‘å¸','4','2014-05-03','1','æœˆ','5','æœˆ','ä¸œèŠä¸œèŠä¸œèŠä¸œèŠ'),(32,'3','è¥¿éƒ¨æ•°æ®','è¥¿éƒ¨æ•°æ®','è¥¿éƒ¨æ•°æ®123','è¥¿éƒ¨æ•°æ®',1,'å°','è¥¿éƒ¨æ•°æ®','è¥¿éƒ¨æ•°æ®',480,'äººæ°‘å¸','3','2014-05-03','1','æœˆ','4','æœˆ','è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®'),(34,'1','usbç”µè„‘è¿æ¥æ¥å£','USB','USB-2259','usbç”µè„‘è¿æ¥æ¥å£',1000,'ä¸ª','usbç”µè„‘è¿æ¥æ¥å£','usbç”µè„‘è¿æ¥æ¥å£',122,'äººæ°‘å¸',NULL,'2014-05-04','1','æœˆ','5','æ—¥','usbç”µè„‘è¿æ¥æ¥å£');

/*Table structure for table `elec_elecfileupload` */

DROP TABLE IF EXISTS `elec_elecfileupload`;

CREATE TABLE `elec_elecfileupload` (
  `fileUploadID` int(11) NOT NULL,
  `bugID` int(11) default NULL,
  `planID` int(11) default NULL,
  `file` longblob,
  `fileFileName` varchar(255) default NULL,
  `fileContentType` varchar(255) default NULL,
  `adjustID` int(11) default NULL,
  `repairID` int(11) default NULL,
  PRIMARY KEY  (`fileUploadID`),
  KEY `FK203F7DCCC822F06` (`planID`),
  KEY `FK203F7DC3763EC0A` (`bugID`),
  CONSTRAINT `FK203F7DC3763EC0A` FOREIGN KEY (`bugID`) REFERENCES `elec_bug` (`bugID`),
  CONSTRAINT `FK203F7DCCC822F06` FOREIGN KEY (`planID`) REFERENCES `elec_plan` (`planID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_elecfileupload` */

/*Table structure for table `elec_equapment` */

DROP TABLE IF EXISTS `elec_equapment`;

CREATE TABLE `elec_equapment` (
  `equapmentID` int(11) NOT NULL,
  `jctID` varchar(255) default NULL,
  `devName` varchar(255) default NULL,
  `devType` varchar(255) default NULL,
  `quality` int(11) default NULL,
  `qunit` varchar(255) default NULL,
  `devExpense` double default NULL,
  `useUnit` varchar(255) default NULL,
  `configure` varchar(255) default NULL,
  `specType` varchar(255) default NULL,
  `trademark` varchar(255) default NULL,
  `devState` varchar(255) default NULL,
  `produceHome` varchar(255) default NULL,
  `produceArea` varchar(255) default NULL,
  `useness` varchar(255) default NULL,
  `overhaulPeriod` varchar(255) default NULL,
  `opunit` varchar(255) default NULL,
  `useDate` date default NULL,
  `planDate` date default NULL,
  `adjustPeriod` varchar(255) default NULL,
  `apunit` varchar(255) default NULL,
  `runDescribe` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  PRIMARY KEY  (`equapmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_equapment` */

insert  into `elec_equapment`(`equapmentID`,`jctID`,`devName`,`devType`,`quality`,`qunit`,`devExpense`,`useUnit`,`configure`,`specType`,`trademark`,`devState`,`produceHome`,`produceArea`,`useness`,`overhaulPeriod`,`opunit`,`useDate`,`planDate`,`adjustPeriod`,`apunit`,`runDescribe`,`comment`) values (1,'4','usbç”µè„‘è¿æ¥æ¥å£','2',1000,'ä¸ª',122,'äººæ°‘å¸','usbç”µè„‘è¿æ¥æ¥å£','USB-2259','USB','1','usbç”µè„‘è¿æ¥æ¥å£','usbç”µè„‘è¿æ¥æ¥å£','usbç”µè„‘è¿æ¥æ¥å£','5','æ—¥','2014-05-04',NULL,'1','æœˆ','ceshi shuju ','æ²¡é—®é¢˜å— ï¼Ÿï¼Ÿï¼Ÿï¼Ÿï¼Ÿï¼Ÿï¼Ÿï¼Ÿï¼Ÿï¼Ÿ'),(2,'1','ç¬”è®°æœ¬ç”µè„‘','2',1,'æœ¬',5600,'äººæ°‘å¸','ç¬”è®°æœ¬ç”µè„‘è´­ä¹°','AD04','åç¡•','1','åŒ—äº¬åç¡•','åŒ—äº¬å¸‚','è‡ªå·±ä½¿ç”¨','3','æ—¥','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(3,'4','Iphone4','3',1,'ä¸ª',3600,'äººæ°‘å¸','è‹¹æœç”µè„‘','æ‹¼è¿‡å››ä»£','Apple','1','éƒ‘å·å¯Œå£«åº·','éƒ‘å·å¸‚','è€é…·','5','æ—¥','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(4,'1','word2007','5',1,'ä»½',200,'äººæ°‘å¸','office','word2007','office','1','å¾®è½¯','ä¸­å›½','ç¼–å†™wordæ–‡æ¡£','5','æ—¥','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(5,'1','ç”µè§†æœº','6',100,'å°',8000,'äººæ°‘å¸','ä¸­å…³æ‘ç”µè§†æœº','IT-5525','IT','1','ä¸­å…³æ‘','åŒ—äº¬å¸‚','é”€å”®','5','æ—¥','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(6,'1','office','5',1,'å°',234,'äººæ°‘å¸','officeofficeofficeoffice','office','office','1','office','office','office','5','æ—¥','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(7,'3','è”æƒ³','3',1,'å°',345,'äººæ°‘å¸','è”æƒ³è”æƒ³è”æƒ³è”æƒ³è”æƒ³','lenovon-i7','è”æƒ³','1','è”æƒ³','è”æƒ³','è”æƒ³','5','å‘¨','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(8,'3','æˆ´å°”','3',1,'å°',4567,'äººæ°‘å¸',NULL,'æˆ´å°”678','æˆ´å°”','1','æˆ´å°”','æˆ´å°”','æˆ´å°”','6','æœˆ','2014-05-04','2014-05-03','2','æœˆ',NULL,NULL),(9,'4','ä¸œèŠ','3',1,'å°',5678,'äººæ°‘å¸','ä¸œèŠä¸œèŠä¸œèŠä¸œèŠ','ä¸œèŠ4567','ä¸œèŠ','1','ä¸œèŠ','ä¸œèŠ','ä¸œèŠ','5','æœˆ','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(10,'3','è¥¿éƒ¨æ•°æ®','3',1,'å°',480,'äººæ°‘å¸','è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®è¥¿éƒ¨æ•°æ®','è¥¿éƒ¨æ•°æ®123','è¥¿éƒ¨æ•°æ®','1','è¥¿éƒ¨æ•°æ®','è¥¿éƒ¨æ•°æ®','è¥¿éƒ¨æ•°æ®','4','æœˆ','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL),(11,'4','é¿é›·é’ˆ','4',1,'ä¸ª',234,'äººæ°‘å¸','é¿é›·é’ˆé¿é›·é’ˆé¿é›·é’ˆé¿é›·é’ˆé¿é›·é’ˆ','é¿é›·é’ˆ5555','é¿é›·é’ˆ','1','é¿é›·é’ˆ','é¿é›·é’ˆ','é¿é›·é’ˆ','5','æœˆ','2014-05-04','2014-05-03','2','æœˆ',NULL,NULL),(12,'1','ç¬”è®°æœ¬ç”µè„‘','2',1,'æœ¬',5600,'äººæ°‘å¸','ç¬”è®°æœ¬ç”µè„‘è´­ä¹°','AD04','åç¡•','1','åŒ—äº¬åç¡•','åŒ—äº¬å¸‚','è‡ªå·±ä½¿ç”¨','3','æ—¥','2014-05-04','2014-05-03','1','æœˆ',NULL,NULL);

/*Table structure for table `elec_exportfields` */

DROP TABLE IF EXISTS `elec_exportfields`;

CREATE TABLE `elec_exportfields` (
  `belongTo` varchar(255) NOT NULL,
  `expNameList` varchar(255) default NULL,
  `expFieldName` varchar(255) default NULL,
  `noExpNameList` varchar(255) default NULL,
  `noExpFieldName` varchar(255) default NULL,
  PRIMARY KEY  (`belongTo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_exportfields` */

insert  into `elec_exportfields`(`belongTo`,`expNameList`,`expFieldName`,`noExpNameList`,`noExpFieldName`) values ('1-1','æ‰€å±å•ä½#è®¾å¤‡ç±»åç§°#è®¾å¤‡ç±»å‹#æ•°é‡#æ•°é‡å•ä½#èŠ±è´¹#é‡‘é’±å•å…ƒ#é…ç½®#è§„æ ¼å‹å·#å“ç‰Œ#è®¾å¤‡çŠ¶å†µ#ç”Ÿäº§å‚å®¶#ç”Ÿäº§åœ°#ç”¨é€”#æ£€ä¿®å‘¨æœŸ#æ£€ä¿®å•å…ƒ#ä½¿ç”¨æ—¥æœŸ#è®¡åˆ’æ—¥æœŸ#è°ƒæ•´å‘¨æœŸ#è°ƒæ•´å•å…ƒ#è¿è¡Œæè¿°#å¤‡æ³¨','jctID#devName#devType#quality#qunit#devExpense#useUnit#configure#specType#trademark#devState#produceHome#produceArea#useness#overhaulPeriod#opunit#useDate#planDate#adjustPeriod#apunit#runDescribe#comment','',''),('1-2','è®¾å¤‡ID#ç”¨æˆ·å•ä½#è®¾å¤‡åç§°#è®¾å¤‡ç±»å‹#å¼€å§‹æ—¥æœŸ#æ˜¯å¦æ ¡å‡†#å¤‡æ³¨#è®°å½•','equapmentID#jctID#devName#devType#startDate#isHaving#comment#record','ä½¿ç”¨æ—¥æœŸ#æ ¡å‡†å•å…ƒ#æ ¡å‡†å‘¨æœŸ','useDate#apunit#adjustPeriod'),('1-2-2','è®¾å¤‡ID#ç”¨æˆ·å•ä½#è®¾å¤‡åç§°#æ ¡å‡†å‘¨æœŸ#æ£€ä¿®å•å…ƒ#ä½¿ç”¨æ—¥æœŸ#è®¾å¤‡ç±»å‹#å¼€å§‹æ—¥æœŸ#æ˜¯å¦æ ¡å‡†#å¤‡æ³¨#è®°å½•','equapmentID#jctID#devName#overhaulPeriod#opunit#useDate#devType#startDate#isHaving#comment#record',NULL,NULL),('1-3','è®¾å¤‡ç±»å‹#è®¾å¤‡åç§°#å“ç‰Œ#è§„æ ¼å‹å·#å‚å®¶#æ•°é‡#å•ä½#ç”¨é€”#åœ°äº§#é‡‘é¢#ä½¿ç”¨å•å…ƒ#æ‰€å±å•ä½#è®¡åˆ’æ—¶é—´#æ ¡å‡†å‘¨æœŸ#æ ¡å‡†å•ä½#æ£€ä¿®å‘¨æœŸ#æ£€ä¿®å•ä½#é…ç½®','devType#devName#trademark#specType#produceHome#quality#qunit#useness#produceArea#devExpense#useUnit#jctID#planDate#adjustPeriod#apunit#overhaulPeriod#opunit#configure','',''),('3-1','æ‰€å±å•ä½#å»ºç­‘åç§°#å»ºç­‘ç±»å‹#å»ºç­‘å±‚æ•°#å»ºç­‘é¢ç§¯#å§‹å»ºæ—¶é—´#æ‰©å»ºæ—¶é—´#æ‰©å»ºé¢ç§¯#å¤§ä¿®æ—¶é—´#é€ ä»·#ä½¿ç”¨æ—¶é—´#å¤‡æ³¨','jctID#buildName#buildType#buildLayer#buildArea#buildStartDate#extendBuildDate#extendBuildArea#dxDate#buildExpense#useDate#comment','',''),('4-1','æ‰€å±å•ä½#ç«™ç‚¹åç§°#ç«™ç‚¹ä»£å·#å¼€å§‹ä½¿ç”¨æ—¶é—´#å®‰è£…åœ°ç‚¹#ç”Ÿäº§å‚å®¶#é€šè®¯æ–¹å¼#ç«™ç‚¹ç±»åˆ«#å½’å±åœ°#å¤‡æ³¨','jctID#stationName#stationCode#useStartDate#jcfrequency#produceHome#contactType#stationType#attributionGround#comment','',''),('4-2','ç«™ç‚¹åç§°#ç«™ç‚¹ä»£å·#æ‰€å±å•ä½#æ•…éšœç±»å‹#æ•…éšœæ—¶é—´#å¤„ç†æ—¶é—´#æ•…éšœåŸå› #å¤„ç†æ–¹æ³•#å¤‡æ³¨#æ•…éšœæè¿°','elecStation.stationName#elecStation.stationCode#elecStation.jctID#bugType#occurDate#resolveDate#bugReason#resolveMethod#comment#bugDescribe','',''),('5-1','æ‰€å±å•ä½#ç”¨æˆ·å§“å#ç™»å½•å#å¯†ç #æ€§åˆ«#å‡ºç”Ÿæ—¥æœŸ#è”ç³»åœ°å€#ç”µå­é‚®ç®±#æ˜¯å¦åœ¨èŒ#å…¥èŒæ—¶é—´#èŒä½#æ‰‹æœº','jctID#userName#logonName#logonPwd#sexID#birthday#address#email#isDuty#onDutyDate#postID#mobile','è”ç³»ç”µè¯#ç¦»èŒæ—¶é—´#å¤‡æ³¨','contactTel#offDutyDate#remark'),('5-3','ç«™ç‚¹è¿è¡Œæƒ…å†µ#è®¾å¤‡è¿è¡Œæƒ…å†µ#åˆ›å»ºæ—¥æœŸ','stationRun#devRun#createDate','','');

/*Table structure for table `elec_plan` */

DROP TABLE IF EXISTS `elec_plan`;

CREATE TABLE `elec_plan` (
  `planID` int(11) NOT NULL,
  `jctID` varchar(255) default NULL,
  `occurDate` date default NULL,
  `mainContent` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  PRIMARY KEY  (`planID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_plan` */

insert  into `elec_plan`(`planID`,`jctID`,`occurDate`,`mainContent`,`comment`) values (1,'1','2014-05-30','åŒ—äº¬ç«™ç‚¹å®šæ—¶ç›‘æµ‹ç”µæœºçŠ¶å†µ\r\n','æ·»åŠ ç”µæœºç»´æŠ¤çš„è®¡åˆ’'),(2,'1','2014-05-30','åŒ—äº¬ç«™ç‚¹åŸºæœ¬ä¿¡æ¯çš„ä¿®æ”¹','åŒ—äº¬ç«™ç‚¹åŸºæœ¬ä¿¡æ¯çš„ä¿®æ”¹'),(3,'4','2014-05-30','ä¿¡é˜³å¸‚ç”µæœºç»´æŠ¤è®¡åˆ’','ä¿¡é˜³å¸‚ç”µæœºç»´æŠ¤è®¡åˆ’'),(4,'2','2014-05-30','ä¸Šæµ·ç»´æŠ¤è®¡åˆ’','ä¸Šæµ·ç»´æŠ¤è®¡åˆ’'),(5,'3','2014-05-30','æ·±åœ³ç»´æŠ¤è®¡åˆ’','æ·±åœ³ç»´æŠ¤è®¡åˆ’'),(6,'4','2014-06-30','ä¿¡é˜³å¸‚ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹','ä¿¡é˜³å¸‚ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹'),(7,'2','2014-06-30','ä¸Šæµ·å¸‚ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹','ä¸Šæµ·å¸‚ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹'),(8,'3','2014-06-30','æ·±åœ³å¸‚ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹','æ·±åœ³å¸‚ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹'),(9,'1','2014-06-30','åŒ—äº¬ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹','åŒ—äº¬ç¬¬äºŒæ¬¡ç»´æŠ¤è®¡åˆ’ç›‘æµ‹'),(12,'1','2014-05-30','ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’','ä¿¡é˜³å¸‚äº”æœˆè®¡åˆ’'),(13,'1','2014-05-06','2014/5/2','2014/5/2'),(14,'1','2014-05-06','HEHEHEHEHEHE','HEEHEHEHEHEHEHEH');

/*Table structure for table `elec_popedom` */

DROP TABLE IF EXISTS `elec_popedom`;

CREATE TABLE `elec_popedom` (
  `mid` varchar(255) NOT NULL,
  `pid` varchar(255) NOT NULL,
  `name` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `icon` varchar(255) default NULL,
  `target` varchar(255) default NULL,
  `isParent` bit(1) default NULL,
  `isMenu` bit(1) default NULL,
  PRIMARY KEY  (`mid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_popedom` */

insert  into `elec_popedom`(`mid`,`pid`,`name`,`url`,`icon`,`target`,`isParent`,`isMenu`) values ('aa','0','æŠ€æœ¯è®¾æ–½ç»´æŠ¤ç®¡ç†','','../images/MenuIcon/jishusheshiweihuguanli.gif','','',''),('ab','aa','ä»ªå™¨è®¾å¤‡ç®¡ç†','../equapment/elecEquapmentAction_home.do','../images/MenuIcon/yiqishebeiguanli.gif','mainFrame','\0',''),('ac','aa','è®¾å¤‡æ ¡å‡†æ£€ä¿®','../equapment/elecAdjustAction_home.do','../images/MenuIcon/shebeijiaozhunjianxiu.gif','mainFrame','\0',''),('ad','aa','è®¾å¤‡è´­ç½®è®¡åˆ’','../equapment/elecDevPlanAction_home.do','../images/MenuIcon/shebeigouzhijihua.gif','mainFrame','\0',''),('ae','0','æŠ€æœ¯èµ„æ–™å›¾çº¸ç®¡ç†','','../images/MenuIcon/jishuziliaotuzhiguanli.gif','','',''),('af','ae','èµ„æ–™å›¾çº¸ç®¡ç†','../dataChart/elecDataChartAction_home.do','../images/MenuIcon/ziliaotuzhiguanli.gif','mainFrame','\0',''),('ag','0','ç«™ç‚¹è®¾å¤‡è¿è¡Œç®¡ç†','','../images/MenuIcon/zhuandianshebeiyunxingguanli.gif','','',''),('ah','ag','ç«™ç‚¹åŸºæœ¬ä¿¡æ¯','../station/elecStationAction_home.do','../images/MenuIcon/zhandianjibenxinxi.gif','mainFrame','\0',''),('ai','ag','è¿è¡Œæƒ…å†µ','../station/elecBugAction_home.do','../images/MenuIcon/yunxingqingkuang.gif','mainFrame','\0',''),('aj','ag','ç»´æŠ¤æƒ…å†µ','../station/elecPlanAction_home.do','../images/MenuIcon/weihuqingkuang.gif','mainFrame','\0',''),('ak','0','ç›‘æµ‹å°å»ºç­‘ç®¡ç†','','../images/MenuIcon/jiancetaijianzhuguanli.gif','','',''),('al','ak','ç›‘æµ‹å°å»ºç­‘ç®¡ç†','../building/elecBuildingAction_home.do','../images/MenuIcon/jiancetaijianzhu.gif','mainFrame','\0',''),('am','0','ç³»ç»Ÿç®¡ç†','','../images/MenuIcon/xitongguanli.gif','','',''),('an','am','ç”¨æˆ·ç®¡ç†','../system/elecUserAction_home.do','../images/MenuIcon/yonghuguanli.gif','mainFrame','\0',''),('ao','am','è§’è‰²ç®¡ç†','../system/elecRoleAction_home.do','../images/MenuIcon/jueseguanli.gif','mainFrame','\0',''),('ap','am','è¿è¡Œç›‘æ§','../system/elecCommonMsgAction_home.do','../images/MenuIcon/daibanshiyi.gif','mainFrame','\0',''),('aq','am','æ•°æ®å­—å…¸ç»´æŠ¤','../system/elecSystemDDLAction_home.do','../images/MenuIcon/shujuzidianguanli.gif','mainFrame','\0',''),('ar','0','å®¡æ‰¹æµè½¬','','../images/MenuIcon/shenpiliuzhuanguanli.gif','','',''),('as','ar','å®¡æ‰¹æµç¨‹ç®¡ç†','../workflow/elecProcessDefinitionAction_home.do','../images/MenuIcon/shenpiliuchengguanli.gif','mainFrame','\0',''),('at','ar','ç”³è¯·æ¨¡æ¿ç®¡ç†','../workflow/elecApplicationTemplateAction_home.do','../images/MenuIcon/shenqingmobanguanli.gif','mainFrame','\0',''),('au','ar','èµ·è‰ç”³è¯·','../workflow/elecApplicationFlowAction_home.do','../images/MenuIcon/qicaoshenqing.gif','mainFrame','\0',''),('av','ar','å¾…æˆ‘å®¡æ‰¹','../workflow/elecApplicationFlowAction_myTaskHome.do','../images/MenuIcon/daiwoshenpi.gif','mainFrame','\0',''),('aw','ar','æˆ‘çš„ç”³è¯·æŸ¥è¯¢','../workflow/elecApplicationFlowAction_myApplicationHome.do','../images/MenuIcon/wodeshenqingchaxun.gif','mainFrame','\0',''),('ba','0','ç³»ç»Ÿç™»å½•','','','','','\0'),('bb','ba','é¦–é¡µæ˜¾ç¤º','/system/elecMenuAction_menuHome.do','','','\0','\0'),('bc','ba','æ ‡é¢˜','/system/elecMenuAction_title.do','','','\0','\0'),('bd','ba','èœå•','/system/elecMenuAction_left.do','','','\0','\0'),('be','ba','åŠ è½½å·¦ä¾§æ ‘å‹ç»“æ„','/system/elecMenuAction_showMenu.do','','','\0','\0'),('bf','ba','æ”¹å˜æ¡†æ¶','../system/elecMenuAction_change.do','','','\0','\0'),('bg','ba','åŠ è½½ä¸»æ˜¾ç¤ºé¡µé¢','../system/elecMenuAction_loading.do','','','\0','\0'),('bh','ba','ç«™ç‚¹è¿è¡Œ','../system/elecMenuAction_alermStation.do','','','\0','\0'),('bi','ba','è®¾å¤‡è¿è¡Œ','../system/elecMenuAction_alermDevice.do','','','\0','\0'),('bj','ba','é‡æ–°ç™»å½•','../system/elecMenuAction_logout.do','','','\0','\0'),('ca','0','è¿è¡Œç›‘æ§','','','','','\0'),('cb','ca','ä¿å­˜','/system/elecCommonMsgAction_save.do','','','\0','\0'),('cc','ca','ajaxè¿›åº¦æ¡','/system/elecCommonMsgAction_progressBar.do','','','\0','\0'),('da','0','å¯¼å‡ºè®¾ç½®','','','','','\0'),('db','da','å¯¼å‡ºè®¾ç½®è®¾ç½®','/system/elecExportFieldsAction_setExportExcel.do','','','\0','\0'),('dc','da','å¯¼å‡ºè®¾ç½®ä¿å­˜','/system/elecExportFieldsAction_saveSetExportExcel.do','','','\0','\0'),('ea','0','æ•°æ®å­—å…¸','','','','','\0'),('eb','ea','ç¼–è¾‘','/system/elecSystemDDLAction_edit.do','','','\0','\0'),('ec','ea','ä¿å­˜','/system/elecSystemDDLAction_save.do','','','\0','\0'),('fa','0','ç”¨æˆ·ç®¡ç†','','','','','\0'),('fb','fa','æ–°å¢','/system/elecUserAction_add.do','','','\0','\0'),('fc','fa','ä¿å­˜','/system/elecUserAction_save.do','','','\0','\0'),('fd','fa','ç¼–è¾‘','/system/elecUserAction_edit.do','','','\0','\0'),('fe','fa','åˆ é™¤','/system/elecUserAction_delete.do','','','\0','\0'),('ff','fa','éªŒè¯ç™»å½•å','/system/elecUserAction_checkUser.do','','','\0','\0'),('fg','fa','å¯¼å‡ºexcel','/system/elecUserAction_exportExcel.do','','','\0','\0'),('fh','fa','excelå¯¼å…¥é¡µé¢','/system/elecUserAction_importPage.do','','','\0','\0'),('fi','fa','excelå¯¼å…¥','/system/elecUserAction_importData.do','','','\0','\0'),('fj','fa','äººå‘˜ç»Ÿè®¡(å•ä½)','/system/elecUserAction_chartUser.do','','','\0','\0'),('fk','fa','äººå‘˜ç»Ÿè®¡(æ€§åˆ«)','/system/elecUserAction_chartUserFCF.do','','','\0','\0'),('fl','fa','è”åŠ¨æŸ¥è¯¢å•ä½åç§°','/system/elecUserAction_findJctUnit.do','','','\0','\0'),('ga','0','è§’è‰²ç®¡ç†','','','','','\0'),('gb','ga','ç¼–è¾‘','/system/elecRoleAction_edit.do','','','\0','\0'),('gc','ga','ä¿å­˜','/system/elecRoleAction_save.do','','','\0','\0'),('ha','0','ç”³è¯·æµç¨‹ç®¡ç†','','','','','\0'),('hb','ha','éƒ¨ç½²æµç¨‹å®šä¹‰','/workflow/elecProcessDefinitionAction_add.do','','','\0','\0'),('hc','ha','ä¿å­˜','/workflow/elecProcessDefinitionAction_save.do','','','\0','\0'),('hd','ha','æŸ¥çœ‹æµç¨‹å›¾','/workflow/elecProcessDefinitionAction_downloadProcessImage.do','','','\0','\0'),('he','ha','åˆ é™¤æµç¨‹å®šä¹‰','/workflow/elecProcessDefinitionAction_delete.do','','','\0','\0'),('ia','0','ç”³è¯·æ¨¡æ¿ç®¡ç†','','','','','\0'),('ib','ia','æ–°å¢','/workflow/elecApplicationTemplateAction_add.do','','','\0','\0'),('ic','ia','æ–°å¢ä¿å­˜','/workflow/elecApplicationTemplateAction_save.do','','','\0','\0'),('id','ia','ç¼–è¾‘','/workflow/elecApplicationTemplateAction_edit.do','','','\0','\0'),('ie','ia','ç¼–è¾‘ä¿å­˜','/workflow/elecApplicationTemplateAction_update.do','','','\0','\0'),('if','ia','åˆ é™¤','/workflow/elecApplicationTemplateAction_delete.do','','','\0','\0'),('ig','ia','ä¸‹è½½','/workflow/elecApplicationTemplateAction_download.do','','','\0','\0'),('ja','0','ç”³è¯·æµç¨‹ç®¡ç†','','','','','\0'),('jb','ja','æäº¤ç”³è¯·é¡µé¢','/workflow/elecApplicationFlowAction_submitApplication.do','','','\0','\0'),('jc','ja','æäº¤ç”³è¯·','/workflow/elecApplicationFlowAction_saveApplication.do','','','\0','\0'),('jd','ja','æˆ‘çš„ç”³è¯·æŸ¥è¯¢é¦–é¡µ','/workflow/elecApplicationFlowAction_myApplicationHome.do','','','\0','\0'),('je','ja','å¾…æˆ‘å®¡æ‰¹é¦–é¡µ','/workflow/elecApplicationFlowAction_myTaskHome.do','','','\0','\0'),('jf','ja','è·³è½¬å®¡æ‰¹å¤„ç†é¡µé¢','/workflow/elecApplicationFlowAction_flowApprove.do','','','\0','\0'),('jg','ja','ä¸‹è½½','/workflow/elecApplicationFlowAction_download.do','','','\0','\0'),('jh','ja','å®¡æ ¸','/workflow/elecApplicationFlowAction_approve.do','','','\0','\0'),('ji','ja','å®¡æ ¸å†å²','/workflow/elecApplicationFlowAction_approvedHistory.do','','','\0','\0'),('jj','ja','æŸ¥çœ‹æµç¨‹å›¾','/workflow/elecApplicationFlowAction_approvedHistoryPic.do','','','\0','\0'),('jk','ja','æŸ¥çœ‹æµç¨‹æ‰§è¡Œä½ç½®å›¾ç‰‡','/workflow/elecApplicationFlowAction_processImage.do','','','\0','\0');

/*Table structure for table `elec_repair` */

DROP TABLE IF EXISTS `elec_repair`;

CREATE TABLE `elec_repair` (
  `repairID` int(11) NOT NULL,
  `equapmentID` int(11) default NULL,
  `jctID` varchar(255) default NULL,
  `devName` varchar(255) default NULL,
  `overhaulPeriod` varchar(255) default NULL,
  `opunit` varchar(255) default NULL,
  `useDate` date default NULL,
  `devType` varchar(255) default NULL,
  `startDate` date default NULL,
  `isHaving` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  `record` varchar(255) default NULL,
  PRIMARY KEY  (`repairID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_repair` */

insert  into `elec_repair`(`repairID`,`equapmentID`,`jctID`,`devName`,`overhaulPeriod`,`opunit`,`useDate`,`devType`,`startDate`,`isHaving`,`comment`,`record`) values (5,10,'3','è¥¿éƒ¨æ•°æ®','4','æœˆ','2014-05-04','3','2014-05-05','2','111','555'),(6,1,'4','usbç”µè„‘è¿æ¥æ¥å£','5','æ—¥','2014-05-04','2','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(7,2,'1','ç¬”è®°æœ¬ç”µè„‘','3','æ—¥','2014-05-04','2','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(8,3,'4','Iphone4','5','æ—¥','2014-05-04','3','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(9,4,'1','word2007','5','æ—¥','2014-05-04','5','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(10,5,'1','ç”µè§†æœº','5','æ—¥','2014-05-04','6','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(11,6,'1','office','5','æ—¥','2014-05-04','5','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(13,8,'3','æˆ´å°”','6','æœˆ','2014-05-04','3','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(14,9,'4','ä¸œèŠ','5','æœˆ','2014-05-04','3','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(15,10,'3','è¥¿éƒ¨æ•°æ®','4','æœˆ','2014-05-04','3','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(16,11,'4','é¿é›·é’ˆ','5','æœˆ','2014-05-04','4','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(17,12,'1','ç¬”è®°æœ¬ç”µè„‘','3','æ—¥','2014-05-04','2','2014-05-05','2','å¥½å¥½åœ°æ´»ä¸‹å»å§','éå¸¸'),(18,3,NULL,NULL,NULL,NULL,NULL,NULL,'2014-05-05','0','æ—¥å­','æˆ‘è¯¥æ€ä¹ˆåŠ');

/*Table structure for table `elec_role` */

DROP TABLE IF EXISTS `elec_role`;

CREATE TABLE `elec_role` (
  `roleID` varchar(255) NOT NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_role` */

insert  into `elec_role`(`roleID`,`roleName`) values ('1','ç³»ç»Ÿç®¡ç†å‘˜'),('2','é«˜çº§ç®¡ç†å‘˜'),('3','ä¸­çº§ç®¡ç†å‘˜'),('4','ä¸šåŠ¡ç”¨æˆ·'),('5','ä¸€èˆ¬ç”¨æˆ·'),('6','æ™®é€šç”¨æˆ·');

/*Table structure for table `elec_role_popedom` */

DROP TABLE IF EXISTS `elec_role_popedom`;

CREATE TABLE `elec_role_popedom` (
  `roleID` varchar(255) NOT NULL,
  `mid` varchar(255) NOT NULL,
  `pid` varchar(255) NOT NULL,
  PRIMARY KEY  (`roleID`,`mid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_role_popedom` */

insert  into `elec_role_popedom`(`roleID`,`mid`,`pid`) values ('1','aa','0'),('1','ab','aa'),('1','ac','aa'),('1','ad','aa'),('1','ae','0'),('1','af','ae'),('1','ag','0'),('1','ah','ag'),('1','ai','ag'),('1','aj','ag'),('1','ak','0'),('1','al','ak'),('1','am','0'),('1','an','am'),('1','ao','am'),('1','ap','am'),('1','aq','am'),('1','ar','0'),('1','as','ar'),('1','at','ar'),('1','au','ar'),('1','av','ar'),('1','aw','ar'),('1','ba','0'),('1','bb','ba'),('1','bc','ba'),('1','bd','ba'),('1','be','ba'),('1','bf','ba'),('1','bg','ba'),('1','bh','ba'),('1','bi','ba'),('1','bj','ba'),('1','ca','0'),('1','cb','ca'),('1','cc','ca'),('1','da','0'),('1','db','da'),('1','dc','da'),('1','ea','0'),('1','eb','ea'),('1','ec','ea'),('1','fa','0'),('1','fb','fa'),('1','fc','fa'),('1','fd','fa'),('1','fe','fa'),('1','ff','fa'),('1','fg','fa'),('1','fh','fa'),('1','fi','fa'),('1','fj','fa'),('1','fk','fa'),('1','fl','fa'),('1','ga','0'),('1','gb','ga'),('1','gc','ga'),('1','ha','0'),('1','hb','ha'),('1','hc','ha'),('1','hd','ha'),('1','he','ha'),('1','ia','0'),('1','ib','ia'),('1','ic','ia'),('1','id','ia'),('1','ie','ia'),('1','if','ia'),('1','ig','ia'),('1','ja','0'),('1','jb','ja'),('1','jc','ja'),('1','jd','ja'),('1','je','ja'),('1','jf','ja'),('1','jg','ja'),('1','jh','ja'),('1','ji','ja'),('1','jj','ja'),('1','jk','ja'),('6','am','0'),('6','an','am'),('6','ao','am'),('6','ap','am'),('6','ar','0'),('6','at','ar'),('6','au','ar'),('6','av','ar'),('6','aw','ar'),('6','ba','0'),('6','bb','ba'),('6','bc','ba'),('6','bd','ba'),('6','be','ba'),('6','bf','ba'),('6','bg','ba'),('6','bh','ba'),('6','bi','ba'),('6','bj','ba'),('6','ca','0'),('6','cb','ca'),('6','cc','ca'),('6','da','0'),('6','db','da'),('6','dc','da'),('6','ia','0'),('6','ib','ia'),('6','ic','ia'),('6','id','ia'),('6','ie','ia'),('6','if','ia'),('6','ig','ia'),('6','ja','0'),('6','jb','ja'),('6','jc','ja'),('6','jd','ja'),('6','je','ja'),('6','jf','ja'),('6','jg','ja'),('6','jh','ja'),('6','ji','ja'),('6','jj','ja'),('6','jk','ja');

/*Table structure for table `elec_station` */

DROP TABLE IF EXISTS `elec_station`;

CREATE TABLE `elec_station` (
  `stationID` int(11) NOT NULL,
  `jctID` varchar(255) default NULL,
  `stationName` varchar(255) default NULL,
  `stationCode` varchar(255) default NULL,
  `useStartDate` date default NULL,
  `jcfrequency` varchar(255) default NULL,
  `produceHome` varchar(255) default NULL,
  `contactType` varchar(255) default NULL,
  `stationType` varchar(255) default NULL,
  `attributionGround` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  PRIMARY KEY  (`stationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_station` */

insert  into `elec_station`(`stationID`,`jctID`,`stationName`,`stationCode`,`useStartDate`,`jcfrequency`,`produceHome`,`contactType`,`stationType`,`attributionGround`,`comment`) values (1,'1','ç”µæœº','1001','2014-05-30','æ²³å—å·¥ä¸šå¤§å­¦','æ²³å—çœéƒ‘å·å¸‚','18639012025','1','æ²³å—çœ','ç«™ç‚¹ä¿¡æ¯æ·»åŠ æ•°æ®'),(2,'4','ç”µæœº','1002','2014-05-30','æ²³å—å†œä¸šå¤§å­¦','æ²³å—çœéƒ‘å·å¸‚','18639012025','2','æ²³å—çœ','æµ‹è¯•æ•°æ®å†œä¸šå¤§å­¦'),(3,'2','ç”µæœº','1003','2014-05-30','ä¸Šæµ·æµ¦ä¸œ','ä¸Šæµ·å¸‚','18639012025','3','ä¸Šæµ·å¸‚','ä¸Šæµ·ç”µç«™æµ‹è¯•ç‚¹'),(4,'3','ç”µæœº','1004','2014-05-30','å¹¿ä¸œ','å¹¿ä¸œæ·±åœ³','13939735338','1','å¹¿ä¸œçœ','å¹¿ä¸œçœæµ‹è¯•æ•°æ®'),(5,'1','ç”µæœº','1005','2014-05-30','åŒ—äº¬ä¼ æ™ºæ’­å®¢','itcast','13938481120','1','åŒ—äº¬å¸‚','åŒ—äº¬å¸‚åŸ¹è®­æœºæ„'),(6,'1','ç”µæœº','1006','2014-05-30','åŒ—äº¬å›é¾™è§‚','åŒ—äº¬è±«è¥¿','18639012025','1','åŒ—äº¬å¸‚','åŒ—äº¬å¸‚æµ‹è¯•ç‚¹'),(7,'4','ç”µæœº','1007','2014-05-30','ä¿¡é˜³åšçˆ±åŒ»é™¢','ä¿¡é˜³åšçˆ±åŒ»é™¢','13938481120','2','æ²³å—çœä¿¡é˜³å¸‚','æ²³å—çœä¿¡é˜³å¸‚æµ‹è¯•ç‚¹'),(8,'4','ç”µæœº','1008','2014-05-30','æ²³å—çœ','ä¿¡é˜³åå’ŒåŒ»é™¢','18639012025','2','æ²³å—çœä¿¡é˜³å¸‚','æ²³å—çœä¿¡é˜³å¸‚é¢å’ŒåŒ»é™¢'),(10,'2','ç”µæœº','1010','2014-05-30','ä¸Šæµ·å¸‚','ä¸Šæµ·å¸‚','13939735338','2','ä¸Šæµ·å¸‚','ä¸Šæµ·å¸‚ç¬¬ä¸€å‘ƒå‘ƒå‘ƒ');

/*Table structure for table `elec_systemddl` */

DROP TABLE IF EXISTS `elec_systemddl`;

CREATE TABLE `elec_systemddl` (
  `seqID` int(11) NOT NULL,
  `keyword` varchar(255) default NULL,
  `ddlCode` int(11) default NULL,
  `ddlName` varchar(255) default NULL,
  PRIMARY KEY  (`seqID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_systemddl` */

insert  into `elec_systemddl`(`seqID`,`keyword`,`ddlCode`,`ddlName`) values (1,'æ€§åˆ«',1,'ç”·'),(2,'æ€§åˆ«',2,'å¥³'),(3,'æ˜¯å¦åœ¨èŒ',1,'æ˜¯'),(4,'æ˜¯å¦åœ¨èŒ',2,'å¦'),(14,'å®¡æ ¸çŠ¶æ€',1,'å®¡æ ¸ä¸­'),(15,'å®¡æ ¸çŠ¶æ€',2,'å®¡æ ¸ä¸é€šè¿‡'),(16,'å®¡æ ¸çŠ¶æ€',3,'å®¡æ ¸é€šè¿‡'),(17,'å›¾çº¸ç±»åˆ«',1,'å›½å†…å›¾ä¹¦'),(18,'å›¾çº¸ç±»åˆ«',2,'å›½å¤–å›¾ä¹¦'),(54,'ä¸Šæµ·',1,'ä¸Šæµ·æµ¦ä¸œç”µåŠ›å…¬å¸'),(55,'ä¸Šæµ·',2,'ä¸Šæµ·é—¸åŒ—ç”µåŠ›å…¬å¸'),(56,'ä¸Šæµ·',3,'ä¸Šæµ·å¾æ±‡ç”µåŠ›å…¬å¸'),(57,'æ·±åœ³',1,'æ·±åœ³ç¦ç”°ç”µåŠ›å…¬å¸'),(58,'æ·±åœ³',2,'æ·±åœ³é¾™å²—ç”µåŠ›å…¬å¸'),(59,'æ·±åœ³',3,'æ·±åœ³å—å±±ç”µåŠ›å…¬å¸'),(60,'èŒä½',1,'æ€»ç»ç†'),(61,'èŒä½',2,'éƒ¨é—¨ç»ç†'),(62,'èŒä½',3,'å‘˜å·¥'),(63,'èŒä½',4,'ç³»ç»Ÿç®¡ç†å‘˜'),(68,'æ‰€å±å•ä½',1,'åŒ—äº¬'),(69,'æ‰€å±å•ä½',2,'ä¸Šæµ·'),(70,'æ‰€å±å•ä½',3,'æ·±åœ³'),(71,'æ‰€å±å•ä½',4,'ä¿¡é˜³'),(72,'åŒ—äº¬',1,'åŒ—äº¬æ˜Œå¹³ç”µåŠ›å…¬å¸'),(73,'åŒ—äº¬',2,'åŒ—äº¬æµ·æ·€ç”µåŠ›å…¬å¸'),(74,'åŒ—äº¬',3,'åŒ—äº¬è¥¿åŸç”µåŠ›å…¬å¸'),(75,'åŒ—äº¬',4,'åŒ—äº¬ä¼ æ™ºæ’­å®¢åŸ¹è®­æœºæ„'),(76,'å»ºç­‘ç±»å‹',1,'åŸºå»º'),(77,'å»ºç­‘ç±»å‹',2,'åŸºæ”¹'),(78,'å»ºç­‘ç±»å‹',3,'ç”Ÿæ´»ç”¨æˆ¿'),(79,'å»ºç­‘ç±»å‹',4,'é“è·¯'),(84,'ç«™ç‚¹ç±»åˆ«',1,'å›½å†…'),(85,'ç«™ç‚¹ç±»åˆ«',2,'å›½å†…é¥æ§ç«™'),(86,'ç«™ç‚¹ç±»åˆ«',3,'å›½å†…é‡‡é›†ç‚¹'),(87,'ç«™ç‚¹ç±»åˆ«',4,'å›½å¤–'),(88,'ç«™ç‚¹ç±»åˆ«',5,'æµ·å¤–é¥æ§ç«™'),(89,'æ•…éšœç±»å‹',1,'å·¥æ§æœº'),(90,'æ•…éšœç±»å‹',2,'æ¥æ”¶æœº'),(91,'æ•…éšœç±»å‹',3,'é€šè®¯'),(92,'æ•…éšœç±»å‹',4,'ä¾›ç”µ'),(93,'æ•…éšœç±»å‹',5,'ç”µæœº'),(94,'æ•…éšœç±»å‹',6,'æµ‹é‡æ¿å¡'),(95,'æ•…éšœç±»å‹',7,'è®¾å¤‡'),(96,'æ•…éšœç±»å‹',8,'ç½‘ç»œ'),(97,'è®¾å¤‡ç±»å‹',1,'ç”µåŠ›è®¾å¤‡'),(98,'è®¾å¤‡ç±»å‹',2,'ç”µæœºè®¾å¤‡'),(99,'è®¾å¤‡ç±»å‹',3,'é€šè®¯è®¾å¤‡'),(100,'è®¾å¤‡ç±»å‹',4,'é˜²é›·è®¾å¤‡'),(101,'è®¾å¤‡ç±»å‹',5,'åŠå…¬è®¾å¤‡'),(102,'è®¾å¤‡ç±»å‹',6,'ç”µè§†æœºæˆ¿è®¾å¤‡'),(103,'è®¾å¤‡ç±»å‹',7,'å‘ç”µæœºæˆ¿è®¾å¤‡'),(104,'è®¾å¤‡çŠ¶æ€',1,'æ­£å¸¸'),(105,'è®¾å¤‡çŠ¶æ€',2,'æ£€ä¿®'),(106,'è®¾å¤‡çŠ¶æ€',3,'ç»´ä¿®'),(108,'æ ¡å‡†çŠ¶æ€',1,'æœªæ ¡å‡†'),(109,'æ ¡å‡†çŠ¶æ€',2,'å·²æ ¡å‡†'),(110,'æ£€ä¿®çŠ¶æ€',1,'æœªæ£€ä¿®'),(111,'æ£€ä¿®çŠ¶æ€',2,'å·²æ£€ä¿®');

/*Table structure for table `elec_text` */

DROP TABLE IF EXISTS `elec_text`;

CREATE TABLE `elec_text` (
  `textID` varchar(255) NOT NULL,
  `textName` varchar(255) default NULL,
  `textDate` date default NULL,
  `textRemark` varchar(255) default NULL,
  PRIMARY KEY  (`textID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_text` */

insert  into `elec_text`(`textID`,`textName`,`textDate`,`textRemark`) values ('297ed2a2457eef2a01457ef0fb2a0002','111','2014-04-20','222'),('297ed2a2457f0a1501457f1d38060001','','2014-04-20',''),('297ed2a2457f0a1501457f242f6d0002','','2014-04-20','');

/*Table structure for table `elec_user` */

DROP TABLE IF EXISTS `elec_user`;

CREATE TABLE `elec_user` (
  `userID` varchar(255) NOT NULL,
  `jctID` varchar(255) default NULL,
  `jctUnitID` varchar(255) default NULL,
  `userName` varchar(255) default NULL,
  `logonName` varchar(255) default NULL,
  `logonPwd` varchar(255) default NULL,
  `sexID` varchar(255) default NULL,
  `birthday` date default NULL,
  `address` varchar(255) default NULL,
  `contactTel` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `isDuty` varchar(255) default NULL,
  `postID` varchar(255) default NULL,
  `onDutyDate` date default NULL,
  `offDutyDate` date default NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_user` */

insert  into `elec_user`(`userID`,`jctID`,`jctUnitID`,`userName`,`logonName`,`logonPwd`,`sexID`,`birthday`,`address`,`contactTel`,`email`,`mobile`,`isDuty`,`postID`,`onDutyDate`,`offDutyDate`,`remark`) values ('40283f81458757b7014587588fa20001','3','1','010','010','202CB962AC59075B964B07152D234B70','2',NULL,'','','','','1','3','2014-04-22',NULL,''),('402881e43d8b1e28013d8b8ae5a10001','1','jctUnitID','è¶…çº§ç®¡ç†å‘˜','admin','202CB962AC59075B964B07152D234B70','1',NULL,'','','','','1','4','2014-04-21','2014-04-21',''),('8aa50bc745837e920145838045430001','1','1','ç”˜äº®','gl','202CB962AC59075B964B07152D234B70','1',NULL,'','','','','1','1','2014-04-21',NULL,''),('8aa50bc745837e9201458384d22f0002','1','1','001','001','202CB962AC59075B964B07152D234B70','1','2014-04-21','','','','','1','3','2014-04-21',NULL,''),('8aa50bc7458399880145839fe3420001','1','1','003','003','202CB962AC59075B964B07152D234B70','1',NULL,'','','','13939735338','1','3','2014-04-21','2014-04-21',''),('8aa50bc74583a0b1014583a1cbae0001','3','1','004','004','202CB962AC59075B964B07152D234B70','1',NULL,'','','','18639012025','1','3','2014-04-21',NULL,''),('8aa50bc74583a0b1014583a64b450002','2','1','002','002','202CB962AC59075B964B07152D234B70','2',NULL,'','','','15652353061','1','3','2014-04-21',NULL,''),('8aa50bc74583a0b1014583c7583d0003','2','1','005','005','202CB962AC59075B964B07152D234B70','2',NULL,'','','','18639012025','1','3','2014-04-21',NULL,''),('8aa50bc74583a0b1014583c7bc0f0004','2','1','006','006','202CB962AC59075B964B07152D234B70','2',NULL,'','','','18639012025','1','3','2014-04-21',NULL,''),('8aa50bc74583a0b1014583c8db6a0007','3','1','009','009','202CB962AC59075B964B07152D234B70','2',NULL,'','','','18639012025','1','3','2014-04-21',NULL,''),('8aa50bc74584be1a014584d8c7870001','1','1','007','007','202CB962AC59075B964B07152D234B70','2',NULL,'','','','','1','3','2014-04-21',NULL,''),('8aa50bc74584be1a014584d915aa0002','1','1','008','008','202CB962AC59075B964B07152D234B70','2',NULL,'','','','','1','3','2014-04-21',NULL,'');

/*Table structure for table `elec_user_role` */

DROP TABLE IF EXISTS `elec_user_role`;

CREATE TABLE `elec_user_role` (
  `userID` varchar(255) NOT NULL,
  `roleID` varchar(255) NOT NULL,
  PRIMARY KEY  (`roleID`,`userID`),
  KEY `FK14CB98303172B4CE` (`roleID`),
  KEY `FK14CB983036C80A38` (`userID`),
  CONSTRAINT `FK14CB98303172B4CE` FOREIGN KEY (`roleID`) REFERENCES `elec_role` (`roleID`),
  CONSTRAINT `FK14CB983036C80A38` FOREIGN KEY (`userID`) REFERENCES `elec_user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `elec_user_role` */

insert  into `elec_user_role`(`userID`,`roleID`) values ('402881e43d8b1e28013d8b8ae5a10001','1'),('8aa50bc745837e920145838045430001','6');

/*Table structure for table `jbpm4_deployment` */

DROP TABLE IF EXISTS `jbpm4_deployment`;

CREATE TABLE `jbpm4_deployment` (
  `DBID_` bigint(20) NOT NULL,
  `NAME_` longtext,
  `TIMESTAMP_` bigint(20) default NULL,
  `STATE_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_deployment` */

insert  into `jbpm4_deployment`(`DBID_`,`NAME_`,`TIMESTAMP_`,`STATE_`) values (1,NULL,0,'active'),(8,NULL,0,'active');

/*Table structure for table `jbpm4_deployprop` */

DROP TABLE IF EXISTS `jbpm4_deployprop`;

CREATE TABLE `jbpm4_deployprop` (
  `DBID_` bigint(20) NOT NULL,
  `DEPLOYMENT_` bigint(20) default NULL,
  `OBJNAME_` varchar(255) default NULL,
  `KEY_` varchar(255) default NULL,
  `STRINGVAL_` varchar(255) default NULL,
  `LONGVAL_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  KEY `IDX_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  CONSTRAINT `FK_DEPLPROP_DEPL` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_deployprop` */

insert  into `jbpm4_deployprop`(`DBID_`,`DEPLOYMENT_`,`OBJNAME_`,`KEY_`,`STRINGVAL_`,`LONGVAL_`) values (4,1,'è®¾å¤‡è´¹ç”¨æŠ¥é”€','langid','jpdl-4.4',NULL),(5,1,'è®¾å¤‡è´¹ç”¨æŠ¥é”€','pdid','è®¾å¤‡è´¹ç”¨æŠ¥é”€-1',NULL),(6,1,'è®¾å¤‡è´¹ç”¨æŠ¥é”€','pdkey','è®¾å¤‡è´¹ç”¨æŠ¥é”€',NULL),(7,1,'è®¾å¤‡è´¹ç”¨æŠ¥é”€','pdversion',NULL,1),(11,8,'è®¾å¤‡è´­ç½®è®¡åˆ’','langid','jpdl-4.4',NULL),(12,8,'è®¾å¤‡è´­ç½®è®¡åˆ’','pdid','è®¾å¤‡è´­ç½®è®¡åˆ’-1',NULL),(13,8,'è®¾å¤‡è´­ç½®è®¡åˆ’','pdkey','è®¾å¤‡è´­ç½®è®¡åˆ’',NULL),(14,8,'è®¾å¤‡è´­ç½®è®¡åˆ’','pdversion',NULL,1);

/*Table structure for table `jbpm4_execution` */

DROP TABLE IF EXISTS `jbpm4_execution`;

CREATE TABLE `jbpm4_execution` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ACTIVITYNAME_` varchar(255) default NULL,
  `PROCDEFID_` varchar(255) default NULL,
  `HASVARS_` bit(1) default NULL,
  `NAME_` varchar(255) default NULL,
  `KEY_` varchar(255) default NULL,
  `ID_` varchar(255) default NULL,
  `STATE_` varchar(255) default NULL,
  `SUSPHISTSTATE_` varchar(255) default NULL,
  `PRIORITY_` int(11) default NULL,
  `HISACTINST_` bigint(20) default NULL,
  `PARENT_` bigint(20) default NULL,
  `INSTANCE_` bigint(20) default NULL,
  `SUPEREXEC_` bigint(20) default NULL,
  `SUBPROCINST_` bigint(20) default NULL,
  `PARENT_IDX_` int(11) default NULL,
  PRIMARY KEY  (`DBID_`),
  UNIQUE KEY `ID_` (`ID_`),
  KEY `FK_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `FK_EXEC_INSTANCE` (`INSTANCE_`),
  KEY `FK_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `FK_EXEC_PARENT` (`PARENT_`),
  KEY `IDX_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `IDX_EXEC_PARENT` (`PARENT_`),
  KEY `IDX_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `IDX_EXEC_INSTANCE` (`INSTANCE_`),
  CONSTRAINT `FK_EXEC_INSTANCE` FOREIGN KEY (`INSTANCE_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUBPI` FOREIGN KEY (`SUBPROCINST_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUPEREXEC` FOREIGN KEY (`SUPEREXEC_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_execution` */

/*Table structure for table `jbpm4_hist_actinst` */

DROP TABLE IF EXISTS `jbpm4_hist_actinst`;

CREATE TABLE `jbpm4_hist_actinst` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `HPROCI_` bigint(20) default NULL,
  `TYPE_` varchar(255) default NULL,
  `EXECUTION_` varchar(255) default NULL,
  `ACTIVITY_NAME_` varchar(255) default NULL,
  `START_` datetime default NULL,
  `END_` datetime default NULL,
  `DURATION_` bigint(20) default NULL,
  `TRANSITION_` varchar(255) default NULL,
  `NEXTIDX_` int(11) default NULL,
  `HTASK_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_HACTI_HPROCI` (`HPROCI_`),
  KEY `FK_HTI_HTASK` (`HTASK_`),
  KEY `IDX_HTI_HTASK` (`HTASK_`),
  KEY `IDX_HACTI_HPROCI` (`HPROCI_`),
  CONSTRAINT `FK_HACTI_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HTI_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_actinst` */

/*Table structure for table `jbpm4_hist_detail` */

DROP TABLE IF EXISTS `jbpm4_hist_detail`;

CREATE TABLE `jbpm4_hist_detail` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USERID_` varchar(255) default NULL,
  `TIME_` datetime default NULL,
  `HPROCI_` bigint(20) default NULL,
  `HPROCIIDX_` int(11) default NULL,
  `HACTI_` bigint(20) default NULL,
  `HACTIIDX_` int(11) default NULL,
  `HTASK_` bigint(20) default NULL,
  `HTASKIDX_` int(11) default NULL,
  `HVAR_` bigint(20) default NULL,
  `HVARIDX_` int(11) default NULL,
  `MESSAGE_` longtext,
  `OLD_STR_` varchar(255) default NULL,
  `NEW_STR_` varchar(255) default NULL,
  `OLD_INT_` int(11) default NULL,
  `NEW_INT_` int(11) default NULL,
  `OLD_TIME_` datetime default NULL,
  `NEW_TIME_` datetime default NULL,
  `PARENT_` bigint(20) default NULL,
  `PARENT_IDX_` int(11) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_HDETAIL_HVAR` (`HVAR_`),
  KEY `FK_HDETAIL_HPROCI` (`HPROCI_`),
  KEY `FK_HDETAIL_HTASK` (`HTASK_`),
  KEY `FK_HDETAIL_HACTI` (`HACTI_`),
  KEY `IDX_HDET_HVAR` (`HVAR_`),
  KEY `IDX_HDET_HACTI` (`HACTI_`),
  KEY `IDX_HDET_HTASK` (`HTASK_`),
  KEY `IDX_HDET_HPROCI` (`HPROCI_`),
  CONSTRAINT `FK_HDETAIL_HACTI` FOREIGN KEY (`HACTI_`) REFERENCES `jbpm4_hist_actinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HVAR` FOREIGN KEY (`HVAR_`) REFERENCES `jbpm4_hist_var` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_detail` */

/*Table structure for table `jbpm4_hist_procinst` */

DROP TABLE IF EXISTS `jbpm4_hist_procinst`;

CREATE TABLE `jbpm4_hist_procinst` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) default NULL,
  `PROCDEFID_` varchar(255) default NULL,
  `KEY_` varchar(255) default NULL,
  `START_` datetime default NULL,
  `END_` datetime default NULL,
  `DURATION_` bigint(20) default NULL,
  `STATE_` varchar(255) default NULL,
  `ENDACTIVITY_` varchar(255) default NULL,
  `NEXTIDX_` int(11) default NULL,
  PRIMARY KEY  (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_procinst` */

/*Table structure for table `jbpm4_hist_task` */

DROP TABLE IF EXISTS `jbpm4_hist_task`;

CREATE TABLE `jbpm4_hist_task` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `EXECUTION_` varchar(255) default NULL,
  `OUTCOME_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `PRIORITY_` int(11) default NULL,
  `STATE_` varchar(255) default NULL,
  `CREATE_` datetime default NULL,
  `END_` datetime default NULL,
  `DURATION_` bigint(20) default NULL,
  `NEXTIDX_` int(11) default NULL,
  `SUPERTASK_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_HSUPERT_SUB` (`SUPERTASK_`),
  KEY `IDX_HSUPERT_SUB` (`SUPERTASK_`),
  CONSTRAINT `FK_HSUPERT_SUB` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_task` */

/*Table structure for table `jbpm4_hist_var` */

DROP TABLE IF EXISTS `jbpm4_hist_var`;

CREATE TABLE `jbpm4_hist_var` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `PROCINSTID_` varchar(255) default NULL,
  `EXECUTIONID_` varchar(255) default NULL,
  `VARNAME_` varchar(255) default NULL,
  `VALUE_` varchar(255) default NULL,
  `HPROCI_` bigint(20) default NULL,
  `HTASK_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_HVAR_HPROCI` (`HPROCI_`),
  KEY `FK_HVAR_HTASK` (`HTASK_`),
  KEY `IDX_HVAR_HTASK` (`HTASK_`),
  KEY `IDX_HVAR_HPROCI` (`HPROCI_`),
  CONSTRAINT `FK_HVAR_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HVAR_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_var` */

/*Table structure for table `jbpm4_id_group` */

DROP TABLE IF EXISTS `jbpm4_id_group`;

CREATE TABLE `jbpm4_id_group` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) default NULL,
  `NAME_` varchar(255) default NULL,
  `TYPE_` varchar(255) default NULL,
  `PARENT_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_GROUP_PARENT` (`PARENT_`),
  KEY `IDX_GROUP_PARENT` (`PARENT_`),
  CONSTRAINT `FK_GROUP_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_id_group` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_id_group` */

/*Table structure for table `jbpm4_id_membership` */

DROP TABLE IF EXISTS `jbpm4_id_membership`;

CREATE TABLE `jbpm4_id_membership` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USER_` bigint(20) default NULL,
  `GROUP_` bigint(20) default NULL,
  `NAME_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_MEM_GROUP` (`GROUP_`),
  KEY `FK_MEM_USER` (`USER_`),
  KEY `IDX_MEM_GROUP` (`GROUP_`),
  KEY `IDX_MEM_USER` (`USER_`),
  CONSTRAINT `FK_MEM_GROUP` FOREIGN KEY (`GROUP_`) REFERENCES `jbpm4_id_group` (`DBID_`),
  CONSTRAINT `FK_MEM_USER` FOREIGN KEY (`USER_`) REFERENCES `jbpm4_id_user` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_id_membership` */

/*Table structure for table `jbpm4_id_user` */

DROP TABLE IF EXISTS `jbpm4_id_user`;

CREATE TABLE `jbpm4_id_user` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) default NULL,
  `PASSWORD_` varchar(255) default NULL,
  `GIVENNAME_` varchar(255) default NULL,
  `FAMILYNAME_` varchar(255) default NULL,
  `BUSINESSEMAIL_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_id_user` */

/*Table structure for table `jbpm4_job` */

DROP TABLE IF EXISTS `jbpm4_job`;

CREATE TABLE `jbpm4_job` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `DUEDATE_` datetime default NULL,
  `STATE_` varchar(255) default NULL,
  `ISEXCLUSIVE_` bit(1) default NULL,
  `LOCKOWNER_` varchar(255) default NULL,
  `LOCKEXPTIME_` datetime default NULL,
  `EXCEPTION_` longtext,
  `RETRIES_` int(11) default NULL,
  `PROCESSINSTANCE_` bigint(20) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  `CFG_` bigint(20) default NULL,
  `SIGNAL_` varchar(255) default NULL,
  `EVENT_` varchar(255) default NULL,
  `REPEAT_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_JOB_CFG` (`CFG_`),
  KEY `IDX_JOBRETRIES` (`RETRIES_`),
  KEY `IDX_JOBDUEDATE` (`DUEDATE_`),
  KEY `IDX_JOBLOCKEXP` (`LOCKEXPTIME_`),
  KEY `IDX_JOB_CFG` (`CFG_`),
  KEY `IDX_JOB_EXE` (`EXECUTION_`),
  KEY `IDX_JOB_PRINST` (`PROCESSINSTANCE_`),
  CONSTRAINT `FK_JOB_CFG` FOREIGN KEY (`CFG_`) REFERENCES `jbpm4_lob` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_job` */

/*Table structure for table `jbpm4_lob` */

DROP TABLE IF EXISTS `jbpm4_lob`;

CREATE TABLE `jbpm4_lob` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `BLOB_VALUE_` longblob,
  `DEPLOYMENT_` bigint(20) default NULL,
  `NAME_` longtext,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  KEY `IDX_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  CONSTRAINT `FK_LOB_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_lob` */

insert  into `jbpm4_lob`(`DBID_`,`DBVERSION_`,`BLOB_VALUE_`,`DEPLOYMENT_`,`NAME_`) values (2,0,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0Œ\0\0±\0\0\0g]po\0\01jIDATxœíİ}pSçèñÙ?û_Úô¶l¶Û¤Û[ÒæewvKº·w¸İî&İ¹wÓ¤MÓ¦wÛ´iînÒ„4ä¥[‚±!N\ZHb\Z‡šâLx‰‰1*°6°@ÆÆ5~Õú\rc[µïOzŒ¢èåH–üœ—ïgñHGçKšùúÑ›ÿd\n\0\0XÒŸè>\0\0\0‘\0À¢ˆ4\0\0E¤\0°(\"\r\0€Ei\0\0,ŠH\0`QD\Z\0\0‹\"Ò\0\0X‘\0À¢ˆ4\0\0E¤\0°(\"\r\0€Ei\0\0,ŠH\0`QD\Z\0\0‹\"Ò\0\0X‘\0À¢ˆ4\0\0E¤\0°(\"\r\0€Ei\0\0,ŠH\0`QD\Z\0\0‹\"Ò\0\0X‘\0À¢ˆ4\0\0E¤\0°(\"\r\0€Ei\0\0,ŠH\0`QD\Z\0\0‹\"Ò\0\0X‘†“““ƒIÈEjYY÷ñ€D\ZsJåY\Z<666:::222<<<GÊE²‚¬&+“j\0îD¤1G\"y¾råŠ4xGMáÊ\rßûÏÿÍ÷ùÌİO\\ûÍÇ?.CNÈYY(½s¨°¯¯o``@V–MH5\0\"ÒÈºè<\rn}ôÁÜ›¾³dŞ¿åÿí/ßøAÙUÇü»ş0Ü,CNÈYY(É\n²ZáÖG.uuR\rÀ…ˆ4²Kz||üòåË=9åİü£eó_yëá3]‡Á6ƒ!+Èj²²lòÆœîîn™UËNdWt\Z€Kid‘*ôØØØÈÈÈŠßİ{ß³Ÿ-(}È¸ÍñC6‘\rWüî;ûûûeW²C:\rÀ\rˆ4²%RèÎ^ÿS¿ş§G^\\à=õÖL­†l(›?õë<ë?ÑÛÛ;44D§¸‘FVD\n-A•B?³æö®Ñ3™Z®Ñ³Ï¬¹C:}áBkOOàD\ZY¡^‡É[ÿ™Ï²Ğ‘N?òâmyëï‘NË|Zv®^ŸÖı»@¶i˜Of·—/_~}÷/î{ö³ŞS¥³/täyïû½¾d×/ÚÚÚúûûå*äŠ˜Lp*\"\róÉìöÊ•+ÿê{(ï–tß)61ƒ÷‘=˜wó©æÆ¹\"&Ó\0œŠHÃdj\Z=<<\\°eÑ–}¡gÌ×;v¾güœŒ^ù9v>tv,t\"tz\\\r¹è|xÉ¹ŞĞ\n²òùqµBhÃĞòñ«ÇÏÿxÙü‚-û|¾îîn¹\"&Ó\0œŠHÃdÓÓè@àÁå_Z½ùÎËÍˆ\Zr¶s$üS	/WKFNw^>İyuµ×¿<½Nä¬ìö\'Ë¿ØØx¢½½]®ˆÉ4\0§\"Ò0Sd\Z½¥ê—÷,™W×º³u !<>ŸÔéÁĞ	uúÂ`Ãô\nƒ¨uZCK>¸\Z®YáBx“:ÿÎ{–üÙæ}ù---L¦8‘†™ÔÇ®r~{÷Ã+o=Ó{ğL_ÍY5z©q¦WN«%5gB#¼°/|:¼føç¡ğòğÙÈ¥ê¬\\Ôwèá•½ì·w555^¼xQ®N}K÷o\0&#Ò0“”rtt´¯/ô–±œßŞÕxéıÆÎğ¸ôş‰«#¼pŸ,<Ñùşô\n—ö5ªåêDgxµğ\n¡M®hüpó÷¥Ğæİ\\__ç÷ûåêäJ‰4\0ç!Ò0S0éééùîÓ^´ã‰ºöwëÚw††F½:ÑY(?wÖ‡—ÔËiµ¼M­Ùêê’¨…²²ìüŞ§¯;rä°Ïç“«“+•«ÖıÛ€Éˆ4Ì¤^îêêº{ñ\'ß>üò¡óoòo;t~[ÿÃZâ»Æÿ¶:qhzáÛ²rxu‘:«Æôj‘5e¡ìü®Å×<øû3gÎÈÕ©—¥uÿö\0`2\"\r3I)‡††:;;ïzü•§JøŞ’á\rÿœ>Ñ¢În	ŸİâmÙòÑKeyh‰Zèm‰l¾eúlhıĞ\nûN¾şÍÇ?áõhnn–«“+%Ò\0œ‡HÃLÑ‘~¯iış³›÷Ÿ}óêØü‘³-¡³Õgß¬nySØßòfè¬Z8½şÕK[ŞŒŞ¬³·iƒDúÀj\"\rÀÁˆ4Ì‰ôİ‹?¹åà¡¸¶l.n‹J¯tWÕzóÕ<oV—ªÓÓ…¾Zåê«ı^gzÛ7ßúıw-¾V\"}êÔ)\"\rÀ©ˆ4Ì‰ôwŸúó5Ûş]=SíõEÄ~²Z=×}À÷ásİ‘|¸šZ!2ŞŠ:ñ–ìüŞ§®#Ò\0œHÃL‘H?˜{Óıïè÷‹Õ´~äíc‰—/\Z²óŸä~‰§»8‘†™\"‘^Zô/æİóù«úÈ§°\"§/~¸¼>vµ]õ¡ñnìoò`ŞÍK‹ş×ë%Ò\0ŒHÃL‘`½±7ç\'?]}úÍÆKUW¿¨dz4Eı¼ziUSd…Îª7¹úE(W¿ó¤J-9Ğ¼ù\'ç•ìzîàÁƒ|€ƒi˜)òe&>ŸïÇ9_|áÿ;ı\r Óß:=>ü®Ğ¾^\Zwvú‹B£.’%²ÛçÜ¸k×»GáËL\08‘†™\"_ê÷ûW½ù£ûŸûïÓÿNc0ò6>«gÃ—N/Tÿ`#4BK¦7ÿ¿\ruih\'üè¹/ÈÎ+++ëëëùZP\0F¤a¦È?Ø¸xñbcãñŸäŞ$5üëÉÎüÏÊÓWÿåéÈÿ /9}õô‡ÿ¹rzaø?ZÊ’û¥Û8ĞÔÔÄ?Ø\0à`D\ZfŠü«Êîîî–––ßîXrß³Ÿ­nÜÔ3v®wü|ÏØù^ãçB?ÇÎõŒ«ÓçB?ÇÏM_:v¾\'¼fhµrhœsÕMoŞ÷ìõëŞY²gÏ£Gò¯*8‘†ÉdF{åÊ•@ ĞŞŞŞØØ¸¼øÛ¼¸ kôL Ø6ËÑ5zö‘o[^ü­;vÈ4Zv.W!W$WÇ4\Z€#i˜,z2íóùêêê–¼òõgÖÜ¨ÓfTègÖÜ±ä•Ø¶mÛ¾}ûd·²s¦Ñ\0œHÃ|j2=00ĞÑÑÑÜÜì=Z)q•ù´÷Ô[™Ì¡\'ÚdCÙ\\v²}×[{÷î=|ø°ìVv.WÁ4\Z€ƒi˜OM¦/_¾ÜßßßÖÖvòäÉC‡åë¾g?[PúĞL#-›È†²ùÛo¿½gÏÙ•ìPv+;—«`\Z\rÀÁˆ4²Bf·ããã###½½½.\\hjj’¸®¯xúÁ¼›´lş+o=|¦ëq›eYMV–MŠw<½mÛ6™CËNdW²CÙ­ì\\®‚i4\0#ÒÈ\n™İªc\r\r\rõôôHVeú{øğá}ïW¼õğOr¿ô%óş-ÿoùÆÊ¬:æßõ‡áfrBÎÊB¹HVÕdå²··îØ±cß¾}²¹ìDv%;”İª]1à`D\ZÙİi™ø¶µµ577×ÕÕ8p`Ï=›+ŸÏ[ÿE+ÿæ{Ï|æî\'®ıæã—!\'ä¬,”‹d…²²2É³¬,›È†²¹ìDvE¡¸‘FE:=22ÒßßßÑÑáóù\Z=*İ­¬¬ÜµkWEE…”øwŞÙ&\'ä¬,”‹dYMV–MdCÙ\\v\"»¢Ğ\0\\‚H#»T§ÇÇÇ/_¾<00ĞİİİŞŞŞÒÒÒÔÔT__äÈ‘ƒz½ŞQä¬,”‹dYMV–MdCÙ\\v¢^‡¦Ğ\0Ü€H#ëT§\'&&®\\¹2<<¤¸/^ôûı2?>sæLsØ©0uZÊE²‚¬&+Ë&²¡l.;¡Ğ\0ÜƒHcÄ¤Z¦Å}}}===]]]qd¡\\$+Èjä€kiÌ©HªÇÆÆFGGGFF¤ÁCqd¡\\$+Èjä€kiwÉÉÉ‰œˆf¼r:;L¹<z‰JµƒIÈEjòÀµˆ´»DG:áBc;LvQšû©¨¨0ÏæW\0ç!ÒnÓHãj&LxÂ]¥3#O¶Ÿh@`f¿\08‘¶«t‹6ØÊ ÃÆÍ6Ø6ÙT;–i\0ˆG¤í*ãHÏ¤#—\Z/1I§œÇ#Ò\0HÛRÂ)i:O8§ùŒtÊ§»Ó?æLº°°°§§ÇàØ\0À…ˆ´]%{\n:Y†£\'Ä)Ãib¤SîM)))ñûı	/\0×\"Òv5ÓH\'\\Í`eƒR>İ=fãiô‘€Dˆ´]™iƒp\ZïÊx˜‰{Êõ‰4\0Ä#Ò6?yM™Õø\'½÷œrŸ)÷0•ŞLÚãñ´´´<\0¸\r‘v‹dOJG/LÖQƒ¾¦3Og&]^^ŞĞĞöo\0®@¤a	D\Z\0âiX‘€xD\Z–PQQQ[[«û(\0ÀZˆ4,¡*L÷Q\0€µiX‘€xD\Z–@¤ ‘†%x½ŞÊÊJİG\0ÖB¤a	\r\r\råååº\0¬…HÃˆ4\0Ä#Ò°\"\r\0ñˆ4,¡±±qëÖ­º\0¬…HÃü~II‰î£\0\0k!Ò°\"\r\0ñˆ4,H@<\"\rKhoo/..Ö}\0`-D\Zš­\\¹2ç£ŠŠŠt\0X‘†f›6mŠ.ôöíÛKKKu\0X‘†f—.]ÊÍÍU…^±bÅúõëı~¿îƒ\0K ÒĞ¯°°PEzË–-<×\r\0D\Zú544,_¾\\Eº¶¶V÷á\0€UiXB~~¾Dº  `bbB÷±\0€UiXÂŞ½{%Ò»wïÖ} \0`!D\Z– èÜÜÜ¡¡!İ\0B¤\0°(\"\r\0€Ei\0\0,ŠH\0`QD\Z\0\0‹\"Ò\0\0X‘v¬ÉÉ©ÇÛ+¯[¸ªBÆŸ.ZÇ˜ıX_.7æc¥5›ûFÆ‚ºïd\0G¤¨oxìé­G¯Y\\¢=i?Ü°ß×5¨ûàXDÚi<‡}ó–xTBnÉ+[º­nOãdô/N1f9ÔùÒ‘\'\'>öèúçÊë&\'ußñ\0œˆH;‡tâ±Ò\ZUÛ_ÙÕĞ\ZĞ4gæÁ{_Û§nğ;³·oxL÷C\0€ÓiçP…–‰İšÊSÚæ!kõÊ‚Ì­™O0‘vÏaŸ*´4C{·Ü6\ZZê%†§·Õı@\0à(DÚ	.ö¨H¬«>£½XîÕ§»ä/$¹ê[{u?\08‘võD÷k÷jo•›ÇÒmuêIoİ\0ÎA¤mod,¨æp¼SLïèªç3]`2\rÀDÚöÊêıj§½RŒ‡ŞğÊ}±l{î\0‡ Ò¶·È\nC^EƒöD1Ş9Ö&÷Å­+Êt?(\08‘¶=õ­\Z¼©Û\n£30&÷Å¼%İ\n\0A¤mï†Ÿo–04wjOC†z€î\0‡ Ò¶§¾ñŠoı´È Ò\0LD¤mOEZ{œj¨»C÷ƒ€CiÛ#Ò–\ZD\Z€‰ˆ´íiK\r\"\rÀDDÚöˆ´¥‘`\"\"m{DÚRƒH0‘¶=‹DzáÂ…‘ÑŒWNg‡)—§³7\"\rÀˆ´íY0Ò)ËoF!W¥¹\"\rÀ¾ˆ´íitL#«™0á	w•~}­Ğf\"\r ˆ´íitL)\r2œò™êdÛ&›j3“àlDÚö¬iã™täRã%Æ3é”³síƒH0‘¶=+D:ıg¤S>İşifÒ\0HÛöHGOˆS†ÓÄH§Ü‘`wDÚö´G:¦‘ñObD=ş\"ã§»ãÃl©i4‘`.\"m{–´A8Ó	ªñ¶ñ¥\'Ò\0œ‡HÛ¥\"]èd«¥\\\'e¤“½,­ıF Ò\0ÌE¤mO{¤“=)½0YG\rúšÎ,œ™4\0g#Ò¶§=ÒŒèA¤˜ˆHÛ‘¶Ô Ò\0LD¤mH[ji\0&\"Ò¶G¤-5ˆ4\0iÛ#Ò–\ZD\Z€‰ˆ´íiK\r\"\rÀDDÚöˆ´¥‘`\"\"m{DÚRƒH0‘¶½yK<R…æAí}bÈPw‡î\0‡ Ò¶· ¿\\ªP}ºK{Ÿ—Ã3ék—è~P\0p\"m{ß]·OÂ°®úŒö>1œë•ûbş²-º\0‚HÛŞÚªS†{_Û§=QŒ¥Ûêä¾Xäñê~P\0p\"m{çº%{t½¿{D{¥\\>nÉ+“ûbç‰6İ\n\0A¤àÎßì•6ütsöJ¹yl<ä“{aŞÏå± îG\0‡ ÒNPßÚ«&ÓGÎõjo•;Gg`L½¯{mÕ)İ\0ÎA¤â‡¿Û/…¸áç›yÒ{îGÿ`páª\n¹ıä—ONê~(\0p\"í#cAõY¬ùË¶4´´wË=CæĞªĞ2“>×=¨û\0ÀQˆ´søºoYQ¦>§»¦ò”öz¹al©õ«g¹å6¯¿Ğ«û!\0Àiˆ´£Œ\\	ŞşØ´šR/İVÇ«ÔÙÍƒ/í9¡ŞË-Cş6b\r ˆ´ÓLNNUo“B«~¨¡f{ŒÙøvõ{\'x\Z@–ig’l”ó/òxÉs6Æ5‹Kîß°ÓaŸ¶UD\Z\0\0‹\"Ò\0\0X‘\0À¢ˆ4\0\0E¤çNNNNÊ‹âO$\\9Zú×’æ\n‘urâ¤<˜èÕRn\00F¤³(aÀv+ãHgp]Si48şÚ\r®ËàÈ‰4\0Ì‘Î\"ã2Å—rj†‘6¸ºd{‹Ypó4ÿbHVúø?ÒŒtyyyÊu\0ÀUˆôÌr:hÜİ„\'’Õ1Yö¶ÙxŠl¼­ÁÕÿÎÑ&Ü!\0 ‚HÏ@I8ÑL8İŒ^?úD²}¦yES3tôşn›ğIiãß%ş\0\0\0D:]Éâjœ–dL¶ƒÔ¥¥1×2£H\'ÛĞ`ã¿?Ò?`\"\r\01ˆôL\r61˜ãN%Ÿ¹féøıÏt·Ñ\'68áúp²\0\0(Dz2‹tÊÓ	w•NøS^W‘N§ÇñûLrƒ£5Ø!\0@!Ò3Y¤“1ØUüT8á>\r/Ù~Òü{\"z[ãÃH¶yÊ£My\0\0\0\"=3ñeJ?-é2&«Æ‘N˜Õè‹âw’òŒÿ†ˆ9È”¿]²C58\0\0@‘;éL‹cj²|Éœ¬ñéC²+JùgAüïB¤`6ˆ4¬‚H@\"\r« Ò\0ƒHÃ*ˆ4\0Ä Ò°\n\"\r\01ˆ4¬‚H@\"\r« Ò\0ƒHÃ*ˆ4\0Ä Ò°\n\"\r\01ˆ4¬‚H@\"\r« Ò\0ƒHÃ*ˆ4\0Ä Ò°\n\"\r\01ˆ4¬‚H@\"\r« Ò\0ƒHÃ*ˆ4\0Ä ÒĞlåÊ•1ÿyº¨¨H÷A€%ih¶iÓ¦èBoß¾½´´T÷A€%ihvéÒ¥ÜÜ\\Uè+V¬_¿Şï÷ë>(\0°\"\rı\nU¤·lÙÂsİ\0A¤¡_CCÃòåËU¤kkku\0X‘†%äççK¤\n\n&&&t\0X‘†%ìİ»W\"½{÷nİ\0B¤a	2ÎÍÍ\Z\ZÒ} \0`!D\Z\0\0‹\"Ò\0\0X‘\0À¢ˆ4\0\0E¤\0°(\"\r\0€Ei§™œœÚy¼í¹òº…«*düé¢uÓÇ‚ür¹m+­ÙtØ72Ô}Ÿp,\"í}ÃcOo=zÍâí\rsÛøá†ı¾®Aİ÷?\0\"Òá9ì›·Ä£šqK^ÙÒmu{\Zÿ £0xytŠaîP·íK{ND«øØ£ëŸ+¯›œÔı8\0à,DÚö$•Ö¨TÜşÊ®†Ö€ö†¹j4wŞûÚ>uûßù›½}Ãcº\0œƒHÛ*´ÌäÖTÒ^,×™X«\ZdnÍ|\Z€Yˆ´½yûT¡%ÚCåòÑĞ\ZP¯8<½õ¨îÇ\0‡ Ò6v±DUa]õí‰bÈ¨>İ%0É=RßÚ«ûÑÀ	ˆ´©\'ºï\\»W{œ‘±t[zÒ[÷£€i»\ZªIï³Ôèª§7]`2\r`¶ˆ´]•ÕûÕŒM{–1ã¡7¼r×,Û^§û1Àöˆ´]-ò„JWÑ ½IŒ˜ñÎ±6¹kn]Q¦û1Àöˆ´]©¯ÑàMİ1¹kæ-ñè~Œ\0°=\"mW7ü|³” ¹cP{“ñC½]@÷c€íi»R_qÅ·~Zsi\0¦ Òv¥\"­½FŒ„Cİ;º#\0lHÛ‘¶ò Ò\0LA¤íŠH[yi\0¦ Òvez¤.\\˜ò¢ø	W–şµ¤¹Bd…qRLôj)·%Ò\0´#Òv5ûH\'XÂneé®+Ç_»Áu9‘`qDÚ®L‰tÊKc26£HwÔ ı	wn[ƒõÓùƒ€H°,\"mWVxº;Y“e/a›§Èétİào‚”E\'Ò\0¬ŒHÛ•)‘N8ÑL8İŒ^?úD²}¦yED:zÿ	·Mø‹$‹´ñïB¤èE¤íÊ¬H§<Óætjšfó2ˆt²\r\rÖ1şûƒH°2\"mWs3“N>š²ysè4w_hƒú\ZŸ%Ò\0lHÛÕÏ¤g\Zé4§¹3t:=ßgÊi4‘`MDÚ®t½&}9jêi<cN\'ÒÉjmİèk1´ÁŸD\Z€-i»Òõîîè¬\ZG:aV£/ŠßIÊc0ş\"aS†œH°,\"mWsé˜†ÅÔ:eù’8Yãg:5—r…ø_H°,\"mW|w·•‘`\n\"mWDÚÊƒH0‘¶+\"måA¤˜‚HÛ‘¶ò Ò\0LA¤íŠH[yi\0¦ ÒvE¤­<ˆ4\0Si»š·Ä#hîÔ$FüP÷îÇ\0Û#Òvµ ¿\\2P}ºK{ñCîšk—è~Œ\0°=\"mWß]·OJ°®úŒö 1bÆ‘s½r×Ì_¶E÷c€íi»Z[uJJpïkû´7‰3–n«“»f‘Ç«û1Àöˆ´]ë”|ìÑõşîíYbD[òÊä®Ùy¢M÷c€íi»ó7{%?İ\\£=KŒÈØxÈ\'wÊ¼%ËcAİ\0¶G¤m¬¾µWM¦œëÕ\'†ŒÎÀ˜z_÷ÚªSº\0œ€HÛÛ·_’pÃÏ7ó¤·öÑ?\\¸ªBîùå““º\0HÛÛÈXP}kş²-\r­í¡rí9´*´Ì¤Ïuê~\\\0p\"m{¾®Á[V”©æ®©<¥=W.[jıêYn¹ê/ôê~D\0p\"í#W‚÷†?6­¦ÔK·Õñ*õŒæÁ—öœPïå–!*1‡`.\"í““SÇÛ¤Ğ*j¨éÃô;¯~ï¯C0‘véDÙ1ÿ\"—<ÏÁ¸fqÉıöo:ìãÓV\0²„H\0`QD\Z\0\0‹\"Ò\0\0X‘\0À¢ˆ4\0\0E¤‘]999)/Š?‘påh×’p\'jaNœ”W½ZÊmÀ\\D\Z&K¹„m‹_IXš‘N¶ÂTòÌ§ìz´–––©$:i\0s‰HÃdÆõŠŸÑÆŸM¶a²fô×€ÁAÆLÂ+%Ò\0æ‘F\n³¬‘qw#\'bšpeƒHï<ÙÕ%ÜDılhhˆŸR§<\00‘F\nÔ(ÙÔ6¾v	×7ØgúG˜,ÉÉfØñ \'<OyyyÂœi\0s€HÃH²¸\Z\'*a§’LI§âfÒGb|)ÿ H˜Ûøuâ©)u:\0&\"ÒH!aíâ—Ç¬cÎdg3‹tš¿BÂ\'üÕbÎ\r\r©)õèèèì\0ÒG¤‘Bf‘Ny:fWiF:Ù\n1å˜+MÙcƒã‰,lhh(,,”)uÊC\0³i¤™tü®¢WHVĞ„iL–ç„k\Z|Ìşã×QSj\"\r`Îi¤_¯ôeÜòè¦é„¥7Xhü÷Áo4£Ã\0€l ÒÈ®t¦ÅÆî„ëÄì<=$\\-a­“­³PfÕ2·ÎèV€´iX‚ç¦---………\r\r\rº€ciX‚#-FGGËËË™RÈ\"\rK°i¤¦Ô\0²„HÃlé)¦Ô\0²ƒHÃìi…)5\0siè\'³O)ôªU«tˆ	˜R0‘†~@@\"]PP û@L£¦Ôµµµº€½ièç¼HO…§ÔÅÅÅ===º€]ièçÈH+íííEEE^¯W÷\0°%\"\rıi111QYYÉ”\Z@ˆ4ôsv¤¦Ô\02@¤¡Ÿ\"=Å”\ZÀÌièç’H+L©¤HC?WEzŠ)5€´ièç¶H+L©¤D¤¡Ÿ;#=uuJ]XX(ÁÖ},\0¬ˆHC?×FZééé)..–ZK³u\0k!ÒĞÏå‘V¼^oQQQdJ}ñâÅ7ê=*\0zièG¤•è)õ¯~õ«¼¼¼²²2İ@\'\"\rıˆt4™R¿üòËêwÊ‰––İG@\"\rıÚÛÛ%HEEEºÄFGGes•tš\'½×\"ÒĞÏï÷KJJJtˆ%”––æDY¹r%Oz®E¤¡‘¶víZ™=ææææççŸ>}Z÷qĞ€HC?\"-È\rRSSSUU%·‰¤ZnœçŸ^÷qĞ€HC?\"\r\0	ièG¤ !\"\rıˆ4\0$D¤¡‘€„ˆ4ô#Ò\0‘†~D\Z\0\"ÒĞHONNí<Şö\\yİÂU2ştÑ:FúcA~¹Üh•Öl:ìê¾33ièçæH÷\r=½õè5‹K´§Î1ã‡öûºuß±€9ˆ4ôsm¤=‡}ó–xTZnÉ+[º­nOãdô/N1ÒêF{iÏ‰È“{tısåu““ºï``Öˆ42!M58k¼r<FZúñXi*Êí¯ìjh\rhO3FsÇà½¯íS7ì¿ÙÛ7<¦û®f…H#Æ‘ÎI%fo.Œ´*´LøÖTÒ6ç\r™X«WdnÍ|\Z¶F¤1c‘Ê¦à©4fÒÍÍÍ²Nii©ùÇjIÃ>Uhi‰ö9u4´ÔK	Oo=ªû2G¤İ.eA“­½aüNÒŸF‹††Y^^^>Ãc·¥‹ı#*ëªÏh/™³Gõé.ùKHnêúÖ^İw;!\"ívD:¾µ	#òt„«\"­è¾sí^í\rsÃXº­N=é­ûn2D¤]-YqS–[­`0Q&Ò	ŒÕÜwŠÍÍèªç-]`2\r[\"Òn—lNlĞéH¡wËÓİñÊêıjb§½^î½á•Û|Ùö:İw>	\"ív3tüŒÙ8ÀE¸\'Ò‹<¡`äU4hO—{Æ;ÇÚä6¿uE™î;È‘v»fÒÉ6OöÊtÊ™·{\"­¾mƒ7uÏåèŒÉm>o‰G÷d‚H#öEè4_“OoÊŞ\'Û­{\"}ÃÏ7K0š;µ§ËUC½@÷d‚Hcf=ásàÑë$Û\\qO¤Õ7añ­ŸD\ZH‘†~n‹´öh¹m¨›]÷d‚HC?\"Í Ò@BD\Zúi‘\"ÒĞHg6.\\˜ò¢ø	Wfp-	w¢.Œ“òŠ¢WK¹-‘†;ièG¤Ó¯²±ÙGÚ ùÆ—\Zt=a’×$Ò€B¤¡‘N?Ò)/IİŒ\"şŸ3ık şØ^)‘bièWSS#‘®¨pşAĞşt·q‰Ó¼\nƒ0\'ÛU²¿ˆ4`ŒHC¿ªª*‰´üÔ} Y7ûH\'›ÚÆ×.zıèÉö™şßÉ’œl†Ón\"\rD#ÒĞHÏ(Ò)OÇ´Ù ·éD:Í?’ÍcÖ1Ş‘bièG¤giãp¦?g5¥‹Ñ…Nòdg‰4‘†~Dz¦Q4>°Í)»h0™N¶Ÿtz)§ÑD\Zˆ ÒĞHÏ(ÒÆ3é„m¬¬ É’™fïÎİ“­`ğ‘bièG¤3vÊ‹bòlé„¥7X˜ÎLÚ ÿi‘†›ièG¤MŒtLçbj²\Z4~µ„µN¶N:WA¤áBD\ZúiFV‘†}ièG¤D\ZHˆHC?\"Í Ò@BD\Zúi‘\"ÒĞH3ˆ4‘†~î‰ô¼%©EsÇ ön¹j¨›]÷d‚HC?÷DzA~¹Ô¢út—ön¹jÈm~Íâİw>	\"\rı*++%Ò^¯W÷dİw×í“`¬«>£½[îGÎõÊm>Ùİw>	\"\rıÊËË%Ò\r\r\rº$ëÖV’`ÜûÚ>íérÏXº­NnóEçÿ	G\"ÒĞÏ=‘>×=(ÁøØ£ëıİ#Úëå’qK^™Üæ;O´é¾óLièçH‹;³WšñÓÍ5Úëå†±ñOníyK<—Ç‚ºïy D\Zú¹*Òõ­½j2}ä\\¯ö†9{tÆÔûº×VÒ}·\"ÒĞÏU‘?üİ~)Ç\r?ßÌ“ŞÙıƒÁ…«*äv^_>9©û.2E¤¡ŸÛ\"=2TŸÅš¿lKCk@{Ïœ7d­\n-3ésİƒºïp sD\Zú¹-ÒÂ×5xËŠ2õùİ5•§´WÍIcK­_=Ë-·mı…^İw50+D\Zú¹0ÒbäJğŞğÇ¦Õ”zé¶:^¥Íhî|iÏ	õ^nò7sh8\0‘†~îŒ´˜œœª8Ş&…V]QCÍéøpõ{\'x\ZÎ@¤¡Ÿk#­HNÊùy¼äy6ãšÅ%÷oØ¿é°O[ÁIˆ4ôsy¤ \"\rıˆ4\0$D¤¡‘€„ˆ4ô#Ò\0‘†~[·n%Ò\0HC¿’’‰´ßï×} \0`-D\ZúérSTTTè>\n\0úièG¤#†††JKKåéééÑ},\0ô#ÒĞH‹‰‰‰ªªª‚‚‚ææfİÇÀ*ˆ4ôË^¤e·gWKæÂÂB‰´¤Z×1\0° \"\rıtE:\'Ó\'^OOüú¥¥¥@`®€½iè—¥HG*›f€çx&=::º{÷n™@»üy~\0ˆ4ôK\'Ò3-ht¡\rv¢k\Z][[+y®©©Éêµ\0°;\"\rı²éøÖ&ŒtÊÓæjoo/**ª¨¨\Z\ZÊÒU\0p\"\rıRF:YqS¦T­`0QËHK•·nİZ\\\\ÜÙÙiúÎ8‘†~Ì¤V6á&Æ¹›§»\'&&¼^oaaacc£‰»àxD\Zúe#Òñ3fã\0gï¥è––Éseeåèè¨é;àlD\Zúei&lód¯L§3ó‘O¯\"\rıÒüVÌL7Í×¤ãÓ›²÷³ï´Lšeê\\TT$ÓèYî\n€›iè—ÏI\',zÂçÀ£×I¶ùŒ466z½^¾>À,ièWPP EtÀsÂíííÅÅÅ[·nåãU\0LA¤¡Ÿ\"-U®¨¨BK§u\0ç ÒĞÏî‘®©©),,¬­­Õ} \0œ†HC?ûFZ}¼j÷îİ|¼\n@6iègÇHËÑ–––z<İÇÀ±ˆ4ô³W¤\'&&ªªªdİÜÜ¬ûX\08‘†~6Štcc£­DšW˜D\ZúÙ\"Ò%%%|¼\nÀ\\\"ÒĞiåÊ•1_3RTT¤û bîŞ½[ÌÜ¯[€”ˆ4tÚ´iSt¡·oß^ZZªû >¢¶¶V&ú555º€iètéÒ¥ÜÜ\\Uè¼¼¼õë×[g¶*G\"³çŠŠ\n>^@\"\rÍ\nU¤=Eë\Z\ZÚºukIIIgg§îcàjD\Zš544¨HoÚ´Iû—vMLLx½Ş‚‚‚ÆÆF½G\0SD\ZV°|ùr‰ôË/¿¬÷sMÍÍÍ2­¯¬¬äãU\0,‚HC¿üü|õ®1]ĞÓÓãñxJKK-ş10\0nC¤¡ŸÏç[µj•–@ÊÔY&Ğ---sí\0`ŒHÃ½jkk%Ï^¯W÷\0@bD\ZnÔŞŞ^\\\\\\QQÁ×‡°2\"\rw‘*———K¡¥Óº\0R Òp¯×[XXØĞĞ û@\0 -D\ZúM&aâU´´´¨Wñõa\0l„HCÕà?†ƒÁ‰‰‰ñ8²P.RëÌ¨Ù§OŸŞ¸qc$Æ@ÀÖÓÓ“µ_\0²‚HcNEò,\r<w´¾ñ½ªı¿}½êµ\rjÈÙ³‡zûFFF$´W®\\QÁN3ÕRâ¼¼¼çŸşİwß•­øx\0[#Ò˜#‘<K›nÜ\\üıŸ,¹nşãŸ¸áê¸şê}òºù¯İó‰wWGÇĞĞ[j2Õõ^xA}É¨œX½zµ×ëåëÃ\0Ø‘Æ\\Py–ĞÊ\\yé_•øã×ËxòÏ¾ğâWï(øÆ·<<\"CNÈYY¨.•Õdåİ¯¼ÚÛİŸê˜«åk×®ü×ËåË—¿úê«Z~Y\00‘FÖ©Bwœ<½òïÿ)4WşøõO}æÆ\r÷?|üuÏå³gƒ.„F[[h„OËÂc%›d…H­ó¾üµ“Õ¿ïëë“TËty||<¾Ó¥¥¥9õ«_ıŠ7r°5\"ìR…ş`Çî\'ÿüFÉíâk?Wúï?k^h›s²q¡MV“éµlšs_7¿ç­îîî™RÇtúı÷ßWÿ‘úå—_Ş¼yó†\r<Ï¯ıëÂÂB½¿>\0Ì‘FE\n½ø¿}^B»ò¶¯wTHÑæ¸Ñşş~™I‡ÿÉ¿ÜWâéìì1V/EËÔ¹¤¤¤¼¼¼ªªJæĞ~¿Ÿ˜ÀÖˆ4²Eºñ½*Uè×¾ı¯£gÎÎ´ĞjŒ=[øÍï©Nï÷”&ì4\08‘FVD^‡~æ†›%®ßøÖøùó™Zq¿õw‡^Ïş‹UUÓi\0n@¤‘RÍÑÑÑ¼ÿ YÍ¹å76&Lo÷á£Uù«_»çkî¸[\rÏ),J¸òpSSÎM_	íğÖ¯inîêê\Zk™˜˜ Ò\0‰HÃ|j\Z½gõ\Z	êŸú|GUu‚â<)=|ë#ã×?{ıMïø­:öxâÓ%ëlüE®ßïïîî\Z\Zºrå\n“i\0D¤a>õ%Ï\\“Ôô\'ÿ#¾µm•Uêipã!3ìá¦“1Ûn{ì)õ!®õõ²§¾¾Ğw“©Oëş½ÀdD\Z&SÓèı¿}]fÉ’ÒÁNŒ;=üïîN§Ğjüò+ÿ8æóEo>x¼ñÉëæËEÿùüK>Ÿ¯³³s``€É4\0G\"Ò0™”Ræµÿümé¨çG†OŒÒİ4­†ÌÅ?²‡¦“î8ô®ÿù¦ÆÆèÉ4‘à0D\Z&SÏu?ñÉ¿”cS ®.PWşY¨­İ³4oF…VÃ¿s—l;½ŸÚºã7É4}ñµŸ««9,“é®®®¡¡!õ6oİ¿=\0˜‰HÃdRÊ³‡„şIÆŸ}¡{ÿşîêêèúâî™GZfä1ûQoÛQòÆ™Ó§;::\"ÏxëşíÀLD\Z&ƒ‡Ş*“H¿øÕ;.îŞ\Z»v«q¤°H}w÷LÇÒù×^±3´“«;\\yÛ×eyéª‚¦¦¦È3ŞrÕº{\00‘†™ÔÒU¯m¿öí=ÿöÛÑã\'ÿ#ƒB«q¾¬,zWßø–ú ÖñãÇı~?/Kp$\"\r3I#ÇÇÇ÷½º^\"]|ßgß|3zx¾ÿãŒ#}ğ…UÑ»’¿\0T¤;æóùº»»Õ·i\0NB¤a¦˜H7¿şzô˜e¤£wéÈ{Çˆ4\0\'!Ò0St¤ïünÌLZ}Ifãì¦MÑ»Š<İM¤8‘†™T¤#o‹yMz6o;_ö‘]åıİÿz<ü}&D\Z€ƒi˜IEúäƒê#XíÓïÇ¾:2üÖ÷<ıñğ[»ÛwîŒ|‹Hp0\"\r3©Hzû‡¿ÌäÈš¢˜Ï7göe&çËÊ¢w¢fä‹¯ı\\Ue%oà`D\ZfRÁ’^ªÿı¼áş‡µ¡ïû¯¨Ÿ/|ùk3*ô¶Ç\n×XíÕ//«-¾ïY¾ò¶¯üıïù\0#Ò0Y0”^î~åÕÇÃÿ«ªçPMÌwwûÊw¨•Îxåşe¸±)zóšÃêl¬êäËL\08‘†ÉşøÇ?^¹r¥«£ã©¿ø¢¤´ôßó_°Bÿkç®œ›nKYèĞDüXCÌ¶¡ÿBÎå®]GmnnækA8‘†ÉT¤Gÿóù—¤¦O|êómï½ÿ/¥‡›š>‘•ó¥Û¬)\n^Hğ¨Õ[ÆŠù™L££ß5F¤8‘†É\"/Kw_º”~ù9çÖ¯76ÅwZÆPSSUşj™¯¹ı.5¤ÜÇJ6/\\HÔõ“97ÿ½úDVUe¥L££Ÿëæi\0ÎC¤a2)¥šLœ<tX=é½ú»Çış„Re5’¬0ŞÚúâ?}S=Ñ½£Ä#ÓèãÇË4º³³3ò\\7‘à0D\Zæ“^ËìVæ¸‡J·ªcüó·‡›Ï§Sá“\'W‡¿blñµŸ+]U …V¯FG¦Ñü3i\0D¤a¾Èdzhh¨»»{¿§Tu:ç¦¯tì?0ÓBwT{—~qA¤ĞPOtûı~ÙyäÕh¦Ñ\0œ‡H#+Ô+Ó£££]]]Òé§şâÆĞûÈ>ıW~ôğpÓÉtò<x¼QVVï{ê37FZ½_Lv.WÁ«Ñ\0œŠH#+ÔdZ=é:;;UUçÜúUõæí%×Í/şşÇ^sìÜ¹ø6ËB¹HVPŸ‡VoöŞQâ‰.´ìPvy¢›Hp$\"l‰ïô™ææÿ|ş¥g>û¥éZ}â©uŞ—¿¶ú»eÆ,CNÈYY(©ud½ş©_TUVª\\Qh\0®B¤‘E1îêêjmm=Q__ºª`õíw=ñ©Ï\'ûœ´\\ôâ×ÿeã/r+wíRo;~üxss³l.;¡Ğ\0\\‚H#»¢;=00ĞİİİÖÖ&³á¦ÆÆÚC5•Ò·×¼*19»£äĞÔù÷kk«<«	´l(›ËN(4\0— ÒÈºH§GGG‡††úúúd6¬R}æôi	ğñ°cQÔ¹HVPy–MdCÙ\\vB¡¸‘Æ\\P˜˜}cèÈH$ÕàÖÖV¿ßï‹\"ge¡\\$+¨<«	´l.;¡Ğ\0\\‚HcL†Å¤ZÒÛßß/\rîë\nS§e¡\\$+Èj1y¦Ğ\0\\‚HcNE§z||\\ÕZŠY(+Èjä€;ihIµƒ*Ø1d¡\\¤Ö!Ï\0Ü‰HC¿É$t\0höÿaîƒCÑM\0\0\0\0IEND®B`‚',1,'deviceFee.png'),(3,0,'<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<process name=\"è®¾å¤‡è´¹ç”¨æŠ¥é”€\" xmlns=\"http://jbpm.org/4.4/jpdl\">\r\n   <start g=\"193,29,48,48\" name=\"å¼€å§‹\">\r\n      <transition g=\"-71,-17\" name=\"to æäº¤ç”³è¯·\" to=\"æäº¤ç”³è¯·\"/>\r\n   </start>\r\n   <end g=\"193,385,48,48\" name=\"ç»“æŸ\"/>\r\n   <task assignee=\"#{application.elecUser.logonName}\" g=\"144,109,147,63\" name=\"æäº¤ç”³è¯·\">\r\n      <transition g=\"-119,-17\" name=\"to å®¡æ‰¹ã€éƒ¨é—¨ç»ç†ã€‘\" to=\"å®¡æ‰¹ã€éƒ¨é—¨ç»ç†ã€‘\"/>\r\n   </task>\r\n   <task assignee=\"#{departmentManager}\" g=\"141,204,152,59\" name=\"å®¡æ‰¹ã€éƒ¨é—¨ç»ç†ã€‘\">\r\n      <transition g=\"-107,-17\" name=\"to å®¡æ‰¹ã€æ€»ç»ç†ã€‘\" to=\"å®¡æ‰¹ã€æ€»ç»ç†ã€‘\"/>\n      <transition name=\"to ç»“æŸ\" to=\"ç»“æŸ\" g=\"-47,-17\"/>\r\n   </task>\r\n   <task assignee=\"#{generalManager}\" g=\"287,297,139,58\" name=\"å®¡æ‰¹ã€æ€»ç»ç†ã€‘\">\r\n      <transition g=\"-47,-17\" name=\"to ç»“æŸ\" to=\"ç»“æŸ\"/>\r\n   </task>\r\n</process>',1,'deviceFee.jpdl.xml'),(9,0,'<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<process name=\"è®¾å¤‡è´­ç½®è®¡åˆ’\" xmlns=\"http://jbpm.org/4.4/jpdl\">\n   <start name=\"å¼€å§‹\" g=\"193,29,48,48\">\n      <transition name=\"to æäº¤ç”³è¯·\" to=\"æäº¤ç”³è¯·\" g=\"-71,-17\"/>\n   </start>\n   <end name=\"ç»“æŸ\" g=\"193,385,48,48\"/>\n   <task name=\"æäº¤ç”³è¯·\" g=\"144,109,147,63\" assignee=\"#{application.elecUser.logonName}\">\n      <transition name=\"to å®¡æ‰¹ã€éƒ¨é—¨ç»ç†ã€‘\" to=\"å®¡æ‰¹ã€éƒ¨é—¨ç»ç†ã€‘\" g=\"-119,-17\"/>\n   </task>\n   <task name=\"å®¡æ‰¹ã€éƒ¨é—¨ç»ç†ã€‘\" g=\"141,204,152,59\" assignee=\"#{departmentManager}\">\n      <transition name=\"to å®¡æ‰¹ã€æ€»ç»ç†ã€‘\" to=\"å®¡æ‰¹ã€æ€»ç»ç†ã€‘\" g=\"-107,-17\"/>\n   </task>\n   <task name=\"å®¡æ‰¹ã€æ€»ç»ç†ã€‘\" g=\"148,295,139,58\" assignee=\"#{generalManager}\">\n      <transition name=\"to ç»“æŸ\" to=\"ç»“æŸ\" g=\"-47,-17\"/>\n   </task>\r\n</process>',8,'devicePlan.jpdl.xml'),(10,0,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0Œ\0\0±\0\0\0g]po\0\0-CIDATxœíİT”÷èñ=§æ¿´émm¶mÒí[ÓæÇîÙ­éŞŞãm»MºçŞ¦I›š¦w›¤ùåİ&MLc~5F-I£=d5©\Z©$E*Š«\"QQ@¢ ˆ¢ù½ü˜âıÌ|q2™ÏøÀ÷û<ó~ïá3ßç™fÎyó_üÍ%\0\0`¤¿Ñ}\0\0\0 6\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\rÆÇÇÿ\Zä÷ûÇâ‹Ô™¬ûx@\"¥ò,\röù|ƒƒƒQäL¹H&È4™Lª¤&\"ÊóÅ‹¥ÁÛ®Z¶şgÿïwÿø³ç¾x×S×üèÉOËò­œ)½{`Uwww__ŸL–MH5€D¤1íÂóÜ?Ğ·jóã§ßøÓ…³ş#óŸ~ÿÎ/\n÷-?âİùÑ`ƒ9!ßÊ™r‘Li«6?v¡½­··—THAD\ZÓKzttthh(wwÚ#7=°xök›ml?Ğëo±2A¦ÉdÙäİi²ª–È®è4€A¤1T¡GFF|>ßÒ?Í»÷ù/gå?bİæè!›È†KÿôÓÖÖó===²+Ù!\nˆ4¦K¨Ğm]Şgşóû½2§âÄ¦ÉZ\rÙP6æ?ÿõ”÷XWW×ÀÀ\0\nˆ4¦E¨ĞT)ôs+oknœZ¡Õh>õÜÊÛ¥ÓgÏ6wvvÒi\0©€HcZ¨ç¡}>_FöOe|……uú±WnÍÈ¾[:-ëiÙ¹z~Z÷Ï\n\0Ó…HÃ~²º\Z\Zzk×‹÷>ÿåŠùW^èĞãŞ÷>]ÎÎ[ZZzzzä*äŠXLp+\"\rûÉêöâÅ‹½ÿÕıHÆÍÉ¾Rll¯#{8ã¦\ru­­­}}}rE,¦¸‘†ÍÔ2zpp0«`ş‹¿Ö9âé\Z9Ó9Ú$£K¾œ	|;88=ª†\\t&xNSW`‚L>Ó9ª&6œ?zùÌÑ3¿\\<;«àQÇÓÑÑ!WÄb\Z€[iØlbİÛûğ’o¬Øø`ÛPÃGaC¾mó¿ªo}ÁóÕ9¾“mC\'Û.OûxşĞÄœĞ·²Û‡–|½®îØ¹sçäŠXLp+\"\r;…–Ñe¿¿{á¬êæÍ}µÁñ¡|=«N÷N¨Ógûk\'&ô¨æ4Îùğl`|<94álp“jï»~aãÌÓ§O³˜àbD\ZvRo»êëëK{ó®G—İÒØµ¿±ûà)5º¨ÑØ%§Õ9#xfwğtpfğëàùÁoC—ªoå¢î.û‡ÅoŞY__wşüy¹:õv,İ?=\0ØŒHÃNRÊáááîîÀKÆÒŞ¼³îÂûumÁqáıc—GğÌ=ræ±¶÷\'&\\ØS§ÎW\'Ú‚Ó‚›\\>Q÷ñæïK¡Î¸©¦¦ÚëõÊÕÉ•i\0îC¤a\'¿ßïóù:;;ïyöoWoªúÜ_ªÏí~\rŒ\Zu¢%t¦|İQ<§FN«ó[ÔœĞV—Ï	;S&ËÎç={í¡C•G®N®T®Z÷O\06#Ò°“zBº½½ı®ŸİRù‡g¶ğn=pfëAïÇ#pwËAïuâÀÄ™[drp‚ºH}«ÆÄ´ĞL9Sv~ç‚köïÿ ±±Q®N=-­û§\0›iØIJ900ĞÖÖvç“Ÿ)=‘³Ï³IFEğëÄ‰ÓêÛ‚à·§>y©œ8GYq:´yÁÄ·ù	{¿õ£\'?SQ±¯¡¡A®N®”Hp\"\r;…Gú½úì½§6î=µáòØø‰oO¾-?µ¡üôubïé\roÕ™ó/_zzCø~dNIız‰ô¾}åD\Z€‹iØ)é»|¶`ÿË¸Ş8QÜÓ*½Ò]Uë—ó¼Q]ªNOúr•Ë/÷{bÎÄ¶6}ğò®‘HŸ8q‚Hp+\"\r;…\"}Ï3»rë¯Ô#ÕĞƒØV«Çº÷y>~¬;4aßÇÓÔ„ĞØvb“ì|Ş3×i\0îF¤a§P¤N¿ñ·«ÿwøëÅ6âåc±Ï±>?lÈÎJÿwp7\"\r;…\"½hõÎ¸1âıW5¡wa…NŸÿøüšÈi;kã/‘#¸ÉÃ7-Zı***ˆ4\0#Ò°Sè-Xï”¤İıôçËOn¨»PvùƒJ&F}Ø×Ë—–Õ‡&´•}¼ÉåB¹ü™\'eêœ}\rï~zVÎÎ—öïßÏ[°\0¸‘†Bfâñx~™öõ—ßù¿Ÿ\0:ñÉ ããÏ\níşä¥QßN|PhØErìö—i7ìÜù—C‡ña&\0\\ŒHÃN¡õz½Ë7<pÿKÿ}âßiô‡şÁÆ‡ÓqùÛà¥gª°s&6ìş¿\rui`\'>ğÒ×dç¥¥¥555|,(\0#Ò°Sèlœ?¾®îèCé7JMCÿz²íÿ³òäåÿAy2ô?(ƒçœ¼|úãÿ\\9qfğ?ZÊJÿÆöâmûöí«¯¯çl\0p1\"\r;…şUeGGÇéÓ§ßÜ¾ğŞç¿\\^—×9ÒÔ5z¦säL—ŒÑ¦À×‘¦ÎQuº)ğu´iâÒ‘3Á™MÔäÀ8Måõî}şºµï.Ü½{÷áÃ‡ùW•\0ÜHÃf²¢½xñbooï¹sçêêê–¬ûÉc¯Ìinìõ·\\áh>õØ+·.Y÷ãíÛ·Ë2Zv.W!W$WÇ2\Z€+iØ,|1íñxª««¾ö½çVŞ«Óg\'UèçVŞ¾ğµïnİºuÏ=²[Ù9Ëh\0îF¤a?µ˜îëëkmmmhh¨8\\*q•õtÅ‰MSYCµÈ†²¹ìdÛÎM%%%•••²[Ù¹\\Ëh\0.F¤a?µ˜\Z\Zêééiii9~üøÒ×ıøŞç¿œ•ÿÈd#-›È†²ù–-[vïŞ-»’Êneçr,£¸‘Æ´Õíèè¨Ïçëêê:{öl}}½Ä5»øÙ‡3nz`ñì×6=ÚØ~ÀºÍ2A¦ÉdÙdİög·nİ*khÙ‰ìJv(»•ËU°ŒàbD\ZÓBV·êíX’UYşVVVîy¿4kÓ£¥ã§gıGæ?ış_î[~Ä»ó£ÁrB¾•3å\"™ Ódrá–ÍÛ·oß³gl.;‘]Ée·êmW,£¸‘Æt	ï´,|[ZZ\Z\Z\Zª««÷íÛ·{÷î¥¿ËÈşéüeÿø³ç¾x×S×üèÉOËò­œ)É„ÂÂBÉ³L–MdCÙ\\v\"»¢Ğ\0R‘Æ4\nuÚçóõôô´¶¶z<ºººÃ‡KwKKKwîÜY\\\\,%~÷İw·É	ùVÎ”‹d‚L“É²‰l(›ËNdW\Z@Š Ò˜^ªÓ£££CCC}}}çÎ;}út}}}MMÍ¡C‡öïß_QQ±/Œ|+gÊE2A¦ÉdÙD6”Íe\'êyh\n\r iL;Õé±±±‹/öööJqÏŸ?ïõze}ÜØØØt\"H–3å\"™ Ód²l\"Êæ²\n\r uiÌˆTË²¸»»»³³³½½½-Šœ)É™F¤,\"JõÈÈÈğğ°Ïç“D‘3å\"™ ÓÈ3€”E¤SKZZZèD8ëÉÉì0áùáç¨T¿ß?‡\\¤æg\0)‹H§–ğHÇ<ÓšÅã]”ä~Š‹‹%ÌWò£€ûéTÑHëjÆLxÌ]%³\"·ŸpYYY½½½“û‘\0Àíˆ´S%óX´ÅV¶n¶Å¶ñ–ÚÉ´œH@4\"íTS´õJ:t©õ9Ö+é„«óhD\Z\0¢iGŠ¹$Mæç$‘Nøpwò§“\\I¯Zµª³³ÓâØ\0 i§Š÷t¼‡/ˆ†ÓÆH\'Ü›’““ãõzc^\0)‹H;Õd#sšÅd‹		î¾fëeô%\"\r\0±i§²+Òá´Ş•õœˆ…{ÂùD\Z\0¢i‹^¼&ÌjôƒŞÖ{N¸Ï„{¸”ÜJ:77÷ôéÓÖ\0©†H§ŠxJ‡Ÿ¯£}MfÌJº¨¨¨¶¶6éŸ\0R‘†ˆ4\0D#Ò0‘€hD\ZF(..®ªªÒ}\0`\"\r#”é>\n\00‘†ˆ4\0D#Ò0‘€hD\ZF¨¨¨(--Õ}\0`\"\r#ÔÖÖé>\n\00‘†ˆ4\0D#Ò0‘€hD\ZF¨««Û¼y³î£\0\0³iÁëõæääè>\n\00‘†ˆ4\0D#Ò0‘€hD\ZF8wîÜºuët\0˜…HC³eË–¥}ÒêÕ«u\0HC³¼¼¼ğBoÛ¶-??_÷A€ˆ44»páBzzº*ôÒ¥K³³³½^¯îƒ\0#iè·jÕ*é‚‚ë€\"\rıjkk—,Y¢\"]UU¥ûp\0ÀD\ZFÈÌÌ”Hgeeé>\00‘†JJJ$Ò»víÒ} \0`\"\r#È:==}``@÷\0€Aˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH»Öøø¥G[^*ª»¼XÆ§æ¯e\\ù˜“Y$¿Ì\'òæUz|#~İ72\0—#Ò.Ô=8òìæÃW/ÈÑ4×ûÖïõ´÷ë¾Á¸‘v›ÜJÏ¬…¹*!7g.ÚZ½»î#=ış¡áKŒ+ê—ùêîc¡\'®z<û¥¢êñqİ7<\07\"Òî!x\"ÿ *Çm¯í¬mîÕ4w†Öşykö¨_øo”tè¾\0p\"íªĞ²°[YzB{ÀRgÈÂZ=³ kkÖÓ\0ìE¤]\"·Ò£\n-ÍĞŞ­TµÍ½ê)†g7Ö}G\0à*DÚ\rÎ÷øT$Ö–7j/Vjò“íò’Ü5Í]ºï\0ÜƒH»z û×K´·*•Ç¢­ÕêAoİw\0îA¤Ï7âWk8^)¦wôôûÕãGÎ²˜`\"íx…5^µ€Ó^)Æ#ïTÈm±x[µî;\0— Ò7?7†ŒâZí‰b¼{¤En‹[–ê¾S\0p	\"íxêS5xQ·	£­wDn‹Ysuß)\0¸‘v¼ë_Ø(ahhí×(†õú\0İw\n\0.A¤O}âŸúiÈ Ò\0lD¤OEZ{œj¨›C÷€KiÇ#ÒF\r\"\rÀFDÚñˆ´QƒH°‘v<\"mÔ Ò\0lD¤H5ˆ4\0iÇ3$ÒsçÎ\rg=9™&<?™½i\0ND¤ÏÀH\',w´I…\\]”ä~ˆ4\0ç\"Ò§=Ò´®fÌ„ÇÜUòõ5¡ÍD\ZÀt Ò§=Ò¥´ÈpÂGªãmo©ÍJ\Z€»iÇ3\'ÒÖ+éĞ¥ÖçX¯¤®Îµ\"\rÀFDÚñLˆtòH\'|¸;ùÓ¬¤¸‘v<í‘_\'§‘N¸7\"\rÀéˆ´ãitD#£Ä¶ˆzôEÖwG‡Ù¨e4‘`/\"íxÆFÚ\"œÉÕzÛèÒi\0îC¤Ï¨H‡:Ş´„sF:ŞÓÒÚ	D\Z€½ˆ´ãit¼¥ÃÏŒ×Q‹¾&³\ng%\rÀİˆ´ãi4#|i\06\"ÒG¤\ZD\Z€ˆ´ãi£‘`#\"íxDÚ¨A¤ØˆH;‘6ji\06\"ÒG¤\ZD\Z€ˆ´ãi£‘`#\"íxDÚ¨A¤ØˆH;Ş¬…¹R…†Ö~í}bÈP7‡î;\0— Ò7\'³HªP~²]{ŸCÁ•ôÕrtß)\0¸‘v¼{Öî‘0¬-oÔŞ\'Æ¡¦.¹-f/.Ğ}§\0àDÚñ^/;!a˜·föD1m­–Ûb~n…î;\0— Ò×ÔÑ/a¸êñlo‡O{¥R|ÜœQ(·Åc-ºï\0\\‚H»Áo”H~½ñ öJ¥òxû€Gn…Ys‡Füºï\0\\‚H»AMs—ZLjêÒŞªÔm½#êuİ¯—Ğ}w\0àDÚ%îûÓ^)Äõ/läAï™=ış¹Ë‹å÷?\'³h|\\÷]€‹i—ğøÕ{±f/.¨mîÕŞ­Ô²†V…–•tSG¿î;\0W!Òîáiï¿yi¡zŸîÊÒÚë•\n£ Ê«å–ßyÍÙ.İw\0nC¤]ÅwÑ?/ø¶iµ¤^´µšg©§c4´ö¿ºû˜z-·ùÛˆ54€é@¤İf|üRñÑ)´ê‡\ZjµÇ¸òı‹]ñŞ1‡0Mˆ´;I6\nxççVçéW/È¹ıŞ¼Jï¶0­ˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆôÌIKKKxQô‰˜“Ã%-INÍI‹’ğ`Â§%Ü\0`HO£˜‹Ù­)Gz\n×u)‰G_»ÅuY9‘€+A¤§‘u™¢Kyi’‘¶¸ºx{‹8?ææIşÅ¯ôÑ$é¢¢¢„s\0 ¥éI¸Âå uwcˆWÇxÙ‹Ùfë%²õ¶Wgıw@2Gs‡\0€\"=	S¨HÌ…fÌåføüğñö™ä]š|¤Ã÷sÛ˜?H¼H[ÿ,Ñ\0\0!ÒÉŠWë´Äd¼ıX¤.ù+¸–IE:Ş†s¬ÿşHş€‰4\0D Ò“`±üµØÄb{)şÊu\n‘Şÿdw~\"fƒcÎŸòÇ;\0\0€B¤\'aj‘Nx:æ®’	ÂëšB¤“éqô>ã…Üâh-v\0Pˆô$L-ÒñXì*z)sŸ‡o?Iş=¾­õaÄÛ<áÑ&<\0\0\0‘œè2%Ÿ–d‘UëHÇÌjøEÑ;IxÖCDdÂŸ.Ş¡Z\0\0 „HÏœd–ÅµNX¾x×ød!Ş%ü³ úg!Ò\0p%ˆ4LA¤ ‘†)ˆ4\0D Ò0‘€D\Z¦ Ò\0HÃD\Z\0\"i˜‚H@\"\rSi\0ˆ@¤a\n\"\r\0ˆ4LA¤ ‘†)ˆ4\0D Ò0‘€D\Z¦ Ò\0HÃD\Z\0\"i˜‚H@\"\rSi\0ˆ@¤¡Ù²eË\"şóôêÕ«u\0HC³¼¼¼ğBoÛ¶-??_÷A€ˆ44»páBzzº*ôÒ¥K³³³½^¯îƒ\0#iè·jÕ*é‚‚ë€\"\rıjkk—,Y¢\"]UU¥ûp\0ÀD\ZFÈÌÌ”Hgeeé>\00‘†JJJ$Ò»víÒ} \0`\"\r#È:==}``@÷\0€Aˆ4\0\0†\"Ò\0\0ŠH\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH»Íøø¥G[^*ª»¼XÆ§æ¯eØ>ædÉïö‰üƒy•ßˆ_÷mÀµˆ´{t<»ùğÕr´7,ÕÆ}ë÷zÚûußş\0\\ˆH»Dn¥gÖÂ\\ÕŒ›3\nm­Ş]÷‘Œ~ÿĞğ%†½Cın_İ},ôXÅUg¿TT=>®û~\0À]ˆ´ãIÈ?¨RqÛk;k›{µ7,¥FCkÿ¼5{Ôïÿ7JºGtß#\0¸‘v<UhYÉ­,=¡½X);da­hµ5ëi\0v!ÒÎ–[éQ…–HhUŠÚæ^õŒÃ³›ë¾_\0p	\"í`ç{|ª\nkËµ\'Š!£üd»üÁ$·HMs—î{\07 Ò¦è¾ãõíqb„Æ¢­ÕêAoİ÷\0n@¤Ê7âW‹6^)fÔèé÷«‡7œe1\ràJi§*¬ñª›ö,1\"Æ#ïTÈM³x[µîû\0Ç#ÒN5?7P‚ŒâZíMbDŒw´ÈMsËÒBİ÷\0G¤J}Œ/ê6p´õÈM3ka®îû\0Ç#ÒNuı¥\r­ıÚ›Äˆêåºï#\0H;•úˆ+>õÓÌA¤Ø‚H;•Š´ö\Z1buëè¾\0p<\"íTDÚäA¤Ø‚H;‘6yi\0¶ ÒNe{¤çÎ›ğ¢è1\'‡KşZ’œš37JÂƒ	Ÿ–p[\"\r@;\"íTWé˜‹Ù­)Gz\n×•Lƒ£¯İâº,œH0‘v*[\"ğÒˆŒM*ÒÖµhÌ[äÖb~2i\0Æ\"ÒNeÂÃİñê/{1Ûl½DN¦ë$,:‘`2\"íT¶D:æB3ær3|~ø‰xûLòŠ¦éğıÇÜ6æ/ÒÖ?‘ ‘v*»\"ğtD›“©i’Í›B¤ãmh1Çúï\"\rÀdDÚ©ff%üz4aó,ÖĞIî6ºĞõµş–Hp\"íT3¼’l¤“\\æN6ÒÉô8zŸ	—ÑD\Z€™ˆ´SézNz(léi½bN&Òñjmİğk±´ÅŸD\Z€#i§Òõêîğ¬ZG:fVÃ/ŠŞIÂc°ş\"f†œH0‘vª™‰tDÃ\"j°|ñ¯ñ“]šGK8!úG Ò\0ŒE¤ŠÏî6yi\0¶ ÒNE¤MD\Z€-ˆ´Si“‘`\"íTDÚäA¤Ø‚H;‘6yi\0¶ ÒNE¤MD\Z€-ˆ´SÍZ˜+hhí×$FôP·îû\0Ç#ÒN5\'³H2P~²]{ÑCnš«äè¾\0p<\"íT÷¬İ#%X[Ş¨=HŒˆq¨©KnšÙ‹tßG\08‘vª×ËNH	æ­Ù£½IŒˆ±hkµÜ4ós+tßG\08‘vª¦~)ÁUg{;|Ú³Ä7gÊM³ãX‹îû\0Ç#ÒvÇ%ƒ_o<¨=KŒĞxû€Gn”Ys‡Füºï \0H;XMs—ZLjêÒ\'†Œ¶Şõºî×ËNè¾w\0p\"íl÷ıi¯$áú6ò ·öÑÓïŸ»¼Xn9™Eããºï\0\\H;›oÄ¯Ş‹5{qAms¯öP¥ì5´*´¬¤›:úuß/\0¸‘v<O{ÿÍKÕsW–Ğ«U^õ(·Ü5g»tß#\0¸‘vßEÿ¼àÛ¦Õ’zÑÖj¥ÑĞÚÿêîcêµÜ2äO%ÖĞ\0ìE¤]b|üRñÑ)´\n†\ZjyÇ°}DÿW¼wŒç¡ØH»Št¢ğˆw~nyqõ‚œû×ïÍ«ôğn+\0Ó„H\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠH›%---áEÑ\'bNgq-1w¢ÎL‹’ğŠÂ§%Ü\0`Hk3r1Û6åHÇ›`±‡„]™dë™ñ\0‘ÖÌº^Ñ+ÚK“Œ´ÅEWø×@ô±Å¼Òä#]TT”p\0¤\"m³+\\2Zw7æ	ë\'ya·«x1L9Ò¬¶ ‘¶ÙJoi]»ğùá\'âí3ù#Œ—äx+ìèƒ´Xvi\0˜\Z\"m\'‹Ç{­·JxúÒ\'ÛsB’Wšü1s=ÇzWÖoç\0€KDÚvË_‹M¬Ã™üš5bŸWò#ÄlpÌÍ®ƒ!Ò\0HÛlj‘Nx:æ®’	¿Å5ÆÜO2=>Œ„Ëh\"\r\0S@¤m6+é˜»\nMˆWĞxÉŒyTñâjqğû·øƒ€HÀÔiûE×+ùü$³àÈ³u¤c–ŞâÌdVÒùOò0¬@\0€B¤Í’Ì²8¢Ö	ëh±¡uA£§Å¬u¼9É\\EÂŸ\0R‘†)ˆ4\0D Ò0‘€D\Z¦ Ò\0HÃD\Z\0\"i˜‚H@\"\rSi\0ˆ@¤a\n\"\r\0ˆ4LA¤ ‘†)ˆ4\0D Ò0‘€D\Z¦ Ò\0HÃD\Z\0\"i˜‚H@\"\rSi\0ˆ@¤a\n\"\r\0ˆ44[¶lYÄ^½zµîƒ\0#ih–——^èmÛ¶åççë>(\00‘†f.\\HOOW…^ºtivv¶×ëÕ}P\0`\"\rıV­Z¥\"]PPÀcİ\0B¤¡_mmí’%KT¤«ªªt\0˜‚HÃ™™™é¬¬¬±±1İÇ\0¦ Ò0BII‰Dz×®]º\0B¤aY@§§§è>\00‘\0ÀPD\Z\0\0Ci\0\0E¤\00‘\0ÀPD\Z\0\0Cé”0>~iÇÑ–—Šªç./–ñ©ùkÉ9™EòK{\"ÿ`^¥Ç7â×}cH!DÚåºGİ|øê9ÚSçšqßú½ö~İ7,€”@¤İ,·Ò3ka®JËÍ…‹¶Vï®ûHFO¿hø#É¡~i¯î>zâªÇ³_*ª×}p;\"íNÒ\'òª¢ÜöÚÎÚæ^í©sÇhhíŸ·fúÅŞñFI÷àˆî›\Z€›iwR…–ßÊÒÚÃæ¾!kõ‚¬­YO˜>DÚ…r+=ªĞÒí=së¨mîUO%<»ù°î€ki·9ßãSñX[Ş¨½dîå\'Ûå/!ùU×4wé¾Ù¸‘võ@÷¯—hoX*ŒE[«ÕƒŞºov\0îD¤]Å7âWk;^)63£§ß¯·8r–Å4\0ûiW)¬ñª…öz¥Îxä\nù/ŞV­ûÆàBDÚUæç‚‘Q\\«=]©3Ş=Ò\"¿ó[–ê¾ñ¸‘või¼¨{&G[ïˆüÎg-ÌÕ}ãp!\"í*×¿°Q‚ÑĞÚ¯=])5Ôë\0tßø\0\\ˆH»Šú$,>õ“Hp\"í**ÒÚ£•jCıÚußø\0\\ˆH»\n‘&Ò\0Ü„H»\n‘&Ò\0Ü„H»Š½‘;wnÂ‹¢OÄœÎâZbîD97JÂ+\nŸ–p[\"\rÀ@DÚU®0Ò1#³mS´Eò­/µèzÌ$[Ï$Ò\0H»Ê•G:á¥©›T¤“ÿ³`²\rD[Ì+%Ò\0œ…H»Šö‡»­KœäUX„9Ş®âıÅ@¤8\Z‘v•+t¼¥mtíÂç‡Ÿˆ·Ïäÿˆ—äx+ìèƒ´Xvi\0B¤]Å–H\'<Ñf‹Ü&é$ÿ ˆ·z˜c½+\"\rÀYˆ´«ÌÀJ:ù5«-]/tÂÇû–Hp(\"í*3¹’l¤-Óñö“L£#á2šHp\n\"í*Z“\n[Æ+h¼d&Ùû˜k÷x,şb Ò\0œ…H»Š–Ww‡·Ö:Ò1Koqf2+i‹ü\'yD\Z€±ˆ´«Ì@¤#:Që„u´ØĞº ÑÓbÖ:Şœd®‚H0\r‘v>»[Ë Ò\0¦	‘v\"M¤¸	‘v\"M¤¸	‘v\"M¤¸	‘v\"M¤¸	‘v•Ys¥\r­ıÚ»•RCıÚußø\0\\ˆH»ÊœÌ\"©EùÉvíİJ©!¿ó«äè¾ñ¸‘v•{Öî‘`¬-oÔŞ­Ô‡šºäw>{qî€iWy½ì„cŞš=ÚÓ•:cÑÖjùÏÏ­Ğ}ãp!\"í*MıŒ«Ïövø´×+EÆÍ…ò;ßq¬E÷À…ˆ´ÛÜñF‰4ã×j¯W*Œ·xä·=kaîĞˆ_÷-À…ˆ´ÛÔ4w©Åô¡¦.í\rs÷hëQ¯ë~½ì„î›€;iºïO{¥×¿°‘½§oôôûç./–ßóœÌ¢ñqİ79\0—\"Ò.äñ«÷bÍ^\\PÛÜ«½gî²†V…–•tSG¿î€kiwò´÷ß¼´P½weé	íUsÓ(¨òªG¹åw[s¶K÷M\rÀÍˆ´kù.úçß6­–Ô‹¶Vó,õ•Œ†ÖşWwS¯å–!±†0İˆ´›_*>Ú\"…V]QC­Éè_àŠ÷ñ<4€@¤İOrRxÄ;?·‚<_É¸zAÎıë÷æUzx·€C¤\00‘\0ÀPD\Z\0\0Ci\0\0E¤1iiißZO\0$‰Hc*¬#–ÈÌ,\08‘Æ¤…*›d€“¬rQQ‘G	\0ÎG¤Sİd×µá…¶ØÉ–Ñ¬° ‘NuSˆttkcF:áé+<\0p=\"Òâ7a/Õ‹…2‘€+G¤S]¼5qÂšZ7u²w¿üòË“>z\0p5\"ê&éè³u€>­ôööfeeMúèÀÕˆtª›ÂJ:Şæñ™NfåM¤ \Z‘Fä“ĞI>\'Ş„½·Ø-‘€hD\Z“³è1ŸoósçÎ­[·nÚ\Z\0‰HÃ^¯7\'\'G÷Q\0€Yˆ4Œ@¤ \Z‘†ˆ4\0D#Ò0B]]İæÍ›u\0˜…HÃµµµüƒ\r\0ˆ@¤a\"\r\0Ñˆ4Œ@¤ \Z‘†Ê‚t\0˜…HÃD\Z\0¢iH@4\"\r#i\0ˆF¤a„¢¢¢ÚÚZİG\0f!Ò0‘€hD\ZF Ò\0HÃ›7o®««Ó}\0`\"\r–-[ñ©W¯^­û \0ÀD\Z:ååå…zÛ¶mùùùº\n\0LA¤¡Ó…ÒÓÓU¡322²³³½^¯îƒ\0Sih¶jÕ*é·Şz‹Çº ‘†fµµµK–,‘HçååUUUé>\00‘†~™™™øÃÆÆÆt\0„HC¿’’YLoÛ¶M÷\0€Yˆ4ô“´¬¤»ººt\0˜…H\0`(\"\r\0€¡ˆ4\0\0†\"Ò\0\0ŠHC¿ñ8t\0hF¤¡jğ_ƒü~ÿØØØh9S.Rsh6€ÔD¤1£By–ûúú›×Ô½W¶÷Í·ÊÖ¬WC¾=uàPoW·Ïç¾xñ¢\n6©‚ˆ4fH(ÏÒæıoo\\÷ó‡^;ûÉÏ\\y\\wy¾}úÚÙkîş…Ä»½µu``@‚-µ&Õ\0R\r‘ÆLPy–ĞÊZyÑ\rß”øÓ×Éxú_{åÛ·gıàÇ¹>&CNÈ·r¦ºT¦Éä]¯ı±«£3:Õº&\0˜vD\ZÓNºõøÉeÿòıÀZùÓ×=óÅÖßÿèÑ·r‡NòŸ=--<-gÉÉ“	¡Zg|ó;ÇË?èîî–TÒi\0©€Hcz©B¸}×Ó{ƒävÁ5_ÉÿÕozÔúÏ¶L„9Ş8Û\"Ódy-›ÖÜ×ÎŞ›»©£££¯¯O–Ôt\Z@* Ò˜F¡B/øo_•Ğ.»õ{­eû´9jœ{¯¬¤ÿìßíÉÉmkkëíí¥Ó\0R‘ÆtQ…®{¯LzÍOş}¸ñÔd­Æğ©S«~ô3Õé½¹ùt\Z@Š Ò˜¡ç¡Ÿ»ş&‰kÖ~<zæÌÔ\n­Æ¨×»âö»Ïgé†#eåt\Z@* Ò˜RÍáááŒ9ß•¬¦İü?ëêb¦·£òpYæŠ5wÿbåíw©‘ûàc‡V­9y°¾>íÆovxË·\ZÚÛÛûúúäZÆÆÆˆ4\0W\"Ò°ŸZFï^±R‚úÔç¾ÚZV£¸ÇKCïÅúÄøÌuÏ_w£Ä;z«Ö½ûúüßËœ·_L÷z½/^d1\rÀ•ˆ4ì§>±ä¹ën”š¾ûôo£[ÛRZ¦·²Â¬?±íÖ\'Qoâ:VS#{êî|6™zó´îŸ\0lF¤a3µŒŞûæ[²J–”öxl¤©)|xÿ²+™B«ñûoıëˆÇ¾yÿÑº§¯-ıùw¯z<¶¶¶¾¾>Ó\0\\‰HÃfRJY×fıÛO¤£¹>6xüxÄî&Yh5d-ş‰=Ô_ÿ£7tıÏÔ×Õ…/¦‰4\0—!Ò°™z¬û©Ïştôè;y½ÕÕ½ÕUÁ¯Õ½UU»eLªĞjxwì”m\'öSU}ôí<Y¦/¸æ+Õ+e1İŞŞ>00 ^æ­û§\0;iØLJyêÀ¡À?ÉøÂ×:öîí(/î|¤eE±õò±í9ï4<ÙÚÚ\ZzÄ[÷O\0v\"Ò°™ßï?°©P\"ıÊ·o?¿kg`ìÜ¥Æ¡U«ÕgwOv,šıÏçŠwvry‡ËnıœŸ¿<«¾¾>ôˆ·\\µîŸ\0ìD¤a\'õ„tÙšõã5?ù÷3[¶„wŸşí\n­Æ™ÂÂğ]eıàÇêXGõz½<-\rÀ•ˆ4ì$İóÇl‰ôº{<µaCøÈıù/§éı//ß•ü \"}äÈÇÓÑÑ¡>}ŒHp\"\r;EDºá­·ÂÇF:|W‘½vŒHp\"\r;…GzÕ÷D¬¤ÕçLmœÊËßUèán\"\rÀÅˆ4ì¤\"záXÄsÒWòÂ±3…ŸØUÆ?ÿ¯\'ƒŸgB¤¸‘†T¤ïÛ¯Ş‚u®¸xâõØ—Çß‚õó_N¼D<øÒîs;v„Ş‚E¤¸‘†T¤{»º?ÌäĞÊÕïoÚ‡™œ),ß‰Z‘/¸æ+e¥¥¼p€‹iØI½Kz©ş÷óúûí­\n|FØ…}}ù›ß™T¡·>ñLğ³Æª.xYÕº{”ó—İú½ı|À[°\0¸‘†Íü~¿ôr×k|2ø¿ª:ŒøìnOÑvõ²’¯}÷‡ƒuõá›w¬Tÿ`#û™÷ïßÏ‡™\0p1\"\r›ıõ¯½xñb{kë3_úº¤4ÿW¿‰ø/X„µcgÚ·&,t`!~¤6bÛÀ¡æ¿tçÎÃ‡744ğ± \0ÜŠHÃf*Ò¾¾ş?ÿîU©éSŸûjË{ïGÿKéÁúz‹wd¥}ãÖC+WûÏÆøGÔê%c«û,£Ã_5F¤¸‘†ÍBOKw\\¸|ú9í–oÖÕGwZÆ@}}Yæ\nY¯¼íN5¤ÜGròügÏÆêúñ´›şE½#«¬´T–Ñáuó„4\0÷!Ò°™”R-¦ûúú¨Tz¯¸ı®Q¯7f§\'†TY8F››_ùşÔİÛsre}ôèQYF·µµ…ë&Ò\0\\†HÃ~ÒËÑÑQYİÊ\Z÷@şfõv¬¬ûÉ`}ìõtÂ1xüøŠàGŒ-¸æ+ùË³¤ĞêÙèĞ2š&\rÀ•ˆ4ìZLtttìÍÍWN»ñ[­{÷M¶Ğ­å‹¾>\'Tè}ûö©º½^¯ì<ôl4Ëh\0îC¤1-Ô3ÓÃÃÃ}}}íííÒég¾tCàudŸÿûõ<:X<™<÷­“Éê•bÏ|ñ†ğB«×‹ÉÎå*x6\Z€[iLµ˜Vz÷öö¶µµ)+O»åÛêÅÛ¯½îçykÃHSSt›åL¹H&¨÷C«{oÏÉ\r/´ìPvz ›Hp%\"éİéÆ††?ÿîÕç¾ü‰·Z}æz©uÆ7¿³âö»dÅ,CNÈ·r¦\\¤æÈ:û™ËJKÕ®(4€”B¤1\":İŞŞŞÜÜ|¬¦&yÖŠÛî|ês_÷>i¹è•ïığíÓKwîT/;zôhCCƒl.;¡Ğ\0R‘Æô\nït___GGGKK‹¬†ëëêª,ÍÍß²òãĞo·ç¼X:°¿ê`¥Ê³Z@Ë†²¹ì„BHD\ZÓ.ÔéáááîînY\r«T7<)>\Zt$Œ:G.’	*Ï²‰l(›ËN(4€A¤1T§ÇÆÆŸêó…RİÚÚ*nnnöz½0ò­œ)É•gµ€–Íe\'\Z@Š Ò˜!ãA©–ôöôôHƒ;‚ÚƒÔi9S.’	2-\"Ï\Z@Š Ò˜Qá©UµVÂ„Î”	2<HMD\Z\Z„R-ü~¿\nv9S.RsÈ3€ÔD¤¡ßxº\04ûÿe-oZÒlôy\0\0\0\0IEND®B`‚',8,'devicePlan.png');

/*Table structure for table `jbpm4_participation` */

DROP TABLE IF EXISTS `jbpm4_participation`;

CREATE TABLE `jbpm4_participation` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `GROUPID_` varchar(255) default NULL,
  `USERID_` varchar(255) default NULL,
  `TYPE_` varchar(255) default NULL,
  `TASK_` bigint(20) default NULL,
  `SWIMLANE_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_PART_SWIMLANE` (`SWIMLANE_`),
  KEY `FK_PART_TASK` (`TASK_`),
  KEY `IDX_PART_TASK` (`TASK_`),
  CONSTRAINT `FK_PART_SWIMLANE` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`),
  CONSTRAINT `FK_PART_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_participation` */

/*Table structure for table `jbpm4_property` */

DROP TABLE IF EXISTS `jbpm4_property`;

CREATE TABLE `jbpm4_property` (
  `KEY_` varchar(255) NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `VALUE_` varchar(255) default NULL,
  PRIMARY KEY  (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_property` */

insert  into `jbpm4_property`(`KEY_`,`VERSION_`,`VALUE_`) values ('next.dbid',1,'10001');

/*Table structure for table `jbpm4_swimlane` */

DROP TABLE IF EXISTS `jbpm4_swimlane`;

CREATE TABLE `jbpm4_swimlane` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_SWIMLANE_EXEC` (`EXECUTION_`),
  KEY `IDX_SWIMLANE_EXEC` (`EXECUTION_`),
  CONSTRAINT `FK_SWIMLANE_EXEC` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_swimlane` */

/*Table structure for table `jbpm4_task` */

DROP TABLE IF EXISTS `jbpm4_task`;

CREATE TABLE `jbpm4_task` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) default NULL,
  `DESCR_` longtext,
  `STATE_` varchar(255) default NULL,
  `SUSPHISTSTATE_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `FORM_` varchar(255) default NULL,
  `PRIORITY_` int(11) default NULL,
  `CREATE_` datetime default NULL,
  `DUEDATE_` datetime default NULL,
  `PROGRESS_` int(11) default NULL,
  `SIGNALLING_` bit(1) default NULL,
  `EXECUTION_ID_` varchar(255) default NULL,
  `ACTIVITY_NAME_` varchar(255) default NULL,
  `HASVARS_` bit(1) default NULL,
  `SUPERTASK_` bigint(20) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  `PROCINST_` bigint(20) default NULL,
  `SWIMLANE_` bigint(20) default NULL,
  `TASKDEFNAME_` varchar(255) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_TASK_SWIML` (`SWIMLANE_`),
  KEY `FK_TASK_SUPERTASK` (`SUPERTASK_`),
  KEY `IDX_TASK_SUPERTASK` (`SUPERTASK_`),
  CONSTRAINT `FK_TASK_SUPERTASK` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_task` (`DBID_`),
  CONSTRAINT `FK_TASK_SWIML` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_task` */

/*Table structure for table `jbpm4_variable` */

DROP TABLE IF EXISTS `jbpm4_variable`;

CREATE TABLE `jbpm4_variable` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `KEY_` varchar(255) default NULL,
  `CONVERTER_` varchar(255) default NULL,
  `HIST_` bit(1) default NULL,
  `EXECUTION_` bigint(20) default NULL,
  `TASK_` bigint(20) default NULL,
  `LOB_` bigint(20) default NULL,
  `DATE_VALUE_` datetime default NULL,
  `DOUBLE_VALUE_` double default NULL,
  `CLASSNAME_` varchar(255) default NULL,
  `LONG_VALUE_` bigint(20) default NULL,
  `STRING_VALUE_` varchar(255) default NULL,
  `TEXT_VALUE_` longtext,
  `EXESYS_` bigint(20) default NULL,
  PRIMARY KEY  (`DBID_`),
  KEY `FK_VAR_EXESYS` (`EXESYS_`),
  KEY `FK_VAR_LOB` (`LOB_`),
  KEY `FK_VAR_TASK` (`TASK_`),
  KEY `FK_VAR_EXECUTION` (`EXECUTION_`),
  KEY `IDX_VAR_EXESYS` (`EXESYS_`),
  KEY `IDX_VAR_TASK` (`TASK_`),
  KEY `IDX_VAR_EXECUTION` (`EXECUTION_`),
  KEY `IDX_VAR_LOB` (`LOB_`),
  CONSTRAINT `FK_VAR_EXECUTION` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_EXESYS` FOREIGN KEY (`EXESYS_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_LOB` FOREIGN KEY (`LOB_`) REFERENCES `jbpm4_lob` (`DBID_`),
  CONSTRAINT `FK_VAR_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_variable` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
