package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.LoginDto;
import com.slzhkj.smartworksite.model.entity.Cron;

import java.util.List;

/**
 * 用户登录mapper
 *
 * @author Yuezejian
 * @date 2020年 11月30日 14:16:45
 */
public interface LoginMapper {

    /**
     * 查询全部用户信息
     * @return
     */
    List<LoginDto> selectAll();
}
