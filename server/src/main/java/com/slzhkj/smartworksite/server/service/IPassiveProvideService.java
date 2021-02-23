package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.SearchFormDto;
import com.slzhkj.smartworksite.model.entity.DataProvideLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>数据对外提供（被动）</h1>
 * @author Yuezejian
 * @date 2020年 12月14日 09:15:51
 */
@Service
public interface IPassiveProvideService {

    /**
     * <h2>通过查询条件查询数据对外提供接口（被动）</h2>
     * @param searchForm
     * @return
     */
     List<DataProvideLog> getListBySearchForm(SearchFormDto searchForm);

     /**
     * <h2>数据对外提供接口（被动）开放/关闭</h2>
     * @param id 接口唯一标识
     * @param status 接口开放状态
     * @return
     * @throws Exception
     */
    boolean startOrStopScheduled(Integer id, Integer status) throws Exception;

    /**
     * <h2>新增数据对外提供接口（被动）</h2>
     * @param dataProvide {@link DataProvideLog} 数据对外提供接口信息
     * @return
     * @throws IllegalStateException
     */
    boolean insert(DataProvideLog dataProvide) throws IllegalStateException;

    /**
     * <h2>更新数据对外提供接口（被动）</h2>
     * @param dataProvideLog
     * @return
     */
    boolean update(DataProvideLog dataProvideLog);

    /**
     * <h2>通过 ID 获取对外数据提供 （被动） 接口信息</h2>
     * @param id
     * @return
     */
    DataProvideLog getDataProvidePassiveInfoById(Integer id);

    /**
     * <h2>通过 ID 逻辑删除对外数据提供 （被动） 接口信息</h2>
     * @param id
     * @return
     */
    boolean deleteDataProvideLog(Integer id);


}

