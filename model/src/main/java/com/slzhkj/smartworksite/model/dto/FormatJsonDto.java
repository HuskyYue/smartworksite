package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * 数据请求URL获取数据测试dto
 *
 * @author Yuezejian
 * @date 2020年 12月07日 16:14:48
 */
@Data
public class FormatJsonDto implements Serializable {

    public static final long serialVersionUID = 1L;

    private String jsonData;

    private String dataName;

    private String tableName;

    private String columnName;

    private String columnType;

    private String columnComment;

    private String key;
}
