package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.DataProvideLog;

import java.util.List;

/**
 * <h1>数据提供接口（被动）mapper</h1>
 * @author Yuezejian
 * @date 2020年 12月14日 09:30:58
 */
public interface PassiveProvideMapper {

    /**
     * <h2>根据查询条件查询全部接口信息</h2>
     * @param searchFormDto {@link SearchFormDto} 查询条件领域模型
     * @return
     */
    List<DataProvideLog> selectAllBySearchForm(SearchFormDto searchFormDto);

    /**
     * <h2>查询全部有效的接口信息</h2>
     * @return
     */
    List<DataProvideLog> selectAll();

    /**
     * <h2>查询全部接口信息(包括无效数据，用于防重校验)</h2>
     * @return
     */
    List<DataProvideLog> selectAllIncludeUnable();

    /**
     * <h2>新增数据提供接口（被动）</h2>
     * @param dataProvideLog {@link DataProvideLog}
     * @return
     */
    boolean insert(DataProvideLog dataProvideLog);

    /**
     * <h2>更新数据提供接口（被动）</h2>
     * @param dataProvideLog
     * @return
     */
    boolean update(DataProvideLog dataProvideLog);

    /**
     * <h2>通过 id 逻辑删除数据提供接口（被动）</h2>
     * @param id
     * @return
     */
    boolean delete(Integer id);

}
