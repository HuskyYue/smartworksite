package com.slzhkj.smartworksite.model.entity;

import com.slzhkj.smartworksite.model.constant.Constant;
import com.slzhkj.smartworksite.model.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Synchronized;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 数据提供dto
 *
 * @author Yuezejian
 * @date 2020年 12月14日 09:00:06
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataProvideLog extends BaseDto {

    /**
     * 唯一标识主键
     */
    private Integer id;

    /**
     * API 名称
     */
    private String apiName;

    /**
     * 对外暴露的 API url 地址
     */
    private String apiUrl;

    /**
     * API 状态 1、对外开放中 0、未对外开放
     */
    private Integer apiStatus;

    /**
     * 获取次数限制 1-3-5 => 每天内最多5次
     * 第一位，次数；
     * 第二位，时间单位（0，秒； 1，分钟； 2，小时； 3，天； 4，周； 5，月； 6，年）；
     * 第三位，时间长度
     */
    private String acquireTimesLimit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订阅者列表
     */
    private String userId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public DataProvideLog(Integer id, Integer apiStatus) {
        this.id = id;
        this.apiStatus = apiStatus;
    }

    private static volatile DataProvideLog instance;

    public static DataProvideLog getInstance() {
        if (null == instance) {
            synchronized (DataProvideLog.class) {
                if (null == instance) {
                    instance = new DataProvideLog();
                }
            }
        }
        return instance;
    }


    /**
     * 解析获取次数限制
     * @param before {@link DataProvideLog} 获取次数限制未经解析的 DataProvideLog 对象
     * @return DataProvideLog 获取次数限制已经解析的 DataProvideLog 对象
     */
    public static DataProvideLog acquireTimesLimit2acquireTimesLimitDescription(DataProvideLog before) {
        Objects.requireNonNull(before);
        String[] acquireTimesLimitStr = StringUtils.split(before.getAcquireTimesLimit(), Constant.Number2StringSplit);
        if (acquireTimesLimitStr != null && acquireTimesLimitStr.length == 3) {
            StringBuilder res = new StringBuilder("每");
            String[] timeUnit = {"秒", "分钟", "小时", "天", "周", "月", "年"};
            Integer timeCount = Integer.valueOf(acquireTimesLimitStr[0]);
            Integer timeUnitIndex = Integer.valueOf(acquireTimesLimitStr[1]);
            Integer times = Integer.valueOf(acquireTimesLimitStr[2]);
            res.append(timeCount)
                    .append(timeUnit[timeUnitIndex])
                    .append("内最多访问").append(times + "次");
            before.acquireTimesLimit = res.toString();
            return before;
        } else {
            throw new IllegalArgumentException("获取次数限制的描述传入参数错误");
        }
    }
}
