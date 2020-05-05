/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : ups

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/11/2019 09:54:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ups_account
-- ----------------------------
DROP TABLE IF EXISTS `ups_account`;
CREATE TABLE `ups_account`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `acName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `isAdmin` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为管理员，1是，0否。管理员可以修改数据，否则只能查看',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户表（本系统登录用户表）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_account
-- ----------------------------
INSERT INTO `ups_account` VALUES ('55f58e2e039346ae8bb4c35435d5be39', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'user01', '202cb962ac59075b964b07152d234b70', '1', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-22 17:21:06');
INSERT INTO `ups_account` VALUES ('55f58e2e039346ae8bb4c35435d5be38', 'SYSTEMALL', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-05 19:23:39');
INSERT INTO `ups_account` VALUES ('55f58e2e039346ae8bb4c35435d5be37', 'SYSTEMALL', 'view', '202cb962ac59075b964b07152d234b70', '0', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-06 19:09:48');
INSERT INTO `ups_account` VALUES ('95549e0171de49a39868ef6926631772', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'user04', '202cb962ac59075b964b07152d234b70', '1', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-07 19:02:09');
INSERT INTO `ups_account` VALUES ('ee6d226506ed497491235f894ddff57b', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'user07', '202cb962ac59075b964b07152d234b70', '0', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-18 17:20:23');
INSERT INTO `ups_account` VALUES ('04962553cb0d40b6bf9b899c7546873f', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'user02', '202cb962ac59075b964b07152d234b70', '1', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-07 18:43:33');

-- ----------------------------
-- Table structure for ups_application
-- ----------------------------
DROP TABLE IF EXISTS `ups_application`;
CREATE TABLE `ups_application`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名称',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_application
-- ----------------------------
INSERT INTO `ups_application` VALUES ('17d11bf136f14717ac75a2ea69caeef7', 'test1', '123', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-07 19:10:00');
INSERT INTO `ups_application` VALUES ('XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '信息管理系统开发平台VSTA', 'IMS-VSTA', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-07 18:58:52');
INSERT INTO `ups_application` VALUES ('XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', '文件管理系统VSFS', 'IMS-VSFS', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-06 20:12:48');
INSERT INTO `ups_application` VALUES ('XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', '内部管理系统VSIM', 'IMS-VSIM', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-07 19:05:07');

-- ----------------------------
-- Table structure for ups_extension_attr
-- ----------------------------
DROP TABLE IF EXISTS `ups_extension_attr`;
CREATE TABLE `ups_extension_attr`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `attrName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性名称',
  `attrCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性编码',
  `plugId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性使用的控件id',
  `isNull` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性值是否可以为空',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '扩展属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_extension_attr
-- ----------------------------
INSERT INTO `ups_extension_attr` VALUES ('1', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '组织扩展属性1', 'orgattr1', 'f5bc17e7324d4bb98839250150a83610', '0', '1', NULL, NULL);
INSERT INTO `ups_extension_attr` VALUES ('2', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '组织扩展属性2', 'orgattr2', '8fed1d86efcf47ae869b751c0109e919', '0', '1', NULL, NULL);

-- ----------------------------
-- Table structure for ups_extension_attr_relation
-- ----------------------------
DROP TABLE IF EXISTS `ups_extension_attr_relation`;
CREATE TABLE `ups_extension_attr_relation`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `unitId` int(2) NOT NULL COMMENT '引用属性的单元（表），用户1，组织2',
  `attrId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `unitId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '扩展属性引用关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_form_plugin
-- ----------------------------
DROP TABLE IF EXISTS `ups_form_plugin`;
CREATE TABLE `ups_form_plugin`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `plugType` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插件类型,1单行文本，2多行文本，3日期框，4单选框，5复选框，6下拉框',
  `plugName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插件名称',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '扩展属性（表单展示）插件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_form_plugin
-- ----------------------------
INSERT INTO `ups_form_plugin` VALUES ('1', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', '2', '描述内容', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-23 10:01:22');
INSERT INTO `ups_form_plugin` VALUES ('4377d50730984eada673066aaffc4114', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '3', '测试日期框', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-22 18:16:54');
INSERT INTO `ups_form_plugin` VALUES ('f5bc17e7324d4bb98839250150a83610', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', '6', '下拉', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-23 10:01:33');
INSERT INTO `ups_form_plugin` VALUES ('a602f442ce9a4a7082346631c5d1d978', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '5', 'test1', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-22 18:16:32');
INSERT INTO `ups_form_plugin` VALUES ('ac8325134d804b70831048b25ae2e367', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', '4', 'test0422', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-26 15:01:01');
INSERT INTO `ups_form_plugin` VALUES ('b1b7c158a8994f8bb7f7093ec9b716bb', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '1', '单行文本测试', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-24 11:20:47');
INSERT INTO `ups_form_plugin` VALUES ('8fed1d86efcf47ae869b751c0109e919', '17d11bf136f14717ac75a2ea69caeef7', '4', '1', '1', '55f58e2e039346ae8bb4c35435d5be38', '2019-04-24 16:48:38');

-- ----------------------------
-- Table structure for ups_form_plugin_data
-- ----------------------------
DROP TABLE IF EXISTS `ups_form_plugin_data`;
CREATE TABLE `ups_form_plugin_data`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `plugId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插件id',
  `plugDataKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插件数据key',
  `plugDataValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '插件数据value'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '扩展属性（表单展示）插件数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_form_plugin_data
-- ----------------------------
INSERT INTO `ups_form_plugin_data` VALUES ('df6e75f3b6774223a35c1fe3ea65b987', 'ac8325134d804b70831048b25ae2e367', '1556075951710', '666666');
INSERT INTO `ups_form_plugin_data` VALUES ('4c7f1b127da14b7ab6ac7bb50555a52f', 'ac8325134d804b70831048b25ae2e367', '1556075951710', '￥……&*#￥FG地方');
INSERT INTO `ups_form_plugin_data` VALUES ('970416c4e58b4dd3be11e8458e0168f6', 'ac8325134d804b70831048b25ae2e367', '1556075951710', '888');
INSERT INTO `ups_form_plugin_data` VALUES ('6b03a7dd089b47e886caae7ecca07fc0', 'ac8325134d804b70831048b25ae2e367', '1556075951710', '劳动法规及');
INSERT INTO `ups_form_plugin_data` VALUES ('92c81b83c62d4dd6a06e3ca6d08a4f83', '8fed1d86efcf47ae869b751c0109e919', '111', '234');
INSERT INTO `ups_form_plugin_data` VALUES ('8eb01dec922340fd9cbb9109d991a4a5', '8fed1d86efcf47ae869b751c0109e919', '222', 'Yui');
INSERT INTO `ups_form_plugin_data` VALUES ('aeca4cee158f492e88471734b368150d', '8fed1d86efcf47ae869b751c0109e919', '333', '456');
INSERT INTO `ups_form_plugin_data` VALUES ('46a3faa1041f4b00b793abe42074fd16', '8fed1d86efcf47ae869b751c0109e919', '444', 'ddd');

-- ----------------------------
-- Table structure for ups_organization
-- ----------------------------
DROP TABLE IF EXISTS `ups_organization`;
CREATE TABLE `ups_organization`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `orgName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织名称',
  `orgCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织编码',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_organization_attr
-- ----------------------------
DROP TABLE IF EXISTS `ups_organization_attr`;
CREATE TABLE `ups_organization_attr`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `orgAttrName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性名称',
  `orgAttrCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性编码',
  `orgAttrPlugId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性使用的控件id',
  `isNull` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性值是否可以为空',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构扩展属性字段表（弃用）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_organization_attr
-- ----------------------------
INSERT INTO `ups_organization_attr` VALUES ('1', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '组织扩展属性1', 'orgattr1', 'f5bc17e7324d4bb98839250150a83610', '0', '1', NULL, NULL);
INSERT INTO `ups_organization_attr` VALUES ('2', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '组织扩展属性2', 'orgattr2', '8fed1d86efcf47ae869b751c0109e919', '0', '1', NULL, NULL);

-- ----------------------------
-- Table structure for ups_organization_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `ups_organization_attr_value`;
CREATE TABLE `ups_organization_attr_value`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `orgId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织数据id',
  `attrId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性字段id',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `orgId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构扩展属性字段数据值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_organization_resource
-- ----------------------------
DROP TABLE IF EXISTS `ups_organization_resource`;
CREATE TABLE `ups_organization_resource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `orgId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织id',
  `resourceId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织-资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_resource
-- ----------------------------
DROP TABLE IF EXISTS `ups_resource`;
CREATE TABLE `ups_resource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `resName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `resCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源编码',
  `resType` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型，1目录 2模块 3按钮',
  `resUrl` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源地址',
  `resIcon` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `resOrder` int(2) NOT NULL COMMENT '资源在同级目录内的排序序号，1开始',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_role
-- ----------------------------
DROP TABLE IF EXISTS `ups_role`;
CREATE TABLE `ups_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `roleName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `roleCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_role_organization
-- ----------------------------
DROP TABLE IF EXISTS `ups_role_organization`;
CREATE TABLE `ups_role_organization`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `orgId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织id',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `ups_role_resource`;
CREATE TABLE `ups_role_resource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `resourceId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_user
-- ----------------------------
DROP TABLE IF EXISTS `ups_user`;
CREATE TABLE `ups_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `userName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称（代码）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称（姓名）',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_user
-- ----------------------------
INSERT INTO `ups_user` VALUES ('10', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang9', '202cb962ac59075b964b07152d234b70', '小强9', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('100', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang99', '202cb962ac59075b964b07152d234b70', '小强99', '0', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('101', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang100', '202cb962ac59075b964b07152d234b70', '小强100', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('102', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang101', '202cb962ac59075b964b07152d234b70', '小强101', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('103', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang102', '202cb962ac59075b964b07152d234b70', '小强102', '0', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('104', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang103', '202cb962ac59075b964b07152d234b70', '小强103', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('105', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang104', '202cb962ac59075b964b07152d234b70', '小强104', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('106', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang105', '202cb962ac59075b964b07152d234b70', '小强105', '0', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('107', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang106', '202cb962ac59075b964b07152d234b70', '小强106', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('108', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang107', '202cb962ac59075b964b07152d234b70', '小强107', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('109', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang108', '202cb962ac59075b964b07152d234b70', '小强108', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('11', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang10', '202cb962ac59075b964b07152d234b70', '小强10', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('110', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang109', '202cb962ac59075b964b07152d234b70', '小强109', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('111', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang110', '202cb962ac59075b964b07152d234b70', '小强110', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('112', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang111', '202cb962ac59075b964b07152d234b70', '小强111', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('113', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang112', '202cb962ac59075b964b07152d234b70', '小强112', '0', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('114', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang113', '202cb962ac59075b964b07152d234b70', '小强113', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('115', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang114', '202cb962ac59075b964b07152d234b70', '小强114', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('116', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang115', '202cb962ac59075b964b07152d234b70', '小强115', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('117', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang116', '202cb962ac59075b964b07152d234b70', '小强116', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('118', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang117', '202cb962ac59075b964b07152d234b70', '小强117', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('119', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang118', '202cb962ac59075b964b07152d234b70', '小强118', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('12', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang11', '202cb962ac59075b964b07152d234b70', '小强11', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('120', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang119', '202cb962ac59075b964b07152d234b70', '小强119', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('121', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang120', '202cb962ac59075b964b07152d234b70', '小强120', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('122', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang121', '202cb962ac59075b964b07152d234b70', '小强121', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('123', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang122', '202cb962ac59075b964b07152d234b70', '小强122', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('124', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang123', '202cb962ac59075b964b07152d234b70', '小强123', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('125', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang124', '202cb962ac59075b964b07152d234b70', '小强124', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('126', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang125', '202cb962ac59075b964b07152d234b70', '小强125', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('127', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang126', '202cb962ac59075b964b07152d234b70', '小强126', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('128', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang127', '202cb962ac59075b964b07152d234b70', '小强127', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('129', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang128', '202cb962ac59075b964b07152d234b70', '小强128', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('13', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang12', '202cb962ac59075b964b07152d234b70', '小强12', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('130', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang129', '202cb962ac59075b964b07152d234b70', '小强129', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('131', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang130', '202cb962ac59075b964b07152d234b70', '小强130', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('132', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang131', '202cb962ac59075b964b07152d234b70', '小强131', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('133', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang132', '202cb962ac59075b964b07152d234b70', '小强132', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('134', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang133', '202cb962ac59075b964b07152d234b70', '小强133', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('135', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang134', '202cb962ac59075b964b07152d234b70', '小强134', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('136', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang135', '202cb962ac59075b964b07152d234b70', '小强135', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('137', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang136', '202cb962ac59075b964b07152d234b70', '小强136', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('138', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang137', '202cb962ac59075b964b07152d234b70', '小强137', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('139', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang138', '202cb962ac59075b964b07152d234b70', '小强138', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('14', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang13', '202cb962ac59075b964b07152d234b70', '小强13', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('140', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang139', '202cb962ac59075b964b07152d234b70', '小强139', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('141', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang140', '202cb962ac59075b964b07152d234b70', '小强140', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('142', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang141', '202cb962ac59075b964b07152d234b70', '小强141', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('143', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang142', '202cb962ac59075b964b07152d234b70', '小强142', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('144', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang143', '202cb962ac59075b964b07152d234b70', '小强143', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('145', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang144', '202cb962ac59075b964b07152d234b70', '小强144', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('146', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang145', '202cb962ac59075b964b07152d234b70', '小强145', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('15', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang14', '202cb962ac59075b964b07152d234b70', '小强14', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('16', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang15', '202cb962ac59075b964b07152d234b70', '小强15', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('17', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang16', '202cb962ac59075b964b07152d234b70', '小强16', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('18', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang17', '202cb962ac59075b964b07152d234b70', '小强17', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('19', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang18', '202cb962ac59075b964b07152d234b70', '小强18', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('2', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang1', '202cb962ac59075b964b07152d234b70', '小强1', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('20', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang19', '202cb962ac59075b964b07152d234b70', '小强19', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('21', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang20', '202cb962ac59075b964b07152d234b70', '小强20', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('22', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang21', '202cb962ac59075b964b07152d234b70', '小强21', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('23', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang22', '202cb962ac59075b964b07152d234b70', '小强22', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('24', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang23', '202cb962ac59075b964b07152d234b70', '小强23', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('25', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang24', '202cb962ac59075b964b07152d234b70', '小强24', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('26', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang25', '202cb962ac59075b964b07152d234b70', '小强25', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('27', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang26', '202cb962ac59075b964b07152d234b70', '小强26', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('28', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang27', '202cb962ac59075b964b07152d234b70', '小强27', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('29', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang28', '202cb962ac59075b964b07152d234b70', '小强28', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('3', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang2', '202cb962ac59075b964b07152d234b70', '小强2', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('30', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang29', '202cb962ac59075b964b07152d234b70', '小强29', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('31', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang30', '202cb962ac59075b964b07152d234b70', '小强30', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('32', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang31', '202cb962ac59075b964b07152d234b70', '小强31', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('33', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang32', '202cb962ac59075b964b07152d234b70', '小强32', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('34', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang33', '202cb962ac59075b964b07152d234b70', '小强33', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('35', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang34', '202cb962ac59075b964b07152d234b70', '小强34', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('36', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang35', '202cb962ac59075b964b07152d234b70', '小强35', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('37', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang36', '202cb962ac59075b964b07152d234b70', '小强36', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('38', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang37', '202cb962ac59075b964b07152d234b70', '小强37', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('39', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang38', '202cb962ac59075b964b07152d234b70', '小强38', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('4', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang3', '202cb962ac59075b964b07152d234b70', '小强3', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('40', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang39', '202cb962ac59075b964b07152d234b70', '小强39', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('41', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang40', '202cb962ac59075b964b07152d234b70', '小强40', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('42', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang41', '202cb962ac59075b964b07152d234b70', '小强41', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('43', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang42', '202cb962ac59075b964b07152d234b70', '小强42', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('44', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang43', '202cb962ac59075b964b07152d234b70', '小强43', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('45', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang44', '202cb962ac59075b964b07152d234b70', '小强44', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('46', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang45', '202cb962ac59075b964b07152d234b70', '小强45', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('47', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang46', '202cb962ac59075b964b07152d234b70', '小强46', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('48', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang47', '202cb962ac59075b964b07152d234b70', '小强47', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('49', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang48', '202cb962ac59075b964b07152d234b70', '小强48', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('5', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang4', '202cb962ac59075b964b07152d234b70', '小强4', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('50', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang49', '202cb962ac59075b964b07152d234b70', '小强49', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('51', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang50', '202cb962ac59075b964b07152d234b70', '小强50', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('52', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang51', '202cb962ac59075b964b07152d234b70', '小强51', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('53', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang52', '202cb962ac59075b964b07152d234b70', '小强52', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('54', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang53', '202cb962ac59075b964b07152d234b70', '小强53', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('55', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang54', '202cb962ac59075b964b07152d234b70', '小强54', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('56', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang55', '202cb962ac59075b964b07152d234b70', '小强55', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('57', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang56', '202cb962ac59075b964b07152d234b70', '小强56', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('58', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang57', '202cb962ac59075b964b07152d234b70', '小强57', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('59', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang58', '202cb962ac59075b964b07152d234b70', '小强58', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('6', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang5', '202cb962ac59075b964b07152d234b70', '小强5', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('60', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang59', '202cb962ac59075b964b07152d234b70', '小强59', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('61', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang60', '202cb962ac59075b964b07152d234b70', '小强60', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('62', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang61', '202cb962ac59075b964b07152d234b70', '小强61', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('63', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang62', '202cb962ac59075b964b07152d234b70', '小强62', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('64', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang63', '202cb962ac59075b964b07152d234b70', '小强63', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('65', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang64', '202cb962ac59075b964b07152d234b70', '小强64', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('66', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang65', '202cb962ac59075b964b07152d234b70', '小强65', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('67', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang66', '202cb962ac59075b964b07152d234b70', '小强66', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('68', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang67', '202cb962ac59075b964b07152d234b70', '小强67', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('69', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang68', '202cb962ac59075b964b07152d234b70', '小强68', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('7', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang6', '202cb962ac59075b964b07152d234b70', '小强6', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('70', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang69', '202cb962ac59075b964b07152d234b70', '小强69', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('71', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang70', '202cb962ac59075b964b07152d234b70', '小强70', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('72', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang71', '202cb962ac59075b964b07152d234b70', '小强71', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('73', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang72', '202cb962ac59075b964b07152d234b70', '小强72', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('74', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang73', '202cb962ac59075b964b07152d234b70', '小强73', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('75', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang74', '202cb962ac59075b964b07152d234b70', '小强74', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('76', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang75', '202cb962ac59075b964b07152d234b70', '小强75', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('77', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang76', '202cb962ac59075b964b07152d234b70', '小强76', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('78', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang77', '202cb962ac59075b964b07152d234b70', '小强77', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('79', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang78', '202cb962ac59075b964b07152d234b70', '小强78', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('8', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang7', '202cb962ac59075b964b07152d234b70', '小强7', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('80', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang79', '202cb962ac59075b964b07152d234b70', '小强79', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('81', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang80', '202cb962ac59075b964b07152d234b70', '小强80', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('82', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang81', '202cb962ac59075b964b07152d234b70', '小强81', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('83', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang82', '202cb962ac59075b964b07152d234b70', '小强82', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('84', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL67', 'xiaoqiang83', '202cb962ac59075b964b07152d234b70', '小强83', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('85', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang84', '202cb962ac59075b964b07152d234b70', '小强84', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('86', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang85', '202cb962ac59075b964b07152d234b70', '小强85', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('87', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang86', '202cb962ac59075b964b07152d234b70', '小强86', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('88', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang87', '202cb962ac59075b964b07152d234b70', '小强87', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('89', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang88', '202cb962ac59075b964b07152d234b70', '小强88', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('9', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL66', 'xiaoqiang8', '202cb962ac59075b964b07152d234b70', '小强8', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('90', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang89', '202cb962ac59075b964b07152d234b70', '小强89', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('91', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang90', '202cb962ac59075b964b07152d234b70', '小强90', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('92', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang91', '202cb962ac59075b964b07152d234b70', '小强91', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('93', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang92', '202cb962ac59075b964b07152d234b70', '小强92', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('94', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang93', '202cb962ac59075b964b07152d234b70', '小强93', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('95', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang94', '202cb962ac59075b964b07152d234b70', '小强94', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('96', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang95', '202cb962ac59075b964b07152d234b70', '小强95', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('97', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang96', '202cb962ac59075b964b07152d234b70', '小强96', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('98', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang97', '202cb962ac59075b964b07152d234b70', '小强97', '1', 'admin', '2019-04-10 10:57:12');
INSERT INTO `ups_user` VALUES ('99', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', 'xiaoqiang98', '202cb962ac59075b964b07152d234b70', '小强98', '1', 'admin', '2019-04-10 10:57:12');

-- ----------------------------
-- Table structure for ups_user_attr
-- ----------------------------
DROP TABLE IF EXISTS `ups_user_attr`;
CREATE TABLE `ups_user_attr`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `userAttrName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性名称',
  `userAttrCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性编码',
  `userAttrPlugId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展属性使用的控件id',
  `isNull` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性值是否可以为空',
  `isAvailable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否有效，1有效，0无效',
  `optName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `optDate` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户扩展属性字段表（弃用）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ups_user_attr
-- ----------------------------
INSERT INTO `ups_user_attr` VALUES ('1', 'XWFZMBUGATDHGXISRKJEBNBVQLFPQL65', '用户扩展属性1', 'userattr1', '8fed1d86efcf47ae869b751c0109e919', '1', '1', NULL, NULL);

-- ----------------------------
-- Table structure for ups_user_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `ups_user_attr_value`;
CREATE TABLE `ups_user_attr_value`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户数据id',
  `attrId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性字段id',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展属性值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  UNIQUE INDEX `id_appid_index`(`id`, `userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户扩展属性字段数据值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `ups_user_organization`;
CREATE TABLE `ups_user_organization`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `orgId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-组织关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `ups_user_resource`;
CREATE TABLE `ups_user_resource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `resourceId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ups_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ups_user_role`;
CREATE TABLE `ups_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `appId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小明', 12, '2018-12-01', NULL);
INSERT INTO `user` VALUES (3, '小强3', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (4, '小强4', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (5, '小强5', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (8, '小强8', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (7, '小强7', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (6, '小强6', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (9, '小强9', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (10, '小强10', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (11, '小强11', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (12, '小强12', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (13, 'xiaoqiang', 8, '2019-03-04', NULL);
INSERT INTO `user` VALUES (2, '小强2', 16, '2018-03-09', NULL);
INSERT INTO `user` VALUES (15, '小强15', 8, '2019-03-04', NULL);
INSERT INTO `user` VALUES (16, '小强xiaoqiang16', 8, '2019-03-04', '2019-03-04 15:42:54');
INSERT INTO `user` VALUES (17, 'xiaoqiang17', 8, '2019-03-04', '2019-03-04 15:32:10');
INSERT INTO `user` VALUES (18, 'xiaoqiang18', 8, '2019-03-04', '2019-03-04 15:38:10');

SET FOREIGN_KEY_CHECKS = 1;
