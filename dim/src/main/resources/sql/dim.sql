/*
Navicat MySQL Data Transfer

Source Server         : dim
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : dim

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-07 12:50:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dim_goods
-- ----------------------------
DROP TABLE IF EXISTS `dim_goods`;
CREATE TABLE `dim_goods` (
`GOODS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID' ,
`GOODS_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称' ,
`GOODS_CODE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编码' ,
`GOODS_TYPE_CODE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型编码' ,
`GOODS_STOCK`  int(11) NULL DEFAULT NULL COMMENT '商品库存' ,
`GOODS_PURCHASE_PRICE`  float NULL DEFAULT NULL COMMENT '商品进货价格' ,
`GOODS_SELL_PRICE`  float NULL DEFAULT NULL COMMENT '商品售出价格' ,
`GOODS_DESCRIBE`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述' ,
`GOODS_PICTURE_ID`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片ID' ,
`GOODS_CREATE_USER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品创建者' ,
`GOODS_CREATE_TIME`  datetime NULL DEFAULT NULL COMMENT '商品创建时间' ,
`GOODS_UPDATE_USER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品修改者' ,
`GOODS_UPDATE_TIME`  datetime NULL DEFAULT NULL COMMENT '商品修改时间' ,
PRIMARY KEY (`GOODS_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商品信息表'

;

-- ----------------------------
-- Records of dim_goods
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dim_goods_purchase_business
-- ----------------------------
DROP TABLE IF EXISTS `dim_goods_purchase_business`;
CREATE TABLE `dim_goods_purchase_business` (
`GOODS_PURCHASE_BUSINESS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品进货商ID' ,
`GOODS_PURCHASE_BUSINESS_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品进货商名称' ,
`GOODS_PURCHASE_BUSINESS_PHONE`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品进货商电话号码' ,
PRIMARY KEY (`GOODS_PURCHASE_BUSINESS_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商品进货商信息表'

;

-- ----------------------------
-- Records of dim_goods_purchase_business
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dim_goods_purchase_log
-- ----------------------------
DROP TABLE IF EXISTS `dim_goods_purchase_log`;
CREATE TABLE `dim_goods_purchase_log` (
`GOODS_PURCHASE_LOG_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品进货日志ID' ,
`GOODS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID' ,
`GOODS_PURCHASE_PRICE`  float NOT NULL COMMENT '商品进货价格' ,
`GOODS_PURCHASE_QUANTITY`  int(11) NOT NULL COMMENT '商品进货数量' ,
`GOODS_PURCHASE_BUSINESS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品进货公司ID' ,
`GOODS_PURCHASE_TIME`  datetime NOT NULL COMMENT '商品进货时间' ,
`GOODS_PURCHASE_REMARKS`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品进货备注' ,
`OPERATION_USER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作用户' ,
`OPERATION_TIME`  datetime NOT NULL COMMENT '操作时间' ,
PRIMARY KEY (`GOODS_PURCHASE_LOG_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商品进货日志信息表'

;

-- ----------------------------
-- Records of dim_goods_purchase_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dim_goods_sell_log
-- ----------------------------
DROP TABLE IF EXISTS `dim_goods_sell_log`;
CREATE TABLE `dim_goods_sell_log` (
`GOODS_SELL_LOG_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品售出日志表' ,
`GOODS_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID' ,
`GOODS_SELL_PRICE`  float NOT NULL COMMENT '商品售出价格' ,
`GOODS_SELL_QUANTITY`  int(255) NOT NULL COMMENT '商品售出数量' ,
`GOODS_SELL_TIME`  datetime NOT NULL COMMENT '商品售出时间' ,
`GOODS_SELL_REMARKS`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品售出备注' ,
`GOODS_SELL_MEMBER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品购买会员ID' ,
`OPERATION_USER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者' ,
`OPERATION_TIME`  datetime NOT NULL COMMENT '操作时间' ,
PRIMARY KEY (`GOODS_SELL_LOG_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商品售出日志信息表'

;

-- ----------------------------
-- Records of dim_goods_sell_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dim_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `dim_goods_type`;
CREATE TABLE `dim_goods_type` (
`GOODS_TYPE_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类型ID' ,
`GOODS_TYPE_CODE`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类型编码' ,
`GOODS_TYPE_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类型名称' ,
PRIMARY KEY (`GOODS_TYPE_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商品类型信息表'

;

-- ----------------------------
-- Records of dim_goods_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dim_member
-- ----------------------------
DROP TABLE IF EXISTS `dim_member`;
CREATE TABLE `dim_member` (
`MEMBER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`MEMBER_ACCOUNT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`MEMBER_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`MEMBER_PHONE`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`MEMBER_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of dim_member
-- ----------------------------
BEGIN;
INSERT INTO `dim_member` VALUES ('1', 'visitor', '游客', '12345678900');
COMMIT;

-- ----------------------------
-- Table structure for dim_role
-- ----------------------------
DROP TABLE IF EXISTS `dim_role`;
CREATE TABLE `dim_role` (
`ROLE_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID' ,
`ROLE_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称' ,
`ROLE_CNNAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色中文名称' ,
PRIMARY KEY (`ROLE_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色信息表'

;

-- ----------------------------
-- Records of dim_role
-- ----------------------------
BEGIN;
INSERT INTO `dim_role` VALUES ('1', 'admin', '超级管理员'), ('2', 'staff', '员工');
COMMIT;

-- ----------------------------
-- Table structure for dim_user
-- ----------------------------
DROP TABLE IF EXISTS `dim_user`;
CREATE TABLE `dim_user` (
`USER_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID' ,
`USER_ACCOUNT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号' ,
`USER_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名' ,
`USER_PASSWORD`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码' ,
`ROLE_ID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID' ,
PRIMARY KEY (`USER_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户信息表'

;

-- ----------------------------
-- Records of dim_user
-- ----------------------------
BEGIN;
INSERT INTO `dim_user` VALUES ('1', 'admin', '大迪哥', '8d26584ca49160d402a353ea07a832f15191014f8d2cce265849d38beca982b1', '1');
COMMIT;
