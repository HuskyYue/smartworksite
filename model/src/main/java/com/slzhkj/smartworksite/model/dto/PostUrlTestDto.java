package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 数据请求URL获取数据测试dto
 *
 * @author Yuezejian
 * @date 2020年 12月07日 16:14:48
 */
@Getter
@AllArgsConstructor
public class PostUrlTestDto implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 进行数据获取的url
     */
    private String postUrl;

    /**
     * 执行定时任务的参数
     */
    private String cronClassParams;
}
