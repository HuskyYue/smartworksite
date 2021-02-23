package com.slzhkj.smartworksite.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据请求URL获取数据测试dto
 *
 * @author Yuezejian
 * @date 2020年 12月07日 16:14:48
 */
@Data
public class ProvideMappersDto implements Serializable {

    public static final long serialVersionUID = 1L;

    private String id;

    private String postUrl;  //url接口路径

    private String database;  //我方数据库

    private String table;  //我方数据表

    private String fields;  //被选的表字段组（有序与mapper对应）以-拼接

    private String mappers;  //对外提供字段名称（有序与fields对应）以-拼接

    private String alias;  //对外提供字段别名（有序与fields对应），以-拼接
}
