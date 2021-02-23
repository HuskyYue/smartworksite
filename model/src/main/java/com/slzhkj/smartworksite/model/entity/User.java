package com.slzhkj.smartworksite.model.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户实体类
 *
 * @author Yuezejian
 * @date 2020年 08月22日 19:41:16
 */
@Getter
@NoArgsConstructor
@ToString
public class User implements Serializable{
    public static final long serialVersionUID = 1L;

    /** 唯一主键 */
    private Integer id;

    /** 用户名 */
    private String userName;

    /** 公司归属 */
    private String company;

    /** 公司唯一标识 */
    private String appId;

    /** 秘钥 */
    private String secret;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 备注 */
    private String remark;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    private static volatile User instance;
    public static User getInstance() {
        if (null == instance) {
            synchronized (User.class) {
                if (null == instance) {
                    instance = new User();
                }
            }
        }
        return instance;
    }
}