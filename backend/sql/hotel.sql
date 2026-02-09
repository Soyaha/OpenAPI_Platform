-- 酒店表
CREATE TABLE IF NOT EXISTS `hotel` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` varchar(256) NOT NULL COMMENT '酒店名称',
    `address` varchar(512) NOT NULL COMMENT '酒店地址',
    `price` decimal(10, 2) NOT NULL COMMENT '基础价格',
    `score` double DEFAULT 0 COMMENT '评分',
    `star` int DEFAULT 3 COMMENT '星级',
    `description` text COMMENT '简介',
    `facilities` varchar(1024) COMMENT '设施(JSON)',
    `status` varchar(50) DEFAULT 'pending' COMMENT '状态: pending/approved/rejected/offline',
    `rejectReason` varchar(512) COMMENT '拒绝原因',
    `userId` bigint NOT NULL COMMENT '创建人ID (商户)',
    `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete` tinyint DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`id`)
) COMMENT '酒店信息表';
