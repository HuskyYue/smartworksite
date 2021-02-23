/*
 Navicat Premium Data Transfer

 Source Server         : yuezj
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : smart_work_site

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 23/02/2021 16:03:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aa_aatest_new
-- ----------------------------
DROP TABLE IF EXISTS `aa_aatest_new`;
CREATE TABLE `aa_aatest_new`  (
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `accountingyear` int(11) NULL DEFAULT NULL,
  `isquantity` tinyint(4) NULL DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  `Remainder` decimal(32, 15) NULL DEFAULT NULL,
  `ModifyLog` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createdtime` datetime NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aa_aatest_new
-- ----------------------------
INSERT INTO `aa_aatest_new` VALUES ('1', 0, 48, NULL, 23.145000000000000, '非现金交易', '2021-01-25 15:53:18', 13);
INSERT INTO `aa_aatest_new` VALUES ('2', 0, 48, NULL, 23.145000000000000, '非现金交易', '2021-01-25 15:53:18', 14);
INSERT INTO `aa_aatest_new` VALUES ('1001', 2021, 0, NULL, 2586.330000000000000, '现金交易', '2021-01-13 08:56:47', 15);
INSERT INTO `aa_aatest_new` VALUES ('1001', 2021, 0, NULL, 2586.330000000000000, '现金交易', '2021-01-13 08:56:47', 16);
INSERT INTO `aa_aatest_new` VALUES ('2018', 0, 48, NULL, 23.145000000000000, '非现金交易', '2021-01-25 15:53:18', 17);
INSERT INTO `aa_aatest_new` VALUES ('1001', 2021, 0, NULL, 2586.330000000000000, '现金交易', '2021-01-13 08:56:47', 19);
INSERT INTO `aa_aatest_new` VALUES ('2018', 0, 48, NULL, 23.145000000000000, '非现金交易', '2021-01-25 15:53:18', 20);

-- ----------------------------
-- Table structure for metadata
-- ----------------------------
DROP TABLE IF EXISTS `metadata`;
CREATE TABLE `metadata`  (
  `table` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `sync_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `sync_value` bigint(11) NOT NULL DEFAULT 0,
  `synd_datetime` datetime NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '增量数据标记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of metadata
-- ----------------------------

-- ----------------------------
-- Table structure for sws_cron
-- ----------------------------
DROP TABLE IF EXISTS `sws_cron`;
CREATE TABLE `sws_cron`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cron_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '唯一标识',
  `cron_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '定时器名称',
  `cron_class` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名（全）',
  `post_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进行数据获取的url',
  `get_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进行数据接入的url',
  `cron_method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '定时器执行方法',
  `cron` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '定时器表达式',
  `cron_class_params` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `cron_status` tinyint(4) NULL DEFAULT NULL COMMENT '定时器启动与否的状态',
  `mapper_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '映射表的唯一主键，用于关联映射表',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '定时器备注描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `classify` tinyint(4) NULL DEFAULT 0 COMMENT '1，主动接入数据；0，被动接入数据 ；2. 接口提供',
  `is_del` tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`, `cron_id`) USING BTREE,
  INDEX `select_key`(`cron_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_cron
-- ----------------------------
INSERT INTO `sws_cron` VALUES (46, '4f08c22f-2742-4827-91bf-8f1c50e8af13', '融创36获取塔吊实时信息入库', 'DataRequestScheduled', 'https://api.sws.sf-soft.com/open/crane/live', 'http://localhost:7112/tower_crane/insertForRongChuang', 'start2', '30 * * * * *', '{\n	\"token\": \"k1NZfBRm\",\n	\"sim\": \"13605418317\"\n}', 0, NULL, '融创36获取塔吊实时信息入库', '2021-01-18 15:44:26', '2021-01-18 15:44:26', 1, 1);
INSERT INTO `sws_cron` VALUES (47, '21aee689-9329-4edd-8e38-f662d3d0b4e8', '获取塔吊实时数据或司机信息010820080173', 'DataRequestScheduled', 'https://www.safe110.net/API/API.ashx', 'http://localhost:7112/tower_crane/insertTowerCraneForWomenAndChildrenHospitalEquipment010820080173', 'start1', '30 * * * * *', '{\n	\"PostType\": \"GetTowerCraneLiveDataWithDriverInfo\",\n	\"ProjectID\": \"91501ea3-721e-eb11-8e29-7845c4ecc6e2\",\n	\"DeviceSN\": \"010820080173\",\n	\"ForeignKey\": \"4ee2df15-25c1-4f95-beb4-bca62b77496a\",\n	\"UserName\": \"zjbjfy\"\n}', 1, NULL, '获取塔吊实时数据或司机信息010820080173', '2021-01-18 16:41:41', '2021-01-18 16:41:41', 1, 1);
INSERT INTO `sws_cron` VALUES (48, 'fa3219db-8b55-4399-8a8c-7334e2f76f62', '新增妇幼医院设备编号为010820100075塔机数据', 'DataRequestScheduled', 'https://www.safe110.net/API/API.ashx', 'http://localhost:7112/tower_crane/insertTowerCraneForWomenAndChildrenHospitalEquipment010820100075', 'start3', '30 * * * * *', '{\n	\"PostType\": \"GetTowerCraneLiveDataWithDriverInfo\",\n	\"ProjectID\": \"91501ea3-721e-eb11-8e29-7845c4ecc6e2\",\n	\"DeviceSN\": \"010820100075\",\n	\"ForeignKey\": \"4ee2df15-25c1-4f95-beb4-bca62b77496a\",\n	\"UserName\": \"zjbjfy\"\n}', 1, NULL, '新增妇幼医院设备编号为010820100075塔机数据', '2021-01-19 08:49:16', '2021-01-19 08:49:16', 1, 1);
INSERT INTO `sws_cron` VALUES (49, '3f79379d-348a-45f3-bbaf-243ac85bfb59', '新增妇幼医院设备编号为010820100077塔机数据', 'DataRequestScheduled', 'https://www.safe110.net/API/API.ashx', 'http://localhost:7112/tower_crane/insertTowerCraneForWomenAndChildrenHospitalEquipment010820100077', 'start4', '30 * * * * *', '{\n	\"PostType\": \"GetTowerCraneLiveDataWithDriverInfo\",\n	\"ProjectID\": \"91501ea3-721e-eb11-8e29-7845c4ecc6e2\",\n	\"DeviceSN\": \"010820100077\",\n	\"ForeignKey\": \"4ee2df15-25c1-4f95-beb4-bca62b77496a\",\n	\"UserName\": \"zjbjfy\"\n}', 1, NULL, '新增妇幼医院设备编号为010820100077塔机数据', '2021-01-19 08:51:02', '2021-01-19 08:51:02', 1, 1);
INSERT INTO `sws_cron` VALUES (50, 'd8b544ba-3fef-489a-93be-4b6d864cbcf5', '新增妇幼医院设备编号为010820100078塔机数据', 'DataRequestScheduled', 'https://www.safe110.net/API/API.ashx', 'http://localhost:7112/tower_crane/insertTowerCraneForWomenAndChildrenHospitalEquipment010820100078', 'start5', '10 * * * * *', '{\n	\"PostType\": \"GetTowerCraneLiveDataWithDriverInfo\",\n	\"ProjectID\": \"91501ea3-721e-eb11-8e29-7845c4ecc6e2\",\n	\"DeviceSN\": \"010820100078\",\n	\"ForeignKey\": \"4ee2df15-25c1-4f95-beb4-bca62b77496a\",\n	\"UserName\": \"zjbjfy\"\n}', 1, NULL, '新增妇幼医院设备编号为010820100078塔机数据', '2021-01-19 08:53:11', '2021-01-19 09:38:15', 1, 1);

-- ----------------------------
-- Table structure for sws_data_provide_log
-- ----------------------------
DROP TABLE IF EXISTS `sws_data_provide_log`;
CREATE TABLE `sws_data_provide_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `api_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'API名称',
  `api_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对外暴露的 API url 地址',
  `api_status` tinyint(4) NULL DEFAULT NULL COMMENT 'API开放状态 1. 已开放 0. 未开放',
  `acquire_times_limit` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问限制',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) NULL DEFAULT 1 COMMENT '是否有效，用于逻辑删除 1. 有效 0. 无效',
  `user_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订阅者id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `api名称单列索引`(`api_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_data_provide_log
-- ----------------------------
INSERT INTO `sws_data_provide_log` VALUES (1, '任务10', 'passive/list', 0, '1-3-5', '任务10的备注', 1, '1,2,3,4,5', '2020-12-14 13:57:59', '2020-12-18 09:00:19');
INSERT INTO `sws_data_provide_log` VALUES (2, '任务11', 'passive/getList', 0, '1-4-10', '任务11的备注', 1, '1,2,3,4', '2020-12-14 14:14:02', '2020-12-16 15:50:39');
INSERT INTO `sws_data_provide_log` VALUES (3, '任务12', 'getList', 0, '1-6-10', '这是任务12的备注', 1, NULL, '2020-12-14 16:27:15', '2020-12-16 15:50:35');
INSERT INTO `sws_data_provide_log` VALUES (4, '动阀', '动阀', 0, '1-3-5', '动阀', 1, NULL, '2020-12-15 10:28:27', '2020-12-16 14:17:54');
INSERT INTO `sws_data_provide_log` VALUES (5, '动阀1', '动阀', 0, '1-0-10', '动阀', 1, NULL, '2020-12-15 10:29:28', '2020-12-16 14:17:56');
INSERT INTO `sws_data_provide_log` VALUES (6, '参数', '的', 1, '10-3-5', '动阀', 1, NULL, '2020-12-15 10:32:15', '2020-12-15 10:32:15');
INSERT INTO `sws_data_provide_log` VALUES (7, '第三方', '第三方', 1, '1-3-5', '第三方', 1, NULL, '2020-12-15 11:09:24', '2020-12-15 11:09:24');
INSERT INTO `sws_data_provide_log` VALUES (8, '第四方', '动阀', 1, '1-3-5', '第三方', 1, NULL, '2020-12-15 16:48:04', '2020-12-15 16:48:04');
INSERT INTO `sws_data_provide_log` VALUES (9, '任务名称', 'url/get', 1, '1-3-5', '第三方士大夫', 0, NULL, '2020-12-16 14:46:57', '2020-12-16 14:47:12');
INSERT INTO `sws_data_provide_log` VALUES (10, 'API名称', 'API地址', 1, '1-4-5', '第三方', 1, NULL, '2020-12-16 15:04:49', '2020-12-16 15:04:49');
INSERT INTO `sws_data_provide_log` VALUES (11, '任务22', '沙发沙发', 0, '20-3-5', '啊打发打发', 1, NULL, '2020-12-16 17:14:19', '2020-12-21 17:21:36');
INSERT INTO `sws_data_provide_log` VALUES (12, '任务1sd', 'getList', 1, '1-6-10', '这是任务12的备注', 0, NULL, '2020-12-16 17:15:13', '2020-12-18 09:10:25');
INSERT INTO `sws_data_provide_log` VALUES (13, '任务15', 'getList', 1, '1-6-10', '这是任务12的备注', 1, NULL, '2020-12-18 09:00:08', '2020-12-18 09:00:08');
INSERT INTO `sws_data_provide_log` VALUES (14, '实名制的1', 'get/list', 0, '1-3-5', 'sdfg ', 0, NULL, '2020-12-18 11:25:14', '2020-12-18 11:25:14');

-- ----------------------------
-- Table structure for sws_data_provide_mappers
-- ----------------------------
DROP TABLE IF EXISTS `sws_data_provide_mappers`;
CREATE TABLE `sws_data_provide_mappers`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cron_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'url接口路径',
  `database_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方数据库',
  `table_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fields` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '被选的表字段组（有序与mapper对应）以-拼接',
  `mappers` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对外提供字段名称（有序与fields对应）以-拼接',
  `alias` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对外提供字段别名（有序与fields对应），以-拼接',
  PRIMARY KEY (`id`, `cron_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_data_provide_mappers
-- ----------------------------
INSERT INTO `sws_data_provide_mappers` VALUES ('20210114103418019389282559065802', '327eb001-2646-49d3-8fbd-699a544c1792', 'hexo-blog', 'article', 'EditDate-InDate-InDateStr', 'title-id-create_time', 'varchar(255)-int(11)-datetime');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210114105725011728819814079823', '6f76cc9d-198e-4963-aa7b-49ad18f3b28d', 'smart_work_site', 'sws_cron', 'cronName-cronClassParams-cronStatus', 'cron_name-cron_class_params-cron_status', 'varchar(64)-varchar(500)-tinyint(4)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210114142253011015420911296976', '6f6438ea-b855-444f-bd1f-a7fbb49236cf', 'smart_work_site', 'sws_cron', 'createTime-company-appId', 'cron_class-cron_name-cron_id', 'varchar(128)-varchar(64)-varchar(36)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210115134730011717054488651372', '9d538bc4-b60d-46b1-8e31-aeea7908de7a', 'smart_work_site', 'sws_database', 'Amplitude-AmplitudeStatus-Crc', 'database_name-update_time-database_type', 'varchar(50)-datetime-tinyint(4)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210118091022011504840342398640', '7afd299c-b671-4207-b0c4-a202c370a8f4', 'smart_work_site', 'sws_cron', 'code-data-msg', 'cron_id-cron_name-cron_class', 'varchar(36)-varchar(64)-varchar(128)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210118152806014836579339204589', '99e584bd-c66d-4928-8689-87f72d63f84a', 'smart_work_site', 'sws_cron', 'Amplitude-AmplitudeStatus-Crc', 'cron_name-cron_class-cron_method', 'varchar(64)-varchar(128)-varchar(32)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210118154007011455647351692699', '4f08c22f-2742-4827-91bf-8f1c50e8af13', 'smart_work_site', 'sws_cron', 'Amplitude-AmplitudeStatus-Crc', 'cron_name-cron_class-cron_method', 'varchar(64)-varchar(128)-varchar(32)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210118163009011537548485402629', '21aee689-9329-4edd-8e38-f662d3d0b4e8', 'park_coupon_data', 'coupon_template', 'Angle-DeviceSN', '-', '-');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210119084836011100170866204180', 'fa3219db-8b55-4399-8a8c-7334e2f76f62', 'smart_work_site', 'sws_cron', 'Alarm-Angle-DeviceSN', 'id-cron_name-cron_class', 'int(20)-varchar(64)-varchar(64)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210119085034011924527701874730', '3f79379d-348a-45f3-bbaf-243ac85bfb59', 'smart_work_site', 'sws_cron', 'Alarm-Angle-DeviceSN', 'cron_name-cron_class-post_url', 'varchar(64)-varchar(64)-varchar(64)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210119085302010524446707814066', 'd8b544ba-3fef-489a-93be-4b6d864cbcf5', 'smart_work_site', 'sws_cron', 'Alarm-Angle-DeviceSN-IsOnline-Radius', 'cron_name-cron_class-post_url-get_url-cron_method', 'varchar(64)-varchar(64)-varchar(64)-varchar(128)-varchar(32)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210119093746017711179467059180', '7cfb19c2-aa1e-4999-8114-70e06a1822ea', 'smart_work_site', 'sws_cron', 'id', 'id', 'int(20)');
INSERT INTO `sws_data_provide_mappers` VALUES ('20210119142042011422911154644562', '1f301fa2-9750-40f8-b148-f9a0b0dafc87', 'smart_work_site', 'sws_cron', '“id”', 'id', 'int(20)');

-- ----------------------------
-- Table structure for sws_database
-- ----------------------------
DROP TABLE IF EXISTS `sws_database`;
CREATE TABLE `sws_database`  (
  `id` int(11) NOT NULL,
  `database_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据名',
  `database_type` tinyint(4) NULL DEFAULT NULL COMMENT '数据类型(1 数据库   2 url)',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `version` tinyint(4) NULL DEFAULT NULL COMMENT '版本号',
  `is_del` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除( 0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_database
-- ----------------------------
INSERT INTO `sws_database` VALUES (1, 'smart_work_site', 1, '2020-12-15 09:25:16', NULL, 1, 0);
INSERT INTO `sws_database` VALUES (2, 'dataj4mysql', 1, '2020-12-17 09:07:33', NULL, 1, 0);
INSERT INTO `sws_database` VALUES (3, '/database/column/list', 2, '2020-12-17 11:48:27', NULL, 1, 0);
INSERT INTO `sws_database` VALUES (4, '/database/table/list', 2, '2020-12-17 11:49:22', NULL, 1, 0);
INSERT INTO `sws_database` VALUES (5, '/database/data/list', 2, '2020-12-17 11:49:57', NULL, 1, 0);

-- ----------------------------
-- Table structure for sws_external_user
-- ----------------------------
DROP TABLE IF EXISTS `sws_external_user`;
CREATE TABLE `sws_external_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `company` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属公司',
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司唯一标识',
  `secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '私钥',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_del` tinyint(4) NULL DEFAULT 1 COMMENT '是否失效 1： 有效  0：失效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_external_user
-- ----------------------------
INSERT INTO `sws_external_user` VALUES (1, '找你去了', '动阀', '1001', 'TT84-dd45-gk98-ffg3', '2020-11-11 10:27:28', '2020-11-11 10:27:33', '撒地方', 1);
INSERT INTO `sws_external_user` VALUES (2, '第三方', '二日', '1002', 'TT84-dd45-gk98-ffg4', '2020-11-11 10:27:59', '2020-11-11 10:28:01', '动阀', 1);
INSERT INTO `sws_external_user` VALUES (3, '安琪拉', '*王者荣耀公司', '100s1889', '1c8fea06-59a4-413a-b66d-31ff0d4813df', '2020-12-18 16:36:14', '2020-12-18 17:29:36', '这是测试', 0);
INSERT INTO `sws_external_user` VALUES (4, '李白', '*王者荣耀公司', '100189', 'f2334f65-02cb-4848-a2f6-116fce75b28c', '2020-12-18 16:39:07', '2020-12-18 16:39:07', '这是测试', 0);
INSERT INTO `sws_external_user` VALUES (5, '李白1', '*王者荣耀公司', '1001889', '56531d6d-329e-4590-a7ae-ebe60749dc28', '2020-12-18 17:02:34', '2020-12-18 17:02:34', '这是测试', 0);
INSERT INTO `sws_external_user` VALUES (6, '李d白1', '*王者荣耀公司', '100s1889', '96c8e911-0956-4b5c-8b56-7a7bd73f51c2', '2020-12-18 17:02:50', '2020-12-18 17:02:50', '这是测试', 0);
INSERT INTO `sws_external_user` VALUES (7, '新的测试1', '撒地方', '撒地方', '724d5647-100f-436e-838d-066f5191c8c8', '2020-12-21 11:20:15', '2020-12-21 11:20:15', '撒地方', 0);
INSERT INTO `sws_external_user` VALUES (8, '新的测试2', '撒地方', '撒地方是', '724d5647-100f-436e-838d-066f5191c8c8', '2020-12-21 11:20:29', '2020-12-21 11:20:29', '撒地方', 0);
INSERT INTO `sws_external_user` VALUES (9, '李白2', '动阀', '动阀', '64186b2a-8856-445a-b763-5eae967edabf', '2020-12-21 13:30:13', '2020-12-21 13:30:13', '第三方', 0);
INSERT INTO `sws_external_user` VALUES (10, '李白', '撒地方', '撒地方是否', 'e9348452-43f1-49dd-ab23-f48d9ae6e7e8', '2020-12-21 13:30:38', '2020-12-22 14:08:29', '动阀', 0);
INSERT INTO `sws_external_user` VALUES (11, '李白33', '地方', '1004', '51aa838d-d263-43f1-b3f8-70ae6e717f1f', '2020-12-22 15:05:14', '2020-12-22 15:06:18', '第三方', 1);

-- ----------------------------
-- Table structure for sws_request_log
-- ----------------------------
DROP TABLE IF EXISTS `sws_request_log`;
CREATE TABLE `sws_request_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `app_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_del` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3633 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_request_log
-- ----------------------------
INSERT INTO `sws_request_log` VALUES (3632, '1001', 'TT84-dd45-gk98-ffg3', '#1859*2020', 'cd97710f2a60ec42adb99ffceabbd8d4', '2020-12-21 15:26:45', '2020-12-21 15:26:45', 1);

-- ----------------------------
-- Table structure for sws_scheduler
-- ----------------------------
DROP TABLE IF EXISTS `sws_scheduler`;
CREATE TABLE `sws_scheduler`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cron_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'DataRequestScheduled' COMMENT '定时任务执行器类名',
  `cron_method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '定时任务执行器方法名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_scheduler
-- ----------------------------
INSERT INTO `sws_scheduler` VALUES (1, 'DataRequestScheduled', 'start1');
INSERT INTO `sws_scheduler` VALUES (2, 'DataRequestScheduled', 'start2');
INSERT INTO `sws_scheduler` VALUES (3, 'DataRequestScheduled', 'start3');
INSERT INTO `sws_scheduler` VALUES (4, 'DataRequestScheduled', 'start4');
INSERT INTO `sws_scheduler` VALUES (5, 'DataRequestScheduled', 'start5');
INSERT INTO `sws_scheduler` VALUES (6, 'DataRequestScheduled', 'start6');
INSERT INTO `sws_scheduler` VALUES (7, 'DataRequestScheduled', 'start7');
INSERT INTO `sws_scheduler` VALUES (8, 'DataRequestScheduled', 'start8');
INSERT INTO `sws_scheduler` VALUES (9, 'DataRequestScheduled', 'start9');
INSERT INTO `sws_scheduler` VALUES (10, 'DataRequestScheduled', 'start10');
INSERT INTO `sws_scheduler` VALUES (11, 'DataRequestScheduled', 'start11');
INSERT INTO `sws_scheduler` VALUES (12, 'DataRequestScheduled', 'start12');
INSERT INTO `sws_scheduler` VALUES (13, 'DataRequestScheduled', 'start13');
INSERT INTO `sws_scheduler` VALUES (14, 'DataRequestScheduled', 'start14');
INSERT INTO `sws_scheduler` VALUES (15, 'DataRequestScheduled', 'start15');
INSERT INTO `sws_scheduler` VALUES (16, 'DataRequestScheduled', 'start16');
INSERT INTO `sws_scheduler` VALUES (17, 'DataRequestScheduled', 'start17');
INSERT INTO `sws_scheduler` VALUES (18, 'DataRequestScheduled', 'start18');
INSERT INTO `sws_scheduler` VALUES (19, 'DataRequestScheduled', 'start19');
INSERT INTO `sws_scheduler` VALUES (20, 'DataRequestScheduled', 'start20');
INSERT INTO `sws_scheduler` VALUES (21, 'DataRequestScheduled', 'start21');
INSERT INTO `sws_scheduler` VALUES (22, 'DataRequestScheduled', 'start22');
INSERT INTO `sws_scheduler` VALUES (23, 'DataRequestScheduled', 'start23');
INSERT INTO `sws_scheduler` VALUES (24, 'DataRequestScheduled', 'start24');
INSERT INTO `sws_scheduler` VALUES (25, 'DataRequestScheduled', 'start25');
INSERT INTO `sws_scheduler` VALUES (26, 'DataRequestScheduled', 'start26');
INSERT INTO `sws_scheduler` VALUES (27, 'DataRequestScheduled', 'start27');
INSERT INTO `sws_scheduler` VALUES (28, 'DataRequestScheduled', 'start28');
INSERT INTO `sws_scheduler` VALUES (29, 'DataRequestScheduled', 'start29');
INSERT INTO `sws_scheduler` VALUES (30, 'DataRequestScheduled', 'start30');

-- ----------------------------
-- Table structure for sws_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sws_sys_log`;
CREATE TABLE `sws_sys_log`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operator_table` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operation` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `memo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_sys_log
-- ----------------------------
INSERT INTO `sws_sys_log` VALUES (10, 'yuezejian', 'sws_sys_log', '新增数据', 'com.slzhkj.smartworksite.server.controller.DataAcceptController.put()', '\"{\\\"paramOne\\\":210178,\\\"paramTwo\\\":\\\"GG84-dd45-gk98-ttlc\\\"}\"', 98, '127.0.0.1', '2020-11-18 11:10:08', '{\"code\":200,\"msg\":\"success\"}');
INSERT INTO `sws_sys_log` VALUES (11, 'yuezejian', 'sws_sys_log', '新增数据', 'com.slzhkj.smartworksite.server.controller.DataAcceptController.put()', '\"{\\\"paramOne\\\":210178,\\\"paramTwo\\\":\\\"GG84-dd45-gk98-ttlc1\\\"}\"', 34, '127.0.0.1', '2020-11-18 11:10:20', '{\"code\":200,\"msg\":\"success\"}');
INSERT INTO `sws_sys_log` VALUES (12, 'yuezejian', 'sws_sys_log', '新增数据', 'com.slzhkj.smartworksite.server.controller.DataAcceptController.put()', '\"{\\\"paramOne\\\":210178,\\\"paramTwo\\\":\\\"GG84-dd45-gk98-ttlc\\\"}\"', 271, '127.0.0.1', '2020-11-19 14:36:04', '{\"code\":200,\"msg\":\"success\"}');

-- ----------------------------
-- Table structure for sws_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sws_sys_user`;
CREATE TABLE `sws_sys_user`  (
  `id` tinyint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_del` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sws_sys_user
-- ----------------------------
INSERT INTO `sws_sys_user` VALUES (1, 'yuezejian', '123456', NULL, NULL, 1);

-- ----------------------------
-- View structure for v_column_info
-- ----------------------------
DROP VIEW IF EXISTS `v_column_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_column_info` AS select `information_schema`.`columns`.`TABLE_SCHEMA` AS `data_name`,`information_schema`.`columns`.`TABLE_NAME` AS `table_name`,`information_schema`.`columns`.`COLUMN_NAME` AS `column_name`,`information_schema`.`columns`.`COLUMN_TYPE` AS `column_type`,`information_schema`.`columns`.`COLUMN_COMMENT` AS `column_comment` from `information_schema`.`columns` where ((`information_schema`.`columns`.`TABLE_SCHEMA` <> 'information_schema') and (`information_schema`.`columns`.`TABLE_SCHEMA` <> 'performance_schema') and (`information_schema`.`columns`.`TABLE_SCHEMA` <> 'mysql') and (`information_schema`.`columns`.`TABLE_SCHEMA` <> 'dataj4mysql'));

-- ----------------------------
-- View structure for v_data_info
-- ----------------------------
DROP VIEW IF EXISTS `v_data_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_data_info` AS select `information_schema`.`schemata`.`SCHEMA_NAME` AS `data_name` from `information_schema`.`schemata` where ((`information_schema`.`schemata`.`SCHEMA_NAME` <> 'information_schema') and (`information_schema`.`schemata`.`SCHEMA_NAME` <> 'mysql') and (`information_schema`.`schemata`.`SCHEMA_NAME` <> 'performance_schema') and (`information_schema`.`schemata`.`SCHEMA_NAME` <> 'performance_schema'));

-- ----------------------------
-- View structure for v_table_info
-- ----------------------------
DROP VIEW IF EXISTS `v_table_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_table_info` AS select `information_schema`.`tables`.`TABLE_NAME` AS `table_name`,`information_schema`.`tables`.`TABLE_SCHEMA` AS `data_name` from `information_schema`.`tables` where ((`information_schema`.`tables`.`TABLE_SCHEMA` <> 'information_schema') and (`information_schema`.`tables`.`TABLE_SCHEMA` <> 'performance_schema') and (`information_schema`.`tables`.`TABLE_SCHEMA` <> 'mysql'));

SET FOREIGN_KEY_CHECKS = 1;
