package com.slzhkj.smartworksite.server.controller;

import com.google.gson.Gson;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.DataAcceptDto;
import com.slzhkj.smartworksite.server.service.DataAcceptService;
import com.slzhkj.smartworksite.server.service.log.LogAopAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据接入Controller
 *
 * @author Yuezejian
 * @date 2020年 11月03日 10:26:35
 */
@RestController
@RequestMapping("openApi")
public class  DataAcceptController extends AbstractController{

    @Autowired
    DataAcceptService dataAcceptService;

    /**
     * 常规流程进行数据接入
     * @param params
     * @return
     */
    @RequestMapping("putOne")
    @LogAopAnnotation(value = "新增数据", operatorTable = "sws_sys_log")
    public BaseResponse insert(@RequestBody String params) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        DataAcceptDto dto = new Gson().fromJson(params,DataAcceptDto.class);
        try {
            //TODO：将接入数据插入数据库
            dataAcceptService.put(dto);
            logger.info("数据入库 params： {}", params);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}
