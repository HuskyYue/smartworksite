package com.slzhkj.smartworksite.model.entity;

import com.slzhkj.smartworksite.model.constant.Constant;
import com.slzhkj.smartworksite.model.dto.BaseDto;
import com.slzhkj.smartworksite.model.dto.SchedulerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 自定义定时器配置类
 *
 * @author Yuezejian
 * @date 2020年 11月09日 09:30:23
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cron extends BaseDto implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 定时器唯一标识
     */
    private String cronId;

    /**
     * 定时器名称
     */
    private String cronName;
    /**
     * 当前定时任务类名
     */
    private String cronClass;
    /**
     * 当前定时任务具体方法名
     */
    private String cronMethod;
    /**
     * 执行周期表达式
     */
    private String cron;
    /**
     * 进行数据获取的url
     */
    private String postUrl;
    /**
     * 进行数据接入的url
     */
    private String getUrl;
    /**
     * 执行定时任务的参数
     */
    private String cronClassParams;
    /**
     * 是否开启当前定时任务（1，开启  0，不开启）
     */
    private Integer cronStatus;
    /**
     * 当前任务的备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 映射表的唯一主键
     */
    private String mapperUrl;

    /**
     * 主动请求和被动请求的标识符 1.主动请求 0.被动请求
     */
    private Integer classify;

    /**
     * 是否进行逻辑删除（1，可用  0，不可用）
     */
    private Integer isDel;

    public void setCronId(String cronId) {
        this.cronId = cronId;
    }

    public void setCronClass(String cronClass) { this.cronClass = cronClass; }

    public void setCronMethod(String cronMethod) { this.cronMethod = cronMethod; }

    public Cron(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private static volatile Cron instance;

    public static Cron getInstance() {
        instance = new Cron();
        return instance;
    }

    public Cron(String cronClass, String cronMethod) {
        this.cronClass = cronClass;
        this.cronMethod = cronMethod;
    }

    public Cron(Integer id, String cronId, String cronName, String cronClass,
                String cronMethod, Integer cronStatus, String mapperUrl) {
        this.id = id;
        this.cronId = cronId;
        this.cronName = cronName;
        this.cronClass = cronClass;
        this.cronMethod = cronMethod;
        this.cronStatus = cronStatus;
        this.mapperUrl = mapperUrl;
    }

    public Cron(Integer id, String cronId, String cronName, String cronClass,
                String cronMethod, String cron, String postUrl, String getUrl,
                String cronClassParams, Integer cronStatus, String remark,
                Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.cronId = cronId;
        this.cronName = cronName;
        this.cronClass = cronClass;
        this.cronMethod = cronMethod;
        this.cron = cron;
        this.postUrl = postUrl;
        this.getUrl = getUrl;
        this.cronClassParams = cronClassParams;
        this.cronStatus = cronStatus;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * 将 Cron 对象转换为 SchedulerDto
     * @param cron
     * @return
     */
    public static SchedulerDto cron2SchedulerDto(Cron cron) {
        return new SchedulerDto(cron.getCronClass(), cron.getCronMethod());
    }
    /**
     * 解析获取次数限制
     * @param before {@link Cron} 获取次数限制未经解析的 Cron 对象
     * @return Cron 获取次数限制已经解析的 Cron 对象
     */
    public static Cron acquireTimesLimit2acquireTimesLimitDescription(Cron before) {
        Objects.requireNonNull(before);
        String[] acquireTimesLimitStr = StringUtils.split(before.getCron(), Constant.Number2StringSplit);
        if (acquireTimesLimitStr != null && acquireTimesLimitStr.length == 3) {
            StringBuilder res = new StringBuilder("每");
            String[] timeUnit = {"秒", "分钟", "小时", "天", "周", "月", "年"};
            Integer timeCount = Integer.valueOf(acquireTimesLimitStr[0]);
            Integer timeUnitIndex = Integer.valueOf(acquireTimesLimitStr[1]);
            Integer times = Integer.valueOf(acquireTimesLimitStr[2]);
            res.append(timeCount)
                    .append(timeUnit[timeUnitIndex])
                    .append("内最多访问").append(times + "次");
            before.cron = res.toString();
            return before;
        } else {
            throw new IllegalArgumentException("获取次数限制的描述传入参数错误");
        }
    }

}
