package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hncboy.chatgpt.base.domain.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 产品实体类
 *
 * @author david
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "t_order")
@Data
public class OrderDO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 产品 id
     */
    private Integer productId;

    /**
     * 产品详情
     */
    private String product;

    /**
     * 订单状态
     */
    @TableField("`status`")
    private OrderStatus status;
    /**
     * 用量
     */
    @TableField("`usage`")
    private Long usage;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}