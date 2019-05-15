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

 Date: 15/05/2019 16:42:26
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `students_num` int(11) NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of applyinfo
-- ----------------------------
INSERT INTO `applyinfo` VALUES (1, 1, '1', '2019-03-22 00:00:00', '2019-04-03 00:00:00', 3, 1, '天天', '20190401001', '理学院', '13888888888', '让人', 1, 1, '');
INSERT INTO `applyinfo` VALUES (2, 2, '全屋定制装修', '2019-03-24 00:00:00', '2019-04-01 00:00:00', 1, 1, '天天', '20190401001', '理学院', '13888888888', NULL, 1, 1, '');
INSERT INTO `applyinfo` VALUES (4, 3, '百度一下，你就知道', '2019-03-26 00:00:00', '2019-04-01 00:00:00', 2, 2, '迪迪', '20190401002', '化工学院', '13888888889', '哈哈哈', 0, 1, '');
INSERT INTO `applyinfo` VALUES (9, 7, '电工杯数学建模竞赛', '2019-05-18 00:00:00', '2019-05-21 00:00:00', 3, 1, '天天', '20190401001', '理学院', '13888888888', '建模竞赛队1', 1, 1, '2a32b544082347db92278dd7c00160fc.jpg');
INSERT INTO `applyinfo` VALUES (10, 7, '电工杯数学建模竞赛', '2019-05-18 00:00:00', '2019-05-21 00:00:00', 3, 2, '迪迪', '20190401002', '化工学院', '13888888889', '建模竞赛队1', 0, 1, '2a32b544082347db92278dd7c00160fc.jpg');
INSERT INTO `applyinfo` VALUES (11, 7, '电工杯数学建模竞赛', '2019-05-18 00:00:00', '2019-05-21 00:00:00', 3, 3, '孔府', '20190401003', '外国语学院', '13888888880', '建模竞赛队1', 0, 1, '2a32b544082347db92278dd7c00160fc.jpg');
INSERT INTO `applyinfo` VALUES (12, 8, '单人比赛测试', '2019-05-18 00:00:00', '2019-05-21 00:00:00', 1, 1, '天天', '20190401001', '理学院', '13888888888', NULL, 1, 1, NULL);

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upload_file` tinyint(4) NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, '1', '2', 1, 3, '2019-03-22 00:00:00', '2019-04-03 00:00:00', 1, NULL, '0fa837ff52ce4562bd0f0b5749351f9c.webp,0fa837ff52ce4562bd0f0b5749351f9c.webp,2b38e9b6d0894f33a66a0c4b7056a5c1.webp', 1, '111', 1);
INSERT INTO `competition` VALUES (2, '全屋定制装修', '笔记齐全的毛概书走过路过不要错过，考试必备，临阵突击也能过过过！', 0, 1, '2019-03-24 00:00:00', '2019-04-01 00:00:00', 1, NULL, '52585d4928e14ab390541cea49a33541.webp,52585d4928e14ab390541cea49a33541.webp,52585d4928e14ab390541cea49a33541.webp', 1, '', 0);
INSERT INTO `competition` VALUES (4, 'testrrr', '笔记齐全的毛概书走过路过不要错过，考试必备，临阵突击也能过过过！', 0, 1, '2019-03-22 00:00:00', '2019-03-26 00:00:00', 1, '组织者1', '', 1, NULL, 0);
INSERT INTO `competition` VALUES (7, '电工杯数学建模竞赛', '电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛电工杯数学建模竞赛', 0, 3, '2019-05-18 00:00:00', '2019-05-21 00:00:00', 1, '组织者1', 'cecb16352df34393b540c2213c8a8374.jpg,35c2dbd74c1e467bb07dbd5213535a7b.jpg,2e3c2e4c82f045b9ba1d0574db67c2fa.jpg,b514514439624902873b2e3ad08a33a6.jpg,4045e95760044c6cba3e6103c7eb4f41.jpg', 1, 'tmp', 1);
INSERT INTO `competition` VALUES (8, '单人比赛测试', '单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试单人比赛测试', 0, 1, '2019-05-18 00:00:00', '2019-05-21 00:00:00', 1, '组织者1', '7f59f90548df4074a5c404997a8d9f49.pdf', 1, NULL, 0);

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
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0成绩1准考证',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (3, 2, '全屋定制装修', 1, '天天', '20190401001', NULL, '亲爱的天天同学，你参加的比赛 全屋定制装修 已经公布成绩，请尽快下载查看！', 1, '2', 0);
INSERT INTO `message` VALUES (4, 1, '1', 1, '天天', '20190401001', '让人', '亲爱的天天同学，你所在的队伍 让人 参加的比赛 1 已经公布成绩，请尽快下载查看！', 1, '1', 0);
INSERT INTO `message` VALUES (5, 3, '百度一下，你就知道', 1, '天天', '20190401001', '哈哈哈', '亲爱的天天同学，你所在的队伍 哈哈哈 参加的比赛 百度一下，你就知道 已经公布成绩，请尽快下载查看！', 1, '3', 0);
INSERT INTO `message` VALUES (6, 3, '百度一下，你就知道', 2, '迪迪', '20190401002', '哈哈哈', '亲爱的迪迪同学，你所在的队伍 哈哈哈 参加的比赛 百度一下，你就知道 已经公布成绩，请尽快下载查看！', 0, '3', 0);
INSERT INTO `message` VALUES (7, 1, '1', 1, '天天', '20190401001', '让人', '亲爱的天天同学，你所在的队伍 让人 参加的比赛 1 已经公布成绩，请前往查看！', 1, '1', 0);
INSERT INTO `message` VALUES (8, 1, '1', 1, '天天', '20190401001', '让人', '亲爱的天天同学，你参加的比赛 1 已经发放准考证，请及时下载！', 1, '1', 1);
INSERT INTO `message` VALUES (16, 7, '电工杯数学建模竞赛', 1, '天天', '20190401001', '建模竞赛队1', '亲爱的天天同学，你所在的队伍 建模竞赛队1 参加的比赛 电工杯数学建模竞赛 已经公布成绩，请前往查看！', 1, '7', 0);
INSERT INTO `message` VALUES (17, 7, '电工杯数学建模竞赛', 2, '迪迪', '20190401002', '建模竞赛队1', '亲爱的迪迪同学，你所在的队伍 建模竞赛队1 参加的比赛 电工杯数学建模竞赛 已经公布成绩，请前往查看！', 0, '7', 0);
INSERT INTO `message` VALUES (18, 7, '电工杯数学建模竞赛', 3, '孔府', '20190401003', '建模竞赛队1', '亲爱的孔府同学，你所在的队伍 建模竞赛队1 参加的比赛 电工杯数学建模竞赛 已经公布成绩，请前往查看！', 0, '7', 0);
INSERT INTO `message` VALUES (20, 8, '单人比赛测试', 1, '天天', '20190401001', NULL, '亲爱的天天同学，你参加的比赛 单人比赛测试 已经发放准考证，请及时下载！', 1, '12', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organizer
-- ----------------------------
INSERT INTO `organizer` VALUES (1, '组织者1', 'organizer1', '123456');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) NULL DEFAULT NULL,
  `student_id` int(11) NULL DEFAULT NULL,
  `student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (3, 1, 1, '天天', '20190401001', '理学院', '13888888888', '66');
INSERT INTO `score` VALUES (4, 2, 1, '天天', '20190401001', '理学院', '13888888888', '66');
INSERT INTO `score` VALUES (8, 7, 1, '天天', '20190401001', '理学院', '13888888888', '98');
INSERT INTO `score` VALUES (9, 7, 2, '迪迪', '20190401002', '化工学院', '13888888889', '99');
INSERT INTO `score` VALUES (10, 7, 3, '孔府', '20190401003', '外国语学院', '13888888880', '100');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '20190401001', '123456', '天天', 1, '2018-09-09', '13888888888', '理学院');
INSERT INTO `student` VALUES (2, '20190401002', '123456', '迪迪', 0, '2019-05-06', '13888888889', '化工学院');
INSERT INTO `student` VALUES (3, '20190401003', '123456', '孔府', 1, '2019-05-15', '13888888880', '外国语学院');

SET FOREIGN_KEY_CHECKS = 1;
