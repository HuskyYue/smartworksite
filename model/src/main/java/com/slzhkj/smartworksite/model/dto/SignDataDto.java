package com.slzhkj.smartworksite.model.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 *建筑工人实名制管理平台标准化服务接口
 *访问签名中 data 参数 dto
 *
 * @author Yuezejian
 * @date 2020年 11月05日 09:03:13
 */
public class SignDataDto implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 不知道是什么，实例中为”“
     */
    private String sk;
    /**
     * 项目编码
     */
    private String projectCode;
    /**
     * 请求时间戳。[String(50)] [必填] [格式：时间格式精确到毫秒] [如，2016-5-1 12:05:43.007]
     */
    private String timeStamp;

    public SignDataDto(String sk, String projectCode, String timeStamp) {
        this.sk = sk;
        this.projectCode = projectCode;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (StringUtils.isNotBlank(projectCode)) {
            sb.append("ProjectCode=" + projectCode + ",");
        }
        if ( StringUtils.isNotBlank(timeStamp)) {
            sb.append("TimeStamp=" + timeStamp + ",");
        }
        if (StringUtils.isNotBlank(sk)) {
            sb.append("sk=" + sk + ",");
        }
        sb.append("}");
        return sb.toString();
    }
}
