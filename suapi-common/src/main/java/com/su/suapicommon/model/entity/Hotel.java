package com.su.suapicommon.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 酒店
 *
 * @TableName hotel
 */
@TableName(value = "hotel")
@Data
public class Hotel implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 酒店名称
     */
    private String name;

    /**
     * 酒店地址
     */
    private String address;

    /**
     * 基础价格
     */
    private Double price;

    /**
     * 评分
     */
    private Double score;

    /**
     * 星级
     */
    private Integer star;

    /**
     * 简介
     */
    private String description;

    /**
     * 设施(JSON字符串)
     */
    private String facilities;

    /**
     * 状态: pending/approved/rejected/offline
     */
    private String status;

    /**
     * 拒绝原因
     */
    private String rejectReason;

    /**
     * 创建人ID (商户)
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
