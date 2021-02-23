package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * vue 接入 用户
 *
 * @author Yuezejian
 * @date 2020年 12月03日 14:32:34
 */
@Getter
@AllArgsConstructor
public class UserDto {

    /**
     * 用户名
     */
    private String username;
    /**
     * 更新时间
     */
    private String password;
}
