package com.slzhkj.smartworksite.server.controller;

import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.server.service.InsertModelService;
import com.slzhkj.smartworksite.server.utils.RequestParamsToMap;
import com.slzhkj.smartworksite.server.utils.db;
import com.slzhkj.smartworksite.server.utils.func;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <h1>数据库表相关操作Controller</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月06日 15:23:33
 */
@RestController
@RequestMapping("openApi")
public class TableOperatorController extends AbstractController{

    @Autowired
    InsertModelService insertModelService;

    /**
     *  url: /openApi/createTable+
     *  <h2>数据库建表</h2>
     * @param request
     * @return
     */
    @RequestMapping(value = "createTable",method = {RequestMethod.POST})
    public BaseResponse createTable(HttpServletRequest request) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String, Object> data = RequestParamsToMap.getParameterMap(request);
        //TODO: 取出用来建表的“ 表名 ”
        String table = String.valueOf(data.get("table"));
        //TODO: 取出用来建表的“ json示例 ”
        String json = String.valueOf(data.get("json"));
        String ticket = func.randStr(16);
        try {
            if (StringUtils.isNotBlank(table) && StringUtils.isNotBlank(json)) {
                //TODO: 先去查看静态注册表内是否已存在
                if (db.schmema.containsKey(table)) {
                    return new BaseResponse(StatusCode.TableNameHasExist);
                }
                //TODO: 再去查 redis 缓存中对表名的维护 (每次系统启动，会进行数据预热)
                //TODO：最后去查看数据库中是已有当前表名
                int res = insertModelService.selectByTableName(table);
                if (res > 0) {
                    return new BaseResponse(StatusCode.TableNameHasExist);
                }
                //TODO: 确定没有该表名，进行建表操作, 返回不重复的随机码
                try {
                    db.initTable(table);
                }catch (Exception e) {
                    logger.error("初始化表出错 error: {}" , e.getMessage());
                }
                db.json2mysql(table, json);
                response.setData(ticket);
                logger.info(" 数据据成功建表 table：{}", table);
                //TODO: 成功建表后写入注册表
                db.schmema.put(table,null);
                //TODO: 更新 redis 缓存
            } else {
                return new BaseResponse(StatusCode.TableNameOrJsonIsNull);
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

}
