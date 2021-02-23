package com.slzhkj.smartworksite.model.dto;

import java.io.Serializable;

/**
 * Token vue接入后台测试实例
 *
 * @author Yuezejian
 * @date 2020年 12月02日 10:37:32
 */
public class Token implements Serializable {

    public static final long serialVersionUID = 1L;

    private String token;

    public Token(String token) {
        this.token = token;
    }


}
