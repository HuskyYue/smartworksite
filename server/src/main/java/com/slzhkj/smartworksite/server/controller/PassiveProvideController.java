package com.slzhkj.smartworksite.server.controller;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.DataProvideLog;
import com.slzhkj.smartworksite.server.service.IPassiveProvideService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <h1>对外数据提供 （被动）</h1>
 * @author Yuezejian
 * @date 2020年 12月11日 16:59:26
 */
@RestController
@RequestMapping("provide/passive")
@SuppressWarnings("all")
public class PassiveProvideController extends AbstractController {

    @Autowired
    IPassiveProvideService passiveProvideService;

    /**
     * url: /provide/passive/list
     * <h2>根据条件查询对外数据提供接口信息</h2>
     * @param searchForm {@link SearchFormDto} 查询条件领域模型
     * @return
     */
    @RequestMapping("list")
    public BaseResponse listDataProvideLogBySearchForm( @RequestBody SearchFormDto searchForm ) {
        Integer currentPage = searchForm.getCurrentPage();
        Integer pageSize = searchForm.getPageSize();
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (currentPage <= 0 || !StringUtils.isNotBlank(String.valueOf(currentPage)) ||
                pageSize <= 0 || !StringUtils.isNotBlank(String.valueOf(pageSize))) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        try {
            response.setData(passiveProvideService.getListBySearchForm(searchForm));
        } catch (Exception e) {
            logger.error("根据条件查询对外数据提供接口信息出现异常：{}", e.getMessage());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /provide/passive/insert
     * 新增对外数据提供接口
     * @param dataProvidePassive {@link DataProvideLog}
     * @return
     */
    @PostMapping("insert")
    public BaseResponse insertDataProvideLog(@RequestBody DataProvideLog dataProvidePassive) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(
                    passiveProvideService.insert(dataProvidePassive)
            );
            logger.info("数据库新增对外数据提供接口信息 data： {}",
                    JSON.toJSONString(dataProvidePassive));
        } catch (Exception e) {
            logger.error("数据库新增对外数据提供接口信息出现异常" +
                    e.getMessage());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /provide/passive/startOrStopScheduled
     * <h2>对外数据提供 （被动） 接口，一键开放/关闭接口</h2>
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
            //TODO: 对外数据提供 （被动） 接口  status : 0 关闭（不允许访问）， 1开放（允许访问）
            boolean flag = passiveProvideService.startOrStopScheduled(id, status);
            if (flag && status == 1) {
                response.setMsg("接口已经开放，允许被访问");
            } if (flag && status == 0) {
                response.setMsg("接口已关闭，无法被访问");
            }
            response.setData(flag);
            logger.info("id为： {} 的对外数据提供 （被动） 接口，已开放/关闭成功,状态变更为 {} ",
                    id, status);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), "系统异常！"
                    + e.getMessage());
        }
        return response;
    }

    /**
     * url: /provide/passive/updateById
     * <h2>通过 ID 更新对外数据提供 （被动） 接口信息</h2>
     * @return
     */
    @PostMapping("updateById")
    public BaseResponse updateDataProvideLogById(@RequestBody DataProvideLog dataProvidePassive) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(passiveProvideService.update(dataProvidePassive));
            logger.info("通过 ID 更新对外数据提供 （被动） 接口信息: {}", JSON.toJSONString(dataProvidePassive));
        } catch (Exception e) {
            logger.error("通过 ID 更新对外数据提供 （被动） 接口信息: {}, 出现异常：{}", JSON.toJSONString(dataProvidePassive), e.getMessage());
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /provide/passive/getDataProvidePassiveInfoById
     * <h2>通过 ID 获取对外数据提供 （被动） 接口信息</h2>
     * @return
     */
    @GetMapping("getDataProvidePassiveInfoById")
    public BaseResponse getDataProvidePassiveInfoById(@RequestParam String id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(passiveProvideService.getDataProvidePassiveInfoById(Integer.valueOf(id)));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /provide/passive/delete
     * <h2>删除对外数据提供 （被动） 接口信息</h2>
     * @param id
     * @return
     */
    @GetMapping("delete")
    public BaseResponse deleteDataProvideLog(@RequestParam Integer id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (id < 0 || !StringUtils.isNotBlank(String.valueOf(id))) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        try {
            //TODO: 逻辑删除定时器任务
            response.setData(passiveProvideService.deleteDataProvideLog(id));
            logger.info("id为：{} 的对外数据提供 （被动） 接口信息: {}，已经被逻辑删除 ", id);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

}
