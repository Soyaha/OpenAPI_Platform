package service;

import com.baomidou.mybatisplus.extension.service.IService;
import model.entity.InterfaceInfo;
import model.entity.User;
import model.entity.UserInterfaceInfo;

/**
 * 用户接口信息服务
 *
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {


    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);
    //	1.数据库中查是否已分配给用户秘钥(根据 accessKey 拿到用户信息，返回用户信息，为空表示不存在）
    User getInvokeUser(String accessKey,String secrectKey);
    //	2.从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数，返回接口信息，为空表示不存在）
    InterfaceInfo getInterfaceInfo(String path,String method);
    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
