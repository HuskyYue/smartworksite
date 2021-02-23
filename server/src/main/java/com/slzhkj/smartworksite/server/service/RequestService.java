package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.RequestDto;
import org.springframework.stereotype.Service;

/**
 * <h1>三方请求service</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月02日 11:38:24
 */
@Service
public interface RequestService {

    /**
     * <h2>新增三方获取token的记录</h2>
     * @param dto
     * @return
     */
    int  addRequestLog(RequestDto dto);


    /**
     * <h2>通过appId和secret 查询三方记录信息</h2>
     * @param appId
     * @param secret
     * @return
     */
    int getByAppIdAndSecret(String appId, String secret);

    /**
     * <h2>更新三方获取token的记录</h2>
     * @param dto
     * @return
     */
    int updateRequestLog(RequestDto dto);

    /**
     * <h2>根据appid 三方获取token的记录</h2>
     * @param appId
     * @return
     */
    int getByAppId(String appId);

    /**
     * <h2>是否为可申请 token 的用户</h2>
     */
    int getByAppIdAndSecretForEnable(String appId, String secret);


}
