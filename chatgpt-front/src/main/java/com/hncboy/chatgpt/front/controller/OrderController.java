package com.hncboy.chatgpt.front.controller;

import com.hncboy.chatgpt.base.handler.response.R;
import com.hncboy.chatgpt.front.domain.vo.LoginInfoVO;
import com.hncboy.chatgpt.front.domain.vo.OrderVO;
import com.hncboy.chatgpt.front.service.OrderService;
import com.hncboy.chatgpt.front.util.FrontUserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端订单控制器
 *
 * @author david
 */
@Slf4j
@AllArgsConstructor
@Tag(name = "订单相关接口")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "当前套餐")
    @GetMapping("/current")
    public R<OrderVO> current() {
        OrderVO orderVO = orderService.currentOrder(FrontUserUtil.getUserId());
//        return R.data(orderVO);
        //TODO
        return R.data(OrderVO.builder().product("test1").build());
    }
}