package com.slzhkj.smartworksite.model.dto;

import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * 查询条件
 * @author Yuezejian
 * @date 2020年 12月11日 10:18:46
 */
@Getter
public class SearchFormDto {

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 定时器名称
     */
    private String cronName;

    /**
     * 进行数据获取的url
     */
    private String postUrl;
    /**
     * 进行数据接入的url
     */
    private String getUrl;

    /**
     * 是否开启当前定时任务（1，开启  0，不开启）
     */
    private Integer cronStatus;

    /**
     * 查询开始时间
     */
    private String startTime;

    /**
     * 主动请求和被动请求的标识符 1.主动请求 0.被动请求
     */
    private Integer classify;

    /**
     * 查询结束时间
     */
    private String endTime;

    /**
     * 数据提供的 API 名称
     */
    private String apiName;

    /**
     * 数据提供的 API url 地址
     */
    private String apiUrl;

    /**
     * 数据提供的 API 状态
     */
    private String apiStatus;

    /** 外部用户名称 */
    private String userName;

    /** 外部用户所属于的公司 */
    private String company;

    /** 外部用户唯一标识 id */
    private String appId;


}
