package com.slzhkj.smartworksite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 定时任务执行器dto
 *
 * @author Yuezejian
 * @date 2020年 12月08日 10:59:10
 */
@Getter
@AllArgsConstructor
public class SchedulerDto implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 执行器对应类
     */
    private String cronClass;

    /**
     * 执行器对应方法
     */
    private String cronMethod;
}
