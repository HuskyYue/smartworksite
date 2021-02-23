package com.slzhkj.smartworksite.server.controller;

import com.alibaba.fastjson.JSON;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.User;
import com.slzhkj.smartworksite.server.service.IExternalUserService;
import org.springframework.web.bind.annotation.*;


/**
 * <h1>外部用户管理相关控制器 </h1>
 * @author Yuezejian
 * @date 2020年 12月18日 13:59:16
 */
@RestController
@RequestMapping("user")
public class ExternalUserController extends AbstractController{

    /** 外部用户管理相关服务 */
    private final IExternalUserService externalUserService;

    public ExternalUserController(IExternalUserService externalUserService) {
        this.externalUserService = externalUserService;
    }


    /**
     * url: /user/insert
     * <h2>新增外部用户信息</h2>
     * @param user {@link User}
     * @return
     */
    @PostMapping("insert")
    public BaseResponse insertExternalUser(@RequestBody User user) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(externalUserService.insertExternalUser(user));
            logger.info("新增外部用户信息: {} ", JSON.toJSONString(user));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /user/delete
     * <h2>删除外部用户信息</h2>
     * @param id
     * @return
     */
    @PostMapping("delete")
    public BaseResponse deleteExternalUser(@RequestParam Integer id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(externalUserService.deleteExternalUser(id));
            logger.info("删除新增外部用户信息 id : {} ", id);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /user/update
     * <h2>更新外部用户信息</h2>
     * @param user {@link User}
     * @return
     */
    @PostMapping("update")
    public BaseResponse updateExternalUser(@RequestBody User user) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(externalUserService.updateExternalUser(user));
            logger.info("修改外部用户信息: {} ", JSON.toJSONString(user));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /user/list
     * <h2>获取外部用户信息列表</h2>
     * @param search {@link SearchFormDto} 查询条件
     * @return
     */
    @PostMapping("list")
    public BaseResponse listExternalUser(@RequestBody SearchFormDto search) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(externalUserService.listExternalUser(search));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * url: /user/get
     * <h2>获取外部用户信息列表</h2>
     * @param id
     * @return
     */
    @PostMapping("get")
    public BaseResponse getExternalUser(@RequestParam Integer id) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(externalUserService.getExternalUser(id));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

}
