package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 数据库建表dto
 *
 * @author Yuezejian
 * @date 2020年 11月06日 09:51:37
 */
@Data
@Getter
@AllArgsConstructor
public class TableCreatedDto {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 用来建表的 json 示例
     */
    private String firstJson;
}
