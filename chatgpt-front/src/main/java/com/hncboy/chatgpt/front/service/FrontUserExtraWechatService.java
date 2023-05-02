package com.hncboy.chatgpt.front.service;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.base.constant.ApplicationConstant;
import com.hncboy.chatgpt.base.domain.entity.FrontUserBaseDO;
import com.hncboy.chatgpt.base.domain.entity.FrontUserExtraBindingDO;
import com.hncboy.chatgpt.base.domain.entity.FrontUserExtraWechatDO;
import com.hncboy.chatgpt.base.enums.FrontUserRegisterTypeEnum;
import com.hncboy.chatgpt.base.exception.ServiceException;
import com.hncboy.chatgpt.base.mapper.FrontUserExtraWechatMapper;
import com.hncboy.chatgpt.front.domain.vo.LoginInfoVO;
import com.hncboy.chatgpt.front.domain.vo.UserInfoVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.hncboy.chatgpt.base.constant.ApplicationConstant.FRONT_JWT_EXTRA_USER_ID;
import static com.hncboy.chatgpt.base.constant.ApplicationConstant.FRONT_JWT_USERNAME;

/**
 * @author david
 * @since 2023/4/30
 */
@Slf4j
@Service
public class FrontUserExtraWechatService extends ServiceImpl<FrontUserExtraWechatMapper, FrontUserExtraWechatDO> {
    @Resource
    private FrontUserBaseService baseUserService;
    @Resource
    private FrontUserExtraBindingService bindingService;

    public FrontUserExtraWechatDO register(String openid) {
        FrontUserExtraWechatDO wechatDO = getByOpenId(openid);
        if (wechatDO != null) {
            log.info("用户已存在，openid:{}", openid);
            return wechatDO;
        }
        wechatDO = FrontUserExtraWechatDO.builder().openid(openid).build();
        FrontUserBaseDO baseUser = baseUserService.createEmptyBaseUser();
        bindingService.bindWechat(baseUser, wechatDO);
        save(wechatDO);
        log.info("用户注册成功，openid:{}", openid);
        return wechatDO;
    }

    public LoginInfoVO login(FrontUserExtraWechatDO wechatDO, String state) {
        //获取用户信息
        UserInfoVO userInfo = this.getLoginUserInfo(wechatDO);

        // 执行登录
        StpUtil.login(userInfo.getBaseUserId(), SaLoginModel.create()
                .setExtra(FRONT_JWT_USERNAME, wechatDO.getOpenid())
                .setExtra(ApplicationConstant.FRONT_JWT_REGISTER_TYPE_CODE, FrontUserRegisterTypeEnum.WECHAT.getCode())
                .setExtra(FRONT_JWT_EXTRA_USER_ID, wechatDO.getId()));

        //返回token给前端
        return LoginInfoVO.builder().token(StpUtil.getTokenValue()).baseUserId(userInfo.getBaseUserId()).build();
    }

    public UserInfoVO getLoginUserInfo(Integer extraInfoId) {
        FrontUserExtraWechatDO extraWechatDO = getById(extraInfoId);
        if (Objects.isNull(extraWechatDO)) {
            throw new ServiceException(StrUtil.format("用户ID：{} 不存在", extraInfoId));
        }
        return getLoginUserInfo(extraWechatDO);
    }

    public UserInfoVO getLoginUserInfo(FrontUserExtraWechatDO extraEmailDO) {
        Integer extraInfoId = extraEmailDO.getId();
        // 根据注册类型+extraInfoId获取 当前邮箱绑定在了哪个用户上
        FrontUserExtraBindingDO bindingRelations = bindingService.findExtraBinding(FrontUserRegisterTypeEnum.WECHAT, extraInfoId);
        if (Objects.isNull(bindingRelations)) {
            throw new ServiceException(StrUtil.format("注册方式：{} 额外信息ID：{} 绑定关系不存在",
                    FrontUserRegisterTypeEnum.EMAIL.getDesc(), extraInfoId));
        }
        // 根据绑定关系查找基础用户信息
        FrontUserBaseDO baseUser = baseUserService.findUserInfoById(bindingRelations.getBaseUserId());
        if (Objects.isNull(baseUser)) {
            throw new ServiceException(StrUtil.format("基础用户不存在：{}", bindingRelations.getBaseUserId()));
        }
        // 封装基础用户信息并返回
        return UserInfoVO.builder().baseUserId(baseUser.getId())
                .description(baseUser.getDescription())
                .nickname(baseUser.getNickname())
                .email("").avatarUrl("").build();
    }


    private FrontUserExtraWechatDO getByOpenId(String openid) {
        return getOne(new LambdaQueryWrapper<FrontUserExtraWechatDO>()
                .eq(FrontUserExtraWechatDO::getOpenid, openid));
    }
}
