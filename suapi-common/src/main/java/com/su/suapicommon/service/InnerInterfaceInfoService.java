package com.su.suapicommon.service;

import com.su.suapicommon.model.entity.InterfaceInfo;

/**
 * 接口信息服务
 *
 */
public interface InnerInterfaceInfoService{
    //	2.从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数，返回接口信息，为空表示不存在）
    InterfaceInfo getInterfaceInfo(String path, String method);
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
