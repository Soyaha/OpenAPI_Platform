package com.su.suapicommon.service;


import com.su.suapicommon.model.entity.UserInterfaceInfo;

/**
 * 用户接口信息服务
 *
 */
public interface InnerUserInterfaceInfoService{

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
