package com.slzhkj.smartworksite.model.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * 通过条件查询数据库（提供给三方调用）
 *
 * @author Yuezejian
 * @date 2020年 11月10日 08:57:37
 */
@Data
@Getter
public class DBSelectDto {
    /**
     * 查询时传入的参数
     */
    private String paramLine;
    /**
     * 数据库表名
     */
    private String tableName;
    /**
     * 查询条件
     */
    private String  condition;
    /**
     * 验证标识
     */
    private String token;
    /**
     * 调用方唯一标识
     */
    private String appId;
}
