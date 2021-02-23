package com.slzhkj.smartworksite.model.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Type;

/**
 * 三方返回结果，反序列化dto
 *
 * @author Yuezejian
 * @date 2020年 11月09日 10:23:44
 */
@AllArgsConstructor
public class ResultDto {
    /**
     * 实际返回数据
     */
    private String data;
    /**
     * 响应信息信息
     */
    private String message;
    /**
     * 响应码
     */
    private String code;

    public String getData() {
        return data;
    }


    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
