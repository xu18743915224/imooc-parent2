/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.26 : Database - imooc_parent2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`imooc_parent2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `imooc_parent2`;

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type` varchar(128) DEFAULT NULL COMMENT '字典类型',
  `dict_code` int(11) DEFAULT NULL COMMENT '字典编码code',
  `dict_name` varchar(128) DEFAULT NULL COMMENT '字典名name',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `no` int(16) DEFAULT NULL COMMENT '排序',
  `is_valid` varchar(16) DEFAULT NULL COMMENT '是否有效0无效 1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`id`,`dict_type`,`dict_code`,`dict_name`,`remark`,`create_time`,`update_time`,`no`,`is_valid`) values (1,'IS_VALID',0,'无效','是否有效','2020-06-17 03:35:23','2020-06-17 03:35:23',1,'1'),(2,'IS_VALID',1,'有效','是否有效','2020-06-17 03:35:23','2020-06-17 03:35:23',2,'1'),(4,'STATUS',0,'锁定','锁定','2020-06-17 21:01:16','2020-06-17 21:01:16',1,'1'),(5,'STATUS',1,'解锁','解锁','2020-06-17 21:19:38','2020-06-17 21:19:38',2,'1'),(8,'TYPE',1,'菜单','菜单','2020-06-17 23:53:13','2020-06-17 23:53:13',1,'1'),(9,'TYPE',2,'按钮','按钮','2020-06-17 23:53:29','2020-06-17 23:53:29',2,'1'),(10,'TYPE',3,'操作','操作','2020-06-17 23:53:44','2020-06-17 23:53:44',3,'1');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type` varchar(128) DEFAULT NULL COMMENT '类型',
  `type_name` varchar(128) DEFAULT NULL COMMENT '类型name',
  `is_valid` varchar(128) DEFAULT NULL COMMENT '是否有效0无效 1有效',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`id`,`dict_type`,`type_name`,`is_valid`,`create_time`,`update_time`) values (2,'IS_VALID','是否有效','1','2020-06-17 03:35:23','2020-06-17 03:35:44'),(3,'STATUS','状态','1','2020-06-17 04:59:07','2020-06-17 04:59:07'),(5,'TYPE','资源类型','1','2020-06-17 23:52:37','2020-06-17 23:52:37');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '菜单父ID',
  `icon_cls` varchar(256) DEFAULT NULL COMMENT '菜单图标',
  `text` varchar(256) NOT NULL COMMENT '菜单名',
  `url` varchar(512) DEFAULT NULL COMMENT '菜单url',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0：无效，1：有效）',
  `no` int(11) DEFAULT NULL COMMENT '顺序',
  `create_user` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`icon_cls`,`text`,`url`,`status`,`no`,`create_user`,`create_time`,`update_user`,`update_time`) values (1,0,'fa fa-send-o','用户模块','yhmk',1,1,'system','2020-06-03 11:52:02','admin','2020-06-29 10:27:08'),(2,0,'fa fa-desktop','系统模块','xtmk',1,2,'sys','2020-06-03 12:00:17','admin','2020-06-29 10:27:42'),(15,1,'11','用户管理','111',1,11,'admin','2020-06-29 11:53:33','admin','2020-06-29 11:53:33');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT '0' COMMENT '父ID',
  `type` tinyint(4) NOT NULL COMMENT '资源类型（1：菜单，2：按钮，3：操作）',
  `name` varchar(500) NOT NULL COMMENT '资源名称',
  `code` varchar(500) CHARACTER SET latin1 NOT NULL COMMENT '资源标识（或者叫权限字符串）',
  `uri` varchar(64) CHARACTER SET latin1 DEFAULT NULL COMMENT '资源URI',
  `seq` int(11) DEFAULT '1' COMMENT '序号',
  `create_user` varchar(64) CHARACTER SET latin1 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(64) CHARACTER SET latin1 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `idx_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`pid`,`type`,`name`,`code`,`uri`,`seq`,`create_user`,`create_time`,`update_user`,`update_time`) values (4,0,2,'权限管理_删除','permission_delete','/permission/delete',3,'admin','2020-06-23 10:27:31','admin','2020-06-27 09:26:21'),(10,0,2,'权限管理_新增/编辑','permission_saveOrUpdate','/permission/saveOrUpdate',2,'admin','2020-06-27 09:27:41','admin','2020-06-27 09:47:09'),(11,0,3,'权限管理_列表','permission_getListPage','/permission/getListPage',1,'admin','2020-06-27 09:31:04','admin','2020-06-27 09:49:47'),(12,0,2,'权限管理_授权角色_保存','permission_permissionToRole','/permission/permissionToRole',4,'admin','2020-06-27 09:45:49','admin','2020-06-27 09:45:49'),(13,0,3,'角色管理_列表','role_getListPage','/role/getListPage',5,'admin','2020-06-27 09:50:35','admin','2020-06-27 09:50:35'),(14,0,2,'角色管理_新增/编辑','role_saveOrUpdate','/role/saveOrUpdate',6,'admin','2020-06-27 09:53:07','admin','2020-06-27 09:53:24'),(15,0,2,'角色管理_删除','role_delete','/role/delete',7,'admin','2020-06-27 09:54:52','admin','2020-06-27 09:55:33'),(16,0,2,'角色管理_授权用户_保存','role_roleToUser','/role/roleToUser',8,'admin','2020-06-27 09:57:29','admin','2020-06-27 09:57:29'),(17,0,3,'用户管理_列表','user_getListPage','/user/getListPage',9,'admin','2020-06-27 09:59:55','admin','2020-06-27 09:59:55'),(18,0,2,'用户管理_新增/编辑','user_saveOrUpdate','/user/saveOrUpdate',10,'admin','2020-06-27 10:00:32','admin','2020-06-27 10:01:39'),(19,0,2,'用户管理_删除','user_delete','/user/delete',11,'admin','2020-06-27 10:02:07','admin','2020-06-27 10:02:07');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_code` varchar(32) NOT NULL,
  `role_description` varchar(64) DEFAULT NULL COMMENT '角色描述',
  `create_user` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_code`,`role_description`,`create_user`,`create_time`,`update_user`,`update_time`) values (2,'普通用户','PTYH','普通用户','system','2019-02-12 11:15:37','admin','2020-06-24 12:19:15'),(4,'系统管理员','SYSTEM','系统管理员','admin','2020-06-05 04:52:07','admin','2020-06-24 12:19:26');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`role_id`,`permission_id`) values (26,4,4),(27,4,10),(30,4,12),(32,4,15),(33,4,14),(34,4,19),(35,4,18),(36,4,17),(37,4,16),(38,4,11),(39,2,11),(40,4,13),(41,2,13);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：锁定，1：解锁）',
  `create_user` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`email`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (1,'admin','admin','管理员','abc@123.com',1,'system','2019-02-12 11:12:19',NULL,NULL),(9,'admin1','admin1','管理员1','111',1,'admin','2020-06-27 08:21:02','admin','2020-06-27 10:09:23'),(10,'admin2','admin2','管理员2','2',0,'admin','2020-06-27 10:10:01','admin','2020-06-27 10:10:01');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (45,1,4),(48,9,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
