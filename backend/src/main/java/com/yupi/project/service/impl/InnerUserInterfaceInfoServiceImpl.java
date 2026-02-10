package com.yupi.project.service.impl;

import com.su.suapicommon.model.entity.UserInterfaceInfo;
import com.su.suapicommon.service.InnerUserInterfaceInfoService;
import com.yupi.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {

        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }
}
