package com.hncboy.chatgpt.front.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.base.domain.entity.OrderDO;
import com.hncboy.chatgpt.base.mapper.OrderMapper;
import com.hncboy.chatgpt.front.domain.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author david
 * @since 2023/5/2
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, OrderDO> {

    public OrderVO currentOrder(Integer userId) {
        if (userId == null) {
            return null;
        }
        List<OrderDO> all = list(Wrappers.<OrderDO>lambdaQuery().eq(OrderDO::getUserId, userId));
        OrderDO orderDO = all.stream().sorted(Comparator.comparing(OrderDO::getUpdateTime).reversed()).findFirst().orElse(null);
        if (orderDO == null) {
            return null;
        }
        return OrderVO.builder()
                .id(orderDO.getId())
                .orderCode(orderDO.getOrderCode())
                .userId(orderDO.getUserId())
                .productId(orderDO.getProductId())
                .product(orderDO.getProduct())
                .status(orderDO.getStatus())
                .usage(orderDO.getUsage())
                .expireTime(orderDO.getExpireTime())
                .createTime(orderDO.getCreateTime())
                .updateTime(orderDO.getUpdateTime())
                .build();
    }

}
