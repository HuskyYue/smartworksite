package com.slzhkj.smartworksite.model.mapper;


import com.slzhkj.smartworksite.model.entity.SysLog;

/**
 * 操作日志mapper
 *
 * @author Yuezejian
 * @date 2020年 08月22日 19:51:25
 */
public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}