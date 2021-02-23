package com.slzhkj.smartworksite.server.scheduler;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Constant;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器任务组for 测试
 *
 * @author Yuezejian
 * @date 2020年 11月16日 11:08:45
 */
@Slf4j
@Component
public class SchedulerGroupForTest {

    private String send = "http://127.0.0.1:9103/smartworksite/test/insert";

    private String get = "http://127.0.0.1:9103/smartworksite/test/query";



    public void scheduled() {
        log.info("=====>>>>>startApi", System.currentTimeMillis());
        JSONObject json1 = new JSONObject();
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
