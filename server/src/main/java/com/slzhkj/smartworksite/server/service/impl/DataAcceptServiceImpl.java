package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.dto.DataAcceptDto;
import com.slzhkj.smartworksite.model.mapper.DataAcceptMapper;
import com.slzhkj.smartworksite.server.service.DataAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>数据接入Service实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月03日 11:10:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataAcceptServiceImpl implements DataAcceptService {

    @Autowired
    DataAcceptMapper dataAcceptMapper;

    /**
     * <h2>数据接入数据库</h2>
     * @param dto
     * @return
     */
    @Override
    public int put(DataAcceptDto dto) {
       return dataAcceptMapper.put(dto);
    }
}
