package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * 登录dto
 *
 * @author Yuezejian
 * @date 2020年 11月30日 14:17:24
 */
@Getter
@AllArgsConstructor
public class LoginDto {

    /**
     * id 生成表的唯一主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 用于监测登录情况
     * @return
     */
    public static LoginDto getInvalidLoginDto() {
        return new LoginDto(-1,"#","#");
    }

}
