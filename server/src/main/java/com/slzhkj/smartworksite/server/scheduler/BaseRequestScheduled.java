package com.slzhkj.smartworksite.server.scheduler;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 定时器类基类
 *
 * @author Yuezejian
 * @date 2020年 11月24日 14:05:18
 */
@Slf4j
@SuppressWarnings("all")
public class BaseRequestScheduled {

    /**
     * 通用执行方法
     * @param params
     * @param get
     * @param send
     */
    protected static void scheduled(String param,String get,String send) {
        log.info("=====>>>>>startApi，currentTime: {}", System.currentTimeMillis());
        String params = "{"+param+"}";
        JSONObject json1 = JSON.parseObject(params);
        log.info("执行的参数为：{}",json1);
        String res = HttpRequest.post(get)
                .timeout(60000)
                .body(json1+"", "application/json;charset=UTF-8")
                .execute()
                .body();
        JSONObject json2 = new JSONObject();
        json2 = (JSONObject) JSONObject.parse(res);
        System.out.println(res);
        if(res.length()>10) {
            String result = HttpRequest.post(send)
                    .timeout(60000)
                    .body(json2+"", "application/json;charset=UTF-8")
                    .execute()
                    .body();
            System.out.println(result);
        }else {
            System.out.println("无新指令！");

        }

    }
}
