package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.FormatJsonDto;
import com.slzhkj.smartworksite.model.dto.ProvideMappersDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库表操作mapper
 *
 * @author Yuezejian
 * @date 2020年 11月06日 11:00:04
 */
public interface DataBaseMapper {

    /**
     * 查询该表名数据库内是否已经存在
     *
     * @param table 表名
     * @return
     */
    int selectByTableName(@Param("table") String table);

    /**
     * 获取关联映射表中所有的唯一主键 cronId, 该字段为 映射表与任务表的关联字段
     * @return cronIds
     */
    List<String> listCronIdsInTableMappers();

    List<FormatJsonDto> getDataList(FormatJsonDto dto);

    List<FormatJsonDto> getUrlList(FormatJsonDto dto);

    List<FormatJsonDto> getTableList(FormatJsonDto dto);

    List<FormatJsonDto> getColumnList(FormatJsonDto dto);

    Integer saveMapper(ProvideMappersDto dto);

}
