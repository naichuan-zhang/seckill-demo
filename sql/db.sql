-- MySQL dump 10.13  Distrib 5.7.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seckill
-- ------------------------------------------------------
-- Server version	5.7.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
                           `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
                           `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
                           `goods_img` varchar(64) DEFAULT NULL COMMENT '商品图片',
                           `goods_detail` longtext COMMENT '商品详情',
                           `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
                           `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存，-1为无限制',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                           `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                           `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
                           `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收货地址ID',
                           `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
                           `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
                           `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
                           `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc,2android,3ios',
                           `status` tinyint(4) DEFAULT '0' COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
                           `create_date` datetime DEFAULT NULL COMMENT '订单创建时间',
                           `pay_date` datetime DEFAULT NULL COMMENT '订单支付时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID，手机号码',
                          `nickname` varchar(255) NOT NULL,
                          `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt)+salt)',
                          `salt` varchar(10) DEFAULT NULL,
                          `head` varchar(128) DEFAULT NULL COMMENT '头像',
                          `register_date` datetime DEFAULT NULL COMMENT '注册时间',
                          `last_login_date` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                          `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 15:53:40
