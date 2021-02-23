package com.slzhkj.smartworksite.model.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 三方请求获取token的Dto
 *
 * @author Yuezejian
 * @date 2020年 11月02日 11:03:08
 */
@Getter
public class RequestDto {
    /**
     * appId 请求方唯一标识
     */
    private String appId;
    /**
     * secret 请求方私钥
     */
    private String secret;
    /**
     * token 进行请求校验的密钥
     */
    private String token;

    /**
     * salt 加密用盐
     */
    private String salt;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public RequestDto(String appId, String secret, String token, String salt) {
        this.appId = appId;
        this.secret = secret;
        this.token = token;
        this.salt = salt;
    }

    public RequestDto(String appId, String secret, String token, Timestamp updateTime) {
        this.appId = appId;
        this.secret = secret;
        this.token = token;
        this.updateTime = updateTime;
    }
}
