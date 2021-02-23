package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.SchedulerDto;

import java.util.List;

/**
 * 定时任务执行器 mapper
 *
 * @author Yuezejian
 * @date 2020年 12月08日 11:05:09
 */
public interface SchedulerMapper {

    /**
     * 查询全部执行器信息
     * @return
     */
    List<SchedulerDto> selectAll();
}
