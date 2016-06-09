/*
MySQL Data Transfer
Source Host: localhost
Source Database: servlet_users_manager
Target Host: localhost
Target Database: servlet_users_manager
Date: 2016/6/9 20:38:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `grade` int(11) NOT NULL DEFAULT '1',
  `passwd` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'qm1', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('2', 'qm2', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('3', 'qm3', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('4', 'qm4', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('5', 'qm5', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('6', 'qm6', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('7', 'qm7', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('8', 'qm8', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('9', 'qm9', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('10', 'qm10', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('11', 'qm11', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('12', 'qm12', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('13', 'qm13', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('14', 'qm14', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('15', 'qm15', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('16', 'qm16', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('17', 'qm17', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('18', 'qm18', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('19', 'qm19', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('20', 'qm20', '369201191@qq.com', '1', 'qm');
INSERT INTO `users` VALUES ('21', 'qm21', '369201191@qq.com', '1', 'qm');
