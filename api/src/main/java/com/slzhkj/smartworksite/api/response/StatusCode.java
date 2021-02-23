package com.slzhkj.smartworksite.api.response;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 状态码
 *
 * @author Yuezejian
 * @date 2020年 08月22日 14:36:55
 */
@Getter
public enum StatusCode {
    Success(200,"success"),
    Fail(500,"false"),
    InvalidParams(-1,"参数错误！"),
    UserNameHasExist(301,"用户名已存在"),
    UserEmailHasExist(302,"用户邮箱已存在"),
    InvalidArticleId(303,"非法文章ID"),
    InvalidCurrentUserId(304,"非法用户ID"),
    TableNameHasExist(305,"表名已存在"),
    TableNameOrJsonIsNull(306,"表名或进行初始化建表的json示例为空"),
    SystemError(307,"系统数据请求错误"),
    InvalidToken(308," Token 无效"),
    InvalidAppId(309,"AppId 无效"),
    InvalidPermission(310,"该账户未成功激活，请联系管理员，Insufficient permissions, please contact administrator!"),
    InvalidCron(311,"该定时任务不存在！"),
    LoginPermission(20000,"成功获取权限")

    ;

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}