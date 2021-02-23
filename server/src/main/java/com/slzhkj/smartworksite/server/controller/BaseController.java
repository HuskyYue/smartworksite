package com.slzhkj.smartworksite.server.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.slzhkj.smartworksite.model.dto.*;
import com.slzhkj.smartworksite.model.mapper.RequestMapper;
import com.slzhkj.smartworksite.server.service.DataAcceptService;
import com.slzhkj.smartworksite.server.service.ILoginService;
import com.slzhkj.smartworksite.server.service.InsertModelService;
import com.slzhkj.smartworksite.server.service.RequestService;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.server.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * 统一响应模型controller
 *
 * @author Yuezejian
 * @date 2020年 08月22日 15:06:33
 */
@RestController
@RequestMapping("test")
@CrossOrigin
public class BaseController extends AbstractController{

    @Resource
    private HttpServletResponse response1;

    @Resource
    private HttpSession session;

    @Autowired
    RequestMapper requestMapper;

    @Autowired
    InsertModelService insertModelService;

    @Autowired
    DataAcceptService dataAcceptService;

    @Autowired
    RequestService requestService;

    @Autowired
    private ILoginService loginService;
    /**
     * 请求建筑工人实名制管理平台标准化服务接口，获取对应的签名
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

    @RequestMapping("info")
    public BaseResponse info() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
//        SignDto signDto = new Gson().fromJson(params,SignDto.class);
        try {
            SignDataDto dataDto = new SignDataDto("","12700","2016-5-1 10:05:43.007");
            //TODO 专门用来测试的
//            SignCreatedUtils signUtils = SignCreatedUtils.getInstance();
//            String res = signUtils.sign(signDto);
//            response.setData(res);
            String table = "swstest";
            int res = insertModelService.selectByTableName(table);
            response.setData(res);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * 测试模式
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/test",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse test(HttpServletRequest request) {
        String ticket = func.randStr(16);
        Map<String, Object> data = RequestParamsToMap.getParameterMap(request);
        String table = String.valueOf(data.get("table"));
        String json = String.valueOf(data.get("json"));
        System.out.println("table:   " + table);
        System.out.println("json:    " + json);
        if(StringUtils.isNotBlank(table) && StringUtils.isNotBlank(json)) {
            db.initTable(table);
            if(!db.schmema.containsKey(table)) {
                return new BaseResponse(StatusCode.Fail);
            }
            db.json2mysql(table,json);
        } else {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        return new BaseResponse(StatusCode.Success);
    }

    /**
     * 测试模式2（三方数据接入）
     *
     * @return the string
     */
    @RequestMapping(value = "/insert",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse testTwo(@RequestBody String params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject = jsonObject.fromObject(params);;//将String转为JSON数据
        String data = jsonObject.getString("data");
        JSONArray json = JSONArray.fromObject(data);
        String appId = json.getJSONObject(0).getString("app_id");
        String token = json.getJSONObject(0).getString("token");
    if(StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(token)) {
        DataAcceptDto dto1 = new DataAcceptDto(appId,token);
        dataAcceptService.put(dto1);
        logger.info("数据入库 params： {}", params);
        } else {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        return new BaseResponse(StatusCode.Success);
    }

    /**
     * 测试模式1（三方数据抽取）
     * @param params
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public BaseResponse query(@RequestBody String params) {
        Gson gson = new Gson();
        SelectBySqlDto dto = gson.fromJson(params,SelectBySqlDto.class);
//        String sql = dto.getSql();
//        String token = dto.getToken();
        String sql = "select t.token,t.app_id from sws_request_log t limit 1";
        String token = "yuezejian";
        Object r;
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (StringUtils.isNotBlank(sql)&& StringUtils.isNotBlank(token)) {
            if (!token.equals("yuezejian")) {
                return new BaseResponse(StatusCode.InvalidParams);
            }
        }
        //TODO 核心业务逻辑
        r = db.fetch_all(sql);
        response.setData(r);

        return response;
    }

    @RequestMapping("/startscheduler")
    public void start() throws Exception {
        ScheduleUtils.Job job1 = new ScheduleUtils.Job();
        job1.setClassName("com.slzhkj.smartworksite.server.scheduler.DataRequestScheduled");
        job1.setCron("*/5 * * * * *");
        job1.setJobName("定时器1");
        job1.setMethodName("scheduled");
        job1.setStatus(1);
        ScheduleUtils.add(job1);
    }

    @RequestMapping("/stopscheduler")
    public void stop2() throws Exception {
        ScheduleUtils.Job job1 = new ScheduleUtils.Job();
        job1.setClassName("com.slzhkj.smartworksite.server.scheduler.DataRequestScheduled");
        job1.setCron("*/5 * * * * *");
        job1.setJobName("定时器1");
        job1.setMethodName("scheduled");
        job1.setStatus(1);
        ScheduleUtils.cancel(job1);
        ScheduleUtils.cancel(job1);

    }

    /**
     * 登录测试
     * @return 结果
     */
    @RequestMapping(value = "token", method = RequestMethod.GET)
    public BaseResponse login(@RequestParam String userName, String password)
    {
        LoginDto loginDto = new LoginDto(1,userName,password);
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.convertValue(loginDto, new TypeReference<Map<String, Object>>() {
            });
            map.put("token", "admin-token");
            map.remove("password");

            session.setAttribute("admin", "admin");
            // 目前暂时将value写死
            session.setAttribute(
                    FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "admin");
            response1.addHeader("jsessionid", EncryptUtils.base64Encode(session.getId()));
            //session有效期：1小时

            session.setMaxInactiveInterval(60 * 60);
            response.setData(map);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


}
