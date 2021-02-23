package com.slzhkj.smartworksite.model.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 三方请求获取token的Dto(包含id,用于token失效验证)
 * @author Yuezejian
 * @date 2020年 11月11日 14:01:24
 */
@Getter
public class RequestIDDto {

    /**
     * token 生成表的唯一主键
     */
    private Integer id;
    /**
     * appId 请求方唯一标识
     */
    private String appId;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * token 进行请求校验的密钥
     */
    private String token;

    public RequestIDDto(Integer id, String appId, Timestamp updateTime, String token) {
        this.id = id;
        this.appId = appId;
        this.updateTime = updateTime;
        this.token = token;
    }
}
