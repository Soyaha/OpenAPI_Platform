package com.yupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.su.suapicommon.model.entity.User;
import com.su.suapicommon.service.InnerUserService;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.annotation.Resource;

@DubboService
public class InnerUserServiceImpl implements InnerUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey, String secrectKey) {
        if(StringUtils.isAnyBlank(accessKey,secrectKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);
        queryWrapper.eq("secrectKey", secrectKey);
        return userMapper.selectOne(queryWrapper);
    }
}
