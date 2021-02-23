package com.slzhkj.smartworksite.model.dto;

import lombok.Getter;

/**
 * 传入sql进行查询
 *
 * @author Yuezejian
 * @date 2020年 10月30日 17:01:55
 */
@Getter
public class SelectBySqlDto {
    /**
     * token
     */
    private String token;
    /**
     * sql参数
     */
    private String sql;

    @Override
    public String toString() {
        return "SelectBySqlDto{" +
                "token='" + token + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
