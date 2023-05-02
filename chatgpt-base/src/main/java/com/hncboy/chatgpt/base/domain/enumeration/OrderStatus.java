package com.hncboy.chatgpt.base.domain.enumeration;

/**
 * @author david
 * @since 2023/5/2
 */
public enum OrderStatus {
/**
     * 未支付
     */
    UNPAID,
    /**
     * 已支付
     */
    PAID,
    /**
     * 已取消
     */
    CANCELLED,
    /**
     * 已完成
     */
    COMPLETED,
    /**
     * 已退款
     */
    REFUNDED,
    /**
     * 已过期
     */
    EXPIRED

}
