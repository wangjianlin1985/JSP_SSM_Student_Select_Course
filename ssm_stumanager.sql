/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : ssm_stumanager

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-08 21:30:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `nickname` varchar(20) NOT NULL COMMENT '管理员昵称',
  PRIMARY KEY (`id`,`nickname`) 
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', '管理员');

-- ----------------------------
-- Table structure for `t_auth`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `url` varchar(255) NOT NULL COMMENT '系统后台接口',
  `admin_auth` tinyint(4) NOT NULL DEFAULT 1 COMMENT '管理员是否有权限查看',
  `teacher_auth` tinyint(4) NOT NULL DEFAULT 0 COMMENT '教师是否有权限查看',
  `student_auth` tinyint(4) NOT NULL DEFAULT 0 COMMENT '学生是否有权限查看',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES ('10', '主界面', '/ssm_stumanager/main/', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('11', '学生信息管理', '/ssm_stumanager/student/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('12', '教师信息管理', '/ssm_stumanager/teacher/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('13', '课程信息管理', '/ssm_stumanager/course/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('14', '系统权限', '/ssm_stumanager/auth/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('15', '公告管理', '/ssm_stumanager/notice/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('16', '查看公告', '/ssm_stumanager/notice/look', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('17', '公告列表', '/ssm_stumanager/notice/list', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('18', '教师课程', '/ssm_stumanager/course/getMyCourse', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('19', '可选课程列表', '/ssm_stumanager/course/choiceList', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('20', '结束课程', '/ssm_stumanager/course/complete', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('21', '学生列表', '/ssm_stumanager/student/stulist', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('22', '录入成绩', '/ssm_stumanager/score/update', '1', '1', '0');
INSERT INTO `t_auth` VALUES ('23', '学生选课', '/ssm_stumanager/score/choiceCourse', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('24', '取消选课', '/ssm_stumanager/score/delete', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('26', '查看成绩', '/ssm_stumanager/score/stuScore', '1', '0', '1');
INSERT INTO `t_auth` VALUES ('27', '密码管理', '/ssm_stumanager/pswd/', '1', '1', '1');
INSERT INTO `t_auth` VALUES ('28', '基本课程管理', '/ssm_stumanager/basecourse/', '1', '0', '0');
INSERT INTO `t_auth` VALUES ('29', '成绩报表', '/ssm_stumanager/score/', '1', '1', '0');

-- ----------------------------
-- Table structure for `t_base_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_base_course`;
CREATE TABLE `t_base_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '基本课程id',
  `name` varchar(100) NOT NULL COMMENT '课程名',
  `synopsis` varchar(255) DEFAULT NULL COMMENT '课程简介',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_base_course
-- ----------------------------
INSERT INTO `t_base_course` VALUES ('1', 'C语言', '本课程介绍计算机结构化程序设计的思想、方法和技巧；C语言的基本知识和概念；Ｃ语言丰富的运算符和数据类型，以及C语言的结构控制语句；在本课程中，函数的概念和指针的使用是课程重点和难点。');
INSERT INTO `t_base_course` VALUES ('2', '数据结构与算法', '数据结构是计算机科学的一门非常重要的专业基础课，内容丰富，涉及面广，我校计算机专业的本科主干基础课程，也是非计算机类本科生和研究生学习计算机的选修课。');
INSERT INTO `t_base_course` VALUES ('3', '操作系统', '操作系统是计算机科学与技术领域中最为活跃的学科之一,因而操作系统课程也自然是该专业的一门核心的专业基础课。操作系统课程内容综合了基础理论教学、课程实践教学、最新技术追踪等多项内容。');
INSERT INTO `t_base_course` VALUES ('4', '计算机网络', '计算机网络是计算机专业学生必修的一门专业基础课和核心课程，它是后续课程《计算机系统安全》、《网络管理技术》、《TCP/IP与网络互联》等理论课程，以及《网络课程设计》等实践教学环节的先行课。');
INSERT INTO `t_base_course` VALUES ('5', '软件工程', '该课程主要介绍软件工程的基本概念、原理和典型的技术方法。该课程的主要目的是通过教学，使学生了解工程学原理在软件开发中的应用，对计算机科学专业和信息工程专业学生理解软件开发过程和软件工程学具有重要意义。');
INSERT INTO `t_base_course` VALUES ('6', 'Linux入门', 'Linux是适用于多种平台的计算机操作系统，也是免费的非商品化的操作系统。本课程以Redhat Linux系统为基础，试图对Linux系统一个简洁而全面的介绍，使学生在较短时间内对该操作系统有个大概的了解。');
INSERT INTO `t_base_course` VALUES ('7', 'mysql', 'mysql是一门关于数据库的建立与链接的软件');
INSERT INTO `t_base_course` VALUES ('8', 'Java', 'Java是一门基础的软件编程与开发项目所用到的一门学科');
INSERT INTO `t_base_course` VALUES ('9', '大学英语', '是一门学习与加强外语学习的学科');
INSERT INTO `t_base_course` VALUES ('10', '教育学', '是一门由于……………………………………………………………………');
INSERT INTO `t_base_course` VALUES ('11', '近代史', '关于近代所发生的一些时间的记录与里哦阿姐');
INSERT INTO `t_base_course` VALUES ('12', '马克思主义四关', '是一门关于马克思学习的学科');
INSERT INTO `t_base_course` VALUES ('13', '毛泽东思想概论', '是一门关于毛泽东时期的主要思想以及提出来的一些理论学习');
INSERT INTO `t_base_course` VALUES ('14', '物理学', '是一门在高中物理上一个拓展');
INSERT INTO `t_base_course` VALUES ('15', '心理学', '是一门对学生的心理研究以及他们的状态表现');
INSERT INTO `t_base_course` VALUES ('16', '操作系统', '介绍了计算机的四个主要部件，还接晒了他的工作原理');
INSERT INTO `t_base_course` VALUES ('17', '计算机组成原理', '介绍了计算机的组成以及他工作的原理是甚么');
INSERT INTO `t_base_course` VALUES ('18', '大学语文', '在高中语文的基础上回顾一下跟重要的是拓展');
INSERT INTO `t_base_course` VALUES ('20', 'c++', '他也是一门基础的计算机编程语言');
INSERT INTO `t_base_course` VALUES ('134', '手撕Spring源码', '带你手把手仿照Spring源码编写的SpringMVC，还原MyDispatcherServlet处理流程：注解扫描，初始化ioc容器（定位、载入、注册、实例化、依赖注入），handlerMapping，视图解析 ...');
INSERT INTO `t_base_course` VALUES ('135', '分布式系统设计', '主要是以下两个方面\n1.  近些年分布式系统领域都在做些什么。\n2.  为什么现在投入分布式系统的学习和研究是值得的。\n我会尽可能多地去介绍更 “实用” 的分布式系统知识。');
INSERT INTO `t_base_course` VALUES ('136', 'RESTful API设计规范', 'RESTful 是目前最流行的 API 设计规范，用于 Web 数据接口的设计。\n它的大原则容易把握，但是细节不容易做对。本文总结 RESTful 的设计细节，介绍如何设计出易于理解和使用的 API。');
INSERT INTO `t_base_course` VALUES ('137', '高等数学', '可以更宽的扩展我们的视野，更好的让我们了解到知识');

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选课课程id',
  `start_date` date DEFAULT NULL COMMENT '开设日期',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `class_hour` smallint(6) DEFAULT NULL COMMENT '总课时',
  `test_mode` varchar(255) DEFAULT NULL COMMENT '考核方式',
  `student_num` int(11) DEFAULT NULL COMMENT '最大可选人数',
  `choice_num` int(11) DEFAULT 0 COMMENT '已选人数',
  `complete` int(11) NOT NULL DEFAULT 0 COMMENT '是否是完成的课程',
  `t_id` varchar(255) NOT NULL COMMENT '外键-教师号',
  `base_course_id` int(11) NOT NULL COMMENT '外键-基本课程号',
  PRIMARY KEY (`id`) ,
  KEY `teacher_course` (`t_id`) ,
  KEY `course` (`base_course_id`) ,
  CONSTRAINT `course` FOREIGN KEY (`base_course_id`) REFERENCES `t_base_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_course` FOREIGN KEY (`t_id`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('7', '2019-09-01', '2019-08-31', '54', '考试', '40', '3', '0', '1560310', '1');
INSERT INTO `t_course` VALUES ('13', '2019-09-15', '2020-01-06', '120', '教考分离', '80', '2', '0', '1560310', '5');
INSERT INTO `t_course` VALUES ('15', '2019-06-19', '2020-06-18', '200', '教考分离', '188', '1', '1', '19980831', '134');
INSERT INTO `t_course` VALUES ('16', '2019-06-20', '2020-08-18', '55', '开卷', '68', '3', '1', '19980831', '135');
INSERT INTO `t_course` VALUES ('17', '2019-06-30', '2019-08-25', '38', '开卷', '50', '3', '1', '19980831', '136');
INSERT INTO `t_course` VALUES ('18', '2019-06-30', '2019-06-19', '100', '教考分离', '75', '5', '1', '19980831', '2');
INSERT INTO `t_course` VALUES ('19', '2019-06-30', '2019-07-30', '40', '教考分离', '90', '7', '1', '19980831', '6');
INSERT INTO `t_course` VALUES ('20', '2019-07-04', '2019-07-11', '40', '教考', '80', '6', '0', '19980831', '1');
INSERT INTO `t_course` VALUES ('21', '2019-07-01', '2019-07-09', '80', '开卷', '75', '3', '0', '16429113', '3');
INSERT INTO `t_course` VALUES ('22', '2019-06-24', '2019-07-26', '100', '开卷', '70', '2', '1', '1560310', '2');
INSERT INTO `t_course` VALUES ('23', '2019-07-01', '2019-07-26', '85', '开卷', '65', '8', '0', '1560311', '3');
INSERT INTO `t_course` VALUES ('24', '2019-06-27', '2019-07-27', '45', '开卷', '85', '0', '0', '1560312', '7');
INSERT INTO `t_course` VALUES ('25', '2019-06-04', '2019-08-03', '50', '开卷', '79', '5', '0', '1560311', '5');
INSERT INTO `t_course` VALUES ('26', '2019-04-08', '2019-07-25', '50', '开卷', '85', '0', '0', '16429113', '1');
INSERT INTO `t_course` VALUES ('27', '2019-03-12', '2019-07-19', '55', '开卷', '90', '0', '0', '1560312', '8');

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `author` varchar(30) NOT NULL COMMENT '作者',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `auth` int(11) NOT NULL DEFAULT 3 COMMENT '查看权限（1、全体可见 2、仅教师可见 3、仅管理员可见）',
  `date` date NOT NULL COMMENT '发布日期',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('5', 'test3全体可见公告', '管理员', '', '1', '2019-06-18');
INSERT INTO `t_notice` VALUES ('6', 'test2教师可见公告', '管理员', '', '2', '2019-06-18');
INSERT INTO `t_notice` VALUES ('7', 'test1管理员可见公告', '管理员', '', '3', '2019-06-18');

-- ----------------------------
-- Table structure for `t_score`
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩id',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '考试成绩',
  `result` varchar(255) NOT NULL DEFAULT '' COMMENT '考察结果',
  `c_id` int(11) NOT NULL COMMENT '外键-课程id',
  `s_id` varchar(255) NOT NULL COMMENT '外键-学号',
  PRIMARY KEY (`id`) ,
  KEY `score_stu` (`s_id`) ,
  KEY `score_course` (`c_id`) ,
  CONSTRAINT `score_course` FOREIGN KEY (`c_id`) REFERENCES `t_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_stu` FOREIGN KEY (`s_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES ('23', '0', '', '7', '20160310529');
INSERT INTO `t_score` VALUES ('24', '100', '99.99', '15', '20160310529');
INSERT INTO `t_score` VALUES ('25', '0', '', '13', '20160310529');
INSERT INTO `t_score` VALUES ('28', '95', '95', '16', '20160310550');
INSERT INTO `t_score` VALUES ('29', '98', '98', '17', '20160310550');
INSERT INTO `t_score` VALUES ('30', '100', '99', '16', '20160310529');
INSERT INTO `t_score` VALUES ('31', '100', '99', '17', '20160310529');
INSERT INTO `t_score` VALUES ('33', '90', '90', '16', '20160310500');
INSERT INTO `t_score` VALUES ('34', '92', '92', '17', '20160310500');
INSERT INTO `t_score` VALUES ('35', '96', '98', '18', '20160310529');
INSERT INTO `t_score` VALUES ('36', '92', '92', '18', '20160310600');
INSERT INTO `t_score` VALUES ('37', '91', '91', '19', '20160310600');
INSERT INTO `t_score` VALUES ('38', '88', '88', '18', '20160310601');
INSERT INTO `t_score` VALUES ('39', '92', '92', '19', '20160310601');
INSERT INTO `t_score` VALUES ('40', '60', '60', '18', '20160310602');
INSERT INTO `t_score` VALUES ('41', '93', '93', '19', '20160310602');
INSERT INTO `t_score` VALUES ('42', '94', '94', '19', '20160310603');
INSERT INTO `t_score` VALUES ('43', '59', '59', '18', '20160310603');
INSERT INTO `t_score` VALUES ('44', '0', '', '13', '20160310603');
INSERT INTO `t_score` VALUES ('45', '98', '99', '19', '20160310529');
INSERT INTO `t_score` VALUES ('46', '97', '97', '19', '20160310500');
INSERT INTO `t_score` VALUES ('47', '96', '96', '19', '20160310550');
INSERT INTO `t_score` VALUES ('48', '0', '', '7', '16429135');

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` varchar(20) NOT NULL COMMENT '学号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` varchar(10) NOT NULL COMMENT '性别',
  `admission_date` date NOT NULL COMMENT '入学日期',
  `major` varchar(50) NOT NULL COMMENT '专业',
  `grade` varchar(50) NOT NULL COMMENT '班级',
  `education` varchar(20) NOT NULL COMMENT '学历',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('16429135', 'E10ADC3949BA59ABBE56E057F20F883E', '王三', '男', '2019-09-02', '网络信息技术', '16级三班', '本科');
INSERT INTO `t_student` VALUES ('16429136', '123456', '王四', '女', '2019-07-02', '计算机科学与技术', '16级', '本科');
INSERT INTO `t_student` VALUES ('16429137', '123456', '王六', '男', '2019-07-15', '网络工程', '15级', '本科');
INSERT INTO `t_student` VALUES ('16429138', '123456', '王七', '女', '2019-05-07', '网络工程', '18级', '本科');
INSERT INTO `t_student` VALUES ('16429139', '123456', '王八', '女', '2019-07-02', '网络工程', '17级', '本科');
INSERT INTO `t_student` VALUES ('16429140', '123456', '王九', '女', '2019-07-15', '计算机', '16级', '本科');
INSERT INTO `t_student` VALUES ('16429141', '123456', '王十', '男', '2019-06-11', '网络工程', '15级', '本科');
INSERT INTO `t_student` VALUES ('16429142', '123456', '李大', '男', '2019-07-09', '信息工程', '17级', '本科');
INSERT INTO `t_student` VALUES ('16429143', '123456', '李二', '男', '2019-07-08', '网络工程', '18级', '本科');
INSERT INTO `t_student` VALUES ('16429144', '123456', '李三', '男', '2019-06-29', '信息工程', '16级', '本科');
INSERT INTO `t_student` VALUES ('16429145', '123456', '李五', '女', '2019-06-24', '网络工程', '16级', '本科');
INSERT INTO `t_student` VALUES ('16429146', '123456', '李六', '女', '2019-06-27', '网络工程', '15级', '本科');
INSERT INTO `t_student` VALUES ('16429147', '123456', '李七', '男', '2019-06-13', '信息工程', '18级', '本科');
INSERT INTO `t_student` VALUES ('16429148', '123456', '李八', '男', '2019-07-25', '信息工程', '15级', '本科');
INSERT INTO `t_student` VALUES ('16429149', '123456', '李九', '女', '2019-07-01', '计算机', '16级', '本科');
INSERT INTO `t_student` VALUES ('16429150', '123456', '李十', '男', '2019-05-15', '网络工程', '17级', '本科');
INSERT INTO `t_student` VALUES ('20160310500', 'E10ADC3949BA59ABBE56E057F20F883E', '王一', '男', '2016-09-06', '计算机科学与技术', '16计科5班', '本科');
INSERT INTO `t_student` VALUES ('20160310529', 'E10ADC3949BA59ABBE56E057F20F883E', '王二', '男', '2016-09-06', '计算机科学与技术', '16计科5班', '本科');
INSERT INTO `t_student` VALUES ('20160310550', 'E10ADC3949BA59ABBE56E057F20F883E', '王三', '女', '2016-09-06', '计算机科学与技术', '16计科5班', '本科');
INSERT INTO `t_student` VALUES ('20160310600', 'E10ADC3949BA59ABBE56E057F20F883E', '老王', '男', '2016-09-06', '计算机科学与技术', '16计科6班', '本科');
INSERT INTO `t_student` VALUES ('20160310601', 'E10ADC3949BA59ABBE56E057F20F883E', '张三', '男', '2016-09-06', '计算机科学与技术', '16计科6班', '本科');
INSERT INTO `t_student` VALUES ('20160310602', 'E10ADC3949BA59ABBE56E057F20F883E', '李四', '男', '2016-09-06', '计算机科学与技术', '16计科6班', '本科');
INSERT INTO `t_student` VALUES ('20160310603', 'E10ADC3949BA59ABBE56E057F20F883E', '王五', '女', '2016-09-06', '计算机科学与技术', '16计科6班', '本科');

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` varchar(20) NOT NULL COMMENT '教师职工号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `synopsis` varchar(255) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1560310', 'E10ADC3949BA59ABBE56E057F20F883E', '蔡老师', '1916年至1927年任北京大学校长，革新北大，开“学术”与“自由”之风；1920年至1930年，蔡元培同时兼任中法大学校长。');
INSERT INTO `t_teacher` VALUES ('1560311', 'E10ADC3949BA59ABBE56E057F20F883E', '陈老师', '清华大学2013年研究生学位毕业');
INSERT INTO `t_teacher` VALUES ('1560312', 'E10ADC3949BA59ABBE56E057F20F883E', '张老师', '兰州大学2017年博士生双学位毕业');
INSERT INTO `t_teacher` VALUES ('16429113', 'E10ADC3949BA59ABBE56E057F20F883E', '张大山', '北大2019年研究生毕业');
INSERT INTO `t_teacher` VALUES ('19980831', 'E10ADC3949BA59ABBE56E057F20F883E', '梁老师', '毕业于广东科学技术大学（原佛山科学技术学院）');
