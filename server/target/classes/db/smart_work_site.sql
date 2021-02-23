/*
Navicat MySQL Data Transfer

Source Server         : yuezj
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : smart_work_site

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-11-20 17:24:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for metadata
-- ----------------------------
DROP TABLE IF EXISTS `metadata`;
CREATE TABLE `metadata` (
  `table` varchar(255) NOT NULL DEFAULT '',
  `sync_key` varchar(255) NOT NULL DEFAULT '',
  `sync_value` bigint(11) NOT NULL DEFAULT '0',
  `synd_datetime` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='增量数据标记表';

-- ----------------------------
-- Records of metadata
-- ----------------------------

-- ----------------------------
-- Table structure for sws_area
-- ----------------------------
DROP TABLE IF EXISTS `sws_area`;
CREATE TABLE `sws_area` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `IsSpecial` text,
  `Lng` text,
  `ParentSortCode` text,
  `SortCode` text,
  `AreaType` text,
  `Code` text,
  `Name` text,
  `ZoneName` text,
  `HasChildren` text,
  `InDate` text,
  `InUserName` text,
  `Pinyin` text,
  `RealAreaType` text,
  `InDateStr` text,
  `AreaTypeStr` text,
  `Lat` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_area
-- ----------------------------
INSERT INTO `sws_area` VALUES ('1', '1', '1.9', '', '', '0', '', '', '', '1', '2020-10-29T10:23:36.7101627+08:00', '', '', '0', '2020-10-29 10:23:36', '省级', '1.9');

-- ----------------------------
-- Table structure for sws_common_config
-- ----------------------------
DROP TABLE IF EXISTS `sws_common_config`;
CREATE TABLE `sws_common_config` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `KeyName` text,
  `Status` text,
  `MgtFlag` text,
  `StatusStr` text,
  `InUserSysNo` text,
  `InDate` text,
  `SysNo` text,
  `InUserName` text,
  `Value` text,
  `EditDate` text,
  `InDateStr` text,
  `Key` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_common_config
-- ----------------------------
INSERT INTO `sws_common_config` VALUES ('1', '', '0', '0', '无效', '1', '2020-10-29T11:51:27.1466425+08:00', '1', '', '', '2020-10-29T11:51:27.1466425+08:00', '2020-10-29 11:51:27', '');

-- ----------------------------
-- Table structure for sws_common_dict
-- ----------------------------
DROP TABLE IF EXISTS `sws_common_dict`;
CREATE TABLE `sws_common_dict` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Status` text,
  `SysNo` text,
  `Priority` text,
  `EditDate` text,
  `EditUserSysNo` text,
  `EditUserName` text,
  `IsMyData` text,
  `DictTypeKey` text,
  `DictCode` text,
  `InUserSysNo` text,
  `InDate` text,
  `DictName` text,
  `EditDateStr` text,
  `InUserName` text,
  `DictTypeName` text,
  `Pinyin` text,
  `InDateStr` text,
  `CompanyCode` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_common_dict
-- ----------------------------
INSERT INTO `sws_common_dict` VALUES ('1', '0', '1', '1', '2020-10-29T13:55:36.371', '1', '', 'false', '', '', '1', '2020-10-29T13:55:36.371003+08:00', '', '2020-10-29 13:55:36', '', '', '', '2020-10-29 13:55:36', '');

-- ----------------------------
-- Table structure for sws_common_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sws_common_dict_type`;
CREATE TABLE `sws_common_dict_type` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Status` text,
  `EditDate` text,
  `EditUserSysNo` text,
  `EditUserName` text,
  `IsMyData` text,
  `DictTypeKey` text,
  `InUserSysNo` text,
  `InDate` text,
  `EditDateStr` text,
  `InUserName` text,
  `DictTypeName` text,
  `InDateStr` text,
  `CompanyCode` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_common_dict_type
-- ----------------------------
INSERT INTO `sws_common_dict_type` VALUES ('1', '0', '2020-10-29T11:52:03.703', '1', '', 'false', '', '1', '2020-10-29T11:52:03.7035153+08:00', '2020-10-29 11:52:03', '', '', '2020-10-29 11:52:03', '');

-- ----------------------------
-- Table structure for sws_cron
-- ----------------------------
DROP TABLE IF EXISTS `sws_cron`;
CREATE TABLE `sws_cron` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cron_id` varchar(36) NOT NULL DEFAULT '' COMMENT '唯一标识',
  `cron_name` varchar(64) DEFAULT NULL COMMENT '定时器名称',
  `cron_class` varchar(128) DEFAULT NULL COMMENT '类名（全）',
  `cron_method` varchar(32) DEFAULT NULL,
  `cron` varchar(32) DEFAULT NULL COMMENT '定时器表达式',
  `cron_class_params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `cron_status` tinyint(4) DEFAULT NULL COMMENT '定时器启动与否的状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '定时器备注描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`,`cron_id`),
  UNIQUE KEY `select_key` (`cron_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_cron
-- ----------------------------
INSERT INTO `sws_cron` VALUES ('1', '97ecffc6-a714-47fe-a33e-ee67ca5cdf72', '实名制接口任务调度-taskOne', 'DataRequestScheduled', 'scheduled', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '0', '实名制三方接口调用定时任务', '2020-11-19 11:07:25', '2020-11-19 11:07:25', '1');
INSERT INTO `sws_cron` VALUES ('2', '1ef96601-2530-47fc-85f4-9fc94fe94fe8', '实名制接口任务调度-taskOne1', 'DataRequestScheduled1', 'scheduled3', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', '实名制三方接口调用定时任务', '2020-11-20 10:11:13', '2020-11-20 10:11:13', '1');
INSERT INTO `sws_cron` VALUES ('3', 'db2b00a2-24a6-4d55-a153-05ca10e0686a', '实名制接口任务调度-taskOne2', 'DataRequestScheduled2', 'scheduled3', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', '实名制三方接口调用定时任务', '2020-11-20 10:11:21', '2020-11-20 10:11:21', '1');
INSERT INTO `sws_cron` VALUES ('4', 'de2ff8f3-cfd9-45a1-9853-17cb86e16db8', '测试定时器1', 'classForOne', 'MethodOne', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', '这是测试定时器不可使用直接', '2020-11-20 13:48:30', '2020-11-20 13:48:30', '1');
INSERT INTO `sws_cron` VALUES ('5', 'a4ba7e80-444e-42bb-be20-670d5430753a', '测试定时器1', 'classForOne1', 'MethodOne1', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', '这是测试定时器不可使用直接', '2020-11-20 13:59:50', '2020-11-20 13:59:50', '1');
INSERT INTO `sws_cron` VALUES ('6', '111dd7da-aeae-41e8-94e4-43c9a8674f90', '测试定时器3', 'classForOne3', 'MethodOne3', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', 'null', '2020-11-20 14:09:59', '2020-11-20 14:09:59', '1');
INSERT INTO `sws_cron` VALUES ('7', '167686ba-1dcd-4707-93a0-be71022168a4', '测试定时器3', 'classForOne4', 'MethodOne4', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', 'null', '2020-11-20 14:11:33', '2020-11-20 14:11:33', '1');
INSERT INTO `sws_cron` VALUES ('8', '82fd6717-63b7-4555-a2d7-dfe86bd8bb05', '测试定时器1', 'classForOne5', 'MethodOne5', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', 'null', '2020-11-20 14:17:17', '2020-11-20 14:17:17', '1');
INSERT INTO `sws_cron` VALUES ('9', 'db8abba6-71d4-44a1-aee3-2c6eafe3505a', '测试定时器1', 'classForOne6', 'MethodOne6', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', 'null', '2020-11-20 14:17:55', '2020-11-20 14:17:55', '1');
INSERT INTO `sws_cron` VALUES ('10', '31560df1-ad97-4d5e-8c70-c2544b44cacd', '测试定时器1', 'classForOne7', 'MethodOne7', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', 'null', '2020-11-20 14:19:44', '2020-11-20 14:19:44', '1');
INSERT INTO `sws_cron` VALUES ('11', 'bf8f6721-314e-4210-ae5b-2cc6ab86f26a', '', '', '', '', '', '1', '', '2020-11-20 14:20:23', '2020-11-20 14:20:23', '1');
INSERT INTO `sws_cron` VALUES ('12', '08e3a750-6fd1-4bbf-b1f0-fb37342d262a', '测试定时器1', 'classForOne8', 'MethodOne8', '*/5 * * * * *', '\'PageIndex\': 0,\'PageSize\': 20,\'SortFields\': null,\'ProjectCode\': \'\',\'TimeStamp\': \'\',\'SK\': \'\'', '1', 'null', '2020-11-20 14:58:50', '2020-11-20 14:58:50', '1');

-- ----------------------------
-- Table structure for sws_insert_model
-- ----------------------------
DROP TABLE IF EXISTS `sws_insert_model`;
CREATE TABLE `sws_insert_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(16) DEFAULT NULL COMMENT '新增操作的表名',
  `insert_sql` varchar(500) DEFAULT NULL COMMENT '新增操作的可执行字符串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '有效标志位 1.可用 0.不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_insert_model
-- ----------------------------
INSERT INTO `sws_insert_model` VALUES ('1', null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for sws_project_info
-- ----------------------------
DROP TABLE IF EXISTS `sws_project_info`;
CREATE TABLE `sws_project_info` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_type` text,
  `build_organization` text,
  `structure_type` text,
  `design_organization` text,
  `started_time` text,
  `monitor_user_and_phone` text,
  `end_time` text,
  `project_area` text,
  `project_name` text,
  `current_part` text,
  `build_area` text,
  `need_money` text,
  `build_user_and_phone` text,
  `date_of_issue` text,
  `development_user_and_phone` text,
  `development_organization` text,
  `survey_organization` text,
  `design_user_and_phone` text,
  `survey_user_and_phone` text,
  `monitor_organization` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_project_info
-- ----------------------------
INSERT INTO `sws_project_info` VALUES ('1', '住宅', '中国建筑第八工程局有限公司', '剪力墙', '天津市润实建筑设计有限公司', '2019/8/10', '王文敏13820292097', '2021/5/20', '华一路与中新大道交口东南侧', '天津生态城中部片区04-01-03-01地块（27#）住宅项目', ' 基础、主体', '194950', '72540.4875', '曲勇13909884885郭亮亮13752125432', '2019/8/16', '王学孟13820051430', '天津生态城建设投资有限公司', '核工业天津工程勘察院', '倪斌13920507810', '肖欣13132555992', '天津港保税区中天建设咨询管理有限公司');
INSERT INTO `sws_project_info` VALUES ('2', '住宅', '中国建筑第二工程局有限公司', '剪力墙', '天津市天友建筑设计股份有限公司', '2020/9/1', '董淑萍13945252630', '2021/8/15', '中新天津生态城华三路以东，中新大道以北', '生态城36号地块住宅项目（观澜壹号）一期工程', ' 基础、主体', '141070.04', '64482', '曾利明18632027725', '2020/6/28', '王刚13941218963', '天津融创沅宸置业有限公司', '天津市地质工程勘察院', '周权13752521468', '霍泽辉13752413705', '天津广源恒信建设工程项目管理有限公司');
INSERT INTO `sws_project_info` VALUES ('3', '住宅', '上海建工五建集团有限公司', '框架剪力墙', '天津市天友建筑设计股份有限公司', '2019/12/3', '邵玉国15522161288', '2021/5/30', '中新生态城北部区域', '天富64号地03-04-04地块住宅项目（地库及附属用房、洋房12楼-21楼、24楼、25楼、配建二）', '主体', '70417.87', '16629.81518', '王建13478815522 岑禹翰13840201907', '2019/12/4', '绳肖松18222155832', '天津市天富房地产开发有限公司', '天津市地质工程勘察院', '周权13752521468', '张松13207625369', '天津正方建设工程监理有限公司');
INSERT INTO `sws_project_info` VALUES ('4', '公建', '中国核工业兴华建设有限公司', '框架/剪力墙', '中国核工业兴华建设有限公司', '2019/9/20', '蔡江洪15668989298', '2021/9/20', '东至玉砂道，南至渔泽路，西至云溪道，北至渔帆路', '核工业大学（天津）建设项目（一期）', '地基', '74053.87', '48498', '谢海涛15122345650冯雪15822032168', '', '宫育锋13466769777', '中核智慧城（天津）教育管理有限公司', '中煤邯郸设计工程有限责任公司', '雷二强13502076188', '房德刚15931121123', '中核工程咨询有限公司');
INSERT INTO `sws_project_info` VALUES ('5', '住宅', '天元建设集团有限公司', '框架剪力墙', '天津市天友建筑设计股份有限公司', '', '陆明18526546496', '', '中心天津生态城北部旅游区云溪道与吉顺道交口', '生态城旅游区域02-17地块住宅项目', '地基', '148280', '1811.8861', '李沛17334088367', '', '田盼18664338310', '天津恒新房地产开发有限公司', '河北建研建筑设计有限公司', '熊碧峰13920564410', '孙晓阳15131191280', '广州市恒合工程监理有限公司');

-- ----------------------------
-- Table structure for sws_request_log
-- ----------------------------
DROP TABLE IF EXISTS `sws_request_log`;
CREATE TABLE `sws_request_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `app_Id` varchar(32) DEFAULT NULL,
  `secret` varchar(64) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `token` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_request_log
-- ----------------------------
INSERT INTO `sws_request_log` VALUES ('17', '1001', 'TT84-dd45-gk98-ffgg', '970c8475-048d-4378-8321-720ed956d617', '3f872d1d40f3ed9336264d01a4d9bc66', '2020-11-11 10:19:56', '2020-11-11 10:20:52', '0');
INSERT INTO `sws_request_log` VALUES ('18', '1001', 'TT84-dd45-gk98-ffg1', 'd85cf240-e380-400d-be2f-f695b791aa3b', '812c97e83796cceff677711b9fa78b25', '2020-11-11 11:51:07', '2020-11-11 11:51:07', '0');
INSERT INTO `sws_request_log` VALUES ('19', '1002', 'TT84-dd45-gk98-ffg2', 'cf45e975-770f-4140-bfcf-d9b43fda87e2', '96915493d8fccfbb2526de6040290633', '2020-11-13 10:51:20', '2020-11-13 10:51:20', '0');
INSERT INTO `sws_request_log` VALUES ('20', '1002', 'TT84-dd45-gk98-ffg4', '0e5e9b49-54f5-4091-971c-ef2fbb207328', '59f5b8af78db9538644644a10b0bd524', '2020-11-13 14:20:12', '2020-11-13 14:20:12', '0');
INSERT INTO `sws_request_log` VALUES ('21', '1001', 'TT84-dd45-gk98-ffg3', 'aeaafb19-1416-4b6f-b460-fb574e32ecf7', 'ad7c325ba2638273c897afaf4933f83d', '2020-11-13 14:22:15', '2020-11-13 14:22:15', '0');
INSERT INTO `sws_request_log` VALUES ('22', '1001', 'TT84-dd45-gk98-ffg3', 'eea34c06-e953-4fdf-bb4b-16038a26ecb7', 'fb39a9ab811ddf2a70af4cfe5c915974', '2020-11-13 14:42:41', '2020-11-13 14:42:41', '1');
INSERT INTO `sws_request_log` VALUES ('23', '1002', 'TT84-dd45-gk98-ffg4', '40b5c84f-92db-4ea6-9bb3-596b94b2c8d5', '9346ed5a6c33fae4cac63aaec1c5d6ba', '2020-11-13 14:42:52', '2020-11-13 14:42:52', '1');
INSERT INTO `sws_request_log` VALUES ('24', '210178', 'GG84-dd45-gk98-ttlc', null, null, null, null, null);
INSERT INTO `sws_request_log` VALUES ('25', '210178', 'GG84-dd45-gk98-ttlc1', null, null, null, null, null);
INSERT INTO `sws_request_log` VALUES ('26', '210178', 'GG84-dd45-gk98-ttlc', null, null, null, null, null);

-- ----------------------------
-- Table structure for sws_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sws_sys_log`;
CREATE TABLE `sws_sys_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `operator_table` varchar(32) DEFAULT NULL,
  `operation` varchar(16) DEFAULT NULL,
  `method` varchar(128) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `memo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_sys_log
-- ----------------------------
INSERT INTO `sws_sys_log` VALUES ('10', 'yuezejian', 'sws_sys_log', '新增数据', 'com.slzhkj.smartworksite.server.controller.DataAcceptController.put()', '\"{\\\"paramOne\\\":210178,\\\"paramTwo\\\":\\\"GG84-dd45-gk98-ttlc\\\"}\"', '98', '127.0.0.1', '2020-11-18 11:10:08', '{\"code\":200,\"msg\":\"success\"}');
INSERT INTO `sws_sys_log` VALUES ('11', 'yuezejian', 'sws_sys_log', '新增数据', 'com.slzhkj.smartworksite.server.controller.DataAcceptController.put()', '\"{\\\"paramOne\\\":210178,\\\"paramTwo\\\":\\\"GG84-dd45-gk98-ttlc1\\\"}\"', '34', '127.0.0.1', '2020-11-18 11:10:20', '{\"code\":200,\"msg\":\"success\"}');
INSERT INTO `sws_sys_log` VALUES ('12', 'yuezejian', 'sws_sys_log', '新增数据', 'com.slzhkj.smartworksite.server.controller.DataAcceptController.put()', '\"{\\\"paramOne\\\":210178,\\\"paramTwo\\\":\\\"GG84-dd45-gk98-ttlc\\\"}\"', '271', '127.0.0.1', '2020-11-19 14:36:04', '{\"code\":200,\"msg\":\"success\"}');

-- ----------------------------
-- Table structure for sws_user_out
-- ----------------------------
DROP TABLE IF EXISTS `sws_user_out`;
CREATE TABLE `sws_user_out` (
  `app_id` varchar(32) DEFAULT NULL,
  `secret` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(4) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_user_out
-- ----------------------------
INSERT INTO `sws_user_out` VALUES ('1001', 'TT84-dd45-gk98-ffg3', '2020-11-11 10:27:28', '2020-11-11 10:27:33', '1');
INSERT INTO `sws_user_out` VALUES ('1002', 'TT84-dd45-gk98-ffg4', '2020-11-11 10:27:59', '2020-11-11 10:28:01', '1');

-- ----------------------------
-- Table structure for sws_workers_info
-- ----------------------------
DROP TABLE IF EXISTS `sws_workers_info`;
CREATE TABLE `sws_workers_info` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `birthday` text,
  `address` text,
  `head_image_path` text,
  `culture_level_type` text,
  `gender` text,
  `nation` text,
  `worker_name` text,
  `project_sys_no` text,
  `birth_place_name` text,
  `project_name` text,
  `politics_type` text,
  `birthday_desc` text,
  `cellphone` text,
  `birth_place_code` text,
  `id` text,
  `id_card_number` text,
  `id_card_type` text,
  `urgent_contract_name` text,
  `worker_sys_no` text,
  `age` text,
  `urgent_contract_cellphone` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_workers_info
-- ----------------------------
INSERT INTO `sws_workers_info` VALUES ('1', '1992-12-27T00:00:00+08:00', '湖南省湘乡市金石镇坪如村长塘村民组276号', 'worker-avatar/Original/2020/0805/43ece0d3-5bfe-4815-af96-f076936411e8.jpg', '0', '0', '汉', '赵肖瑶', '12754', '湖南省湘潭市湘乡市', '', '3', '1992-12-27', '', '430381', '2362', '430381199212277411', '0', '', '459666', '28', '');

-- ----------------------------
-- Table structure for sws_work_type_dict
-- ----------------------------
DROP TABLE IF EXISTS `sws_work_type_dict`;
CREATE TABLE `sws_work_type_dict` (
  `xxx3id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Status` text,
  `WorkTypeCode` text,
  `IsSpecial` text,
  `SysNo` text,
  `EditDate` text,
  `WorkTypeName` text,
  `EditUserSysNo` text,
  `EditUserName` text,
  `IsMyData` text,
  `InUserSysNo` varchar(36) NOT NULL DEFAULT '',
  `InDate` text,
  `EditDateStr` varchar(36) NOT NULL DEFAULT '',
  `InUserName` varchar(36) NOT NULL DEFAULT '',
  `Pinyin` text,
  `Note` text,
  `InDateStr` text,
  `CompanyCode` text,
  PRIMARY KEY (`xxx3id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sws_work_type_dict
-- ----------------------------
INSERT INTO `sws_work_type_dict` VALUES ('1', '0', '', '1', '1', '2020-10-29T14:48:10.842', '', '1', '', 'false', '1', '2020-10-29T14:48:10.8426395+08:00', '2020-10-29 14:48:10', '', '', '', '2020-10-29 14:48:10', '');

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `work_name` varchar(64) DEFAULT NULL,
  `work_site` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES ('1', '李白的工作', '李白的工作地点', '2020-11-01 14:31:19');
INSERT INTO `work` VALUES ('2', '安琪拉的工作', '安琪拉的工作地点', '2020-11-05 14:31:24');
