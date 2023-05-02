package com.hncboy.chatgpt.front.controller;

import cn.hutool.json.JSONUtil;
import com.hncboy.chatgpt.base.domain.entity.FrontUserExtraWechatDO;
import com.hncboy.chatgpt.base.handler.response.R;
import com.hncboy.chatgpt.front.domain.vo.LoginInfoVO;
import com.hncboy.chatgpt.front.service.FrontUserExtraWechatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 前端oauth回调控制器
 *
 * @author david
 */
@Slf4j
@AllArgsConstructor
@Tag(name = "用户相关接口")
@RestController
@RequestMapping("/callback")
public class OauthController {
    //TODO 放到配置文件
    private static final String APP_ID = "your_appid"; // 替换为你的 AppID
    private static final String APP_SECRET = "your_appsecret"; // 替换为你的 AppSecret

    private final FrontUserExtraWechatService wechatService;

    @Operation(summary = "微信回调")
    @PostMapping("/wechat")
    public R<LoginInfoVO> loginWechat(@RequestParam("code") String code, @RequestParam("state") String state) {
        log.info("code: {}, state: {}", code, state);
        //1.调用微信接口
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(accessTokenUrl, String.class);
        log.info("response: {}", response);
        String openid = JSONUtil.parseObj(response).get("openid").toString();
        //2.没注册的话先注册,否则跳过直接登录
        FrontUserExtraWechatDO wechatDO = wechatService.register(openid);
        //3.登陆
        LoginInfoVO res = wechatService.login(wechatDO, state);
        return R.data(res);
    }
}