package com.slzhkj.smartworksite.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import com.slzhkj.smartworksite.model.dto.FormatJsonDto;
import com.slzhkj.smartworksite.model.dto.ProvideMappersDto;
import com.slzhkj.smartworksite.server.service.DataFormatService;
import com.slzhkj.smartworksite.server.utils.DataFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <h1>对外数据提供 （被动）</h1>
 * @author Yuezejian
 * @date 2020年 12月11日 16:59:26
 */
@RestController
@RequestMapping("/database")
public class DataFormatController extends AbstractController {

    @Autowired
    DataFormatService dataFormatService;

    /**
     * url: /database/format/json
     * <h2>表关联JSON 解析</h2>
     * @param keys {@link FormatJsonDto}
     * {
     *     "jsonData":"[{\"key\":\"value\"},{\"key\":\"value\"}]"
     * }
     * @return
     * {
     *     "code":200,
     *     "msg":"success",
     *     "data":
     *     [{
     *     "key":"value"
     *     },{
     *     "key":"value"
     *     }]
     * }
     */
    @ResponseBody
    @PostMapping("/format/json")
    public BaseResponse formatJson(@RequestBody FormatJsonDto keys) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //TODO 专门用来测试的
            String jsonData = keys.getJsonData();
            //字符串转map
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            Map<String,Object> map = (Map<String,Object>)jsonObject;//    //json对象转Map
            logger.info("执行的参数为：{}",map);
//            List<FormatJsonDto> list= dataFormatService.getTableList(keys);
           List list= DataFormatUtils.mapTransitionList(map);
            response.setData(list);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * url: /database/data/list
     * <h2>查询 数据库</h2>
     * @return
     */
    @ResponseBody
    @PostMapping("/data/list")
    public BaseResponse getDataList(@RequestBody FormatJsonDto keys)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<FormatJsonDto> list= dataFormatService.getDataList(keys);
            response.setData(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * url: /database/table/list
     * <h2>查询 表</h2>
     * @params keys FormatJsonDto  dataName 库名
     * {
     *    "dataName":"smart_work_site"
     * }
     * @return
     *{
     * "code":200,
     * "msg":"success",
     * "data":
     *       [{
     *       "dataName":"smart_work_site",
     *       "tableName":"metadata"
     *       }]
     * }
     */
    @ResponseBody
    @PostMapping("/table/list")
    public BaseResponse getTableList(@RequestBody FormatJsonDto keys)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<FormatJsonDto> list= dataFormatService.getTableList(keys);
            response.setData(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * url: /database/column/list
     * <h2>查询 字段</h2>
     * @params keys FormatJsonDto  tableName 表名
     * {
     *    "dataName":"smart_work_site",
     *    "tableName":"metadata"
     * }
     * @return
     *{
     * "code":200,
     * "msg":"success",
     * "data":
     *       [{
     *       "dataName":"smart_work_site",
     *       "tableName":"metadata",
     *       "columnName":"table",
     *       "columnType":"varchar(255)"
     *       }]
     * }
     */
    @ResponseBody
    @PostMapping("/column/list")
    public BaseResponse getColumnList(@RequestBody FormatJsonDto keys)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<FormatJsonDto> list= new ArrayList<>();
            if(keys!=null&&keys.getTableName()!=null&&!"".equals(keys.getTableName())){
               list= dataFormatService.getColumnList(keys);
            }
            response.setData(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * url: /database/mapper/save
     * <h2>保存 映射关系</h2>
     * @params keys FormatJsonDto  tableName 表名
     * {
     *    "url":"",
     *    "database":"",
     *    "table":"",
     *    "fields":"",
     *    "mappers":"",
     *    "alias":""
     * }
     * @return
     */
    @ResponseBody
    @PostMapping("/mapper/save")
    public BaseResponse saveMapper(@RequestBody ProvideMappersDto keys)
    {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            dataFormatService.saveMapper(keys);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }
}
