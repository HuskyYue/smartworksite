package com.slzhkj.smartworksite.server.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.PostUrlTestDto;
import com.slzhkj.smartworksite.server.enums.Constant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>主动接入数据相关控制器</h1>
 * @author Yuezejian
 * @date 2020年 12月07日 16:09:17
 */
@RestController
@RequestMapping("accept")
public class ActiveAcceptController extends AbstractController{

    /**
     * url: accept/postUrlTest
     * <h2>数据请求URL获取数据测试接口</h2>
     * @param dto {@link PostUrlTestDto}
     * @return
     */
    @RequestMapping("postUrlTest")
    public BaseResponse postUrlTest(@RequestBody PostUrlTestDto dto) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO
            String params = "{"+dto.getCronClassParams()+"}";
            JSONObject json1 = JSON.parseObject(params);
            logger.info("执行的参数为：{}",json1);
            String get = String.format("%s%s",
                    Constant.API.SmartWorkSiteGetPrefix,dto.getPostUrl());
            String res = HttpRequest.post(get)
                    .timeout(60000)
                    .body(json1+"", "application/json;charset=UTF-8")
                    .execute()
                    .body();
            response.setData(res);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
