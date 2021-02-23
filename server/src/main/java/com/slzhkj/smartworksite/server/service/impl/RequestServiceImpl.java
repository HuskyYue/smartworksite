package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.dto.RequestDto;
import com.slzhkj.smartworksite.model.mapper.RequestMapper;
import com.slzhkj.smartworksite.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>三方请求service实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月02日 13:54:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestMapper requestMapper;

    /**
     * <h2>新增三方获取token的记录</h2>
     * @param dto
     * @return
     */
    @Override
    public int addRequestLog(RequestDto dto) {
        return requestMapper.addRequestLog(dto);
    }

    /**
     * <h2>通过appId和secret 查询三方记录信息</h2>
     *
     * @param appId
     * @param secret
     * @return
     */
    @Override
    public int getByAppIdAndSecret(String appId, String secret) {
        return requestMapper.getByAppIdAndSecret(appId,secret);
    }

    /**
     * <h2>更新三方获取token的记录</h2>
     *
     * @param dto
     * @return
     */
    @Override
    public int updateRequestLog(RequestDto dto) {
        return requestMapper.updateRequestLog(dto);
    }

    /**
     * <h2>根据appid 三方获取token的记录</h2>
     *
     * @param appId
     * @return
     */
    @Override
    public int getByAppId(String appId) {
        return requestMapper.getByAppId(appId);
    }

    /**
     * <h2>是否为可申请 token 的用户</h2>
     *
     * @param appId
     * @param secret
     */
    @Override
    public int getByAppIdAndSecretForEnable(String appId, String secret) {
        return requestMapper.getByAppIdAndSecretForEnable(appId,secret);
    }


}
