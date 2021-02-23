package com.slzhkj.smartworksite.server.controller;

import com.google.gson.Gson;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.RequestDto;
import com.slzhkj.smartworksite.model.dto.SignDto;
import com.slzhkj.smartworksite.server.enums.Constant;
import com.slzhkj.smartworksite.server.service.RequestService;
import com.slzhkj.smartworksite.server.utils.SaltMD5Util;
import com.slzhkj.smartworksite.server.utils.SignCreatedUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * <h1>和获取 token 相关操作的控制器</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月02日 10:26:32
 */
@RestController
@RequestMapping("sign")
public class TokenAndSignController extends AbstractController{

    @Autowired
    private Environment env;

    @Autowired
    RequestService requestService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * url: /sign/getToken
     * <h2>三方请求当前系统，需要从当前接口获取 token 令牌</h2>
     * @param params
     * @return
     */
    @RequestMapping("getToken")
    public BaseResponse getToken(@RequestBody String params) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Gson gson = new Gson();
        RequestDto dto = gson.fromJson(params,RequestDto.class);
        String appId = dto.getAppId();
        String secret = dto.getSecret();
        //TODO: 判断是否为可申请 token 的用户
        int enable = requestService.getByAppIdAndSecretForEnable(appId,secret);
        if (enable == 0) {
            return new BaseResponse(StatusCode.InvalidPermission);
        }
        SaltMD5Util md5Util = SaltMD5Util.getInstance();
        Pair<String, String> pair;
        ValueOperations <String,String> tokenOpera = redisTemplate.opsForValue();
        //TODO: appId_secret 作为字符串进行加盐密，add方法使用UUID做为加密的盐
        pair = md5Util.add(dto.getAppId()+ Constant.SplitChar+dto.getSecret(), SaltMD5Util.EncryptCategory.FIXED_STRING);
        try {
            int res = requestService.getByAppIdAndSecret(appId,secret);
            if (res > 0) {
                //TODO: (非第一次申请 token ) 执行token更新操作
                    RequestDto dto1 = new RequestDto(appId, secret, pair.getRight(), pair.getLeft());
                    int res1 = requestService.updateRequestLog(dto1);
                    updateRedisAndBloomFilter(appId, tokenOpera, pair.getRight(), res1);

            } else {
                //TODO (第一次申请 token ) 执行token新增操作
                    RequestDto dto1 = new RequestDto(appId, secret, pair.getRight(), pair.getLeft());
                    int res2 = requestService.addRequestLog(dto1);
                    updateRedisAndBloomFilter(appId, tokenOpera, pair.getRight(), res2);
            }
           String token = pair.getRight();
           response.setData(token);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 更新 Redis 和 BloomFilter
     * @param appId
     * @param tokenOpera
     * @param token
     * @param res
     */
    private void updateRedisAndBloomFilter(String appId, ValueOperations<String, String> tokenOpera,
                                           String token, int res) {
        if ( res > 0 ) {
            //TODO: update redis ，insert BloomFilter
            String key = appId;
            tokenOpera.set(key,token,env.getProperty("token.enable.time",Long.class), TimeUnit.MINUTES);
            filter.put(token);
            filter.put(appId);
            logger.info("appId为：{} 的用户，更新了token: {}， 已存入Redis和基类布隆过滤器", appId, token);
        } else {
            logger.error("appId为：{} 的用户，更新了token: {}， Database update success, but Redis or BloomFilter update fail!",appId,token);
            throw new IllegalStateException("Database update success, but Redis or BloomFilter update fail!");
        }
    }

    /**
     * url: /sign/getSign
     * <h2>请求建筑工人实名制管理平台标准化服务接口，获取对应的签名</h2>
     * @param params
     * @return
     */
    @RequestMapping("getSign")
    public BaseResponse getSign(@RequestBody String params) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        SignDto signDto = new Gson().fromJson(params,SignDto.class);
        try {
            //TODO 专门用来测试的
            SignCreatedUtils signUtils = SignCreatedUtils.getInstance();
            String res = signUtils.sign(signDto);
            response.setData(res);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
