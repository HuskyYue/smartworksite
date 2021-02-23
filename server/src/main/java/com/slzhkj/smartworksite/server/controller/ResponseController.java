package com.slzhkj.smartworksite.server.controller;

import com.google.gson.Gson;
import com.slzhkj.smartworksite.model.dto.DBSelectDto;
import com.slzhkj.smartworksite.model.dto.RequestDto;
import com.slzhkj.smartworksite.model.mapper.RequestMapper;
import com.slzhkj.smartworksite.server.utils.db;
import com.slzhkj.smartworksite.api.response.BaseResponse;
import com.slzhkj.smartworksite.api.response.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * <h1>数据提供相关操作的控制器</h1>
 *
 * @author Yuezejian
 * @date 2020年 10月30日 15:57:15
 */
@RestController
@RequestMapping("response")
public class ResponseController extends AbstractController{

    @Autowired
    Environment environment;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RequestMapper requestMapper;

    /**
     * url: /response/query
     * <h2>（对外提供）统一查询接口</h2>
     * @param params
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public BaseResponse query(@RequestBody String params) {
        Gson gson = new Gson();
        //TODO:数据接入
        DBSelectDto dto = gson.fromJson(params,DBSelectDto.class);
        String paramLine = dto.getParamLine();
        String tableName = dto.getTableName();
        String token = dto.getToken();
        String appId = dto.getAppId();
        Object r;
        //TODO: condition 默认为有效数据
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(dto.getCondition())) {
            sb.append(dto.getCondition()).append(" and is_del = 1");
        } else {
            sb.append(" is_del = 1");
        }
        String condition = sb.toString();
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if (!StringUtils.isNotBlank(paramLine) || !StringUtils.isNotBlank(tableName) ||
               !StringUtils.isNotBlank(token) || !StringUtils.isNotBlank(appId)
        ) {
            return new BaseResponse(StatusCode.InvalidParams);
        }

        //TODO: Judge whether the token has been registered in BloomFilter successfully,
        // and go through bloom filter to prevent cache penetration
        if (!filter.mightContain(token)) {
            return new BaseResponse(StatusCode.InvalidToken);
        }
        if (!filter.mightContain(appId)) {
            return new BaseResponse(StatusCode.InvalidAppId);
        }
        //TODO：Judge whether the token has been registered in redis successfully
        ValueOperations<String,String> tokenOpera = redisTemplate.opsForValue();
        String tokenInRedis = tokenOpera.get(appId);
        try {
            if (token.equals(tokenInRedis)) {
                //TODO: 允许进行数据库查询操作
                String sql = concat(paramLine,tableName,condition);
                r = db.fetch_all(sql);
                response.setData(r);
                logger.info("appId: {},执行sql: {}"+appId+ "---" + sql);
            } else {
                //TODO: 查询数据库 token 是否有效
                List<RequestDto> requestDtoList = requestMapper.selectTokenRecord();
                requestDtoList.stream()
                        .filter( requestDto -> appId.equals(requestDto.getAppId()) && token.equals(requestDto.getToken()))
                        .parallel()
                        .findAny()
                        .orElseThrow(() -> new IllegalAccessException("token 令牌： {}，已经失效，不可使用 :" + token ));

                //TODO: 允许进行数据库查询操作,同时更新 redis 缓存
                tokenOpera.getAndSet(appId,token);
                String sql = concat(paramLine,tableName,condition);
                System.out.println(sql);
                r = db.fetch_all(sql);
                response.setData(r);
                logger.info("appId: {},执行sql: {}"+appId+ "---" + sql);
            }

        } catch (Exception e) {
            BaseResponse response1 = new BaseResponse(StatusCode.SystemError);
            response1.setData(e.getMessage());
            return response1;
        }
       return response;
    }

    /**
     * <h2>sql 语句拼接</h2>
     * @param paramLine 参数
     * @param tableName 表名
     * @param condition 条件
     * @return
     */
    private String concat(String paramLine, String tableName, String condition) {
        Objects.requireNonNull(paramLine);
        Objects.requireNonNull(tableName);
        return  String.format("select %s from %s where %s" ,paramLine, tableName, condition);

    }


}
