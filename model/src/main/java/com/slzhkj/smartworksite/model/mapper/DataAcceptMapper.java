package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.DataAcceptDto;

/**
 * 数据接入Mapper
 *
 * @author Yuezejian
 * @date 2020年 11月03日 11:18:54
 */
public interface DataAcceptMapper {

    /**
     * 数据接入数据库
     * @param dto
     * @return
     */
    int put(DataAcceptDto dto);
}
