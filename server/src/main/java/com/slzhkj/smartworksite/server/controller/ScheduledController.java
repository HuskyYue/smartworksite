package com.slzhkj.smartworksite.server.controller;

import com.alibaba.fastjson.JSON;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.Cron;
import com.slzhkj.smartworksite.server.service.CronBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 *<h1>定时器相关操作Controller</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月13日 08:53:55
 */
@RestController
@RequestMapping("scheduled")
public class ScheduledController extends AbstractController {

    @Autowired
    CronBaseService cronBaseService;

    /**
     * url: /scheduled/insert
     * <h2>新增定时器配置</h2>
     * @param cron {@link Cron}
     * @return
     */
    @PostMapping("insert")
    public BaseResponse insertCron(@RequestBody Cron cron) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO: 数据库新增定时器信息
            response.setData(
                    cronBaseService.insert(cron)
            );
            logger.info("数据库新增定时器信息 data： {}", JSON.toJSONString(cron));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
            logger.error("新增定时器出现异常" + e.getMessage());
        }
        return response;
    }

    /**
     * url: /scheduled/list
     * <h2>查询定时器列表</h2>
     * @param searchForm 表单查询条件
     * @return
     */
    @PostMapping("list")
    public BaseResponse listCron( @RequestBody SearchFormDto searchForm ) {
        Integer currentPage = searchForm.getCurrentPage();
        Integer pageSize = searchForm.getPageSize();
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (currentPage <= 0 || !StringUtils.isNotBlank(String.valueOf(currentPage)) ||
                pageSize <= 0 || !StringUtils.isNotBlank(String.valueOf(pageSize))) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        try {
            //TODO: 查询全部定时器信息-
            response.setData(cronBaseService.selectAllBySearchForm(currentPage, pageSize, searchForm));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     *  url: /scheduled/startOrStopScheduled
     * <h2>定时器任务启停接口</h2>
     * @param id
     * @return
     */
    @PostMapping("startOrStopScheduled")
    public BaseResponse startOrStopScheduled(@RequestParam Integer id, Integer status) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (id < 0 || StringUtils.isBlank(String.valueOf(id)) ||
                StringUtils.isBlank(String.valueOf(status))) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        try {
            //TODO: 定时器启动或停止  status : 0 停止， 1启动
            response.setData(cronBaseService.startOrStopScheduled(id, status));
            logger.info("id为： {} 的定时器，已启动/停止成功,状态变更为 {} ", id, status);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), "系统异常！" + e.getMessage());
        }
        return response;
    }


    /**
     * url: /scheduled/delete
     * <h2>删除定时器任务（逻辑删除）</h2>
     * @param id
     * @return
     */
    @PostMapping("delete")
    public BaseResponse deleteCron(@RequestParam Integer id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (id < 0 || !StringUtils.isNotBlank(String.valueOf(id))) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        try {
            //TODO: 逻辑删除定时器任务
            response.setData(cronBaseService.delete(id));
            logger.info("id为：{} 的定时器，已经被逻辑删除 ", id);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /scheduled/getUsableScheduler
     * <h2>查询当前可用的执行器</h2>
     * @return
     */
    @PostMapping("getUsableScheduler")
    public BaseResponse getUsableScheduler() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO: 查询全部定时器信息
            response.setData(cronBaseService.getUsableScheduler());
        } catch (Exception e) {
            logger.error("查询当前可用的执行器出现异常：{}", e.getMessage());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /scheduled/getSchedulerInfoById
     * <h2>通过 ID 查询定时任务信息</h2>
     * @return
     */
    @PostMapping("getSchedulerInfoById")
    public BaseResponse getSchedulerInfoById(@RequestParam String id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO: 查询全部定时器信息
            response.setData(cronBaseService.getSchedulerInfoById(Integer.valueOf(id)));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /scheduled/updateById
     * <h2>通过 ID 更新定时任务信息</h2>
     * @return
     */
    @PostMapping("updateById")
    public BaseResponse updateCronById(@RequestBody Cron cron) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO: 查询全部定时器信息
            response.setData(cronBaseService.update(cron));
            logger.info("通过 ID 更新定时任务信息: {}", JSON.toJSONString(cron));
        } catch (Exception e) {
            logger.error("通过 ID 更新定时任务信息: {}, 出现异常：{}", JSON.toJSONString(cron), e.getMessage());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
   
}

