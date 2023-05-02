package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品实体类
 *
 * @author david
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "t_product")
@Data
public class ProductDO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 套餐分类
     */
    private String catalog;

    /**
     * 套餐类型
     */
    private String type;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 币种
     */
    private String currency;

    /**
     * 状态
     */
    @TableField("`status`")
    private String status;

    /**
     * 套餐上限
     */
    private Long threshold;

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