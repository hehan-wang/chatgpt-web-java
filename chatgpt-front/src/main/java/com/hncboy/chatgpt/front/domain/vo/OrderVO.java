package com.hncboy.chatgpt.front.domain.vo;

import com.hncboy.chatgpt.base.domain.enumeration.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 登录成功返回登录结果
 *
 * @author CoDeleven
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(title = "订单返回结果")
public class OrderVO {

    private Integer id;

    private String orderCode;

    private Integer userId;

    private Integer productId;

    private String product;

    private OrderStatus status;

    private Long usage;

    private Date expireTime;

    private Date createTime;

    private Date updateTime;
}
