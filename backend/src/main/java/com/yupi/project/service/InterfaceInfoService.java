package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.su.suapicommon.model.entity.InterfaceInfo;

/**
 * 接口信息服务
 *
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
