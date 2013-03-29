Create database System_DB_Test_Model;
use System_DB_Test_Model;

/*
 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost
 Source Database       : System_DB_Test_Model

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : utf-8

 Date: 01/31/2013 17:34:37 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Access_Log`
-- ----------------------------
DROP TABLE IF EXISTS `Access_Log`;
CREATE TABLE `Access_Log` (
  `Access_Log_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` int(11) NOT NULL,
  `Access_Start_Time` datetime DEFAULT '1970-01-01 00:00:00',
  `Access_End_Time` datetime DEFAULT '1970-01-01 00:00:00',
  `Device` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Location` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `isOnline` tinyint(4) NOT NULL DEFAULT '0',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Access_Log_Id`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `access_log_ibfk_3` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Access_Log`
-- ----------------------------
BEGIN;
INSERT INTO `Access_Log` VALUES ('1', '1', '2013-01-31 03:43:00', '2013-01-31 03:52:07', null, null, '0', '2013-01-31 03:43:25'), ('2', '3', '2013-01-17 21:43:34', '2013-01-17 21:51:45', null, null, '0', '2013-01-31 03:44:13'), ('3', '6', '2013-01-18 23:44:22', '2013-01-18 23:54:33', null, null, '0', '2013-01-31 03:44:51'), ('4', '3', '2013-01-18 18:44:58', '2013-01-18 18:50:10', null, null, '0', '2013-01-31 03:45:35'), ('5', '7', '2013-01-19 03:47:01', '2013-01-19 03:53:06', null, null, '0', '2013-01-31 03:47:21'), ('6', '7', '2013-01-20 02:47:29', '2013-01-20 02:53:39', null, null, '0', '2013-01-31 03:47:52'), ('7', '7', '2013-01-17 03:56:10', '2013-01-17 04:00:00', null, null, '0', '2013-01-31 03:49:00'), ('8', '6', '2013-01-23 09:49:08', '2013-01-23 09:58:17', null, null, '0', '2013-01-31 03:49:49'), ('9', '8', '2013-01-30 03:50:03', '2013-01-30 03:55:09', null, null, '0', '2013-01-31 03:50:18');
COMMIT;

-- ----------------------------
--  Table structure for `Charity`
-- ----------------------------
DROP TABLE IF EXISTS `Charity`;
CREATE TABLE `Charity` (
  `Charity_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Charity_Name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Charity_Description` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `Address_Line1` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Address_Line2` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `PostCode` varchar(7) DEFAULT NULL,
  `Email` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `Phone` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `User_Id` int(11) NOT NULL COMMENT 'Charity Administrator''s ID',
  `Registration_No` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Account_No` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Connection_String` varchar(500) NOT NULL,
  `isVerified` tinyint(1) NOT NULL DEFAULT '0',
  `isActive` tinyint(1) NOT NULL DEFAULT '0',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Charity_ID`),
  KEY `User_Id` (`User_Id`),
  CONSTRAINT `FK_Charity_User_ID` FOREIGN KEY (`User_Id`) REFERENCES `Users` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Charity`
-- ----------------------------
BEGIN;
INSERT INTO `Charity` VALUES ('4', 'Red Cross', null, null, null, null, null, 'info@redcross.co.uk', '07582693376', '1', 'C482963', null, '', '1', '1', '2013-01-25 16:24:59'), ('8', 'Hope', null, null, null, null, null, 'info@hope.co.uk', '07582773376', '6', '1041258', null, '', '1', '1', '2013-01-25 16:24:52'), ('9', 'Grow Peace', 'Grow Peace, with a project in Africa called Path Out of Poverty (POP) runs from a farm called Goedgedacht (Good Ideas) on the West Coast region of South Africa.', '27a Lower Brook Street ', 'Ipswich', 'Suffolk', null, 'info@growpeace.co.uk', '01473287437', '3', '1091687', null, '', '1', '1', '2013-01-25 16:24:53'), ('11', 'Carers UK', 'Carers UK is a charity set up to help the millions of people who care for family or friends. We provide information and advice about caring alongside practical and emotional support for carers. ', '20 Great Dover Street ', '', 'London', 'SE14LX', 'info@carers.co.uk', '07894685268', '7', '246329', null, '', '0', '0', '2013-01-25 16:22:29'), ('12', 'Life Care Edinburgh Ltd', 'LifeCare delivers quality, responsive, centre-based and domiciliary care services, graduated to individual needs.', 'Stockbridge House ', '2 Cheyne Street ', 'Edinburgh', null, 'info@lifecare.co.uk', '07854689994', '8', 'SC012641', null, '', '0', '0', '2013-01-25 16:22:44'), ('13', 'Age UK', 'Improving later life', null, '', 'London', null, 'info@age.co.uk', '07998256648', '9', '1128267', null, '', '0', '0', '2013-01-25 16:22:49'), ('15', 'I Can', 'I CAN is the charity that helps children with speech and language difficulties across the UK.', '8 Wakley Street ', '', 'London', 'EC1V7QE', 'info@ican.co.uk', '07894688526', '12', '210031', null, '', '0', '0', '2013-01-25 16:24:42');
COMMIT;

-- ----------------------------
--  Table structure for `Feedback`
-- ----------------------------
DROP TABLE IF EXISTS `Feedback`;
CREATE TABLE `Feedback` (
  `Feedback_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Email` varchar(250) NOT NULL,
  `Comment` mediumtext NOT NULL,
  `ReviewedBy` int(11) DEFAULT NULL,
  `ReviewedDate` datetime DEFAULT '1970-01-01 00:00:00',
  `isReviewed` tinyint(1) NOT NULL DEFAULT '0',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Feedback_Id`),
  KEY `ReviewedBy` (`ReviewedBy`),
  CONSTRAINT `FK_Feedback_User_Id` FOREIGN KEY (`ReviewedBy`) REFERENCES `Users` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `User_Type`
-- ----------------------------
DROP TABLE IF EXISTS `User_Type`;
CREATE TABLE `User_Type` (
  `User_Type_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Type` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `User_Type_Description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `isActive` tinyint(4) NOT NULL DEFAULT '1',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`User_Type_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `User_Type`
-- ----------------------------
BEGIN;
INSERT INTO `User_Type` VALUES ('1', 'UCL Administrator', 'UCL Administrator', '1', '2013-01-25 15:20:29'), ('2', 'Charity Administrator', 'Charity Administrator', '1', '2013-01-25 15:20:54');
COMMIT;

-- ----------------------------
--  Table structure for `Users`
-- ----------------------------
DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `User_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `User_Type_Id` int(11) NOT NULL,
  `User_Password` varchar(250) CHARACTER SET utf8 NOT NULL,
  `Salt` varchar(10) NOT NULL,
  `User_Email` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `Date_Created` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `isActive` tinyint(1) NOT NULL DEFAULT '0',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`User_Id`),
  KEY `User_Type_Id` (`User_Type_Id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`User_Type_Id`) REFERENCES `User_Type` (`User_Type_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=ascii;

-- ----------------------------
--  Records of `Users`
-- ----------------------------
BEGIN;
INSERT INTO `Users` VALUES ('1', 'rcadmin', '2', '', '', 'admin@redcross.co.uk', '2013-01-08 15:27:43', '1', '2013-01-31 03:56:14'), ('3', 'gpadmin', '2', '', '', 'admin@growpeace.co.uk', '2013-01-20 15:28:26', '1', '2013-01-31 15:41:00'), ('6', 'hope', '2', '', '', 'admin@hope.co.uk', '2013-01-29 15:28:53', '1', '2013-01-31 03:55:16'), ('7', 'carersadmin', '2', '', '', 'admin@carers.co.uk', '2013-01-19 15:29:42', '0', '2013-01-31 03:55:23'), ('8', 'lcadmin', '2', '', '', 'admin@lifecare.co.uk', '2013-01-08 15:34:48', '0', '2013-01-31 03:56:07'), ('9', 'age', '2', '', '', 'admin@age.co.uk', '2013-01-11 15:34:49', '0', '2013-01-31 03:55:31'), ('12', 'ican', '2', '', '', 'admin@ican.co.uk', '2013-01-25 15:34:53', '0', '2013-01-25 15:35:15'), ('13', 'ucladmin', '1', '', '', 'charities@ucl.ac..uk', '2013-01-09 03:54:59', '1', '2013-01-31 03:55:05');
COMMIT;

-- ----------------------------
--  Procedure structure for `spSchemaGeneration`
-- ----------------------------
DROP PROCEDURE IF EXISTS `spSchemaGeneration`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spSchemaGeneration`(IN DB_Name VARCHAR(100))
BEGIN
	DECLARE
		sql_cmd VARCHAR(10000)DEFAULT "CREATE DATABASE " ;
	SET @sql_cmd = concat(sql_cmd, DB_Name, ";"); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Access_Log (Access_Log_Id int(11) NOT NULL AUTO_INCREMENT,
																		  Form_Id int(11) NOT NULL,
																		  Field_Id int(11) DEFAULT NULL,
																		  User_Id int(11) NOT NULL,
																		  Access_Start_Time datetime DEFAULT '1970-01-01 00:00:00',
																		  Access_End_Time datetime DEFAULT NULL,
																		  Device varchar(100) CHARACTER SET utf8 DEFAULT NULL,
																		  Location varchar(100) CHARACTER SET utf8 DEFAULT NULL,
																		  isOnline tinyint(1) NOT NULL DEFAULT '0',	
																		  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
																		  PRIMARY KEY (`Access_Log_Id`),
																		  KEY Form_Id (Form_Id),
																		  KEY Field_Id (Field_Id),
																		  KEY User_Id (User_Id)
																		) ENGINE=InnoDB DEFAULT CHARSET=ascii;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Event (
																		  Event_Id int(11) NOT NULL AUTO_INCREMENT,
																		  Event_Name varchar(255) NOT NULL,
																		  Event_Description text NOT NULL,
																		  Event_Location varchar(255) NOT NULL,
																		  Event_Date date NOT NULL,
																		  Event_Time time NOT NULL,
																		  User_Id int(11) NOT NULL,
																		  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
																		  PRIMARY KEY (Event_Id),
																		  KEY User_Id (User_Id)	
																		) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Field_Selection (
															  Field_Selection_Id int(11) NOT NULL AUTO_INCREMENT,
															  Field_Id int(11) NOT NULL,
															  Field_Selection_Value varchar(255) NOT NULL,
															  Timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
															  PRIMARY KEY (Field_Selection_Id),
															  KEY Field_Id (Field_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Field_Type (
															  Field_Type_Id int(11) NOT NULL AUTO_INCREMENT,
															  Field_Type varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Field_DataType varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Field_Description varchar(200) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Field_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Filled_Form (
															  Filled_Form_Id int(11) NOT NULL AUTO_INCREMENT,
															  Field_Id int(11) DEFAULT NULL,
															  Value varchar(250) CHARACTER SET utf8 DEFAULT NULL,
															  User_Id int(11) DEFAULT NULL,
															  Record_Id int(11) NOT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Filled_Form_Id),
															  KEY Field_Id (Field_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form (
															  Form_Id int(11) NOT NULL AUTO_INCREMENT,
															  Form_Type_Id int(11) DEFAULT NULL,
															  Form_Name varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Date_Created datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
															  URL varchar(250) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Form_Id),
															  KEY Form_Type_Id (Form_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form_Fields (
															  Field_Id int(11) NOT NULL AUTO_INCREMENT,
															  Form_Id int(11) NOT NULL,
															  Field_Label varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  Field_Type_Id int(11) DEFAULT NULL,
															  X_coordinate float DEFAULT NULL,
															  Y_coordinate float DEFAULT NULL,
															  isRequired bit(1) DEFAULT NULL,
															  Default_Value bit(1) DEFAULT NULL,
															  Min_Value float DEFAULT NULL,
															  Max_Value float DEFAULT NULL,
															  User_Id int(11) DEFAULT NULL,
															  Date_Created datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Field_Id),
															  KEY Form_Id (Form_Id),
															  KEY Field_Type_Id (Field_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form_Permissions (
															  Form_Id int(11) NOT NULL,
															  User_Type_Id int(11) NOT NULL,
															  Permission varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  isActive bit(1) DEFAULT NULL,
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Form_Id,User_Type_Id),
															  KEY Form_Id (Form_Id),
															  KEY User_Type_Id (User_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Form_Type (
															  Form_Type_Id int(11) NOT NULL AUTO_INCREMENT,
															  Form_Type varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(4) NOT NULL DEFAULT '1',
															  PRIMARY KEY (Form_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Mailing_Group (
															  Mailing_Group_Id int(11) NOT NULL AUTO_INCREMENT,
															  Mailing_Group varchar(255) NOT NULL,
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (Mailing_Group_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Mailing_List (
															  Mailing_Group_Id int(11) NOT NULL,
															  User_Id int(11) NOT NULL,
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
															  PRIMARY KEY (Mailing_Group_Id,User_Id),
															  KEY Mailing_Group_Id (Mailing_Group_Id),
															  KEY User_Id (User_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".User_Type (
															  User_Type_Id int(11) NOT NULL AUTO_INCREMENT,
															  User_Type varchar(100) CHARACTER SET utf8 DEFAULT NULL,
															  User_Type_Description varchar(200) CHARACTER SET utf8 DEFAULT NULL,
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (User_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"CREATE TABLE ",
		DB_Name,
		".Users (
															  User_Id int(11) NOT NULL AUTO_INCREMENT,
															  Username varchar(20) CHARACTER SET utf8 NOT NULL,
															  User_Type_Id int(11) NOT NULL,
															  User_Password varchar(250) CHARACTER SET utf8 NOT NULL,
															  Salt varchar(10) NOT NULL,
															  User_Email varchar(250) CHARACTER SET utf8 DEFAULT NULL,
															  Date_Created datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
															  isActive tinyint(1) NOT NULL DEFAULT '1',
															  Timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
															  PRIMARY KEY (User_Id),
															  KEY User_Type_Id (User_Type_Id)
															) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Mailing_List ADD CONSTRAINT FK_Mailing_List_User_Id FOREIGN KEY (User_Id) REFERENCES ",
		DB_Name,
		".Users (User_Id) ON UPDATE CASCADE, 
									ADD CONSTRAINT FK_Mailing_List_Mailing_Group_Id FOREIGN KEY (Mailing_Group_Id) REFERENCES ",
		DB_Name,
		".Mailing_Group (Mailing_Group_Id) ON UPDATE CASCADE;"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Users ADD CONSTRAINT FK_User_User_Type_ID FOREIGN KEY (User_Type_Id) REFERENCES ",
		DB_Name,
		".User_Type (User_Type_Id)"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Access_Log ADD CONSTRAINT FK_Access_Log_Form_Fields_ID FOREIGN KEY (Field_Id) REFERENCES ",
		DB_Name,
		".Form_Fields (Field_Id) ON UPDATE CASCADE,
									ADD CONSTRAINT FK_Access_Log_Form_ID FOREIGN KEY (Form_Id) REFERENCES ",
		DB_Name,
		".Form (Form_Id) ON UPDATE CASCADE,
									ADD CONSTRAINT FK_Access_Log_Users_ID FOREIGN KEY (User_Id) REFERENCES ",
		DB_Name,
		".Users (User_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Event ADD CONSTRAINT FK_Event_User_Id FOREIGN KEY (User_Id) REFERENCES ",
		DB_Name,
		".Users (User_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Field_Selection ADD CONSTRAINT FK_Field_Selection_Form_Field FOREIGN KEY (Field_Id) REFERENCES ",
		DB_Name,
		".Form_Fields (Field_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Filled_Form ADD CONSTRAINT FK_Filled_Form_Form_Fields_ID FOREIGN KEY (Field_Id) REFERENCES ",
		DB_Name,
		".Form_Fields (Field_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Form ADD CONSTRAINT FK_Form_Form_Type_ID FOREIGN KEY (Form_Type_Id) REFERENCES ",
		DB_Name,
		".Form_Type (Form_Type_Id)"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Form_Fields ADD CONSTRAINT FK_Form_Fields_Field_Type_ID FOREIGN KEY (Field_Type_Id) REFERENCES ",
		DB_Name,
		".Field_Type (Field_Type_Id) ON UPDATE CASCADE, 
									ADD CONSTRAINT FK_Form_Fields_Form_ID FOREIGN KEY (Form_Id) REFERENCES ",
		DB_Name,
		".Form (Form_Id)"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ;
	SET @sql_cmd = concat(
		"ALTER TABLE ",
		DB_Name,
		".Form_Permissions ADD CONSTRAINT FK_Form_Permissions_Form_ID FOREIGN KEY (Form_Id) REFERENCES ",
		DB_Name,
		".Form (Form_Id) ON UPDATE CASCADE,
									ADD CONSTRAINT FK_Form_Permissions_User_Type_ID FOREIGN KEY (User_Type_Id) REFERENCES ",
		DB_Name,
		".User_Type (User_Type_Id) ON UPDATE CASCADE"
	); PREPARE stmt
	FROM
		@sql_cmd ; EXECUTE stmt ; DEALLOCATE PREPARE stmt ;
	END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

update users set Salt="Y4qeEqBlJk", User_Password="b6da945d600fc36192e50ffb585e96c8bd6604c02ef74c253271531048eebd62" where Username="ucladmin";

