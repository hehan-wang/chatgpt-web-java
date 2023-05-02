package com.hncboy.chatgpt.front.controller;

import com.hncboy.chatgpt.base.domain.entity.ProductDO;
import com.hncboy.chatgpt.base.handler.response.R;
import com.hncboy.chatgpt.front.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前端订单控制器
 *
 * @author david
 */
@Slf4j
@AllArgsConstructor
@Tag(name = "产品相关接口")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "列表接口")
    @GetMapping("/all")
    public R<List<ProductDO>> list() {
        List<ProductDO> list = productService.list();
        return R.data(list);
    }
}