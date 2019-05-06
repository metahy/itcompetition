/*
 Navicat MySQL Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : itcompetition

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 06/05/2019 19:47:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin1', '123456');

-- ----------------------------
-- Table structure for applyinfo
-- ----------------------------
DROP TABLE IF EXISTS `applyinfo`;
CREATE TABLE `applyinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) NULL DEFAULT NULL,
  `competition_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_start_time` datetime(0) NULL DEFAULT NULL,
  `competition_end_time` datetime(0) NULL DEFAULT NULL,
  `student_id` int(11) NULL DEFAULT NULL,
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `team_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `team_leader` tinyint(4) NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '是否通过',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of applyinfo
-- ----------------------------
INSERT INTO `applyinfo` VALUES (1, 1, '1', '2019-03-22 00:00:00', '2019-04-03 00:00:00', 1, '天天', '20190401001', '理学院', '13888888888', '让人', 1, 1, 'c3b4397aee2b4f3cae1e10da99636f2f.png');
INSERT INTO `applyinfo` VALUES (2, 2, '全屋定制装修', '2019-03-24 00:00:00', '2019-04-01 00:00:00', 1, '天天', '20190401001', '理学院', '13888888888', NULL, 1, 1, 'a0675b99c2cc48ea8851c9b630c20082.webp');
INSERT INTO `applyinfo` VALUES (3, 3, '百度一下，你就知道', '2019-03-26 00:00:00', '2019-04-01 00:00:00', 1, '天天', '20190401001', '理学院', '13888888888', '哈哈哈', 1, 1, '73bc826279bf4a55954d46a8d1d53711.webp');
INSERT INTO `applyinfo` VALUES (4, 3, '百度一下，你就知道', '2019-03-26 00:00:00', '2019-04-01 00:00:00', 2, '迪迪', '20190401002', '化工学院', '13888888889', '哈哈哈', 0, 1, '73bc826279bf4a55954d46a8d1d53711.webp');

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `students_num` int(11) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `organizer_id` int(11) NULL DEFAULT NULL,
  `organizer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reference_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_result` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, '1', '2', 3, '2019-03-22 00:00:00', '2019-04-03 00:00:00', 1, NULL, '021905bf4aaa4aeab3350897c3d97bac.webp', 1, '736e7fa61f8149ac9fe5e61e00712fd2.webp', 1);
INSERT INTO `competition` VALUES (2, '全屋定制装修', '笔记齐全的毛概书走过路过不要错过，考试必备，临阵突击也能过过过！', 1, '2019-03-24 00:00:00', '2019-04-01 00:00:00', 1, NULL, '52585d4928e14ab390541cea49a33541.webp', 1, '68313d7347534991a96b5980198bcf4b.webp', 1);
INSERT INTO `competition` VALUES (3, '百度一下，你就知道', '笔记齐全的毛概书走过路过不要错过，考试必备，临阵突击也能过过过！', 2, '2019-03-26 00:00:00', '2019-04-01 00:00:00', 1, NULL, '7f638efbfcd34e5a8da4c82c45acb069.webp', 1, '945376c494e340ba9661d07b21921fa5.webp', 1);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) NULL DEFAULT NULL,
  `competition_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_id` int(11) NULL DEFAULT NULL,
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `team_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL COMMENT '0未读1已读',
  `link_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (3, 2, '全屋定制装修', 1, '天天', '20190401001', NULL, '亲爱的天天同学，你参加的比赛 全屋定制装修 已经公布成绩，请尽快下载查看！', 1, '2');
INSERT INTO `message` VALUES (4, 1, '1', 1, '天天', '20190401001', '让人', '亲爱的天天同学，你所在的队伍 让人 参加的比赛 1 已经公布成绩，请尽快下载查看！', 1, '1');
INSERT INTO `message` VALUES (5, 3, '百度一下，你就知道', 1, '天天', '20190401001', '哈哈哈', '亲爱的天天同学，你所在的队伍 哈哈哈 参加的比赛 百度一下，你就知道 已经公布成绩，请尽快下载查看！', 1, '3');
INSERT INTO `message` VALUES (6, 3, '百度一下，你就知道', 2, '迪迪', '20190401002', '哈哈哈', '亲爱的迪迪同学，你所在的队伍 哈哈哈 参加的比赛 百度一下，你就知道 已经公布成绩，请尽快下载查看！', 0, '3');

-- ----------------------------
-- Table structure for organizer
-- ----------------------------
DROP TABLE IF EXISTS `organizer`;
CREATE TABLE `organizer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organizer
-- ----------------------------
INSERT INTO `organizer` VALUES (1, '组织者1', 'organizer1', '123456');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` tinyint(4) NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '20190401001', '123456', '天天', 1, '2018-09-09', '13888888888', '理学院');
INSERT INTO `student` VALUES (2, '20190401002', '123456', '迪迪', 0, '2019-05-06', '13888888889', '化工学院');

SET FOREIGN_KEY_CHECKS = 1;
