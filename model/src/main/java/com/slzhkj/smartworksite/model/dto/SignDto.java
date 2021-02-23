package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * 建筑工人实名制管理平台标准化服务接口
 * 访问签名dto
 *
 * @author Yuezejian
 * @date 2020年 11月04日 16:37:10
 */
@AllArgsConstructor
public class SignDto implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 项目编号，由接口提供方分配给接口调用方的身份标识符
     */
    private String appid;
    /**
     * 密钥
     */
    private String appsecret;
    /**
     * 由接口提供方指定的接口标识符。例如：Common.GetWorkTypeDictList
     */
    private String method;
    /**
     * 	随机数，由调用方生成，防止重复处理和提高验签的安全性的，所以要求要求保证同一timestamp传递不同的nonce值。
     */
//    private String nonce;
    /**
     * 调用方时间戳，格式为“ 4 位年+2 位月+2 位日+2 位小时(24 小时制)+2 位分+2 位秒”，用于接口提供方判断调用方的时间，
     * 通常约定调用请求的时间戳与接口提供方收到请求的时间差在约定的范围内。例如：20170215101958
     */
    //private long timestamp;
    /**
     * 具体的接口方法中的参数实体信息（对象需要转换成 JSON 格式的字符串）
     */
    private SignDataDto data;
    /**
     * 签名字符串，按照签名生成算法计算得来。签名算法参见签名算法详细说明。
     */
    private String sign;

    public String getAppid() {
        return appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getMethod() {
        return method;
    }

    public SignDataDto getData() {
        return data;
    }

    public String getSign() {
        return sign;
    }
}
