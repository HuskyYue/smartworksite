package com.slzhkj.smartworksite.server.utils;

import com.slzhkj.smartworksite.model.dto.SignDto;
import com.slzhkj.smartworksite.server.enums.Constant;
import org.springframework.util.DigestUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 主动请求建筑工人实名制管理平台标准化服务接口的 sign 生成器
 *
 * @author Yuezejian
 * @date 2020年 11月04日 16:53:09
 */
public class SignCreatedUtils<T extends SignDto> {

    private String sign;

    private T t;


    public String sign(T t) {
        //TODO: timestamp 调用方时间戳，格式为“ 4 位年+2 位月+2 位日+2 位小时(24 小时制)+2 位分+2 位秒”，
        // 用于接口提供方判断调用方的时间，通常约定调用请求的时间戳与接口提供方收到请求的时间差在约定的范围内。
        // 例如：20170215101958
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timestamp = sf.format(date);
        String beforeSign = String.format("appid=%s&format=json&method=%s&nonce=%s&timestamp=%s&version=%s&data=%s&appsecret=%s",
                t.getAppid(),t.getMethod(),UUID.randomUUID(),timestamp,Constant.version,t.getData().toString(),t.getAppsecret());

        String beforeSign1 = "appid="+ t.getAppid() + "&format=json&method=" + t.getMethod()
                + "&nonce=" + "d0350475-d31a-4c00-856c-76e0a65d381b" + "&timestamp=" + "20201105111124"
                + "&version=" + Constant.version + "&data=" + t.getData().toString()
                + "&appsecret=" + t.getAppsecret();
        String afterSign = DigestUtils.md5DigestAsHex(beforeSign1.getBytes());
        return afterSign;
    }

    private static volatile SignCreatedUtils instance;

    public static SignCreatedUtils getInstance() {
        if ( instance == null ) {
            synchronized (SignCreatedUtils.class) {
                if ( instance == null ) {
                    instance = new SignCreatedUtils();
                }
            }
        }
        return instance;
    }
}
